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
public class Visitante {
    private String identidade, nome, sobrenome, nomeguerra, email, fone;
    private int tipo;
    private final PostoGraduacao pg = new PostoGraduacao();
    private final Om om = new Om();

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeguerra() {
        return nomeguerra;
    }

    public void setNomeguerra(String nomeguerra) {
        this.nomeguerra = nomeguerra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    
    public int getIdPostoGraduacao() {
        return pg.getId();
    }

    public void setIdPostoGraduacao(int idPostoGraduacao) {
        pg.setId(idPostoGraduacao);
    }

    public String getNomePostoGraduacao() {
        return pg.getNome();
    }

    public void setNomePostoGraduacao(String nomePostoGraduacao) {
        pg.setNome(nomePostoGraduacao);
    }

    public String getAbreviaturaPostoGraduacao() {
        return pg.getAbreviatura();
    }

    public void setAbreviaturaPostoGraduacao(String abreviaturaPostoGraduacao) {
        pg.setAbreviatura(abreviaturaPostoGraduacao);
    }
    
    public int getIdForcaPostoGraduacao() {
        return pg.getIdForca();
    }

    public void setIdForcaPostoGraduacao(int idForca) {
        pg.setIdForca(idForca);
    }

    public String getNomeForcaPostoGraduacao() {
        return pg.getNomeForca();
    }

    public void setNomeForcaPostoGraduacao(String nomeForca) {
        pg.setNomeForca(nomeForca);
    }

    public String getSiglaForcaPostoGraduacao() {
        return pg.getSiglaForca();
    }

    public void setSiglaForcaPostoGraduacao(String siglaForca) {
        pg.setSiglaForca(siglaForca);
    }
    
    public int getIdTipoForcaPostoGraduacao() {
        return pg.getIdTipoForca();
    }

    public void setIdTipoForcaPostoGraduacao(int id) {
        pg.setIdTipoForca(id);
    }

    public String getNomeTipoForcaPostoGraduacao() {
        return pg.getNomeTipoForca();
    }

    public void setNomeTipoForcaPostoGraduacao(String nome) {
        pg.setNomeTipoForca(nome);
    }
    
    public int getIdOm() {
        return om.getId();
    }

    public void setIdOm(int id) {
        om.setId(id);
    }

    public String getNomeOm() {
        return om.getNome();
    }

    public void setNomeOm(String nome) {
        om.setNome(nome);
    }

    public String getAbreviaturaOm() {
        return om.getAbreviatura();
    }

    public void setAbreviaturaOm(String abreviatura) {
        om.setAbreviatura(abreviatura);
    }
    
    public int getIdForcaOm() {
        return om.getIdForca();
    }

    public void setIdForcaOm(int idForca) {
        om.setIdForca(idForca);
    }

    public String getNomeForcaOm() {
        return om.getNome();
    }

    public void setNomeForcaOm(String nomeForca) {
        om.setNomeForca(nomeForca);
    }

    public String getSiglaForcaOm() {
        return om.getSiglaForca();
    }

    public void setSiglaForcaOm(String siglaForca) {
        om.setSiglaForca(siglaForca);
    }
    
    public int getIdTipoForcaOm() {
        return om.getIdTipoForca();
    }

    public void setIdTipoForcaOm(int id) {
        om.setIdTipoForca(id);
    }

    public String getNomeTipoForcaOm() {
        return om.getNomeTipoForca();
    }

    public void setNomeTipoForcaOm(String nome) {
        om.setNomeTipoForca(nome);
    }

    public void setIdPostoGraduacao(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
