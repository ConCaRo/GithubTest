package trong.test.github.core.model

import com.google.gson.annotations.SerializedName

class Git(
    val id: String,
    val name: String?,
    val login: String,
    @SerializedName("avatar_url") val avatar: String?,
    val bio: String?,
    val site_admin: Boolean?,
    val location: String?,
    val blog: String?) {

    companion object {
        fun empty() = Git("", "", "", "", "", false, "", "")
    }

    // Transform from data layer to domain layer
    fun transform(): Git {
        return this
    }

}