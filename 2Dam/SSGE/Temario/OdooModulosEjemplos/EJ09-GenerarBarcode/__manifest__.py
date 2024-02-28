# -*- coding: utf-8 -*-
{
    'name': "Generador de codigos de barras a partir de un numero",  # Titulo del módulo
    'summary': "A partir de una llamada web, genera un codigo de barras",  # Resumen de la funcionaliadad
    'description': """
    A partir de una llamada web, genera un codigo de barras
    ==============
    """,  

    #Indicamos que es una aplicación
    'application': True,
    'author': "Sergi García",
    'website': "http://apuntesfpinformatica.es",
    'category': 'Tools',
    'version': '0.1',
    'depends': ['base'],

    #IMPORTANTE: Si estais usando Docker Compose, debeis instalar la dependencia:
    #docker-compose exec web bash
    #y tras ello pip3 instal python-barcode y luego pip3 install python-barcode[images]

    #Dependencias externas de https://pypi.org/project/python-barcode/
    'external_dependencies': {"python": ['python-barcode',"python-barcode[images]"], "bin": []},
    'data': [
    ],
    # Fichero con data de demo si se inicializa la base de datos con "demo data" (No incluido en ejemplo)
    # 'demo': [
    #     'demo.xml'
    # ],
}
