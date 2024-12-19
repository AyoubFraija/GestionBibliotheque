pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Default'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/AyoubFraija/GestionBibliotheque.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
       stage('Quality Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat """mvn clean verify sonar:sonar \
                         -Dsonar.projectKey=GestionBibliotheque \
                         -Dsonar.projectName='GestionBibliothèque' \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.token=sqp_96cd8242c584612e0cd3bb89edc6d3724885dab1"""
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }
    post {
        success {
            emailext to: 'fraija9@gmail.com',
                subject: 'Build Success',
                body: 'Le build a été complété avec succès.'
        }
        failure {
            emailext to: 'fraija9@gmail.com',
                subject: 'Build Failed',
                body: 'Le build a échoué.'
        }
    }
}
