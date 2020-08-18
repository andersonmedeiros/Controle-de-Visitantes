/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Usuario;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.GrupoAcesso;
import model.bean.PostoGraduacao;

/**
 *
 * @author anderson
 */
public class UsuarioDAO {
    //Tabela
    String tabela = "Usuario";
    
    //Atributos
    String identidade = "identidade";
    String nome = "nome";
    String sobrenome = "sobrenome";
    String nomeguerra = "nomeguerra";
    String senha = "senha";
    String situacao = "situacao";
    String idPostoGraduacao = "idPostoGraduacao";
    String idGrupoAcesso = "idGrupoAcesso";
    
    //Insert SQL
    private final String INSERT = "INSERT INTO " + tabela + "(" + identidade + "," + nome + "," + sobrenome +  "," + nomeguerra + ","  + senha +  "," + situacao + "," + idPostoGraduacao + "," +idGrupoAcesso + ")" +
                                  " VALUES(?,?,?,?,md5(?),?,?,?);";
    
    //Update SQL
    private final String UPDATE = "UPDATE " + tabela +
                                  " SET " + nome + "=?, " + sobrenome + "=?, " + nomeguerra + "=?, " + idPostoGraduacao + "=? " +
                                  "WHERE " + identidade + "=?;";
    
    private final String UPDATESENHA = "UPDATE " + tabela +
                                  " SET " + senha + "=md5(?) " +
                                  "WHERE " + identidade + "=?;";
    
    private final String UPDATESITUACAO = "UPDATE " + tabela +
                                  " SET " + situacao + "=? " +
                                  "WHERE " + identidade + "=?;";
    
    private final String UPDATEGRUPOACESSO = "UPDATE " + tabela +
                                  " SET " + idGrupoAcesso + "=? " +
                                  "WHERE " + identidade + "=?;";
        
    //Delete SQL
    private final String DELETE = "DELETE FROM " + tabela + " WHERE " + identidade + "=?;";
    
