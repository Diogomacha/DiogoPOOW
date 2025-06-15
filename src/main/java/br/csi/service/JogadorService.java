package br.csi.service;

import br.csi.dao.JogadorDAO;
import br.csi.model.Jogador;

import java.util.ArrayList;

public class JogadorService {

    private JogadorDAO dao = new JogadorDAO();

    public ArrayList<Jogador> getJogadores() {
        return dao.getJogadores();
    }

    public Jogador buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void inserir(Jogador jogador) {
        dao.inserir(jogador);
    }

    public void alterar(Jogador jogador) {
        dao.alterar(jogador);
    }

    public void excluir(int id) {
        dao.excluir(id);
    }
}

