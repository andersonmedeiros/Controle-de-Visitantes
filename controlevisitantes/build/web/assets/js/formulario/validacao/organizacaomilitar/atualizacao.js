//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//21.08.2020

//Ao carregar ou recarregar a página
$(document).ready(function(){   
    //Campo Select Tipo de Força
    validSelect("select[name=txtTipoForcaAtt]");
    validSelectTReal("select[name=txtTipoForcaAtt]");   
    
    //Campo Select Força
    validSelect("select[name=txtForcaAtt]");
    validSelectTReal("select[name=txtForcaAtt]");
    
    //Campo Input Nome
    validInput("input[name=txtNomeAtt]");
    validInputTReal("input[name=txtNomeAtt]");
    
    //Campo Input Abreviatura
    validInput("input[name=txtAbreviaturaAtt]");
    validInputTReal("input[name=txtAbreviaturaAtt]");
});

$("button[name=btnAlterar]").click(function(){
    //Campo Select Tipo de Força
    validSelect("select[name=txtTipoForcaAtt]");   
    
    //Campo Select Força
    validSelect("select[name=txtForcaAtt]"); 
    
    //Campo Input Nome
    validInput("input[name=txtNomeAtt]");
    
    //Campo Input Abreviatura
    validInput("input[name=txtAbreviaturaAtt]");
});

//Obrigatoriedade
$("button[name=btnSalvarAtualizacao]").click(function(){    
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
    else if($("input[name=txtNomeAtt]").val() == ''){
        $("input[name=txtNomeAtt]").removeClass("is-valid");
        $("input[name=txtNomeAtt]").addClass("is-invalid");
        $("input[name=txtNomeAtt]").focus();
        return false;
    }
    else if($("input[name=txtAbreviaturaAtt]").val() == ''){
        $("input[name=txtAbreviaturaAtt]").removeClass("is-valid");
        $("input[name=txtAbreviaturaAtt]").addClass("is-invalid");
        $("input[name=txtAbreviaturaAtt]").focus();
        return false;
    }
    else{        
        return true;
    }
});

