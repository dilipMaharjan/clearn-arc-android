package com.arc.dilip.cache.data

import com.arc.dilip.cache.model.Config

object ConfigDataFactory {
    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }
}