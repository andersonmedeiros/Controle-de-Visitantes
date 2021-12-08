//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//21.08.2020

$("#txtVeiculoEntrar").change(function() {
    if($("#txtVeiculoEntrar").val() === "s"){
        $("#divVeiculos").css("display", "block");        
    }
    else if($("#txtVeiculoEntrar").val() === "n" || $("#txtVeiculoEntrar").val() === "0"){
        $("#divVeiculos").css("display", "none");

        $("select[name=txtTipoVeiculoAl]").val('0');
        $("select[name=txtTipoVeiculoAl]").removeClass("is-invalid");
        $("select[name=txtTipoVeiculoAl]").removeClass("is-valid");

        $("input[name=txtMarcaVeiculoAl]").val('');            
        $("input[name=txtMarcaVeiculoAl]").removeClass("is-invalid");
        $("input[name=txtMarcaVeiculoAl]").removeClass("is-valid");

        $("input[name=txtModeloVeiculoAl]").val('');            
        $("input[name=txtModeloVeiculoAl]").removeClass("is-invalid");
        $("input[name=txtModeloVeiculoAl]").removeClass("is-valid");

        $("input[name=txtCorVeiculoAl]").val('');            
        $("input[name=txtCorVeiculoAl]").removeClass("is-invalid");
        $("input[name=txtCorVeiculoAl]").removeClass("is-valid");

        $("input[name=txtPlacaVeiculoAl]").val('');            
        $("input[name=txtPlacaVeiculoAl]").removeClass("is-invalid");
        $("input[name=txtPlacaVeiculoAl]").removeClass("is-valid");
    }
});

$("select[name=txtTipoVisitante]").change(function(){
    $("input[name=txtIdentidadeMilitar").val('');
    $("input[name=txtIdentidadeCivil").val('');
    
    var tipoVisitante = $("select[name=txtTipoVisitante]").val();
    
    if(tipoVisitante == 1){
        $("#div-idtcivil").css("display", "none");
        $("#div-idtmilitar").css("display", "block");
    }else if(tipoVisitante == 2){
        $("#div-idtmilitar").css("display", "none");
        $("#div-idtcivil").css("display", "block");
    }
});

function getVisitanteByCpf(cpf){
    FacadeAjax.getVisitanteByCpfDWR(cpf, {
        callback:function(visitante){
            if(visitante.nome != null){
                $("#div-visnencontrado").css("display", "none");
                $("#formVisita").css("display", "block");
                dwr.util.setValues({txtNomeVisitante: visitante.nome + " " + visitante.sobrenome});
            }
            else{
                $("#formVisita").css("display", "none");
                $("#div-visnencontrado").css("display", "block");
            }
        }
    }); 
}

function alimentaSelectSetor(setores){
    dwr.util.removeAllOptions("txtSetorVisitado");
    dwr.util.addOptions("txtSetorVisitado", [{id: "0", nome: "Selecione um Setor..."}], "id", "nome");
    dwr.util.addOptions("txtSetorVisitado", setores, "id", "nome");
}
FacadeAjax.getSetoresDWR(alimentaSelectSetor);