def FILENAME = ""
pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                sh "echo Building..."
                sh 'printenv | sort'
                if($jiracomment) {
                    echo "$jiracomment"
                }
                if($nothing) {
                    echo "nothing"
                } else {
                    echo "something"
                }
            }
        }
        stage("Test") {
            steps {
                sh "echo Testing..."
                // sh "./gradlew testDebugUnitTest"
            }
            post {
                always {
                    sh "echo Finish Test"
                }
            }
        }
    }
    post {
        always {
            sh "echo Finish "
        }
    }
}