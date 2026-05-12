package com.rubrica.persistenza;

import com.rubrica.model.Persona;
import com.rubrica.model.Rubrica;
import com.rubrica.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersoneDAO {

    private final DBService dbService;


    public PersoneDAO() {
        this.dbService = new DBService();
    }

    public Rubrica fetchPersoneOfUser(Utente user) {
        String query = """
                SELECT id, nome, cognome, indirizzo, telefono, eta
                FROM persone
                WHERE utente_id = ?
                """;
        try (
                Connection connection = dbService.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setInt(1, user.getId());


            ResultSet rs = ps.executeQuery();
            Rubrica rubrica = new Rubrica();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                int eta = rs.getInt("eta");

                Persona p = new Persona(id, nome, cognome, indirizzo, telefono, eta);
                rubrica.aggiungiPersona(p);
            }
            rs.close();
            return rubrica;
        } catch (Exception e) {
            System.out.println("Eccezione nel recupero persone : " + e.getMessage());
            return new Rubrica();
        }
    }

    public boolean deletePersona(int personaId) {
        String query = """
                DELETE FROM persone
                WHERE id = ?
                """;

        try (
                Connection connection = dbService.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setInt(1, personaId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Errore eliminazione persona: " + e.getMessage());
            return false;
        }
    }

    public boolean createPersona(Persona persona, int userId) {
        String query = """
                INSERT INTO persone (nome, cognome, indirizzo, telefono, eta, utente_id)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (
                Connection connection = dbService.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setString(1, persona.getNome());
            ps.setString(2, persona.getCognome());
            ps.setString(3, persona.getIndirizzo());
            ps.setString(4, persona.getTelefono());
            ps.setInt(5, persona.getEta());
            ps.setInt(6, userId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Errore inserimento persona: " + e.getMessage());
            return false;
        }

    }


    public boolean updatePersona(Persona persona, int id) {
        String query = """
                UPDATE persone
                SET nome = ?, cognome = ?, indirizzo = ?, telefono = ?, eta = ?
                WHERE id = ?
                """;
        try (
                Connection connection = dbService.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)
        ) {
            ps.setString(1, persona.getNome());
            ps.setString(2, persona.getCognome());
            ps.setString(3, persona.getIndirizzo());
            ps.setString(4, persona.getTelefono());
            ps.setInt(5, persona.getEta());
            ps.setInt(6, id);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Errore modifica persona: " + e.getMessage());
            return false;
        }

    }
}
