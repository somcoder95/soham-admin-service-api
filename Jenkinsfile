node {

	try {
		notifySlack()	
		stage('Checkout Code From GIT') {
			checkout scm
		}
		
	    stage('Build Project (Static Code Analysis, Compile, JUnit, Code Coverage)') {
	    		withSonarQubeEnv('Idexcel SonarQube Server') {
				def M2_Home = tool name: 'maven_3_6_0', type: 'maven'
	       		sh "${M2_Home}/bin/mvn clean surefire-report:report jacoco:prepare-agent package jacoco:report sonar:sonar"
	       	}
	    }
	    
	    stage("Wait for Sonar Quality Gate Response"){
			timeout(time: 1, unit: 'HOURS') { 
			  def qualityGate = waitForQualityGate()
			  if (qualityGate.status != 'OK') {
			    error "Pipeline aborted due to quality gate failure: ${qualityGate.status}"
			  }
			}
		}
	    
	    stage('Build Docker Image') {
	    		sh 'docker build -t sohamgupte1/sohamdocker:${BUILD_NUMBER} -t sohamgupte1/sohamdocker:latest -f Dockerfile .'
	    }
	        
	    stage('Push Docker Image to Docker Hub') { 
	    		withCredentials([usernamePassword(credentialsId: 'sohamDocker', passwordVariable: 'pass', usernameVariable: 'user')]) {
	    			sh 'docker login -u $user -p $pass' 
	        		sh 'docker push sohamgupte1/sohamdocker'
			}	 
	    }
	    
	    stage('Deploy in AWS ECS'){
            sh 'aws ecs update-service --region us-east-1 --cluster idexceldemo --service soham-new-service --force-new-deployment'
       		sh 'sleep 30'
       		sh 'aws ecs wait services-stable --region us-east-1 --cluster idexceldemo --service soham-new-service'
       	}
	} catch (Exception e) {
		currentBuild.result = 'FAILURE'
        throw e
    } finally {
        notifySlack(currentBuild.result)
    }
    
}

def notifySlack(String buildStatus = 'STARTED') {
    // Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'

    def color

    if (buildStatus == 'STARTED') {
        color = '#D4DADF'
    } else if (buildStatus == 'SUCCESS') {
        color = '#BDFFC3'
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFFE89'
    } else {
        color = '#FF9FA1'
    }

    def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"

    slackSend(color: color, message: msg)
}
