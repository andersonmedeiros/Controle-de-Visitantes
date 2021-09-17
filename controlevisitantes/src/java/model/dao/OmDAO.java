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
public class OmDAO {
    //Tabela
    String tabela = "organizacaomilitar";
    
    //Atributos
    String id = "id";
    String nome = "nome";
    String abreviatura = "abreviatura";
    String idForca = "idForca";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + id + "," + nome + "," + abreviatura + "," + idForca + ") " +
                                  "VALUES(?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + nome + "=?, " + abreviatura + "=?, " + idForca + "=? " +
                                  "WHERE " + id + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + id + "=?;";
    
    //Consultas SQL
    private final String GETUltimoID = "SELECT MAX(" + id + ") as ultimo_id FROM " + tabela + ";";
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    //Próximo ID a ser inserido
    public int proxID(){
        int ultimo_id = 0;
        try{
            conn = ConnectionFactory.getConnection();
            
            pstm = conn.prepareStatement(GETUltimoID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                
                ultimo_id = rs.getInt("ultimo_id");
            }
           
            ConnectionFactory.fechaConexao(conn, pstm);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return (ultimo_id+1);
    }
    
    //Insert SQL
    public void insert(Om om) {
        if (om != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setInt(1, om.getId());
                pstm.setString(2, om.getNome());
                pstm.setString(3, om.getAbreviatura());
                pstm.setInt(4, om.getIdForca());
                                                              
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
    public void update(Om om) {
        if (om != null) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATE);                
                
                pstm.setString(1, om.getNome());
                pstm.setString(2, om.getAbreviatura());
                pstm.setInt(3, om.getIdForca());
                pstm.setInt(4, om.getId());
            
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
    
    private final String GETOMBYID = "SELECT * " +
                                      "FROM " + tabela + " " +
                                      "WHERE id = ?;";
       
    public Om getOmById(int idOM){
        Om om = new Om();                
        ForcaDAO forcaDAO = new ForcaDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMBYID);
            pstm.setInt(1, idOM);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                om.setId(rs.getInt("id"));
                om.setNome(rs.getString("nome"));
                om.setAbreviatura(rs.getString("abreviatura"));

                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                om.setIdForca(forca.getId());
                om.setNomeForca(forca.getNome());
                om.setSiglaForca(forca.getSigla());
                om.setIdTipoForca(forca.getIdTipoForca());
                om.setNomeTipoForca(forca.getNomeTipoForca());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return om;
    }
    
    private final String GETOMS = "SELECT * " +
                                   "FROM " + tabela + " ORDER BY idForca, nome";
       
    public ArrayList<Om> getOms(){
        ArrayList<Om> oms = new ArrayList<>();                    
        ForcaDAO forcaDAO = new ForcaDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Om om = new Om();    
                
                om.setId(rs.getInt("id"));
                om.setNome(rs.getString("nome"));
                om.setAbreviatura(rs.getString("abreviatura"));

                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                om.setIdForca(forca.getId());
                om.setNomeForca(forca.getNome());
                om.setSiglaForca(forca.getSigla());
                om.setIdTipoForca(forca.getIdTipoForca());
                om.setNomeTipoForca(forca.getNomeTipoForca());
                
                oms.add(om);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return oms;
    }
    
    private final static String GETOMSBYFORCADWR = "SELECT * " +
                                   "FROM organizacaomilitar " + 
                                   "WHERE idForca = ? ORDER BY nome;";
       
    public static ArrayList<Om> getOmsByForcaDWR(int idForca){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Om> oms = new ArrayList<>();                    
        ForcaDAO forcaDAO = new ForcaDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMSBYFORCADWR);
            pstm.setInt(1, idForca);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Om om = new Om();    
                
                om.setId(rs.getInt("id"));
                om.setNome(rs.getString("nome"));
                om.setAbreviatura(rs.getString("abreviatura"));

                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                om.setIdForca(forca.getId());
                om.setNomeForca(forca.getNome());
                om.setSiglaForca(forca.getSigla());
                om.setIdTipoForca(forca.getIdTipoForca());
                om.setNomeTipoForca(forca.getNomeTipoForca());
                
                oms.add(om);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return oms;
    }
    
    private final static String GETOMBYIDDWR = "SELECT * " +
                                            "FROM organizacaomilitar " +
                                            "WHERE id = ?;";
    
    public static Om getOmByIdDWR(int idOM){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Om om = new Om();
        ForcaDAO forcaDAO = new ForcaDAO();
        
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMBYIDDWR);
            pstm.setInt(1, idOM);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                om.setId(rs.getInt("id"));
                om.setNome(rs.getString("nome"));
                om.setAbreviatura(rs.getString("abreviatura"));

                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                om.setIdForca(forca.getId());
                om.setNomeForca(forca.getNome());
                om.setSiglaForca(forca.getSigla());
                om.setIdTipoForca(forca.getIdTipoForca());
                om.setNomeTipoForca(forca.getNomeTipoForca());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return om;
    }
}
