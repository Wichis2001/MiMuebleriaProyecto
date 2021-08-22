$(document).ready(function(){
   $("tr #deletePieza").click(function(e){
      e.preventDefault();
      var tipo=$(this).parent().find('#tipo').val();
      var costo=$(this).parent().find('#costo').val();
      swal({
          title: "Estas seguro de eliminar este registro?",
          text: "No podras recuperar este archivo!",
          type: "warning",
          showCancelButton: true,
          confirmButtonClass: "btn-danger",
          confirmButtonText: "Yes, delete it!",
          cancelButtonText: "No, cancel plx!",
          closeOnConfirm: false,
          closeOnCancel: false
        },
        function(isConfirm) {
          if (isConfirm) {
            eliminarUsuario(tipo, costo);
            swal("Eliminado!", "El registro ha sido eliminado correctamente", "eliminado");
            setTimeout(function(){
               parent.location.href="Servlet-Pieza?accion=listar";
            }, 180);
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