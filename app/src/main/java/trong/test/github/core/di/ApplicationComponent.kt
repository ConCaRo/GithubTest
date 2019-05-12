package trong.test.github.core.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import trong.test.github.GitApplication
import trong.test.github.core.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ViewModelModule::class, ApplicationModule::class,
        ActivityModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GitApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: GitApplication)
}