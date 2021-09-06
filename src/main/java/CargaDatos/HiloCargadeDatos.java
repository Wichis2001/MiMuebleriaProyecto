package CargaDatos;
import Mueble.EnsamblePiezas;
import Mueble.Mueble;
import Mueble.MuebleEnsamblado;
import Mueble.Pieza;
import Mysql.Conexion;
import Mysql.Insert;
import Mysql.modelos.CrearMuebleDAO;
import Mysql.modelos.PiezaDAO;
import Trabajadores.Usuario;
import java.io.File;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Este hilo me permite la correcta carga de un archivo de la empresa AeroBalamDevs
 * @author luis
 */
public class HiloCargadeDatos extends Thread{
    EnsamblePiezas ensamble= new EnsamblePiezas();
    CrearMuebleDAO mdao= new CrearMuebleDAO();
    Mueble mueble= new Mueble();
    Pieza pieza = new Pieza();
    PiezaDAO pdao = new PiezaDAO();
    public static ArrayList<String>errores=new ArrayList<>();
    VerificadorDatos verificador= new VerificadorDatos();
    private File archivoAProcesar;
    private static final String USUARIO = "USUARIO";
    private static final String PIEZA = "PIEZA";
    private static final String MUEBLE = "MUEBLE";
    private static final String ENSAMBLEPIEZAS = "ENSAMBLE_PIEZAS";
    private static final String ENSAMBLARMUEBLE ="ENSAMBLAR_MUEBLE";
    private static final String CLIENTE = "CLIENTE";
    Conexion conexion=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario usuarios = new Usuario();
    /**
     * Este constructor me permite hacer la carga de un archivo y poder aplicarla a la ventana deseada, en este caso a la ventana de carga de datos 
     * @param archivoAProcesar
     * @param ventanaCarga
     */
    public HiloCargadeDatos(File archivoAProcesar){
        this.archivoAProcesar = archivoAProcesar;
    }

    /**
     * Este metodo run se encargara de llamar al hilo haciendo el llamado al metodo leer archivo 
     */
    @Override
    public void run(){
        try {
            //Llamamos al metodo leer archivo
            leerArchivo();
            //Archivo no encontrado
        } catch (FileNotFoundException e) {
            System.out.println(e);
            //Errores Generales
        } catch (IOException e) {
            System.out.println(e);
            //Error en el indice ilegal que posee el arreglo 
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }
    }

