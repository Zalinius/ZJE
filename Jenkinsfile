@Library('zalinius-shared-library') _

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
        always  { testReport() }    
        success { githubSuccess() }    
        failure { githubFailure() }    
    }
}
