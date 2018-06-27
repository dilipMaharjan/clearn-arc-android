package com.arc.dilip.domain.respository

import com.arc.dilip.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectRepository {
    fun getProjects(): Observable<List<Project>>
    fun bookmarkProject(projectId: String): Completable
    fun unBookmarkProject(projectId: String): Completable
    fun getBookMarkedProjects(): Observable<List<Project>>
}