<%-- 
    Document   : om
    Created on : 21/08/2020, 16:35:46
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
        <title>Organização Militar</title>
        
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
                <a class="navbar-brand active" href="../../restrito/inicial.jsp"><img src="../../assets/img/brasaocigs.png" width="30px" height="35px"><span class="sr-only">CtVis</span></a>
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
                                                "<a class=\"nav-link dropdown-toggle active\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Manutenção"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                  
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/organizacaomilitar/om.jsp\">Organização Militar</a>"+              
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

                    if(retorno == 1){
                        out.println("<div class=\"alert alert-success shadow-sm text-center\" role=\"alert\">");
                        out.println("       Organização Militar adicionada com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 2){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a realização do cadastro.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    else if(retorno == 3){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a exclusão do cadastro.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    else if(retorno == 4){
                        out.println("<div class=\"alert alert-success shadow-sm text-center\" role=\"alert\">");
                        out.println("       Organização Militar excluída com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 5){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a atualização do cadastro.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    else if(retorno == 6){
                        out.println("<div class=\"alert alert-success shadow-sm text-center\" role=\"alert\">");
                        out.println("       Organização Militar atualizado com sucesso!");
                        out.println("</div>");
                    }
                }
            %>
            
            <!-- Botão para acionar modal -->
            <center>
                <div class="col-md-12 mb-3">
                    <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#modalFormOm">Nova Organização Militar</button>
                </div>
            </center>
            
            <form id="formPesqOms" class="form formCad">
                <fieldset>
                    <div class="fieldset-header">
                        <h5 class="fieldset-title">Organização Militar</h5>
                    </div>
                    <div class="fieldset-body">
                        <div class="form-row">
                            <div class="form-group col-md-5">
                                <label for="txtTipoForca">Tipo de Força: <span class="campo-obrigatorio">*</span></label>
                                <select class="form-control" id="txtTipoForca" name="txtTipoForca">
                                    <option value="0" selected>Selecione um Tipo de Força...</option>                                    
                                </select>
                                <div class="valid-feedback">Selva!</div>
                                <div class="invalid-feedback">Campo Obrigatório!</div>
                            </div>
                            <div class="form-group col-md-5">
                                <label for="txtForca">Força: <span class="campo-obrigatorio">*</span></label>
                                <select class="form-control" id="txtForca" name="txtForca">
                                    <option value="0" selected>Selecione uma Força...</option>                                    
                                </select>
                                <div class="valid-feedback">Selva!</div>
                                <div class="invalid-feedback">Campo Obrigatório!</div>
                            </div>
                            <div class="form-group col-md-2 align-self-center">
                               <button type="button" id="btn" name="btnPesquisar" class="btn btn-warning btn-pesquisar col-md-12" value="Pesquisar">Pesquisar</button>
                            </div>
                        </div>                        
                        <div class="header-interno">
                            <h5 class="title-interno"></h5>
                        </div>
                        <div class="fieldset-interno">
                            <table class="table">
                                <thead class="thead-light">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">NOME</th>
                                        <th scope="col">ABREVIATURA</th>
                                        <th scope="col">FORÇA</th>
                                        <th scope="col">AÇÃO</th>
                                    </tr>
                                </thead>
                                <tbody id="oms"></tbody>
                            </table>
                                                       
                        </div>
                    </div>                    
                </fieldset>                
            </form>
                                
            <!--<table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">NOME</th>
                        <th scope="col">ABREVIATURA</th>
                        <th scope="col">FORÇA</th>
                        <th scope="col">AÇÃO</th>
                    </tr>
                </thead>
                <tbody>
                    <%              
                        /*OmDAO omDAO = new OmDAO();
                        ArrayList<Om> oms = omDAO.getOms();
                        int i=1;
                        
                        if(oms.size() == 0){
                            out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                            out.println("       Nenhum Organização Militar cadastrado.<br>Adicione um Organização Militar!");
                            out.println("</div>");
                        }
                        else{
                            for(Om om : oms){
                                out.println("<tr>");
                                out.println("   <th scope=\"row\">"+ i +"</th>");
                                out.println("   <td>"+ om.getNome() +"</td>");
                                out.println("   <td>"+ om.getAbreviatura()+"</td>");             
                                out.println("   <td>"+ om.getNomeForca()+"</td>");             
                                out.println("   <td>");
                                out.println("       <div class=form-row>");
                                out.println("           <div class=\"form-group mr-2\">");
                                out.println("               <form name=\"formExcluir\" method=\"POST\" action=\"controller.organizacaomilitar/ExcluirOm\">");
                                out.println("                   <input type=\"hidden\" class=\"form-control\" name=\"idOmExcluir\" id=\"idOmExcluir\" readonly=\"readonly\" value=\""+om.getId()+"\"/>");
                                out.println("                   <button id="+om.getId()+" type=\"submit\" name=\"btnExcluir\" class=\"btn btn-danger\" onclick=\"return confirm('Tem certeza que deseja excluir o registro?');\">Excluir</button>");
                                out.println("               </form>");
                                out.println("           </div>");
                                out.println("           <div class=\"form-group mr-2\">");                  
                                out.println("                   <button id="+om.getId()+" type=\"button\" name=\"btnAlterar\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#modalFormAttOm\" onclick=\"alteraOm("+"'"+om.getId()+"'"+");\">Alterar</button>");
                                out.println("           </div>");
                                out.println("       </div>");
                                out.println("   </td>");
                                out.println("</tr>");
                                i++;
                            }
                        }*/
                    %>
                </tbody>
            </table>-->
            
            

            <!-- Modal Cadastro-->
            <div class="modal fade" id="modalFormOm" tabindex="-1" role="dialog" aria-labelledby="modalFormOm" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title title" id="modalFormOm">Nova Organização Militar</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">                            
                            <form class="container-fluid" action="controller.organizacaomilitar/CadastrarOm" method="POST" name="formCadastrar">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtTipoForcaCad">Tipo de Força: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtTipoForcaCad" name="txtTipoForcaCad">
                                            <option value="0" selected>Selecione um Tipo de Força...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="txtForcaCad">Força: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtForcaCad" name="txtForcaCad">
                                            <option value="0" selected>Selecione uma Força...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <label for="txtNome">Nome: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtNome" name="txtNome" placeholder="Nome">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="txtAbreviatura">Abreviatura: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtAbreviatura" name="txtAbreviatura" placeholder="Abreviatura">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>                                    
                                </div>
                                
                                <button type="button" name="btnLimparCadastro" class="btn btn-warning">Limpar</button>
                                <button type="submit" name="btnSalvarCadastro" class="btn btn-success">Salvar</button>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" name="btnFecharCadastro" class="btn btn-danger" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Modal Atualizar-->
            <div class="modal fade" id="modalFormAttOm" tabindex="-1" role="dialog" aria-labelledby="modalFormAttOm" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title title" id="modalFormAttOm">Nova Organização Militar</h5>
                            <button type="button" name="btnFecharAtualizacao" class="close" data-dismiss="modal" aria-label="Fechar">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">                            
                            <form class="container-fluid" action="controller.organizacaomilitar/AtualizarOm" method="POST" name="formAtualizar">
                                <input type="hidden" class="form-control" id="txtIdAtt" name="txtIdAtt">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtTipoForcaAtt">Tipo de Força: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtTipoForcaAtt" name="txtTipoForcaAtt">
                                            <option value="0" selected>Selecione um Tipo de Força...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="txtForcaAtt">Força: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtForcaAtt" name="txtForcaAtt">
                                            <option value="0" selected>Selecione uma Força...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <label for="txtNomeAtt">Nome: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtNomeAtt" name="txtNomeAtt" placeholder="Nome">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="txtAbreviaturaAtt">Abreviatura: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtAbreviaturaAtt" name="txtAbreviaturaAtt" placeholder="Abreviatura">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>                                    
                                </div>
                                <button type="button" name="btnLimparAtualizacao" class="btn btn-warning">Limpar</button>
                                <button type="submit" name="btnSalvarAtualizacao" class="btn btn-success">Salvar</button>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" name="btnFecharAtualizacao" class="btn btn-danger" data-dismiss="modal">Fechar</button>
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
        
        <script src="../../assets/js/dwr/organizacaomilitar/camposDinamicos.js"></script>
        <script src="../../assets/js/dwr/organizacaomilitar/tabelaDinamica.js"></script>

        <script src="../../assets/js/formulario/validacao/organizacaomilitar/funcoesImportantes.js"></script>
        <script src="../../assets/js/formulario/validacao/organizacaomilitar/cadastro.js"></script> 
        <script src="../../assets/js/formulario/validacao/organizacaomilitar/pesqomsbyforca.js"></script> 
        <script src="../../assets/js/formulario/validacao/organizacaomilitar/atualizacao.js"></script> 
        <script src="../../assets/js/bootstrap-validate.js"></script>
    </body>
</html>
