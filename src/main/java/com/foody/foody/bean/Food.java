package com.foody.foody.bean;

import jakarta.persistence.*;

@Entity
@Table(name="ads")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false, length = 100, unique = false)
    private long userId;
    @Column (nullable = true, length = 100, unique = false)
    private long idd;
    @Column (nullable = true, length = 100, unique = false)
    private String description;
    @Column (nullable = true, length = 100, unique = false)
    private String title;

    public Food(long id, long idd, String description, String title) {
        this.id = id;
        this.idd = idd;
        this.description = description;
        this.title = title;
    }

    public Food(long id, long userId, long idd, String description, String title) {
        this.id = id;
        this.userId = userId;
        this.idd = idd;
        this.description = description;
        this.title = title;
    }

    public Food() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getIdd() {
        return idd;
    }

    public void setIdd(long idd) {
        this.idd = idd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
