pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
                failure {
                    echo "Build failed!"
                }
            }
        }

        stage('Deploy to QA') {
            steps {
                echo "deploy to qa done"
            }
            post {
                always {
                    echo "Deployment to QA finished."
                }
            }
        }

        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Aayush-Mishraa/Ecommerce-TechProduct-E2E-Automation-Project-'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/testng_regression.xml"
                }
            }
        }

        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }

        stage('Publish ChainTest Report') {
            steps {
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false,
                             keepAll: true,
                             reportDir: 'target/chaintest',
                             reportFiles: 'Index.html',
                             reportName: 'HTML Regression ChainTest Report',
                             reportTitles: ''])
            }
        }

        stage('Deploy to Stage') {
            steps {
                echo "deploy to Stage"
            }
        }

        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Aayush-Mishraa/Ecommerce-TechProduct-E2E-Automation-Project-'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/testng_Sanity.xml"
                }
            }
        }

        stage('Publish Sanity ChainTest Report') {
            steps {
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false,
                             keepAll: true,
                             reportDir: 'target/chaintest',
                             reportFiles: 'Index.html',
                             reportName: 'HTML Sanity ChainTest Report',
                             reportTitles: ''])
            }
        }

        stage('Deploy to PROD') {
            steps {
                echo "deploy to PROD"
            }
        }
    }
}
