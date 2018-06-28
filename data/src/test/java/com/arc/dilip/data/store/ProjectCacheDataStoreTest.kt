package com.arc.dilip.data.store

import com.arc.dilip.data.data.DataFactory
import com.arc.dilip.data.data.ProjectFactory
import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.data.repository.ProjectCache
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectCacheDataStoreTest {
    private val cache = mock<ProjectCache>()
    private val store = ProjectCacheDataStore(cache)

    @Test
    fun getProjectsCompletes() {
        stubProjectCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectCacheGetProjects(Observable.just(data))
        val testObservable = store.getProjects().test()
        testObservable.assertValue(data)
    }

    @Test
    fun getProjectsCallsCacheSource() {
        stubProjectCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects().test()
        verify(cache).getProjects()
    }

    @Test
    fun saveProjectsCompletes() {
        stubSaveProjects(Completable.complete())
        val testObser = store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
        testObser.assertComplete()
    }

    @Test
    fun saveProjectCallsCache() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubSaveProjects(Completable.complete())
        store.saveProjects(data).test()
        verify(cache).saveProjects(data)


    }

    @Test
    fun clearProjectsCompletes() {
        stubClearProjects(Completable.complete())
        val testObserver = store.clearProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearProjectsCallsCacheStore() {
        stubClearProjects(Completable.complete())
        store.clearProjects().test()
        verify(cache).clearProjects()
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectsCacheGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsCallsCacheStore() {
        stubProjectsCacheGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getBookmarkedProjects().test()
        verify(cache).getBookmarkedProjects()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetBookmarkedProjects(Observable.just(data))

        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertValue(data)
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())

        val testObserver = store.setProjectAsBookmarked(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }


    @Test
    fun setProjectAsBookmarkedCallsCacheStore() {
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())

        store.setProjectAsBookmarked(DataFactory.randomString()).test()
        verify(cache).setProjectAsBookmarked(DataFactory.randomString())
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        val testObserver = store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCallsCacheStore() {
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        verify(cache).setProjectAsNotBookMarked(DataFactory.randomString())
    }


    private fun stubProjectCacheGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getProjects())
                .thenReturn(observable)
    }

    private fun stubSaveProjects(completable: Completable) {
        whenever(cache.saveProjects(any()))
                .thenReturn(completable)
    }

    private fun stubClearProjects(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
                .thenReturn(completable)
    }

    private fun stubProjectsCacheGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getProjects())
                .thenReturn(observable)
    }

    private fun stubProjectsCacheSetProjectAsBookmarked(completable: Completable) {
        whenever(cache.setProjectAsBookmarked(any()))
                .thenReturn(completable)

    }

    private fun stubProjectsCacheSetProjectAsNotBookmarked(completable: Completable) {
        whenever(cache.setProjectAsNotBookMarked(any()))
                .thenReturn(completable)

    }
}