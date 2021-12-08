//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//01.08.2020

var cpfMilitarOk = 0;
var cpfCivilOk = 0;
//Ao carregar ou recarregar a página
$(document).ready(function(){   
    //Campo Select Visitante
    validSelect("select[name=txtTipoVisitante]");
    validSelectTReal("select[name=txtTipoVisitante]");
    
    //Campo Input Cpf Militar
    validCPF("input[name=txtCpfMilitar]");
    validCPFTReal("input[name=txtCpfMilitar]"); 
    
    //Campo Input Identidade Militar
    validIdentidadeMilitar("input[name=txtIdentidadeMilitar]");
    validIdentidadeMilitarTReal("input[name=txtIdentidadeMilitar]"); 
   
    //Campo Input Cpf Civil
    validCPF("input[name=txtCpfCivil]");
    validCPFTReal("input[name=txtCpfCivil]"); 
   
    //Campo Input Identidade Civil
    validIdentidadeCivil("input[name=txtIdentidadeCivil]");
    validIdentidadeCivilTReal("input[name=txtIdentidadeCivil]"); 
    
    //Campo Select Tipo Força
    validSelect("select[name=txtTipoForca]");
    validSelectTReal("select[name=txtTipoForca]");
    
    //Campo Select Força
    validSelect("select[name=txtForca]");
    validSelectTReal("select[name=txtForca]");
    
    //Campo Select Posto/Graduação
    validSelect("select[name=txtPGrad]");
    validSelectTReal("select[name=txtPGrad]");
    
    //Campo Select Organização Militar
    validSelect("select[name=txtOM]");
    validSelectTReal("select[name=txtOM]");       
    
    //Campo Input Nome
    validInput("input[name=txtNomeMilitar]");
    validInputTReal("input[name=txtNomeMilitar]");
    
    //Campo Input Sobrenome
    validInput("input[name=txtSobrenomeMilitar]");
    validInputTReal("input[name=txtSobrenomeMilitar]");
    
    //Campo Input Nome
    validInput("input[name=txtNomeCivil]");
    validInputTReal("input[name=txtNomeCivil]");
    
    //Campo Input Sobrenome
    validInput("input[name=txtSobrenomeCivil]");
    validInputTReal("input[name=txtSobrenomeCivil]");
    
    //Campo Input Nome Guerra
    validInput("input[name=txtNomeGuerra]");
    validInputTReal("input[name=txtNomeGuerra]");
    
    //Campo Input Email
    validEmail("input[name=txtEmailMilitar]");
    validEmailTReal("input[name=txtEmailMilitar]");
    
    //Campo Input Email
    validEmail("input[name=txtEmailCivil]");
    validEmailTReal("input[name=txtEmailCivil]");
    
    //Campo Input Email
    validFoneCel("input[name=txtFoneMilitar]");
    validFoneCelTReal("input[name=txtFoneMilitar]");
    
    //Campo Input Email
    validFoneCel("input[name=txtFoneCivil]");
    validFoneCelTReal("input[name=txtFoneCivil]");
});

$("select[name=txtTipoVisitante]").change(function(){
    limpaCampoInput("input[name=txtIdentidadeMilitar]");
    limpaCampoInput("input[name=txtCpfMilitar]");
    limpaCampoInput("input[name=txtIdentidadeCivil]");
    limpaCampoInput("input[name=txtCpfCivil]");
    limpaCampoSelect("select[name=txtTipoForca");
    limpaCampoSelect("select[name=txtForca");
    limpaCampoSelect("select[name=txtPGrad");
    limpaCampoSelect("select[name=txtOM");
    limpaCampoInput("input[name=txtNomeMilitar]");
    limpaCampoInput("input[name=txtNomeCivil]");
    limpaCampoInput("input[name=txtSobrenomeMilitar]");
    limpaCampoInput("input[name=txtSobrenomeCivil]");
    limpaCampoInput("input[name=txtNomeGuerra]");
    limpaCampoInput("input[name=txtEmailMilitar]");
    limpaCampoInput("input[name=txtEmailCivil]");
    limpaCampoInput("input[name=txtFoneMilitar]");
    limpaCampoInput("input[name=txtFoneCivil]");
});

