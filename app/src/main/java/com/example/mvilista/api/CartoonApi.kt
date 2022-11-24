package com.example.mvilista.api

import com.example.mvilista.model.Cartoon
import retrofit2.http.GET

//Definir las reglas de comunicación con la API
//Que desean consumir
interface CartoonApi {
    //Obtener personajes
    @GET("character")
    suspend fun getCartoons(): List<Cartoon>

    //Una función suspendida es la que se permite trabajar
    //con corrutinas....
}