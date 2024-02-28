# -*- coding: utf-8 -*-
from odoo import http
from odoo.http import request
import json

# Clase del controlador web



class ListaSocios(http.Controller):
    
    '''
    LLamada web para obtener lista completa de socios. No es parte de la API REST.
    
    
    Decorador que indica que la url "/gestion/<modelo>" atendera por HTTP, sin autentificacion
    Devolvera texto que estará en formato JSON
    Se puede probar accediendo a http://localhost:8069/gestion/socio
    Y nos devolvera informacion sobre cada socio

    '''

    @http.route('/gestion/<modelo>', auth='public', cors='*', type='http')
    def obtenerDatosSocios(self, modelo, **kw):
        # Obtenemos la referencia del modelo (pensado en este programa para ser "socios")
        socios = request.env[modelo].sudo().search([])

        #Generamos la lista de socios)
        listaSocios=[]
        for s in socios:
                listaSocios.append([s.num_socio,s.nombre, s.apellidos, str(s.foto), str(s.barcode_carnet)])
        json_result=json.dumps(listaSocios, default=str)
        return json_result
