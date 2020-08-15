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
import model.bean.DivisaoSecao;

/**
 *
 * @author anderson
 */
public class DivisaoSecaoDAO {
    //Tabela
    String tabela = "DivisaoSecao";
    
    //Atributos
    String id = "id";
    String nome = "nome";
    String abreviatura = "abreviatura";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + id + "," + nome + "," + abreviatura + ")" +
                                  " VALUES(?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + nome + "=?, " + abreviatura + "=? " +
                                  "WHERE " + id + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + id + "=?;";
    
    //Consultas SQL    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    //Insert SQL
    public void insert(DivisaoSecao divsec) {
        if (divsec != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setInt(1, divsec.getId());
                pstm.setString(2, divsec.getNome());
                pstm.setString(3, divsec.getAbreviatura());
                
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
    public void update(DivisaoSecao divsec) {
        if (divsec != null) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATE);
                                
                pstm.setString(1, divsec.getNome());
                pstm.setString(2, divsec.getAbreviatura());
                pstm.setInt(3, divsec.getId());
            
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
    
    private final String GETDIVISAOSECAOBYID = "SELECT * " +
                                            "FROM DivisaoSecao " + 
                                            "WHERE id = ?;";
       
    public DivisaoSecao getDivisaoSecaoById(int idDivisaoSecao){
        DivisaoSecao divsec = new DivisaoSecao();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETDIVISAOSECAOBYID);
            pstm.setInt(1, idDivisaoSecao);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                divsec.setId(rs.getInt("id"));
                divsec.setNome(rs.getString("nome"));
                divsec.setAbreviatura(rs.getString("abreviatura"));
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return divsec;
    }
    
    private final String GETTIPOSFORCA = "SELECT * " +
                                         "FROM " + tabela;
       
    public ArrayList<DivisaoSecao> getDivisoesSecoes(){
        ArrayList<DivisaoSecao> divisoessecoes = new ArrayList<>();        
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETTIPOSFORCA);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                DivisaoSecao divsec = new DivisaoSecao();
                
                divsec.setId(rs.getInt("id"));
                divsec.setNome(rs.getString("nome"));
                divsec.setAbreviatura(rs.getString("abreviatura"));
                
                divisoessecoes.add(divsec);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return divisoessecoes;
    }
    
    private final static String GETDIVISAOSECAOBYIDDWR = "SELECT * " +
                                                      "FROM DivisaoSecao " + 
                                                      "WHERE id = ?;";
       
    public DivisaoSecao getDivisaoSecaoByIdDWR(int idDivisaoSecao){
        DivisaoSecao divsec = new DivisaoSecao();    
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETDIVISAOSECAOBYIDDWR);
            pstm.setInt(1, idDivisaoSecao);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                divsec.setId(rs.getInt("id"));
                divsec.setNome(rs.getString("nome"));
                divsec.setAbreviatura(rs.getString("abreviatura"));
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return divsec;
    }
    
    private final static String GETTIPOSFORCADWR = "SELECT * " +
                                                   "FROM DivisaoSecao";
       
    public static ArrayList<DivisaoSecao> getDivisoesSecoesDWR(){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<DivisaoSecao> divisoessecoes = new ArrayList<>();
        
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETTIPOSFORCADWR);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                DivisaoSecao divsec = new DivisaoSecao();
                
                divsec.setId(rs.getInt("id"));
                divsec.setNome(rs.getString("nome")); 
                divsec.setAbreviatura(rs.getString("abreviatura"));
                
                divisoessecoes.add(divsec);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return divisoessecoes;
    }
}
