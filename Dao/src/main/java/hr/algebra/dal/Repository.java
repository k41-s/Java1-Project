/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Event;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kaish
 */
public interface Repository {

    int createEvent(Event event) throws Exception;

    void createEvents(List<Event> events) throws Exception;

    void updateEvent(int id, Event data) throws Exception;

    void deleteEvent(int id) throws Exception;

    Optional<Event> selectEvent(int id) throws Exception;

    List<Event> selectEvents() throws Exception;

}
