package br.csi.dao;

import br.csi.model.Dirigente;

import java.sql.*;
import java.util.ArrayList;

public class DirigenteDAO {


    public static ArrayList<Dirigente> getDirigentes() {
        ArrayList<Dirigente> dirigentes = new ArrayList<>();
        String sql = "SELECT * FROM dirigentes";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dirigente dirigente = new Dirigente(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                );
                dirigente.setId(rs.getInt("id"));
                dirigentes.add(dirigente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar dirigentes: " + e.getMessage());
            e.printStackTrace();
        }

        return dirigentes;
    }


    public static Dirigente inserir(Dirigente dirigente) {
        String sql = "INSERT INTO dirigentes(nome, email, senha) VALUES (?, ?, ?)";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, dirigente.getNome());
            stmt.setString(2, dirigente.getEmail());
            stmt.setString(3, dirigente.getSenha());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        dirigente.setId(rs.getInt(1));
                        System.out.println("Dirigente inserido com sucesso! ID: " + dirigente.getId());
                    }
                }
            } else {
                System.err.println("Falha ao inserir dirigente: nenhuma linha afetada.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dirigente: " + e.getMessage());
            e.printStackTrace();
        }

        return dirigente;
    }


    public static boolean excluir(int id) {
        String sql = "DELETE FROM dirigentes WHERE id = ?";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir dirigente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public static Dirigente alterar(Dirigente dirigente) {
        String sql = "UPDATE dirigentes SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, dirigente.getNome());
            stmt.setString(2, dirigente.getEmail());
            stmt.setString(3, dirigente.getSenha());
            stmt.setInt(4, dirigente.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Dirigente atualizado com sucesso!");
            } else {
                System.err.println("Nenhuma linha atualizada. Verifique o ID.");
            }

            return dirigente;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dirigente: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public static Dirigente buscarPorEmail(String email) {
        String sql = "SELECT * FROM dirigentes WHERE email = ?";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Dirigente dirigente = new Dirigente(
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha")
                    );
                    dirigente.setId(rs.getInt("id"));
                    return dirigente;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar dirigente por email: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
