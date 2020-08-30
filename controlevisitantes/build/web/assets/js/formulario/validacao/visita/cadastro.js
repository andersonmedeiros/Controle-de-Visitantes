//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//01.08.2020

//Ao carregar ou recarregar a página
$(document).ready(function(){   
    limpaCampoSelect("select[name=txtTipoVisitante]");
    limpaCampoInput("input[name=txtIdentidadeMilitar]");
    limpaCampoInput("input[name=txtIdentidadeCivil]");
    limpaCampoInput("input[name=txtNomeVisitante]");    
    
    //Campo Select Visitante
    validSelect("select[name=txtTipoVisitante]");
    validSelectTReal("select[name=txtTipoVisitante]");
    
    //Campo Input Identidade Militar
    validIdentidadeMilitar("input[name=txtIdentidadeMilitar]");
    validIdentidadeMilitarTReal("input[name=txtIdentidadeMilitar]"); 
   
    //Campo Input Identidade Civil
    validIdentidadeCivil("input[name=txtIdentidadeCivil]");
    validIdentidadeCivilTReal("input[name=txtIdentidadeCivil]"); 
    
    //Campo Input Cracha
    validInput("input[name=txtCracha]");
    validInputTReal("input[name=txtCracha]");
    
    //Campo Input Data Entrada
    validDataEntrada("input[name=txtDataEntrada]");
    validDataEntradaTReal("input[name=txtDataEntrada]");
    
    //Campo Input Hora Entrada
    validInput("input[name=txtHoraEntrada]");
    validInputTReal("input[name=txtHoraEntrada]");    
    
    //Campo Select Setor Visitado
    validSelect("select[name=txtSetorVisitado]");
    validSelectTReal("select[name=txtSetorVisitado]");
    
    //Campo Textarea Finalidade
    validInput("textarea[name=txtFinalidade]");
    validInputTReal("textarea[name=txtFinalidade]");    
    
    //Campo Select Entrar Veiculo
    validSelect("select[name=txtVeiculoEntrar]");
    validSelectTReal("select[name=txtVeiculoEntrar]");
    
    //Campo Select Tipo Veiculo
    validSelect("select[name=txtTipoVeiculo]");
    validSelectTReal("select[name=txtTipoVeiculo]");
        
    //Campo Input Marca Veiculo
    validInput("input[name=txtMarcaVeiculo]");
    validInputTReal("input[name=txtMarcaVeiculo]"); 
        
    //Campo Input Modelo Veiculo
    validInput("input[name=txtModeloVeiculo]");
    validInputTReal("input[name=txtModeloVeiculo]"); 
        
    //Campo Input Cor Veiculo
    validInput("input[name=txtCorVeiculo]");
    validInputTReal("input[name=txtCorVeiculo]"); 
        
    //Campo Input Placa Veiculo
    validInput("input[name=txtPlacaVeiculo]");
    validInputTReal("input[name=txtPlacaVeiculo]"); 
});

