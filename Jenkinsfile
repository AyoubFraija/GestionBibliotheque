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
                    bat 'sonar-scanner.bat -D"sonar.projectKey=GestionBibliotheque" -D"sonar.sources=." -D"sonar.host.url=http://localhost:9000" -D"sonar.token=sqp_6e0806f911d3de06c600aef132683b4d4ed04728"'
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
