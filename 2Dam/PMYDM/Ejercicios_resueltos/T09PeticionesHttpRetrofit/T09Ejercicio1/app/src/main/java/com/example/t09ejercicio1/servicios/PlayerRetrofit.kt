package com.example.t09ejercicio1.servicios

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlayerRetrofit {

    private val BASE_URL = "https://www.balldontlie.io/api/v1/"

    //Crea un objeto Retrofit que se utilizará para realizar peticiones a la API
    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) //RetrofitBuilder
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Crea un objeto de la interfaz APIService (interfaz que contiene las diferentes peticiones a la API) a partir del objeto Retrofit.
    //Se utiliza 'by lazy' para que este objeto se cree únicamente cuando se invoque (solo se crea cuando se va a utilizar, sirve para ahorrar recursos)
    val apiService: APIService by lazy {
        getRetrofitInstance().create(APIService::class.java)
    }
}