//Obrigatoriedade
$("button[name=btnPesquisar]").click(function(){     
    if($("select[name=txtTipoVisitante]").val() == '0'){
        $("select[name=txtTipoVisitante]").removeClass("is-valid");
        $("select[name=txtTipoVisitante]").addClass("is-invalid");
        $("select[name=txtTipoVisitante]").focus();
        return false;
    }   
    else if($("select[name=txtTipoVisitante]").val() == '1'){
        var identidadeMilitar = $("input[name=txtIdentidadeMilitar]").val().replace("-", "");  
        
        if(identidadeMilitar == ''){
            $("input[name=txtIdentidadeMilitar]").removeClass("is-valid");
            $("input[name=txtIdentidadeMilitar]").addClass("is-invalid");
            $("input[name=txtIdentidadeMilitar]").focus();
            $(".invalid-identidademilitar").html("Campo Obrigatório!");
            return false;
        }
        else if(identidadeMilitar == '0000000000' || identidadeMilitar == '1111111111' || identidadeMilitar == '2222222222' || identidadeMilitar == '3333333333' ||                 
                identidadeMilitar == '4444444444' || identidadeMilitar == '5555555555' || identidadeMilitar == '6666666666' || identidadeMilitar == '7777777777' ||                 
                identidadeMilitar == '8888888888' || identidadeMilitar == '9999999999'){
            $("input[name=txtIdentidadeMilitar]").removeClass("is-valid");
            $("input[name=txtIdentidadeMilitar]").addClass("is-invalid");

            $(".invalid-identidademilitar").html("Identidade Militar Inválida!");
            $("input[name=txtIdentidadeMilitar]").focus();
            return false;
        }
        else{        
            getVisitanteByIdentidade($("input[name=txtIdentidadeMilitar").val().replace("-",""));
        }
    }  
    else if($("select[name=txtTipoVisitante]").val() == '2'){
        var identidadeCivil = $("input[name=txtIdentidadeCivil]").val().replace("-", "");      
        
        if(identidadeCivil == ''){
            $("input[name=txtIdentidadeCivil]").removeClass("is-valid");
            $("input[name=txtIdentidadeCivil]").addClass("is-invalid");
            $("input[name=txtIdentidadeCivil]").focus();
            $(".invalid-identidadecivil").html("Campo Obrigatório!");
            return false;
        }
        else if(identidadeCivil == '00000000' || identidadeCivil == '11111111' || identidadeCivil == '22222222' || identidadeCivil == '33333333' ||                 
                identidadeCivil == '44444444' || identidadeCivil == '55555555' || identidadeCivil == '66666666' || identidadeCivil == '77777777' ||                 
                identidadeCivil == '88888888' || identidadeCivil == '99999999'){
            $("input[name=txtIdentidadeCivil]").removeClass("is-valid");
            $("input[name=txtIdentidadeCivil]").addClass("is-invalid");

            $(".invalid-identidadecivil").html("Identidade Inválida!");
            $("input[name=txtIdentidadeCivil]").focus();
            return false;
        }       
        else{        
            getVisitanteByIdentidade($("input[name=txtIdentidadeCivil").val().replace("-",""));
        }
    }    
});

