package com.example.mvilista.view

import com.example.mvilista.model.Animal

sealed class MainState {
    //Estados posibles ante la acci+on de un click del usuario
    object Idle: MainState()
    object Loading: MainState()
    data class Animals(val animals: List<Animal>):MainState()
    data class Error(val error: String?):MainState()
}