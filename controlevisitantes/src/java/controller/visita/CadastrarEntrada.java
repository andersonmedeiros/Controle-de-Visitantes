/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.visita;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.*;
import model.dao.*;

/**
 *
 * @author anderson
 */
public class CadastrarEntrada extends HttpServlet {

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
            out.println("<title>Servlet CadastrarEntrada</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastrarEntrada at " + request.getContextPath() + "</h1>");
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
                //Veiculo
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                int idVeiculo = 0;
                if(!request.getParameter("txtVeiculoEntrar").equals("n") && !request.getParameter("txtVeiculoEntrar").equals("0")){
                    Veiculo veiculo = new Veiculo();
                    
                    idVeiculo = veiculoDAO.proxID();
                    veiculo.setId(idVeiculo);
                    veiculo.setTipo(String.valueOf(request.getParameter("txtTipoVeiculo")).toUpperCase());
                    veiculo.setMarca(String.valueOf(request.getParameter("txtMarcaVeiculo")).toUpperCase());
                    veiculo.setModelo(String.valueOf(request.getParameter("txtModeloVeiculo")).toUpperCase());
                    veiculo.setCor(String.valueOf(request.getParameter("txtCorVeiculo")).toUpperCase());
                    veiculo.setPlaca(String.valueOf(request.getParameter("txtPlacaVeiculo")).toUpperCase());

                    veiculoDAO.insert(veiculo);      
                }                
                
                VisitaDAO visitaDAO = new VisitaDAO();                 
                Visita visita = new Visita();
                
                if(!request.getParameter("txtIdentidadeMilitar").equals("")){
                    visita.setIdentidadeVisitante(String.valueOf(request.getParameter("txtIdentidadeMilitar")).replace("-", ""));
                }else if(!request.getParameter("txtIdentidadeCivil").equals("")){
                    visita.setIdentidadeVisitante(String.valueOf(request.getParameter("txtIdentidadeCivil")).replace("-", ""));
                }
                                
                visita.setIdSetor(Integer.parseInt(request.getParameter("txtSetorVisitado")));
                visita.setCracha(Integer.parseInt(request.getParameter("txtCracha")));
                
                String dataEntrada[] = String.valueOf(request.getParameter("txtDataEntrada")).split("-");
                visita.setDataEntrada(new Date((Integer.parseInt(dataEntrada[0])-1900), (Integer.parseInt(dataEntrada[1]) - 1), Integer.parseInt(dataEntrada[2])));
                String horaEntrada[] = request.getParameter("txtHoraEntrada").split(":");
                visita.setHoraEntrada(new Time(Integer.parseInt(horaEntrada[0]), Integer.parseInt(horaEntrada[1]), Integer.parseInt(horaEntrada[2])));
                
                visita.setIdVeiculo(idVeiculo);
                visita.setFinalidade(String.valueOf(request.getParameter("txtFinalidade")));  
                
                visitaDAO.insertEntrada(visita);

            }catch(Exception ex){
                //e=2: erro durante realização do cadastro
                response.sendRedirect("/controlevisitantes/restrito/visita/cadastro.jsp?e=2");
                throw new ServletException(ex);
            }
            //e=1: cadastro sucesso
            response.sendRedirect("/controlevisitantes/restrito/visita/cadastro.jsp?e=1");
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
