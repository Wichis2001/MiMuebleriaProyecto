$(document).ready(function(){
   $("tr #suspender").click(function(e){
      e.preventDefault();
      var nombre=$(this).parent().find('#nombre').val();
      var costo=$(this).parent().find('#costo').val();
      swal({
          title: "Estas seguro de querer suspender a este usuario?",
          text: "Este Usuario no podra volver a ingresar al sistema!",
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
            eliminarUsuario(nombre);
            swal("Suspendido!", "El usuario ha sido suspendido", "success");
            setTimeout(function(){
               parent.location.href="Servlet-Usuarios?accion=listar";
            }, 1800);
          } else {
            swal("Cancelado", "Tu registro esta a salvo :)", "error");
          }
        });
   });
   function eliminarUsuario(nombre){
       var url="Servlet-Usuarios?accion=eliminar&nombre=" + nombre;
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

