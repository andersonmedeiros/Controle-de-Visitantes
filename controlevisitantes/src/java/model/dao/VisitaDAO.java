/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Setor;
import model.bean.Veiculo;
import model.bean.Visita;
import model.bean.Visitante;

/**
 *
 * @author anderson
 */
public class VisitaDAO {
    //Tabela
    String tabela = "Visita";
    
    //Atributos
    String idtVisitante = "idtVisitante";
    String idSetor = "idSetor";
    String cracha = "cracha";
    String entrada = "entrada";
    String saida = "saida";
    String idVeiculo = "idVeiculo";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + idtVisitante + "," + idSetor + "," + cracha +  "," + entrada + ","  + saida +  "," + idVeiculo + ")" +
                                  " VALUES(?,?,?,?,?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + cracha + "=?, " + saida + "=?, " + idVeiculo + "=? " +
                                  "WHERE " + idtVisitante + "=? AND " + idSetor + "=? AND " + entrada + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + idtVisitante + "=? AND " + idSetor + "=? AND " + entrada + "=?;";
    
    //Consultas SQL
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    //Insert SQL
    public void insert(Visita visita) {
        if (visita != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setString(1, visita.getIdentidadeVisitante());
                pstm.setInt(2, visita.getIdSetor());
                pstm.setInt(3, visita.getCracha());
                pstm.setDate(4, visita.getEntrada());
                pstm.setDate(5, visita.getSaida());
                pstm.setInt(6, visita.getIdVeiculo());
                
                pstm.execute();
                
                ConnectionFactory.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());  
            }
        } else {
            throw new RuntimeException();
        }
    }
    
    //Update SQL
    public void update(Visita visita) {
        if (visita != null) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATE); 
                
                pstm.setInt(1, visita.getCracha());
                pstm.setDate(2, visita.getSaida());
                pstm.setInt(3, visita.getIdVeiculo());
                pstm.setString(4, visita.getIdentidadeVisitante());
                pstm.setInt(5, visita.getIdSetor());
                pstm.setDate(6, visita.getEntrada());
                
                pstm.execute();
                ConnectionFactory.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());  
            }
        } else {            
            throw new RuntimeException();
        }
    }
    
    //Delete SQL
    public void delete(String idtVisitante, int idSetor, Date entrada) {
        if (!idtVisitante.equals("") && idSetor != 0 && entrada != null){
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(DELETE);
                pstm.setString(1, idtVisitante);
                pstm.setInt(2, idSetor);
                pstm.setDate(3, entrada);
            
                pstm.execute();
                ConnectionFactory.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());  
            }
        } else {            
            throw new RuntimeException();
        }
    }
    
    private final String GETVISITABYIDT = "SELECT * " +
                                            "FROM Visita " + 
                                            "WHERE idtVisitante = ?";
       
    public Visita getVisitaByIdtVisitante(String idtVisitante){
        Visita visita = new Visita();
        VisitanteDAO visDAO = new VisitanteDAO();
        SetorDAO setorDAO = new SetorDAO();
        VeiculoDAO vDAO = new VeiculoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITABYIDT);
            pstm.setString(1, idtVisitante);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                visita.setCracha(rs.getInt("cracha"));
                visita.setEntrada(rs.getDate("entrada"));
                visita.setSaida(rs.getDate("saida"));                            
                
                Visitante vis = visDAO.getVisitanteById(rs.getString("idtVisitante"));
                visita.setIdentidadeVisitante(vis.getNome());
                visita.setNomeVisitante(vis.getNome());
                visita.setSobrenomeVisitante(vis.getSobrenome());
                visita.setNomeguerraVisitante(vis.getNomeguerra());
                visita.setEmailVisitante(vis.getEmail());
                visita.setFoneVisitante(vis.getFone());
                
                visita.setIdPostoGraduacaoVisitante(vis.getIdPostoGraduacao());
                visita.setNomePostoGraduacaoVisitante(vis.getNomePostoGraduacao());
                visita.setAbreviaturaPostoGraduacaoVisitante(vis.getAbreviaturaPostoGraduacao());
                visita.setIdForcaPostoGraduacaoVisitante(vis.getIdForcaPostoGraduacao());
                visita.setNomeForcaPostoGraduacaoVisitante(vis.getNomeForcaPostoGraduacao());
                visita.setSiglaForcaPostoGraduacaoVisitante(vis.getSiglaForcaPostoGraduacao());
                visita.setIdTipoForcaPostoGraduacaoVisitante(vis.getIdTipoForcaPostoGraduacao());
                visita.setNomeTipoForcaPostoGraduacaoVisitante(vis.getNomeTipoForcaPostoGraduacao());
                
                visita.setIdOmVisitante(vis.getIdOm());
                visita.setNomeOmVisitante(vis.getNomeOm());
                visita.setAbreviaturaOmVisitante(vis.getAbreviaturaOm());
                visita.setIdForcaOmVisitante(vis.getIdForcaOm());
                visita.setNomeForcaOmVisitante(vis.getNomeForcaOm());
                visita.setAbreviaturaOmVisitante(vis.getAbreviaturaOm());
                visita.setIdForcaOmVisitante(vis.getIdForcaOm());
                visita.setNomeForcaOmVisitante(vis.getNomeForcaOm());
                visita.setSiglaForcaOmVisitante(vis.getSiglaForcaOm());
                visita.setIdTipoForcaOmVisitante(vis.getIdTipoForcaOm());
                visita.setNomeTipoForcaOmVisitante(vis.getNomeTipoForcaOm());                
                
                Setor setor = setorDAO.getSetorById(rs.getInt("idSetor"));
                visita.setIdSetor(setor.getId());
                visita.setNomeSetor(setor.getNome());
                visita.setAbreviaturaSetor(setor.getAbreviatura());
                visita.setIdDivisaoSecaoSetor(setor.getIdDivisaoSecao());
                visita.setNomeDivisaoSecaoSetor(setor.getNomeDivisaoSecao());
                visita.setAbreviaturaDivisaoSecaoSetor(setor.getAbreviaturaDivisaoSecao());
                
                Veiculo v = vDAO.getVeiculoById(rs.getInt("idVeiculo"));
                visita.setIdVeiculo(v.getId());
                visita.setTipoVeiculo(v.getTipo());
                visita.setMarcaVeiculo(v.getMarca());
                visita.setModeloVeiculo(v.getModelo());
                visita.setCorVeiculo(v.getCor());
                visita.setPlacaVeiculo(v.getPlaca());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return visita;
    }  
    
    private final String GETVISITAS = "SELECT * " +
                                        "FROM " + tabela;
       
    public ArrayList<Visita> getVisitas(){
        ArrayList<Visita> visitas = new ArrayList<>();  
        VisitanteDAO visDAO = new VisitanteDAO();
        SetorDAO setorDAO = new SetorDAO();
        VeiculoDAO vDAO = new VeiculoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITAS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Visita visita = new Visita();
                
                visita.setCracha(rs.getInt("cracha"));
                visita.setEntrada(rs.getDate("entrada"));
                visita.setSaida(rs.getDate("saida"));                            
                
                Visitante vis = visDAO.getVisitanteById(rs.getString("idtVisitante"));
                visita.setIdentidadeVisitante(vis.getNome());
                visita.setNomeVisitante(vis.getNome());
                visita.setSobrenomeVisitante(vis.getSobrenome());
                visita.setNomeguerraVisitante(vis.getNomeguerra());
                visita.setEmailVisitante(vis.getEmail());
                visita.setFoneVisitante(vis.getFone());
                
                visita.setIdPostoGraduacaoVisitante(vis.getIdPostoGraduacao());
                visita.setNomePostoGraduacaoVisitante(vis.getNomePostoGraduacao());
                visita.setAbreviaturaPostoGraduacaoVisitante(vis.getAbreviaturaPostoGraduacao());
                visita.setIdForcaPostoGraduacaoVisitante(vis.getIdForcaPostoGraduacao());
                visita.setNomeForcaPostoGraduacaoVisitante(vis.getNomeForcaPostoGraduacao());
                visita.setSiglaForcaPostoGraduacaoVisitante(vis.getSiglaForcaPostoGraduacao());
                visita.setIdTipoForcaPostoGraduacaoVisitante(vis.getIdTipoForcaPostoGraduacao());
                visita.setNomeTipoForcaPostoGraduacaoVisitante(vis.getNomeTipoForcaPostoGraduacao());
                
                visita.setIdOmVisitante(vis.getIdOm());
                visita.setNomeOmVisitante(vis.getNomeOm());
                visita.setAbreviaturaOmVisitante(vis.getAbreviaturaOm());
                visita.setIdForcaOmVisitante(vis.getIdForcaOm());
                visita.setNomeForcaOmVisitante(vis.getNomeForcaOm());
                visita.setAbreviaturaOmVisitante(vis.getAbreviaturaOm());
                visita.setIdForcaOmVisitante(vis.getIdForcaOm());
                visita.setNomeForcaOmVisitante(vis.getNomeForcaOm());
                visita.setSiglaForcaOmVisitante(vis.getSiglaForcaOm());
                visita.setIdTipoForcaOmVisitante(vis.getIdTipoForcaOm());
                visita.setNomeTipoForcaOmVisitante(vis.getNomeTipoForcaOm());                
                
                Setor setor = setorDAO.getSetorById(rs.getInt("idSetor"));
                visita.setIdSetor(setor.getId());
                visita.setNomeSetor(setor.getNome());
                visita.setAbreviaturaSetor(setor.getAbreviatura());
                visita.setIdDivisaoSecaoSetor(setor.getIdDivisaoSecao());
                visita.setNomeDivisaoSecaoSetor(setor.getNomeDivisaoSecao());
                visita.setAbreviaturaDivisaoSecaoSetor(setor.getAbreviaturaDivisaoSecao());
                
                Veiculo v = vDAO.getVeiculoById(rs.getInt("idVeiculo"));
                visita.setIdVeiculo(v.getId());
                visita.setTipoVeiculo(v.getTipo());
                visita.setMarcaVeiculo(v.getMarca());
                visita.setModeloVeiculo(v.getModelo());
                visita.setCorVeiculo(v.getCor());
                visita.setPlacaVeiculo(v.getPlaca());
                
                visitas.add(visita);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return visitas;
    }
    
    private final static String GETVISITABYIDTDWR = "SELECT * FROM Visita WHERE idtVisitante = ?";
       
    public static Visita getVisitaByIdentidadeDWR(String idtVisitante){
        VisitanteDAO visDAO = new VisitanteDAO();
        SetorDAO setorDAO = new SetorDAO();
        VeiculoDAO vDAO = new VeiculoDAO();
        Visita visita = new Visita();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITABYIDTDWR);
            pstm.setString(1, idtVisitante);
           
            rs = pstm.executeQuery();
            while (rs.next()) {       
                visita.setCracha(rs.getInt("cracha"));
                visita.setEntrada(rs.getDate("entrada"));
                visita.setSaida(rs.getDate("saida"));                            
                
                Visitante vis = visDAO.getVisitanteById(rs.getString("idtVisitante"));
                visita.setIdentidadeVisitante(vis.getNome());
                visita.setNomeVisitante(vis.getNome());
                visita.setSobrenomeVisitante(vis.getSobrenome());
                visita.setNomeguerraVisitante(vis.getNomeguerra());
                visita.setEmailVisitante(vis.getEmail());
                visita.setFoneVisitante(vis.getFone());
                
                visita.setIdPostoGraduacaoVisitante(vis.getIdPostoGraduacao());
                visita.setNomePostoGraduacaoVisitante(vis.getNomePostoGraduacao());
                visita.setAbreviaturaPostoGraduacaoVisitante(vis.getAbreviaturaPostoGraduacao());
                visita.setIdForcaPostoGraduacaoVisitante(vis.getIdForcaPostoGraduacao());
                visita.setNomeForcaPostoGraduacaoVisitante(vis.getNomeForcaPostoGraduacao());
                visita.setSiglaForcaPostoGraduacaoVisitante(vis.getSiglaForcaPostoGraduacao());
                visita.setIdTipoForcaPostoGraduacaoVisitante(vis.getIdTipoForcaPostoGraduacao());
                visita.setNomeTipoForcaPostoGraduacaoVisitante(vis.getNomeTipoForcaPostoGraduacao());
                
                visita.setIdOmVisitante(vis.getIdOm());
                visita.setNomeOmVisitante(vis.getNomeOm());
                visita.setAbreviaturaOmVisitante(vis.getAbreviaturaOm());
                visita.setIdForcaOmVisitante(vis.getIdForcaOm());
                visita.setNomeForcaOmVisitante(vis.getNomeForcaOm());
                visita.setAbreviaturaOmVisitante(vis.getAbreviaturaOm());
                visita.setIdForcaOmVisitante(vis.getIdForcaOm());
                visita.setNomeForcaOmVisitante(vis.getNomeForcaOm());
                visita.setSiglaForcaOmVisitante(vis.getSiglaForcaOm());
                visita.setIdTipoForcaOmVisitante(vis.getIdTipoForcaOm());
                visita.setNomeTipoForcaOmVisitante(vis.getNomeTipoForcaOm());                
                
                Setor setor = setorDAO.getSetorById(rs.getInt("idSetor"));
                visita.setIdSetor(setor.getId());
                visita.setNomeSetor(setor.getNome());
                visita.setAbreviaturaSetor(setor.getAbreviatura());
                visita.setIdDivisaoSecaoSetor(setor.getIdDivisaoSecao());
                visita.setNomeDivisaoSecaoSetor(setor.getNomeDivisaoSecao());
                visita.setAbreviaturaDivisaoSecaoSetor(setor.getAbreviaturaDivisaoSecao());
                
                Veiculo v = vDAO.getVeiculoById(rs.getInt("idVeiculo"));
                visita.setIdVeiculo(v.getId());
                visita.setTipoVeiculo(v.getTipo());
                visita.setMarcaVeiculo(v.getMarca());
                visita.setModeloVeiculo(v.getModelo());
                visita.setCorVeiculo(v.getCor());
                visita.setPlacaVeiculo(v.getPlaca());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return visita;
    }    
}
