//ZJE Jenkinsfile
pipeline {
    agent any
    stages {
   		// Note that the agent automatically checks out the source code from Github	
        stage('Compile') { 
            steps {
            	sh 'mvn --batch-mode compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn --batch-mode test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package Jar') {
            steps {
                sh 'mvn --batch-mode -DskipTests clean package'
            }
        }
        stage('Deploy') {
        	when {
 				branch 'main'
        	}
			steps {
                sh 'mvn --batch-mode -DskipTests clean install'  //Install publishes to the local jenkins Maven repo
                												 //Only done with the main branch
	        }
	    }
    }
}