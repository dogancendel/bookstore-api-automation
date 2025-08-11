# Bookstore API Automation Framework

## 🎯 Project Overview

A robust Java-based API automation framework for testing Bookstore API endpoints, built with industry best practices including comprehensive test coverage, modular design, and CI/CD integration.

- **Complete CRUD Operations Testing**: Full coverage for Authors and Books endpoints
- **Multi-layer Architecture**: Separates API clients, models, and test layers
- **Configuration Management**: Centralized API configuration
- **Dynamic Test Data Generation**: Using Faker for realistic test data
- **Test Data Factory**: Dynamic test data generation
- **CI/CD Ready**: Jenkins and Docker integration
- **Allure Reporting**: Detailed test reporting

## 📋 Test Coverage Summary

### ✅ Endpoints Covered
#### Authors
- **GET /api/v1/Authors** - Retrieve all authors
- **GET /api/v1/Authors/{id}** - Retrieve specific author by ID
- **POST /api/v1/Authors** - Create new author
- **PUT /api/v1/Authors/{id}** - Update existing author
- **DELETE /api/v1/Authors/{id}** - Delete author
----
#### Books
- **GET /api/v1/Books** - Retrieve all books
- **GET /api/v1/Books/{id}** - Retrieve specific book by ID
- **POST /api/v1/Books** - Create new book
- **PUT /api/v1/Books/{id}** - Update existing book
- **DELETE /api/v1/Books/{id}** - Delete book

### ✅ Test Categories
- **Happy Path Tests**: Basic functionality verification
- **Negative Tests**: Error handling and invalid inputs
- **Edge Case Tests**: Boundary conditions and special scenarios

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker
- Jenkins

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/dogancendel/bookstore-api-automation.git
   cd bookstore-api-automation
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```
### Running Tests

1. **Running all tests**
   ```bash
    mvn test
   

2. **Run specific test suite**
   ```bash
   mvn test -Dtest=AuthorApiTest
   ```

3. **Generate Allure report**
   ```bash
   mvn allure:serve
   ```

### 📊 Reporting 

#### Allure Reports
```bash
# Comprehensive HTML reports with test history
  
# Detailed step-by-step execution logs
  
# Environment and category breakdowns
```

#### To view reports
```bash
# allure serve allure-results
```

#### 🔄 CI/CD Integration
#### Jenkins Pipeline
The Jenkinsfile provides a complete CI/CD pipeline:
```bash
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

```

## Docker Support
```groovy
docker build -t bookstore-api-tests .
docker run bookstore-api-tests
```

## 🛠️ Jenkins Installation & Configuration Guide

### Prerequisites
- **Java 11 or higher** (Java 17 recommended)
- **Git** installed and configured
- **GitHub account** with repository access

### Step 1: Install Java

#### Windows
```powershell
# Download and install Java 17 from Oracle or OpenJDK
# Set JAVA_HOME environment variable
setx JAVA_HOME "C:\Program Files\Java\jdk-17"
setx PATH "%PATH%;%JAVA_HOME%\bin"
```

#### Linux/Mac
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# macOS
brew install openjdk@17
```

### Step 2: Install Jenkins

#### Download Jenkins WAR
```bash
# Create Jenkins directory
mkdir jenkins
cd jenkins

# Download Jenkins WAR file
curl -L https://get.jenkins.io/war-stable/latest/jenkins.war -o jenkins.war
```

#### Start Jenkins
```bash
# Start Jenkins on port 8080 (to avoid conflicts)
java -jar jenkins.war --httpPort=8080
```

#### Access Jenkins
1. Open browser: `http://localhost:8080`
2. Get initial admin password:
   ```bash
   # Windows
   type %USERPROFILE%\.jenkins\secrets\initialAdminPassword
   
   # Linux/Mac
   cat ~/.jenkins/secrets/initialAdminPassword
   ```

### Step 3: Jenkins Initial Setup

1. **Install Suggested Plugins**
   - Git plugin
   - Pipeline plugin
   - HTML Publisher plugin
   - Coverage plugin

2. **Create Admin User**
   - Username: `admin`
   - Password: `your-secure-password`

3. **Configure Jenkins URL**
   - URL: `http://localhost:8080`

### Step 4: Configure GitHub Integration

#### Create GitHub Personal Access Token
1. Go to GitHub → Settings → Developer settings → Personal access tokens
2. Generate new token (classic)
3. Select scopes:
   - `repo` (full control of private repositories)
   - `admin:repo_hook` (manage repository hooks)
4. Copy the token (you'll need it for Jenkins)

#### Configure Jenkins Credentials
1. Go to Jenkins → Manage Jenkins → Credentials
2. Click "System" → "Global credentials" → "Add Credentials"
3. Configure:
   - **Kind**: Username with password
   - **Scope**: Global
   - **Username**: Your GitHub username
   - **Password**: Your GitHub Personal Access Token
   - **ID**: `github-token`
   - **Description**: GitHub Personal Access Token

### Step 5: Create Jenkins Pipeline Job

#### Create New Job
1. Go to Jenkins → New Item
2. Enter job name: `API-Automation-Framework`
3. Select "Pipeline"
4. Click "OK"


### Step 6: Test the Pipeline

#### Manual Trigger
1. Go to your Jenkins job
2. Click "Build Now"
3. Monitor the build progress
4. Check build artifacts and reports

#### Automatic Trigger (Webhook)
1. Make a change to your repository
2. Push to GitHub
3. Jenkins should automatically trigger a build
4. Monitor the build progress

## 📁 Project Structure

```
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/bookstore/
│   │           ├── api/
│   │           │   ├── AuthorApiClient.java
│   │           │   ├── BookApiClient.java
│   │           │   └── BaseApiClient.java
│   │           ├── config/
│   │           │   ├── ApiConfig.java
│   │           │   └── RequestSpecFactory.java
│   │           ├── models/
│   │           │   ├── Author.java
│   │           │   └── Book.java
│   │           └── utils/
│   │               └── TestDataFactory.java
│   └── test/
│       └── java/
│           └── com/bookstore/
│               ├── author/
│               │   ├── AuthorApiTest.java
│               │   ├── AuthorEdgeCaseTest.java
│               │   └── AuthorNegativeTest.java
│               ├── book/
│               │   ├── BooksApiTest.java
│               │   ├── BookEdgeCaseTest.java
│               │   └── BookNegativeTest.java
│               └── base/
│                   └── BaseTest.java
├── target/
├── .allure/
├── allure-results/
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

## 🛠 Development

### Adding New Tests
- Create new test class in appropriate package (author or book)
- Extend BaseTest for common functionality
- Use TestDataFactory for test data generation
- Add assertions using REST Assured

### Extending the Framework
- New API Endpoints: Add methods to relevant *ApiClient class
- New Models: Create POJO in models package
- Configuration: Update ApiConfig for new environments
- Non-existent resources
- Test Data: Extend TestDataFactory for new data scenarios

# 📈 Performance Metrics
- Test Execution Time: ~2 minutes (full suite)
- Success Rate: 98%+
- Endpoint Coverage: 100% of critical paths
- Parallel Execution: Supported via Maven Surefire
