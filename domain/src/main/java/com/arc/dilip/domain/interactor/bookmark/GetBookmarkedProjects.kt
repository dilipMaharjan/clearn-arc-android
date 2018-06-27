package com.arc.dilip.domain.interactor.bookmark

import com.arc.dilip.domain.executor.PostExecutionThread
import com.arc.dilip.domain.interactor.ObservableUseCase
import com.arc.dilip.domain.model.Project
import com.arc.dilip.domain.respository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(private val projectRepository: ProjectRepository,
                                                postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getBookMarkedProjects()
    }
}