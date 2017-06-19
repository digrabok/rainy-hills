## Rainy Hills

### Requirements
- Application tested on Payara application server (Glassfish 4 based, could be started as stage environment - see below). 
- Application server should be EJB 3.2 compatible.
- Application tested with Postgres 9.6. Postgres could be started with docker:   
```docker-compose -f docker/stage/docker-compose.yml up --build postgres```

### Usage
#### Application build
Maven build supported several profiles:
- **prod** - build will be prepared for production environment (by default for development environment). 
- **autodeploy** - prepare artifacts for start of stage environment.
- **it** - enable integration tests. For usage need create copy of file _rainyHillsRoot/rainyHillsLogic/src/test/resources/tests.properties.sample_ 
 as **tests.properties** with actual configuration properties for access to test database.
- **exploded** - result of build will be _exploded_ EAR with exploded _WAR_ & _EJB_ inside.
- **sonar** - will force inspection of code quality by SonarQube (see [SonarQube support](#sonarQubeSupport) below).  


1. For module **rainyHillsRoot** execute command:   
```mvn clean package -P prod```   
2. Prepare *jdbc pool* with name **jdbc/rainyHills** on application server.   
3. Deploy *EAR* from module **rainyHillsApp** to the application server.
4. In module **rainyHillsRoot** copy *liquibase.properties.sample* to *liquibase.properties*. 
Change Liquibase configuration in *liquibase.properties* for used database (by default 
parameters provided for docker container from **docker/dev/postgres**).
5. For module **rainyHillsPersistentEntities** execute command:   
```mvn liquibase:update```   
6. Application will be acceptable on the context root **/rainyHills**

#### SonarQube support <a name="sonarQubeSupport"></a>
SonarQube could be started with docker container:   
```docker-compose -f docker/stage/docker-compose.yml up --build sonar```   
**sonar** profile require actual url in property _sonar.host.url_ in _rainyHillsRoot_ module.
Build with SonarQube support:  
```mvn clean install -P prod,sonar```

#### Run stage environment:
```
mvn clean install -P prod,autodeploy
docker-compose -f docker/stage/docker-compose.yml up --build payara
```

Stop all rainyHills app containers:  
```docker kill $(docker ps --filter 'name=rainy_hills_*' -q)```  
Delete all rainyHills app containers:  
```docker rm $(docker ps -a -q --filter='name=rainy_hills_*')```  
Delete all rainyHills app images:  
```docker images -a | grep com.digrabok.rainy_hills/ | awk '{print $3}' | xargs -I {} docker rmi {}```
