//Ao carregar ou recarregar a página
$(document).ready(function(){
    limpaCampoSelect("select[name=txtTipoRela]");
    limpaCampoInput("input[name=txtData]");
    
    //Campo Select Tipo Rela
    validSelect("select[name=txtTipoRela]");
    validSelectTReal("select[name=txtTipoRela]");
    
    //Campo Input Data
    validInput("input[name=txtData]");
    validInputTReal("input[name=txtData]");
    
});

//Obrigatoriedade
$("button[name=btnPesquisar]").click(function(){
    if($("select[name=txtTipoRela]").val() == '0'){
        $("select[name=txtTipoRela]").removeClass("is-valid");
        $("select[name=txtTipoRela]").addClass("is-invalid");
        $("select[name=txtTipoRela]").focus();
        return false;
    }
    else if($("input[name=txtData]").val() == ''){
        $("input[name=txtData]").removeClass("is-valid");
        $("input[name=txtData]").addClass("is-invalid");
        $("input[name=txtData]").focus();
        return false;
    }
    else{        
        var tipo = $("#txtTipoRela").val();
        var data = moment($("#txtData").val()).format('YYYY-MM-DD');
        getVisitasByTipoAndData(tipo, data);
        
    }
});