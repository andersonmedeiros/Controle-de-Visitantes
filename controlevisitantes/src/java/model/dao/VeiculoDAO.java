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
import model.bean.Aluno;
import model.bean.Veiculo;

/**
 *
 * @author anderson
 */
public class VeiculoDAO {
    //Tabela
    String tabela = "Veiculo";
    
    //Atributos
    String id = "id";
    String tipo = "tipo";
    String marca = "marca";
    String modelo = "modelo";
    String cor = "cor";
    String placa = "placa";
    String idtAluno = "idtAluno";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + id + "," + tipo + "," + marca +  "," + modelo +  "," + cor +  "," + placa +  "," + idtAluno + ")" +
                                  " VALUES(?,?,?,?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + tipo + "=?, " + marca + "=?, " + modelo + "=?, " + cor + "=?, " + placa + "=?, " + idtAluno + "=? " +
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
                pstm.setString(7, v.getIdentidadeAluno());
                
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
                pstm.setString(6, v.getIdentidadeAluno());
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
                                        "FROM Veiculo " + 
                                        "WHERE id = ?";
       
    public Veiculo getVeiculoById(int id){
        Veiculo v = new Veiculo();
        AlunoDAO alDAO = new AlunoDAO();
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
                
                Aluno al = alDAO.getAlunoByIdentidade(rs.getString("idtAluno"));
                v.setIdentidadeAluno(al.getIdentidade());
                v.setSituacaoAluno(al.getSituacao());
                v.setIdPostoGraduacaoAluno(al.getIdPostoGraduacao());
                v.setIdQasQmsAluno(al.getIdQasQms());
                v.setIdCmtAluno(al.getIdCmt());
                v.setDataNascimentoAluno(al.getDataNascimento());
                v.setNomeAluno(al.getNome());
                v.setSobrenomeAluno(al.getSobrenome());
                v.setNomeguerraAluno(al.getNomeguerra());
                v.setPreccpAluno(al.getPreccp());
                v.setCpAluno(al.getCp());
                v.setCpfAluno(al.getCpf());
                v.setUltDataPracaAluno(al.getUltDataPraca());
                v.setIdNatCidadeAluno(al.getIdNatCidade());
                v.setIdEstadoCivilAluno(al.getIdEstadoCivil());
                v.setTsAluno(al.getTs());
                v.setFtrhAluno(al.getFtrh());
                v.setPaiAluno(al.getPai());
                v.setMaeAluno(al.getMae());
                v.setEmailAluno(al.getEmail());
                v.setFumanteAluno(al.getFumante());
                v.setIdOMAluno(al.getIdOM());
                v.setIdComportamentoAluno(al.getIdComportamento());
                v.setIdChImtoAluno(al.getIdChImto());
                v.setSexoAluno(al.getSexo());
                v.setUltfuncao1Aluno(al.getUltfuncao1());
                v.setUltfuncao2Aluno(al.getUltfuncao2());
                v.setUltfuncao3Aluno(al.getUltfuncao3());
                v.setIdTafAluno(al.getIdTaf());
                v.setIdPromocaoAluno(al.getIdPromocao());
                v.setIdPreparacaoAluno(al.getIdPreparacao());
                v.setIdUniformeAluno(al.getIdUniforme());
                v.setEasAluno(al.getEas());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return v;
    }
        
    private final String GETVEICULOSBYIDTALUNO = "SELECT * " +
                                            "FROM Veiculo " + 
                                            "WHERE idtAluno = ?";
       
    public ArrayList<Veiculo> getVeiculosByIdtAluno(String idtAluno){
        ArrayList<Veiculo> veiculos = new ArrayList<>();  
        AlunoDAO alDAO = new AlunoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETVEICULOSBYIDTALUNO);
            pstm.setString(1, idtAluno);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Veiculo v = new Veiculo();
                
                v.setId(rs.getInt("id"));
                v.setTipo(rs.getString("tipo"));
                v.setMarca(rs.getString("marca"));               
                v.setModelo(rs.getString("modelo"));               
                v.setCor(rs.getString("cor"));               
                v.setPlaca(rs.getString("placa"));    
                
