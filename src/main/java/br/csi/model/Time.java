package br.csi.model;

public class Time {
    private int id;
    private String nome;
    private String cidade;
    private int dirigenteId;

    public Time() {
    }

    public Time(int id, String nome, String cidade, int dirigenteId) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.dirigenteId = dirigenteId;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getDirigenteId() {
        return dirigenteId;
    }

    public void setDirigenteId(int dirigenteId) {
        this.dirigenteId = dirigenteId;
    }
}
