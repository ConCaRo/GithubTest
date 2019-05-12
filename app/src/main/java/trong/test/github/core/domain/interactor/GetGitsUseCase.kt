package trong.test.github.core.domain.interactor

import trong.test.github.core.domain.GitRepository
import trong.test.github.core.domain.UseCase
import trong.test.github.core.exception.Failure
import trong.test.github.core.functional.Either
import trong.test.github.core.model.Git
import javax.inject.Inject

class GetGitsUseCase @Inject constructor(val gitRepository: GitRepository) : UseCase<List<Git>, GetGitsUseCase.Param>() {

    override suspend fun run(params: Param): Either<Failure, List<Git>> = gitRepository.gits(params.page, params.per_page, params.since)

    data class Param(val page: Int, val per_page: Int, val since: Int)
}