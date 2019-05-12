package trong.test.github.core.domain

import trong.test.github.core.exception.Failure.FeatureFailure

/**
 * Git Failure
 */
class GitFailure {
    class GitsListFailure : FeatureFailure()
    class GitDetailFailure : FeatureFailure()
}