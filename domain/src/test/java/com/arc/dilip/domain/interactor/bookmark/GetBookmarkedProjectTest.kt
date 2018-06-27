package com.arc.dilip.domain.interactor.bookmark

import com.arc.dilip.domain.data.ProjectDataFactory
import com.arc.dilip.domain.executor.PostExecutionThread
import com.arc.dilip.domain.model.Project
import com.arc.dilip.domain.respository.ProjectRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects

    @Mock
    private lateinit var projectRepository: ProjectRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectRepository, postExecutionThread)
    }

    @Test
    fun getProjectCompletes() {
        stufGetProject(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stufGetProject(Observable.just(projects))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stufGetProject(observable: Observable<List<Project>>) {
        whenever(projectRepository.getBookMarkedProjects()).thenReturn(observable)
    }
}