package com.example.mvilista.view

import com.example.mvilista.model.Cartoon

//Pueden existir varios estados para diferentes elementos de tu app
sealed class MainState {
    //Abstracción de todos los posibles
    //Estados por los que pasa tu pantalla ante la acción de un click del usuario
    //a un botón

    object Idle: MainState()
    object Loading: MainState()
    //el estado cuando exitosamente obtienes la lista de los personajes
    //Ejemplo estado success
    data class Cartoons(val cartoons: List<Cartoon>):MainState()
    data class Error(val error: String?):MainState()
}