package com.zisal.twit.crawl.core.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by fauzi on 11/2/15.
 */
@Entity(name = "friendship")
@Table(name = "mst_friendship", schema = "master", uniqueConstraints = {
        @UniqueConstraint(columnNames = "friendship_code")
})
public class Friendship {

    @Id
    @Column(name = "friendship_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "friendship_code", unique = true, length = 50)
    private String code;

    @Column(name = "friendship_name")
    private String name;

    @Column(name = "friendship_image_url")
    private String imageUrl;

    @Column(name = "friendship_type")
    private int type;

    @Column(name = "friendship_level")
    private int level;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="mst_friendship_link", schema = "master",
            joinColumns = {
                    @JoinColumn(name="friendship_link_me", referencedColumnName = "friendship_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="friendship_link_friend", referencedColumnName = "friendship_id")
            }
    )
    private List<Friendship> friendships = new ArrayList<>();

    @ManyToMany(mappedBy="friendships")
    private Set<Friendship> friendOfFriend = new HashSet<>();

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(List<Friendship> friendships) {
        this.friendships = friendships;
    }

    public Set<Friendship> getFriendOfFriend() {
        return friendOfFriend;
    }

    public void setFriendOfFriend(Set<Friendship> friendOfFriend) {
        this.friendOfFriend = friendOfFriend;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type=" + type +
                ", level=" + level +
                '}';
    }
}
