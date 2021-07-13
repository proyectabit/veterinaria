
//Una vez que se cargue toda la pagina
  document.addEventListener("DOMContentLoaded", function(event) { 
    
    //Agregar evento de click al boton menu
    document.getElementById("btn_menu").addEventListener('click', function(){
       const sidebar = document.getElementById("sidebar_menu");
       const iconMenu = document.getElementById("icon_menu");
       const classSidebar = sidebar.className;

       if(classSidebar == 'nav-responsive'){
           sidebar.className = "nav-responsive-hidden";
           iconMenu.src="/assets/images/menu.png";
       }
       else {
           sidebar.className = "nav-responsive";
           iconMenu.src="/assets/images/x.png";
       }
    })

  });