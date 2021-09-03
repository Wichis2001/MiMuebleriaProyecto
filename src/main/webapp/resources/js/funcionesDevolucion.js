$(document).ready(function(){
   $("tr #boton").click(function(e){
      e.preventDefault();
      var idmueble=$(this).parent().find('#idmueble').val();
      swal({
          title: "Estas seguro de quieres devovler este mueble?",
          text: "Este mueble se devolvera!",
          type: "warning",
          showCancelButton: true,
          confirmButtonClass: "btn-danger",
          confirmButtonText: "Si, Devuelvelo!",
          cancelButtonText: "No, cancelar plx!",
          closeOnConfirm: false,
          closeOnCancel: false
        },
        function(isConfirm) {
          if (isConfirm) {
            construirMueble(idmueble);
            swal("MuebleDevuelto!", "tu mueble ha sido devuelto", "success");
            setTimeout(function(){
               
            }, 1800);
          } else {
            swal("Cancelado", "El mueble no a sido devuelto :)", "error");
          }
        });
   });
   function construirMueble(idmueble){
       var url="Servlet-Factura?accion=devolucion&idmueble=" + idmueble;
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

