//Ao carregar ou recarregar a página
$(document).ready(function(){
    limpaCampoSelect("select[name=txtTipoForca]");
    limpaCampoSelect("select[name=txtForca]");
    
    //Campo Select TipoForca
    validSelect("select[name=txtTipoForca]");
    validSelectTReal("select[name=txtTipoForca]");
    
    //Campo Select Força
    validSelect("select[name=txtForca]");
    validSelectTReal("select[name=txtForca]");
    
});

$("select[name=txtTipoForca]").change(function(){
   limpaCampoSelect("select[name=txtForca]");
});

//Obrigatoriedade
$("button[name=btnPesquisar]").click(function(){
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
    else{        
        var idForca = $("#txtForca").val();
        getOmsByForca(idForca);  
        
    }
});