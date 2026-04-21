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

        // stage('Build All Services') {
        //     steps {
        //         bat '''
        //         mvn clean package -DskipTests -f Eureka_Server/pom.xml
        //         mvn clean package -DskipTests -f ConfigServer/pom.xml
        //         mvn clean package -DskipTests -f APIGateway/pom.xml

        //         mvn clean package -DskipTests -f PRODUCT-CATALOG-SERVICE/pom.xml
        //         mvn clean package -DskipTests -f INVENTORY-SERVICE/pom.xml
        //         mvn clean package -DskipTests -f PRICING-SERVICE/pom.xml

        //         mvn clean package -DskipTests -f CART-SERVICE/pom.xml
        //         mvn clean package -DskipTests -f RECOMMENDATION-SERVICE/pom.xml
        //         '''
        //     }
        // }

        // stage('Docker Build') {
        //     steps {
        //         bat '''
        //         docker build -t eureka-server ./Eureka_Server
        //         docker build -t config-server ./ConfigServer
        //         docker build -t api-gateway ./APIGateway

        //         docker build -t product-catalogue-service ./PRODUCT-CATALOG-SERVICE
        //         docker build -t inventory-service ./INVENTORY-SERVICE
        //         docker build -t pricing-service ./PRICING-SERVICE

        //         docker build -t cart-service ./CART-SERVICE
        //         docker build -t recommendation-service ./RECOMMENDATION-SERVICE
        //         '''
        //     }
        // }

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