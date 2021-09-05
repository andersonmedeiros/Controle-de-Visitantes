//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//21.08.2020

//Ao carregar ou recarregar a página
$(document).ready(function(){   
    //Campo Select Tipo Forca
    validSelect("select[name=txtTipoForcaCad]");
    validSelectTReal("select[name=txtTipoForcaCad]");
    
    //Campo Select Força
    validSelect("select[name=txtForcaCad]");
    validSelectTReal("select[name=txtForcaCad]");
    
    //Campo Input Nome
    validInput("input[name=txtNome]");
    validInputTReal("input[name=txtNome]");
    
    //Campo Input Abreviatura
    validInput("input[name=txtAbreviatura]");
    validInputTReal("input[name=txtAbreviatura]");
});

$("select[name=txtTipoForcaCad]").change(function(){
    limpaCampoSelect("select[name=txtForcaCad");
    limpaCampoInput("input[name=txtNome]");
    limpaCampoInput("input[name=txtAbreviatura]");
});

$("select[name=txtForca]").change(function(){
    limpaCampoInput("input[name=txtNome]");
    limpaCampoInput("input[name=txtAbreviatura]");
});

//Obrigatoriedade
$("button[name=btnSalvarCadastro]").click(function(){     
    if($("select[name=txtTipoForcaCad]").val() == '0'){
        $("select[name=txtTipoForcaCad]").removeClass("is-valid");
        $("select[name=txtTipoForcaCad]").addClass("is-invalid");
        $("select[name=txtTipoForcaCad]").focus();
        return false;
    } 
    else if($("select[name=txtForcaCad]").val() == '0'){
        $("select[name=txtForcaCad]").removeClass("is-valid");
        $("select[name=txtForcaCad]").addClass("is-invalid");
        $("select[name=txtForcaCad]").focus();
        return false;
    }
    else if($("input[name=txtNome]").val() == ''){
        $("input[name=txtNome]").removeClass("is-valid");
        $("input[name=txtNome]").addClass("is-invalid");
        $("input[name=txtNome]").focus();
        return false;
    }
    else if($("input[name=txtAbreviatura]").val() == ''){
        $("input[name=txtAbreviatura]").removeClass("is-valid");
        $("input[name=txtAbreviatura]").addClass("is-invalid");
        $("input[name=txtAbreviatura]").focus();
        return false;
    }
    else{        
        return true;
    }  
});

