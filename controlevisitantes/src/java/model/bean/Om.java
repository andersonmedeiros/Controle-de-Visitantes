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
public class Om {
    private int id;
    private String nome, abreviatura, numendereco;
    private final Forca forca = new Forca();
    private final Estado est = new Estado();
    private final Endereco end = new Endereco();

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

    public String getNumendereco() {
        return numendereco;
    }

    public void setNumendereco(String numendereco) {
        this.numendereco = numendereco;
    }
    
    public int getIdForca() {
        return forca.getId();
    }

    public void setIdForca(int idForca) {
        forca.setId(idForca);
    }

    public String getNomeForca() {
        return forca.getNome();
    }

    public void setNomeForca(String nomeForca) {
        forca.setNome(nomeForca);
    }

    public String getSiglaForca() {
        return forca.getSigla();
    }

    public void setSiglaForca(String siglaForca) {
        forca.setSigla(siglaForca);
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
    
    public int getIdEstado() {
        return est.getId();
    }

    public void setIdEstado(int id) {
        est.setId(id);
    }

    public String getNomeEstado() {
        return est.getNome();
    }

    public void setNomeEstado(String nome) {
        est.setNome(nome);
    }

    public String getSiglaEstado() {
        return est.getSigla();
    }

    public void setSiglaEstado(String sigla) {
        est.setSigla(sigla);
    }
    
    public int getIdRegiaoEstado() {
        return est.getIdRegiao();
    }

    public void setIdRegiaoEstado(int id) {
        est.setIdRegiao(id);
    }

    public String getNomeRegiaoEstado() {
        return est.getNomeRegiao();
    }

    public void setNomeRegiaoEstado(String nome) {
        est.setNomeRegiao(nome);
    }
    
    public int getIdEndereco() {
        return end.getId();
    }

    public void setIdEndereco(int idEndereco) {
        end.setId(idEndereco);
    }

    public String getCepEndereco() {
        return end.getCep();
    }

    public void setCepEndereco(String cepEndereco) {
        end.setCep(cepEndereco);
    }

    public String getDescricaoEndereco() {
        return end.getDescricao();
    }

    public void setDescricaoEndereco(String descricaoEndereco) {
        end.setDescricao(descricaoEndereco);
    }

    public String getComplementoEndereco() {
        return end.getComplemento();
    }

    public void setComplementoEndereco(String complementoEndereco) {
        end.setComplemento(complementoEndereco);
    }

    public String getPontoreferenciaEndereco() {
        return end.getPontoreferencia();
    }

    public void setPontoreferenciaEndereco(String pontoreferenciaEndereco) {
        end.setPontoreferencia(pontoreferenciaEndereco);
    }

    public String getBairroEndereco() {
        return end.getBairro();
    }

    public void setBairroEndereco(String bairro) {
        end.setBairro(bairro);
    }
    
    public int getIdCidadeEndereco() {
        return end.getIdCidade();
    }

    public void setIdCidadeEndereco(int idCidade) {
        end.setIdCidade(idCidade);
    }

    public String getNomeCidadeEndereco() {
        return end.getNomeCidade();
    }

    public void setNomeCidadeEndereco(String nomeCidade) {
        end.setNomeCidade(nomeCidade);
    }
    
    public int getIdEstadoCidadeEndereco() {
        return end.getIdEstadoCidade();
    }

    public void setIdEstadoCidadeEndereco(int idEstado) {
        end.setIdEstadoCidade(idEstado);
    }

    public String getNomeEstadoCidadeEndereco() {
        return end.getNomeEstadoCidade();
    }

    public void setNomeEstadoCidadeEndereco(String nomeEstado) {
        end.setNomeEstadoCidade(nomeEstado);
    }

    public String getSiglaEstadoCidadeEndereco() {
        return end.getSiglaEstadoCidade();
    }

    public void setSiglaEstadoCidadeEndereco(String siglaEstado) {
        end.setSiglaEstadoCidade(siglaEstado);
    }
    
    public int getIdRegiaoEstadoCidadeEndereco() {
        return end.getIdRegiaoEstadoCidade();
    }

    public void setIdRegiaoEstadoCidadeEndereco(int id) {
        end.setIdRegiaoEstadoCidade(id);
    }

    public String getNomeRegiaoEstadoCidadeEndereco() {
        return end.getNomeRegiaoEstadoCidade();
    }

    public void setNomeRegiaoEstadoCidadeEndereco(String nome) {
        end.setNomeRegiaoEstadoCidade(nome);
    }
}
