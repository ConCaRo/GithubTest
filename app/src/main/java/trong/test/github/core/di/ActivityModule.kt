package trong.test.github.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import trong.test.github.features.gitdetail.GitDetailActivity
import trong.test.github.features.gits.GitsActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeGitsActivity(): GitsActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeGitDetailActivity(): GitDetailActivity
}