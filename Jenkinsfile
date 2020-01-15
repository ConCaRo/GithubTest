
pipeline {
    agent any
    stages {
        stage("Build") {

            steps {
                sh "echo Building..."
                // sh 'printenv | sort'
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
                withEnv(['JIRA_SITE=meomeo']) {
                  /*def fields = jiraGetFields idOrKey: "${jiraticket}"
                  echo fields.data.toString()*/

                  def transitions = jiraGetIssueTransitions idOrKey: "${jiraticket}"
                  def abc = transitions.data.transitions
                  echo "${abc.size()}"



                  def qa = abc.find { item ->
                    item.to.name == "QA"
                  }
                  echo qa.toString()
                  /* def transitionInput =
                      [
                          transition: [
                              id: '5'
                          ]
                      ]

                  jiraTransitionIssue idOrKey: "${jiraticket}", input: transitionInput */

                  /*def issue = jiraGetIssue idOrKey: "${jiraticket}"
                  echo issue.data.fields.status.name.toString()*/
                }
                sh 'printenv | sort'
            }
        }
        failure {
            sh 'printenv | sort'
        }
    }
}