/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.*;

/**
 *
 * @author anderson
 */
public class VisitanteDAO {
    //Tabela
    String tabela = "visitante";
    
    //Atributos
    String identidade = "identidade";
    String cpf = "cpf";
    String tipo = "tipo";
    String nome = "nome";
    String sobrenome = "sobrenome";
    String nomeguerra = "nomeguerra";
    String email = "email";
    String fone = "fone";
    String idPostoGraduacao = "idPostoGraduacao";
    String idOrganizacaoMilitar = "idOrganizacaoMilitar";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + cpf + "," + identidade + ","+ tipo + "," + nome + "," + sobrenome +  "," + nomeguerra + ","  + email +  "," + fone + "," + idPostoGraduacao + "," +idOrganizacaoMilitar + ")" +
                                  " VALUES(?,?,?,?,?,?,?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + identidade + "=?, "+ nome + "=?, " + sobrenome + "=?, " + nomeguerra + "=?, " + email + "=?, " + fone + "=?, " + idPostoGraduacao + "=?, " + idOrganizacaoMilitar + "=? " +
                                  "WHERE " + cpf + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + cpf + "=?;";
    
    //Consultas SQL
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    //Insert SQL
    public void insert(Visitante vis) {
        if (vis != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setString(1, vis.getCpf());
                pstm.setString(2, vis.getIdentidade());
                pstm.setInt(3, vis.getTipo());
                pstm.setString(4, vis.getNome());
                pstm.setString(5, vis.getSobrenome());
                pstm.setString(6, vis.getNomeguerra());
                pstm.setString(7, vis.getEmail());
                pstm.setString(8, vis.getFone());
                
                if(vis.getIdPostoGraduacao() == 0){
                    pstm.setNull(9, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(9, vis.getIdPostoGraduacao());
                }
                
                if(vis.getIdOm() == 0){
                    pstm.setNull(10, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(10, vis.getIdOm());
                }
                
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
    public void update(Visitante vis) {
        if (vis != null) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATE); 
                
                pstm.setString(1, vis.getIdentidade());
                pstm.setString(2, vis.getNome());
                pstm.setString(3, vis.getSobrenome());
                pstm.setString(4, vis.getNomeguerra());
                pstm.setString(5, vis.getEmail());
                pstm.setString(6, vis.getFone());
                
                if(vis.getIdPostoGraduacao() == 0){
                    pstm.setNull(7, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(7, vis.getIdPostoGraduacao());
                }
                
                if(vis.getIdOm() == 0){
                    pstm.setNull(8, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(8, vis.getIdOm());
                }
                
                pstm.setString(9, vis.getCpf());
                
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
    public void delete(String cpf) {
        if (!cpf.equals("")){
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(DELETE);
                pstm.setString(1, cpf);
            
                pstm.execute();
                ConnectionFactory.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());  
            }
        } else {            
            throw new RuntimeException();
        }
    }
    
    private final String GETVISITANTEBYCPF = "SELECT * " +
                                            "FROM visitante " + 
                                            "WHERE cpf = ?";
       
    public Visitante getVisitanteByCpf(String cpf){
        Visitante vis = new Visitante();
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        OmDAO omDAO = new OmDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITANTEBYCPF);
            pstm.setString(1, cpf);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                vis.setCpf(rs.getString("cpf"));
                vis.setIdentidade(rs.getString("identidade"));
                vis.setTipo(rs.getInt("tipo"));
                vis.setNome(rs.getString("nome"));
                vis.setSobrenome(rs.getString("sobrenome"));                         
                vis.setNomeguerra(rs.getString("nomeguerra"));            
                vis.setEmail(rs.getString("email"));                        
                vis.setFone(rs.getString("fone"));                        
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                vis.setIdPostoGraduacao(pg.getId());
                vis.setNomePostoGraduacao(pg.getNome());
                vis.setAbreviaturaPostoGraduacao(pg.getAbreviatura());             
                vis.setIdForcaPostoGraduacao(pg.getIdForca());
                vis.setNomeForcaPostoGraduacao(pg.getNomeForca());
                vis.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                vis.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                vis.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                Om om = omDAO.getOmById(rs.getInt("idOrganizacaoMilitar"));
                vis.setIdOm(om.getId());
                vis.setNomeOm(om.getNome());
                vis.setAbreviaturaOm(om.getAbreviatura());
                vis.setIdForcaOm(om.getIdForca());
                vis.setNomeForcaOm(om.getNomeForca());
                vis.setSiglaForcaOm(om.getSiglaForca());
                vis.setIdTipoForcaOm(om.getIdTipoForca());
                vis.setNomeTipoForcaOm(om.getNomeTipoForca());                
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return vis;
    }  
    
    private final String GETVISITANTES = "SELECT * " +
                                        "FROM " + tabela;
       
    public ArrayList<Visitante> getVisitantes(){
        ArrayList<Visitante> visitantes = new ArrayList<>();  
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        OmDAO omDAO = new OmDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITANTES);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Visitante vis = new Visitante();
                
                vis.setCpf(rs.getString("cpf"));
                vis.setIdentidade(rs.getString("identidade"));
                vis.setTipo(rs.getInt("tipo"));
                vis.setNome(rs.getString("nome"));
                vis.setSobrenome(rs.getString("sobrenome"));                         
                vis.setNomeguerra(rs.getString("nomeguerra"));            
                vis.setEmail(rs.getString("email"));     
                vis.setFone(rs.getString("fone"));   
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                vis.setIdPostoGraduacao(pg.getId());
                vis.setNomePostoGraduacao(pg.getNome());
                vis.setAbreviaturaPostoGraduacao(pg.getAbreviatura());               
                vis.setIdForcaPostoGraduacao(pg.getIdForca());
                vis.setNomeForcaPostoGraduacao(pg.getNomeForca());
                vis.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                vis.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                vis.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                Om om = omDAO.getOmById(rs.getInt("idOrganizacaoMilitar"));
                vis.setIdOm(om.getId());
                vis.setNomeOm(om.getNome());
                vis.setAbreviaturaOm(om.getAbreviatura());
                vis.setIdForcaOm(om.getIdForca());
                vis.setNomeForcaOm(om.getNomeForca());
                vis.setSiglaForcaOm(om.getSiglaForca());
                vis.setIdTipoForcaOm(om.getIdTipoForca());
                vis.setNomeTipoForcaOm(om.getNomeTipoForca()); 
                
                visitantes.add(vis);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return visitantes;
    }
    
    private final String GETVISITANTESBYTIPO = "SELECT * " +
                                        "FROM " + tabela + " WHERE tipo = ?;";
       
    public ArrayList<Visitante> getVisitantesByTipo(int tipo){
        ArrayList<Visitante> visitantes = new ArrayList<>();  
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        OmDAO omDAO = new OmDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITANTESBYTIPO);
            pstm.setInt(1, tipo);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Visitante vis = new Visitante();
                
                vis.setCpf(rs.getString("cpf"));
                vis.setIdentidade(rs.getString("identidade"));
                vis.setTipo(rs.getInt("tipo"));
                vis.setNome(rs.getString("nome"));
                vis.setSobrenome(rs.getString("sobrenome"));                         
                vis.setNomeguerra(rs.getString("nomeguerra"));            
                vis.setEmail(rs.getString("email"));     
                vis.setFone(rs.getString("fone"));   
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                vis.setIdPostoGraduacao(pg.getId());
                vis.setNomePostoGraduacao(pg.getNome());
                vis.setAbreviaturaPostoGraduacao(pg.getAbreviatura());               
                vis.setIdForcaPostoGraduacao(pg.getIdForca());
                vis.setNomeForcaPostoGraduacao(pg.getNomeForca());
                vis.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                vis.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                vis.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                Om om = omDAO.getOmById(rs.getInt("idOrganizacaoMilitar"));
                vis.setIdOm(om.getId());
                vis.setNomeOm(om.getNome());
                vis.setAbreviaturaOm(om.getAbreviatura());
                vis.setIdForcaOm(om.getIdForca());
                vis.setNomeForcaOm(om.getNomeForca());
                vis.setSiglaForcaOm(om.getSiglaForca());
                vis.setIdTipoForcaOm(om.getIdTipoForca());
                vis.setNomeTipoForcaOm(om.getNomeTipoForca()); 
                
                visitantes.add(vis);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return visitantes;
    }
    
    private final static String GETVISITANTEBYCPFDWR = "SELECT * FROM visitante WHERE cpf = ?";
       
    public static Visitante getVisitanteByCpfDWR(String cpf){
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        OmDAO omDAO = new OmDAO();
        Visitante vis = new Visitante();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITANTEBYCPFDWR);
            pstm.setString(1, cpf);
           
            rs = pstm.executeQuery();
            while (rs.next()) {       
                vis.setCpf(rs.getString("cpf"));
                vis.setIdentidade(rs.getString("identidade"));
                vis.setTipo(rs.getInt("tipo"));
                vis.setNome(rs.getString("nome"));
                vis.setSobrenome(rs.getString("sobrenome"));                         
                vis.setNomeguerra(rs.getString("nomeguerra"));            
                vis.setEmail(rs.getString("email"));     
                vis.setFone(rs.getString("fone"));   
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                vis.setIdPostoGraduacao(pg.getId());
                vis.setNomePostoGraduacao(pg.getNome());
                vis.setAbreviaturaPostoGraduacao(pg.getAbreviatura());               
                vis.setIdForcaPostoGraduacao(pg.getIdForca());
                vis.setNomeForcaPostoGraduacao(pg.getNomeForca());
                vis.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                vis.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                vis.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                Om om = omDAO.getOmById(rs.getInt("idOrganizacaoMilitar"));
                vis.setIdOm(om.getId());
                vis.setNomeOm(om.getNome());
                vis.setAbreviaturaOm(om.getAbreviatura());
                vis.setIdForcaOm(om.getIdForca());
                vis.setNomeForcaOm(om.getNomeForca());
                vis.setSiglaForcaOm(om.getSiglaForca());
                vis.setIdTipoForcaOm(om.getIdTipoForca());
                vis.setNomeTipoForcaOm(om.getNomeTipoForca()); 
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return vis;
    }    
    
    
   
}
