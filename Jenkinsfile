@Library('zalinius-shared-library') _

//ZJE Jenkinsfile
pipeline {
    agent any
    tools {
        maven 'maven3'
    }    
    stages {
   	// Note that the agent automatically checks out the source code from Github
        stage('Build') { steps{ buildAndTest() }}
        stage('Deploy') { steps{ deployLibrary() }}
        stage('Sonar') { steps{ sonarScan(sonarcubeHost: $SONARQUBE_HOST, sonarcubeCredentials: credentials('sonar')) }}
    }
    post {
        always  { testReport() }    
        success { githubSuccess() }    
        failure { githubFailure() }    
    }
}
