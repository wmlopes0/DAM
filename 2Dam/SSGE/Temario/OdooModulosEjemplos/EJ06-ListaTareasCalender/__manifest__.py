# -*- coding: utf-8 -*-
{
    'name': "Lista de tareas (Calendario)",  # Module title
    'description': """Lista de tareas en formato calendario""",  # You can also rst format
    'author': "",
    'website': "",
    'category': 'Project',
    'version': '1.0',
    'application': True,
    #Importante! Se basa en toda la estructura de project de Odoo
    #https://www.odoo.com/documentation/13.0/es/applications/services/project/overview/setup.html
    'depends': ['project'],
    'data': [
        'views/view_tareas.xml',
    ],
}
