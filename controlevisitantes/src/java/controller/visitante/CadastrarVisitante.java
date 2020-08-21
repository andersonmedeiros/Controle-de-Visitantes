/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.visitante;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Visitante;
import model.dao.VisitanteDAO;

/**
 *
 * @author anderson
 */
public class CadastrarVisitante extends HttpServlet {

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
            out.println("<title>Servlet CadastrarVisitante</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastrarVisitante at " + request.getContextPath() + "</h1>");
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
        
        if(sessao.getAttribute("usuarioAutenticado") != null){
            try{
                VisitanteDAO visDAO = new VisitanteDAO();    
                
                Visitante vis = new Visitante();  
                
                if(Integer.parseInt(request.getParameter("txtTipoVisitante")) == 1){
                    vis.setTipo(Integer.parseInt(request.getParameter("txtTipoVisitante")));
                    vis.setIdentidade(String.valueOf(request.getParameter("txtIdentidadeMilitar").replace("-", "")));
                    vis.setIdPostoGraduacao(Integer.parseInt(request.getParameter("txtPGrad")));
                    vis.setIdOm(Integer.parseInt(request.getParameter("txtOM")));
                    vis.setNome(String.valueOf(request.getParameter("txtNomeMilitar")).toUpperCase());
                    vis.setSobrenome(String.valueOf(request.getParameter("txtSobrenomeMilitar")).toUpperCase());
                    vis.setNomeguerra(String.valueOf(request.getParameter("txtNomeGuerra")).toUpperCase());
                    vis.setEmail(String.valueOf(request.getParameter("txtEmailMilitar")).toUpperCase());
                    vis.setFone(String.valueOf(request.getParameter("txtFoneMilitar")).toUpperCase().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
                }
                else if(Integer.parseInt(request.getParameter("txtTipoVisitante")) == 2){
                    vis.setTipo(Integer.parseInt(request.getParameter("txtTipoVisitante")));
                    vis.setIdentidade(String.valueOf(request.getParameter("txtIdentidadeCivil").replace("-", "")));
                    vis.setNome(String.valueOf(request.getParameter("txtNomeCivil")).toUpperCase());
                    vis.setSobrenome(String.valueOf(request.getParameter("txtSobrenomeCivil")).toUpperCase());
                    vis.setEmail(String.valueOf(request.getParameter("txtEmailCivil")).toUpperCase());
                    vis.setFone(String.valueOf(request.getParameter("txtFoneCivil")).toUpperCase().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
                }
                
                System.out.println("Visitante: //");
                System.out.println("tipo: " + vis.getTipo());
                System.out.println("identidade: " + vis.getIdentidade());                
                System.out.println("pg: " + vis.getIdPostoGraduacao());
                System.out.println("om: " + vis.getIdOm());
                System.out.println("nome: " + vis.getNome());
                System.out.println("sobrenome: " + vis.getSobrenome());
                System.out.println("nomeguerra: " + vis.getNomeguerra());
                System.out.println("email: " + vis.getEmail());
                System.out.println("fone: " + vis.getFone());
                
                visDAO.insert(vis);

            }catch(Exception ex){
                //e=2: erro durante realização do cadastro
                response.sendRedirect("/controlevisitantes/restrito/visitante/cadastro.jsp?e=2");
                throw new ServletException(ex);
            }
            //e=1: cadastro sucesso
            response.sendRedirect("/controlevisitantes/restrito/visitante/cadastro.jsp?e=1");
        }
        else{
            //e=4: Sessão Encerrada
            response.sendRedirect("/controlevisitantes/index.jsp?e=4");
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
