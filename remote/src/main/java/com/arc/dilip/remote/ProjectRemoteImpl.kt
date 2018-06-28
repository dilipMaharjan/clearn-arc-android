package com.arc.dilip.remote

import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.data.repository.ProjectRemote
import com.arc.dilip.remote.mapper.ProjectResponseMapper
import com.arc.dilip.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectRemoteImpl @Inject constructor(private val service: GithubTrendingService,
                                            private val mapper: ProjectResponseMapper) : ProjectRemote {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.serachRepositories("language:kotlin", "stars", "desc")
                .map {
                    it.items.map {
                        mapper.mapFromModel(it)
                    }
                }
    }

}