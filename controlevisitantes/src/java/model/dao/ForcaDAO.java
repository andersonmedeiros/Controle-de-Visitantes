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
import model.bean.TipoForca;

/**
 *
 * @author anderson
 */
public class ForcaDAO {
    //Tabela
    String tabela = "Forca";
    
    //Atributos
    String id = "id";
    String nome = "nome";
    String sigla = "sigla";
    String idTipoForca = "idTipoForca";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + id + "," + nome + "," + sigla + "," + idTipoForca + ")" +
                                  " VALUES(?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + nome + "=?, " + sigla + "=?, " + idTipoForca + "=? " +
                                  "WHERE " + id + "=?;";    
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + id + "=?;";
    
    //Consultas SQL
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    
    //Insert SQL
    public void insert(Forca forca) {
        if (forca != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setInt(1, forca.getId());
                pstm.setString(2, forca.getNome());
                pstm.setString(3, forca.getSigla());
                pstm.setInt(4, forca.getIdTipoForca());
                
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
    public void update(Forca forca) {
        if (forca != null) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATE);                
                
                pstm.setString(1, forca.getNome());
                pstm.setString(2, forca.getSigla());
                pstm.setInt(3, forca.getIdTipoForca());
                pstm.setInt(4, forca.getId());
            
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
    
    private final String GETFORCABYID = "SELECT * " +
                                        "FROM Forca " + 
                                        "WHERE id = ?;";
       
    public Forca getForcaById(int idForca){
        Forca forca = new Forca();     
        TipoForcaDAO tipoforcaDAO = new TipoForcaDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETFORCABYID);
            pstm.setInt(1, idForca);
           
            rs = pstm.executeQuery();
            while (rs.next()) {                
                forca.setId(rs.getInt("id"));
                forca.setNome(rs.getString("nome"));
                forca.setSigla(rs.getString("sigla"));
                
                TipoForca tipoforca = tipoforcaDAO.getTipoForcaById(rs.getInt("idTipoForca"));
                forca.setIdTipoForca(tipoforca.getId());
                forca.setNomeTipoForca(tipoforca.getNome());
                
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return forca;
    }
    
    private final String GETFORCAS = "SELECT * " +
                                   "FROM " + tabela;
       
    public ArrayList<Forca> getForcas(){
        ArrayList<Forca> forcas = new ArrayList<>(); 
        TipoForcaDAO tipoforcaDAO = new TipoForcaDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETFORCAS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Forca forca = new Forca();

                forca.setId(rs.getInt("id"));
                forca.setNome(rs.getString("nome"));
                forca.setSigla(rs.getString("sigla"));

                TipoForca tipoforca = tipoforcaDAO.getTipoForcaById(rs.getInt("idTipoForca"));
                forca.setIdTipoForca(tipoforca.getId());
                forca.setNomeTipoForca(tipoforca.getNome());

                forcas.add(forca);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return forcas;
    }
    
    private final static String GETFORCABYIDDWR = "SELECT * " +
                                              "FROM Forca " + 
                                              "WHERE id = ?;";
       
    public static Forca getForcaByIdDWR(int idForca){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Forca forca = new Forca();    
        TipoForcaDAO tipoforcaDAO = new TipoForcaDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETFORCABYIDDWR);
            pstm.setInt(1, idForca);
           
            rs = pstm.executeQuery();
            while (rs.next()) {                
                forca.setId(rs.getInt("id"));
                forca.setNome(rs.getString("nome"));
                forca.setSigla(rs.getString("sigla"));
                
                TipoForca tipoforca = tipoforcaDAO.getTipoForcaById(rs.getInt("idTipoForca"));
                forca.setIdTipoForca(tipoforca.getId());
                forca.setNomeTipoForca(tipoforca.getNome());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return forca;
    }
    
    private final static String GETFORCABYTIPOFORCADWR = "SELECT * " +
                                                         "FROM Forca " + 
                                                         "WHERE idTipoForca = ?;";
       
    public static ArrayList<Forca> getForcasByIdTipoForcaDWR(int idTipoForca){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Forca> forcas = new ArrayList<>();
        TipoForcaDAO tipoforcaDAO = new TipoForcaDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETFORCABYTIPOFORCADWR);
            pstm.setInt(1, idTipoForca);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Forca forca = new Forca();

                forca.setId(rs.getInt("id"));
                forca.setNome(rs.getString("nome"));
                forca.setSigla(rs.getString("sigla"));

                TipoForca tipoforca = tipoforcaDAO.getTipoForcaById(rs.getInt("idTipoForca"));
                forca.setIdTipoForca(tipoforca.getId());
                forca.setNomeTipoForca(tipoforca.getNome());

                forcas.add(forca);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return forcas;
    }
    
    private final static String GETFORCASDWR = "SELECT * " +
                                               "FROM Forca";
       
    public static ArrayList<Forca> getForcasDWR(){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Forca> forcas = new ArrayList<>();
        TipoForcaDAO tipoforcaDAO = new TipoForcaDAO();
        
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETFORCASDWR);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Forca forca = new Forca();
                
                forca.setId(rs.getInt("id"));
                forca.setNome(rs.getString("nome"));
                forca.setSigla(rs.getString("sigla"));
                
                TipoForca tipoforca = tipoforcaDAO.getTipoForcaById(rs.getInt("idTipoForca"));
                forca.setIdTipoForca(tipoforca.getId());
                forca.setNomeTipoForca(tipoforca.getNome());                
                
                forcas.add(forca);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return forcas;
    }
}
