# -*- coding: utf-8 -*-
from datetime import timedelta

from odoo import models, fields, api
from odoo.exceptions import ValidationError


# Modelo base, creado como modelo abstracto 
# Este modelo lo heredarara el modelo BibliotecaComic
# Y se ha creado puramente con fin didáctico para ver herencia entre modelos
class BaseArchive(models.AbstractModel):
    #Nombre y descripcion del modelo
    _name = 'base.archive'
    _description = 'Fichero abstracto'

    #Introduce el atributo "Activo"
    activo = fields.Boolean(default=True)

    #Introducice metodo "archivar" que invierte el estado de "activo"
    def archivar(self):
        for record in self:
            record.activo = not record.activo


#Definimos modelo Biblioteca comic
class BibliotecaComic(models.Model):

    #Nombre y descripcion del modelo
    _name = 'biblioteca.comic'
    #Hereda de "base.archive" (el modelo abstracto creado antes)
    _inherit = ['base.archive']

    _description = 'Comic de biblioteca'

    #Parametros de ordenacion por defecto
    _order = 'fecha_publicacion desc, nombre'

    #ATRIBUTOS

    #PARA CUANDO NO HAY UN ATRIBUTO LLAMADO NAME PARA MOSTRAR NOMBRE DE UN REGISTRO
    # https://www.odoo.com/es_ES/forum/ayuda-1/how-defined-display-name-in-custom-many2one-91657
    
    #Indicamos que atributo sera el que se usara para mostrar nombre.
    #Por defecto es "name", pero si no hay un atributo que se llama name, aqui lo indicamos
    #Aqui indicamos que se use el atributo "nombre"
    _rec_name = 'nombre'


    #Atributo nombre
    nombre = fields.Char('Titulo', required=True, index=True)
    #Atributo para seleccionar entre varios
    estado = fields.Selection(
        [('borrador', 'No disponible'),
         ('disponible', 'Disponible'),
         ('perdido', 'Perdido')],
        'Estado', default="borrador")
    #Campo con HTML (Sanitizado) donde se guarda la descripción del comic
    descripcion = fields.Html('Descripción', sanitize=True, strip_style=False)
    #Dato binario, para guardar un binario (en la vista indicaremos que es una imagen) con la portada del comic
    portada = fields.Binary('Portada Comic')

    #Fecha de publicación
    fecha_publicacion = fields.Date('Fecha publicación')

    #Precio del libro    
    precio = fields.Float('Precio')
    #Numero de paginas. 
    paginas = fields.Integer('Numero de páginas',
        #Hace que este atributo este disponible para este grupo de seguridad 
        #Que en este caso son todos los usuarios
        groups='base.group_user',
        #Establece que si el estado es perdido, el numero de paginas no se puede cambiar
        estados={'perdido': [('readonly', True)]},
        #Texto a mostrar en la ayuda de la interfaza al dejar el ratón encima
        help='Total numero de paginas',
        #Si se pone a true, indica que si este atributo se aplica a distintas empreas en Odoo
        #para cada empresa ponga un valor distintos
        #Esta colocado con fin didactico a false 
        company_dependent=False)

 
    #Valoración lector, indicando como son los datos
    valoracion_lector = fields.Float(
        'Valoración media lectores',
        digits=(14, 4),  # Precision opcional (total, decimales),
    )
    # Relación muchos a muchos de autores utilizando un "partner"
    # de Odoo (Es un elemento que puede ser empresa o individuo)
    autor_ids = fields.Many2many('res.partner', string='Autores')

    # Relación muchos a uno utilizando un "partner"
    # de Odoo (Es un elemento que puede ser empresa o individuo)

    editorial_id = fields.Many2one('res.partner', string='Editorial')

    #Relacion muchos a uno con el modelo de las categorias
    categoria_id = fields.Many2one('biblioteca.comic.categoria')
    
    #Variable computada para calcular dias desde el lanzamiento
    dias_lanzamiento = fields.Integer(
        string='Dias desde lanzamiento',
        #Indicamos las funciones que computan el valor, la inversa y la funcion usada para fecha
        compute='_compute_anyo', inverse='_inverse_anyo', search='_search_anyo',
        #Indicamos que no se almacene en la base de datos el valor computado
        store=False,
        #Hace que en la ejecución de la función de computo del valor, se hace con
        #permisos de superusuario
        compute_sudo=True,
    )

    #Para poder meter referencias a modelos existentes en Odoo (por ejemplo, una factura) como 
    #un dato del modelo. Para saber que documentos, usa "referencable_models"
    ref_doc_id = fields.Reference(selection='_referencable_models', string='Referencia a documento')

    #Funcion usada para obtener que modelos pueden ser referenciados 
    @api.model
    def _referencable_models(self):
        models = self.env['ir.model'].search([('field_id.name', '=', 'message_ids')])
        return [(x.model, x.name) for x in models]


    #Funcion para computar el valor de "Dias desde lanzamiento"
    #Indicamos con este decordador que esta funcion depende del atributo "fecha_publicacion"
    @api.depends('fecha_publicacion')
    def _compute_anyo(self):
        #Obtenemos la fecha de hoy
        hoy = fields.Date.today()
        #Como self es el modelo, recorremos cada uno de los registros
        for comic in self:
            #Caso de que este establecida una fecha (si no lo esta, el calculo es 0)
            if comic.fecha_publicacion:
                #Obtenemos la diferencia entre la fecha de hoy y esa fecha
                delta = hoy - comic.fecha_publicacion
                #De la fecha resultado, extraemos el número de dias
                comic.dias_lanzamiento = delta.days
            else:
                comic.dias_lanzamiento = 0

    #Funcion que sirve para la inversa del computado: esto hace, que si modificamos
    #el valor computado, podamos modificar el valor de la fecha
    def _inverse_anyo(self):
        #Obtenemos la fecha de hoy
        hoy = fields.Date.today()
        #Obtenemos los registros de comic. En esta ocasión 
        #filtramos por aquellos que tengan "fecha_publicacion" y asi evitamos no establecidos
        for comic in self.filtered('fecha_publicacion'):
            #restamos la fecha de hoy a una fecha generada con los dias del lanzamiento
            d = hoy - timedelta(days=comic.dias_lanzamiento)
            #Con esa resta, obtenemos la fecha de lanzamiento
            comic.fecha_publicacion = d

    def _search_age(self, operator, value):
        hoy = fields.Date.today()
        value_dias = timedelta(dias=value)
        value_fecha = hoy - value_dias
        operator_map = {
            '>': '<', '>=': '<=',
            '<': '>', '<=': '>=',
        }
        new_op = operator_map.get(operator, operator)
        return [('fecha_publicacion', new_op, value_fecha)]

    def name_get(self):
        result = []
        for record in self:
            rec_name = "%s (%s)" % (record.nombre, record.fecha_publicacion)
            result.append((record.id, rec_name))
        return result


    #Constraints de SQL del modelo
    #Util cuando la constraint se puede definir con sintaxis SQL
    _sql_constraints = [
        ('name_uniq', 'UNIQUE (nombre)', 'El titulo Comic debe ser único.'),
        ('positive_page', 'CHECK(paginas>0)', 'El comic debe tener al menos una página')
    ]

    #Indicamos que esta funcion es una "Constraints" de ese atributos
    #Dicho de otra forma, cada vez que se cambie ese atributo, se lanzara esta funcion
    #Y si la funcion detecta un cambio inadecuado, cambiara una instruccion
    #Util cuando la constraint no se puede definir con sintaxis SQL y debe indicar en una funcion
    @api.constrains('fecha_publicacion')
    def _check_release_date(self):
        # Recorremos el modelo
        for record in self:
            #Comprobamos de cada registro, primero que haya una fecha_publicacion
            #y tras ello, que la fecha no sea posterior a la actual.
            if record.fecha_publicacion and record.fecha_publicacion > fields.Date.today():
                #Si procede, lanzamos una excepcion
                raise models.ValidationError('La fecha de lanzamiento debe ser anterior a la actual')


