# How to Clone a new prj
1. Clone a prj folder
2. Modify settings.gradle
   1. rootProject.name
3. Modify build.gradle
   1. group
4. Open prj via intelliJ
5. Refactor the package
6. (optional) Modify application.properties
   1. `spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/xxxx`
   2. `server.port: 8081`
7. (optional) Create database
   1. Character Set : utf8mb4
   2. Collection : utf8mb4_unicode_520_ci
8. (optional) Grant user
   1. `CREATE USER 'username'@'%' IDENTIFIED BY 'password';`
   2. `GRANT ALL PRIVILEGES ON database_name.* TO 'username'@'%';`
9. Set to git repo
   1. `rm -fr .git`
   2. `git init --initial-branch=main`
   3. `git remote add origin git@git-url:port/path/to/repo.git`
   4. `git add .gitignore`
   5. `git add .`
   6. `git commit -m "Initial commit"`
   7. `git push -u origin main`

# sample-service-db
1. Springboot
2. Mysql
3. OpenAPI(Swagger3)
   1. http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
   2. json data in /v3/api-docs
4. Elasticsearch
5. Kibana
