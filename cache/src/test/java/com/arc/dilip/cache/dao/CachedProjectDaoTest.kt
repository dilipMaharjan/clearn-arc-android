package com.arc.dilip.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.arc.dilip.cache.data.ProjectDataFactory
import com.arc.dilip.cache.db.ProjectDatabase
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CachedProjectsDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            ProjectDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getProjectsReturnsData() {
        val project = ProjectDataFactory.makeCachedProject()
        database.cachedPrjectDao().insertProjects(listOf(project))

        val testObserver = database.cachedPrjectDao().getProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun deleteProjectsClearsData() {
        val project = ProjectDataFactory.makeCachedProject()
        database.cachedPrjectDao().insertProjects(listOf(project))
        database.cachedPrjectDao().deleteProjects()

        val testObserver = database.cachedPrjectDao().getProjects().test()
        testObserver.assertValue(emptyList())
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val project = ProjectDataFactory.makeCachedProject()
        val bookmarkedProject = ProjectDataFactory.makeBookmarkedCachedProject()
        database.cachedPrjectDao().insertProjects(listOf(project, bookmarkedProject))

        val testObserver = database.cachedPrjectDao().getBookmarkedProject().test()
        testObserver.assertValue(listOf(bookmarkedProject))
    }

    @Test
    fun setProjectAsBookmarkedSavesData() {
        val project = ProjectDataFactory.makeCachedProject()
        database.cachedPrjectDao().insertProjects(listOf(project))
        database.cachedPrjectDao().setBookmarkStatus(true, project.id)
        project.isBookmarked = true

        val testObserver = database.cachedPrjectDao().getBookmarkedProject().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun setProjectAsNotBookmarkedSavesData() {
        val project = ProjectDataFactory.makeBookmarkedCachedProject()
        database.cachedPrjectDao().insertProjects(listOf(project))
        database.cachedPrjectDao().setBookmarkStatus(false, project.id)
        project.isBookmarked = false

        val testObserver = database.cachedPrjectDao().getBookmarkedProject().test()
        testObserver.assertValue(emptyList())
    }
}