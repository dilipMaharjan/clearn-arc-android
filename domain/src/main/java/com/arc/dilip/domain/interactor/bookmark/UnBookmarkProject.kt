package com.arc.dilip.domain.interactor.bookmark

import com.arc.dilip.domain.executor.PostExecutionThread
import com.arc.dilip.domain.interactor.CompletableUseCase
import com.arc.dilip.domain.respository.ProjectRepository
import io.reactivex.Completable
import javax.inject.Inject

class UnBookmarkProject @Inject constructor(private val projectRepository: ProjectRepository,
                                            postExecutionThread: PostExecutionThread)
    : CompletableUseCase<UnBookmarkProject.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return projectRepository.unBookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}