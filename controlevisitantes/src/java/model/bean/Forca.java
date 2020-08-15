/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author anderson
 */
public class Forca {
    int id;
    String nome, sigla;
    private final TipoForca tipoforca = new TipoForca();

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public int getIdTipoForca() {
        return tipoforca.getId();
    }

    public void setIdTipoForca(int id) {
        tipoforca.setId(id);
    }

    public String getNomeTipoForca() {
        return tipoforca.getNome();
    }

    public void setNomeTipoForca(String nome) {
        tipoforca.setNome(nome);
    }
}
