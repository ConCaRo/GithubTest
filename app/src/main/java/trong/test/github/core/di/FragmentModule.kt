package trong.test.github.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import trong.test.github.features.fragmentnestedrecyclerview.FragmentNestedRecyclerViewFragment
import trong.test.github.features.fragmentnestedrecyclerview.GitsFragment1
import trong.test.github.features.fragmentnestedrecyclerview.GitsFragment2
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

    @ContributesAndroidInjector
    abstract fun contributeFragmentNestedRecyclerViewFragment(): FragmentNestedRecyclerViewFragment

    @ContributesAndroidInjector
    abstract fun contributeGitsFragment1(): GitsFragment1

    @ContributesAndroidInjector
    abstract fun contributeGitsFragment2(): GitsFragment2
}