/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author kaish
 */
public class LoginService {

    private static final String ID_USER = "IDUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String IS_ADMIN = "IsAdmin";

    private static final String SELECT_USER = "{ CALL selectUser (?) }";
    
    // Procedure created in SQL, this should work. 
    // No implementation yet of loginService
    public Optional<User> selectUser(String username) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER)) {

            stmt.setString(USERNAME, username);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            rs.getBoolean(IS_ADMIN)
                    ));
                }
            }
        }
        return Optional.empty();
    }

}
