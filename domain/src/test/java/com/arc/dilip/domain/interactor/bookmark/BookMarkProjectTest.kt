package com.arc.dilip.domain.interactor.bookmark

import com.arc.dilip.domain.data.ProjectDataFactory
import com.arc.dilip.domain.executor.PostExecutionThread
import com.arc.dilip.domain.respository.ProjectRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookMarkProjectTest {
    private lateinit var bookmarkProject: BookmarkProject

    @Mock
    private lateinit var projectRepository: ProjectRepository

    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectRepository, postExecutionThread)
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubBookmarkProject(Completable.complete())
        val testObserver = bookmarkProject.buildUseCaseCompletable(
                BookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsExeption() {
        bookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubBookmarkProject(completable: Completable) {
        whenever(projectRepository.bookmarkProject(any())).thenReturn(completable)

    }
}