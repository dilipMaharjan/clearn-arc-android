package com.arc.dilip.remote

import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.remote.data.ProjectDataFactory
import com.arc.dilip.remote.mapper.ProjectResponseMapper
import com.arc.dilip.remote.model.Project
import com.arc.dilip.remote.model.ProjectResponse
import com.arc.dilip.remote.service.GithubTrendingService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectRemoteImplTest {
    private val mapper = mock<ProjectResponseMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectRemoteImpl(service, mapper)

    @Test
    fun getProjectsCompletes() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectResponse()))
        stubProjectResponseMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsServer() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectResponse()))
        stubProjectResponseMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        remote.getProjects().test()
        verify(service).serachRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val data = ProjectDataFactory.makeProjectResponse()
        stubGithubTrendingServiceSearchRepositories(Observable.just(data))

        val entities = mutableListOf<ProjectEntity>()
        data.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectResponseMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectCallsServiceWithCorrectParameters() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectResponse()))
        stubProjectResponseMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        remote.getProjects().test()
        verify(service).serachRepositories("language:kotlin", "stars", "desc")
    }

    private fun stubGithubTrendingServiceSearchRepositories(observable: Observable<ProjectResponse>) {
        whenever(service.serachRepositories(any(), any(), any()))
                .thenReturn(observable)
    }

    private fun stubProjectResponseMapperMapFromModel(model: Project, entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
                .thenReturn(entity)

    }
}