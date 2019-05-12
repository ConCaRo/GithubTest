package trong.test.github.core.domain.interactor

import trong.test.github.core.domain.GitRepository
import trong.test.github.core.domain.UseCase
import trong.test.github.core.exception.Failure
import trong.test.github.core.functional.Either
import trong.test.github.core.model.Git
import javax.inject.Inject

class GetGitsDetailUseCase @Inject constructor(val gitRepository: GitRepository) : UseCase<Git, GetGitsDetailUseCase.Param>() {

    override suspend fun run(params: Param): Either<Failure, Git> = gitRepository.gitDetail(params.id)

    data class Param(val id: String)
}