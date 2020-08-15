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
public class PostoGraduacao {
    private int id;
    private String nome, abreviatura, circulohierarquico;
    private final Forca forca = new Forca();
    private final Categoria categoria = new Categoria();

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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getCirculohierarquico() {
        return circulohierarquico;
    }

    public void setCirculohierarquico(String circulohierarquico) {
        this.circulohierarquico = circulohierarquico;
    }
    
    public int getIdForca() {
        return forca.getId();
    }

    public void setIdForca(int id) {
        forca.setId(id);
    }

    public String getNomeForca() {
        return forca.getNome();
    }

    public void setNomeForca(String nome) {
        forca.setNome(nome);
    }

    public String getSiglaForca() {
        return forca.getSigla();
    }

    public void setSiglaForca(String sigla) {
        forca.setSigla(sigla);
    }
    
    public int getIdTipoForca() {
        return forca.getIdTipoForca();
    }

    public void setIdTipoForca(int id) {
        forca.setIdTipoForca(id);
    }

    public String getNomeTipoForca() {
        return forca.getNomeTipoForca();
    }

    public void setNomeTipoForca(String nome) {
        forca.setNomeTipoForca(nome);
    }
    
    public int getIdCategoria() {
        return categoria.getId();
    }

    public void setIdCategoria(int id) {
        categoria.setId(id);
    }

    public String getNomeCategoria() {
        return categoria.getNome();
    }

    public void setNomeCategoria(String nome) {
        categoria.setNome(nome);
    }

    public String getDescricaoCategoria() {
        return categoria.getDescricao();
    }

    public void setDescricaoCategoria(String descricao) {
        categoria.setDescricao(descricao);
    }
}
