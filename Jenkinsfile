pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Nadeeshasanjaya/devops-projecct2.git'
            }
        }

       stage('Build Maven Project') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t devops-project2 .'
            }
        }

        stage('Run Container') {
            steps {
                sh 'docker run -d -p 8081:8081 devops-project2'
            }
        }

    }
}