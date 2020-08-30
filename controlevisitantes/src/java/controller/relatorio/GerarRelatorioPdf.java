/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.relatorio;

import connection.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.GeradorDeRelatorios;
import model.dao.VisitaDAO;

/**
 *
 * @author anderson
 */
public class GerarRelatorioPdf extends HttpServlet {

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
            out.println("<title>Servlet GerarRelatorioPdf</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GerarRelatorioPdf at " + request.getContextPath() + "</h1>");
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

        if((request.getParameter("txtTipoRela")!=null) && (request.getParameter("txtData")!=null)){
            VisitaDAO visitaDAO = new VisitaDAO();
            ServletContext contexto = getServletContext();
            
            // abre conexão com o banco
            Connection conexao = new ConnectionFactory().getConnection();
            // gera relatório
            GeradorDeRelatorios gerador = new GeradorDeRelatorios(conexao);
            
            // prepara parâmetros
            Map<String, Object> parametros = new HashMap<>();
            
            String sql_query = "";
            String jrxml = "";
            
            int tipo = Integer.parseInt(request.getParameter("txtTipoRela"));
            String dataSeparada[] = String.valueOf(request.getParameter("txtData")).split("-");
            Date data = new Date((Integer.parseInt(dataSeparada[0])-1900), (Integer.parseInt(dataSeparada[1]) - 1), (Integer.parseInt(dataSeparada[2])));
            
            //Controle de Entrada/Saída de militares de outras om
            if(tipo == 1){
                String sqlQtdeVisitas = "SELECT COUNT(vis.identidade) as qtdeVisitas " +
                                        "FROM Visita as v " +
                                        "INNER JOIN Visitante as vis on v.idtVisitante = vis.identidade " +
                                        "INNER JOIN PostoGraduacao as pg on vis.idPostoGraduacao = pg.id " +
                                        "INNER JOIN OrganizacaoMilitar as om on vis.idOrganizacaoMilitar = om.id " +
                                        "INNER JOIN Setor as s on v.idSetor = s.id " +
                                        "LEFT JOIN Veiculo as veiculo on v.idVeiculo = veiculo.id " +
                                        "WHERE v.dataSaida is not null AND v.horaSaida is not null AND " +
                                        "vis.tipo = " + tipo + " AND v.dataEntrada = " + "'" + data + "'" + " order by v.horaEntrada;";
                
                if(visitaDAO.getQtdeVisitas(sqlQtdeVisitas) == 0){
                    response.sendRedirect("/controlevisitantes/restrito/visita/fechada.jsp?e=1");
                }
                else{
                    sql_query = "SELECT pg.abreviatura as pg, vis.nome, vis.sobrenome, om.abreviatura as om, v.idtVisitante, v.dataEntrada, v.horaEntrada, v.dataSaida, v.horaSaida, s.abreviatura as destino, " +
                            "IFNULL(veiculo.marca, '-') as marca, IFNULL(veiculo.modelo, '-') as modelo, IFNULL(veiculo.cor, '-') as cor, IFNULL(veiculo.placa, '-') as placa " +
                            "FROM Visita as v " +
                            "INNER JOIN Visitante as vis on v.idtVisitante = vis.identidade " +
                            "INNER JOIN PostoGraduacao as pg on vis.idPostoGraduacao = pg.id " +
                            "INNER JOIN OrganizacaoMilitar as om on vis.idOrganizacaoMilitar = om.id " +
                            "INNER JOIN Setor as s on v.idSetor = s.id " +
                            "LEFT JOIN Veiculo as veiculo on v.idVeiculo = veiculo.id " +
                            "WHERE v.dataSaida is not null AND v.horaSaida is not null AND " +
                            "vis.tipo = " + tipo + " AND v.dataEntrada = " + "'" + data + "'" + " order by v.horaEntrada;";
                
                    jrxml = contexto.getRealPath("/relatorio/ControleMilitarOutrasOM.jrxml");
                }               
            }
            //Controle de Entrada/Saída de civis
            else if(tipo == 2){
                String sqlQtdeVisitas = "SELECT COUNT(vis.identidade) as qtdeVisitas " +
                                        "FROM Visita as v " +
                                        "INNER JOIN Visitante as vis on v.idtVisitante = vis.identidade " +
                                        "INNER JOIN Setor as s on v.idSetor = s.id " +
                                        "LEFT JOIN Veiculo as veiculo on v.idVeiculo = veiculo.id " +
                                        "WHERE v.dataSaida is not null and v.horaSaida is not null AND " +
                                        "vis.tipo = " + tipo + " AND v.dataEntrada = " + "'" + data + "'" + " order by v.horaEntrada;";
                
                if(visitaDAO.getQtdeVisitas(sqlQtdeVisitas) == 0){
                    response.sendRedirect("/controlevisitantes/restrito/visita/fechada.jsp?e=2");
                }
                else{
                    sql_query = "SELECT vis.nome, vis.sobrenome, v.idtVisitante, v.dataEntrada, v.horaEntrada, v.dataSaida, v.horaSaida, s.abreviatura as destino, " +
                                "IFNULL(veiculo.marca, '-') as marca, IFNULL(veiculo.modelo, '-') as modelo, IFNULL(veiculo.cor, '-') as cor, IFNULL(veiculo.placa, '-') as placa " +
                                "FROM Visita as v " +
                                "INNER JOIN Visitante as vis on v.idtVisitante = vis.identidade " +
                                "INNER JOIN Setor as s on v.idSetor = s.id " +
                                "LEFT JOIN Veiculo as veiculo on v.idVeiculo = veiculo.id " +
                                "WHERE v.dataSaida is not null and v.horaSaida is not null AND " +
                                "vis.tipo = " + tipo + " AND v.dataEntrada = " + "'" + data + "'" + " order by v.horaEntrada;";

                    jrxml = contexto.getRealPath("/relatorio/ControleCivis.jrxml");
                }
            }
            
            gerador.geraPdf(jrxml, parametros, response.getOutputStream(), sql_query);

            try {
                conexao.close(); // não esqueça de fechar a conexão
            } catch (SQLException ex) {
                Logger.getLogger(GerarRelatorioPdf.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else{
            response.sendRedirect("/controlevisitantes/restrito/visita/fechada.jsp");
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
