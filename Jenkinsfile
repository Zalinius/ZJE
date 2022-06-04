@Library('zalinius-shared-library') _

//ZJE Jenkinsfile
pipeline {
    agent any
    tools {
        maven 'maven3'
    }
    
    stages {
   	// Note that the agent automatically checks out the source code from Github
	buildAndTest()
	deployLibrary()
	sonarScan(sonarcubeHost: $SONARQUBE_HOST, sonarcubeCredentials: credentials('sonar'))
    }
    
    post {
	reportResults()
    }
}
