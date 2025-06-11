/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author kaish
 */

@XmlAccessorType(XmlAccessType.FIELD)
public final class Event {
    
    //public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    private int id;
    
    
    @XmlElement(name = "title")
    private String title;
    
    @XmlElement(name = "link")
    private String link;
    
    @XmlElement(name = "description")
    private String description;
    
    private String picturePath;
    
    @XmlElement(name = "pubDate")
    @XmlJavaTypeAdapter(PublishedDateAdapter.class)
    private OffsetDateTime publishedDate;

    public Event() {
    }
    
    
    public Event(String title, String link, String description, OffsetDateTime publishedDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.publishedDate = publishedDate;
    }
    
    
    public Event(int id, String title, String link, String description, OffsetDateTime publishedDate) {
        this(title, link, description, publishedDate);
        this.id = id;
    }
    
    // My rss does not have pictures, but will leave these here for the funcitonality
    public Event(String title, String link, String description, String picturePath, OffsetDateTime publishedDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
    }
    
    public Event(int id, String title, String link, String description, String picturePath, OffsetDateTime publishedDate) {
        this(title, link, description, picturePath, publishedDate);
        this.id = id;
    }
    
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(OffsetDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }
    
}

