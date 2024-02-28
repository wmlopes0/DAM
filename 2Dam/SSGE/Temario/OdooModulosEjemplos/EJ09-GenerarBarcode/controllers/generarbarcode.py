# -*- coding: utf-8 -*-
from odoo import http
from odoo.http import request

#Necesario para trabajar con base64
import base64
#Biblioteca para guardar la imagen en memoria antes de pasarla a base64
from io import BytesIO
#Bibiliotecas (hay que instalarlas, son dependencias, ver "__manifest__.py")
import barcode
from barcode.writer import ImageWriter

# Clase del controlador web
class GenerarBarcode(http.Controller):
    
    '''
    LLamada web para generar una imagen con un codigo de barras.
    
    
    Decorador que indica que la url "/generador/numero" atendera por HTTP, sin autentificacion
    Se puede probar accediendo a http://localhost:8069/generador/1
    Y nos devolvera via web una imagen con el codigo de barras generado

    '''

    @http.route('/generador/<codigo>', auth='public', cors='*', type='http')
    def crearBarcode(self, codigo, **kw):
        #Formato del codigo de barras ean13
        EAN = barcode.get_barcode_class('ean13')
        #Generamos el codigo de barras de 12 digitos. El zfill es para rellenar 0s hasta 12
        ean = EAN(str(codigo).zfill(12), writer=ImageWriter())
        #Declaramos un flujo de bytes (guardaremos ahi la imagen)
        fp = BytesIO()
        #Guardamos la imagen en el flujo de bytes
        ean.write(fp)
        #PAsamos el flujo de bytes y lo codificamos en base 64
        img_str = base64.b64encode(fp.getvalue()).decode("utf-8")
        #Devolvemos el HTML que muestra la imagen generada, pasada como base64
        return '<div><img src="data:image/png;base64,'+str(img_str)+'"/></div>'