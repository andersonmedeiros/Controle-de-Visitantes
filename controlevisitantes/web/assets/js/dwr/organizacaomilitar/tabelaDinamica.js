var colunasOrganizacoesMilitares = [
    function(oms){ return oms.id;},
    function(oms){ return oms.nome;},
    function(oms){ return oms.abreviatura;},
    function(oms){ return oms.nomeForca;},
    function(oms){ return "<div class=form-row>"+
                          " <div class=\"form-group mr-2\">"+
                          "     <form name=\"formExcluir\" method=\"POST\" action=\"controller.organizacaomilitar/ExcluirOm\">"+
                          "         <input type=\"hidden\" class=\"form-control\" name=\"idOmExcluir\" id=\"idOmExcluir\" readonly=\"readonly\" value=\""+oms.id+"\"/>"+
                          "         <button id="+oms.id+" type=\"submit\" name=\"btnExcluir\" class=\"btn btn-danger\" onclick=\"return confirm('Tem certeza que deseja excluir o registro?');\">Excluir</button>"+
                          "     </form>"+
                          " </div>"+
                          " <div class=\"form-group mr-2\">"+                
                          "     <button id="+oms.id+" type=\"button\" name=\"btnAlterar\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#modalFormAttOm\" onclick=\"alteraOm("+"'"+oms.id+"'"+");\">Alterar</button>"+
                          " </div>"+
                          "</div>";}
];

function getOmsByForca(idForca){
    FacadeAjax.getOmsByForcaDWR(idForca, {
        callback:function(oms){ 
            dwr.util.removeAllRows("oms");
            dwr.util.addRows("oms", oms, colunasOrganizacoesMilitares, { escapeHtml: false });            
        }
    });
}