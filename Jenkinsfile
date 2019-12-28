pipeline {
    agent any
    environment {
        file = ''
    }
    stages {
        stage("Build") {
            steps {
                sh "echo Building..."
                // sh "chmod +x gradlew"
                // sh 'printenv'
                // sh "./gradlew clean"
                // sh "./gradlew assembleDebug"
                script {
                    def pathApk = 'app/build/outputs/apk/debug/*.apk'
                    def files = findFiles(glob: pathApk)
                    def apkFileName = files[0].name
                    def name = input.substring(0, apkFileName.lastIndexOf("."))
                    def extension = input.substring(apkFileName.lastIndexOf(".") + 2)
                    echo """${files[0].name} ${files[0].path} ${files[0].directory} ${files[0].length} ${files[0].lastModified}"""
                    echo """${name} ${extenstion}"""
                    sh "mv ${path} ${name}-${gitbranch}.${extension}"
                }
                // echo "${file}"

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