<%-- 
    Document   : inativos
    Created on : 01/08/2020, 11:27:47
    Author     : CB Anderson de Paula Andrade Medeiros
--%>

<%@page import="model.dao.MilitarDAO"%>
<%@page import="model.dao.GrupoAcessoDAO"%>
<%@page import="model.bean.GrupoAcesso"%>
<%@page import="model.dao.MilitarHasGrupoAcessoDAO"%>
<%@page import="model.bean.Militar"%>
<%@page import="model.bean.CursoHasCategoria"%>
<%@page import="model.dao.CursoHasCategoriaDAO"%>
<%@page import="model.dao.CategoriaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Curso"%>
<%@page import="model.dao.CursoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Anderson de Paula Andrade Medeiros">
        
        <link rel="icon" type="image/x-icon" href="../../assets/img/logo_sgdis.png" />
        <title>Usuário</title>
        
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
                <a class="navbar-brand active" href="../../restrito/inicial.jsp"><img src="../../assets/img/logo_sgdis.png" width="30px" height="35px"><span class="sr-only">SGDis</span></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerSgdis" aria-controls="navbarTogglerSgdis" aria-expanded="false" aria-label="Alterna navegação">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerSgdis">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">          
                        <li class="nav-item">
                            <a class="nav-link" href="../../restrito/cursos.jsp">Cursos</a>
                        </li>
                        <%
                            HttpSession sessao = request.getSession();
                            Militar militarLogado = (Militar) sessao.getAttribute("militarAutenticado");
                            MilitarHasGrupoAcessoDAO milgrpacessoDAO = new MilitarHasGrupoAcessoDAO();
                            ArrayList<GrupoAcesso> grpsacesso = milgrpacessoDAO.getGruposAcesso(militarLogado.getIdentidade());
                            for(GrupoAcesso ga : grpsacesso){
                                if(ga.getId() == 1){
                                    out.println(
                                                "<li class=\"nav-item dropdown\">"+
                                                    "<a class=\"nav-link dropdown-toggle active\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                        "Usuários"+
                                                    "</a>"+
                                                    "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                        "<a class=\"dropdown-item\" href=\"../../restrito/usuario/ativos.jsp\">Listar Usuários Ativos</a>"+
                                                        "<a class=\"dropdown-item active\" href=\"../../restrito/usuario/inativos.jsp\">Listar Usuários Inativos</a>"+
                                                        "<div class=\"dropdown-divider\"></div>"+
                                                        "<a class=\"dropdown-item\" href=\"../../restrito/usuario/cadastro.jsp\">Novo Usuário</a>"+
                                                    "</div>"+                                                    
                                                "</li>"
                                                );
                                }
                            }
                        %>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Avaliações
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <%
                                    for(GrupoAcesso ga : grpsacesso){                                        
                                        if(ga.getId() == 2){
                                            out.println("<a class=\"dropdown-item\" href=\"../restrito/curso.jsp\">Teste de Conhecimento Militar</a>");
                                        }
                                        else if(ga.getId() == 3){                                            
                                            out.println("<a class=\"dropdown-item\" href=\"../restrito/curso.jsp\">Exame de Aptidão Física</a>");
                                        }
                                        else if(ga.getId() == 4){
                                            out.println("<a class=\"dropdown-item\" href=\"../restrito/avaliacao/psico.jsp\">Avaliação Psicológica</a>");
                                        }
                                    }
                                %>
                            </div>
                        </li>
                        <%

                            for(GrupoAcesso ga : grpsacesso){
                                if(ga.getId() == 2){
                                    out.println(
                                                "<li class=\"nav-item dropdown\">"+
                                                    "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                        "STE"+
                                                    "</a>"+
                                                    "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                        "<a class=\"dropdown-item\" href=\"../restrito/curso.jsp\">Curso</a>"+
                                                        "<div class=\"dropdown-divider\"></div>"+
                                                        "<a class=\"dropdown-item\" href=\"../restrito/gradeCurricular.jsp\">Grade Curricular</a>"+
                                                    "</div>"+
                                                "</li>");                                        
                                                    
                                }
                                else if(ga.getId() == 3){
                                    out.println(
                                                "<li class=\"nav-item dropdown\">"+
                                                    "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                        "DivAl"+
                                                    "</a>"+
                                                    "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+
                                                        "<a class=\"dropdown-item\" href=\"../restrito/curso/curso.jsp\">Curso</a>"+
                                                        "<a class=\"dropdown-item\" href=\"../restrito/turma/turma.jsp\">Turma</a>"+
                                                        "<a class=\"dropdown-item\" href=\"../restrito/candidato/precandidatura.jsp\">Cadastrar Candidato</a>"+
                                                    "</div>"+
                                                "</li>");                        
                                }
                                else if(ga.getId() == 4){
                                    out.println();
                                }
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
            <%
                if(request.getParameter("e") != null){
                    int retorno = Integer.parseInt(request.getParameter("e"));
                    
                    if(retorno == 1){
                        out.println("<div class=\"alert alert-success shadow-sm text-center\" role=\"alert\">");
                        out.println("       Usuário ativado com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 4){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a ativação do Usuário.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                }
            %>
            
            <table class="table col-md-12">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">IDENTIDADE</th>
                        <th scope="col">MILITAR</th>
                        <th scope="col">AÇÃO</th>
                    </tr>
                </thead>
                <tbody>
                    <%              
                        int i = 1;
                        MilitarDAO milDAO = new MilitarDAO();
                        ArrayList<Militar> militaresInativos = milDAO.getMilitaresInativos();
                        
                        if(militaresInativos.size() == 0){
                            out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                            out.println("       Nenhum usuário desativado.");
                            out.println("</div>");
                        }
                        else{
                            for(Militar mil : militaresInativos){
                                
                                out.println("<tr>");
                                out.println("   <th scope=\"row\">"+ (i) +"</th>");
                                out.println("   <td>"+ mil.getIdentidade() +"</td>");
                                out.println("   <td>"+ mil.getAbreviaturaPostoGraduacao() + " " + mil.getNomeguerra() +"</td>");
                                out.println("   <td>");
                                out.println("       <div class=form-row>");
                                out.println("           <div class=\"form-group mr-2\">");
                                out.println("               <form name=\"formAtivar\" method=\"POST\" action=\"controller.usuario/AtivarUsuario\">");
                                out.println("                   <input type=\"hidden\" class=\"form-control\" name=\"idtUsuarioAtivar\" id=\"idtUsuarioAtivar\" readonly=\"readonly\" value=\""+mil.getIdentidade()+"\"/>");
                                out.println("                   <button id="+mil.getIdentidade()+" type=\"submit\" name=\"btnAtivar\" class=\"btn btn-success\" onclick=\"return confirm('Tem certeza que deseja ativar o usuário?');\">Ativar</button>");
                                out.println("               </form>");
                                out.println("           </div>");
                                out.println("       </div>");
                                out.println("   </td>");
                                out.println("</tr>");
                                
                                i++;
                            }
                        }
                    %>
                </tbody>
            </table>          
        </section>
        
        <footer class="container-fluid bg-success text-center fixed-bottom">
            <b>Anderson de Paula Andrade Medeiros</b><br>
            <b class="text-center copy">&copy; 2019-2020</b>
        </footer>
        <script src="../../assets/node_modules/jquery/dist/jquery.js"></script>
        <script src="../../assets/node_modules/popper.js/dist/popper.js"></script>
        <script src="../../assets/node_modules/bootstrap/dist/js/bootstrap.js"></script>
        
        <script src="../../assets/js/bootstrap-validate.js"></script>
    </body>
</html>
