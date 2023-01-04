package com.example.cache

import com.example.features.registration.RegisterReceiveRemote

data class TokenCache(
    val login: String,
    val token: String
)

object InMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val tokenList: MutableList<TokenCache> = mutableListOf()
}