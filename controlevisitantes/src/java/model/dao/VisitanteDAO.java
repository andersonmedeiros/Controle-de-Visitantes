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
    String tipo = "tipo";
    String nome = "nome";
    String sobrenome = "sobrenome";
    String nomeguerra = "nomeguerra";
    String email = "email";
    String fone = "fone";
    String idPostoGraduacao = "idPostoGraduacao";
    String idOrganizacaoMilitar = "idOrganizacaoMilitar";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + identidade + "," + tipo + "," + nome + "," + sobrenome +  "," + nomeguerra + ","  + email +  "," + fone + "," + idPostoGraduacao + "," +idOrganizacaoMilitar + ")" +
                                  " VALUES(?,?,?,?,?,?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + nome + "=?, " + sobrenome + "=?, " + nomeguerra + "=?, " + email + "=?, " + fone + "=?, " + idPostoGraduacao + "=?, " + idOrganizacaoMilitar + "=? " +
                                  "WHERE " + identidade + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + identidade + "=?;";
    
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
                
                pstm.setString(1, vis.getIdentidade());
                pstm.setInt(2, vis.getTipo());
                pstm.setString(3, vis.getNome());
                pstm.setString(4, vis.getSobrenome());
                pstm.setString(5, vis.getNomeguerra());
                pstm.setString(6, vis.getEmail());
                pstm.setString(7, vis.getFone());
                
                if(vis.getIdPostoGraduacao() == 0){
                    pstm.setNull(8, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(8, vis.getIdPostoGraduacao());
                }
                
                if(vis.getIdOm() == 0){
                    pstm.setNull(9, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(9, vis.getIdOm());
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
                
                pstm.setString(1, vis.getNome());
                pstm.setString(2, vis.getSobrenome());
                pstm.setString(3, vis.getNomeguerra());
                pstm.setString(4, vis.getEmail());
                pstm.setString(5, vis.getFone());
                
                if(vis.getIdPostoGraduacao() == 0){
                    pstm.setNull(6, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(6, vis.getIdPostoGraduacao());
                }
                
                if(vis.getIdOm() == 0){
                    pstm.setNull(7, java.sql.Types.INTEGER);
                }else{
                    pstm.setInt(7, vis.getIdOm());
                }
                
                pstm.setString(8, vis.getIdentidade());
                
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
    public void delete(String identidade) {
        if (!identidade.equals("")){
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(DELETE);
                pstm.setString(1, identidade);
            
                pstm.execute();
                ConnectionFactory.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());  
            }
        } else {            
            throw new RuntimeException();
        }
    }
    
    private final String GETVISITANTEBYIDT = "SELECT * " +
                                            "FROM visitante " + 
                                            "WHERE identidade = ?";
       
    public Visitante getVisitanteById(String identidade){
        Visitante vis = new Visitante();
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        OmDAO omDAO = new OmDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITANTEBYIDT);
            pstm.setString(1, identidade);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
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
    
    private final static String GETVISITANTEBYIDTDWR = "SELECT * FROM visitante WHERE identidade = ?";
       
    public static Visitante getVisitanteByIdentidadeDWR(String identidade){
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        OmDAO omDAO = new OmDAO();
        Visitante vis = new Visitante();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVISITANTEBYIDTDWR);
            pstm.setString(1, identidade);
           
            rs = pstm.executeQuery();
            while (rs.next()) {       
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
