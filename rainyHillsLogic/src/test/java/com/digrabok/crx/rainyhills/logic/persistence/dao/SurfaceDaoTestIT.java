package com.digrabok.crx.rainyhills.logic.persistence.dao;

import com.digrabok.crx.rainyhills.logic.bo.Surface;
import com.digrabok.crx.rainyhills.logic.persistence.producers.ConverterProducers;
import com.digrabok.tests.utils.RowMapper;
import com.digrabok.tests.utils.TestDatabase;
import liquibase.exception.LiquibaseException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SurfaceDaoTestIT {
    private SurfaceDao dao;

    private TestDatabase db = new TestDatabase();
    private EntityManager entityManager;

    @Before
    public void beforeEach() throws LiquibaseException {
        db.connect();
        entityManager = db.getEntityManager();

        dao = new SurfaceDao();
        dao.setSurfaceBoConverter(new ConverterProducers().getSurfaceBoConverter());
        dao.setEntityManager(entityManager);

        db.rebuild();

        entityManager.getTransaction().begin();
    }

    @After
    public void afterEach() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
        db.disconnect();
    }

    @Test
    public void shouldBeFetchedById() {
        long surfaceId = 2;
        String expectedName = "Surface #2";
        Surface fetchedSurface = dao.fetch(surfaceId);

        assertEquals("Surface should be fetched by id", fetchedSurface.getName(), expectedName);
    }

    @Test
    public void shouldFetchAll() {
        List<String> expectedNames = Arrays.asList("Surface #1", "Surface #2");
        List<String> fetchedNames = dao.fetchSurfaceList().stream().map(Surface::getName).collect(Collectors.toList());
        Collections.sort(fetchedNames);

        assertThat("Should fetch all", expectedNames, is(fetchedNames));
    }

    @Test
    public void shouldBeCreated() {
        String expectedName = "New surface";
        Surface surface = new Surface();
        surface.setName(expectedName);

        assertFalse(isDataExists("select count(1) from surfaces where name = ?", expectedName));
        Surface createdSurface = dao.create(surface);

        entityManager.getTransaction().commit();
        assertTrue("Surface should be created",
                isDataExists(
                        "select count(1) from surfaces where name = ?",
                        expectedName));
    }

    @Test
    public void shouldNotCreateDuplicatedName() {
        String expectedName = "Surface #1";
        Surface surface = new Surface();
        surface.setName("Surface #1");

        assertTrue(isDataExists("select count(1) from surfaces where name = ?", expectedName));
        try {
            dao.create(surface);
            entityManager.getTransaction().commit();
            fail("Should not create surface with duplicated name");
        } catch (RollbackException e) {
            assertCause(ConstraintViolationException.class, e);
        }
    }

    @Test
    public void shouldDelete() {
        long expectedId = 1;
        Surface surface = new Surface();
        surface.setId(expectedId);

        assertTrue(isDataExists("select count(1) from surfaces where id = ?", expectedId));
        dao.delete(surface);
        entityManager.getTransaction().commit();

        assertFalse(isDataExists("select count(1) from surfaces where id = ?", expectedId));
    }

    private boolean isDataExists(String countQuery, Object ... params) {
        return db.queryForObject(countQuery, params, booleanCountRowMapper);
    }

    private void assertCause(Class<? extends Throwable> expected, Throwable throwed) {
        Throwable cause = throwed.getCause();
        if (cause != null) {
            if (expected.isInstance(throwed)) {
                return;
            }
            assertCause(expected, cause);
        } else {
            fail(expected.getName() + " expected");
        }
    }

    private RowMapper<Boolean> booleanCountRowMapper = (rs, rowNumber) -> {
        int count = rs.getInt(1);
        return count > 0;
    };
}
