package com.arc.dilip.remote.service

import com.arc.dilip.remote.model.ProjectResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubTrendingService {
    @GET("search/repositories")
    fun serachRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String): Observable<ProjectResponse>
}