pipeline {
    agent any

    stages {

        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
    steps {
        git branch: 'main',
            url: 'https://github.com/Nadeeshasanjaya/devops-projecct2.git'
    }
}

        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t devops-project2:latest .'
            }
        }
    }
}