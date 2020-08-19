//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//19.08.2020

function alimentaSelectDivisaoSecao(divisoessecoes){
    dwr.util.removeAllOptions("txtDivisaoSecao");
    dwr.util.addOptions("txtDivisaoSecao", [{id: "0", nome: "Selecione uma Divisão/Seção..."}], "id", "nome");
    dwr.util.addOptions("txtDivisaoSecao", divisoessecoes, "id", "nome");
}
FacadeAjax.getDivisoesSecoesDWR(alimentaSelectDivisaoSecao);

function alteraSetor(id){
    FacadeAjax.getSetorByIdDWR(id, {
        callback: function(setor){
            dwr.util.setValues({
                txtIdAtt: setor.id,
                txtNomeAtt: setor.nome,
                txtAbreviaturaAtt: setor.abreviatura,
                txtDivisaoSecaoAtt: setor.idDivisaoSecao
            });
        }
    });
}