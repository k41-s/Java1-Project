/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Event;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlEventRepository implements Repository<Event> {

    private static final String ID_EVENT = "IDEvent";
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String VENUE_ID = "VenueID";
    private static final String ORGANISER_ID = "OrganiserID";

    private static final String CREATE_EVENT = "{ CALL createEvent (?,?,?,?,?,?) }";
    private static final String CREATE_EVENT_FK = "{ CALL createEventWithFK (?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_EVENT = "{ CALL updateEvent (?,?,?,?,?,?) }";
    private static final String DELETE_EVENT = "{ CALL deleteEvent (?) }";
    private static final String SELECT_EVENT = "{ CALL selectEvent (?) }";
    private static final String SELECT_EVENTS = "{ CALL selectEvents }";

    // Events CRUD
    @Override
    public int create(Event event) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_EVENT)) {

            stmt.setString(TITLE, event.getTitle());
            stmt.setString(LINK, event.getLink());
            stmt.setString(DESCRIPTION, event.getDescription());
            stmt.setString(PICTURE_PATH, event.getPicturePath());
            stmt.setString(PUBLISHED_DATE, event.getPublishedDate().format(Event.DATE_FORMATTER));
            stmt.registerOutParameter(ID_EVENT, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_EVENT);
        }
    }

    public int createWithFK(Event event) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_EVENT_FK)) {

            stmt.setString(TITLE, event.getTitle());
            stmt.setString(LINK, event.getLink());
            stmt.setString(DESCRIPTION, event.getDescription());
            stmt.setString(PICTURE_PATH, event.getPicturePath());
            stmt.setString(PUBLISHED_DATE, event.getPublishedDate().format(Event.DATE_FORMATTER));
            stmt.setInt(VENUE_ID, event.getVenue().getId());
            stmt.setInt(ORGANISER_ID, event.getOrganiser().getId());
            stmt.registerOutParameter(ID_EVENT, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_EVENT);
        }
    }

    @Override
    public void create(List<Event> events) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_EVENT)) {

            for (Event event : events) {
                stmt.setString(TITLE, event.getTitle());
                stmt.setString(LINK, event.getLink());
                stmt.setString(DESCRIPTION, event.getDescription());
                stmt.setString(PICTURE_PATH, event.getPicturePath());
                stmt.setString(PUBLISHED_DATE, event.getPublishedDate().format(Event.DATE_FORMATTER));
                stmt.registerOutParameter(ID_EVENT, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(int id, Event data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_EVENT)) {

            stmt.setString(TITLE, data.getTitle());
            stmt.setString(LINK, data.getLink());
            stmt.setString(DESCRIPTION, data.getDescription());
            stmt.setString(PICTURE_PATH, data.getPicturePath());
            stmt.setString(PUBLISHED_DATE, data.getPublishedDate().format(Event.DATE_FORMATTER));
            stmt.setInt(ID_EVENT, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_EVENT)) {

            stmt.setInt(ID_EVENT, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Event> selectOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_EVENT)) {

            stmt.setInt(ID_EVENT, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Event e = new Event(
                            rs.getInt(ID_EVENT),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            OffsetDateTime.parse(rs.getString(PUBLISHED_DATE), Event.DATE_FORMATTER));
                    e.setVenue(rs.getInt(VENUE_ID));
                    e.setOrganiser(rs.getInt(ORGANISER_ID));

                    return Optional.of(e);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Event> selectAll() throws Exception {
        List<Event> events = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_EVENTS); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Event e = new Event(
                        rs.getInt(ID_EVENT),
                        rs.getString(TITLE),
                        rs.getString(LINK),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        OffsetDateTime.parse(rs.getString(PUBLISHED_DATE), Event.DATE_FORMATTER));
                e.setVenue(rs.getInt(VENUE_ID));
                e.setOrganiser(rs.getInt(ORGANISER_ID));

                events.add(e);

            }
        }
        return events;
    }

}
