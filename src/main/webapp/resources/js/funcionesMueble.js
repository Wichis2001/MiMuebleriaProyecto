$(document).ready(function(){
   $("tr #construirPieza").click(function(e){
      e.preventDefault();
      var tipo=$(this).parent().find('#tipo').val();
      var costo=$(this).parent().find('#costo').val();
      swal({
          title: "Estas seguro de quieres armar este mueble?",
          text: "Este mueble se armara definitivamente!",
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
            swal("Cancelado", "Tu registro esta a salvo :)", "error");
          }
        });
   });
   function construirMueble(tipo, costo){
       var url="Servlet-Mueble?accion=construir&tipo=" + tipo +"&costo=" + costo;
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
