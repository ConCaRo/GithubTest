package trong.test.github.core.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import trong.test.github.BuildConfig
import trong.test.github.GitApplication
import trong.test.github.core.data.GitApi
import trong.test.github.core.data.GitService
import trong.test.github.core.data.Network
import trong.test.github.core.domain.GitRepository
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: GitApplication): Context = application

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request = chain.request()
            val headers = request.headers().newBuilder().add("Authorization", "3a7e91e8130c505ab51c:35fa27a63489f3d4eef9e5cc3acf8e0f7d3c6bd6").build()
            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(headerAuthorizationInterceptor)
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGitApi(retrofit: Retrofit) = retrofit.create(GitApi::class.java)

    @Provides
    @Singleton
    fun provideGitService(gitApi: GitApi) = GitService(gitApi)

    @Provides
    @Singleton
    fun provideGitRepository(source: Network): GitRepository = source

}