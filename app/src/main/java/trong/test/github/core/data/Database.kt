package trong.test.github.core.data

import trong.test.github.core.domain.GitRepository
import trong.test.github.core.exception.Failure
import trong.test.github.core.functional.Either
import trong.test.github.core.model.Git
import trong.test.github.core.model.Parent

/**
 * Responsible for calling data from database (implement repository)
 * @see GitRepository
 */
class Database : GitRepository {
    override fun gits(page: Int, per_page: Int, since: Int): Either<Failure, List<Git>> {
        return Either.Right(arrayListOf())
    }

    override fun gitDetail(id: String): Either<Failure, Git> {
        return Either.Right(Git.empty())
    }

    override fun nestedData(): Either<Failure, List<Parent>> {
        return Either.Right(arrayListOf())
    }

}
