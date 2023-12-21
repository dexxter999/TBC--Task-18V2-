package com.example.task18.data.network.model

import com.example.task18.domain.PersonsList
import com.squareup.moshi.Json

data class PersonsListDto(
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    val total: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    val data: List<PersonDto>
) {
    fun toPersonsList() = PersonsList(page, perPage, total, totalPages, data.map { it.toPerson() })
    data class PersonDto(
        val id: Int,
        val email: String,
        @Json(name = "first_name")
        val firstname: String,
        @Json(name = "last_name")
        val lastname: String,
        val avatar: String
    ) {
        fun toPerson() = PersonsList.Person(id, email, firstname, lastname, avatar)
    }
}