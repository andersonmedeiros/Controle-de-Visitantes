<%-- 
    Document   : militar
    Created on : 20/08/2020, 11:08:05
    Author     : CB Anderson de Paula Andrade Medeiros
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.*"%>
<%@page import="model.bean.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Anderson de Paula Andrade Medeiros">
        
        <link rel="icon" type="image/x-icon" href="../../assets/img/brasaocigs.png" />
        <title>Visitantes Militares</title>
        
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
                                                "<a class=\"nav-link dropdown-toggle active\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitantes"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/civil.jsp\">Listar Visitantes Civis</a>"+
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/visitante/militar.jsp\">Listar Visitantes Militares</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/cadastro.jsp\">Novo Visitante</a>"+
                                                "</div>"+
                                            "</li>"+  
                                            
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitas"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/aberta.jsp\">Visitas Abertas</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/fechada.jsp\">Visitas Fechadas</a>"+
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
                                                    "Manutenção"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                  
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/organizacaomilitar/om.jsp\">Organização Militar</a>"+              
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/divisaosecao/divisaosecao.jsp\">Divisão/Seção</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/setor/setor.jsp\">Setores</a>"+
                                                "</div>"+
                                            "</li>"+
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle active\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitantes"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/civil.jsp\">Listar Visitantes Civis</a>"+
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/visitante/militar.jsp\">Listar Visitantes Militares</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/cadastro.jsp\">Novo Visitante</a>"+
                                                "</div>"+
                                            "</li>"+
                                                    
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitas"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/aberta.jsp\">Visitas Abertas</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/fechada.jsp\">Visitas Fechadas</a>"+
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
            <%
                if(request.getParameter("e") != null){
                    int retorno = Integer.parseInt(request.getParameter("e"));
                    
                    if(retorno == 3){
                        out.println("<div class=\"alert alert-success shadow-sm text-center\" role=\"alert\">");
                        out.println("       Visitante excluído com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 4){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a exclusão do Visitante.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    
                    else if(retorno == 5){
                        out.println("<div class=\"alert alert-success shadow-sm text-center\" role=\"alert\">");
                        out.println("       Visitante atualizado com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 6){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a atualização do Visitante.<br>Tente Novamente!");
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
                        VisitanteDAO visDAO = new VisitanteDAO();
                        ArrayList<Visitante> visitantesMilitares = visDAO.getVisitantesByTipo(1);
                        
                        if(visitantesMilitares.size() == 0){
                            out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                            out.println("       Nenhum  visitante militar cadastrado.");
                            out.println("</div>");
                        }
                        else{
                            for(Visitante vis : visitantesMilitares){
                                
                                out.println("<tr>");
                                out.println("   <th scope=\"row\">"+ (i) +"</th>");
                                out.println("   <td>"+ vis.getIdentidade() +"</td>");
                                out.println("   <td>"+ vis.getAbreviaturaPostoGraduacao() + " " + vis.getNomeguerra() +"</td>");
                                out.println("   <td>");
                                out.println("       <div class=form-row>");
                                out.println("           <div class=\"form-group mr-2\">");
                                out.println("               <form name=\"formExcluir\" method=\"POST\" action=\"controller.visitante/ExcluirVisitante\">");
                                out.println("                   <input type=\"hidden\" class=\"form-control\" name=\"idtVisitanteExcluir\" id=\"idtVisitanteExcluir\" readonly=\"readonly\" value=\""+vis.getIdentidade()+"\"/>");
                                out.println("                   <input type=\"hidden\" class=\"form-control\" name=\"tipoVisitanteExcluir\" id=\"tipoVisitanteExcluir\" readonly=\"readonly\" value=\""+vis.getTipo()+"\"/>");
                                out.println("                   <button id="+vis.getIdentidade()+" type=\"submit\" name=\"btnExcluir\" class=\"btn btn-danger\" onclick=\"return confirm('Tem certeza que deseja excluir o registro?');\">Excluir</button>");
                                out.println("               </form>");
                                out.println("           </div>");
                                out.println("           <div class=\"form-group mr-2\">");                  
                                out.println("                   <button id="+vis.getIdentidade()+" type=\"submit\" name=\"btnAlterar\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#modalFormAttVisitante\" onclick=\"alteraVisitanteMilitar("+"'"+vis.getIdentidade()+"'"+");\">Alterar</button>");
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
                
            <!-- Modal Atualizar-->
            <div class="modal fade" id="modalFormAttVisitante" tabindex="-1" role="dialog" aria-labelledby="modalFormAttVisitante" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title title" id="modalFormAttVisitante">Atualizar Visitante</h5>
                            <button type="button" name="btnFecharAtualizacaoMilitar" class="close" data-dismiss="modal" aria-label="Fechar">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">                            
                            <form class="container-fluid" action="controller.visitante/AtualizarVisitante" method="POST" name="formAtualizar">
                                <input type="hidden" class="form-control" id="txtIdentidadeMilitarAtt" name="txtIdentidadeMilitarAtt">
                                <input type="hidden" class="form-control" id="txtTipoVisitante" name="txtTipoVisitante">
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="txtTipoForcaAtt">Tipo de Força: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtTipoForcaAtt" name="txtTipoForcaAtt">
                                            <option value="0" selected>Selecione um Tipo de Força...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="txtForcaAtt">Força: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtForcaAtt" name="txtForcaAtt">
                                            <option value="0" selected>Selecione uma Força...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="txtPGradAtt">Posto/Graduação: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtPGradAtt" name="txtPGradAtt">
                                            <option value="0" selected>Selecione um Posto/Graduação...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>

                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label for="txtOMAtt">Organização Militar: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtOMAtt" name="txtOMAtt">
                                            <option value="0" selected>Selecione uma Organização Militar...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div id="div-nome" class="form-group col-md-3">
                                        <label for="txtNomeMilitarAtt">Nome: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtNomeMilitarAtt" name="txtNomeMilitarAtt" placeholder="Ex.: Nome">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>                                    
                                    <div id="div-sobrenome" class="form-group col-md-6">
                                        <label for="txtSobrenomeMilitarAtt">Sobrenome: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtSobrenomeMilitarAtt" name="txtSobrenomeMilitarAtt" placeholder="Ex.: Sobrenome">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>

                                    <div id="div-nomeguerra" class="form-group col-md-3">
                                        <label for="txtNomeGuerraAtt">Nome de Guerra: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtNomeGuerraAtt" name="txtNomeGuerraAtt" placeholder="Ex.: Nome de Guerra">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                </div>                        
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <label for="txtEmailMilitarAtt">Email: <span class="campo-obrigatorio">*</span></label>
                                        <input type="email" class="form-control" id="txtEmailMilitarAtt" name="txtEmailMilitarAtt" placeholder="Ex.: fulano@tal.com">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback invalid-email">Campo Obrigatório!</div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="txtFoneMilitarAtt">Fone: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control fone" id="txtFoneMilitarAtt" name="txtFoneMilitarAtt" placeholder="Ex.: (00) 00000-0000">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback invalid-foneCel">Campo Obrigatório!</div>
                                    </div>
                                </div>
                                
                                <button type="button" name="btnLimparAtualizacaoMilitar" class="btn btn-warning">Limpar</button>
                                <button type="submit" name="btnSalvarAtualizacaoMilitar" class="btn btn-success">Salvar</button>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" name="btnFecharAtualizacaoMilitar" class="btn btn-danger" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <footer class="container-fluid bg-success text-center fixed-bottom">
            <b>Desenvolvido pelo 3º Sgt Anderson de Paula Andrade Medeiros</b><br>
            <b>Seção de Tecnologia da Informação</b><br>
            <b class="text-center copy">&copy; 2021</b>
        </footer> 
                
        <script src="../../assets/node_modules/jquery/dist/jquery.js"></script>
        <script src="../../assets/node_modules/popper.js/dist/popper.js"></script>
        <script src="../../assets/node_modules/bootstrap/dist/js/bootstrap.js"></script>
        
        <script src="../../assets/js/formulario/validacao/mascaras.js"></script>
        <script src="../../assets/js/dwr/visitante/camposDinamicos.js"></script>
        
        <script src="../../assets/js/formulario/validacao/visitante/funcoesImportantes.js"></script>
        <script src="../../assets/js/formulario/validacao/visitante/atualizacaoMilitar.js"></script> 
        
        <script src="../../assets/js/bootstrap-validate.js"></script>
    </body>
