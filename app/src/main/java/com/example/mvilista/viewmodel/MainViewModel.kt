package com.example.mvilista.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvilista.api.CartoonRepo
import com.example.mvilista.view.MainIntent
import com.example.mvilista.view.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

//Para que esta clase se comporte como ViewModel
//hay que heredar la clase padre de ViewModel
class MainViewModel(private val repo: CartoonRepo): ViewModel() {
    //Vamos a trabajar con las abstracciones de Intents y States
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private var _state = MutableStateFlow<MainState>(MainState.Idle)
    val state : StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it) {
                    //MainIntent.FetchCartoons  = objeto que refleja la intencion de interacciòn
                    //fetchCartoons() = funcion o metodo que va a interactura con
                    //en base a los estados
                    is MainIntent.FetchCartoons -> fetchCartoons()
                    else -> fetchCartoons()
                }
            }
        }

    }

    private fun fetchCartoons() {
        viewModelScope.launch {
            //Estado cambia de reposo idle a loading
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Cartoons(repo.getCartoons())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}