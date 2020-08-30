//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//01.08.2020

function limpaAlimentacaoSelect(campo, primeiroSelect){
    dwr.util.removeAllOptions(campo);
    dwr.util.addOptions(campo, [{id: "0", nome: primeiroSelect}], "id", "nome");
}

$("select[name=txtTipoVisitante").change(function(){
    var tipoVisitante = $("select[name=txtTipoVisitante]").val();
   
    if(tipoVisitante == 0){
        $("#formVisitanteCivil").css("display", "none");
        $("#formVisitanteMilitar").css("display", "none");
    }
    else if(tipoVisitante == 1){
        $("#formVisitanteCivil").css("display", "none");
        $("#formVisitanteMilitar").css("display", "block");
    }
    else if(tipoVisitante == 2){
        $("#formVisitanteMilitar").css("display", "none");
        $("#formVisitanteCivil").css("display", "block");
    }
});

$(document).ready(function(){
    var tipoVisitante = $("select[name=txtTipoVisitante]").val();
   
    if(tipoVisitante == 0){
        $("#formVisitanteCivil").css("display", "none");
        $("#formVisitanteMilitar").css("display", "none");
    }
    else if(tipoVisitante == 1){
        $("#formVisitanteCivil").css("display", "none");
        $("#formVisitanteMilitar").css("display", "block");
    }
    else if(tipoVisitante == 2){
        $("#formVisitanteMilitar").css("display", "none");
        $("#formVisitanteCivil").css("display", "block");
    }    
});

function alimentaSelectTipoForca(tiposforca){
    dwr.util.removeAllOptions("txtTipoForca");
    dwr.util.addOptions("txtTipoForca", [{id: "0", nome: "Selecione um Tipo de Força..."}], "id", "nome");
    dwr.util.addOptions("txtTipoForca", tiposforca, "id", "nome");
}
FacadeAjax.getTiposForcaDWR(alimentaSelectTipoForca);

function alimentaSelectTipoForcaAtt(tiposforca){    
    dwr.util.removeAllOptions("txtTipoForcaAtt");
    dwr.util.addOptions("txtTipoForcaAtt", [{id: "0", nome: "Selecione um Tipo de Força..."}], "id", "nome");
    dwr.util.addOptions("txtTipoForcaAtt", tiposforca, "id", "nome");
}
FacadeAjax.getTiposForcaDWR(alimentaSelectTipoForcaAtt);

function alimentaSelectDependenteTipoForca(idTipoForca){
    FacadeAjax.getForcasByIdTipoForcaDWR(idTipoForca, {
        callback: function(forcas){
            dwr.util.removeAllOptions("txtForca");
            dwr.util.addOptions("txtForca", [{id: "0", nome: "Selecione uma Força..."}], "id", "nome");
            dwr.util.addOptions("txtForca", forcas, "id", "nome");
        } 
    });
}

function alimentaSelectDependenteTipoForcaAtt(idTipoForca){
    FacadeAjax.getForcasByIdTipoForcaDWR(idTipoForca, {
        callback: function(forcas){
            dwr.util.removeAllOptions("txtForcaAtt");
            dwr.util.addOptions("txtForcaAtt", [{id: "0", nome: "Selecione uma Força..."}], "id", "nome");
            dwr.util.addOptions("txtForcaAtt", forcas, "id", "nome");
        } 
    });
}

function alimentaSelectDependenteForca(idForca){
    FacadeAjax.getPGsByForcaDWR(idForca, {
        callback: function(postosgraduacoes){            
            dwr.util.removeAllOptions("txtPGrad");
            dwr.util.addOptions("txtPGrad", [{id: "0", nome: "Selecione um Posto/Graduação..."}], "id", "nome");
            dwr.util.addOptions("txtPGrad", postosgraduacoes, "id", "nome");
        }
    });         
    
    FacadeAjax.getOmsByForcaDWR(idForca, {
        callback: function(oms){            
            dwr.util.removeAllOptions("txtOM");
            dwr.util.addOptions("txtOM", [{id: "0", nome: "Selecione uma Organização Militar..."}], "id", "nome");
            dwr.util.addOptions("txtOM", oms, "id", "nome");
        }
    });         
}

