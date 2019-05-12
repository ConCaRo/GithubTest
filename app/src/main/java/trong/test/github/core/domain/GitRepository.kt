package trong.test.github.core.domain

import trong.test.github.core.exception.Failure
import trong.test.github.core.functional.Either
import trong.test.github.core.model.Git

/**
 * This repository will be implemented by network api or database based on Use case
 */
interface GitRepository {
    fun gits(page: Int, per_page: Int, since: Int): Either<Failure, List<Git>>
    fun gitDetail(id: String): Either<Failure, Git>
}