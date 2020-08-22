//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//21.08.2020

//Ao carregar ou recarregar a página
$(document).ready(function(){   
    //Campo Select Tipo Forca
    validSelect("select[name=txtTipoForca]");
    validSelectTReal("select[name=txtTipoForca]");
    
    //Campo Select Força
    validSelect("select[name=txtForca]");
    validSelectTReal("select[name=txtForca]");
    
    //Campo Input Nome
    validInput("input[name=txtNome]");
    validInputTReal("input[name=txtNome]");
    
    //Campo Input Abreviatura
    validInput("input[name=txtAbreviatura]");
    validInputTReal("input[name=txtAbreviatura]");
});

$("select[name=txtTipoForca]").change(function(){
    limpaCampoSelect("select[name=txtForca");
    limpaCampoInput("input[name=txtNome]");
    limpaCampoInput("input[name=txtAbreviatura]");
});

$("select[name=txtForca]").change(function(){
    limpaCampoInput("input[name=txtNome]");
    limpaCampoInput("input[name=txtAbreviatura]");
});

//Obrigatoriedade
$("button[name=btnSalvarCadastro]").click(function(){     
    if($("select[name=txtTipoForca]").val() == '0'){
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

