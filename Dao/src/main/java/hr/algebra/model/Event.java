/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author kaish
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class Event implements Comparable<Event> {

    // Might be bad separation of concerns, but I need to supply 
    // functions for  Organiser and venue by id
    @XmlTransient
    private final Repository<Organiser> orgRepo = RepositoryFactory.getOrganiserRepository();
    @XmlTransient
    private final Repository<Venue> venueRepo = RepositoryFactory.getVenueRepository();

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

    private Venue venue;

    @XmlElement(name = "organisers")
    private Organiser organiser;

    public Event() {
    }

    public Event(String title, String description, OffsetDateTime publishedDate, Organiser organiser) {
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.organiser = organiser;
    }

    public Event(String title, String description, OffsetDateTime publishedDate, Venue venue) {
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.venue = venue;
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

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setVenue(int idVenue) throws Exception {
        Optional<Venue> opt = venueRepo.selectOne(idVenue);
        if (opt.isPresent()) {
            this.venue = opt.get();
        }
    }

    public Organiser getOrganiser() {
        return organiser;
    }

    public void setOrganiser(Organiser organiser) {
        this.organiser = organiser;
    }

    public void setOrganiser(int idOrganiser) throws Exception {
        Optional<Organiser> opt = orgRepo.selectOne(idOrganiser);
        if (opt.isPresent()) {
            this.organiser = opt.get();
        }
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        return this.id == other.id;
    }

    @Override
    public int compareTo(Event o) {
        return Integer.compare(this.id, o.getId());
    }

}
