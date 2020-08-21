//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//01.08.2020

//Ao carregar ou recarregar a página
$(document).ready(function(){   
    //Campo Select Tipo de Força
    validSelect("select[name=txtTipoForcaAtt]");
    validSelectTReal("select[name=txtTipoForcaAtt]");   
    
    //Campo Select Força
    validSelect("select[name=txtForcaAtt]");
    validSelectTReal("select[name=txtForcaAtt]");
    
    //Campo Select Posto/Graduação
    validSelect("select[name=txtPGradAtt]");
    validSelectTReal("select[name=txtPGradAtt]");   
    
    //Campo Select Organização Militar
    validSelect("select[name=txtOMAtt]");
    validSelectTReal("select[name=txtOMAtt]");   
    
    //Campo Input Nome
    validInput("input[name=txtNomeMilitarAtt]");
    validInputTReal("input[name=txtNomeMilitarAtt]");
    
    //Campo Input Sobrenome
    validInput("input[name=txtSobrenomeMilitarAtt]");
    validInputTReal("input[name=txtSobrenomeMilitarAtt]");
    
    //Campo Input Nome Guerra
    validInput("input[name=txtNomeGuerraAtt]");
    validInputTReal("input[name=txtNomeGuerraAtt]");
    
    //Campo Input Email
    validEmail("input[name=txtEmailMilitarAtt]");
    validEmailTReal("input[name=txtEmailMilitarAtt]");
    
    //Campo Input Fone
    validFoneCel("input[name=txtFoneMilitarAtt]");
    validFoneCelTReal("input[name=txtFoneMilitarAtt]");
});

$("button[name=btnAlterar]").click(function(){
    //Campo Select Tipo de Força
    validSelect("select[name=txtTipoForcaAtt]");   
    
    //Campo Select Força
    validSelect("select[name=txtForcaAtt]");
    
    //Campo Select Posto/Graduação
    validSelect("select[name=txtPGradAtt]");   
    
    //Campo Select Organização Militar
    validSelect("select[name=txtOMAtt]"); 
    
    //Campo Input Nome
    validInput("input[name=txtNomeMilitarAtt]");
    
    //Campo Input Sobrenome
    validInput("input[name=txtSobrenomeMilitarAtt]");
    
    //Campo Input Nome Guerra
    validInput("input[name=txtNomeGuerraAtt]");
    
    //Campo Input Email
    validEmail("input[name=txtEmailMilitarAtt]");
    
    //Campo Input Fone
    validFoneCel("input[name=txtFoneMilitarAtt]");
});

//Obrigatoriedade
$("button[name=btnSalvarAtualizacaoMilitar]").click(function(){
    var emailMilitar = $("input[name=txtEmailMilitarAtt]").val(); 
    var foneCelMilitar = $("input[name=txtFoneMilitarAtt]").val().replace("-","").replace("(","").replace(")","").replace(" ",""); 
    var filtro = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
    
    if($("select[name=txtTipoForcaAtt]").val() == '0'){
        $("select[name=txtTipoForcaAtt]").removeClass("is-valid");
        $("select[name=txtTipoForcaAtt]").addClass("is-invalid");
        $("select[name=txtTipoForcaAtt]").focus();
        return false;
    }   
    else if($("select[name=txtForcaAtt]").val() == '0'){
        $("select[name=txtForcaAtt]").removeClass("is-valid");
        $("select[name=txtForcaAtt]").addClass("is-invalid");
        $("select[name=txtForcaAtt]").focus();
        return false;
    }   
    else if($("select[name=txtPGradAtt]").val() == '0'){
        $("select[name=txtPGradAtt]").removeClass("is-valid");
        $("select[name=txtPGradAtt]").addClass("is-invalid");
        $("select[name=txtPGradAtt]").focus();
        return false;
    }   
    else if($("select[name=txtOMAtt]").val() == '0'){
        $("select[name=txtOMAtt]").removeClass("is-valid");
        $("select[name=txtOMAtt]").addClass("is-invalid");
        $("select[name=txtOMAtt]").focus();
        return false;
    }   
    else if($("input[name=txtNomeMilitarAtt]").val() == ''){
        $("input[name=txtNomeMilitarAtt]").removeClass("is-valid");
        $("input[name=txtNomeMilitarAtt]").addClass("is-invalid");
        $("input[name=txtNomeMilitarAtt]").focus();
        return false;
    }
    else if($("input[name=txtSobrenomeMilitarAtt]").val() == ''){
        $("input[name=txtSobrenomeMilitarAtt]").removeClass("is-valid");
        $("input[name=txtSobrenomeMilitarAtt]").addClass("is-invalid");
        $("input[name=txtSobrenomeMilitarAtt]").focus();
        return false;
    }
    else if($("input[name=txtNomeGuerraAtt]").val() == ''){
        $("input[name=txtNomeGuerraAtt]").removeClass("is-valid");
        $("input[name=txtNomeGuerraAtt]").addClass("is-invalid");
        $("input[name=txtNomeGuerraAtt]").focus();
        return false;
    }
    else if(emailMilitar == ''){
        $("input[name=txtEmailMilitarAtt]").removeClass("is-valid");
        $("input[name=txtEmailMilitarAtt]").addClass("is-invalid");
        $("input[name=txtEmailMilitarAtt]").focus();
        $(".invalid-email").html("Campo Obrigatório!");
        return false;
    }        
    else if(filtro.test(emailMilitar) == false){
        $("input[name=txtEmailMilitarAtt]").removeClass("is-valid");
        $("input[name=txtEmailMilitarAtt]").addClass("is-invalid");
        $("input[name=txtEmailMilitarAtt]").focus();
        $(".invalid-email").html("Email Inválido!");
        return false;
    }
    else if(foneCelMilitar == ''){
        $("input[name=txtFoneMilitarAtt]").removeClass("is-valid");
        $("input[name=txtFoneMilitarAtt]").addClass("is-invalid");
        $("input[name=txtFoneMilitarAtt]").focus();
        $(".invalid-foneCel").html("Campo Obrigatório!");
        return false;
    }
    else if(foneCelMilitar == '00000000000' || foneCelMilitar == '11111111111' || foneCelMilitar == '22222222222' || foneCelMilitar == '33333333333' ||                 
            foneCelMilitar == '44444444444' || foneCelMilitar == '55555555555' || foneCelMilitar == '66666666666' || foneCelMilitar == '77777777777' ||                 
            foneCelMilitar == '88888888888' || foneCelMilitar == '99999999999'){
        $("input[name=txtFoneMilitarAtt]").removeClass("is-valid");
        $("input[name=txtFoneMilitarAtt]").addClass("is-invalid");
        $("input[name=txtFoneMilitarAtt]").focus();
        $(".invalid-foneCel").html("Fone Inválido!");
        return false;
    }
    else{        
        return true;
    }
});

