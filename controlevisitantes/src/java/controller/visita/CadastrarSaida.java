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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Visita;
import model.dao.VisitaDAO;

/**
 *
 * @author anderson
 */
public class CadastrarSaida extends HttpServlet {

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
            out.println("<title>Servlet CadastrarSaida</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastrarSaida at " + request.getContextPath() + "</h1>");
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
                VisitaDAO visitaDAO = new VisitaDAO();                 
                Visita visita = new Visita();
                    
                visita.setCpfVisitante(String.valueOf(request.getParameter("cpfVisitante")).replace(".", "").replace(".", "").replace("-", ""));                             
                visita.setIdSetor(Integer.parseInt(request.getParameter("idSetor")));
                
                String dataEntrada[] = String.valueOf(request.getParameter("dataEntrada")).split("-");
                visita.setDataEntrada(new Date((Integer.parseInt(dataEntrada[0])-1900), (Integer.parseInt(dataEntrada[1]) - 1), Integer.parseInt(dataEntrada[2])));
                String horaEntrada[] = request.getParameter("horaEntrada").split(":");
                visita.setHoraEntrada(new Time(Integer.parseInt(horaEntrada[0]), Integer.parseInt(horaEntrada[1]), Integer.parseInt(horaEntrada[2])));
                
                Calendar hoje = Calendar.getInstance();
                DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd"); 
                DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss"); 
                String dataSaidaSeparada[] = dataFormat.format(hoje.getTime()).split("-");
                String horaSaidaSeparada[] = horaFormat.format(hoje.getTime()).split(":");

                Date dataSaida = new Date(Integer.parseInt(dataSaidaSeparada[0]) - 1900, Integer.parseInt(dataSaidaSeparada[1]) - 1, Integer.parseInt(dataSaidaSeparada[2]));
                Time horaSaida = new Time(Integer.parseInt(horaSaidaSeparada[0]), Integer.parseInt(horaSaidaSeparada[1]), Integer.parseInt(horaSaidaSeparada[2]));
                
                visita.setDataSaida(dataSaida);
                visita.setHoraSaida(horaSaida);
                
                visitaDAO.insertSaida(visita);

            }catch(Exception ex){
                //e=2: erro durante realização do cadastro
                response.sendRedirect("/controlevisitantes/restrito/visita/aberta.jsp?e=2");
                throw new ServletException(ex);
            }
            //e=1: cadastro sucesso
            response.sendRedirect("/controlevisitantes/restrito/visita/aberta.jsp?e=1");
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
