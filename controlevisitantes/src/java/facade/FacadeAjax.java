/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.ArrayList;
import model.bean.*;
import model.dao.*;
/**
 *
 * @author anderson
 */
public class FacadeAjax {
    public ArrayList<TipoForca> getTiposForcaDWR() throws Throwable, Exception{
        ArrayList<TipoForca> tiposforca = new ArrayList<>();
        tiposforca = TipoForcaDAO.getTiposForcaDWR();
        return tiposforca;
    }
    public ArrayList<Forca> getForcasDWR() throws Throwable, Exception{
        ArrayList<Forca> forcas = new ArrayList<>();
        forcas = ForcaDAO.getForcasDWR();
        return forcas;
    }
    public Forca getForcaByIdDWR(int idForca) throws Throwable, Exception{
        Forca forca = ForcaDAO.getForcaByIdDWR(idForca);
        return forca;
    }
    public ArrayList<Forca> getForcasByIdTipoForcaDWR(int idTipoForca) throws Throwable, Exception{
        ArrayList<Forca> forcas = new ArrayList<>();
        forcas = ForcaDAO.getForcasByIdTipoForcaDWR(idTipoForca);
        return forcas;
    }
    public ArrayList<Om> getOmsByForcaDWR(int idForca) throws Throwable, Exception{
        return OmDAO.getOmsByForcaDWR(idForca);
    }
    public ArrayList<PostoGraduacao> getPGsByForcaDWR(int idForca) throws Throwable, Exception{
        ArrayList<PostoGraduacao> pgs = new ArrayList<>();
        pgs = PostoGraduacaoDAO.getPGsByForcaDWR(idForca);
        return pgs;
    }
    public Om getOmByIdDWR(int idOM) throws Throwable, Exception{
        return OmDAO.getOmByIdDWR(idOM);
    } 
    public Setor getSetorByIdDWR(int idSetor) throws Throwable, Exception{
        return SetorDAO.getSetorByIdDWR(idSetor);
    } 
    public Usuario getUsuarioByIdentidadeDWR(String identidade){
        return UsuarioDAO.getUsuarioByIdentidadeDWR(identidade);
    }
    public DivisaoSecao getDivisaoSecaoByIdDWR(int idDivisaoSecao){
        return DivisaoSecaoDAO.getDivisaoSecaoByIdDWR(idDivisaoSecao);
    }    
    public ArrayList<DivisaoSecao> getDivisoesSecoesDWR() throws Throwable, Exception{
        return DivisaoSecaoDAO.getDivisoesSecoesDWR();
    }
    
    public Visitante getVisitanteByIdentidadeDWR(String identidade){
        return VisitanteDAO.getVisitanteByIdentidadeDWR(identidade);
    }
    
    public ArrayList<Setor> getSetoresDWR() throws Throwable, Exception{
        return SetorDAO.getSetoresDWR();
    }
}
