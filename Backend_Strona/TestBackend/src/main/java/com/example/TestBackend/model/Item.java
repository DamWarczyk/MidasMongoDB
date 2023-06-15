package com.example.TestBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Item implements Serializable{

    @Id
    private String id;
    private String name;
    private Long cena;
    private String opis;
    private String imageUrl;

    public Item(String id, String name, Long cena, String opis, String imageUrl) {
        this.id = id;
        this.name = name;
        this.cena = cena;
        this.opis = opis;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCena() {
        return cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCena(Long cena) {
        this.cena = cena;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString(){
        return "Item{" +
                "id" + id +
                "name" + name + '\'' +
                "cena" + cena + '\'' +
                "opis" + opis + '\'' +
                "image:" + imageUrl +
                '}';
    }
}
