package info.firozansari.stackoverflowapp.api.model

import com.squareup.moshi.Json

data class StackoverflowResponse(
    @Json(name = "items") val questionsList: List<Question>?,
    @Json(name = "has_more") val hasMore: Boolean = false,
    @Json(name = "quota_max") val quoteMax: Int = 0,
    @Json(name = "quota_remaining") val quoteRemaining: Int = 0
)