function alimentaSelectDependenteForcaAtt(idForca){
    FacadeAjax.getPGsByForcaDWR(idForca, {
        callback: function(postosgraduacoes){                        
            dwr.util.removeAllOptions("txtPGradAtt");
            dwr.util.addOptions("txtPGradAtt", [{id: "0", nome: "Selecione um Posto/Graduação..."}], "id", "nome");
            dwr.util.addOptions("txtPGradAtt", postosgraduacoes, "id", "nome");
        }
    });         
    
    FacadeAjax.getOmsByForcaDWR(idForca, {
        callback: function(oms){                        
            dwr.util.removeAllOptions("txtOMAtt");
            dwr.util.addOptions("txtOMAtt", [{id: "0", nome: "Selecione uma Organização Militar..."}], "id", "nome");
            dwr.util.addOptions("txtOMAtt", oms, "id", "nome");
        }
    });         
}

$("select[name=txtTipoForca").change(function(){
    limpaAlimentacaoSelect("txtForca", "Selecione uma Forca...");
    limpaAlimentacaoSelect("txtPGrad", "Selecione um Posto/Graduação...");
    limpaAlimentacaoSelect("txtOM", "Selecione uma Organização Militar...");
    alimentaSelectDependenteTipoForca(this.value);
});

$("select[name=txtTipoForcaAtt").change(function(){
    limpaAlimentacaoSelect("txtForcaAtt", "Selecione uma Forca...");
    limpaAlimentacaoSelect("txtPGradAtt", "Selecione um Posto/Graduação...");
    limpaAlimentacaoSelect("txtOMAtt", "Selecione uma Organização Militar...");
    alimentaSelectDependenteTipoForcaAtt(this.value);
});

$("select[name=txtForca").change(function(){
    limpaAlimentacaoSelect("txtPGrad", "Selecione um Posto/Graduação...");
    limpaAlimentacaoSelect("txtOM", "Selecione uma Organização Militar...");
    alimentaSelectDependenteForca(this.value);
});

$("select[name=txtForcaAtt").change(function(){
    limpaAlimentacaoSelect("txtPGradAtt", "Selecione um Posto/Graduação...");
    limpaAlimentacaoSelect("txtOMAtt", "Selecione uma Organização Militar...");
    alimentaSelectDependenteForcaAtt(this.value);
});

function alteraVisitanteMilitar(identidade){
    FacadeAjax.getVisitanteByIdentidadeDWR(identidade, {
        callback: function(visitante){
            dwr.util.setValues({
                txtIdentidadeMilitarAtt: visitante.identidade,
                txtTipoVisitante: visitante.tipo,
                //txtTipoForcaAtt: visitante.idTipoForcaPostoGraduacao,
                //txtForcaAtt: visitante.idForcaPostoGraduacao,
                //txtPGradAtt: visitante.idPostoGraduacao,
                //txtOMAtt: visitante.idOM,
                txtNomeMilitarAtt: visitante.nome,
                txtSobrenomeMilitarAtt: visitante.sobrenome,
                txtNomeGuerraAtt: visitante.nomeguerra,
                txtEmailMilitarAtt: visitante.email,
                txtFoneMilitarAtt: visitante.fone
            });
        }
    });
}

function alteraVisitanteCivil(identidade){
    FacadeAjax.getVisitanteByIdentidadeDWR(identidade, {
        callback: function(visitante){
            dwr.util.setValues({
                txtIdentidadeCivilAtt: visitante.identidade,
                txtTipoVisitante: visitante.tipo,
                txtNomeCivilAtt: visitante.nome,
                txtSobrenomeCivilAtt: visitante.sobrenome,
                txtEmailCivilAtt: visitante.email,
                txtFoneCivilAtt: visitante.fone
            });
        }
    });
}