$("button[name=btnSalvarCadastro]").click(function(){
    var dataEntrada = $("input[name=txtDataEntrada]").val();
    var dataEntradaSplit = dataEntrada.split('-');        
    var diaNascAl = dataEntradaSplit[2];
    var mesNascAl = dataEntradaSplit[1];
    var anoNascAl = dataEntradaSplit[0];

    var dataAtual = new Date();
    var diaAtual = dataAtual.getDate();
    var mesAtual = (dataAtual.getMonth() + 1);
    var anoAtual = dataAtual.getFullYear();  
    
    if($("input[name=txtNomeVisitante]").val() == ''){
        alert("Visitante não selecionado!");
        return false;
    }
    else if($("input[name=txtCracha]").val() == ''){
        $("input[name=txtCracha]").removeClass("is-valid");
        $("input[name=txtCracha]").addClass("is-invalid");
        $("input[name=txtCracha]").focus();
        return false;
    }
    else if(dataEntrada == ''){
        $("input[name=txtDataEntrada]").removeClass("is-valid");
        $("input[name=txtDataEntrada]").addClass("is-invalid");
        $(".invalid-dataEntrada").html("Campo Obrigatório!");
        $("input[name=txtDataEntrada]").focus();
        return false;
    }       
    else if((anoNascAl == anoAtual) && (mesNascAl == mesAtual) && (diaNascAl < diaAtual)){
        $("input[name=txtDataEntrada]").removeClass("is-valid");
        $("input[name=txtDataEntrada]").addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Entrada antes data atual.");
        $("input[name=txtDataEntrada]").focus();
        return false;
    }       
    else if((anoNascAl == anoAtual) && (mesNascAl < mesAtual)){
        $("input[name=txtDataEntrada]").removeClass("is-valid");
        $("input[name=txtDataEntrada]").addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Entrada antes data atual.");
        $("input[name=txtDataEntrada]").focus();
        return false;
    }       
    else if((anoNascAl < anoAtual)){
        $("input[name=txtDataEntrada]").removeClass("is-valid");
        $("input[name=txtDataEntrada]").addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Entrada antes data atual.");
        $("input[name=txtDataEntrada]").focus();
        return false;
    }   
    else if((anoNascAl == anoAtual) && (mesNascAl == mesAtual) && (diaNascAl > diaAtual)){
        $("input[name=txtDataEntrada]").removeClass("is-valid");
        $("input[name=txtDataEntrada]").addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Entrada após data atual.");
        $("input[name=txtDataEntrada]").focus();
        return false;
    }       
    else if((anoNascAl == anoAtual) && (mesNascAl > mesAtual)){
        $("input[name=txtDataEntrada]").removeClass("is-valid");
        $("input[name=txtDataEntrada]").addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Entrada após data atual.");
        $("input[name=txtDataEntrada]").focus();
        return false;
    }       
    else if((anoNascAl > anoAtual)){
        $("input[name=txtDataEntrada]").removeClass("is-valid");
        $("input[name=txtDataEntrada]").addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Entrada após data atual.");
        $("input[name=txtDataEntrada]").focus();
        return false;
    }
    else if($("input[name=txtHoraEntrada]").val() == ''){
        $("input[name=txtHoraEntrada]").removeClass("is-valid");
        $("input[name=txtHoraEntrada]").addClass("is-invalid");
        $("input[name=txtHoraEntrada]").focus();
        return false;
    }
    else if($("select[name=txtSetorVisitado]").val() == '0'){
        $("select[name=txtSetorVisitado]").removeClass("is-valid");
        $("select[name=txtSetorVisitado]").addClass("is-invalid");
        $("select[name=txtSetorVisitado]").focus();
        return false;
    }
    else if($("textarea[name=txtFinalidade]").val() == ''){
        $("textarea[name=txtFinalidade]").removeClass("is-valid");
        $("textarea[name=txtFinalidade]").addClass("is-invalid");
        $("textarea[name=txtFinalidade]").focus();
        return false;
    }
    else if($("select[name=txtVeiculoEntrar]").val() == '0'){
        $("select[name=txtVeiculoEntrar]").removeClass("is-valid");
        $("select[name=txtVeiculoEntrar]").addClass("is-invalid");
        $("select[name=txtVeiculoEntrar]").focus();
        return false;
    }  
    else if($("select[name=txtVeiculoEntrar]").val() == 'n'){
        return true;
    }  
    else if($("select[name=txtVeiculoEntrar]").val() == 's'){
        if($("select[name=txtTipoVeiculo]").val() == '0'){
            $("select[name=txtTipoVeiculo]").removeClass("is-valid");
            $("select[name=txtTipoVeiculo]").addClass("is-invalid");
            $("select[name=txtTipoVeiculo]").focus();
            return false;
        }
        else if($("input[name=txtMarcaVeiculo]").val() == ''){
            $("input[name=txtMarcaVeiculo]").removeClass("is-valid");
            $("input[name=txtMarcaVeiculo]").addClass("is-invalid");
            $("input[name=txtMarcaVeiculo]").focus();
            return false;
        }
        else if($("input[name=txtModeloVeiculo]").val() == ''){
            $("input[name=txtModeloVeiculo]").removeClass("is-valid");
            $("input[name=txtModeloVeiculo]").addClass("is-invalid");
            $("input[name=txtModeloVeiculo]").focus();
            return false;
        }
        else if($("input[name=txtCorVeiculo]").val() == ''){
            $("input[name=txtCorVeiculo]").removeClass("is-valid");
            $("input[name=txtCorVeiculo]").addClass("is-invalid");
            $("input[name=txtCorVeiculo]").focus();
            return false;
        }
        else if($("input[name=txtPlacaVeiculo]").val() == ''){
            $("input[name=txtPlacaVeiculo]").removeClass("is-valid");
            $("input[name=txtPlacaVeiculo]").addClass("is-invalid");
            $("input[name=txtPlacaVeiculo]").focus();
            return false;
        }
    }  
});
