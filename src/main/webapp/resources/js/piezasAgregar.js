$(document).ready(function(){
   $("form #regresarbutton").click(function(e){
      e.preventDefault();
        swal({
          title: "REGRESAR?",
          text: "Regresaras a la pesataña anterior!",
          type: "warning",
          showCancelButton: true,
          confirmButtonClass: "btn-danger",
          confirmButtonText: "Si, Regresar!",
          closeOnConfirm: false
        },
        function(){
          swal("Regresando!", "Regresando a la pestaña anterior.", "success");
          setTimeout(function(){
               parent.location.href="Servlet-Pieza?accion=listar";
            }, 1800);
        });
   });
});
