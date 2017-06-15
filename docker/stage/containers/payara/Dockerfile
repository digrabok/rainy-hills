FROM payara/server-full:latest

RUN wget -O /opt/payara41/glassfish/domains/domain1/lib/extpostgresql-42.1.1.jar https://jdbc.postgresql.org/download/postgresql-42.1.1.jar \
    && /opt/payara41/glassfish/bin/asadmin start-domain \
    && echo 'AS_ADMIN_PASSWORD=admin' > /tmp/payara_passwordfile \
    && /opt/payara41/glassfish/bin/asadmin create-jdbc-connection-pool \
        --user admin \
        --passwordfile=/tmp/payara_passwordfile \
        --datasourceclassname org.postgresql.ds.PGSimpleDataSource \
        --restype javax.sql.DataSource \
        --property user=postgres:password=mysecretpassword:databaseName=postgres:serverName=postgres:port=5432 \
        rainyHills \
    && /opt/payara41/glassfish/bin/asadmin create-jdbc-resource \
        --user admin \
        --passwordfile=/tmp/payara_passwordfile \
        --connectionpoolid rainyHills \
        jdbc/rainyHills \
    && rm /tmp/payara_passwordfile \
    && /opt/payara41/glassfish/bin/asadmin stop-domain

COPY autodeploy/*.ear /opt/payara41/deployments