<%-- 
    Document   : index
    Created on : 15/08/2020, 15:43:22
    Author     : CB Anderson de Paula Andrade Medeiros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Anderson de Paula Andrade Medeiros">
        
        <link rel="icon" type="image/x-icon" href="assets/img/logo_sgdis.png" />
        <title>Login</title>
        
        <link rel="stylesheet" type="text/css" href="assets/node_modules/bootstrap/compiler/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="assets/css/estilo_universal.css">
        <link rel="stylesheet" type="text/css" href="assets/css/estilo_index.css">
        <link rel="stylesheet" type="text/css" href="assets/css/floating_labels.css">
    </head>
    <body>
        
        <form class="form-signin" name="formLogin" action="Autenticador" method="POST">
            <%
                if(request.getParameter("e") != null){
                    int grp_acesso_sgdis = Integer.parseInt(request.getParameter("e"));

                    if(grp_acesso_sgdis == 1){
                        out.println("<div class=\"alert alert-danger col-md-6 mr-auto ml-auto shadow-sm text-center\" role=\"alert\">");
                        out.println("       Identidade Inválida!<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    else if(grp_acesso_sgdis == 2){
                        out.println("<div class=\"alert alert-danger col-md-6 mr-auto ml-auto shadow-sm text-center\" role=\"alert\">");
                        out.println("       Senha Inválida!<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    else if(grp_acesso_sgdis == 3){
                        out.println("<div class=\"alert alert-danger col-md-6 mr-auto ml-auto shadow-sm text-center\" role=\"alert\">");
                        out.println("       Identidade e Senha Inválidas!<br>Tente Novamente!");
                        out.println("</div>");
                    }
                    else if(grp_acesso_sgdis == 4){
                        out.println("<div class=\"alert alert-danger col-md-6 mr-auto ml-auto shadow-sm text-center\" role=\"alert\">");
                        out.println("       Sessão Encerrada!<br>Tente Novamente!");
                        out.println("</div>");
                    }
                }
            %>
            <div class="text-center col-md-3 form form-signin">
                <div class="col-md-12">
                    <b class="text-center">Controle de Visitantes</b>
                </div>
                <img class="col-4" src="assets/img/logo_sgdis.png" alt="Sistema de Controle de Visitantes" >                                       

                <div class="form-label-group">
                    <label for="txtIdentidade">Identidade: </label>
                    <input type="text" id="txtIdentidade" name="txtIdentidade" class="form-control" placeholder="Identidade" required autofocus>
                </div>
                
                <div class="form-label-group">
                    <label for="txtSenha">Senha: </label>
                    <input type="password" id="txtSenha" name="txtSenha" class="form-control" placeholder="Senha" required>
                </div>
                
                <button type="submit" class="btn btn-primary btn-block btn-success">Entrar</button>
                <p class="mt-3 text-center">Desenvolvido por <b>CB</b> Anderson <b>De Paula</b>Andrade Medeiros</p>
                <p class="mb-3 text-center copy">&copy; 2020</p>
            </div>
        </form>
    </body>
</html>
