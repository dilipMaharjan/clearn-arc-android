package com.arc.dilip.cache.data

import com.arc.dilip.cache.model.CachedProject
import com.arc.dilip.data.model.ProjectEntity

object ProjectDataFactory {
    fun makeCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                false)
    }

    fun makeBookmarkedCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                true)
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomBoolean())
    }

    fun makeBookmarkedProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                true)
    }
}