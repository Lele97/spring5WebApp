package com.udemi.corso.spring.guru.spring5WebApp.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String addreessLine1;
    private String city;
    private String state;
    private String zip;

    @OneToMany
    @JoinColumn(name = "publisher_id")
    private Set<Book> books = new HashSet<>();

    public Publisher() {
    }

    public Publisher(String addreessLine1, String city, String state, String zip) {
        this.addreessLine1 = addreessLine1;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Publisher(String name, String addreessLine1, String city, String state, String zip) {
        this.name = name;
        this.addreessLine1 = addreessLine1;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddreessLine1() {
        return addreessLine1;
    }

    public void setAddreessLine1(String addreessLine1) {
        this.addreessLine1 = addreessLine1;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", addreessLine1='" + addreessLine1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        return Objects.equals(id, publisher.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
