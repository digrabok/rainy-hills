## Rainy Hills

### Requirements
- Application tested on Payara application server (Glassfish 4 based, could be started as stage environment - see below). 
- Application server should be EJB 3.2 compatible.
- Application server should provide *jdbc pool* with name **jdbc/rainyHills**.
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

1. (Optional) run SonarQube instance with command:   
```docker-compose -f docker/stage/docker-compose.yml up --build sonar```  
Could be used another SonarQube instance, see [SonarQube support](#sonarQubeSupport).
2. (Optional) run postgres instance with command:   
```docker-compose -f docker/stage/docker-compose.yml up --build postgres```
3. (Optional, if step 2 was implemented) copy file _rainyHillsLogic/src/test/resources/tests.properties.sample_ as **rainyHillsLogic/src/test/resources/tests.properties**. 
File properties **should be customised** for you environment.
4. run maven **install** for **rainyHillsRoot** module:   
```mvn clean install -P prod,it,sonar, autodeploy```   
   - **it** profile should be provided only if step 2 was implemented.
   - **sonar** profile should be provided only if step 1 was implemented.   
   - **autodeploy** profile should be provided only if stage environment will be used for runtime.   
   
   Minimalistic build command will be:
   ```mvn clean install -P prod```   
   It will produce application EAR in **rainyHillsApp/target**.
5. If sonar was used (step 1) your could check code quality report at [http://localhost:9000]()
6. If postgres was runned at step 2 or sonar was runned at step 1 they should be stopped:    
```docker kill $(docker ps --filter 'name=rainy_hills_*' -q)```
7. If autodeploy was used at step 4 we could start stage environment (details see at [Run stage environment](#stageEnvironment)):   
```docker-compose -f docker/stage/docker-compose.yml up --build payara```
Application will be deployed at [127.0.0.1:8080/rainyHills/]().   
Application **EAR** will be located at **rainyHillsApp/target**.

#### Database migrations for external database
Database could be created on external database:   
1. In the root of project (**rainyHillsRoot** module) copy **liquibase.properties.sample** to the **liquibase.properties** 
and customise properties in the file for your environment.   
2. For **rainyHillsPersistentEntities** module execute command:   
```mvn liquibase:update```

#### SonarQube support <a name="sonarQubeSupport"></a>
SonarQube could be started with docker container:   
```docker-compose -f docker/stage/docker-compose.yml up --build sonar```   
**sonar** profile require actual url in property _sonar.host.url_ in _rainyHillsRoot_ module.
Build with SonarQube support:  
```mvn clean install -P prod,sonar```

#### Run stage environment <a name="stageEnvironment"></a>:
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
