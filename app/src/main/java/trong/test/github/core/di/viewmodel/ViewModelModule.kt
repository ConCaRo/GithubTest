package trong.test.github.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import trong.test.github.features.gitdetail.GitDetailViewModel
import trong.test.github.features.gits.GitsViewModel

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
}