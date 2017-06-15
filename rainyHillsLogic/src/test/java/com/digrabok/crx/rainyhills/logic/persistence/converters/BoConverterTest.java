package com.digrabok.crx.rainyhills.logic.persistence.converters;

import com.digrabok.crx.rainyhills.logic.bo.Surface;
import com.digrabok.crx.rainyhills.logic.persistence.entities.SurfaceEntity;
import com.digrabok.crx.rainyhills.logic.persistence.producers.ConverterProducers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.digrabok.tests.utils.Asserts.reflectEquals;
import static com.digrabok.tests.utils.Asserts.reflectEqualsList;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BoConverterTest {
    private final ConverterProducers producer = new ConverterProducers();

    private BoConverter<SurfaceEntity, Surface> converter;

    @Before
    public void beforeEach() {
        converter = producer.getSurfaceBoConverter();
    }

    @Test
    public void shouldConvertEntityToBusinessObject() {
        SurfaceEntity srcEntity = buildEntity().get(0);

        Surface destBo = converter.fromEntity(srcEntity);

        reflectEquals("Should convert entity to business object", srcEntity, destBo);
    }

    @Test
    public void shouldReturnNullIfSourceEntityIsNull() {
        Surface destBo = converter.fromEntity((SurfaceEntity) null);
        assertNull("Should return null if source entity is null", destBo);
    }

    @Test
    public void shouldConvertEntityListToBusinessObjectList() {
        List<SurfaceEntity> srcEntityList = buildEntity();

        List<Surface> destBoList = converter.fromEntity(srcEntityList);

        reflectEqualsList("Should convert entity to business object", srcEntityList, destBoList);
    }

    @Test
    public void shouldReturnEmptyBusinessObjectListIfSourceListIsNull() {
        List<Surface> destBoList = converter.fromEntity((List<SurfaceEntity>) null);

        assertNotNull("Should not be null", destBoList);
        assertTrue("Should be empty", destBoList.isEmpty());
    }

    @Test
    public void shouldConvertBusinessObjectToEntity() {
        Surface srcBo = buildBo().get(0);

        SurfaceEntity destEntity = converter.toEntity(srcBo);

        reflectEquals("Should convert business object to entity", srcBo, destEntity);
    }

    @Test
    public void shouldReturnNullIfSourceBusinessObjectIsNull() {
        SurfaceEntity destEntityList = converter.toEntity((Surface) null);
        assertNull("Should return null if source business object is null", destEntityList);
    }

    @Test
    public void shouldConvertBusinessObjectListToEntityList() {
        List<Surface> srcBoList = buildBo();

        List<SurfaceEntity> destEntityList = converter.toEntity(srcBoList);

        reflectEqualsList("Should convert business object list to entity list", srcBoList, destEntityList);
    }

    @Test
    public void shouldReturnEmptyEntityListIfSourceListIsNull() {
        List<SurfaceEntity> destEntityList = converter.toEntity((List<Surface>) null);

        assertNotNull("Should not be null", destEntityList);
        assertTrue("Should be empty", destEntityList.isEmpty());
    }

    private List<SurfaceEntity> buildEntity() {
        List<SurfaceEntity> entities = new LinkedList<>();

        SurfaceEntity entity = new SurfaceEntity();
        entities.add(entity);
        entity.setId(5);
        entity.setName("Expected Name");
        entity.setVolume(25);
        entity.setProfile(Arrays.asList(4L, 5L, 7L, 10L));

        entity = new SurfaceEntity();
        entities.add(entity);
        entity.setId(8);
        entity.setName("Expected Name #2");
        entity.setVolume(12);
        entity.setProfile(null);

        return entities;
    }

    private List<Surface> buildBo() {
        List<Surface> boList = new LinkedList<>();

        Surface entity = new Surface();
        boList.add(entity);
        entity.setId(5);
        entity.setName("Expected Name");
        entity.setVolume(25);
        entity.setProfile(Arrays.asList(4L, 5L, 7L, 10L));

        entity = new Surface();
        boList.add(entity);
        entity.setId(8);
        entity.setName("Expected Name #2");
        entity.setVolume(12);
        entity.setProfile(null);

        return boList;
    }
}
