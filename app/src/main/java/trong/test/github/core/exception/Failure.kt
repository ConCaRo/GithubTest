package trong.test.github.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkError : Failure()
    object ServerError : Failure()
    abstract class FeatureFailure : Failure()
}