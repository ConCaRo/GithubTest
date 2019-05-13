package trong.test.github.features.gits

import android.arch.lifecycle.MutableLiveData
import trong.test.github.core.base.BaseViewModel
import trong.test.github.core.domain.interactor.GetGitsUseCase
import trong.test.github.core.model.Git
import javax.inject.Inject

class GitsViewModel
@Inject constructor(val getGitsUseCase: GetGitsUseCase) : BaseViewModel() {

    val gits = MutableLiveData<List<Git>>()
    private var currentPage = 1     // starting page  1
    val PER_PAGE = 20           // loading each page 20 item
    var canLoadMore = true      // Default can load more
    var isLoading = false       // Track status of loading -> need improve

    fun getGits() = getGitsUseCase(GetGitsUseCase.Param(currentPage, PER_PAGE, (currentPage - 1) * PER_PAGE)) { it.either(::handleFailure, ::handleGits) }

    private fun handleGits(gits: List<Git>) {
        val newList: MutableList<Git> = arrayListOf()
        this.gits?.value?.let { newList.addAll(this.gits.value!!) }
        newList.addAll(gits)
        this.gits.value = newList

        // Check load more and increase current page
        canLoadMore = gits.size == PER_PAGE && this.gits.value?.size?.compareTo(100) == -1
        isLoading = false
        currentPage++
    }

    fun loadMore() {
        isLoading = true
        getGits()
    }
}