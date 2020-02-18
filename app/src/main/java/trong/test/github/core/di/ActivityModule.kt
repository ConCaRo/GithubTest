package trong.test.github.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import trong.test.github.features.fragmentnestedrecyclerview.FragmentNestedRecyclerviewActivity
import trong.test.github.features.fragmentnestedrecyclerview.GitsFragment1
import trong.test.github.features.gitdetail.GitDetailActivity
import trong.test.github.features.gits.GitsActivity
import trong.test.github.features.nestedrecyclerview.NestedRecyclerviewActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeGitsActivity(): GitsActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeGitDetailActivity(): GitDetailActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeNestedRecyclerviewActivity(): NestedRecyclerviewActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeFragmentNestedRecyclerviewActivity(): FragmentNestedRecyclerviewActivity

}