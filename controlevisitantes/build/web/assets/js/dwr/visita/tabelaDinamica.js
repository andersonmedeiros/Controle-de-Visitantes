$("select[name=txtTipoRela]").change(function(){
    dwr.util.removeAllRows("visitas");
    var tipoRela = $("select[name=txtTipoRela]").val();
    if(tipoRela == 0){
        $("#tipoRela>thead>tr>th").remove();
        $("#div-visitas").css("display", "none");
        $("#div-btn-pdf").css("display", "none");
        $("#div-visitas-0").css("display", "none");
    }
    else if(tipoRela == 1){
        $("#tbl-visitas>thead>tr>th").remove(); 
        $("#div-visitas-0").css("display", "none");
        $("#div-btn-pdf").css("display", "none");
        $("#div-visitas").css("display", "block");
        $("#tbl-visitas>thead>tr").append("<th>P/G</th>");
        $("#tbl-visitas>thead>tr").append("<th>NOME COMPLETO</th>");
        $("#tbl-visitas>thead>tr").append("<th>ENTRADA</th>");
        $("#tbl-visitas>thead>tr").append("<th>SAÍDA</th>");
        $("#tbl-visitas>thead>tr").append("<th>DESTINO</th>");
    }
    else if(tipoRela == 2){
        $("#tbl-visitas>thead>tr>th").remove();
        $("#div-visitas-0").css("display", "none");
        $("#div-btn-pdf").css("display", "none");
        $("#div-visitas").css("display", "block");
        $("#tbl-visitas>thead>tr").append("<th>NOME COMPLETO</th>");
        $("#tbl-visitas>thead>tr").append("<th>ENTRADA</th>");
        $("#tbl-visitas>thead>tr").append("<th>SAÍDA</th>");
        $("#tbl-visitas>thead>tr").append("<th>DESTINO</th>");
    }
});

var colunasVisitasMilOutrasOM = [
    function(visitas){ return visitas.abreviaturaPostoGraduacaoVisitante;},
    function(visitas){ return visitas.nomeVisitante + " " + visitas.sobrenomeVisitante;},
    function(visitas){ return moment(visitas.dataEntrada).format('YYYY-MM-DD') + " " + moment(visitas.horaEntrada).format('HH:mm:ss');},
    function(visitas){ return moment(visitas.dataSaida).format('YYYY-MM-DD') + " " + moment(visitas.horaSaida).format('HH:mm:ss');},
    function(visitas){ return visitas.abreviaturaSetor;}
];

var colunasVisitasCivis = [
    function(visitas){ return visitas.nomeVisitante + " " + visitas.sobrenomeVisitante;},
    function(visitas){ return moment(visitas.dataEntrada).format('YYYY-MM-DD') + " " + moment(visitas.horaEntrada).format('HH:mm:ss');},
    function(visitas){ return moment(visitas.dataSaida).format('YYYY-MM-DD') + " " + moment(visitas.horaSaida).format('HH:mm:ss');},
    function(visitas){ return visitas.abreviaturaSetor;}
];

function getVisitasByTipoAndData(tipo, data){
    FacadeAjax.getVisitasByTipoAndDataDWR(tipo, data, {        
        callback:function(visitas){ 
            dwr.util.removeAllRows("visitas");
            if(visitas.length == 0){                
                $("#div-visitas").css("display", "none");
                $("#div-visitas-0").css("display", "block");
            }
            else if(tipo == 1){                
                $("#div-visitas-0").css("display", "none");
                $("#div-visitas").css("display", "block");
                dwr.util.addRows("visitas", visitas, colunasVisitasMilOutrasOM, { escapeHtml: false });
            }
            else if(tipo == 2){
                $("#div-visitas-0").css("display", "none");
                $("#div-visitas").css("display", "block");
                dwr.util.addRows("visitas", visitas, colunasVisitasCivis, { escapeHtml: false }); 
            }
        }
    });
    $("#div-btn-pdf").css("display", "block");
}