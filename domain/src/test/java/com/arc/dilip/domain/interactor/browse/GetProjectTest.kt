package com.arc.dilip.domain.interactor.browse

import com.arc.dilip.domain.data.ProjectDataFactory
import com.arc.dilip.domain.executor.PostExecutionThread
import com.arc.dilip.domain.interactor.browse.GetProjects
import com.arc.dilip.domain.model.Project
import com.arc.dilip.domain.respository.ProjectRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectTest {

    private lateinit var getProjects: GetProjects

    @Mock
    lateinit var projectRepository: ProjectRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectRepository, postExecutionThread)
    }

    @Test
    fun getProjectCompletes() {
        stufGetProject(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stufGetProject(Observable.just(projects))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stufGetProject(observable: Observable<List<Project>>) {
        whenever(projectRepository.getProjects()).thenReturn(observable)
    }
}
