package com.foody.foody.bean;

import jakarta.persistence.*;

@Entity
@Table(name="ads")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;

    @Column (nullable = true, length = 100, unique = false)
    private long idd;
    @Column (nullable = true, length = 100, unique = false)
    private String description;
    @Column (nullable = true, length = 100, unique = false)
    private String title;

    public Food(long id, User user, long idd, String description, String title) {
        this.id = id;
        this.user = user;
        this.idd = idd;
        this.description = description;
        this.title = title;
    }

    public Food(User user, long idd, String description, String title) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
