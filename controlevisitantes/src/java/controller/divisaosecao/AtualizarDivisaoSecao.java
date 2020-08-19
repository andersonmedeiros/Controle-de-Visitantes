/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.divisaosecao;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.DivisaoSecao;
import model.dao.DivisaoSecaoDAO;

/**
 *
 * @author anderson
 */
public class AtualizarDivisaoSecao extends HttpServlet {

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
            out.println("<title>Servlet AtualizarDivisaoSecao</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AtualizarDivisaoSecao at " + request.getContextPath() + "</h1>");
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
                DivisaoSecaoDAO dsDAO = new DivisaoSecaoDAO();
                                              
                DivisaoSecao ds = new DivisaoSecao();  
                ds.setId(Integer.parseInt(request.getParameter("txtIdAtt")));                
                ds.setNome(String.valueOf(request.getParameter("txtNomeAtt")).toUpperCase());
                ds.setAbreviatura(String.valueOf(request.getParameter("txtAbreviaturaAtt")).toUpperCase());
                
                System.out.println("Divisao/Secao: Cad");
                System.out.println("id: " + ds.getId());
                System.out.println("nome: " + ds.getNome());
                System.out.println("abrev: " + ds.getAbreviatura());
                
                dsDAO.update(ds);

            }catch(Exception ex){
                //e=2: erro durante realização da atualização
                response.sendRedirect("/controlevisitantes/restrito/divisaosecao/divisaosecao.jsp?e=5");
                throw new ServletException(ex);
            }
            //e=1: atualização sucesso
            response.sendRedirect("/controlevisitantes/restrito/divisaosecao/divisaosecao.jsp?e=6");
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
