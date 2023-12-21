package com.example.task18.domain

data class PersonsList(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<Person>
) {
    data class Person(
        val id: Int,
        val email: String,
        val firstname: String,
        val lastname: String,
        val avatar: String
    )
}
