pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t warehouse-backend-app:latest .'
            }
        }

        stage('Run Container (Optional Test)') {
            steps {
                echo 'Running container to verify build...'
                sh '''
                docker rm -f warehouse-test || true
                docker run -d --name warehouse-test -p 8085:8080 warehouse-backend-app:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
