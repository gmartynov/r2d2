pipeline {
    
    environment {
        PROJECT_NAME = "DOCKER CACTI"
        OWNER_NAME = "GM"
    }

    stages {
        stage("docker login") {
            steps {
                echo " ============== docker login =================="
                withCredentials([usernamePassword(credentialsId: 'dockerhub_gmartynov', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh """
                    docker login -u $USERNAME -p $PASSWORD
                    """
                }
            }
        }
            
        stage("docker run") {
            steps {
                echo "===========docker build==========="
                sh """
                docker run -itp 5050:80 smcline06/cacti
                """
            }
        }
    }
}