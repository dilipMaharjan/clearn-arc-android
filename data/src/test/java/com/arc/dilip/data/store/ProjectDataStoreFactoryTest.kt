package com.arc.dilip.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectDataStoreFactoryTest {

    private val cacheStore = mock<ProjectCacheDataStore>()
    private val remoteStore = mock<ProjectRemoteDataStore>()
    private val factory = ProjectDataStoreFactory(cacheStore, remoteStore)


    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreProjectNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }


    @Test
    fun getCachedDataStore() {
        assertEquals(cacheStore, factory.getCachedDataStore())
    }
}