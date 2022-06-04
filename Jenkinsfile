@Library('zalinius-shared-library') _

void setBuildStatus(String message, String state) {
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: env.GIT_URL],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
  ]);
}


//ZJE Jenkinsfile
pipeline {
    agent any
    tools {
        maven 'maven3'
    }
    environment{
        SONAR_CREDS=credentials('sonar')
    }
    
    stages {
   		// Note that the agent automatically checks out the source code from Github	
        stage('Build') {
            steps {
                buildAndTest()
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                sonarScan(sonarcubeHost: $SONARQUBE_HOST, sonarcubeCredentials: credentials('sonar'))
                deployLibrary()
	    }
	}
    }
    
    post {

        always {
            junit '**/target/*-reports/TEST-*.xml'
        }
    
        success {
            setBuildStatus("Build succeeded", "SUCCESS");
        }
        failure {
            setBuildStatus("Build failed", "FAILURE");
        }
    }
}
