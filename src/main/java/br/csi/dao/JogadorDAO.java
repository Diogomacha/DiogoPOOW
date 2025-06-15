package br.csi.dao;

import br.csi.model.Jogador;

import java.sql.*;
import java.util.ArrayList;

public class JogadorDAO {

    public static ArrayList<Jogador> getJogadores() {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT * FROM jogadores";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Jogador jogador = new Jogador(
                        rs.getString("nome"),
                        rs.getString("posicao"),
                        rs.getInt("idade"),
                        rs.getInt("numero"),
                        rs.getInt("time_id")
                );
                jogador.setId(rs.getInt("id"));
                jogadores.add(jogador);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar jogadores: " + e.getMessage());
            e.printStackTrace();
        }

        return jogadores;
    }

    public static Jogador inserir(Jogador jogador) {
        String sql = "INSERT INTO jogadores (nome, posicao, idade, numero, time_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getPosicao());
            stmt.setInt(3, jogador.getIdade());
            stmt.setInt(4, jogador.getNumero());
            stmt.setInt(5, jogador.getTimeId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        jogador.setId(rs.getInt(1));
                        System.out.println("Jogador inserido com sucesso! ID: " + jogador.getId());
                    }
                }
            } else {
                System.err.println("Falha ao inserir jogador: nenhuma linha afetada.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir jogador: " + e.getMessage());
            e.printStackTrace();
        }

        return jogador;
    }

    public static boolean excluir(int id) {
        String sql = "DELETE FROM jogadores WHERE id = ?";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir jogador: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static Jogador alterar(Jogador jogador) {
        String sql = "UPDATE jogadores SET nome = ?, posicao = ?, idade = ?, numero = ?, time_id = ? WHERE id = ?";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getPosicao());
            stmt.setInt(3, jogador.getIdade());
            stmt.setInt(4, jogador.getNumero());
            stmt.setInt(5, jogador.getTimeId());
            stmt.setInt(6, jogador.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Jogador atualizado com sucesso!");
            } else {
                System.err.println("Nenhuma linha atualizada. Verifique o ID.");
            }

            return jogador;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar jogador: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Jogador buscarPorId(int id) {
        String sql = "SELECT * FROM jogadores WHERE id = ?";

        try (Connection connection = ConectarBD.getConnectionPostgres();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Jogador jogador = new Jogador(
                            rs.getString("nome"),
                            rs.getString("posicao"),
                            rs.getInt("idade"),
                            rs.getInt("numero"),
                            rs.getInt("time_id")
                    );
                    jogador.setId(rs.getInt("id"));
                    return jogador;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar jogador por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}

