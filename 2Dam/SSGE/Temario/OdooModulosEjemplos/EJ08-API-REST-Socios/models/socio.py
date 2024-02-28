# -*- coding: utf-8 -*-
from datetime import timedelta

from odoo import models, fields, api
from odoo.exceptions import ValidationError



#Definimos modelo Liga Equipo, que almacenara información de cada equipo
class Socio(models.Model):

    #Nombre y descripcion del modelo
    _name = 'socio'

    _description = 'Modelo par almacenar socios'

    #Parametros de ordenacion por defecto
    _order = 'nombre'

    #ATRIBUTOS

    #PARA CUANDO NO HAY UN ATRIBUTO LLAMADO NAME PARA MOSTRAR NOMBRE DE UN REGISTRO
    # https://www.odoo.com/es_ES/forum/ayuda-1/how-defined-display-name-in-custom-many2one-91657
    
    #Indicamos que atributo sera el que se usara para mostrar nombre.
    #Por defecto es "name", pero si no hay un atributo que se llama name, aqui lo indicamos
    #Aqui indicamos que se use el atributo "nombre"
    _rec_name = 'nombre'


    num_socio= fields.Integer("Numero de socio")
    #Atributo nombre
    nombre = fields.Char('Nombre socio', required=True, index=True)
    apellidos = fields.Char('Apellidos socio', required=True, index=True)
    #Imagen (indicaremos en la vista como mostrarlo)
    foto = fields.Image('Foto socio',max_width=100,max_height=100)

    #Imagen (indicaremos en la vista como mostrarlo)
    barcode_carnet = fields.Image('Barcode carnet',max_width=100,max_height=100)

    #Constraints de SQL del modelo
    _sql_constraints = [
        ('socio_uniq', 'UNIQUE (num_socio)', 'El numero de socio debe ser unico.')
    ]
