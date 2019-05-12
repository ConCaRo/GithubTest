package trong.test.github.features.gitdetail

import android.arch.lifecycle.MutableLiveData
import trong.test.github.core.base.BaseViewModel
import trong.test.github.core.domain.interactor.GetGitsDetailUseCase
import trong.test.github.core.model.Git
import javax.inject.Inject

class GitDetailViewModel @Inject constructor(val getGitDetaiUseCase: GetGitsDetailUseCase) : BaseViewModel() {

    val gitDetail = MutableLiveData<Git>()

    fun getGitDetail(id: String) = getGitDetaiUseCase(GetGitsDetailUseCase.Param(id)) { it.either(::handleFailure, ::handleGitDetail) }

    fun handleGitDetail(git: Git) {
        this.gitDetail.value = git
    }
}