                Aluno al = alDAO.getAlunoByIdentidade(rs.getString("idtAluno"));
                v.setIdentidadeAluno(al.getIdentidade());
                v.setSituacaoAluno(al.getSituacao());
                v.setIdPostoGraduacaoAluno(al.getIdPostoGraduacao());
                v.setIdQasQmsAluno(al.getIdQasQms());
                v.setIdCmtAluno(al.getIdCmt());
                v.setDataNascimentoAluno(al.getDataNascimento());
                v.setNomeAluno(al.getNome());
                v.setSobrenomeAluno(al.getSobrenome());
                v.setNomeguerraAluno(al.getNomeguerra());
                v.setPreccpAluno(al.getPreccp());
                v.setCpAluno(al.getCp());
                v.setCpfAluno(al.getCpf());
                v.setUltDataPracaAluno(al.getUltDataPraca());
                v.setIdNatCidadeAluno(al.getIdNatCidade());
                v.setIdEstadoCivilAluno(al.getIdEstadoCivil());
                v.setTsAluno(al.getTs());
                v.setFtrhAluno(al.getFtrh());
                v.setPaiAluno(al.getPai());
                v.setMaeAluno(al.getMae());
                v.setEmailAluno(al.getEmail());
                v.setFumanteAluno(al.getFumante());
                v.setIdOMAluno(al.getIdOM());
                v.setIdComportamentoAluno(al.getIdComportamento());
                v.setIdChImtoAluno(al.getIdChImto());
                v.setSexoAluno(al.getSexo());
                v.setUltfuncao1Aluno(al.getUltfuncao1());
                v.setUltfuncao2Aluno(al.getUltfuncao2());
                v.setUltfuncao3Aluno(al.getUltfuncao3());
                v.setIdTafAluno(al.getIdTaf());
                v.setIdPromocaoAluno(al.getIdPromocao());
                v.setIdPreparacaoAluno(al.getIdPreparacao());
                v.setIdUniformeAluno(al.getIdUniforme());
                v.setEasAluno(al.getEas());
                
                veiculos.add(v);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return veiculos;
    }
    
    private final String GETVEICULOS = "SELECT * " +
                                       "FROM " + tabela;
       
    public ArrayList<Veiculo> getVeiculos(){
        ArrayList<Veiculo> veiculos = new ArrayList<>();  
        AlunoDAO alDAO = new AlunoDAO();
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
                
                Aluno al = alDAO.getAlunoByIdentidade(rs.getString("idtAluno"));
                v.setIdentidadeAluno(al.getIdentidade());
                v.setSituacaoAluno(al.getSituacao());
                v.setIdPostoGraduacaoAluno(al.getIdPostoGraduacao());
                v.setIdQasQmsAluno(al.getIdQasQms());
                v.setIdCmtAluno(al.getIdCmt());
                v.setDataNascimentoAluno(al.getDataNascimento());
                v.setNomeAluno(al.getNome());
                v.setSobrenomeAluno(al.getSobrenome());
                v.setNomeguerraAluno(al.getNomeguerra());
                v.setPreccpAluno(al.getPreccp());
                v.setCpAluno(al.getCp());
                v.setCpfAluno(al.getCpf());
                v.setUltDataPracaAluno(al.getUltDataPraca());
                v.setIdNatCidadeAluno(al.getIdNatCidade());
                v.setIdEstadoCivilAluno(al.getIdEstadoCivil());
                v.setTsAluno(al.getTs());
                v.setFtrhAluno(al.getFtrh());
                v.setPaiAluno(al.getPai());
                v.setMaeAluno(al.getMae());
                v.setEmailAluno(al.getEmail());
                v.setFumanteAluno(al.getFumante());
                v.setIdOMAluno(al.getIdOM());
                v.setIdComportamentoAluno(al.getIdComportamento());
                v.setIdChImtoAluno(al.getIdChImto());
                v.setSexoAluno(al.getSexo());
                v.setUltfuncao1Aluno(al.getUltfuncao1());
                v.setUltfuncao2Aluno(al.getUltfuncao2());
                v.setUltfuncao3Aluno(al.getUltfuncao3());
                v.setIdTafAluno(al.getIdTaf());
                v.setIdPromocaoAluno(al.getIdPromocao());
                v.setIdPreparacaoAluno(al.getIdPreparacao());
                v.setIdUniformeAluno(al.getIdUniforme());
                v.setEasAluno(al.getEas());
                
                veiculos.add(v);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return veiculos;
    }
}
