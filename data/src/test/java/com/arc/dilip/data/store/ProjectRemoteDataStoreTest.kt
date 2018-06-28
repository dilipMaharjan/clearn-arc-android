package com.arc.dilip.data.store

import com.arc.dilip.data.data.ProjectFactory
import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.data.repository.ProjectRemote
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectRemoteDataStoreTest {
    private val remote = mock<ProjectRemote>()
    private val store = ProjectRemoteDataStore(remote)


    @Test
    fun getProjectsCompletes() {
        stubRemoteGetProject(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubRemoteGetProject(Observable.just(data))
        val testObserver = store.getProjects().test()
        testObserver.assertValue(data)
    }

    //can write this test for every method that throws the exception
    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowsException() {
        store.saveProjects(listOf()).test()
    }

    private fun stubRemoteGetProject(observable: Observable<List<ProjectEntity>>) {
        whenever(remote.getProjects())
                .thenReturn(observable)
    }
}