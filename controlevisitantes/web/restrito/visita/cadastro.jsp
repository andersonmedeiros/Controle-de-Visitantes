<%-- 
    Document   : cadastro
    Created on : 21/08/2020, 22:35:28
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
        <title>Nova Visita</title>
        
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
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/fechada.jsp\">Visitas Fechadas</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/visita/cadastro.jsp\">Nova Visita</a>"+
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
                                                    "<a class=\"dropdown-item\" href=\"../../restrito/visita/fechada.jsp\">Visitas Fechadas</a>"+
                                                    "<div class=\"dropdown-divider\"></div>"+
                                                    "<a class=\"dropdown-item active\" href=\"../../restrito/visita/cadastro.jsp\">Nova Visita</a>"+
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
                        out.println("       Visita adicionado com sucesso!");
                        out.println("</div>");
                    }
                    else if(retorno == 2){
                        out.println("<div class=\"alert alert-danger shadow-sm text-center\" role=\"alert\">");
                        out.println("       Erro durante a realização do cadastro.<br>Tente Novamente!");
                        out.println("</div>");
                    }
                }
            %>
            
            <form class="container-fluid form" action="controller.visita/CadastrarEntrada" method="POST" name="formCadastrar">
                <fieldset>
                    <div class="fieldset-header">
                        <h5 class="fieldset-title">Nova Visita</h5>
                    </div>
                    <div class="fieldset-body">
                        <div class="header-interno">
                            <h5 class="title-interno">Visitante</h5>
                        </div>
                        <div class="fieldset-interno">
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="txtTipoVisitante">Qual tipo de Visitante: <span class="campo-obrigatorio">*</span></label>
                                    <select class="form-control" id="txtTipoVisitante" name="txtTipoVisitante">
                                        <option value="0" selected>Selecione o Tipo de Visitante...</option>
                                        <option value="1">MILITAR</option>
                                        <option value="2">CIVIL</option>
                                    </select>
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                                <div id="div-idtmilitar" class="form-group col-md-4" style="display: none;">
                                    <label for="txtIdentidadeMilitar">Identidade: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control idt-mil" id="txtIdentidadeMilitar" name="txtIdentidadeMilitar" placeholder="Ex.: 120000000-0" maxlength="10">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-identidade">Campo Obrigatório!</div>
                                </div>   
                                <div id="div-idtcivil" class="form-group col-md-4" style="display: none;">
                                    <label for="txtIdentidadeCivil">Identidade: <span class="campo-obrigatorio">*</span></label>
                                    <input type="text" class="form-control idt-cv" id="txtIdentidadeCivil" name="txtIdentidadeCivil" placeholder="Ex.: 120000000-0" maxlength="10">
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback invalid-identidade">Campo Obrigatório!</div>
                                </div>  
                                <div class="form-group col-md-4 align-self-end" style="margin-bottom: 16px;">
                                    <button type="button" id="btn" name="btnPesquisar" class="btn btn-warning btn-tentativas" value="Candidatura" >Pesquisar</button>
                                </div>
                            </div>
                        </div>
                        
                        <div id="div-visnencontrado" class="alert alert-danger shadow-sm text-center" role="alert" style="display: none;">
                            Visitante não cadastrado.
                        </div>
                        
                        <div id="formVisita" class="formVisita" style="display: none;">
                            <div class="header-interno">
                                <h5 class="title-interno">Visitante</h5>
                            </div>
                            <div class="fieldset-interno">
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <label for="txtNomeVisitante">Nome: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtNomeVisitante" name="txtNomeVisitante" placeholder="Ex.: Nome" readonly="">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>    
                                    <div class="form-group col-md-4">
                                        <label for="txtCracha">Crachá: <span class="campo-obrigatorio">*</span></label>
                                        <input type="text" class="form-control" id="txtCracha" name="txtCracha" placeholder="Ex.: 10">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>    
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-3">
                                        <label for="txtDataEntrada">Data de entrada: <span class="campo-obrigatorio">*</span></label>
                                        <input type="date" class="form-control" id="txtDataEntrada" name="txtDataEntrada">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>    
                                    <div class="form-group col-md-3">
                                        <label for="txtHoraEntrada">Horário de entrada: <span class="campo-obrigatorio">*</span></label>
                                        <input type="time" step="1" class="form-control" id="txtHoraEntrada" name="txtHoraEntrada">
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>    
                                    <div class="form-group col-md-6">
                                    <label for="txtSetorVisitado">Setor a ser visitado: <span class="campo-obrigatorio">*</span></label>
                                    <select class="form-control" id="txtSetorVisitado" name="txtSetorVisitado">
                                        <option value="0" selected>Selecione o Setor...</option>
                                    </select>
                                    <div class="valid-feedback">Selva!</div>
                                    <div class="invalid-feedback">Campo Obrigatório!</div>
                                </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label for="txtFinalidade">Finalidade: <span class="campo-obrigatorio">*</span></label>
                                        <textarea class="form-control" id="txtFinalidade" name="txtFinalidade" placeholder="Ex.: Finalidade"></textarea>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>    
                                </div>
                            </div>
                            <div class="header-interno">
                                <h5 class="title-interno">Veículo</h5>
                            </div>
                            <div class="fieldset-interno">
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label for="txtVeiculoEntrar">O visitante vai adentrar o CIGS com veículo?<span class="campo-obrigatorio"> *</span></label>
                                        <select class="form-control" id="txtVeiculoEntrar" name="txtVeiculoEntrar">
                                            <option value="0" selected>Selecione SIM ou NÃO...</option>
                                            <option value="s">SIM</option>
                                            <option value="n">NÃO</option>
                                        </select>
                                        <div class="valid-feedback">Selva!</div>
                                        <div class="invalid-feedback">Campo Obrigatório!</div>
                                    </div>
                                </div>                            
                        
                                <div id="divVeiculos" class="fieldset-interno fieldset-dependente" style="display: none;">
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="txtTipoVeiculo">Tipo: <span class="campo-obrigatorio">*</span></label>
                                            <select class="form-control" id="txtTipoVeiculo" name="txtTipoVeiculo">
                                                <option value="0" selected>Selecione o tipo...</option>
                                                <option value="motocicleta">MOTOCICLETA</option>
                                                <option value="automovel">AUTOMÓVEL</option>
                                                <option value="microonibus">MICROÔNIBUS</option>
                                                <option value="onibus">ÔNIBUS</option>
                                            </select>
                                            <div class="valid-feedback">Selva!</div>
                                            <div class="invalid-feedback">Campo Obrigatório!</div>
                                        </div>
                                        <div class="form-group col-md-3">
                                            <label for="txtMarcaVeiculo">Marca: <span class="campo-obrigatorio">*</span></label>
                                            <input type="text" class="form-control" id="txtMarcaVeiculo" name="txtMarcaVeiculo" placeholder="Ex.: Hyundai">               
                                            <div class="valid-feedback">Selva!</div>
                                            <div class="invalid-feedback">Campo Obrigatório!</div>
                                        </div>
                                        <div class="form-group col-md-3">
                                            <label for="txtModeloVeiculo">Modelo: <span class="campo-obrigatorio">*</span></label>
                                            <input type="text" class="form-control" id="txtModeloVeiculo" name="txtModeloVeiculo" placeholder="Ex.: HB20S">
                                            <div class="valid-feedback">Selva!</div>
                                            <div class="invalid-feedback">Campo Obrigatório!</div>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label for="txtCorVeiculo">Cor: <span class="campo-obrigatorio">*</span></label>
                                            <input type="text" class="form-control" id="txtCorVeiculo" name="txtCorVeiculo" placeholder="Ex.: Vermelho">
                                            <div class="valid-feedback">Selva!</div>
                                            <div class="invalid-feedback">Campo Obrigatório!</div>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label for="txtPlacaVeiculo">Placa: <span class="campo-obrigatorio">*</span></label>
                                            <input type="text" class="form-control" id="txtPlacaVeiculo" name="txtPlacaVeiculo" placeholder="Ex.: XXX 0X00">
                                            <div class="valid-feedback">Selva!</div>
                                            <div class="invalid-feedback">Campo Obrigatório!</div>
                                        </div>
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
        <script src="../../assets/js/dwr/visita/cadastro/camposDinamicos.js"></script>
        
        <script src="../../assets/js/formulario/validacao/visita/funcoesImportantes.js"></script>
        <script src="../../assets/js/formulario/validacao/visita/cadastro.js"></script> 
        <script src="../../assets/js/bootstrap-validate.js"></script>
    </body>
</html>

