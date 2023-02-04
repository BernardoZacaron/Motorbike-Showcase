var cards = document.getElementsByClassName("card-moto-catalogo");

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