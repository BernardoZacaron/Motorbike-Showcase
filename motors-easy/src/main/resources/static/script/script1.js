
var cards = document.querySelector(".card-moto");

cards.addEventListener("mouseover", function(){
    this.classList.remove('fechado');
    this.classList.add('aberto');
});
cards.addEventListener("mouseout", function(){
    this.classList.remove('aberto');
    this.classList.add('fechado');
});

/*
function abrir(card){
    card.classList.remove('fechado');
    card.classList.add('aberto');
}

function fechar(card){
    card.classList.add('aberto');
    card.classList.remove('fechado');
}*/