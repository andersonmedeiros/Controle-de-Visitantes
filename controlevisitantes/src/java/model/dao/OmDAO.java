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
    String tabela = "OM";
    
    //Atributos
    String id = "id";
    String nome = "nome";
    String abreviatura = "abreviatura";
    String numendereco = "numendereco";
    String idForca = "idForca";
    String idEstado = "idEstado";
    String idEndereco = "idEndereco";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + id + "," + nome + "," + abreviatura + "," + numendereco + "," + idForca + "," + idEstado + "," + idEndereco + ") " +
                                  "VALUES(?,?,?,?,?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + nome + "=?, " + abreviatura + "=?, " + numendereco + "=?, " + idForca + "=?, " + idEstado + "=?, " + idEndereco + "=? " + 
                                  "WHERE " + id + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + id + "=?;";
    
    //Consultas SQL
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    //Insert SQL
    public void insert(Om om) {
        if (om != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setInt(1, om.getId());
                pstm.setString(2, om.getNome());
                pstm.setString(3, om.getAbreviatura());
                pstm.setString(4, om.getNumendereco());
                pstm.setInt(5, om.getIdForca());
                pstm.setInt(6, om.getIdEstado());
                pstm.setInt(7, om.getIdEndereco());
                                                              
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
                pstm.setString(3, om.getNumendereco());
                pstm.setInt(4, om.getIdForca());
                pstm.setInt(5, om.getIdEstado());
                pstm.setInt(6, om.getIdEndereco());
                pstm.setInt(7, om.getId());
            
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
        EstadoDAO estDAO = new EstadoDAO();
        EnderecoDAO endDAO = new EnderecoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMBYID);
            pstm.setInt(1, idOM);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                om.setId(rs.getInt("id"));
                om.setNome(rs.getString("nome"));
                om.setAbreviatura(rs.getString("abreviatura"));
                om.setNumendereco(rs.getString("numendereco"));

                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                om.setIdForca(forca.getId());
                om.setNomeForca(forca.getNome());
                om.setSiglaForca(forca.getSigla());
                om.setIdTipoForca(forca.getIdTipoForca());
                om.setNomeTipoForca(forca.getNomeTipoForca());

                Estado est = estDAO.getEstadoById(rs.getInt("idEstado"));
                om.setIdEstado(est.getId());
                om.setNomeEstado(est.getNome());
                om.setSiglaEstado(est.getSigla());
                om.setIdRegiaoEstado(est.getIdRegiao());
                om.setNomeRegiaoEstado(est.getNomeRegiao());

                Endereco end = endDAO.getEnderecoById(rs.getInt("idEndereco"));
                om.setIdEndereco(end.getId());
                om.setCepEndereco(end.getCep());
                om.setDescricaoEndereco(end.getDescricao());
                om.setComplementoEndereco(end.getComplemento());
                om.setPontoreferenciaEndereco(end.getPontoreferencia());
                om.setBairroEndereco(end.getBairro());
                om.setIdCidadeEndereco(end.getIdCidade());
                om.setNomeCidadeEndereco(end.getNomeCidade());
                om.setIdEstadoCidadeEndereco(end.getIdEstadoCidade());
                om.setNomeEstadoCidadeEndereco(end.getNomeEstadoCidade());
                om.setSiglaEstadoCidadeEndereco(end.getSiglaEstadoCidade());
                om.setIdRegiaoEstadoCidadeEndereco(end.getIdRegiaoEstadoCidade());
                om.setNomeRegiaoEstadoCidadeEndereco(end.getNomeRegiaoEstadoCidade());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return om;
    }
    
    private final String GETOMS = "SELECT * " +
                                   "FROM " + tabela;
       
    public ArrayList<Om> getOms(){
        ArrayList<Om> oms = new ArrayList<>();                    
        ForcaDAO forcaDAO = new ForcaDAO();
        EstadoDAO estDAO = new EstadoDAO();
        EnderecoDAO endDAO = new EnderecoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Om om = new Om();    
                
                om.setId(rs.getInt("id"));
                om.setNome(rs.getString("nome"));
                om.setAbreviatura(rs.getString("abreviatura"));
                om.setNumendereco(rs.getString("numendereco"));

                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                om.setIdForca(forca.getId());
                om.setNomeForca(forca.getNome());
                om.setSiglaForca(forca.getSigla());
                om.setIdTipoForca(forca.getIdTipoForca());
                om.setNomeTipoForca(forca.getNomeTipoForca());

                Estado est = estDAO.getEstadoById(rs.getInt("idEstado"));
                om.setIdEstado(est.getId());
                om.setNomeEstado(est.getNome());
                om.setSiglaEstado(est.getSigla());
                om.setIdRegiaoEstado(est.getIdRegiao());
                om.setNomeRegiaoEstado(est.getNomeRegiao());

                Endereco end = endDAO.getEnderecoById(rs.getInt("idEndereco"));
                om.setIdEndereco(end.getId());
                om.setCepEndereco(end.getCep());
                om.setDescricaoEndereco(end.getDescricao());
                om.setComplementoEndereco(end.getComplemento());
                om.setPontoreferenciaEndereco(end.getPontoreferencia());
                om.setBairroEndereco(end.getBairro());
                om.setIdCidadeEndereco(end.getIdCidade());
                om.setNomeCidadeEndereco(end.getNomeCidade());
                om.setIdEstadoCidadeEndereco(end.getIdEstadoCidade());
                om.setNomeEstadoCidadeEndereco(end.getNomeEstadoCidade());
                om.setSiglaEstadoCidadeEndereco(end.getSiglaEstadoCidade());
                om.setIdRegiaoEstadoCidadeEndereco(end.getIdRegiaoEstadoCidade());
                om.setNomeRegiaoEstadoCidadeEndereco(end.getNomeRegiaoEstadoCidade());
                
                oms.add(om);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return oms;
    }
    
    private final static String GETOMBYFORCAANDESTADODWR = "SELECT * " +
                                                        "FROM OM " +
                                                        "WHERE idForca = ? AND idEstado = ? " + 
                                                        "ORDER BY nome";
    
    public static ArrayList<Om> getOmsByForcaAndEstadoDWR(int idForca, int idEstado){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Om> oms = new ArrayList<>();
        
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMBYFORCAANDESTADODWR);
            pstm.setInt(1, idForca);
            pstm.setInt(2, idEstado);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Om om = new Om();

                om.setId(rs.getInt("id"));
                om.setNome(rs.getString("nome"));
                om.setAbreviatura(rs.getString("abreviatura"));
                om.setNumendereco(rs.getString("numendereco"));
                om.setIdForca(rs.getInt("idForca"));
                om.setIdEstadoCidadeEndereco(rs.getInt("idEstado"));
                om.setIdEndereco(rs.getInt("idEndereco"));

                oms.add(om);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return oms;
    }
    
    private final static String GETOMSREGIAONORTEDWR = "SELECT om.* " +
                                                       "FROM OM AS om " +
                                                       "INNER JOIN Estado as e on om.idEstado = e.id " +
                                                       "INNER JOIN Regiao as r on e.idRegiao = r.id " +
                                                       "WHERE om.idForca = ? AND r.id = 1 " + 
                                                       "ORDER BY om.nome";
    
    public static ArrayList<Om> getOmsRegiaoNorteDWR(int idForca){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Om> oms = new ArrayList<>();        
        ForcaDAO forcaDAO = new ForcaDAO();
        EstadoDAO estDAO = new EstadoDAO();
        EnderecoDAO endDAO = new EnderecoDAO();
        
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMSREGIAONORTEDWR);
            pstm.setInt(1, idForca);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Om om = new Om();

                om.setId(rs.getInt("om.id"));
                om.setNome(rs.getString("om.nome"));
                om.setAbreviatura(rs.getString("om.abreviatura"));
                om.setNumendereco(rs.getString("om.numendereco"));

                Forca forca = forcaDAO.getForcaById(rs.getInt("om.idForca"));
                om.setIdForca(forca.getId());
                om.setNomeForca(forca.getNome());
                om.setSiglaForca(forca.getSigla());
                om.setIdTipoForca(forca.getIdTipoForca());
                om.setNomeTipoForca(forca.getNomeTipoForca());

                Estado est = estDAO.getEstadoById(rs.getInt("om.idEstado"));
                om.setIdEstado(est.getId());
                om.setNomeEstado(est.getNome());
                om.setSiglaEstado(est.getSigla());
                om.setIdRegiaoEstado(est.getIdRegiao());
                om.setNomeRegiaoEstado(est.getNomeRegiao());
               
                Endereco end = endDAO.getEnderecoById(rs.getInt("om.idEndereco"));
                om.setIdEndereco(end.getId());
                om.setCepEndereco(end.getCep());
                om.setDescricaoEndereco(end.getDescricao());
                om.setComplementoEndereco(end.getComplemento());
                om.setPontoreferenciaEndereco(end.getPontoreferencia());
                om.setBairroEndereco(end.getBairro());
                om.setIdCidadeEndereco(end.getIdCidade());
                om.setNomeCidadeEndereco(end.getNomeCidade());
                om.setIdEstadoCidadeEndereco(end.getIdEstadoCidade());
                om.setNomeEstadoCidadeEndereco(end.getNomeEstadoCidade());
                om.setSiglaEstadoCidadeEndereco(end.getSiglaEstadoCidade());
                om.setIdRegiaoEstadoCidadeEndereco(end.getIdRegiaoEstadoCidade());
                om.setNomeRegiaoEstadoCidadeEndereco(end.getNomeRegiaoEstadoCidade());

                oms.add(om);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return oms;
    }
    
    private final static String GETOMBYIDDWR = "SELECT * " +
                                            "FROM OM " +
                                            "WHERE id = ?;";
    
    public static Om getOmByIdDWR(int idOM){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Om om = new Om();
        ForcaDAO forcaDAO = new ForcaDAO();
        EstadoDAO estDAO = new EstadoDAO();
        EnderecoDAO endDAO = new EnderecoDAO();
        
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETOMBYIDDWR);
            pstm.setInt(1, idOM);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                om.setId(rs.getInt("id"));
                om.setNome(rs.getString("nome"));
                om.setAbreviatura(rs.getString("abreviatura"));
                om.setNumendereco(rs.getString("numendereco"));

                Forca forca = forcaDAO.getForcaById(rs.getInt("idForca"));
                om.setIdForca(forca.getId());
                om.setNomeForca(forca.getNome());
                om.setSiglaForca(forca.getSigla());
                om.setIdTipoForca(forca.getIdTipoForca());
                om.setNomeTipoForca(forca.getNomeTipoForca());

                Estado est = estDAO.getEstadoById(rs.getInt("om.idEstado"));
                om.setIdEstado(est.getId());
                om.setNomeEstado(est.getNome());
                om.setSiglaEstado(est.getSigla());
                om.setIdRegiaoEstado(est.getIdRegiao());
                om.setNomeRegiaoEstado(est.getNomeRegiao());
               
                Endereco end = endDAO.getEnderecoById(rs.getInt("idEndereco"));
                om.setIdEndereco(end.getId());
                om.setCepEndereco(end.getCep());
                om.setDescricaoEndereco(end.getDescricao());
                om.setComplementoEndereco(end.getComplemento());
                om.setPontoreferenciaEndereco(end.getPontoreferencia());
                om.setBairroEndereco(end.getBairro());
                om.setIdCidadeEndereco(end.getIdCidade());
                om.setNomeCidadeEndereco(end.getNomeCidade());
                om.setIdEstadoCidadeEndereco(end.getIdEstadoCidade());
                om.setNomeEstadoCidadeEndereco(end.getNomeEstadoCidade());
                om.setSiglaEstadoCidadeEndereco(end.getSiglaEstadoCidade());
                om.setIdRegiaoEstadoCidadeEndereco(end.getIdRegiaoEstadoCidade());
                om.setNomeRegiaoEstadoCidadeEndereco(end.getNomeRegiaoEstadoCidade());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return om;
    }
}
