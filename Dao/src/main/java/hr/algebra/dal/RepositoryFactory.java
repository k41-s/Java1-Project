/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaish
 */
public final class RepositoryFactory {

    // Load all into memory and have one method for each repo type
    
    private static Repository eventRepository;
    private static Repository venueRepository;
    private static Repository organiserRepository;

    static {
        try {
            eventRepository = (Repository) Class
                    .forName("hr.algebra.dal.sql.SqlEventRepository")
                    .getDeclaredConstructor()
                    .newInstance();
            
            venueRepository = (Repository) Class
                    .forName("hr.algebra.dal.sql.SqlVenueRepository")
                    .getDeclaredConstructor()
                    .newInstance();
            
            organiserRepository = (Repository) Class
                    .forName("hr.algebra.dal.sql.SqlOrganiserRepository")
                    .getDeclaredConstructor()
                    .newInstance();
            
        } catch (Exception ex) {
            Logger.getLogger(RepositoryFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Repository getEventRepository() {
        return eventRepository;
    }

    public static Repository getVenueRepository() {
        return venueRepository;
    }

    public static Repository getOrganiserRepository() {
        return organiserRepository;
    }

}
