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
public class Cliente extends Conexion{
    /*
    ResultSet resultado es el cascaron para guardar el resultado de las consultas.
    int numero_registros guarda el valor de la cantidad de registros en la tabla cliente.
    String Mensaje guarda una cadena de texto espcificando si una operación fué exitosa o fracasó.
    */
    private ResultSet resultado;
    private int numero_registros;
    private String Mensaje;
    
    /*
    public Cliente es el constructor que llama al método conectar de la clase Conexión para crear la conexión con la base de datos.
    */
    public Cliente(){
        Conectar();
    }
    
    /*
    public ResultSet Listado consulta todos los datos de los clientes registrados en la base de datos.
    */
    public ResultSet Listado()throws Exception {
        try{
            getStmt();
	    resultado = stmt.executeQuery("select * from cliente");
            return resultado;
            
        } catch (SQLException ex){
                System.err.println("SQLException: " + ex.getMessage());
                return null;
        }
    }
    
    /*
    public String Insertar añade un registro a la base de datos.
    */
    public String Insertar(String RFC_cliente, String nombre_cliente, String tel_cliente) throws Exception {
        try {
            getStmt();
            stmt.executeUpdate("insert into cliente (RFC_cliente, nombre_cliente, tel_cliente) "
                    + "values ('" + RFC_cliente + "', '" + nombre_cliente + "', '" + tel_cliente + "')");
            Mensaje = "Cliente Registrado Exitosamente";
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al registrar al cliente";
        }
        return Mensaje;
    }
    
    /*
    public String Actualizar actualiza el registro de un cliente en la base de datos.
    */
    public String Actualizar(int ID_cliente, String RFC_cliente, String nombre_cliente, String tel_cliente) throws Exception {
        try {
        getStmt();
        stmt.executeUpdate("update cliente set RFC_cliente='" + RFC_cliente + "', nombre_cliente='" + nombre_cliente + "', tel_cliente='" + tel_cliente 
                + "' where id_cliente=" + ID_cliente);
            Mensaje = "Cliente Editado Exitosamente";
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al editar el cliente";
        }
        return Mensaje;
    }
    
    /* VERIFICAR SI ELIMINAR EL SIGUIENTE MÉTODO */
    public String Eliminar(int ID_cliente) throws Exception { 
        try {
            getStmt();
            stmt.executeUpdate("delete from cliente where id=" + ID_cliente);
            Mensaje = "Cliente Eliminado Exitosamente";
        } catch (SQLException ex) {
           System.err.println("SQLException: " + ex.getMessage());
           Mensaje="Error al Eliminar el Cliente"; 
        }
        return Mensaje;
    }
    
    /*
    public ResultSet BuscarExistente busca un cliente desde su ID en la base de datos y si lo encuentra regresa los datos de su tupla.
    */
    public ResultSet BuscarExistente(int ID_cliente) throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT * FROM CLIENTE WHERE id_cliente = " + ID_cliente);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al obtener el registro. El ID no existe en la base de datos.";
            return null;
        }
        return resultado;
    }
    
    /*
    public int CantidadRegistros hace una consulta para contar la cantidad de clientes registrados en la base de datos.
    */
    public int CantidadRegistros() throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT COUNT(*) FROM CLIENTE");
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
    
    /*
    public ResultSet ListarIdNombre lista los id y nombres de todos los clientes ordenados para usarse en un <select> "combobox"
    */
    public ResultSet ListarIdNombre() throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT id_cliente, nombre_cliente FROM CLIENTE ORDER BY nombre_cliente");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al realizar la búsqueda";
            return null;
        }
        return resultado;
    }
    
}
