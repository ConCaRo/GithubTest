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
            post {
                always {
                    sh "echo Finish Test"
                }
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
              }
            }
            steps {
                sh "echo Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
            post {
                always {
                    sh "echo Finish Deploy"
                    slackSend "Build Started - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", color: '#BADA55'
                }
            }
        }
    }
}