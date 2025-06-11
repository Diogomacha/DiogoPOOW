package br.csi.dao;

import br.csi.model.Time;
import java.sql.*;
import java.util.ArrayList;

public class TimeDAO {


    public static ArrayList<Time> getTimes() {
        ArrayList<Time> times = new ArrayList<>();
        String sql = "SELECT * FROM times";

        try (Connection conn = ConectarBD.getConnectionPostgres();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Time time = new Time();
                time.setId(rs.getInt("id"));
                time.setNome(rs.getString("nome"));
                time.setCidade(rs.getString("cidade"));
                time.setDirigenteId(rs.getInt("dirigente_id"));

                times.add(time);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar times: " + e.getMessage());
            e.printStackTrace();
        }

        return times;
    }


    public static Time buscarPorId(int id) {
        String sql = "SELECT * FROM times WHERE id = ?";

        try (Connection conn = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Time time = new Time();
                    time.setId(rs.getInt("id"));
                    time.setNome(rs.getString("nome"));
                    time.setCidade(rs.getString("cidade"));
                    time.setDirigenteId(rs.getInt("dirigente_id"));
                    return time;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar time por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


    public static Time inserir(Time time) {
        String sql = "INSERT INTO times(nome, cidade, dirigente_id) VALUES (?, ?, ?)";

        try (Connection conn = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, time.getNome());
            stmt.setString(2, time.getCidade());
            stmt.setInt(3, time.getDirigenteId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        time.setId(rs.getInt(1));
                    }
                }
            } else {
                System.err.println("Falha ao inserir time: nenhuma linha afetada.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir time: " + e.getMessage());
            e.printStackTrace();
        }

        return time;
    }


    public static boolean alterar(Time time) {
        String sql = "UPDATE times SET nome = ?, cidade = ?, dirigente_id = ? WHERE id = ?";

        try (Connection conn = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, time.getNome());
            stmt.setString(2, time.getCidade());
            stmt.setInt(3, time.getDirigenteId());
            stmt.setInt(4, time.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar time: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public static boolean excluir(int id) {
        String sql = "DELETE FROM times WHERE id = ?";

        try (Connection conn = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir time: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static Time buscarPorDirigente(int dirigenteId) {
        String sql = "SELECT * FROM times WHERE dirigente_id = ?";

        try (Connection conn = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dirigenteId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Time time = new Time();
                    time.setId(rs.getInt("id"));
                    time.setNome(rs.getString("nome"));
                    time.setCidade(rs.getString("cidade"));
                    time.setDirigenteId(rs.getInt("dirigente_id"));
                    return time;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar time por dirigente: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


}

