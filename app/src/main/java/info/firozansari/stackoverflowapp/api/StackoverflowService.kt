package info.firozansari.stackoverflowapp.api

import info.firozansari.stackoverflowapp.api.model.StackoverflowResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface StackoverflowService {
    @GET("questions/unanswered")
    fun getQuestions(
        @Query("formDate") formDate: Long,
        @Query("order") order: String = "desc",
        @Query("min") min: Int = 5,
        @Query("sort") sort: String = "votes",
        @Query("site") site: String = "stackoverflow",
        @Query("tagged") tagged: String = "android"
    ): Deferred<StackoverflowResponse>
}