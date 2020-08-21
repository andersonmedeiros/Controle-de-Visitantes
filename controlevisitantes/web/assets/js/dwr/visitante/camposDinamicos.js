//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//01.08.2020

FacadeAjax.getPGsByForcaDWR(2, {
    callback: function(pgs){
        dwr.util.removeAllOptions("txtPGrad");
        dwr.util.addOptions("txtPGrad", [{id: "0", nome: "Selecione um Posto/Graduação..."}], "id", "nome");
        dwr.util.addOptions("txtPGrad", pgs, "id", "nome");
    } 
});

function alteraUsuario(identidade){
    FacadeAjax.getUsuarioByIdentidadeDWR(identidade, {
        callback: function(usuario){
            dwr.util.setValues({
                txtIdtAtt: usuario.identidade,
                txtPGrad: usuario.idPostoGraduacao,
                txtNome: usuario.nome,
                txtSobrenome: usuario.sobrenome,
                txtNomeGuerra: usuario.nomeguerra
            });
        }
    });
}

function alteraSenhaUsuario(identidade){
    FacadeAjax.getUsuarioByIdentidadeDWR(identidade, {
        callback: function(usuario){
            dwr.util.setValues({
                txtIdtAttSenha: usuario.identidade
            });
        }
    });
}