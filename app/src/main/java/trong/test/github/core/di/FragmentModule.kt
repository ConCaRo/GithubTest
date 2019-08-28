package trong.test.github.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import trong.test.github.features.gitdetail.GitDetailFragment
import trong.test.github.features.gits.GitsFragment
import trong.test.github.features.nestedrecyclerview.NestedRecyclerViewFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeGitsFragment(): GitsFragment

    @ContributesAndroidInjector
    abstract fun contributeGitDetailFragment(): GitDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeNestedRecyclerViewFragment(): NestedRecyclerViewFragment
}