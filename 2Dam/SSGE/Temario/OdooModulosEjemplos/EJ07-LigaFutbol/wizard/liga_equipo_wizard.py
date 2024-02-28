# -*- coding: utf-8 -*-
from odoo import models, fields

#Esta clase observamos que hereda de "models.TransientModel" una clase especial
#que crea un modelo, pero es temporal y no hacer permanente sus datos en la base de datos.
#Se utiliza para "mientras dura el Wizard"
class LigaEquipoWizard(models.TransientModel):
    _name = 'liga.equipo.wizard'
    #Campos del modelo que usaremos en el Wizard
    nombre = fields.Char()
    descripcion = fields.Html('Descripción', sanitize=True, strip_style=False)

    #Funcion que se llamara desde el Wizard, para utilizando este modelo temporal
    #y con el crear un nuevo registro en el modelo destino
    def add_liga_equipo(self):
        #Obtenemos referencia al modelo destino
        ligaEquipoModel = self.env['liga.equipo']
        #Tenemos que recorrer porque recordamos self referencia a todo el modelo
        for wiz in self:
            #Por cada elemento (en verdad, este Wizars solo tendra uno)
            #Creamos un registro en "liga.equipo"
            ligaEquipoModel.create({
                'nombre': wiz.nombre,
                'descripcion': wiz.descripcion,
            })
