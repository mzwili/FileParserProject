package com.eviro.assessment.grad001.Entity;

import javax.persistence.*;
import java.net.URI;

@Entity
@Table(name = "AccountProfile")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "AccountHolderName")
    private String name;
    @Column(name = "AccountHolderSurname")
    private String surname;
    @Column(name = "HttpImageLink")
    private URI imagePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public URI getImagePath() {
        return imagePath;
    }

    public void setImagePath(URI imagePath) {
        this.imagePath = imagePath;
    }

}
