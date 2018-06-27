package com.arc.dilip.data.repository

import com.arc.dilip.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}