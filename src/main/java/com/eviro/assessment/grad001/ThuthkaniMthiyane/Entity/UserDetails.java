package com.eviro.assessment.grad001.ThuthkaniMthiyane.Entity;

import javax.persistence.*;
import java.net.URI;

@Entity
@Table(name = "Account_Profile")
public class UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "AccountHolderName")
    private String name;
    @Column(name = "AccountHolderSurname")
    private String surname;
    @Column(name = "HttpImageLink")
    private String imagePath;

    public UserDetails(){
        super();
    }

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
