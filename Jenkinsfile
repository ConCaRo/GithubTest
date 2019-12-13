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
                sh "echo Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
            post {
                always {
                    sh "echo Final Deploy"
                }
            }
        }
        post {
            always {
                echo "Final stages"
            }
            failure {
                mail to: vitali.nguyen@shopback.com, subject: 'The Pipeline failed :('
            }
        }
    }
}