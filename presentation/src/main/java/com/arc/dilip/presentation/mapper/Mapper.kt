package com.arc.dilip.presentation.mapper

interface Mapper<V, D> {
    fun mapToView(type: D): V
}