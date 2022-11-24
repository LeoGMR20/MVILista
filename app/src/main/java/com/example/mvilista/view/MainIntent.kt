package com.example.mvilista.view

//Pueden existir diferentes interacciones del usuario con la app
sealed class MainIntent {
    //Abstracción de las interacciones del usuario
    //Obtener los registros
    object FetchCartoons: MainIntent()
}