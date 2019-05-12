package trong.test.github.core.data

import retrofit2.Call
import trong.test.github.core.model.Git
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provide services for all apis
 * @see GitApi
 */
@Singleton
class GitService @Inject constructor(val gitApi: GitApi): GitApi {

    override fun gits(page: Int, per_page: Int, since: Int): Call<List<Git>>  = gitApi.gits(page, per_page, since)
    override fun gitDetail(id: String): Call<Git> =  gitApi.gitDetail(id)
}