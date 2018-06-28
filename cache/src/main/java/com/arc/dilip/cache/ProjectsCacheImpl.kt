package com.arc.dilip.cache

import com.arc.dilip.cache.db.ProjectDatabase
import com.arc.dilip.cache.mapper.CachedProjectMapper
import com.arc.dilip.cache.model.Config
import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.data.repository.ProjectCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(private val projectDatabase: ProjectDatabase,
                                            private val mapper: CachedProjectMapper) : ProjectCache {
    override fun clearProjects(): Completable {
        return Completable.defer {
            projectDatabase.cachedPrjectDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectDatabase.cachedPrjectDao().insertProjects(
                    projects.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectDatabase
                .cachedPrjectDao()
                .getProjects()
                .toObservable()
                .map {
                    it.map {
                        mapper.mapFromCached(it)
                    }
                }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectDatabase
                .cachedPrjectDao()
                .getBookmarkedProject()
                .toObservable()
                .map {
                    it.map {
                        mapper.mapFromCached(it)
                    }
                }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectDatabase.cachedPrjectDao()
                    .setBookmarkStatus(true, projectId)
            Completable.complete()
        }

    }

    override fun setProjectAsNotBookMarked(projectId: String): Completable {
        return Completable.defer {
            projectDatabase
                    .cachedPrjectDao()
                    .setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectDatabase
                .cachedPrjectDao()
                .getProjects()
                .isEmpty
                .map {
                    !it
                }

    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectDatabase.configDao().insertConfig(Config(lastCachedTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectDatabase.configDao().getConfig()
                .single(Config(lastCachedTime = 0))
                .map {
                    currentTime - it.lastCachedTime > expirationTime
                }
    }
}