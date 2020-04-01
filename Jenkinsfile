
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
                build(
                  job: 'GitTest_dev_pr_Pipeline_trigger',
                  parameters: [
                    [
                      $class: 'StringParameterValue',
                      name: 'gitbranch',
                      value: "${gitbranch}",
                    ]
                  ]
                )
                sh "echo Finish test "
            }
        }
    }
}