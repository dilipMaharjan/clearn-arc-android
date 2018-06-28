package com.arc.dilip.remote.mapper

interface ModelMapper<M, E> {
    fun mapFromModel(model: M): E
}