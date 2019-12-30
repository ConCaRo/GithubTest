def FILENAME = ""
pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                sh "echo Building..."
                sh 'printenv | sort'
                script {
                    if(env.jiracomment != null) {
                        echo "jira comment ${jiracomment}"
                    }
                    if(evn.nothing) {
                        echo "nothing"
                    } else {
                        echo "something"
                    }
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
            script {
                if(env.jiracomment != null) {
                    echo "jira comment ${jiracomment}"
                }
                if(evn.nothing) {
                    echo "nothing"
                } else {
                    echo "something"
                }
            }
        }
    }
}