    /**
     * Este metodo se encarga de la lectura del archivo de texto y la extraccion de datos para cada objeto respectivamente 
     */
    private void leerArchivo() throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException{
       //Leemos el texto del archivo
        BufferedReader lector = new BufferedReader(new FileReader(this.archivoAProcesar));
        //Usamos esta variable para la lectura de linea por linea
        String auxiliar = lector.readLine();
        //Usamos esta variable para determinar la poscion en la que esta ubicada la lnea
        int posicion;
        String auxiliarUno, auxiliarDos, datos[];
        while(auxiliar != null){
            //Con la linea leida separamos los campos
            posicion = auxiliar.indexOf("(");
            auxiliarUno = auxiliar.substring(0, posicion);
            auxiliarDos = auxiliar.substring(posicion);
            datos = EliminarParentesis(auxiliarDos);
            //Procedemos al Guardado de objetos s
            try{
                switch(auxiliarUno){
                    //En caso que este comience con la palabra usuario procedemos a guardar el archivo a la base de datos
                    case USUARIO:
                        //Verificamos que el tamaño de la entrada de datos sea aceptado
                        if(verificador.verificarTamañoEntradaUsuario(quitarComillas(datos[0]), quitarComillas(datos[1]))==true){
                            //Verificamos que el nombre de usuario no haya sido registrado previamente en la base de datos
                            if(verificador.verificadorUsuario(quitarComillas(datos[0]))==true){
                                //Verificamos que la contraseña tenga mas de 6 caracteres
                                if(verificador.verificadorContraseña(quitarComillas(datos[1]))){
                                    //Verificamos que el area laboral este registrada en la base de datos
                                    if(verificador.verificar(Integer.parseInt(datos[2]))==true){
                                        try{
                                            //Establecemos la Query
                                            String sql="INSERT INTO usuario (nombre_usuario,contraseña,tipo_usuario) VALUES(?,?,?)";
                                            //Establecemos una conexion la base de datos
                                            con=conexion.getConnection();
                                            con.setAutoCommit(false);
                                            //Realizamos un insert de un usuario a travez de enviar un parametro
                                            ps=con.prepareStatement(sql);
                                            ps.setString(1, quitarComillas(datos[0]));
                                            ps.setString(2, quitarComillas(datos[1]));
                                            ps.setInt(3, Integer.parseInt(datos[2]));
                                            ps.executeUpdate();
                                            con.commit();
                                            //Error SQL al momento de agregar una pieza
                                        } catch(NumberFormatException e){
                                            errores.add("Error donde se deberia de registrar un dato numerico en : "+auxiliarUno+auxiliarDos);
                                        } catch(SQLException e){
                                            System.err.print(e);
                                            try {
                                                //Regresamos el rollback
                                                con.rollback();
                                            } catch (SQLException ex) {
                                                //Error SQL al momento de hacer un roll back
                                                Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    } else{
                                        errores.add("El area ingresesada para el usuario no es valido:  "+auxiliarUno+auxiliarDos);
                                    }
                                } else{
                                    errores.add("La contraseña del usuario debe de tener como minimo 6 caraceteres en:  "+auxiliarUno+auxiliarDos);
                                }
                            } else{
                                errores.add("Ya hay un usuario con el mismo nombre en la base de datos en:  "+auxiliarUno+auxiliarDos);
                            }
                        } else {
                            errores.add("Los datos ingresados exceden el tamaño aceptado en la base de datos en: "+auxiliarUno+auxiliarDos);
                        }
                    break;
                    //En caso que este comience con la palabra aerolinea procedemos a guardar el archivo a la base de datos
                    case PIEZA:
                        //Verificamos que el valor de la pieza tenga el tamaño de caracteres aceptados
                        if(verificador.verificarTamañoEntradaPieza(quitarComillas(datos[0]))==true){
                            //Verificamos que esta pieza no exista para poder crear una
                            if(pdao.verificar(quitarComillas(datos[0]), Double.parseDouble(datos[1]), 1)==false){
                                //Establecemos un objeto pieza y asignamos atributos
                                pieza.setTipo(quitarComillas(datos[0]));
                                pieza.setCosto(Double.parseDouble(datos[1]));
                                pieza.setCantidad(1);
                                pdao.add(pieza);
                            } else {
                                //Si esta pieza existe procedemos a actualizr la cantidad de esta pieza
                                pdao.update(quitarComillas(datos[0]), Double.parseDouble(datos[1]), 1);
                            }
                        } else{
                            errores.add("El nombre de la pieza excede el limite de caracteres aceptados en: "+auxiliarUno+auxiliarDos);
                        }
                    break;
                    //En caso que este comience con la palabra mueble procedemos a guardar el mismo a la base de datos
                    case MUEBLE:
                        //Vereficamos que el tamaño del nombre de los muebles tenga la cantidad de carecteres aceptados
                        if(verificador.verificarTamañoEntradaMueble(quitarComillas(datos[0]))==true){
                            //Verificamos que el mueble no haya sido registro previamente
                           if(verificador.verificadorMueble(quitarComillas(datos[0]))==true){
                               //Creamos un mueble nuevo y lo asignamos a la base de datos
                               mueble.setNombre_mueble_ensamble(quitarComillas(datos[0]));
                               mueble.setPrecio(Double.parseDouble(datos[1]));
                               mdao.addMueble(mueble);
                           } else{
                               errores.add("Este mueble ya esta registrado en la base de datos: "+auxiliarUno+auxiliarDos);
                           }
                        } else {
                            errores.add("El nombre del muble excede la cantidad de caracteres permitidos: "+auxiliarUno+auxiliarDos);
                        }                    
                    break;
                    //En caso que este comience con la palabra ensamble pieza procedemos a guardar el mismo en la base de datos
                    case ENSAMBLEPIEZAS:
                        //Verificamos que la cantidad de caracteres ingresados sea aceptado por la instruccion
                        if(verificador.verificarTamañoEntradaEnsamblaje(quitarComillas(datos[0]), quitarComillas(datos[1]))==true){
                            //Verificamos que el mueble exista
                            if(verificador.verificadorMueble2(quitarComillas(datos[0]))==true){
                                //Verificamos que la pieza exista
                                if(verificador.verificadorPieza(quitarComillas(datos[1]))==true){
                                    if(verificador.verificadorEnsamblajePieza(quitarComillas(datos[0]), quitarComillas(datos[1]))){
                                        String sql="INSERT INTO ensamble_piezas (mueble_nombre,pieza_tipo,cantidad) VALUES(?,?,?)";
                                        try{
                                            //Establecemos una conexin la base de datos
                                            con=conexion.getConnection();
                                            con.setAutoCommit(false);
                                            //Realizamos un insert de un mueble a travez de enviar un parametro
                                            ps=con.prepareStatement(sql);
                                            ps.setString(1, quitarComillas(datos[0]));
                                            ps.setString(2, quitarComillas(datos[1]));
                                            ps.setInt(3, Integer.parseInt(datos[2]));
                                            ps.executeUpdate();
                                            con.commit();
                                            //Error SQL al momento de agregar una pieza
                                        } catch(SQLException e){
                                            System.err.print(e);
                                            try {
                                                //Regresamos el rollback
                                                con.rollback();
                                            } catch (SQLException ex) {
                                                //Error SQL al momento de hacer un roll back
                                                Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    } else {
                                        errores.add("Esta instruccion ya esta hecha en: "+auxiliarUno+auxiliarDos);
                                    }
                                    
                                } else {
                                    errores.add("La pieza ingresada no existe: "+auxiliarUno+auxiliarDos);
                                }
                            } else{
                                errores.add("El mueble ingresado no existe: "+auxiliarUno+auxiliarDos);
                            }
                        } else {
                            errores.add("Los caracteres ingresados exceden los admitidos al momento de establecer un ensamblaje"+auxiliarUno+auxiliarDos);
                        }
                    break;
                    //En caso que este comience con la palabra ensamblar mueble procedemos a guardar el mismo en la base de datos
                    case ENSAMBLARMUEBLE:
//                        Procedemos a darle un formato date a las fechas que se nos da como atributos en en ensamblaje del mueble
                        Date fecha = DarFormatoaFecha(quitarComillas(datos[2]));
//                        Verificamos que el tamaño de los caracteres sea aceptados por la base de datos
                        if(verificador.verificarTamañoEntradaEnsamblarMueble(quitarComillas(datos[0]),quitarComillas(datos[1]))==true){
                            //Verificamos la existencia del mueble
                            if(verificador.verificadorMueble2(quitarComillas(datos[0]))==true){
                                //Verificamos la existencia del usuario
                                if(verificador.verificadorUsuario2(quitarComillas(datos[1]))==true){
                                    //Asignamos el mueble
                                    MuebleEnsamblado muebleE=new MuebleEnsamblado(fecha.toLocalDate(), verificador.precioMueble(quitarComillas(datos[0])), quitarComillas(datos[1]), quitarComillas(datos[0]));
                                    verificador.add(muebleE);
                                } else{
                                    errores.add("El usuario no esta registrado en la base de datos"+auxiliarUno+auxiliarDos);
                                }
                            } else {
                                errores.add("El mueble registrado en el ensamblaje no existe en: "+auxiliarUno+auxiliarDos);
                            }
                        } else{
                            errores.add("Las cadenas de texto exceden los limites en Ensamblar Mueble: "+auxiliarUno+auxiliarDos);
                        }
                    break;
                    //En caso que este comience con la palabra cliente procedemos a guardar este en la base de datos
                    case CLIENTE:
                        if(datos.length==3){
                            //Verificamos si la cad
                            if(verificador.verificarTamañoEntradaClientePequeño(quitarComillas(datos[1]), quitarComillas(datos[2]), quitarComillas(datos[0]))==true){
                                //Verificamos que el nit no posea guiones
                                if(verificador.verificarGuiones(quitarComillas(datos[1]))==true){
                                    if(verificador.verificadorCliente(quitarComillas(datos[1]))==true){
                                        try{
                                            //Establecemos una conexin la base de datos
                                            con=conexion.getConnection();
                                            con.setAutoCommit(false);
                                            //Realizamos un insert de Cliente pieza a travez de enviar un parametro
                                            ps=con.prepareStatement(Insert.INSERTCLIENTE);
                                            ps.setString(1, String.valueOf(quitarComillas(datos[1])));
                                            ps.setString(2, String.valueOf(quitarComillas(datos[2])));
                                            ps.setString(3, String.valueOf(""));
                                            ps.setString(4, String.valueOf(""));
                                            ps.setString(5, String.valueOf(quitarComillas(datos[0])));
                                            ps.executeUpdate();
                                            con.commit();
                                            //Error SQL al momento de agregar una pieza
                                        } catch(SQLException e){
                                            System.err.print(e);
                                            try {
                                                //Regresamos el rollback
                                                con.rollback();
                                            } catch (SQLException ex) {
                                                //Error SQL al momento de hacer un roll back
                                                Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    } else{
                                        errores.add("El NIT del cliente ya esta puesto en la base de datos"+auxiliarUno+auxiliarDos);
                                    }

                                } else{
                                    errores.add("No se puede asignar un cliente el cual contenga guiones en su NIT"+auxiliarUno+auxiliarDos);
                                }
                            } else{
                                errores.add("Los caracteres en la instruccion cliente exceden los limites aceptados: "+auxiliarUno+auxiliarDos);
                            }   
                        }else{
                            //Verificamos si la cad
                            if(verificador.verificarTamañoEntradaCliente(quitarComillas(datos[1]), quitarComillas(datos[2]), quitarComillas(datos[3]), quitarComillas(datos[4]), quitarComillas(datos[0]))==true){
                                //Verificamos que el nit no posea guiones
                                if(verificador.verificarGuiones(quitarComillas(datos[1]))==true){
                                    if(verificador.verificadorCliente(quitarComillas(datos[1]))==true){
                                        try{
                                            //Establecemos una conexin la base de datos
                                            con=conexion.getConnection();
                                            con.setAutoCommit(false);
                                            //Realizamos un insert de Cliente pieza a travez de enviar un parametro
                                            ps=con.prepareStatement(Insert.INSERTCLIENTE);
                                            ps.setString(1, String.valueOf(quitarComillas(datos[1])));
                                            ps.setString(2, String.valueOf(quitarComillas(datos[2])));
                                            ps.setString(3, String.valueOf(quitarComillas(datos[3])));
                                            ps.setString(4, String.valueOf(quitarComillas(datos[4])));
                                            ps.setString(5, String.valueOf(quitarComillas(datos[0])));
                                            ps.executeUpdate();
                                            con.commit();
                                            //Error SQL al momento de agregar una pieza
                                        } catch(SQLException e){
                                            System.err.print(e);
                                            try {
                                                //Regresamos el rollback
                                                con.rollback();
                                            } catch (SQLException ex) {
                                                //Error SQL al momento de hacer un roll back
                                                Logger.getLogger(PiezaDAO.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    } else{
                                        errores.add("El NIT del cliente ya esta puesto en la base de datos"+auxiliarUno+auxiliarDos);
                                    }

                                } else{
                                    errores.add("No se puede asignar un cliente el cual contenga guiones en su NIT"+auxiliarUno+auxiliarDos);
                                }
                            } else{
                                errores.add("Los caracteres en la instruccion cliente exceden los limites aceptados: "+auxiliarUno+auxiliarDos);
                            }   
                        }
                        
                    break;
                    default:
                    //Establecemos que esta tiene un formato invalido para poder ser asignado en la carga de archivos
                    errores.add("Formato inválido. "+auxiliar);
                    break;
                }
            } catch(NumberFormatException e){
                //Error al no haber asignado un valor númerico
                errores.add("Error en el ingreso de datos en los datos donde se debia ingresar un valor númerico en la línea: "+auxiliar);
                System.out.println(e);
            } catch (SQLException ex) {
                Logger.getLogger(HiloCargadeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }

            try{
                //Dormimos el hilo para que se pueda apreciar la carga del archivo
                Thread.sleep(1);
                //Error de carga de archivos interrumpida
            } catch(InterruptedException e){
                System.out.println(e);
            }
            auxiliar = lector.readLine();
        }
        //Establecemos que la carga fue exitosa y cerramos el lector
        lector.close();
    }
    
     /**
     * Dicho metodo convierte el texto en objeto, eliminando los parentesis que separa los campos de dicho objeto
     * @return
     */
    private String[] EliminarParentesis(String auxiliarDos){
        //Establecemos el final del campo
        int posicion = auxiliarDos.lastIndexOf(")");
        String textoDatos = auxiliarDos.substring(1, posicion);
        //Establecemos a la separación de los atributos de un objeto
        String[] datos = textoDatos.split(",");
        return datos;
    }
    
     /**
     * Dicho metodo me duvuelve devuelve el formato como una fecha para poder usar date
     * @return
     */
    private Date DarFormatoaFecha(String texto){
        //Establecemos el formato de la fecha
        Date fechaInicio;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        //Le damos el formato a la fecha
        try {
            java.util.Date nfecha= formato.parse(texto);
            fechaInicio= new java.sql.Date(nfecha.getTime());
            return fechaInicio;
            //Error en la cadena ingresada
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }
    
    private String quitarComillas(String dato){
        return dato.substring(1, dato.length()-1);
    }
}