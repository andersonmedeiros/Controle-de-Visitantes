//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//01.08.2020

function limpaAlimentacaoSelect(campo, primeiroSelect){
    dwr.util.removeAllOptions(campo);
    dwr.util.addOptions(campo, [{id: "0", nome: primeiroSelect}], "id", "nome");
}


function alimentaSelectTipoForca(tiposforca){
    dwr.util.removeAllOptions("txtTipoForca");
    dwr.util.addOptions("txtTipoForca", [{id: "0", nome: "Selecione um Tipo de Força..."}], "id", "nome");
    dwr.util.addOptions("txtTipoForca", tiposforca, "id", "nome");
}
FacadeAjax.getTiposForcaDWR(alimentaSelectTipoForca);

function alimentaSelectDependenteTipoForca(idTipoForca){
    FacadeAjax.getForcasByIdTipoForcaDWR(idTipoForca, {
        callback: function(forcas){
            dwr.util.removeAllOptions("txtForca");
            dwr.util.addOptions("txtForca", [{id: "0", nome: "Selecione uma Força..."}], "id", "nome");
            dwr.util.addOptions("txtForca", forcas, "id", "nome");
        } 
    });
}

function alimentaSelectTipoForcaAtt(tiposforca){
    dwr.util.removeAllOptions("txtTipoForcaAtt");
    dwr.util.addOptions("txtTipoForcaAtt", [{id: "0", nome: "Selecione um Tipo de Força..."}], "id", "nome");
    dwr.util.addOptions("txtTipoForcaAtt", tiposforca, "id", "nome");
}
FacadeAjax.getTiposForcaDWR(alimentaSelectTipoForcaAtt);
function alimentaSelectDependenteTipoForcaAtt(idTipoForca){
    FacadeAjax.getForcasByIdTipoForcaDWR(idTipoForca, {
        callback: function(forcas){
            dwr.util.removeAllOptions("txtForcaAtt");
            dwr.util.addOptions("txtForcaAtt", [{id: "0", nome: "Selecione uma Força..."}], "id", "nome");
            dwr.util.addOptions("txtForcaAtt", forcas, "id", "nome");
        } 
    });
}

$("select[name=txtTipoForca").change(function(){
    limpaAlimentacaoSelect("txtForca", "Selecione uma Forca...");
    alimentaSelectDependenteTipoForca(this.value);
});
$("select[name=txtTipoForcaAtt").change(function(){
    limpaAlimentacaoSelect("txtForcaAtt", "Selecione uma Forca...");
    alimentaSelectDependenteTipoForcaAtt(this.value);
    
});

function alteraOm(id){
    FacadeAjax.getOmByIdDWR(id, {
        callback: function(om){
            dwr.util.setValues({
                txtIdAtt: om.id,
                //txtTipoForcaAtt: om.idTipoForca,
                //txtForcaAtt: om.idForca,
                txtNomeAtt: om.nome,
                txtAbreviaturaAtt: om.abreviatura
            });
        }
    });   
}