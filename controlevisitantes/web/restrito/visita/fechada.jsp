<%-- 
    Document   : fechada
    Created on : 21/08/2020, 22:35:48
    Author     : CB Anderson de Paula Andrade Medeiros
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.dao.*"%>
<%@page import="model.bean.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Anderson de Paula Andrade Medeiros">
        
        <link rel="icon" type="image/x-icon" href="../../assets/img/brasaocigs.png" />
            <title>Visitas Fechadas</title>
        
        <link rel="stylesheet" type="text/css" href="../../assets/node_modules/bootstrap/compiler/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="../../assets/css/estilo_universal.css">
        <link rel="stylesheet" type="text/css" href="../../assets/css/usuario/estiloCadastro.css">
        <link rel="stylesheet" type="text/css" href="../../assets/css/estilo_cadastroCurso.css">   
        
        <script type='text/javascript' src='../../dwr/engine.js'></script>
        <script type='text/javascript' src='../../dwr/interface/FacadeAjax.js'></script>
        <script type='text/javascript' src='../../dwr/util.js'></script> 
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-success">
                <a class="navbar-brand active" href="../../restrito/inicial.jsp"><img src="../../assets/img/brasaocigs.png" width="30px" height="35px"><span class="sr-only">SGDis</span></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerSgdis" aria-controls="navbarTogglerSgdis" aria-expanded="false" aria-label="Alterna navegação">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerSgdis">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">          
                        <%
                            HttpSession sessao = request.getSession();
                            Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioAutenticado");
                
                            if(usuarioLogado.getIdGrupoAcesso()== 1){
                                out.println(
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Usuários"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/usuario/ativos.jsp\">Listar Usuários Ativos</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/usuario/inativos.jsp\">Listar Usuários Inativos</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/usuario/cadastro.jsp\">Novo Usuário</a>"+
                                                "</div>"+
                                            "</li>"+
                                                    
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Manutenção"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                  
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/organizacaomilitar/om.jsp\">Organização Militar</a>"+              
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/divisaosecao/divisaosecao.jsp\">Divisão/Seção</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/setor/setor.jsp\">Setores</a>"+
                                                "</div>"+
                                            "</li>"+
                                            
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitantes"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/civil.jsp\">Listar Visitantes Civis</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/militar.jsp\">Listar Visitantes Militares</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/cadastro.jsp\">Novo Visitante</a>"+
                                                "</div>"+
                                            "</li>"+  
                                            
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle active\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitas"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/aberta.jsp\">Visitas Abertas</a>"+
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/visita/fechada.jsp\">Visitas Fechadas</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/cadastro.jsp\">Nova Visita</a>"+
                                                "</div>"+
                                            "</li>"
                                            );
                            }
                            else if(usuarioLogado.getIdGrupoAcesso() == 2){
                                out.println(
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitantes"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/civil.jsp\">Listar Visitantes Civis</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/militar.jsp\">Listar Visitantes Militares</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/cadastro.jsp\">Novo Visitante</a>"+
                                                "</div>"+
                                            "</li>"+
                                                    
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle active\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitas"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/aberta.jsp\">Visitas Abertas</a>"+
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/visita/fechada.jsp\">Visitas Fechadas</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/cadastro.jsp\">Nova Visita</a>"+
                                                "</div>"+
                                            "</li>"
                                );
                            }
                        %> 
                    </ul>
                    <form class="form-inline my-2 my-lg-0" name="formSair" action="Sair" method="POST">                  
                        <button class="btn btn-danger my-2 my-sm-0" type="submit">Sair</button>
                    </form>
                </div>
            </nav>
        </header>
        <section class="container-fluid col-md-10 mr-auto ml-auto area">
            <center>
                <div class="col-md-12 mb-3">
                    <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#modalFormRelaPdf">Gerar Relatório PDF</button>
                </div>
            </center>
            
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">NOME COMPLETO</th>
                        <th scope="col">ENTRADA</th>
                        <th scope="col">SAÍDA</th>
                        <th scope="col">DESTINO</th>
                    </tr>
                </thead>
                <tbody>
                    <%              
                        VisitaDAO visitaDAO = new VisitaDAO();
                        ArrayList<Visita> visitasfechadas = visitaDAO.getVisitasFechadas();
                        int i=1;
                        
                        if(visitasfechadas.size() == 0){
                            out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                            out.println("       Nenhuma visita fechada!");
                            out.println("</div>");
                        }
                        else{
                            for(Visita visita : visitasfechadas){
                                out.println("<tr>");
                                out.println("   <th scope=\"row\">"+ i +"</th>");
                                out.println("   <td>"+ visita.getNomeVisitante() + " " + visita.getSobrenomeVisitante() +"</td>");
                                out.println("   <td style=\"color: #125e12;\">"+ visita.getDataEntrada() + " " + visita.getHoraEntrada() +"</td>");             
                                out.println("   <td style=\"color: #dc0000;\">"+ visita.getDataSaida() + " " + visita.getHoraSaida() +"</td>");             
                                out.println("   <td>"+ visita.getNomeSetor() +"</td>"); 
                                out.println("</tr>");
                                i++;
                            }
                        }
                    %>
                </tbody>
            </table> 
                
            <!-- Modal Cadastro-->
            <div class="modal fade" id="modalFormRelaPdf" tabindex="-1" role="dialog" aria-labelledby="modalFormRelaPdf" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title title" id="modalFormRelaPdf">Relatório em Pdf</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">                            
                            <form class="container-fluid" action="controller.relatorio/GerarRelatorioPdf" method="POST" name="formCadastrar" target="_blank">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtTipoRela">Relatório de: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtTipoRela" name="txtTipoRela">
                                            <option value="0" selected>Selecione o relatório desejado...</option>
                                            <option value="1">ENTRADA/SAÍDA DE MILITARES DE OUTRAS OM</option>
                                            <option value="2">ENTRADA/SAÍDA DE CIVIS</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>  
                                    <div class="form-group col-md-6">
                                        <label for="txtData">Data: <span class="campo-obrigatorio">*</span></label>
                                        <input type="date" class="form-control" id="txtData" name="txtData">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div> 
                                </div>
                                <button type="button" name="btnLimpar" class="btn btn-warning">Limpar</button>
                                <button type="submit" name="btnGerarRelatorio" class="btn btn-success">Gerar Relatório</button>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" name="btnFecharCadastro" class="btn btn-danger" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <footer class="container-fluid bg-success text-center fixed-bottom">
            <b>Desenvolvido por CB Anderson de Paula Andrade Medeiros</b><br>
            <b class="text-center copy">&copy; 2020</b>
        </footer>

        <script src="../../assets/node_modules/jquery/dist/jquery.js"></script>
        <script src="../../assets/node_modules/popper.js/dist/popper.js"></script>
        <script src="../../assets/node_modules/bootstrap/dist/js/bootstrap.js"></script>
        
        <script src="../../assets/js/dwr/visita/cadastro/camposDinamicos.js"></script>

        <script src="../../assets/js/formulario/validacao/visita/funcoesImportantes.js"></script>
        <script src="../../assets/js/formulario/validacao/visita/cadastro.js"></script> 
        <script src="../../assets/js/formulario/validacao/visita/relatorio.js"></script> 
        <script src="../../assets/js/bootstrap-validate.js"></script>
    </body>
</html>

