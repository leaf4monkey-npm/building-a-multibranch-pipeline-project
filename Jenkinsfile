pipeline {
  agent {
    docker {
      image 'node:6-alpine'
      args '-p 8000:3000 -p 9000:5000'
    }
    
  }
  stages {
    stage('Build') {
      steps {
        sh 'yarn install'
        libraryResource 'hello_world'
      }
    }
    stage('Load Inner') {
      steps {
        script {
          def b = load('nodeBuild.groovy')
          b.run()
        }
        
        libraryResource 'hello_world'
      }
    }
    stage('Test') {
      steps {
        sh './jenkins/scripts/test.sh'
      }
    }
    stage('Deliver for development') {
      when {
        branch 'development'
      }
      steps {
        sh './jenkins/scripts/deliver-for-development.sh'
        input 'Finished using the web site? (Click "Proceed" to continue)'
        sh './jenkins/scripts/kill.sh'
      }
    }
    stage('Deploy for production') {
      when {
        branch 'production'
      }
      steps {
        sh './jenkins/scripts/deploy-for-production.sh'
        input 'Finished using the web site? (Click "Proceed" to continue)'
        sh './jenkins/scripts/kill.sh'
      }
    }
  }
  environment {
    CI = 'true'
  }
}