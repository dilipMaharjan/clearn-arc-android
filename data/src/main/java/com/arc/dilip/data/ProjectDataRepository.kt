package com.arc.dilip.data

import com.arc.dilip.data.mapper.ProjectMapper
import com.arc.dilip.data.repository.ProjectCache
import com.arc.dilip.data.store.ProjectDataStoreFactory
import com.arc.dilip.domain.model.Project
import com.arc.dilip.domain.respository.ProjectRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectCache,
        private val factory: ProjectDataStoreFactory
) : ProjectRepository {
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(), cache.isProjectCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getProjects()
                }
                .flatMap { projects ->
                    factory.getCachedDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))
                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCachedDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return factory.getCachedDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookMarkedProjects(): Observable<List<Project>> {
        return factory.getCachedDataStore().getBookmarkedProjects()
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }
}