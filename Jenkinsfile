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
                sh "./gradlew testDebugUnitTest"
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
              }
            }
            steps {
                sh "echo ${currentBuild.result}"
            }
        }
    }
}