//Obrigatoriedade
$("button[name=btnSalvarCadastro]").click(function(){     
    if($("select[name=txtTipoVisitante]").val() == '0'){
        $("select[name=txtTipoVisitante]").removeClass("is-valid");
        $("select[name=txtTipoVisitante]").addClass("is-invalid");
        $("select[name=txtTipoVisitante]").focus();
        return false;
    }   
    else if($("select[name=txtTipoVisitante]").val() == '1'){
        var cpfMilitar = $("input[name=txtCpfMilitar]").val().replace(".", "").replace(".","").replace("-","");    
        var identidadeMilitar = $("input[name=txtIdentidadeMilitar]").val().replace("-", "");    
        var emailMilitar = $("input[name=txtEmailMilitar]").val(); 
        var foneCelMilitar = $("input[name=txtFoneMilitar]").val().replace("-","").replace("(","").replace(")","").replace(" ",""); 
        var filtro = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
        
        //CPF
        if(cpfMilitar == ''){
            $("input[name=txtCpfMilitar]").removeClass("is-valid");
            $("input[name=txtCpfMilitar]").addClass("is-invalid");
            $("input[name=txtCpfMilitar]").focus();
            $(".invalid-cpf").html("Campo Obrigatório!");
            return false;
        }
        else if(cpfMilitar == '00000000000' || cpfMilitar == '11111111111' || cpfMilitar == '22222222222' || cpfMilitar == '33333333333' ||                 
                cpfMilitar == '44444444444' || cpfMilitar == '55555555555' || cpfMilitar == '66666666666' || cpfMilitar == '77777777777' ||                 
                cpfMilitar == '88888888888' || cpfMilitar == '99999999999'){
            $("input[name=txtCpfMilitar]").removeClass("is-valid");
            $("input[name=txtCpfMilitar]").addClass("is-invalid");
            $("input[name=txtCpfMilitar]").focus();
            $(".invalid-cpf").html("CPF Inválido!");
            return false;
        }        
        else if(cpfMilitarOk == 0){
            // Valida 1o digito	
            var add = 0;	
            for (var i=0; i < 9; i ++)		
                add += parseInt(cpfMilitar.charAt(i)) * (10 - i);	
            var rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)		
                rev = 0;	
            if (rev != parseInt(cpfMilitar.charAt(9))){
                $("input[name=txtCpfMilitar]").removeClass("is-valid");
                $("input[name=txtCpfMilitar]").addClass("is-invalid");
                $("input[name=txtCpfMilitar]").focus();
                $(".invalid-cpf").html("CPF Inválido!");
                return false;

            }else{
                $("input[name=txtCpfMilitar]").removeClass("is-invalid");
                $("input[name=txtCpfMilitar]").addClass("is-valid");
                cpfMilitarOk=1;
                return false;
            }
            // Valida 2o digito	
            add = 0;	
            for (var i = 0; i < 10; i ++)		
                add += parseInt(cpfMilitar.charAt(i)) * (11 - i);	
            rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)	
                rev = 0;	
            if (rev != parseInt(cpfMilitar.charAt(10))){
                $("input[name=txtCpfMilitar]").removeClass("is-valid");
                $("input[name=txtCpfMilitar]").addClass("is-invalid");
                $("input[name=txtCpfMilitar]").focus();
                $(".invalid-cpf").html("CPF Inválido!");
                return false;
                
            }else{
                $("input[name=txtCpfMilitar]").removeClass("is-invalid");
                $("input[name=txtCpfMilitar]").addClass("is-valid");
                cpfMilitarOk=1;
                return false;
            }    
        } 
        else if(identidadeMilitar == ''){
            $("input[name=txtIdentidadeMilitar]").removeClass("is-valid");
            $("input[name=txtIdentidadeMilitar]").addClass("is-invalid");
            $("input[name=txtIdentidadeMilitar]").focus();
            $(".invalid-identidademilitar").html("Campo Obrigatório!");
            return false;
        }
        else if(identidadeMilitar == '0000000000' || identidadeMilitar == '1111111111' || identidadeMilitar == '2222222222' || identidadeMilitar == '3333333333' ||                 
                identidadeMilitar == '4444444444' || identidadeMilitar == '5555555555' || identidadeMilitar == '6666666666' || identidadeMilitar == '7777777777' ||                 
                identidadeMilitar == '8888888888' || identidadeMilitar == '9999999999'){
            $("input[name=txtIdentidadeMilitar]").removeClass("is-valid");
            $("input[name=txtIdentidadeMilitar]").addClass("is-invalid");
            $(".invalid-identidademilitar").html("Identidade Militar Inválida!");
            $("input[name=txtIdentidadeMilitar]").focus();
            return false;
        }
        else if($("select[name=txtTipoForca]").val() == '0'){
            $("select[name=txtTipoForca]").removeClass("is-valid");
            $("select[name=txtTipoForca]").addClass("is-invalid");
            $("select[name=txtTipoForca]").focus();
            return false;
        } 
        else if($("select[name=txtForca]").val() == '0'){
            $("select[name=txtForca]").removeClass("is-valid");
            $("select[name=txtForca]").addClass("is-invalid");
            $("select[name=txtForca]").focus();
            return false;
        } 
        else if($("select[name=txtPGrad]").val() == '0'){
            $("select[name=txtPGrad]").removeClass("is-valid");
            $("select[name=txtPGrad]").addClass("is-invalid");
            $("select[name=txtPGrad]").focus();
            return false;
        } 
        else if($("select[name=txtOM]").val() == '0'){
            $("select[name=txtOM]").removeClass("is-valid");
            $("select[name=txtOM]").addClass("is-invalid");
            $("select[name=txtOM]").focus();
            return false;
        }
        else if($("input[name=txtNomeMilitar]").val() == ''){
            $("input[name=txtNomeMilitar]").removeClass("is-valid");
            $("input[name=txtNomeMilitar]").addClass("is-invalid");
            $("input[name=txtNomeMilitar]").focus();
            return false;
        }
        else if($("input[name=txtSobrenomeMilitar]").val() == ''){
            $("input[name=txtSobrenomeMilitar]").removeClass("is-valid");
            $("input[name=txtSobrenomeMilitar]").addClass("is-invalid");
            $("input[name=txtSobrenomeMilitar]").focus();
            return false;
        }
        else if($("input[name=txtNomeGuerra]").val() == ''){
            $("input[name=txtNomeGuerra]").removeClass("is-valid");
            $("input[name=txtNomeGuerra]").addClass("is-invalid");
            $("input[name=txtNomeGuerra]").focus();
            return false;
        }
        else if(emailMilitar == ''){
            $("input[name=txtEmailMilitar]").removeClass("is-valid");
            $("input[name=txtEmailMilitar]").addClass("is-invalid");
            $("input[name=txtEmailMilitar]").focus();
            $(".invalid-emailmilitar").html("Campo Obrigatório!");
            return false;
        }        
        else if(filtro.test(emailMilitar) == false){
            $("input[name=txtEmailMilitar]").removeClass("is-valid");
            $("input[name=txtEmailMilitar]").addClass("is-invalid");
            $("input[name=txtEmailMilitar]").focus();
            $(".invalid-emailmilitar").html("Email Inválido!");
            return false;
        }
        else if(foneCelMilitar == ''){
            $("input[name=txtFoneMilitar]").removeClass("is-valid");
            $("input[name=txtFoneMilitar]").addClass("is-invalid");
            $("input[name=txtFoneMilitar]").focus();
            $(".invalid-fonecelmilitar").html("Campo Obrigatório!");
            return false;
        }
        else if(foneCelMilitar == '00000000000' || foneCelMilitar == '11111111111' || foneCelMilitar == '22222222222' || foneCelMilitar == '33333333333' ||                 
                foneCelMilitar == '44444444444' || foneCelMilitar == '55555555555' || foneCelMilitar == '66666666666' || foneCelMilitar == '77777777777' ||                 
                foneCelMilitar == '88888888888' || foneCelMilitar == '99999999999'){
            $("input[name=txtFoneMilitar]").removeClass("is-valid");
            $("input[name=txtFoneMilitar]").addClass("is-invalid");
            $("input[name=txtFoneMilitar]").focus();
            $(".invalid-fonecelmilitar").html("Fone Inválido!");
            return false
        }
        else{        
            return true;
        }
    }  
    else if($("select[name=txtTipoVisitante]").val() == '2'){
        var cpfCivil = $("input[name=txtCpfCivil]").val().replace(".", "").replace(".","").replace("-","");                    
        var identidadeCivil = $("input[name=txtIdentidadeCivil]").val().replace("-", "");                    
        var emailCivil = $("input[name=txtEmailCivil]").val(); 
        var foneCelCivil = $("input[name=txtFoneCivil]").val().replace("-","").replace("(","").replace(")","").replace(" ","");        
        var filtro = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
        
        //CPF
        if(cpfCivil == ''){
            $("input[name=txtCpfCivil]").removeClass("is-valid");
            $("input[name=txtCpfCivil]").addClass("is-invalid");
            $("input[name=txtCpfCivil]").focus();
            $(".invalid-cpf").html("Campo Obrigatório!");
            return false;
        }
        else if(cpfCivil == '00000000000' || cpfCivil == '11111111111' || cpfCivil == '22222222222' || cpfCivil == '33333333333' ||                 
                cpfCivil == '44444444444' || cpfCivil == '55555555555' || cpfCivil == '66666666666' || cpfCivil == '77777777777' ||                 
                cpfCivil == '88888888888' || cpfCivil == '99999999999'){
            $("input[name=txtCpfCivil]").removeClass("is-valid");
            $("input[name=txtCpfCivil]").addClass("is-invalid");
            $("input[name=txtCpfCivil]").focus();
            $(".invalid-cpf").html("CPF Inválido!");
            return false;
        }        
        else if(cpfCivilOk == 0){
            // Valida 1o digito	
            var add = 0;	
            for (var i=0; i < 9; i ++)		
                add += parseInt(cpfCivil.charAt(i)) * (10 - i);	
            var rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)		
                rev = 0;	
            if (rev != parseInt(cpfCivil.charAt(9))){
                $("input[name=txtCpfCivil]").removeClass("is-valid");
                $("input[name=txtCpfCivil]").addClass("is-invalid");
                $("input[name=txtCpfCivil]").focus();
                $(".invalid-cpf").html("CPF Inválido!");
                return false;

            }else{
                $("input[name=txtCpfCivil]").removeClass("is-invalid");
                $("input[name=txtCpfCivil]").addClass("is-valid");
                cpfCivilOk=1;
                return false;
            }
            // Valida 2o digito	
            add = 0;	
            for (var i = 0; i < 10; i ++)		
                add += parseInt(cpfCivil.charAt(i)) * (11 - i);	
            rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)	
                rev = 0;	
            if (rev != parseInt(cpfCivil.charAt(10))){
                $("input[name=txtCpfCivil]").removeClass("is-valid");
                $("input[name=txtCpfCivil]").addClass("is-invalid");
                $("input[name=txtCpfCivil]").focus();
                $(".invalid-cpf").html("CPF Inválido!");
                return false;
                
            }else{
                $("input[name=txtCpfCivil]").removeClass("is-invalid");
                $("input[name=txtCpfCivil]").addClass("is-valid");
                cpfCivilOk=1;
                return false;
            }    
        } 
        else if(identidadeCivil == ''){
            $("input[name=txtIdentidadeCivil]").removeClass("is-valid");
            $("input[name=txtIdentidadeCivil]").addClass("is-invalid");
            $("input[name=txtIdentidadeCivil]").focus();
            $(".invalid-identidadecivil").html("Campo Obrigatório!");
            return false;
        }
        else if(identidadeCivil == '00000000' || identidadeCivil == '11111111' || identidadeCivil == '22222222' || identidadeCivil == '33333333' ||                 
                identidadeCivil == '44444444' || identidadeCivil == '55555555' || identidadeCivil == '66666666' || identidadeCivil == '77777777' ||                 
                identidadeCivil == '88888888' || identidadeCivil == '99999999'){
            $("input[name=txtIdentidadeCivil]").removeClass("is-valid");
            $("input[name=txtIdentidadeCivil]").addClass("is-invalid");

            $(".invalid-identidadecivil").html("Identidade Inválida!");
            $("input[name=txtIdentidadeCivil]").focus();
            return false;
        }        
        else if($("input[name=txtNomeCivil]").val() == ''){
            $("input[name=txtNomeCivil]").removeClass("is-valid");
            $("input[name=txtNomeCivil]").addClass("is-invalid");
            $("input[name=txtNomeCivil]").focus();
            return false;
        }
        else if($("input[name=txtSobrenomeCivil]").val() == ''){
            $("input[name=txtSobrenomeCivil]").removeClass("is-valid");
            $("input[name=txtSobrenomeCivil]").addClass("is-invalid");
            $("input[name=txtSobrenomeCivil]").focus();
            return false;
        }
        else if(emailCivil == ''){
            $("input[name=txtEmailCivil]").removeClass("is-valid");
            $("input[name=txtEmailCivil]").addClass("is-invalid");
            $("input[name=txtEmailCivil]").focus();
            $(".invalid-emailcivil").html("Campo Obrigatório!");
            return false;
        }        
        else if(filtro.test(emailCivil) == false){
            $("input[name=txtEmailCivil]").removeClass("is-valid");
            $("input[name=txtEmailCivil]").addClass("is-invalid");
            $("input[name=txtEmailCivil]").focus();
            $(".invalid-emailcivil").html("Email Inválido!");
            return false;
        }
        else if(foneCelCivil == ''){
            $("input[name=txtFoneCivil]").removeClass("is-valid");
            $("input[name=txtFoneCivil]").addClass("is-invalid");
            $("input[name=txtFoneCivil]").focus();
            $(".invalid-fonecelcivil").html("Campo Obrigatório!");
            return false;
        }
        else if(foneCelCivil == '00000000000' || foneCelCivil == '11111111111' || foneCelCivil == '22222222222' || foneCelCivil == '33333333333' ||                 
                foneCelCivil == '44444444444' || foneCelCivil == '55555555555' || foneCelCivil == '66666666666' || foneCelCivil == '77777777777' ||                 
                foneCelCivil == '88888888888' || foneCelCivil == '99999999999'){
            $("input[name=txtFoneCivil]").removeClass("is-valid");
            $("input[name=txtFoneCivil]").addClass("is-invalid");
            $("input[name=txtFoneCivil]").focus();
            $(".invalid-fonecelcivil").html("Fone Inválido!");
            return false;
        }
        else{        
            return true;
        }
    }    
});