    //Consultas SQL
    
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    //Insert SQL
    public void insert(Usuario usu) {
        if (usu != null) {
            try {
                conn = ConnectionFactory.getConnection();
                
                pstm = conn.prepareStatement(INSERT);
                
                pstm.setString(1, usu.getIdentidade());
                pstm.setString(2, usu.getNome());
                pstm.setString(3, usu.getSobrenome());
                pstm.setString(4, usu.getNomeguerra());
                pstm.setString(5, usu.getSenha());
                pstm.setInt(6, usu.getSituacao());
                pstm.setInt(7, usu.getIdPostoGraduacao());
                pstm.setInt(8, usu.getIdGrupoAcesso());
                
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
    public void update(Usuario usu) {
        if (usu != null) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATE); 
                
                pstm.setString(1, usu.getNome());
                pstm.setString(2, usu.getSobrenome());
                pstm.setString(3, usu.getNomeguerra());
                pstm.setInt(4, usu.getIdPostoGraduacao());
                pstm.setString(5, usu.getIdentidade());
                
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
    public void updateSenha(String senha, String idtUsuario) {
        if (senha != "") {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATESENHA); 
                
                pstm.setString(1, senha);
                pstm.setString(2, idtUsuario);
                
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
    public void updateGrupoAcesso(int idGrupoAcesso, String idtUsuario) {
        if (idGrupoAcesso >= 0) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATEGRUPOACESSO); 
                
                pstm.setInt(1, idGrupoAcesso);
                pstm.setString(2, idtUsuario);
                
                pstm.execute();
                ConnectionFactory.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());  
            }
        } else {            
            throw new RuntimeException();
        }
    }
    public void updateSituacao(int situacao, String idtUsuario) {
        if (situacao >= 0) {
            try {
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(UPDATESITUACAO); 
                
                pstm.setInt(1, situacao);
                pstm.setString(2, idtUsuario);
                
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
    
    private final String GETUSUARIOBYIDT = "SELECT * " +
                                            "FROM Usuario " + 
                                            "WHERE identidade = ?";
       
    public Usuario getUsuarioByIdentidade(String identidade){
        Usuario usu = new Usuario();
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        GrupoAcessoDAO grpacessoDAO = new GrupoAcessoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETUSUARIOBYIDT);
            pstm.setString(1, identidade);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                usu.setIdentidade(rs.getString("identidade"));
                usu.setNome(rs.getString("nome"));
                usu.setSobrenome(rs.getString("sobrenome"));                         
                usu.setNomeguerra(rs.getString("nomeguerra"));            
                usu.setSenha(rs.getString("senha"));                        
                usu.setSituacao(rs.getInt("situacao"));                        
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                usu.setIdPostoGraduacao(pg.getId());
                usu.setNomePostoGraduacao(pg.getNome());
                usu.setAbreviaturaPostoGraduacao(pg.getAbreviatura());             
                usu.setIdForcaPostoGraduacao(pg.getIdForca());
                usu.setNomeForcaPostoGraduacao(pg.getNomeForca());
                usu.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                usu.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                usu.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                GrupoAcesso grpacesso = grpacessoDAO.getGrupoAcessoById(rs.getInt("idGrupoAcesso"));
                usu.setIdGrupoAcesso(grpacesso.getId());
                usu.setNomeGrupoAcesso(grpacesso.getNome());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return usu;
    }  
    
    private final String GETUSUARIOS = "SELECT * " +
                                        "FROM " + tabela;
       
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();  
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        GrupoAcessoDAO grpacessoDAO = new GrupoAcessoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETUSUARIOS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario usu = new Usuario();
                
                usu.setIdentidade(rs.getString("identidade"));
                usu.setNome(rs.getString("nome"));
                usu.setSobrenome(rs.getString("sobrenome"));                         
                usu.setNomeguerra(rs.getString("nomeguerra"));            
                usu.setSenha(rs.getString("senha"));     
                usu.setSituacao(rs.getInt("situacao"));   
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                usu.setIdPostoGraduacao(pg.getId());
                usu.setNomePostoGraduacao(pg.getNome());
                usu.setAbreviaturaPostoGraduacao(pg.getAbreviatura());               
                usu.setIdForcaPostoGraduacao(pg.getIdForca());
                usu.setNomeForcaPostoGraduacao(pg.getNomeForca());
                usu.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                usu.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                usu.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                GrupoAcesso grpacesso = grpacessoDAO.getGrupoAcessoById(rs.getInt("idGrupoAcesso"));
                usu.setIdGrupoAcesso(grpacesso.getId());
                usu.setNomeGrupoAcesso(grpacesso.getNome());
                
                usuarios.add(usu);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return usuarios;
    }
    private final String GETUSUARIOSATIVOS = "SELECT * " +
                                        "FROM " + tabela + " WHERE situacao = 1";
       
    public ArrayList<Usuario> getUsuariosAtivos(){
        ArrayList<Usuario> usuarios = new ArrayList<>();  
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        GrupoAcessoDAO grpacessoDAO = new GrupoAcessoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETUSUARIOSATIVOS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario usu = new Usuario();
                
                usu.setIdentidade(rs.getString("identidade"));
                usu.setNome(rs.getString("nome"));
                usu.setSobrenome(rs.getString("sobrenome"));                         
                usu.setNomeguerra(rs.getString("nomeguerra"));            
                usu.setSenha(rs.getString("senha"));     
                usu.setSituacao(rs.getInt("situacao"));   
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                usu.setIdPostoGraduacao(pg.getId());
                usu.setNomePostoGraduacao(pg.getNome());
                usu.setAbreviaturaPostoGraduacao(pg.getAbreviatura());               
                usu.setIdForcaPostoGraduacao(pg.getIdForca());
                usu.setNomeForcaPostoGraduacao(pg.getNomeForca());
                usu.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                usu.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                usu.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                GrupoAcesso grpacesso = grpacessoDAO.getGrupoAcessoById(rs.getInt("idGrupoAcesso"));
                usu.setIdGrupoAcesso(grpacesso.getId());
                usu.setNomeGrupoAcesso(grpacesso.getNome());
                
                usuarios.add(usu);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return usuarios;
    }
    private final String GETUSUARIOSINATIVOS = "SELECT * " +
                                        "FROM " + tabela + " WHERE situacao = 0";
       
    public ArrayList<Usuario> getUsuarioesInativos(){
        ArrayList<Usuario> usuarios = new ArrayList<>();  
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        GrupoAcessoDAO grpacessoDAO = new GrupoAcessoDAO();
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETUSUARIOSINATIVOS);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario usu = new Usuario();
                
                usu.setIdentidade(rs.getString("identidade"));
                usu.setNome(rs.getString("nome"));
                usu.setSobrenome(rs.getString("sobrenome"));                         
                usu.setNomeguerra(rs.getString("nomeguerra"));            
                usu.setSenha(rs.getString("senha"));     
                usu.setSituacao(rs.getInt("situacao"));   
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                usu.setIdPostoGraduacao(pg.getId());
                usu.setNomePostoGraduacao(pg.getNome());
                usu.setAbreviaturaPostoGraduacao(pg.getAbreviatura());                
                usu.setIdForcaPostoGraduacao(pg.getIdForca());
                usu.setNomeForcaPostoGraduacao(pg.getNomeForca());
                usu.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                usu.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                usu.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                GrupoAcesso grpacesso = grpacessoDAO.getGrupoAcessoById(rs.getInt("idGrupoAcesso"));
                usu.setIdGrupoAcesso(grpacesso.getId());
                usu.setNomeGrupoAcesso(grpacesso.getNome());
                
                usuarios.add(usu);
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return usuarios;
    }
    
    private final static String GETUSUARIOBYIDTDWR = "SELECT * FROM Usuario WHERE identidade = ?";
       
