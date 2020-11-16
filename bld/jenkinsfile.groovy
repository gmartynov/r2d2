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
            
        stage("docker 1st build") {
            steps {
                echo "===========docker build==========="
                sh """
                docker build -t gmartynov/python3http:latest ./dkr/pythonhttp
                """
            }
        }

        stage("docker 2nd build") {
            steps {
                echo "==========docker build============"
                sh """
                docker build -t gmartynov/lynx:latest ./dkr/lynx
                """
            }
        }

        stage("docker 1st push") {
            steps {
                echo "==========docker push============="
                sh """
                docker push gmartynov/python3http:latest
                """
            }
        }

        stage("docker 2nd push") {
            steps {
                echo "==========docker push============"
                sh """
                docker push gmartynov/lynx:latest
                """
            }
        }
    }
}