pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                sh "echo Building..."
                // sh "chmod +x gradlew"
                // sh 'printenv'
                // sh "./gradlew clean"
                // sh "./gradlew assembleDebug"
                def path = app/build/outputs/apk/debug/*.apk
                def file = ${path##*/}
                def extension = "${file##*.}"
                def filename = "${file%.*}"
                sh "echo ${file}"
                sh "echo ${extension}"
                sh "echo ${filename}"
                // sh "mv ${path} ${filename}-${gitbranch}.${extension}"
                // dropbox configName: 'Dropbox location', remoteDirectory: '', removePrefix: 'app/build/outputs/apk/debug', sourceFiles: 'app/build/outputs/apk/debug/*.apk'

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
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
              }
            }
            steps {
                sh "echo Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
    }
    post {
        always {
            sh "echo Finish "
            slackSend message: "Branch `${gitbranch}` Build ${currentBuild.currentResult} - Job ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)", color: '#BADA55', channel: "jenkinstest"
        }
    }
}