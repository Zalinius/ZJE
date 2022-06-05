@Library('zalinius-shared-library') _

//ZJE Jenkinsfile
pipeline {
    agent any
    tools {
        maven 'maven3'
    }    
    stages {
   	// Note that the agent automatically checks out the source code from Github	
        stage('Build') { steps { buildAndTest()}}
        stage('Deploy') {
            when { branch 'main'}
            environment { SONAR_CREDS = credentials('sonar')}
            steps {
                sonarScan()
                deployLibrary()
    }}}
    post {
        always  { testReport() }    
        success { githubSuccess() }    
        failure { githubFailure() }    
    }
}
