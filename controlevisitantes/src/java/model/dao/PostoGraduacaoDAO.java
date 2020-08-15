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
import model.bean.Forca;
import model.bean.PostoGraduacao;

/**
 *
 * @author anderson
 */
public class PostoGraduacaoDAO {
    //Tabela
    String tabela = "PostoGraduacao";
    
    //Atributos
    String id = "id";
    String nome = "nome";
    String abreviatura = "abreviatura";
    String idForca = "idForca";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + id + "," + nome + "," + abreviatura + ","  + idForca + ") " +
                                  "VALUES(?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + nome + "=?, " + abreviatura + "=?, " + idForca + "=?, " + 
                                  "WHERE " + id + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + id + "=?;";
    
    //Consultas SQL
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    //Insert SQL
    public void insert(PostoGraduacao pg) {
        if (pg != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setInt(1, pg.getId());
                pstm.setString(2, pg.getNome());
                pstm.setString(3, pg.getAbreviatura());
                pstm.setInt(4, pg.getIdForca());
                                                              
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
    public void update(PostoGraduacao pg) {
        if (pg != null) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATE);
                                
                pstm.setString(1, pg.getNome());
                pstm.setString(2, pg.getAbreviatura());
                pstm.setInt(3, pg.getIdForca());
                pstm.setInt(4, pg.getId());
                
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
    public void delete(int id) {
        if (id != 0){
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(DELETE);
                pstm.setInt(1, id);
            
                pstm.execute();
                ConnectionFactory.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());  
            }
        } else {            
            throw new RuntimeException();
        }
    }
    
    private final String GETPOSTOGRADUACAOBYID = "SELECT * " +
                                             "FROM PostoGraduacao " + 
                                             "WHERE id = ?";
       
    public PostoGraduacao getPostoGraduacaoById(int idPG){
        PostoGraduacao pg = new PostoGraduacao();
        ForcaDAO forcaDAO = new ForcaDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETPOSTOGRADUACAOBYID);
            pstm.setInt(1, idPG);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                pg.setId(rs.getInt("id"));
                pg.setNome(rs.getString("nome"));
                pg.setAbreviatura(rs.getString("abreviatura"));
                
                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                pg.setIdForca(forca.getId());
                pg.setNomeForca(forca.getNome());
                pg.setSiglaForca(forca.getSigla());
                pg.setIdTipoForca(forca.getIdTipoForca());
                pg.setNomeTipoForca(forca.getNomeTipoForca());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return pg;
    }
    
    private final static String GETPOSTOSGRADUACOESBYFORCADWR = "SELECT * " +
                                                                          "FROM PostoGraduacao " + 
                                                                          "WHERE idForca = ?";   
    
    
    public static ArrayList<PostoGraduacao> getPGsByForcaDWR(int idForca){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ForcaDAO forcaDAO = new ForcaDAO();
        ArrayList<PostoGraduacao> pgs = new ArrayList<>();
        
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETPOSTOSGRADUACOESBYFORCADWR);
            pstm.setInt(1, idForca);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                PostoGraduacao pg = new PostoGraduacao();                

                pg.setId(rs.getInt("id"));
                pg.setNome(rs.getString("nome"));
                pg.setAbreviatura(rs.getString("abreviatura"));
                
                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                pg.setIdForca(forca.getId());
                pg.setNomeForca(forca.getNome());
                pg.setSiglaForca(forca.getSigla());
                pg.setIdTipoForca(forca.getIdTipoForca());
                pg.setNomeTipoForca(forca.getNomeTipoForca());

                pgs.add(pg);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return pgs;
    }
}
