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
import model.bean.Veiculo;

/**
 *
 * @author anderson
 */
public class VeiculoDAO {
    //Tabela
    String tabela = "veiculo";
    
    //Atributos
    String id = "id";
    String tipo = "tipo";
    String marca = "marca";
    String modelo = "modelo";
    String cor = "cor";
    String placa = "placa";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + id + "," + tipo + "," + marca +  "," + modelo +  "," + cor +  "," + placa + ")" +
                                  " VALUES(?,?,?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + tipo + "=?, " + marca + "=?, " + modelo + "=?, " + cor + "=?, " + placa + "=?, " + 
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
    public void insert(Veiculo v) {
        if (v != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setInt(1, v.getId());
                pstm.setString(2, v.getTipo());
                pstm.setString(3, v.getMarca());
                pstm.setString(4, v.getModelo());
                pstm.setString(5, v.getCor());
                pstm.setString(6, v.getPlaca());
                
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
    public void update(Veiculo v) {
        if (v != null) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATE);      
                
                pstm.setString(1, v.getTipo());
                pstm.setString(2, v.getMarca());
                pstm.setString(3, v.getModelo());
                pstm.setString(4, v.getCor());
                pstm.setString(5, v.getPlaca());
                pstm.setInt(7, v.getId());
                
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
    
    private final String GETVEICULOBYID = "SELECT * " +
                                        "FROM veiculo " + 
                                        "WHERE id = ?";
       
    public Veiculo getVeiculoById(int id){
        Veiculo v = new Veiculo();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVEICULOBYID);
            pstm.setInt(1, id);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                v.setId(rs.getInt("id"));
                v.setTipo(rs.getString("tipo"));
                v.setMarca(rs.getString("marca"));               
                v.setModelo(rs.getString("modelo"));               
                v.setCor(rs.getString("cor"));               
                v.setPlaca(rs.getString("placa"));          
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return v;
    }
    
    private final String GETVEICULOS = "SELECT * " +
                                       "FROM " + tabela;
       
    public ArrayList<Veiculo> getVeiculos(){
        ArrayList<Veiculo> veiculos = new ArrayList<>();  
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVEICULOS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Veiculo v = new Veiculo();
                
                v.setId(rs.getInt("id"));
                v.setTipo(rs.getString("tipo"));
                v.setMarca(rs.getString("marca"));               
                v.setModelo(rs.getString("modelo"));               
                v.setCor(rs.getString("cor"));               
                v.setPlaca(rs.getString("placa"));    
                
                veiculos.add(v);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return veiculos;
    }
}
