package com.example.mvilista.api

class CartoonRepo(val api: CartoonApi) {
    suspend fun getCartoons() = api.getCartoons()
}