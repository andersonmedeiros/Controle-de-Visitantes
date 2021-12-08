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
public class AtualizarVisitante extends HttpServlet {

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
            out.println("<title>Servlet AtualizarVisitante</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AtualizarVisitante at " + request.getContextPath() + "</h1>");
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
                
                Visitante vis = new Visitante();                 
                
                if(Integer.parseInt(request.getParameter("txtTipoVisitante")) == 1){
                    vis.setTipo(Integer.parseInt(request.getParameter("txtTipoVisitante")));
                    vis.setCpf(String.valueOf(request.getParameter("txtCpfMilitarAtt").replace(".", "").replace(".","").replace("-", "")));
                    vis.setIdentidade(String.valueOf(request.getParameter("txtIdentidadeMilitarAtt").replace("-", "")));
                    vis.setIdPostoGraduacao(Integer.parseInt(request.getParameter("txtPGradAtt")));
                    vis.setIdOm(Integer.parseInt(request.getParameter("txtOMAtt")));
                    vis.setNome(String.valueOf(request.getParameter("txtNomeMilitarAtt")).toUpperCase());
                    vis.setSobrenome(String.valueOf(request.getParameter("txtSobrenomeMilitarAtt")).toUpperCase());
                    vis.setNomeguerra(String.valueOf(request.getParameter("txtNomeGuerraAtt")).toUpperCase());
                    vis.setEmail(String.valueOf(request.getParameter("txtEmailMilitarAtt")).toUpperCase());
                    vis.setFone(String.valueOf(request.getParameter("txtFoneMilitarAtt")).toUpperCase().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
                    pagina = "militar.jsp";
                }
                else if(Integer.parseInt(request.getParameter("txtTipoVisitante")) == 2){
                    vis.setTipo(Integer.parseInt(request.getParameter("txtTipoVisitante")));
                    vis.setCpf(String.valueOf(request.getParameter("txtCpfCivilAtt").replace(".", "").replace(".","").replace("-", "")));
                    vis.setIdentidade(String.valueOf(request.getParameter("txtIdentidadeCivilAtt").replace("-", "")));
                    vis.setNome(String.valueOf(request.getParameter("txtNomeCivilAtt")).toUpperCase());
                    vis.setSobrenome(String.valueOf(request.getParameter("txtSobrenomeCivilAtt")).toUpperCase());
                    vis.setEmail(String.valueOf(request.getParameter("txtEmailCivilAtt")).toUpperCase());
                    vis.setFone(String.valueOf(request.getParameter("txtFoneCivilAtt")).toUpperCase().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""));
                    pagina = "civil.jsp";
                }
                
                System.out.println("Visitante: //");
                System.out.println("tipo: " + vis.getTipo());
                System.out.println("identidade: " + vis.getCpf());                
                System.out.println("identidade: " + vis.getIdentidade());                
                System.out.println("pg: " + vis.getIdPostoGraduacao());
                System.out.println("om: " + vis.getIdOm());
                System.out.println("nome: " + vis.getNome());
                System.out.println("sobrenome: " + vis.getSobrenome());
                System.out.println("nomeguerra: " + vis.getNomeguerra());
                System.out.println("email: " + vis.getEmail());
                System.out.println("fone: " + vis.getFone());
                
                visDAO.update(vis);

            }catch(Exception ex){
                //e=2: erro durante realização da atualização
                response.sendRedirect("/controlevisitantes/restrito/visitante/"+pagina+"?e=6");
                throw new ServletException(ex);
            }
            //e=1: atualização sucesso
            response.sendRedirect("/controlevisitantes/restrito/visitante/"+pagina+"?e=5");
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
