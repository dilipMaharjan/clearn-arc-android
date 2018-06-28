package com.arc.dilip.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.arc.dilip.cache.dao.CachedProjectDao
import com.arc.dilip.cache.dao.ConfigDao
import com.arc.dilip.cache.model.CachedProject
import com.arc.dilip.cache.model.Config
import javax.inject.Inject

@Database(entities = arrayOf(CachedProject::class, Config::class), version = 1)
abstract class ProjectDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedPrjectDao(): CachedProjectDao

    abstract fun configDao(): ConfigDao

    private var INSTANCE: ProjectDatabase? = null

    private val lock = Any()
    fun getInstance(context: Context): ProjectDatabase {
        if (INSTANCE == null) {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ProjectDatabase::class.java,
                            "projects.db").build()
                }
                return INSTANCE as ProjectDatabase
            }
        }
        return INSTANCE as ProjectDatabase
    }
}