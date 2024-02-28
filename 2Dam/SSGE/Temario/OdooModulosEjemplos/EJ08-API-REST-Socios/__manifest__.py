# -*- coding: utf-8 -*-
{
    'name': "API REST  para gestionar socios",  # Titulo del módulo
    'summary': "Gestionar socios mediante Vistas y API REST :)",  # Resumen de la funcionaliadad
    'description': """
    Gestionar socios mediante Vistas y API REST :)
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
        'security/ir.model.access.csv',
        'views/socio.xml',
    ],
    # Fichero con data de demo si se inicializa la base de datos con "demo data" (No incluido en ejemplo)
    # 'demo': [
    #     'demo.xml'
    # ],
}
