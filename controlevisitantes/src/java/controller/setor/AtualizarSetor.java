/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.setor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Setor;
import model.dao.SetorDAO;

/**
 *
 * @author anderson
 */
public class AtualizarSetor extends HttpServlet {

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
            out.println("<title>Servlet AtualizarSetor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AtualizarSetor at " + request.getContextPath() + "</h1>");
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
                SetorDAO setorDAO = new SetorDAO();
                                              
                Setor setor = new Setor();  
                setor.setId(Integer.parseInt(request.getParameter("txtIdAtt")));                
                setor.setNome(String.valueOf(request.getParameter("txtNomeAtt")).toUpperCase());
                setor.setAbreviatura(String.valueOf(request.getParameter("txtAbreviaturaAtt")).toUpperCase());
                setor.setIdDivisaoSecao(Integer.parseInt(request.getParameter("txtDivisaoSecaoAtt")));     
                
                System.out.println("Setor: Cad");
                System.out.println("id: " + setor.getId());
                System.out.println("nome: " + setor.getNome());
                System.out.println("abrev: " + setor.getAbreviatura());
                System.out.println("iddivsec: " + setor.getIdDivisaoSecao());
                
                setorDAO.update(setor);

            }catch(Exception ex){
                //e=2: erro durante realização da atualização
                response.sendRedirect("/controlevisitantes/restrito/setor/setor.jsp?e=5");
                throw new ServletException(ex);
            }
            //e=1: atualização sucesso
            response.sendRedirect("/controlevisitantes/restrito/setor/setor.jsp?e=6");
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
