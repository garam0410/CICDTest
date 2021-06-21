pipeline {
    agent none
    options { skipDefaultCheckout(true) }
    stages {
        stage('Build and Test') {
            agent {
                docker {
                    image 'maven:3-alpine'
//                     args '-v /root/.m2:/root/.m2'
                }
            }
            options { skipDefaultCheckout(false) }
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Docker build') {
            agent any
            steps {
                sh 'docker build -t test .'
            }
        }
        stage('Docker run') {
            agent any
            steps {
                sh 'docker ps -f name=test -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=test -q | xargs -r docker container rm'
                sh 'docker rmi $(docker images -f "dangling=true" -q)'
                sh 'docker run -d --name test -p 8080:8080 test'
            }
        }
    }
}