pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git url: 'https://github.com/dogancendel/bookstore-api-automation.git', branch: 'main'
      }
    }
    stage('Build Image') {
      steps {
        sh "docker build -t bookstore-api-tests:${env.BUILD_NUMBER} ."
      }
    }
    stage('Run Tests in Container') {
      steps {
        sh """
          docker run --rm \
            -v ${env.WORKSPACE}/target:/app/target \
            bookstore-api-tests:${env.BUILD_NUMBER}
        """
      }
    }
    stage('Publish Reports') {
      steps {
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
