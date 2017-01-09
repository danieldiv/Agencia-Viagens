$(function () {
    $('.js-toggle').bind('click', function (event) {
        $('.js-sidebar, .js-content').toggleClass('is-toggled');
        event.preventDefault();
    });
});


function mostrarAtivo(tag, id) {
    alert(id)
    
    var tag_li = document.getElementById('lista_menu');
    var tag_a = tag_li.getElementsByTagName('a');

    for (i = 0; i < tag_a.length; i++) {
        tag_a[i].style.backgroundColor = "";
        tag_a[i].style.color = "";
    }
    
    tag.style.backgroundColor = "#ff0000"; // altera o fundo
    tag.style.color = "#ffffff"; // altera a cor
}