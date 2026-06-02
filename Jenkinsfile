pipeline {
    agent any

    parameters {
        booleanParam(name: 'PUSH_TO_DOCKER_HUB', defaultValue: false, description: 'Enable Docker Hub push when Docker Hub credentials are configured')
        string(name: 'DOCKERHUB_CREDENTIALS_ID', defaultValue: 'dockerhub-credentials', description: 'Jenkins credentials ID for Docker Hub login')
    }

    stages {

        stage('Clean Workspace') {
    steps {
        cleanWs()
    }
}

        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Check Docker') {
            steps {
                script {
                    def rc = sh(returnStatus: true, script: 'docker info >/dev/null 2>&1')
                    env.DOCKER_AVAILABLE = (rc == 0).toString()
                    if (rc != 0) {
                        echo 'Docker not available or cannot access /var/run/docker.sock — Docker stages will be skipped.'
                    } else {
                        echo 'Docker is available.'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            when {
                expression { return env.DOCKER_AVAILABLE == 'true' }
            }
            steps {
                sh 'docker build -t devops-project2:latest .'
            }
        }

        stage('Push Docker Hub') {
            when {
                expression { return params.PUSH_TO_DOCKER_HUB && env.DOCKER_AVAILABLE == 'true' }
            }
            steps {
                withCredentials([usernamePassword(
                    credentialsId: params.DOCKERHUB_CREDENTIALS_ID,
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker tag devops-project2:latest $DOCKER_USER/devops-project2:latest'
                    sh 'docker push $DOCKER_USER/devops-project2:latest'
                }
            }
        }

        stage('Deploy container') {
            steps {
                sh '''
                docker stop devops-project2 || true
                docker rm devops-project2 || true
                docker run -d -p 8080:8080 --name devops-project2 devops-project2:latest
                '''
            }
        }
    }
}