# -*- coding: utf-8 -*-
from datetime import timedelta

from odoo import models, fields, api
from odoo.exceptions import ValidationError



#Definimos modelo Liga Equipo, que almacenara información de cada equipo
class LigaEquipo(models.Model):

    #Nombre y descripcion del modelo
    _name = 'liga.equipo'

    _description = 'Equipo de la liga'

    #Parametros de ordenacion por defecto
    _order = 'nombre'

    #ATRIBUTOS

    #PARA CUANDO NO HAY UN ATRIBUTO LLAMADO NAME PARA MOSTRAR NOMBRE DE UN REGISTRO
    # https://www.odoo.com/es_ES/forum/ayuda-1/how-defined-display-name-in-custom-many2one-91657
    
    #Indicamos que atributo sera el que se usara para mostrar nombre.
    #Por defecto es "name", pero si no hay un atributo que se llama name, aqui lo indicamos
    #Aqui indicamos que se use el atributo "nombre"
    _rec_name = 'nombre'


    #Atributo nombre
    nombre = fields.Char('Nombre equipo', required=True, index=True)
    #Dato binario, para guardar un binario (en la vista indicaremos que es una imagen) con la portada del comic
    escudo = fields.Image('Escudo equipo',max_width=50,max_height=50)

    #Anyo de fundacion
    fecha_fundacion = fields.Date('Fecha fundación')

    #Campo con HTML (Sanitizado) donde se guarda la descripción del comic
    descripcion = fields.Html('Descripción', sanitize=True, strip_style=False)
    

    #Partidos jugados, ganados, empatados, perdidos
    victorias=fields.Integer(default=0)
    empates=fields.Integer(default=0)
    derrotas=fields.Integer(default=0)
    
    jugados= fields.Integer( compute="_compute_jugados", store=True)
    
    @api.depends('victorias','empates','derrotas')
    def _compute_jugados(self):
        for record in self:
            record.jugados = record.victorias + record.empates + record.derrotas

    
    puntos= fields.Integer( compute="_compute_puntos",default=0, store=True)
    
    @api.depends('victorias','empates')
    def _compute_puntos(self):
        for record in self:
            record.puntos = record.victorias * 3 + record.empates

    
    #Goles a favor y en contra

    goles_a_favor=fields.Integer()
    goles_en_contra=fields.Integer()


    #Constraints de SQL del modelo
    _sql_constraints = [
        ('name_uniq', 'UNIQUE (nombre)', 'El nombre del equipo.')
    ]

    #Constraints de atributos
    @api.constrains('fecha_fundacion')
    def _check_release_date(self):
        for record in self:
            if record.fecha_fundacion and record.fecha_fundacion > fields.Date.today():
                raise models.ValidationError('La fecha de fundación del club debe ser anterior a la actual')


