$(document).ready(function() {
   $('.ui-menuitem-link').each(function(){
       if(window.location.pathname.indexOf($(this).attr('href')) !== -1) {
           $(this).addClass('active');
//           $(this).css('background', 'red');//or add class
       }
   });  

});

//$('.menuLateral').click(function () {
//    //remove class active
//    $('.menuLateral').removeClass('active');
//    //adiciona class active ao item clicado
//    $(this).addClass('active');
//});

