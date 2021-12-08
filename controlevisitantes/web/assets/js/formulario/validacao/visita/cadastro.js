//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//01.08.2020
var cpfMilitarOk = 0;
var cpfCivilOk = 0;
//Ao carregar ou recarregar a página
$(document).ready(function(){   
    limpaCampoSelect("select[name=txtTipoVisitante]");
    limpaCampoInput("input[name=txtcpfMilitar]");
    limpaCampoInput("input[name=txtCpfMilitar]");
    limpaCampoInput("input[name=txtNomeVisitante]");    
    
    //Campo Select Visitante
    validSelect("select[name=txtTipoVisitante]");
    validSelectTReal("select[name=txtTipoVisitante]");
    
    //Campo Input Cpf Militar
    validCPF("input[name=txtcpfMilitar]");
    validCPFTReal("input[name=txtcpfMilitar]"); 
   
    //Campo Input Cpf Civil
    validCPFCivil("input[name=txtCpfMilitar]");
    validCPFTReal("input[name=txtCpfMilitar]"); 
    
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
        var cpfMilitar = $("input[name=txtCpfMilitar]").val().replace(".", "").replace(".","").replace("-","");  
        
        //CPF
        if(cpfMilitar == ''){
            $("input[name=txtCpfMilitar]").removeClass("is-valid");
            $("input[name=txtCpfMilitar]").addClass("is-invalid");
            $("input[name=txtCpfMilitar]").focus();
            $(".invalid-cpf").html("Campo Obrigatório!");
            return false;
        }
        else if(cpfMilitar == '00000000000' || cpfMilitar == '11111111111' || cpfMilitar == '22222222222' || cpfMilitar == '33333333333' ||                 
                cpfMilitar == '44444444444' || cpfMilitar == '55555555555' || cpfMilitar == '66666666666' || cpfMilitar == '77777777777' ||                 
                cpfMilitar == '88888888888' || cpfMilitar == '99999999999'){
            $("input[name=txtCpfMilitar]").removeClass("is-valid");
            $("input[name=txtCpfMilitar]").addClass("is-invalid");
            $("input[name=txtCpfMilitar]").focus();
            $(".invalid-cpf").html("CPF Inválido!");
            return false;
        }        
        else if(cpfMilitarOk == 0){
            // Valida 1o digito	
            var add = 0;	
            for (var i=0; i < 9; i ++)		
                add += parseInt(cpfMilitar.charAt(i)) * (10 - i);	
            var rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)		
                rev = 0;	
            if (rev != parseInt(cpfMilitar.charAt(9))){
                $("input[name=txtCpfMilitar]").removeClass("is-valid");
                $("input[name=txtCpfMilitar]").addClass("is-invalid");
                $("input[name=txtCpfMilitar]").focus();
                $(".invalid-cpf").html("CPF Inválido!");
                return false;

            }else{
                $("input[name=txtCpfMilitar]").removeClass("is-invalid");
                $("input[name=txtCpfMilitar]").addClass("is-valid");
                cpfMilitarOk=1;
                return false;
            }
            // Valida 2o digito	
            add = 0;	
            for (var i = 0; i < 10; i ++)		
                add += parseInt(cpfMilitar.charAt(i)) * (11 - i);	
            rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)	
                rev = 0;	
            if (rev != parseInt(cpfMilitar.charAt(10))){
                $("input[name=txtCpfMilitar]").removeClass("is-valid");
                $("input[name=txtCpfMilitar]").addClass("is-invalid");
                $("input[name=txtCpfMilitar]").focus();
                $(".invalid-cpf").html("CPF Inválido!");
                return false;
                
            }else{
                $("input[name=txtCpfMilitar]").removeClass("is-invalid");
                $("input[name=txtCpfMilitar]").addClass("is-valid");
                cpfMilitarOk=1;
                return false;
            }    
        } 
        else{        
            getVisitanteByCpf($("input[name=txtCpfMilitar").val().replace(".","").replace(".","").replace("-",""));
        }
    }  
    else if($("select[name=txtTipoVisitante]").val() == '2'){
        var cpfCivil = $("input[name=txtCpfCivil]").val().replace(".", "").replace(".", "").replace("-", "");      
        
        //CPF
        if(cpfCivil == ''){
            $("input[name=txtCpfCivil]").removeClass("is-valid");
            $("input[name=txtCpfCivil]").addClass("is-invalid");
            $("input[name=txtCpfCivil]").focus();
            $(".invalid-cpf").html("Campo Obrigatório!");
            return false;
        }
        else if(cpfCivil == '00000000000' || cpfCivil == '11111111111' || cpfCivil == '22222222222' || cpfCivil == '33333333333' ||                 
                cpfCivil == '44444444444' || cpfCivil == '55555555555' || cpfCivil == '66666666666' || cpfCivil == '77777777777' ||                 
                cpfCivil == '88888888888' || cpfCivil == '99999999999'){
            $("input[name=txtCpfCivil]").removeClass("is-valid");
            $("input[name=txtCpfCivil]").addClass("is-invalid");
            $("input[name=txtCpfCivil]").focus();
            $(".invalid-cpf").html("CPF Inválido!");
            return false;
        }        
        else if(cpfCivilOk == 0){
            // Valida 1o digito	
            var add = 0;	
            for (var i=0; i < 9; i ++)		
                add += parseInt(cpfCivil.charAt(i)) * (10 - i);	
            var rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)		
                rev = 0;	
            if (rev != parseInt(cpfCivil.charAt(9))){
                $("input[name=txtCpfCivil]").removeClass("is-valid");
                $("input[name=txtCpfCivil]").addClass("is-invalid");
                $("input[name=txtCpfCivil]").focus();
                $(".invalid-cpf").html("CPF Inválido!");
                return false;

            }else{
                $("input[name=txtCpfCivil]").removeClass("is-invalid");
                $("input[name=txtCpfCivil]").addClass("is-valid");
                cpfCivilOk=1;
                return false;
            }
            // Valida 2o digito	
            add = 0;	
            for (var i = 0; i < 10; i ++)		
                add += parseInt(cpfCivil.charAt(i)) * (11 - i);	
            rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)	
                rev = 0;	
            if (rev != parseInt(cpfCivil.charAt(10))){
                $("input[name=txtCpfCivil]").removeClass("is-valid");
                $("input[name=txtCpfCivil]").addClass("is-invalid");
                $("input[name=txtCpfCivil]").focus();
                $(".invalid-cpf").html("CPF Inválido!");
                return false;
                
            }else{
                $("input[name=txtCpfCivil]").removeClass("is-invalid");
                $("input[name=txtCpfCivil]").addClass("is-valid");
                cpfCivilOk=1;
                return false;
            }    
        } 
        else{        
            getVisitanteByCpf($("input[name=txtCpfCivil").val().replace(".","").replace(".","").replace("-",""));
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
