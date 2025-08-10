pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build Image') {
      steps {
        sh "docker build -t bookstore-api-tests:${env.BUILD_NUMBER} ."
      }
    }
    stage('Run Tests in Container') {
      steps {
        // Mount workspace/target so test artifacts are available to Jenkins
        sh '''
          docker run --rm             -v $(pwd)/target:/app/target             bookstore-api-tests:${env.BUILD_NUMBER}
        '''
      }
    }
    stage('Publish Reports') {
      steps {
        // Assumes Allure plugin installed on Jenkins
        allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
      }
    }
    stage('Archive') {
      steps {
        archiveArtifacts artifacts: 'target/surefire-reports/*.xml, target/allure-results/**', fingerprint: true
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
    }
  }
}
