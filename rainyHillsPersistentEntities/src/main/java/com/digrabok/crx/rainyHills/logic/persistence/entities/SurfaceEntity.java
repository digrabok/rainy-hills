package com.digrabok.crx.rainyHills.logic.persistence.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "surfaces")
public class SurfaceEntity {
    @Id
    @SequenceGenerator(name = "surfacesIdGenerator", sequenceName = "surfaces_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surfacesIdGenerator")
    private long id;

    private String name;

    private long volume;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "surface_points", joinColumns = @JoinColumn(name = "surface_id"))
    @OrderColumn(name = "point_order")
    @Column(name = "point_height")
    private List<Long> profile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public List<Long> getProfile() {
        return profile;
    }

    public void setProfile(List<Long> profile) {
        this.profile = profile;
    }
}
