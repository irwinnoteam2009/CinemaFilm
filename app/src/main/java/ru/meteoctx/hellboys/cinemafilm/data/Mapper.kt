package ru.meteoctx.hellboys.cinemafilm.data

interface Mapper<From, To> {
    // from data to domain
    fun mapTo(from: From): To
    fun mapTo(elements: List<From?>?): List<To> = elements?.filter { it != null }?.map { item -> mapTo(item!!) } ?: emptyList()

    // from domain to data
    fun mapFrom(entity: To): From
    fun mapFrom(elements: List<To?>?): List<From> = elements?.filter { it != null }?.map { item -> mapFrom(item!!) } ?: emptyList()
}