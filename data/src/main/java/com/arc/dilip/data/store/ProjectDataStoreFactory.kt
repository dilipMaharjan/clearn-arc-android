package com.arc.dilip.data.store

import com.arc.dilip.data.repository.ProjectDataStore
import javax.inject.Inject

open class ProjectDataStoreFactory @Inject constructor(private val projectCacheDataStore: ProjectCacheDataStore,
                                                       private val projectRemoteDataStore: ProjectRemoteDataStore) {
    open fun getDataStore(projectsCached: Boolean, cacheExpired: Boolean): ProjectDataStore {
        return if (projectsCached && !cacheExpired) {
            projectCacheDataStore
        } else {
            projectRemoteDataStore
        }
    }

    open fun getCachedDataStore(): ProjectDataStore {
        return projectCacheDataStore
    }
}