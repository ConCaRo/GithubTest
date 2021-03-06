package trong.test.github.features.nestedrecyclerview

import android.arch.lifecycle.MutableLiveData
import trong.test.github.core.base.BaseViewModel
import trong.test.github.core.domain.interactor.GetNestedDataUseCase
import trong.test.github.core.model.Parent
import javax.inject.Inject

class NestedRecyclerViewViewModel
@Inject constructor(val getGitsUseCase: GetNestedDataUseCase) : BaseViewModel() {

    val nestedData = MutableLiveData<List<Parent>>()

    fun getData() = getGitsUseCase(GetNestedDataUseCase.Param("")) { it.either(::handleFailure, ::handleNestedData) }

    private fun handleNestedData(data: List<Parent>) {
        nestedData.value = data
    }

}