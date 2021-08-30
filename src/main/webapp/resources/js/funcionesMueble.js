$(document).ready(function(){
   $("section #actualizar").click(function(e){
      e.preventDefault();
      var tipo=$(this).parent().find('#tipo').val();
      var costo=$(this).parent().find('#precio').val();
      swal({
          title: "Estas seguro de quieres armar este mueble?",
          text: "Este mueble se armara!",
          type: "warning",
          showCancelButton: true,
          confirmButtonClass: "btn-danger",
          confirmButtonText: "Si, Contruyelo!",
          cancelButtonText: "No, cancelar plx!",
          closeOnConfirm: false,
          closeOnCancel: false
        },
        function(isConfirm) {
          if (isConfirm) {
            construirMueble(tipo, costo);
            swal("MuebleCreado!", "tu mueble ha sido cread", "success");
            setTimeout(function(){
               parent.location.href="Servlet-Mueble?accion=listar";
            }, 1800);
          } else {
            swal("Cancelado", "El mueble no a sido ensamblado :)", "error");
          }
        });
   });
   function construirMueble(tipo, costo){
       var url="Servlet-Mueble?accion=construir&tipo=" + tipo +"&precio=" + costo;
       console.log("eliminado");
       $.ajax({
           type: 'GET',
           url: url,
           async: true,
           success: function (r) {
                
           }
       });
   }
});
