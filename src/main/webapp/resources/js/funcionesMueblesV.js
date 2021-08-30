 $("tr #upgrade").click(function(e){
      e.preventDefault();
      var id=$(this).parent().find('#id').val();
      swal({
          title: "Estas seguro que quieres transferir este mueble?",
          text: "El mueble sera transferido!",
          type: "warning",
          showCancelButton: true,
          confirmButtonClass: "btn-danger",
          confirmButtonText: "Si, Transfierelo!",
          cancelButtonText: "No, cancelar plx!",
          closeOnConfirm: false,
          closeOnCancel: false
        },
        function(isConfirm) {
          if (isConfirm) {
            construirMueble(id);
            swal("MuebleTransferido!", "tu mueble ha sido transferido", "success");
            setTimeout(function(){
               parent.location.href="Servlet-Mueble?accion=listar2";
            }, 1800);
          } else {
            swal("Cancelado", "El mueble no a sido transferido :)", "error");
          }
        });
   });
   function construirMueble(id){
       var url="Servlet-Mueble?accion=update&id=" + id;
       console.log("actualizado");
       $.ajax({
           type: 'GET',
           url: url,
           async: true,
           success: function (r) {
                
           }
       });
   }


