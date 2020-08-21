//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//01.08.2020

//Ao carregar ou recarregar a página
$(document).ready(function(){   
    
    //Campo Input Nome
    validInput("input[name=txtNomeCivilAtt]");
    validInputTReal("input[name=txtNomeCivilAtt]");
    
    //Campo Input Sobrenome
    validInput("input[name=txtSobrenomeCivilAtt]");
    validInputTReal("input[name=txtSobrenomeCivilAtt]");
    
    //Campo Input Email
    validEmail("input[name=txtEmailCivilAtt]");
    validEmailTReal("input[name=txtEmailCivilAtt]");
    
    //Campo Input Fone
    validFoneCel("input[name=txtFoneCivilAtt]");
    validFoneCelTReal("input[name=txtFoneCivilAtt]");
});

$("button[name=btnAlterar]").click(function(){
    
    
    //Campo Input Nome
    validInput("input[name=txtNomeCivilAtt]");
    
    //Campo Input Sobrenome
    validInput("input[name=txtSobrenomeCivilAtt]");
        
    //Campo Input Email
    validEmail("input[name=txtEmailCivilAtt]");
    
    //Campo Input Fone
    validFoneCel("input[name=txtFoneCivilAtt]");
});

//Obrigatoriedade
$("button[name=btnSalvarAtualizacaoCivil]").click(function(){
    var emailCivil = $("input[name=txtEmailCivilAtt]").val(); 
    var foneCelCivil = $("input[name=txtFoneCivilAtt]").val().replace("-","").replace("(","").replace(")","").replace(" ",""); 
    var filtro = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
    
    if($("input[name=txtNomeCivilAtt]").val() == ''){
        $("input[name=txtNomeCivilAtt]").removeClass("is-valid");
        $("input[name=txtNomeCivilAtt]").addClass("is-invalid");
        $("input[name=txtNomeCivilAtt]").focus();
        return false;
    }
    else if($("input[name=txtSobrenomeCivilAtt]").val() == ''){
        $("input[name=txtSobrenomeCivilAtt]").removeClass("is-valid");
        $("input[name=txtSobrenomeCivilAtt]").addClass("is-invalid");
        $("input[name=txtSobrenomeCivilAtt]").focus();
        return false;
    }
    else if(emailCivil == ''){
        $("input[name=txtEmailCivilAtt]").removeClass("is-valid");
        $("input[name=txtEmailCivilAtt]").addClass("is-invalid");
        $("input[name=txtEmailCivilAtt]").focus();
        $(".invalid-email").html("Campo Obrigatório!");
        return false;
    }        
    else if(filtro.test(emailCivil) == false){
        $("input[name=txtEmailCivilAtt]").removeClass("is-valid");
        $("input[name=txtEmailCivilAtt]").addClass("is-invalid");
        $("input[name=txtEmailCivilAtt]").focus();
        $(".invalid-email").html("Email Inválido!");
        return false;
    }
    else if(foneCelCivil == ''){
        $("input[name=txtFoneCivilAtt]").removeClass("is-valid");
        $("input[name=txtFoneCivilAtt]").addClass("is-invalid");
        $("input[name=txtFoneCivilAtt]").focus();
        $(".invalid-foneCel").html("Campo Obrigatório!");
        return false;
    }
    else if(foneCelCivil == '00000000000' || foneCelCivil == '11111111111' || foneCelCivil == '22222222222' || foneCelCivil == '33333333333' ||                 
            foneCelCivil == '44444444444' || foneCelCivil == '55555555555' || foneCelCivil == '66666666666' || foneCelCivil == '77777777777' ||                 
            foneCelCivil == '88888888888' || foneCelCivil == '99999999999'){
        $("input[name=txtFoneCivilAtt]").removeClass("is-valid");
        $("input[name=txtFoneCivilAtt]").addClass("is-invalid");
        $("input[name=txtFoneCivilAtt]").focus();
        $(".invalid-foneCel").html("Fone Inválido!");
        return false;
    }
    else{        
        return true;
    }
});

