pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                sh "echo Building..."
                sh "chmod +x gradlew"
                sh "./gradlew clean"
                sh "./gradlew assembleDebug"
            }
        }
        stage("Test") {
            steps {
                sh "echo Testing..."
                sh "/gradlew testDebugUnitTest"
            }
        }
    }
}