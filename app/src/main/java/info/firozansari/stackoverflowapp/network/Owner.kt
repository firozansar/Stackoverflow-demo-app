package info.firozansari.stackoverflowapp.network

import com.squareup.moshi.Json

data class Owner(
    @Json(name = "user_id") val userId: String?,
    @Json(name = "profile_image") val profileImage: String?,
    @Json(name = "display_name") val displayName: String?,
    @Json(name = "link") val profileLink: String?
)