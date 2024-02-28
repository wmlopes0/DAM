# -*- coding: utf-8 -*-
from odoo import http
from odoo.http import request
import json

# Clase del controlador web



class ApiRest(http.Controller):
    

    '''
    DEFINIMOS LA API REST
    https://geekytheory.com/que-es-una-api-rest-y-para-que-se-utiliza/

    INFORMACION SOBRE LA API REST
    Se accedera mediante "/gestion/apirest/<model>" (aunque el programa esta pensando para que sea socio)
    La operación que se realizará depende de si es una llamada POST, PUT, PATCH, GET o DELETE
    La información sobre la operación se enviara formateada en JSON en un parametro llamada "data"
    FIN INFORMACION SOBRE LA API REST

    SE PUEDE PROBAR CON LA EXTENSION THUNDER CLIENT
    Nombre: Thunder Client
    Vínculo de VS Marketplace: https://marketplace.visualstudio.com/items?itemName=rangav.vscode-thunder-client
    
    '''




    '''

    Probar POST (Creando socio 3)
    Valor de data: {"num_socio":"3", "nombre":"Sergi","Apellidos":"Garcia"}
    URL COMPLETA (Enviada con POST): http://localhost:8069/gestion/apirest/socio?data={"num_socio":"3", "nombre":"Sergi","apellidos":"Garcia"} 

    --------------
    Probar PUT/PATCH (Modificar socio 3)
    Valor de data: {"num_socio":"3", "nombre":"Sergi","apellidos":"NuevoApellido"}

    URL COMPLETA (Enviada con PUT/PATCH): http://localhost:8069/gestion/apirest/socio?data={"num_socio":"3", "nombre":"Sergi","apellidos":"NuevoApellido"} 

    '''


    #Definimos la operacion para metodos POST, PUT y MATCH
    @http.route('/gestion/apirest/<model>', auth="none", cors='*', csrf=False,
                methods=["POST", "PUT", "PATCH"], type='http')
    def apiPost(self, **args):
        #Obtenemos el modelo de los argumentos
        model = args['model']

        #Pasamos lo recibido en "data" a un diccionario
        dicDatos=json.loads(args['data'])
        #Si se ha indicado num_socio, hay busqueda
        if dicDatos["num_socio"]:
            search = [('num_socio', '=', int(dicDatos["num_socio"]))]
        else:
            return "{'estado':'SOCIONOINDICADO'}"
        #Si la peticion es de tipo POST,m ejecutamos esto
        #En este caso, crearemos un nuevo registro con los datos indicados en "data"
        if (http.request.httprequest.method == 'POST'):
            #Creamos el nuevo registro
            record = request.env[model].sudo().create(
                #Proporcionamos un diccionario con los datos del registro a crear
                dicDatos
                )
            
            #Devolvemos el registro creado, siguiendo este esquema
            return http.Response(
                json.dumps(
                
                    record.read(), #Lectura del registro
                    default=str #Funcion de conversion por defecto (str, para convertir a String elementos como los datetime)
                    ),
                    status=200, # Respuesta de la aplicación al navegador
                    mimetype='application/json'
                )
        #Si la peticion es de tipo PUT o PATCH, ejecutamos esto
        #En este caso, modificaremos un registro con un numero de socio concreto, cambiando 
        #a los valores pasados en "data"
        if (http.request.httprequest.method == 'PUT' or http.request.httprequest.method == 'PATCH'):

            record = http.request.env[model].sudo().search(search)
            if record and record[0]:
                record[0].write(dicDatos)
                #Devolvemos el registro creado, siguiendo este esquema
                return http.Response(
                    json.dumps(
                    
                        record.read(), #Lectura del registro
                        default=str #Funcion de conversion por defecto (str, para convertir a String elementos como los datetime)
                        ),
                        status=200, # Respuesta de la aplicación al navegador
                        mimetype='application/json'
                    )
            #Caso de que el registro no sea encontrado
            return "Registro no encontrado"
        #Si no es POST, PUT ni PATCH
        return http.request.env['ir.http'].session_info()
        


    '''

    Probar GET (Consultando socio 3)
    Valor de data: {"num_socio":"3"}
    URL COMPLETA (Enviada con GET): http://localhost:8069/gestion/apirest/socio?data={"num_socio":"3"} 

    --------------
    Probar DELETE (Borrando socio 3)
    Valor de data: {"num_socio":"3"}

    URL COMPLETA (Enviada con DELETE): http://localhost:8069/gestion/apirest/socio?data={"num_socio":"3"} 

    '''
    @http.route('/gestion/apirest/<model>', auth="none", cors='*', csrf=False, methods=["GET", "DELETE"],
                type='http')
    def apiGet(self, **args):
        #Obtenemos el modelo y si hay num_socio, hacemos la busqueda
        model = args['model']
        search = []
        #Pasamos lo recibido en "data" a un diccionario
        dicDatos=json.loads(args['data'])
        #Si se ha indicado num_socio, hay busqueda
        if dicDatos["num_socio"]:
            search = [('num_socio', '=', int(dicDatos["num_socio"]))]
        else:
            return "{'estado':'SOCIONOINDICADO'}"

        #Si es GET, ddeolvemos el registro de la busqueda
        if (http.request.httprequest.method == 'GET'):
            record = http.request.env[model].sudo().search(search)
            if record and record[0]:
                return http.Response( 
                json.dumps(record[0].read(), default=str), 
                    status=200,
                    mimetype='application/json'
                )

            return "{'estado':'NOTFOUND'}"
        #Si es delete, cogemos el primer elemento de la busqueda
        if (http.request.httprequest.method == 'DELETE'):

            record = http.request.env[ model].sudo().search(search)
            #Si hay algun elemento
            if record and record[0]:
                #Eliminamos el registro encontrado
                record[0].unlink()
                #Devolvemos el registro eliminado, siguiendo este esquema
                return http.Response(
                    json.dumps(
                    
                        record[0].read(), #Lectura del registro
                        default=str #Funcion de conversion por defecto (str, para convertir a String elementos como los datetime)
                        ),
                        status=200, # Respuesta de la aplicación al navegador
                        mimetype='application/json'
                    )
            return "{'estado':'NOTFOUND'}"
        #Si no es GET ni DELETE
        return http.request.env['ir.http'].session_info()
        
