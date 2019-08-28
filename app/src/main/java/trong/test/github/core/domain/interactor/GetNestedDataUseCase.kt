package trong.test.github.core.domain.interactor

import trong.test.github.core.domain.GitRepository
import trong.test.github.core.domain.UseCase
import trong.test.github.core.exception.Failure
import trong.test.github.core.functional.Either
import trong.test.github.core.model.Parent
import javax.inject.Inject

class GetNestedDataUseCase @Inject constructor(val gitRepository: GitRepository) : UseCase<List<Parent>, GetNestedDataUseCase.Param>() {

    override suspend fun run(params: Param): Either<Failure, List<Parent>> = gitRepository.nestedData()

    data class Param(val id: String)
}