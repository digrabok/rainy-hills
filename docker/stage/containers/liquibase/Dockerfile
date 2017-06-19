FROM openjdk:8-jdk

RUN mkdir /opt/liquibase \
    && cd /opt/liquibase \
    && wget https://github.com/liquibase/liquibase/releases/download/liquibase-parent-3.5.3/liquibase-3.5.3-bin.tar.gz \
    && wget https://jdbc.postgresql.org/download/postgresql-42.1.1.jar \
    && tar zxvf liquibase-3.5.3-bin.tar.gz \
    && rm liquibase-3.5.3-bin.tar.gz

COPY autodeploy/ /tmp/migrations/

CMD /opt/liquibase/liquibase --driver=org.postgresql.Driver \
    --classpath=/opt/liquibase/postgresql-42.1.1.jar \
    --changeLogFile=/tmp/migrations/master.xml \
    --url="jdbc:postgresql://postgres:5432/postgres" \
    --username=postgres \
    --password=mysecretpassword \
    migrate
