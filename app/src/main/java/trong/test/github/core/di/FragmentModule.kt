package trong.test.github.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import trong.test.github.features.gitdetail.GitDetailFragment
import trong.test.github.features.gits.GitsFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeGitsFragment(): GitsFragment

    @ContributesAndroidInjector
    abstract fun contributeGitDetailFragment(): GitDetailFragment
}