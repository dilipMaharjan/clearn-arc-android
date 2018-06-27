package com.arc.dilip.data.store

import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.data.repository.ProjectCache
import com.arc.dilip.data.repository.ProjectDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class ProjectCacheDataStore @Inject constructor(private val projectCache: ProjectCache) : ProjectDataStore {
    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectCache.saveProjects(projects)
                .andThen(projectCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectCache.clearProjects()
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectCache.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectCache.setProjectAsNotBookMarked(projectId)
    }
}