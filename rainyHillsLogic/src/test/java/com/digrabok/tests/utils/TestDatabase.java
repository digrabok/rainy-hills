package com.digrabok.tests.utils;

import com.digrabok.tests.utils.exceptions.NoDataFound;
import com.digrabok.tests.utils.exceptions.ToManyRowsException;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import liquibase.resource.FileSystemResourceAccessor;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TestDatabase {
    private final String jpaPuName = "integrationTestsPU";
    private final String jdbcUrl;
    private final String jdbcDriver;
    private final String jdbcUser;
    private final String jdbcPassword;
    private final String ddlPath;
    private final String dmlPath;

    private EntityManager entityManager;

    public TestDatabase() {
        Properties prop = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("tests.properties")) {
            prop.load(in);
            jdbcUrl = prop.getProperty("javax.persistence.jdbc.url");
            jdbcDriver = prop.getProperty("javax.persistence.jdbc.driver");
            jdbcUser = prop.getProperty("javax.persistence.jdbc.user");
            jdbcPassword = prop.getProperty("javax.persistence.jdbc.password");
            ddlPath = prop.getProperty("liquibase.ddl.path");
            dmlPath = prop.getProperty("liquibase.dml.path");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void connect() {
        if (entityManager != null && entityManager.isOpen()) {
            disconnect();
        }

        Map<String, String> persistenceProperties = new HashMap<>();
        persistenceProperties.put("javax.persistence.jdbc.url", jdbcUrl);
        persistenceProperties.put("javax.persistence.jdbc.driver", jdbcDriver);
        persistenceProperties.put("javax.persistence.jdbc.user", jdbcUser);
        persistenceProperties.put("javax.persistence.jdbc.password", jdbcPassword);

        entityManager = Persistence.createEntityManagerFactory(jpaPuName, persistenceProperties)
                .createEntityManager();
    }

    public void disconnect() {
        if (entityManager != null && entityManager.isOpen()) {
            dropAll();
            entityManager.close();
        }
    }

    public void rebuild() {
        dropAll();
        applyDDL();
        applyDML();
    }

    public void dropAll() {
        entityManager.unwrap(Session.class).doWork(this::liquibaseDropAll);
    }

    public void applyDDL() {
        entityManager.unwrap(Session.class).doWork(connection -> liquibaseUpdate(connection, ddlPath));
    }

    public void applyDML() {
        entityManager.unwrap(Session.class).doWork(connection -> liquibaseUpdate(connection, dmlPath));
    }

    public <E> E queryForObject(String query, RowMapper<E> mapper) {
        return queryForObject(query, new Object[0], mapper);
    }

    public <E> E queryForObject(String query, Object[] params, RowMapper<E> mapper) {
        List<E> results = query(query, params, mapper);
        if (results.size() > 1) {
            throw new ToManyRowsException();
        } else if (results.isEmpty()) {
            throw new NoDataFound();
        }

        return results.get(0);
    }

    public <E> List<E> query(String query, Object[] params, RowMapper<E> mapper) {
        return entityManager.unwrap(Session.class).doReturningWork((Connection connection) -> {
            PreparedStatement stmt = connection.prepareStatement(query);
            setStatementParameters(stmt, params);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            int rowNumber = 1;
            List<E> result = new LinkedList<>();
            while (rs.next()) {
                result.add(mapper.mapRow(rs, rowNumber));
                rowNumber++;
            }
            return result;
        });
    }

    private void setStatementParameters(PreparedStatement stmt, Object[] params) {
        try {
            for (int idx = 1; idx <= params.length; idx++) {
                Object param = params[idx-1];

                if (param instanceof Integer) {
                    stmt.setInt(idx, (Integer) param);
                } else if (param instanceof Long) {
                    stmt.setLong(idx, (Long) param);
                } else if (param instanceof String) {
                    stmt.setString(idx, (String) param);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void liquibaseUpdate(Connection connection, String changelogPath) {
        try {
            liquibaseInstance(connection, changelogPath).update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }

    private void liquibaseDropAll(Connection connection) {
        try {
            liquibaseInstance(connection, ddlPath).dropAll();
        } catch (DatabaseException | LockException e) {
            throw new RuntimeException(e);
        }
    }

    private Liquibase liquibaseInstance(Connection connection, String changelogPath) {
        try {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            return new Liquibase(changelogPath, new FileSystemResourceAccessor(), database);
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }
}
