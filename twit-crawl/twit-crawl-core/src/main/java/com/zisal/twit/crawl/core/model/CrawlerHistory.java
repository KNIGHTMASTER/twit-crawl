package com.zisal.twit.crawl.core.model;

import javax.persistence.*;

/**
 * Created by Ladies Man on 11/21/2015.
 */
@Entity(name = "crawler_history")
@Table(name = "mst_crawler_history", schema = "master")
public class CrawlerHistory{

    @Id
    @Column(name = "crawler_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "crawler_history_code", length = 50)
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CrawlerHistory{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrawlerHistory that = (CrawlerHistory) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
