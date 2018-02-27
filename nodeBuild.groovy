pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'yarn install'
      }
    }
  }

  stage('Test') {
    steps {
      sh './jenkins/scripts/test.sh'
    }
  }
}
