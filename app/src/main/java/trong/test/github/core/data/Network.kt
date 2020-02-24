package trong.test.github.core.data

import retrofit2.Call
import trong.test.github.core.base.NetworkHandler
import trong.test.github.core.domain.GitRepository
import trong.test.github.core.exception.Failure
import trong.test.github.core.functional.Either
import trong.test.github.core.model.Children
import trong.test.github.core.model.Git
import trong.test.github.core.model.Parent
import java.util.*
import javax.inject.Inject

/**
 * Responsible for network call from service (implement repository)
 * @see GitRepository
 */
class Network @Inject constructor(
    val networkHandler: NetworkHandler,
    val gitService: GitService
) : GitRepository {

    override fun gits(page: Int, per_page: Int, since: Int): Either<Failure, List<Git>> {
        return when (networkHandler.isConnected) {
            // true -> request(gitService.gits(page, per_page, since), { it.map { it } }, Collections.emptyList())
            true -> Either.Right(getDummuGit(per_page))
            false, null -> Either.Left(Failure.NetworkError)
        }
    }

    override fun gitDetail(id: String): Either<Failure, Git> {
        return when (networkHandler.isConnected) {
            true -> request(gitService.gitDetail(id), { it.transform() }, Git.empty())
            false, null -> Either.Left(Failure.NetworkError)
        }
    }

    override fun nestedData(): Either<Failure, List<Parent>> {
        return when (networkHandler.isConnected) {
            true -> Either.Right(getDummyNestedData())
            false, null -> Either.Left(Failure.NetworkError)
        }
    }

    // process request, transform data from data layer to domain layer (no need now)
    private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform(response.body() ?: default))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (e: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }

    fun getDummyNestedData(): List<Parent> {
        return arrayListOf(Parent(), Parent(), Parent(), Parent(), Parent(), Parent(), Parent(), Parent())
    }

    fun getDummyNestedChild(): List<Children> {
        return arrayListOf(Children(), Children(), Children(), Children())
    }

    fun getDummuGit(number: Int) : List<Git> {
        val list = arrayListOf<Git>()
        for(i in 0..number) {
            list.add(Git.empty())
        }
        return list
    }

}