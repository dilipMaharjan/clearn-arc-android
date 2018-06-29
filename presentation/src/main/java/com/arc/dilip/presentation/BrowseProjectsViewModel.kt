package com.arc.dilip.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.arc.dilip.domain.interactor.bookmark.BookmarkProject
import com.arc.dilip.domain.interactor.bookmark.UnBookmarkProject
import com.arc.dilip.domain.interactor.browse.GetProjects
import com.arc.dilip.presentation.mapper.ProjectMapper
import com.arc.dilip.presentation.model.Project
import com.arc.dilip.presentation.state.Resource
import com.arc.dilip.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseProjectsViewModel @Inject constructor(
        private val getProjects: GetProjects,
        private val mapper: ProjectMapper,
        private val bookmarkProject: BookmarkProject,
        private val unBookmarkProject: UnBookmarkProject
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<Project>>> = MutableLiveData()

    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): MutableLiveData<Resource<List<Project>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProjects.execute(ProjectSubscriber())
    }

    inner class ProjectSubscriber : DisposableObserver<List<com.arc.dilip.domain.model.Project>>() {
        override fun onComplete() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onNext(t: List<com.arc.dilip.domain.model.Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}