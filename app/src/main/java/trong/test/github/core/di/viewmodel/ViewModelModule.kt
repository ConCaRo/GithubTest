package trong.test.github.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import trong.test.github.features.fragmentnestedrecyclerview.FragmentNestedRecyclerViewFragment
import trong.test.github.features.fragmentnestedrecyclerview.FragmentNestedRecyclerViewViewModel
import trong.test.github.features.fragmentnestedrecyclerview.GitsFragment1
import trong.test.github.features.fragmentnestedrecyclerview.GitsViewModel1
import trong.test.github.features.gitdetail.GitDetailViewModel
import trong.test.github.features.gits.GitsViewModel
import trong.test.github.features.nestedrecyclerview.NestedRecyclerViewViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GitsViewModel::class)
    internal abstract fun bindGitsViewModel(viewModel: GitsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(GitDetailViewModel::class)
    internal abstract fun bindGitDetailViewModel(viewModel: GitDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NestedRecyclerViewViewModel::class)
    internal abstract fun bindNestedRecyclerViewViewModel(viewModel: NestedRecyclerViewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FragmentNestedRecyclerViewViewModel::class)
    internal abstract fun bindFragmentNestedRecyclerViewViewModel(viewModel: FragmentNestedRecyclerViewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GitsViewModel1::class)
    internal abstract fun bindGitsViewModel1(viewModel: GitsViewModel1): ViewModel
}