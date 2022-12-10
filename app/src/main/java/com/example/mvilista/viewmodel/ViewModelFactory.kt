package com.example.mvilista.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvilista.api.CartoonApi
import com.example.mvilista.api.CartoonRepo

//Personalizando su factory de viewModels
//para poder crear objetos que puedan recibir argumentos
//por defefcto la fábrica genera objetos genéricos con 0 argumentos
class ViewModelFactory(
    private val api: CartoonApi
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(CartoonRepo(api)) as T
        }
        throw IllegalArgumentException("La clase no es válida como ViewModel")
    }
}