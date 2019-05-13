# Github App

Create an Android App which shows the GitHub users in a List and Detail View

## Introduction

App mainly used MVVM with Android architecture components from [JetPack](https://developer.android.com/jetpack)

### Functionality

The app has 2 main screens:

#### GitsFragment

This fragment displays a list of user information.

#### GitDetailFragment

This fragment displays user detail:  avatar_url, name, bio, login, site_admin (badge), location, blog.

## Known Issues

Error Handling: Too much boiler plate codes for checking which use case cause the error. Need error code and error message in Failure returned

Missing Model layer in MVVM to interact with Domain layer (now using directly ViewModel to get data)

## Inspiration

[Android Architecture Components](https://github.com/googlesamples/android-architecture-components)

[Android - Clean Architecture - Kotlin](https://github.com/android10/Android-CleanArchitecture-Kotlin)
especially the [Error Handling - Either<Failure, Type>](https://github.com/android10/Android-CleanArchitecture-Kotlin/blob/master/app/src/main/kotlin/com/fernandocejas/sample/core/functional/Either.kt)
