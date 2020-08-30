//CB Anderson de Paula Andrade Medeiros
//Técnico em Informática
//21.08.2020

function somenteNumero(campo) {
    // Somente números
    $(campo).on('keydown', function(e) {
        var keyCode = e.keyCode || e.which,
            pattern = /\d/,
            // Permite somente Backspace, Delete e as setas direita e esquerda (além dos números)
            keys = [8,9,37,39,46,48,49,50,51,52,53,54,55,56,57,96,97,98,99,100,101,102,103,104,105];

        if(!pattern.test(String.fromCharCode(keyCode)) && $.inArray(keyCode, keys) === -1) {
            return false;
        }
    });
};

//AO CARREGAR OU RECARREGAR A PÁGINA
//FUNÇÕES
//SELECT
function limpaCampoInput(campo){
    $(campo).val('');    
    $(campo).removeClass("is-valid");
    $(campo).removeClass("is-invalid");
}

function limpaCampoSelect(campo){
    $(campo).val('0');    
    $(campo).removeClass("is-valid");
    $(campo).removeClass("is-invalid");
}

//Input
function validInput(campo){
    if($(campo).val() != ''){
        $(campo).removeClass("is-invalid");
        $(campo).addClass("is-valid");
    }
};

//Select
function validSelect(campo){
    if($(campo).val() != '0'){
        $(campo).removeClass("is-invalid");
        $(campo).addClass("is-valid");
    }
};

//IDENTIDADE MILITAR
function validIdentidadeMilitar(campo){  
    var idtMil = $(campo).val().replace("-","").replace("(","").replace(")","").replace(" ","");
    
    if(idtMil == ''){
    }
    else if(idtMil == '0000000000' || idtMil == '1111111111' || idtMil == '2222222222' || idtMil == '3333333333' ||                 
            idtMil == '4444444444' || idtMil == '5555555555' || idtMil == '6666666666' || idtMil == '7777777777' ||                 
            idtMil == '8888888888' || idtMil == '9999999999'){
        $(campo).removeClass("is-valid");
        $(campo).addClass("is-invalid");
        $(".invalid-identidade").html("Identidade Militar Inválida!");
    }
    else{
        $(campo).removeClass("is-invalid");
        $(campo).addClass("is-valid");
    }
};

//IDENTIDADE CIVIL
function validIdentidadeCivil(campo){  
    var idtMil = $(campo).val().replace("-","").replace("(","").replace(")","").replace(" ","");
    
    if(idtMil == ''){
    }
    else if(idtMil == '00000000' || idtMil == '11111111' || idtMil == '22222222' || idtMil == '33333333' ||                 
            idtMil == '44444444' || idtMil == '55555555' || idtMil == '66666666' || idtMil == '77777777' ||                 
            idtMil == '88888888' || idtMil == '99999999'){
        $(campo).removeClass("is-valid");
        $(campo).addClass("is-invalid");
        $(".invalid-identidadecivil").html("Identidade Inválida!");
    }
    else{
        $(campo).removeClass("is-invalid");
        $(campo).addClass("is-valid");
    }
};

// Data de Entrada
function validDataEntrada(campo){
    var dataEntrada = $(campo).val();
    var dataEntradaSplit = dataEntrada.split('-');        
    var diaEntrada = dataEntradaSplit[2];
    var mesEntrada = dataEntradaSplit[1];
    var anoEntrada = dataEntradaSplit[0];

    var dataAtual = new Date();
    var diaAtual = dataAtual.getDate();
    var mesAtual = (dataAtual.getMonth() + 1);
    var anoAtual = dataAtual.getFullYear();

    if(dataEntrada == ''){
    }       
    else if((anoEntrada == anoAtual) && (mesEntrada == mesAtual) && (diaEntrada > diaAtual)){
        $(campo).removeClass("is-valid");
        $(campo).addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Nascimento após data atual.");
    }       
    else if((anoEntrada == anoAtual) && (mesEntrada > mesAtual)){
        $(campo).removeClass("is-valid");
        $(campo).addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Nascimento após data atual.");
    }       
    else if((anoEntrada > anoAtual)){
        $(campo).removeClass("is-valid");
        $(campo).addClass("is-invalid");
        $(".invalid-dataEntrada").html("Data Inválida! Entrada após data atual.");
    }    
    else{
        $(campo).removeClass("is-invalid");
        $(campo).addClass("is-valid");
    }
};

//AO MUDAR O VALOR DO CAMPO
//FUNÇÕES
//SELECT
function validSelectTReal(campo){
    $(campo).change(function(){
        if($(campo).val() != '0'){
            $(campo).removeClass("is-invalid");
            $(campo).addClass("is-valid");
        }else{
            $(campo).removeClass("is-valid");
            $(campo).addClass("is-invalid");
        }
    });    
};

//Input
function validInputTReal(campo){
    $(campo).change(function(){
        if($(campo).val() != ''){
            $(campo).removeClass("is-invalid");
            $(campo).addClass("is-valid");
        }else{
            $(campo).removeClass("is-valid");
            $(campo).addClass("is-invalid");
        }
    });
};

//IDENTIDADE MILITAR
function validIdentidadeMilitarTReal(campo){ 
    $(campo).change(function(){
        var idtMil = $(campo).val().replace("-","").replace(" ","");
        if(idtMil == ''){
            $(campo).removeClass("is-valid");
            $(campo).addClass("is-invalid");
            $(".invalid-identidademilitar").html("Campo Obrigatório!");
        }
        else if(idtMil == '0000000000' || idtMil == '1111111111' || idtMil == '2222222222' || idtMil == '3333333333' ||                 
                idtMil == '4444444444' || idtMil == '5555555555' || idtMil == '6666666666' || idtMil == '7777777777' ||                 
                idtMil == '8888888888' || idtMil == '9999999999'){
            $(campo).removeClass("is-valid");
            $(campo).addClass("is-invalid");
            $(".invalid-identidademilitar").html("Identidade Militar Inválida!");  
        }
        else{
            $(campo).removeClass("is-invalid");
            $(campo).addClass("is-valid");
        }
    });
};

//IDENTIDADE CIVIL
function validIdentidadeCivilTReal(campo){ 
    $(campo).change(function(){
        var idtMil = $(campo).val().replace("-","").replace(" ","");
        if(idtMil == ''){
            $(campo).removeClass("is-valid");
            $(campo).addClass("is-invalid");
            $(".invalid-identidadecivil").html("Campo Obrigatório!");
        }
        else if(idtMil == '00000000' || idtMil == '11111111' || idtMil == '22222222' || idtMil == '33333333' ||                 
                idtMil == '44444444' || idtMil == '55555555' || idtMil == '66666666' || idtMil == '77777777' ||                 
                idtMil == '88888888' || idtMil == '99999999'){
            $(campo).removeClass("is-valid");
            $(campo).addClass("is-invalid");
            $(".invalid-identidadecivil").html("Identidade Inválida!");  
        }
        else{
            $(campo).removeClass("is-invalid");
            $(campo).addClass("is-valid");
        }
    });
};
