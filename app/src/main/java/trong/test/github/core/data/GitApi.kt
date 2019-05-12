package trong.test.github.core.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import trong.test.github.core.model.Git

/**
 * GitApi is interface for retrofit
 */
interface GitApi {
    companion object {
        const val PARAM_GIT_ID = "id"
        const val GITS = "users"
        const val GIT_DETAIL = "users/{id}"
    }

    @GET(GITS) fun gits(@Query("page") page: Int, @Query("per_page") per_page: Int,
                        @Query("since") since: Int) : Call<List<Git>>
    @GET(GIT_DETAIL) fun gitDetail(@Path(PARAM_GIT_ID) id: String) : Call<Git>

}