/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Usuario;
import model.bean.MilitarHasGrupoAcesso;
import model.dao.UsuarioDAO;
import model.dao.MilitarHasGrupoAcessoDAO;

/**
 *
 * @author anderson
 */
public class CadastrarUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastrarUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastrarUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession sessao = request.getSession();
        
        if(sessao.getAttribute("militarAutenticado") != null){
            try{
                UsuarioDAO milDAO = new UsuarioDAO();    
                
                Usuario mil = new Usuario();  
                mil.setIdPostoGraduacao(Integer.parseInt(request.getParameter("txtPGrad")));
                mil.setIdentidade(String.valueOf(request.getParameter("txtIdentidade").replace("-", "")));
                mil.setNome(String.valueOf(request.getParameter("txtNome")).toUpperCase());
                mil.setSobrenome(String.valueOf(request.getParameter("txtSobrenome")).toUpperCase());
                mil.setNomeguerra(String.valueOf(request.getParameter("txtNomeGuerra")).toUpperCase());
                mil.setNgs(Integer.parseInt(request.getParameter("txtNGS")));
                mil.setSenha(String.valueOf(request.getParameter("txtSenha")));          
                mil.setSituacao(1);   
                
                milDAO.insert(mil);
                
                MilitarHasGrupoAcessoDAO milgrpacessoDAO = new MilitarHasGrupoAcessoDAO();
                String[] gruposAcesso = request.getParameterValues("txtGrupoAcesso");
                for (String ga : gruposAcesso) {
                    MilitarHasGrupoAcesso milgrpacesso = new MilitarHasGrupoAcesso();
                    milgrpacesso.setIdentidadeMilitar(mil.getIdentidade());
                    milgrpacesso.setIdGrpAcesso(Integer.parseInt(ga));
                    milgrpacessoDAO.insert(milgrpacesso);
                }

            }catch(Exception ex){
                //e=2: erro durante realização do cadastro
                response.sendRedirect("/sgdis/restrito/usuario/cadastro.jsp?e=2");
                throw new ServletException(ex);
            }
            //e=1: cadastro sucesso
            response.sendRedirect("/sgdis/restrito/usuario/cadastro.jsp?e=1");
        }
        else{
            //e=4: Sessão Encerrada
            response.sendRedirect("/sgdis/index.jsp?e=4");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
