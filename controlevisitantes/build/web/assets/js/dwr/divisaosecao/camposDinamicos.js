//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//18.08.2020

function alteraDivisaoSecao(id){
    FacadeAjax.getDivisaoSecaoByIdDWR(id, {
        callback: function(divsec){
            dwr.util.setValues({
                txtIdAtt: divsec.id,
                txtNomeAtt: divsec.nome,
                txtAbreviaturaAtt: divsec.abreviatura
            });
        }
    });
}