## Rainy Hills

#### Requirements
- Application tested on Payara application server (Glassfish 4 based). 
- Application server should be EJB 3.2 compatible.

#### Usage
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