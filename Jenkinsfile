pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Nadeeshasanjaya/devops-projecct2.git'
            }
        }

        stage('Build Maven') {
    tools {
        maven 'M3'
    }
    steps {
       sh '''
        docker run --rm \
        -v $WORKSPACE:/app \
        -w /app \
        maven:3.9.6-eclipse-temurin-17 \
        find /app -type f -name "pom.xml"
        '''
       sh '''
        docker run --rm \
        -v $WORKSPACE:/app \
        -w /app \
        maven:3.9.6-eclipse-temurin-17 \
        bash -c "tree /app -L 3 2>/dev/null || find /app -maxdepth 3 -type f | head -20"
        '''
    }
}

        stage('Docker Build') {
            steps {
                sh 'docker build -t devops-project2 devops-project2/devops-project2'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'docker stop devops-app || true'
                sh 'docker rm devops-app || true'
            }
        }

        stage('Run App') {
            steps {
                sh 'docker run -d --name devops-app -p 8082:8080 devops-project2'
            }
        }
    }
}