/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author kaish
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {

    public Channel() {
    }
    
    @XmlElement(name = "item")
    private List<Event> events;

    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
    public List<Event> getEvents(){
        return events;
    }
}
