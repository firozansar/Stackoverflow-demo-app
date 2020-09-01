package info.firozansari.stackoverflowapp.api.model

import com.squareup.moshi.Json
import info.firozansari.stackoverflowapp.api.model.Owner

data class Question(
    val tags: List<String>?,
    val owner: Owner?,
    @Json(name = "is_answered") val isAnswered: Boolean = false,
    @Json(name = "view_count") val viewCount: Int = 0,
    @Json(name = "answer_count") val answerCount: Int = 0,
    val score: Int = 0,
    @Json(name = "question_id") val questionID: Long?,
    @Json(name = "creation_date") val creationDate: Long?,
    @Json(name = "last_activity_date") val lastActivityDate: Long?,
    val title: String?
)
