pipeline {
    agent any

    tools {
        maven 'Maven-3.9.12'
    }

    environment {
        STAGING_HOST = '172.31.14.45'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Archive Application') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }

        stage('Deploy to Staging') {
            steps {
                withCredentials([
                    sshUserPrivateKey(
                        credentialsId: 'staging-ssh-key-v2',
                        keyFileVariable: 'SSH_KEY',
                        usernameVariable: 'SSH_USER'
                    )
                ]) {
                    sh '''
                        mkdir -p ~/.ssh
                        chmod 700 ~/.ssh

                        ssh-keyscan -H "$STAGING_HOST" >> ~/.ssh/known_hosts
                        chmod 600 ~/.ssh/known_hosts

                        scp -i "$SSH_KEY" \
                            target/jenkins-cicd-project-1.0-SNAPSHOT.jar \
                            "$SSH_USER@$STAGING_HOST:/home/ubuntu/jenkins-cicd-project.jar"

                        ssh -i "$SSH_KEY" \
                            "$SSH_USER@$STAGING_HOST" \
                            'java -cp /home/ubuntu/jenkins-cicd-project.jar com.example.App'
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline completed successfully!'
        }
        failure {
            echo 'CI/CD Pipeline failed. Check the console output.'
        }
   }
}
