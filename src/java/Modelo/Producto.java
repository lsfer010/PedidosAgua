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
public class Producto extends Conexion {
    /*
    ResultSet resultado es el cascaron para guardar el resultado de las consultas.
    int numero_registros guarda el valor de la cantidad de registros en la tabla producto.
    String Mensaje guarda una cadena de texto espcificando si una operación fué exitosa o fracasó.
    */
    private ResultSet resultado;
    private int numero_registros;
    private String Mensaje;
    
    /*
    public Producto es el constructor que llama al método conectar de la clase Conexión para crear la conexión con la base de datos.
    */
    public Producto() {
        Conectar();
    }
    
    /*
    public ResultSet Listado consulta todos los datos de los productos registrados en la base de datos.
    */
    public ResultSet Listado()throws Exception {
        try{
            getStmt();
	    resultado = stmt.executeQuery("select * from producto");
            return resultado;
            
        } catch (SQLException ex){
                System.err.println("SQLException: " + ex.getMessage());
                return null;
        }
        
    }
    
    /*
    public String Insertar añade un registro a la base de datos.
    */
    public String Insertar(String nombre_producto, String unidad_medida) throws Exception {
        try {
            getStmt();
            stmt.executeUpdate("insert into producto (nombre_producto, unidad_medida) "
                    + "values ('" + nombre_producto + "', '" + unidad_medida + "')");
            Mensaje = "Producto registrado exitosamente";
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al registrar el producto";
        }
        return Mensaje;
    }
    
    /*
    public String Actualizar actualiza el registro de un producto en la base de datos.
    */
    public String Actualizar(int id_producto, String nombre_producto, String unidad_medida) throws Exception {
        try {
        getStmt();
        stmt.executeUpdate("update producto set nombre_producto='" + nombre_producto + "', unidad_medida='" + unidad_medida + "' where id_producto=" + id_producto);
            Mensaje = "Producto editado exitosamente";
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al editar el producto";
        }
        return Mensaje;
    }
    
    /* VERIFICAR SI ELIMINAR EL SIGUIENTE MÉTODO */
    public String Eliminar(int id_producto) throws Exception {
        try {
            getStmt();
            stmt.executeUpdate("delete from producto where id_producto=" + id_producto);
            Mensaje = "Producto eliminado exitosamente";
        } catch (SQLException ex) {
           System.err.println("SQLException: " + ex.getMessage());
           Mensaje="Error al eliminar el producto"; 
        }
        return Mensaje;
    }
    
    /*
    public ResultSet BuscarExistente busca un producto desde su ID en la base de datos y si lo encuentra regresa los datos de su tupla.
    */
    public ResultSet BuscarExistente(int id_producto) throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT * FROM PRODUCTO WHERE id_producto = " + id_producto);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al obtener el registro. El ID no existe en la base de datos.";
            return null;
        }
        return resultado;
    }
    
    /*
    public ResultSet ListarIdNombre lista los nombres de los productos junto con su ID para uso en etiqueta <select> 
    */
    public ResultSet ListarIdNombre() throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT id_producto, nombre_producto, unidad_medida FROM PRODUCTO");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al listar.";
            return null;
        }
        return resultado;
    }
    
    /*
    public int CantidadRegistros hace una consulta para contar la cantidad de productos registrados en la base de datos.
    */
    public int CantidadRegistros() throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT COUNT(*) FROM PRODUCTO");
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
    

