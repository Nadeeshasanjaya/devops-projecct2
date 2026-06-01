pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t devops-project2:latest .'
            }
        }

        stage('Push Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
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