/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Organiser;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/*
CREATE TABLE Organiser (
    IDOrganiser INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(300),
    Surname NVARCHAR(300),
    Email NVARCHAR(20),
    Phone NVARCHAR(20)
);
*/

public class SqlOrganiserRepository implements Repository<Organiser> {

    private static final String ID_ORGANISER = "IDOrganiser";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String EMAIL = "Email";
    private static final String PHONE = "Phone";

    private static final String CREATE_ORGANISER = "{ CALL createOrganiser (?,?,?,?,?) }";
    private static final String UPDATE_ORGANISER = "{ CALL updateOrganiser (?,?,?,?,?) }";
    private static final String DELETE_ORGANISER = "{ CALL deleteOrganiser (?) }";
    private static final String SELECT_ORGANISER = "{ CALL selectOrganiser (?) }";
    private static final String SELECT_ORGANISERS = "{ CALL selectOrganisers }";

    // Organisers CRUD
    @Override
    public int create(Organiser organiser) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ORGANISER)) {

            stmt.setString(NAME, organiser.getName());
            stmt.setString(SURNAME, organiser.getSurname());
            stmt.setString(EMAIL, organiser.getEmail());
            stmt.setString(PHONE, organiser.getPhone());
            stmt.registerOutParameter(ID_ORGANISER, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_ORGANISER);
        }
    }

    @Override
    public void create(List<Organiser> organisers) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ORGANISER)) {

            for (Organiser organiser : organisers) {
                stmt.setString(NAME, organiser.getName());
                stmt.setString(SURNAME, organiser.getSurname());
                stmt.setString(EMAIL, organiser.getEmail());
                stmt.setString(PHONE, organiser.getPhone());
                stmt.registerOutParameter(ID_ORGANISER, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(int id, Organiser data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_ORGANISER)) {

            stmt.setString(NAME, data.getName());
            stmt.setString(SURNAME, data.getSurname());
            stmt.setString(EMAIL, data.getEmail());
            stmt.setString(PHONE, data.getPhone());
            stmt.setInt(ID_ORGANISER, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ORGANISER)) {

            stmt.setInt(ID_ORGANISER, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Organiser> selectOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ORGANISER)) {

            stmt.setInt(ID_ORGANISER, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Organiser(
                            rs.getInt(ID_ORGANISER),
                            rs.getString(NAME),
                            rs.getString(SURNAME),
                            rs.getString(EMAIL),
                            rs.getString(PHONE)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Organiser> selectAll() throws Exception {
        List<Organiser> organisers = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ORGANISERS); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                organisers.add(new Organiser(
                        rs.getInt(ID_ORGANISER),
                        rs.getString(NAME),
                        rs.getString(SURNAME),
                        rs.getString(EMAIL),
                        rs.getString(PHONE)));
            }
        }
        return organisers;
    }

}
