package com.example.t09ejercicio1.servicios

import com.example.t09ejercicio1.modelo.Data
import com.example.t09ejercicio1.modelo.Player
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("players")
    suspend fun getAllPlayers(): Response <Data>

    @GET("players/{id}")
    suspend fun getPlayerId(@Path("id") playerId: Int): Response<Player>

//    @Headers("Content-Type: application/json")
//    @POST("players")
//    fun insertPlayer (@Body player: Player): Response<Player>

}