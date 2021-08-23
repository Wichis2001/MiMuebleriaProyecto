$(document).ready(function(){
   $("tr #deletePieza").click(function(e){
      e.preventDefault();
      var tipo=$(this).parent().find('#tipo').val();
      var costo=$(this).parent().find('#costo').val();
      swal({
          title: "Estas seguro de querer eliminar este registro?",
          text: "No podras recuperar este registro!",
          type: "warning",
          showCancelButton: true,
          confirmButtonClass: "btn-danger",
          confirmButtonText: "Si, eliminalo!",
          cancelButtonText: "No, cancelar plx!",
          closeOnConfirm: false,
          closeOnCancel: false
        },
        function(isConfirm) {
          if (isConfirm) {
            eliminarUsuario(tipo, costo);
            swal("Eliminado!", "tu registro ha sido eliminado", "success");
            setTimeout(function(){
               parent.location.href="Servlet-Pieza?accion=listar";
            }, 1800);
          } else {
            swal("Cancelado", "Tu registro esta a salvo :)", "error");
          }
        });
   });
   function eliminarUsuario(tipo, costo){
       var url="Servlet-Pieza?accion=eliminar&tipo=" + tipo +"&costo=" + costo;
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