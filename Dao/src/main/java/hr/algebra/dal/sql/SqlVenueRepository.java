/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Venue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/*
CREATE TABLE Venue (
    IDVenue INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(300),
    Address NVARCHAR(300),
    PostCode NVARCHAR(20),
    Capacity INT
);
*/

public class SqlVenueRepository implements Repository<Venue> {

    private static final String ID_VENUE = "IDVenue";
    private static final String NAME = "Name";
    private static final String ADDRESS = "Address";
    private static final String POST_CODE = "PostCode";
    private static final String CAPACITY = "Capacity";

    private static final String CREATE_VENUE = "{ CALL createVenue (?,?,?,?,?) }";
    private static final String UPDATE_VENUE = "{ CALL updateVenue (?,?,?,?,?) }";
    private static final String DELETE_VENUE = "{ CALL deleteVenue (?) }";
    private static final String SELECT_VENUE = "{ CALL selectVenue (?) }";
    private static final String SELECT_VENUES = "{ CALL selectVenues }";

    // Venues CRUD
    @Override
    public int create(Venue venue) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_VENUE)) {

            stmt.setString(NAME, venue.getName());
            stmt.setString(ADDRESS, venue.getAddress());
            stmt.setString(POST_CODE, venue.getPostCode());
            stmt.setInt(CAPACITY, venue.getCapacity());
            stmt.registerOutParameter(ID_VENUE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_VENUE);
        }
    }

    @Override
    public void create(List<Venue> venues) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_VENUE)) {

            for (Venue venue : venues) {
                stmt.setString(NAME, venue.getName());
                stmt.setString(ADDRESS, venue.getAddress());
                stmt.setString(POST_CODE, venue.getPostCode());
                stmt.setInt(CAPACITY, venue.getCapacity());
                stmt.registerOutParameter(ID_VENUE, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(int id, Venue data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_VENUE)) {

            stmt.setString(NAME, data.getName());
            stmt.setString(ADDRESS, data.getAddress());
            stmt.setString(POST_CODE, data.getPostCode());
            stmt.setInt(CAPACITY, data.getCapacity());
            stmt.setInt(ID_VENUE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_VENUE)) {

            stmt.setInt(ID_VENUE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Venue> selectOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_VENUE)) {

            stmt.setInt(ID_VENUE, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Venue(
                            rs.getInt(ID_VENUE),
                            rs.getString(NAME),
                            rs.getString(ADDRESS),
                            rs.getString(POST_CODE),
                            rs.getInt(CAPACITY)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Venue> selectAll() throws Exception {
        List<Venue> venues = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_VENUES); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                venues.add(new Venue(
                        rs.getInt(ID_VENUE),
                        rs.getString(NAME),
                        rs.getString(ADDRESS),
                        rs.getString(POST_CODE),
                        rs.getInt(CAPACITY)));
            }
        }
        return venues;
    }

}
