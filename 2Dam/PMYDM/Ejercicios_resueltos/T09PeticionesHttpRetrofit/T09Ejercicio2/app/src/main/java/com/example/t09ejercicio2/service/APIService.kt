package com.example.t09ejercicio2.service

import com.example.t09ejercicio2.model.Modulo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @GET("modulos")
    suspend fun getAllModules(): Response<List<Modulo>>

    @GET("modulos/{id}")
    suspend fun getModuleId(@Path("id") moduloId: Int): Response<Modulo>

//    @Headers(“Content-Type: application/json”)
    @POST("modulos")
    suspend fun insertModule (@Body modulo: Modulo)

    @DELETE("modulos/{id}")
    suspend fun deleteModuleId(@Path("id") moduloId: Int)
}