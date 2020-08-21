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
import model.dao.VisitanteDAO;

/**
 *
 * @author anderson
 */
public class ExcluirVisitante extends HttpServlet {

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
            out.println("<title>Servlet ExcluirVisitante</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExcluirVisitante at " + request.getContextPath() + "</h1>");
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
            String pagina = "";
            try{
                VisitanteDAO visDAO = new VisitanteDAO();    
                int tipoVisitante = Integer.parseInt(request.getParameter("tipoVisitanteExcluir"));
                
                if(tipoVisitante == 1){
                    pagina = "militar.jsp";
                }else if(tipoVisitante == 2){
                    pagina = "civil.jsp";
                }
                
                visDAO.delete(String.valueOf(request.getParameter("idtVisitanteExcluir").replace("-", "")));

            }catch(Exception ex){
                //e=2: erro durante realização da atualização da senha
                response.sendRedirect("/controlevisitantes/restrito/visitante/"+pagina+"?e=4");
                throw new ServletException(ex);
            }
            //e=1: atualização senha sucesso
            response.sendRedirect("/controlevisitantes/restrito/visitante/"+pagina+"?e=3");
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
