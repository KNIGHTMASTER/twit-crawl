package com.zisal.twit.crawl.core.model;

import javax.persistence.*;

/**
 * Created by fauzi on 11/2/15.
 */
@Entity(name = "friendship")
@Table(name = "mst_friendship", schema = "master")
public class Friendship {

    @Id
    @Column(name = "friendship_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "friendship_code", unique = true)
    private String code;

    @Column(name = "friendship_name")
    private String name;

    @Column(name = "friendship_image_url")
    private String imageUrl;

    @Column(name = "friendship_type")
    private int type;


    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
