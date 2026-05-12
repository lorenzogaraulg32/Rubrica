package com.rubrica.persistenza;

import com.rubrica.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersDAO {

    private final DBService dbService;

    public UsersDAO() {
        this.dbService = new DBService();
    }

    public Utente fetchUsernameAndPassword(String username, String password) {
        String query = """
                SELECT id, username
                FROM utenti
                WHERE username = ? and password = ?
                """;
        try (
                Connection connection = dbService.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                rs.close();
                return new Utente(id, name);
            }

        } catch (Exception e) {
            System.out.println("Eccezione nel recupero utenti : " + e.getMessage());
            return null;
        }

        return null;
    }

}
