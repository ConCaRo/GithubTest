
pipeline {
    agent any
    stages {
        stage("Build") {

            steps {
                sh "echo Building..."
                sh 'printenv | sort'
            }
        }
        stage("Test") {
            steps {
                sh "echo Testing..."
            }
        }
    }
    post {
        always {
            sh "echo Finish "
            script {
                def fields = jiraGetFields idOrKey: "${jiraticket}"
                echo fields.data.toString()
            }
        }
    }
}