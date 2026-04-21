pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ShubhamChoudhary147/Jenkins-Docker-Integration-DockerSwarm.git'
            }
        }

        stage('Remove Old Stack') {
            steps {
                bat '''
                echo Cleaning old containers...
                docker stack rm ecommerce || exit 0
		        ping -n 5 127.0.0.1 > nul
                '''
            }
        }

        stage('Deploy Using Docker Swarm') {
            steps {
                bat '''
                echo deploying stack from docker Hub images...
                docker stack deploy -c docker-stack.yaml ecommerce
                '''
            }
        }

        stage('Verify Containers') {
            steps {
                bat '''
		docker stack services ecommerce
		docker service ls
		'''
            }
        }

    }

    post {
        success {
            echo "All services deployed successfully 🚀"
        }
        failure {
            echo "Pipeline failed ❌ check logs"
        }
    }
}