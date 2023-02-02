
var cards = document.getElementsByClassName("card-moto");

for (var i = 0; i < cards.length; i++) {
    cards[i].addEventListener("mouseover", function(){
        this.classList.remove('fechado');
        this.classList.add('aberto');
    });
    cards[i].addEventListener("mouseout", function(){
        this.classList.remove('aberto');
        this.classList.add('fechado');
    });
}


/*
function abrir(card){
    card.classList.remove('fechado');
    card.classList.add('aberto');
}

function fechar(card){
    card.classList.add('aberto');
    card.classList.remove('fechado');
}*/