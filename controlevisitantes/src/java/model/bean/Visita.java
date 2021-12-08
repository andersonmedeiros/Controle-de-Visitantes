/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author anderson
 */
public class Visita {
    private final Visitante vis = new Visitante();
    private final Setor setor = new Setor();
    private final Veiculo v = new Veiculo();
    
    int cracha;
    Date dataEntrada, dataSaida;
    Time horaEntrada, horaSaida;
    String finalidade;

    public int getCracha() {
        return cracha;
    }

    public void setCracha(int cracha) {
        this.cracha = cracha;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Time horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }   
    
    public String getCpfVisitante() {
        return vis.getCpf();
    }

    public void setCpfVisitante(String cpf) {
        vis.setCpf(cpf);
    }
    public String getIdentidadeVisitante() {
        return vis.getIdentidade();
    }

    public void setIdentidadeVisitante(String identidade) {
        vis.setIdentidade(identidade);
    }

    public String getNomeVisitante() {
        return vis.getNome();
    }

    public void setNomeVisitante(String nome) {
        vis.setNome(nome);
    }

    public String getSobrenomeVisitante() {
        return vis.getSobrenome();
    }

    public void setSobrenomeVisitante(String sobrenome) {
        vis.setSobrenome(sobrenome);
    }

    public String getNomeguerraVisitante() {
        return vis.getNomeguerra();
    }

    public void setNomeguerraVisitante(String nomeguerra) {
        vis.setNomeguerra(nomeguerra);
    }

    public String getEmailVisitante() {
        return vis.getEmail();
    }

    public void setEmailVisitante(String email) {
        vis.setEmail(email);
    }

    public String getFoneVisitante() {
        return vis.getFone();
    }

    public void setFoneVisitante(String fone) {
        vis.setFone(fone);
    }
    
    public int getIdPostoGraduacaoVisitante() {
        return vis.getIdPostoGraduacao();
    }

    public void setIdPostoGraduacaoVisitante(int idPostoGraduacao) {
        vis.setIdPostoGraduacao(idPostoGraduacao);
    }

    public String getNomePostoGraduacaoVisitante() {
        return vis.getNomePostoGraduacao();
    }

    public void setNomePostoGraduacaoVisitante(String nomePostoGraduacao) {
        vis.setNomePostoGraduacao(nomePostoGraduacao);
    }

    public String getAbreviaturaPostoGraduacaoVisitante() {
        return vis.getAbreviaturaPostoGraduacao();
    }

    public void setAbreviaturaPostoGraduacaoVisitante(String abreviaturaPostoGraduacao) {
        vis.setAbreviaturaPostoGraduacao(abreviaturaPostoGraduacao);
    }
    
    public int getIdForcaPostoGraduacaoVisitante() {
        return vis.getIdForcaPostoGraduacao();
    }

    public void setIdForcaPostoGraduacaoVisitante(int idForca) {
        vis.setIdForcaPostoGraduacao(idForca);
    }

    public String getNomeForcaPostoGraduacaoVisitante() {
        return vis.getNomeForcaPostoGraduacao();
    }

    public void setNomeForcaPostoGraduacaoVisitante(String nomeForca) {
        vis.setNomeForcaPostoGraduacao(nomeForca);
    }

    public String getSiglaForcaPostoGraduacaoVisitante() {
        return vis.getSiglaForcaPostoGraduacao();
    }

    public void setSiglaForcaPostoGraduacaoVisitante(String siglaForca) {
        vis.setSiglaForcaPostoGraduacao(siglaForca);
    }
    
    public int getIdTipoForcaPostoGraduacaoVisitante() {
        return vis.getIdTipoForcaPostoGraduacao();
    }

    public void setIdTipoForcaPostoGraduacaoVisitante(int id) {
        vis.setIdTipoForcaPostoGraduacao(id);
    }

    public String getNomeTipoForcaPostoGraduacaoVisitante() {
        return vis.getNomeTipoForcaPostoGraduacao();
    }

