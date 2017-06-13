package com.digrabok.crx.rainyHills.logic.persistence.dao;

import com.digrabok.crx.rainyHills.logic.bo.Surface;
import com.digrabok.crx.rainyHills.logic.persistence.converters.BoConverter;
import com.digrabok.crx.rainyHills.logic.persistence.entities.SurfaceEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SurfaceDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Surface create(Surface surface) {
        SurfaceEntity surfaceEntity = surfaceBoConverter.toEntity(surface);

        entityManager.persist(surfaceEntity);

        return surfaceBoConverter.fromEntity(surfaceEntity);
    }

    public void delete(Surface surface) {
        SurfaceEntity surfaceEntity = surfaceBoConverter.toEntity(surface);
        surfaceEntity = entityManager.merge(surfaceEntity);
        entityManager.remove(surfaceEntity);
    }

    public Surface fetch(long id) {
        return surfaceBoConverter.fromEntity(entityManager.find(SurfaceEntity.class, id));
    }

    public List<Surface> fetchSurfaceList() {
        TypedQuery<SurfaceEntity> query = entityManager.createQuery("select s from SurfaceEntity s", SurfaceEntity.class);
        return surfaceBoConverter.fromEntity(query.getResultList());
    }

    private BoConverter<SurfaceEntity, Surface> surfaceBoConverter;

    @Inject
    public void setSurfaceBoConverter(BoConverter<SurfaceEntity, Surface> surfaceBoConverter) {
        this.surfaceBoConverter = surfaceBoConverter;
    }
}
