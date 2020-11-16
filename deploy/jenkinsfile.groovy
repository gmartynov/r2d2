pipeline {
    agent any
    environment {
        PROJECT_NAME = "DOCKER PYTHONHTTP AND LYNX"
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
            
        stage("docker 1st run") {
            steps {
                echo "===========docker run==========="
                sh """
                docker run -d -p 8080:8080 gmartynov/python3http:latest
                """
            }
        }

        stage("docker 2nd run") {
            steps {
                echo "==========docker run============"
                sh """
                docker run -d gmartynov/lynx:latest
                """
            }
        }
    }
}