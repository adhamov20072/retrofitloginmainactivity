package com.alimardon.retrofitlogin

data class Login(
    val `data`: List<Data>,
    val error: Boolean,
    val message: String
)