    public static Usuario getUsuarioByIdentidadeDWR(String identidade){
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        GrupoAcessoDAO grpacessoDAO = new GrupoAcessoDAO();
        Usuario usu = new Usuario();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETUSUARIOBYIDTDWR);
            pstm.setString(1, identidade);
           
            rs = pstm.executeQuery();
            while (rs.next()) {       
                usu.setIdentidade(rs.getString("identidade"));
                usu.setNome(rs.getString("nome"));
                usu.setSobrenome(rs.getString("sobrenome"));                         
                usu.setNomeguerra(rs.getString("nomeguerra"));            
                usu.setSenha(rs.getString("senha"));     
                usu.setSituacao(rs.getInt("situacao"));   
                
                PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                usu.setIdPostoGraduacao(pg.getId());
                usu.setNomePostoGraduacao(pg.getNome());
                usu.setAbreviaturaPostoGraduacao(pg.getAbreviatura());              
                usu.setIdForcaPostoGraduacao(pg.getIdForca());
                usu.setNomeForcaPostoGraduacao(pg.getNomeForca());
                usu.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                usu.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                usu.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());
                
                GrupoAcesso grpacesso = grpacessoDAO.getGrupoAcessoById(rs.getInt("idGrupoAcesso"));
                usu.setIdGrupoAcesso(grpacesso.getId());
                usu.setNomeGrupoAcesso(grpacesso.getNome());
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());           
        }
        return usu;
    }
    
    //Consultas SQL
    private final String GETIDTSENHA = "SELECT " + identidade + "," + senha + " FROM " + tabela + " WHERE " + identidade + "=? AND " + senha + "=md5(?)";
    private final String GETSENHA = "SELECT " + senha + " FROM " + tabela + " WHERE " + senha + "=md5(?)";
    private final String GETIDENTIDADE = "SELECT "+ identidade + " FROM "+ tabela + " WHERE " + identidade + "=?";
    
    //Validação de Login(Identidade) e senha
    public boolean validarLoginSenha(String identidade, String senha) {
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETIDTSENHA);
            pstm.setString(1, identidade);
            pstm.setString(2, senha);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());            
        }
        return false;
    }
    
    //Validação de Login(Identidade)
    public boolean validarLogin(String identidade) {       
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETIDENTIDADE);
            pstm.setString(1, identidade);
            
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());            
        }
        return false;
    }
    
    //Validação de Senha
    public boolean validarSenha(String senha) {
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(GETSENHA);
            
            pstm.setString(1, senha);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
            ConnectionFactory.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());            
        }
        return false;
    }
    
    //Recupera os dodos do usuario que está logando
    public Usuario autenticacao(Usuario usu){
        Usuario usuRetorno = null;
        PostoGraduacaoDAO pgDAO = new PostoGraduacaoDAO();
        GrupoAcessoDAO grpacessoDAO = new GrupoAcessoDAO();
        if(usu != null){
            conn = null;
            try{
                conn = ConnectionFactory.getConnection();
                pstm = conn.prepareStatement(GETUSUARIOBYIDT);
                pstm.setString(1, usu.getIdentidade());
                
                rs = pstm.executeQuery();
                
                if(rs.next()){
                    usuRetorno = new Usuario();
                    
                    usuRetorno.setIdentidade(rs.getString("identidade"));
                    usuRetorno.setNome(rs.getString("nome"));
                    usuRetorno.setSobrenome(rs.getString("sobrenome"));                         
                    usuRetorno.setNomeguerra(rs.getString("nomeguerra"));            
                    usuRetorno.setSenha(rs.getString("senha"));     
                    usuRetorno.setSituacao(rs.getInt("situacao"));   

                    PostoGraduacao pg = pgDAO.getPostoGraduacaoById(rs.getInt("idPostoGraduacao"));
                    usuRetorno.setIdPostoGraduacao(pg.getId());
                    usuRetorno.setNomePostoGraduacao(pg.getNome());
                    usuRetorno.setAbreviaturaPostoGraduacao(pg.getAbreviatura());              
                    usuRetorno.setIdForcaPostoGraduacao(pg.getIdForca());
                    usuRetorno.setNomeForcaPostoGraduacao(pg.getNomeForca());
                    usuRetorno.setSiglaForcaPostoGraduacao(pg.getSiglaForca());
                    usuRetorno.setIdTipoForcaPostoGraduacao(pg.getIdTipoForca());
                    usuRetorno.setNomeTipoForcaPostoGraduacao(pg.getNomeTipoForca());

                    GrupoAcesso grpacesso = grpacessoDAO.getGrupoAcessoById(rs.getInt("idGrupoAcesso"));
                    usuRetorno.setIdGrupoAcesso(grpacesso.getId());
                    usuRetorno.setNomeGrupoAcesso(grpacesso.getNome());
                }
                
                ConnectionFactory.fechaConexao(conn, pstm, rs);
            }catch(SQLException e){
                throw new RuntimeException("Erro: " + e.getMessage());
            }
        }
        return usuRetorno;
    }
}