    public void setNomeTipoForcaPostoGraduacaoVisitante(String nome) {
        vis.setNomeTipoForcaPostoGraduacao(nome);
    }
    
    public int getIdOmVisitante() {
        return vis.getIdOm();
    }

    public void setIdOmVisitante(int id) {
        vis.setIdOm(id);
    }

    public String getNomeOmVisitante() {
        return vis.getNomeOm();
    }

    public void setNomeOmVisitante(String nome) {
        vis.setNomeOm(nome);
    }

    public String getAbreviaturaOmVisitante() {
        return vis.getAbreviaturaOm();
    }

    public void setAbreviaturaOmVisitante(String abreviatura) {
        vis.setAbreviaturaOm(abreviatura);
    }
    
    public int getIdForcaOmVisitante() {
        return vis.getIdForcaOm();
    }

    public void setIdForcaOmVisitante(int idForca) {
        vis.setIdForcaOm(idForca);
    }

    public String getNomeForcaOmVisitante() {
        return vis.getNome();
    }

    public void setNomeForcaOmVisitante(String nomeForca) {
        vis.setNomeForcaOm(nomeForca);
    }

    public String getSiglaForcaOmVisitante() {
        return vis.getSiglaForcaOm();
    }

    public void setSiglaForcaOmVisitante(String siglaForca) {
        vis.setSiglaForcaOm(siglaForca);
    }
    
    public int getIdTipoForcaOmVisitante() {
        return vis.getIdTipoForcaOm();
    }

    public void setIdTipoForcaOmVisitante(int id) {
        vis.setIdTipoForcaOm(id);
    }

    public String getNomeTipoForcaOmVisitante() {
        return vis.getNomeTipoForcaOm();
    }

    public void setNomeTipoForcaOmVisitante(String nome) {
        vis.setNomeTipoForcaOm(nome);
    }
    
    public int getIdSetor() {
        return setor.getId();
    }

    public void setIdSetor(int id) {
        setor.setId(id);
    }

    public String getNomeSetor() {
        return setor.getNome();
    }

    public void setNomeSetor(String nome) {
        setor.setNome(nome);
    }

    public String getAbreviaturaSetor() {
        return setor.getAbreviatura();
    }

    public void setAbreviaturaSetor(String abreviatura) {
        setor.setAbreviatura(abreviatura);
    }
    
    public int getIdDivisaoSecaoSetor() {
        return setor.getIdDivisaoSecao();
    }

    public void setIdDivisaoSecaoSetor(int id) {
        setor.setIdDivisaoSecao(id);
    }

    public String getNomeDivisaoSecaoSetor() {
        return setor.getNomeDivisaoSecao();
    }

    public void setNomeDivisaoSecaoSetor(String nome) {
        setor.setNomeDivisaoSecao(nome);
    }

    public String getAbreviaturaDivisaoSecaoSetor() {
        return setor.getAbreviaturaDivisaoSecao();
    }

    public void setAbreviaturaDivisaoSecaoSetor(String abreviatura) {
        setor.setAbreviaturaDivisaoSecao(abreviatura);
    }
    
    public int getIdVeiculo() {
        return v.getId();
    }

    public void setIdVeiculo(int id) {
        v.setId(id);
    }

    public String getTipoVeiculo() {
        return v.getTipo();
    }

    public void setTipoVeiculo(String tipo) {
        v.setTipo(tipo);
    }

    public String getMarcaVeiculo() {
        return v.getMarca();
    }

    public void setMarcaVeiculo(String marca) {
        v.setMarca(marca);
    }

    public String getModeloVeiculo() {
        return v.getModelo();
    }

    public void setModeloVeiculo(String modelo) {
        v.setModelo(modelo);
    }

    public String getCorVeiculo() {
        return v.getCor();
    }

    public void setCorVeiculo(String cor) {
        v.setCor(cor);
    }

    public String getPlacaVeiculo() {
        return v.getPlaca();
    }

    public void setPlacaVeiculo(String placa) {
        v.setPlaca(placa);
    }

}
