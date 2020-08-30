<%-- 
    Document   : cadastro
    Created on : 19/08/2020, 13:43:00
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
        <title>Novo Visitante</title>
        
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
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/militar.jsp\">Listar Visitantes Militares</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/visitante/cadastro.jsp\">Novo Visitante</a>"+
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
                                                "<a class=\"nav-link dropdown-toggle active\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                                                    "Visitantes"+
                                                "</a>"+
                                                "<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">"+                                
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/civil.jsp\">Listar Visitantes Civis</a>"+
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visitante/militar.jsp\">Listar Visitantes Militares</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/visitante/cadastro.jsp\">Novo Visitante</a>"+
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
                        out.println("       Visitante adicionado com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 2){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a realização do cadastro.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                }
            %>
            
            <form class="container-fluid form" action="controller.visitante/CadastrarVisitante" method="POST" name="formCadastrar">
                <fieldset>
                    <div class="fieldset-header">
                        <h5 class="fieldset-title">Novo Visitante</h5>
                    </div>
                    <div class="fieldset-body">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="txtTipoVisitante">Qual tipo de Visitante: <span class="campo-obrigatorio">*</span></label>
                                <select class="form-control" id="txtTipoVisitante" name="txtTipoVisitante">
                                    <option value="0" selected>Selecione o Tipo de Visitante...</option>
                                    <option value="1">MILITAR</option>
                                    <option value="2">CIVIL</option>
                                </select>
                                <div class="valid-feedback">Selva!</div>
                                <div class="invalid-feedback">Campo Obrigatório!</div>
                            </div>
                        </div>
                        <div id="formVisitanteMilitar" style="display: none;">
                            <div class="form-row">
                                <div id="div-identidade" class="form-group col-md-4">
                                    <label for="txtIdentidadeMilitar">Identidade: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control idt-mil" id="txtIdentidadeMilitar" name="txtIdentidadeMilitar" placeholder="Ex.: 120000000-0">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-identidademilitar">Campo Obrigatório!</div>
                                </div>  
                                <div class="form-group col-md-4">
                                    <label for="txtFotoMilitar">Foto: <span class="campo-obrigatorio">*</span></label>
                                    <input type="file" class="form-control" id="txtFotoMilitar" name="txtFotoMilitar" accept="image/*" capture>
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-identidademilitar">Campo Obrigatório!</div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="txtTipoForca">Tipo de Força: <span class="campo-obrigatorio">*</span></label>
                                    <select class="form-control" id="txtTipoForca" name="txtTipoForca">
                                        <option value="0" selected>Selecione um Tipo de Força...</option>
                                    </select>
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="txtForca">Força: <span class="campo-obrigatorio">*</span></label>
                                    <select class="form-control" id="txtForca" name="txtForca">
                                        <option value="0" selected>Selecione uma Força...</option>
                                    </select>
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="txtPGrad">Posto/Graduação: <span class="campo-obrigatorio">*</span></label>
                                    <select class="form-control" id="txtPGrad" name="txtPGrad">
                                        <option value="0" selected>Selecione um Posto/Graduação...</option>
                                    </select>
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                                
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <label for="txtOM">Organização Militar: <span class="campo-obrigatorio">*</span></label>
                                    <select class="form-control" id="txtOM" name="txtOM">
                                        <option value="0" selected>Selecione uma Organização Militar...</option>
                                    </select>
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div id="div-nome" class="form-group col-md-3">
                                    <label for="txtNomeMilitar">Nome: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control" id="txtNomeMilitar" name="txtNomeMilitar" placeholder="Ex.: Nome">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>                                    
                                <div id="div-sobrenome" class="form-group col-md-6">
                                    <label for="txtSobrenomeMilitar">Sobrenome: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control" id="txtSobrenomeMilitar" name="txtSobrenomeMilitar" placeholder="Ex.: Sobrenome">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                                
                                <div id="div-nomeguerra" class="form-group col-md-3">
                                    <label for="txtNomeGuerra">Nome de Guerra: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control" id="txtNomeGuerra" name="txtNomeGuerra" placeholder="Ex.: Nome de Guerra">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                            </div>                        
                            <div class="form-row">
                                <div class="form-group col-md-8">
                                    <label for="txtEmailMilitar">Email: <span class="campo-obrigatorio">*</span></label>
                                    <input type="email" class="form-control" id="txtEmailMilitar" name="txtEmailMilitar" placeholder="Ex.: fulano@tal.com">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-emailmilitar">Campo Obrigatório!</div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="txtFoneMilitar">Fone: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control fone" id="txtFoneMilitar" name="txtFoneMilitar" placeholder="Ex.: (00) 00000-0000">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-fonecelmilitar">Campo Obrigatório!</div>
                                </div>
                            </div>
                        </div>
                        <div id="formVisitanteCivil" style="display: none;">
                            <div class="form-row">
                                <div id="div-identidade" class="form-group col-md-3">
                                    <label for="txtIdentidadeCivil">Identidade: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control idt-cv" id="txtIdentidadeCivil" name="txtIdentidadeCivil" placeholder="Ex.: 0000000-0">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-identidade">Campo Obrigatório!</div>
                                </div>  
                                <div id="div-nome" class="form-group col-md-3">
                                    <label for="txtNomeCivil">Nome: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control" id="txtNomeCivil" name="txtNomeCivil" placeholder="Ex.: Nome">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>                                    
                                <div id="div-sobrenome" class="form-group col-md-6">
                                    <label for="txtSobrenomeCivil">Sobrenome: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control" id="txtSobrenomeCivil" name="txtSobrenomeCivil" placeholder="Ex.: Sobrenome">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                            </div>                    
                            <div class="form-row">
                                <div class="form-group col-md-8">
                                    <label for="txtEmailCivil">Email: <span class="campo-obrigatorio">*</span></label>
                                    <input type="email" class="form-control" id="txtEmailCivil" name="txtEmailCivil" placeholder="Ex.: fulano@tal.com">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-emailcivil">Campo Obrigatório!</div>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="txtFoneCivil">Fone: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control fone" id="txtFoneCivil" name="txtFoneCivil" placeholder="Ex.: (00) 00000-0000">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-fonecelcivil">Campo Obrigatório!</div>
                                </div>
                            </div>
                        </div>
                            <button type="button" name="btnLimparCadastro" class="btn btn-warning">Limpar</button>
                            <button type="submit" name="btnSalvarCadastro" class="btn btn-success">Salvar</button>                    
                        </div>
                    </div>
                </fieldset>
            </form>
        </section>
        
        <footer class="container-fluid bg-success text-center fixed-bottom">
            <b>Desenvolvido por CB Anderson de Paula Andrade Medeiros</b><br>
            <b class="text-center copy">&copy; 2020</b>
        </footer>
                            
        <script src="../../assets/node_modules/jquery/dist/jquery.js"></script>
        <script src="../../assets/node_modules/popper.js/dist/popper.js"></script>
        <script src="../../assets/node_modules/bootstrap/dist/js/bootstrap.js"></script>
        
        <script src="../../assets/js/formulario/validacao/mascaras.js"></script>
        <script src="../../assets/js/dwr/visitante/camposDinamicos.js"></script>
        
        <script src="../../assets/js/formulario/validacao/visitante/funcoesImportantes.js"></script>
        <script src="../../assets/js/formulario/validacao/visitante/cadastro.js"></script> 
        <script src="../../assets/js/bootstrap-validate.js"></script>
    </body>
</html>