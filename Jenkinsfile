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
            stage('Quality Analysis') {
            steps {
                    withSonarQubeEnv('SonarQube') { 
                        sh 'mvn clean verify sonar:sonar '
                    }
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
