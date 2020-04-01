
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
            script {
                sh "echo Finish "
                build(
                  job: 'GitTest_dev_pr_Pipeline_trigger',
                  parameters: [
                    [
                      $class: 'StringParameterValue',
                      name: 'passData',
                      value: "my value",
                    ]
                  ]
                )
            }
        }
    }
}