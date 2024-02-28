package com.example.t09practicaentregable.service

import com.example.t09practicaentregable.model.Libro
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @GET("libros")
    suspend fun getAllLibros(): Response<List<Libro>>

    @GET("libros/{id}")
    suspend fun getLibroId(@Path("id") moduloId: Int): Response<Libro>

    @POST("libros")
    suspend fun insertLibro (@Body modulo: Libro)

    @DELETE("libros/{id}")
    suspend fun deleteLibroId(@Path("id") libroId: Int)
}