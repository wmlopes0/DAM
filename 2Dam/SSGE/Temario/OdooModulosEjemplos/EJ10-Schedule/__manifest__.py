# -*- coding: utf-8 -*-
{
    'name': "Ejemplo Schedule (Biblioteca comics)",  # Titulo del módulo
    'summary': "Ejemplo Schedule (Version simple)",  # Resumen de la funcionaliadad
    'description': """
Ejemplo Schedule, donde cada minuto se actualiza la fecha de publicacion
de todos los comics a la de hoy (sea cual sea la fecha)
==============
    """,  

    #Indicamos que es una aplicación
    'application': True,
    'author': "Sergi García",
    'website': "http://apuntesfpinformatica.es",
    'category': 'Tools',
    'version': '0.1',
    'depends': ['base'],

    'data': [
        #Estos dos primeros ficheros:
        #1) El primero indica grupo de seguridad basado en rol
        #2) El segundo indica la politica de acceso del modelo
        #Mas información en https://www.odoo.com/documentation/14.0/es/developer/howtos/rdtraining/05_securityintro.html
        #Y en www.odoo.yenthevg.com/creating-security-groups-odoo/ 
        'security/groups.xml',
        'security/ir.model.access.csv',
        #Cargamos la tarea programada
        'cron/cron_data.xml',
        #Cargamos la vista de la biblioteca de comics

        'views/biblioteca_comic.xml'
    ],
    # Fichero con data de demo si se inicializa la base de datos con "demo data" (No incluido en ejemplo)
    # 'demo': [
    #     'demo.xml'
    # ],
}
