package br.csi.model;

public class Jogador {
    private int id;
    private String nome;
    private String posicao;
    private int idade;
    private int numero;
    private int timeId;

    public Jogador() {}

    public Jogador(String nome, String posicao, int idade, int numero, int timeId) {
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.numero = numero;
        this.timeId = timeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", posicao='" + posicao + '\'' +
                ", idade=" + idade +
                ", numero=" + numero +
                ", timeId=" + timeId +
                '}';
    }
}

