package trong.test.github.features.fragmentnestedrecyclerview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import trong.test.github.core.base.BaseViewModel
import trong.test.github.core.domain.interactor.GetNestedDataUseCase
import trong.test.github.core.model.Parent
import javax.inject.Inject

class FragmentNestedRecyclerViewViewModel
@Inject constructor(val getGitsUseCase: GetNestedDataUseCase) : BaseViewModel() {

    val nestedData = MutableLiveData<List<Parent>>()

    val isLoading = MutableLiveData<Boolean>().apply { value = true }

    fun getData() = getGitsUseCase(GetNestedDataUseCase.Param("")) { it.either(::handleFailure, ::handleNestedData) }

    private fun handleNestedData(data: List<Parent>) {
        isLoading.value = false
        nestedData.value = data
    }

}