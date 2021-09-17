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
    String tabela = "visita";
    
    //Atributos
    String idtVisitante = "idtVisitante";
    String idSetor = "idSetor";
    String cracha = "cracha";
    String dataEntrada = "dataEntrada";
    String horaEntrada = "horaEntrada";
    String dataSaida = "dataSaida";
    String horaSaida = "horaSaida";
    String idVeiculo = "idVeiculo";
    String finalidade = "finalidade";
    
    //Insert SQL
    private final String INSERTENTRADA = "INSERT INTO " + tabela + "(" + idtVisitante + "," + idSetor + "," + cracha +  "," + dataEntrada + "," + horaEntrada + "," + idVeiculo + "," + finalidade + ")" +
                                         " VALUES(?,?,?,?,?,?,?);";
    
    //Update SQL
    private final String INSERTSAIDA = "UPDATE " + tabela +
                                        " SET " +  dataSaida + "=?, " + horaSaida + "=? " +
                                        "WHERE " + idtVisitante + "=? AND " + idSetor + "=? AND " + dataEntrada + "=? AND " + horaEntrada + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + idtVisitante + "=? AND " + idSetor + "=? AND " + dataEntrada + "=? AND " + horaEntrada + "=?;";
    
    //Consultas SQL
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    //Insert SQL
    public void insertEntrada(Visita visita) {
        if (visita != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERTENTRADA);
                
                pstm.setString(1, visita.getIdentidadeVisitante());
                pstm.setInt(2, visita.getIdSetor());
                pstm.setInt(3, visita.getCracha());
                pstm.setDate(4, visita.getDataEntrada());
                pstm.setTime(5, visita.getHoraEntrada());
                
                if(visita.getIdVeiculo() == 0){
                    pstm.setNull(6, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(6, visita.getIdVeiculo());
                }
                
                pstm.setString(7, visita.getFinalidade());
                
                pstm.execute();
                
                ConnectionFactory.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());  
            }
        } else {
            throw new RuntimeException();
        }
    }
    public void insertSaida(Visita visita) {
        if (visita != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERTSAIDA);
                
                
                pstm.setDate(1, visita.getDataSaida());
                pstm.setTime(2, visita.getHoraSaida());
                pstm.setString(3, visita.getIdentidadeVisitante());
                pstm.setInt(4, visita.getIdSetor());
                pstm.setDate(5, visita.getDataEntrada());
                pstm.setTime(6, visita.getHoraEntrada());
                
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
                                            "FROM visita " + 
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
                visita.setDataEntrada(rs.getDate("dataEntrada"));
                visita.setHoraEntrada(rs.getTime("horaEntrada"));
                visita.setDataSaida(rs.getDate("dataSaida"));                            
                visita.setHoraSaida(rs.getTime("horaSaida"));                            
                
                Visitante vis = visDAO.getVisitanteById(rs.getString("idtVisitante"));
                visita.setIdentidadeVisitante(vis.getIdentidade());
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
       
    public int getQtdeVisitas(String sql){
        int qtde = 0;
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(sql);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                qtde = rs.getInt("qtdeVisitas");
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return qtde;
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
                visita.setDataEntrada(rs.getDate("dataEntrada"));
                visita.setHoraEntrada(rs.getTime("horaEntrada"));
                visita.setDataSaida(rs.getDate("dataSaida"));                            
                visita.setHoraSaida(rs.getTime("horaSaida"));                           
                
                Visitante vis = visDAO.getVisitanteById(rs.getString("idtVisitante"));
                visita.setIdentidadeVisitante(vis.getIdentidade());
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
    
    private final String GETVISITASABERTAS = "SELECT * " +
                                             "FROM " + tabela + 
                                             " WHERE dataSaida is null AND horaSaida is null order by dataEntrada desc;";
       
    public ArrayList<Visita> getVisitasAbertas(){
        ArrayList<Visita> visitas = new ArrayList<>();  
        VisitanteDAO visDAO = new VisitanteDAO();
        SetorDAO setorDAO = new SetorDAO();
        VeiculoDAO vDAO = new VeiculoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITASABERTAS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Visita visita = new Visita();
                
                visita.setCracha(rs.getInt("cracha"));
                visita.setDataEntrada(rs.getDate("dataEntrada"));
                visita.setHoraEntrada(rs.getTime("horaEntrada"));
                visita.setDataSaida(rs.getDate("dataSaida"));                            
                visita.setHoraSaida(rs.getTime("horaSaida"));                           
                
                Visitante vis = visDAO.getVisitanteById(rs.getString("idtVisitante"));
                visita.setIdentidadeVisitante(vis.getIdentidade());
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
    
    private final String GETVISITASFECHADAS = "SELECT * " +
                                              "FROM " + tabela + 
                                             " WHERE dataSaida is not null AND horaSaida is not null order by dataEntrada desc;";
       
    public ArrayList<Visita> getVisitasFechadas(){
        ArrayList<Visita> visitas = new ArrayList<>();  
        VisitanteDAO visDAO = new VisitanteDAO();
        SetorDAO setorDAO = new SetorDAO();
        VeiculoDAO vDAO = new VeiculoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITASFECHADAS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Visita visita = new Visita();
                
                visita.setCracha(rs.getInt("cracha"));
                visita.setDataEntrada(rs.getDate("dataEntrada"));
                visita.setHoraEntrada(rs.getTime("horaEntrada"));
                visita.setDataSaida(rs.getDate("dataSaida"));                            
                visita.setHoraSaida(rs.getTime("horaSaida"));                           
                
                Visitante vis = visDAO.getVisitanteById(rs.getString("idtVisitante"));
                visita.setIdentidadeVisitante(vis.getIdentidade());
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
    
    private final static String GETVISITABYIDTDWR = "SELECT * FROM visita WHERE idtVisitante = ?";
       
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
                visita.setDataEntrada(rs.getDate("dataEntrada"));
                visita.setHoraEntrada(rs.getTime("horaEntrada"));
                visita.setDataSaida(rs.getDate("dataSaida"));                            
                visita.setHoraSaida(rs.getTime("horaSaida"));                            
                
                Visitante vis = visDAO.getVisitanteById(rs.getString("idtVisitante"));
                visita.setIdentidadeVisitante(vis.getIdentidade());
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
    
    private static final String GETVISITASMILOUTRASOMDWR = "SELECT pg.abreviatura as pg, vis.nome, vis.sobrenome, om.abreviatura as om, v.idtVisitante, v.dataEntrada, v.horaEntrada, v.dataSaida, v.horaSaida, s.abreviatura as destino, " +
                                                        "IFNULL(veiculo.marca, '-') as marca, IFNULL(veiculo.modelo, '-') as modelo, IFNULL(veiculo.cor, '-') as cor, IFNULL(veiculo.placa, '-') as placa " +
                                                        "FROM visita as v " +
                                                        "INNER JOIN visitante as vis on v.idtVisitante = vis.identidade " +
                                                        "INNER JOIN postograduacao as pg on vis.idPostoGraduacao = pg.id " +
                                                        "INNER JOIN organizacaomilitar as om on vis.idOrganizacaoMilitar = om.id " +
                                                        "INNER JOIN setor as s on v.idSetor = s.id " +
                                                        "LEFT JOIN veiculo as veiculo on v.idVeiculo = veiculo.id " +
                                                        "WHERE v.dataSaida is not null AND v.horaSaida is not null AND " +
                                                        "vis.tipo = ? AND v.dataEntrada = ? order by v.horaEntrada;";
    
    private static final String GETVISITASCIVISDWR = "SELECT vis.nome, vis.sobrenome, v.idtVisitante, v.dataEntrada, v.horaEntrada, v.dataSaida, v.horaSaida, s.abreviatura as destino, " +
                                                  "IFNULL(veiculo.marca, '-') as marca, IFNULL(veiculo.modelo, '-') as modelo, IFNULL(veiculo.cor, '-') as cor, IFNULL(veiculo.placa, '-') as placa " +
                                                  "FROM visita as v " +
                                                  "INNER JOIN visitante as vis on v.idtVisitante = vis.identidade " +
                                                  "INNER JOIN setor as s on v.idSetor = s.id " +
                                                  "LEFT JOIN veiculo as veiculo on v.idVeiculo = veiculo.id " +
                                                  "WHERE v.dataSaida is not null and v.horaSaida is not null AND " +
                                                  "vis.tipo = ? AND v.dataEntrada = ? order by v.horaEntrada;";
       
    public static ArrayList<Visita> getVisitasByTipoAndDataDWR(int tipo, String data){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Visita> visitas = new ArrayList<>();  
        VisitanteDAO visDAO = new VisitanteDAO();
        SetorDAO setorDAO = new SetorDAO();
        VeiculoDAO vDAO = new VeiculoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            if(tipo == 1){
                pstm = conn.prepareStatement(GETVISITASMILOUTRASOMDWR);
                pstm.setInt(1, tipo);
                pstm.setString(2, data);
                
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Visita visita = new Visita();
                    visita.setDataEntrada(rs.getDate("v.dataEntrada"));
                    visita.setHoraEntrada(rs.getTime("v.horaEntrada"));
                    visita.setDataSaida(rs.getDate("v.dataSaida"));                            
                    visita.setHoraSaida(rs.getTime("v.horaSaida"));                           

                    Visitante vis = visDAO.getVisitanteById(rs.getString("v.idtVisitante"));
                    visita.setIdentidadeVisitante(vis.getIdentidade());
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

                    visita.setAbreviaturaSetor(rs.getString("destino"));

                    visita.setMarcaVeiculo(rs.getString("marca"));
                    visita.setModeloVeiculo(rs.getString("modelo"));
                    visita.setCorVeiculo(rs.getString("cor"));
                    visita.setPlacaVeiculo(rs.getString("placa"));

                    visitas.add(visita);
                }

            }
            else if(tipo == 2){
                pstm = conn.prepareStatement(GETVISITASCIVISDWR);
                pstm.setInt(1, tipo);
                pstm.setString(2, data);
                
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Visita visita = new Visita();
                    visita.setDataEntrada(rs.getDate("v.dataEntrada"));
                    visita.setHoraEntrada(rs.getTime("v.horaEntrada"));
                    visita.setDataSaida(rs.getDate("v.dataSaida"));                            
                    visita.setHoraSaida(rs.getTime("v.horaSaida"));                           

                    Visitante vis = visDAO.getVisitanteById(rs.getString("v.idtVisitante"));
                    visita.setIdentidadeVisitante(vis.getIdentidade());
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

                    visita.setAbreviaturaSetor(rs.getString("destino"));

                    visita.setMarcaVeiculo(rs.getString("marca"));
                    visita.setModeloVeiculo(rs.getString("modelo"));
                    visita.setCorVeiculo(rs.getString("cor"));
                    visita.setPlacaVeiculo(rs.getString("placa"));

                    visitas.add(visita);
                }
            }            
           
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return visitas;
    }
    
}
