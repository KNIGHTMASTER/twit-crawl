package com.zisal.twit.crawl.core.dto;

import java.io.Serializable;

/**
 * Created by fauzi on 11/2/15.
 */
public class DTOFriendship implements Serializable{

    private long id;

    private String name;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
