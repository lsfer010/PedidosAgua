/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;

/**
 *
 * @author lsfer
 */
public class Pedido extends Conexion {
    /*
    ResultSet resultado es el cascaron para guardar el resultado de las consultas.
    int numero_registros guarda el valor de la cantidad de registros en la tabla pedido.
    String Mensaje guarda una cadena de texto espcificando si una operación fué exitosa o fracasó.
    */
    private ResultSet resultado;
    private int numero_registros;
    private String Mensaje;
    
    /*
    public Pedido es el constructor que llama al método conectar de la clase Conexión para crear la conexión con la base de datos.
    */
    public Pedido(){
        Conectar();
    }
    
    /*
    public ResultSet Listado consulta todos los datos de los pedidos registrados en la base de datos.
    */
    public ResultSet Listado()throws Exception {
        try{
            getStmt();
	    resultado = stmt.executeQuery("select * from pedido");
            return resultado;
            
        } catch (SQLException ex){
                System.err.println("SQLException: " + ex.getMessage());
                return null;
        }
        
    }
    
    /*
    public ResultSet ListadoCompleto devuelve todos los datos de los pedidos con nombre de cliente, dirección y producto en ves de sus ID.
    */
    public ResultSet ListadoCompleto()throws Exception {
        try{
            getStmt();
	    resultado = stmt.executeQuery("select id_pedido, fecha_pedido, cantidad_pedido, estado_pedido, nombre_producto, nombre_cliente, calle, numero_interior, numero_exterior, colonia, referencia "
                    + "from pedido, producto, direccion, cliente where id_producto = producto_id_producto AND id_direccion = direccion_id_direccion AND id_cliente = cliente_id_cliente");
            return resultado;
            
        } catch (SQLException ex){
                System.err.println("SQLException: " + ex.getMessage());
                return null;
        }
        
    }
    
    /*
    public String Insertar añade un registro a la base de datos.
    */
    public String Insertar(int cantidad_pedido, String fecha_pedido, String estado_pedido, int producto_id_producto, int direccion_id_direccion) throws Exception {
        try {
            getStmt();
            stmt.executeUpdate("insert into pedido (cantidad_pedido, fecha_pedido, estado_pedido, producto_id_producto, direccion_id_direccion) "
                    + "values (" + cantidad_pedido + ", '" + fecha_pedido + "', '" + estado_pedido + "', " + producto_id_producto + ", " + direccion_id_direccion + ")");
            Mensaje = "Pedido registrado exitosamente";
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al registrar el pedido";
        }
        return Mensaje;
    }
    
    /*
    public String Actualizar actualiza el registro de un pedido en la base de datos.
    */
    public String Actualizar(int id_pedido, int cantidad_pedido, String fecha_pedido, String estado_pedido, int producto_id_producto, int direccion_id_direccion) throws Exception {
        try {
        getStmt();
        stmt.executeUpdate("update pedido set cantidad_pedido=" + cantidad_pedido + ", fecha_pedido='" + fecha_pedido + "', "
                + "estado_pedido='" + estado_pedido + "', producto_id_producto=" + producto_id_producto + ", direccion_id_direccion=" + direccion_id_direccion + " where id_pedido=" + id_pedido);
            Mensaje = "Pedido editado exitosamente";
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al editar el pedido";
        }
        return Mensaje;
    }
    
    /*
    public String Eliminar borra un pedido registrado en la base de datos.
    */
    public String Eliminar(int id_pedido) throws Exception {
        try {
            getStmt();
            stmt.executeUpdate("delete from pedido where id_pedido=" + id_pedido);
            Mensaje = "Pedido eliminado exitosamente";
        } catch (SQLException ex) {
           System.err.println("SQLException: " + ex.getMessage());
           Mensaje="Error al eliminar el pedido"; 
        }
        return Mensaje;
    }
    
    /*
    public ResultSet BuscarExistente busca un pedido desde su ID en la base de datos y si lo encuentra regresa los datos de su tupla.
    */
    public ResultSet BuscarExistente(int id_pedido) throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT * FROM PEDIDO WHERE id_pedido = " + id_pedido);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al obtener el registro. El ID no existe en la base de datos.";
            return null;
        }
        return resultado;
    }
    
    /*
    public int CantidadRegistros hace una consulta para contar la cantidad de pedidos registrados en la base de datos.
    */
    public int CantidadRegistros() throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT COUNT(*) FROM PEDIDO");
            if(resultado.next()){
                numero_registros = resultado.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al identificar número de registros";
            return 0;
        }
        return numero_registros;
    }
    
}
    
