<%-- 
    Document   : ativos
    Created on : 16/08/2020, 19:48:37
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
        
        <link rel="icon" type="image/x-icon" href="../../assets/img/logo_sgdis.png" />
        <title>Usuários Ativos</title>
        
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
                        <%
                            HttpSession sessao = request.getSession();
                            Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioAutenticado");
                
                            if(usuarioLogado.getIdGrupoAcesso()== 1){
                                out.println(
                                            "<li class=\"nav-item dropdown\">"+
                                                "<a class=\"nav-link dropdown-toggle active\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Usuários"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/usuario/ativos.jsp\">Listar Usuários Ativos</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/usuario/inativos.jsp\">Listar Usuários Inativos</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/usuario/cadastro.jsp\">Novo Usuário</a>"+
                                                "</div>"+
                                            "</li>"+
                                            
                                            "<li class=\"nav-item\">"+
                                                "<a class=\"nav-link\" href=\"../restrito/avaliacao/avpreliminar.jsp\">Divisão/Seção</a>"+
                                            "</li>"+
                                                    
                                            "<li class=\"nav-item\">"+
                                                "<a class=\"nav-link\" href=\"../restrito/avaliacao/avpreliminar.jsp\">Setores</a>"+
                                            "</li>"+
                                            
                                            "<li class=\"nav-item\">"+
                                                "<a class=\"nav-link\" href=\"../restrito/avaliacao/avpreliminar.jsp\">Visitantes</a>"+
                                            "</li>"
                                            );
                            }
                            else if(usuarioLogado.getIdGrupoAcesso() == 2){
                                out.println(
                                            "<li class=\"nav-item\">"+
                                                "<a class=\"nav-link\" href=\"../restrito/avaliacao/avpreliminar.jsp\">Divisão/Seção</a>"+
                                            "</li>"+
                                                    
                                            "<li class=\"nav-item\">"+
                                                "<a class=\"nav-link\" href=\"../restrito/avaliacao/avpreliminar.jsp\">Setores</a>"+
                                            "</li>"+
                                            
                                            "<li class=\"nav-item\">"+
                                                "<a class=\"nav-link\" href=\"../restrito/avaliacao/avpreliminar.jsp\">Visitantes</a>"+
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
                        out.println("       Usuário excluído com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 4){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a exclusão do Usuário.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    
                    else if(retorno == 5){
                        out.println("<div class=\"alert alert-success shadow-sm text-center\" role=\"alert\">");
                        out.println("       Usuário atualizado com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 6){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a atualização do Usuário.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    
                    else if(retorno == 7){
                        out.println("<div class=\"alert alert-success shadow-sm text-center\" role=\"alert\">");
                        out.println("       Senha atualizada com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 8){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a atualização da senha.<br>Tente Novamente!");
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
                        UsuarioDAO usuDAO = new UsuarioDAO();
                        ArrayList<Usuario> usuariosAtivos = usuDAO.getUsuariosAtivos();
                        
                        if(usuariosAtivos.size() == 0){
                            out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                            out.println("       Nenhum usuário cadastrado.");
                            out.println("</div>");
                        }
                        else{
                            for(Usuario usu : usuariosAtivos){
                                
                                out.println("<tr>");
                                out.println("   <th scope=\"row\">"+ (i) +"</th>");
                                out.println("   <td>"+ usu.getIdentidade() +"</td>");
                                out.println("   <td>"+ usu.getAbreviaturaPostoGraduacao() + " " + usu.getNomeguerra() +"</td>");
                                out.println("   <td>");
                                out.println("       <div class=form-row>");
                                out.println("           <div class=\"form-group mr-2\">");
                                out.println("               <form name=\"formExcluir\" method=\"POST\" action=\"controller.usuario/ExcluirUsuario\">");
                                out.println("                   <input type=\"hidden\" class=\"form-control\" name=\"idtUsuarioExcluir\" id=\"idtUsuarioExcluir\" readonly=\"readonly\" value=\""+usu.getIdentidade()+"\"/>");
                                out.println("                   <button id="+usu.getIdentidade()+" type=\"submit\" name=\"btnExcluir\" class=\"btn btn-danger\" onclick=\"return confirm('Tem certeza que deseja excluir o registro?');\">Excluir</button>");
                                out.println("               </form>");
                                out.println("           </div>");
                                out.println("           <div class=\"form-group mr-2\">");                  
                                out.println("                   <button id="+usu.getIdentidade()+" type=\"submit\" name=\"btnAlterar\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#modalFormAttUsuario\" onclick=\"alteraUsuario("+"'"+usu.getIdentidade()+"'"+");\">Alterar</button>");
                                out.println("           </div>");
                                out.println("           <div class=form-group>");                  
                                out.println("                   <button id="+ usu.getIdentidade() +" type=\"submit\" name=\"btnAlterarSenha\" class=\"btn btn-dark\" data-toggle=\"modal\" data-target=\"#modalFormAttSenhaUsuario\" onclick=\"alteraSenhaUsuario("+"'"+usu.getIdentidade()+"'"+");\">Senha</button>");
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
            <div class="modal fade" id="modalFormAttUsuario" tabindex="-1" role="dialog" aria-labelledby="modalFormAttUsuario" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title title" id="modalFormAttUsuario">Atualizar Usuário</h5>
                            <button type="button" name="btnFecharAtualizacao" class="close" data-dismiss="modal" aria-label="Fechar">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">                            
                            <form class="container-fluid" action="controller.usuario/AtualizarUsuario" method="POST" name="formAtualizar">
                                <input type="hidden" class="form-control" id="txtIdtAtt" name="txtIdtAtt">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtPGrad">Posto/Graduação: <span class="campo-obrigatorio">*</span></label>
                                        <select class="form-control" id="txtPGrad" name="txtPGrad">
                                            <option value="0" selected>Selecione um Posto/Graduação...</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>  
                                    <div class="form-group col-md-6">
                                        <label for="txtNome">Nome: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtNome" name="txtNome" placeholder="Ex.: Nome">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div> 
                                </div>
                                <div class="form-row">                                                                       
                                    <div class="form-group col-md-8">
                                        <label for="txtSobrenome">Sobrenome: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtSobrenome" name="txtSobrenome" placeholder="Ex.: Sobrenome">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="txtNomeGuerra">Nome de Guerra: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtNomeGuerra" name="txtNomeGuerra" placeholder="Ex.: Nome de Guerra">
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
                                    
            <!-- Modal Atualizar Senha-->
            <div class="modal fade" id="modalFormAttSenhaUsuario" tabindex="-1" role="dialog" aria-labelledby="modalFormAttSenhaUsuario" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title title" id="modalFormAttSenhaUsuario">Atualizar Senha do Usuário</h5>
                            <button type="button" name="btnFecharAtualizacaoSenha" class="close" data-dismiss="modal" aria-label="Fechar">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">                            
                            <form class="container-fluid" action="controller.usuario/AtualizarSenhaUsuario" method="POST" name="formAtualizarSenhaUsuario">
                                <input type="hidden" class="form-control" id="txtIdtAttSenha" name="txtIdtAttSenha">
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label for="txtSenha">Senha: <span class="campo-obrigatorio">*</span></label>
                                        <input type="password" class="form-control" id="txtSenha" name="txtSenha" placeholder="Ex.: Senha">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback invalid-senha">Campo Obrigatório!</div>
                                    </div>  
                                </div> 
                                <button type="button" name="btnLimparAtualizacaoSenha" class="btn btn-warning">Limpar</button>
                                <button type="submit" name="btnSalvarAtualizacaoSenha" class="btn btn-success">Salvar</button>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" name="btnFecharAtualizacaoSenha" class="btn btn-danger" data-dismiss="modal">Fechar</button>
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
        
        <script src="../../assets/js/formulario/validacao/mascaras.js"></script>
        <script src="../../assets/js/dwr/usuario/cadastro/camposDinamicos.js"></script>
        
        <script src="../../assets/js/formulario/validacao/usuario/funcoesImportantes.js"></script>
        <script src="../../assets/js/formulario/validacao/usuario/atualizacao.js"></script> 
        <script src="../../assets/js/formulario/validacao/usuario/atualizacaoSenha.js"></script> 
        
        <script src="../../assets/js/bootstrap-validate.js"></script>
    </body>
</html>


