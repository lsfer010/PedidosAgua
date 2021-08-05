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
public class Direccion extends Conexion {
    /*
    ResultSet resultado es el cascaron para guardar el resultado de las consultas.
    int numero_registros guarda el valor de la cantidad de registros en la tabla direccion.
    String Mensaje guarda una cadena de texto espcificando si una operación fué exitosa o fracasó.
    */
    private ResultSet resultado;
    private int numero_registros;
    private String Mensaje;
    
    /*
    public Direccion es el constructor que llama al método conectar de la clase Conexión para crear la conexión con la base de datos.
    */
    public Direccion(){
        Conectar();
    }
    
    /*
    public ResultSet Listado consulta todos los datos de las direcciones registradas en la base de datos.
    */
    public ResultSet Listado()throws Exception {
        try{
            getStmt();
	    resultado = stmt.executeQuery("select * from direccion");
            return resultado;
            
        } catch (SQLException ex){
                System.err.println("SQLException: " + ex.getMessage());
                return null;
        }
    }
    
    /*
    public ResultSet ListadoCompleto devuelve todos los datos de las direcciones con su nombre del cliente en ves de su ID.
    */
    public ResultSet ListadoCompleto()throws Exception {
        try{
            getStmt();
	    resultado = stmt.executeQuery("select id_direccion, nombre_cliente, calle, numero_interior, numero_exterior, colonia, referencia from cliente, direccion "
                    + "where id_cliente = cliente_id_cliente ");
            return resultado;
            
        } catch (SQLException ex){
                System.err.println("SQLException: " + ex.getMessage());
                return null;
        }
        
    }
    
    /*
    public String Insertar añade un registro a la base de datos.
    */
    public String Insertar(String calle, String numero_interior, String numero_exterior, String colonia, String referencia, int cliente_id_cliente) throws Exception {
        try {
            getStmt();
            stmt.executeUpdate("insert into direccion (calle, numero_interior, numero_exterior, colonia, referencia, cliente_id_cliente) "
                    + "values ('" + calle + "', '" + numero_interior + "', '" + numero_exterior + "', '" + colonia + "', '" + referencia + "', "
                            + cliente_id_cliente + ")");
            Mensaje = "Direccion registrada exitosamente";
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al registrar la dirección";
        }
        return Mensaje;
    }
    
    /*
    public String Actualizar actualiza el registro de una dirección en la base de datos.
    */
    public String Actualizar(int id_direccion, String calle, String numero_interior, String numero_exterior, String colonia, String referencia, int cliente_id_cliente) throws Exception {
        try {
        getStmt();
        stmt.executeUpdate("update direccion set calle='" + calle + "', numero_interior='" + numero_interior + "', numero_exterior='" + numero_exterior 
                + "', colonia='" + colonia + "', referencia='" + referencia + "', cliente_id_cliente=" + cliente_id_cliente + " where id_direccion=" + id_direccion);
            Mensaje = "Direccion editada exitosamente";
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al editar la direccion";
        }
        return Mensaje;
    }
    
    /* VERIFICAR SI ELIMINAR EL SIGUIENTE MÉTODO */
    public String Eliminar(int id_direccion) throws Exception {
        try {
            getStmt();
            stmt.executeUpdate("delete from direccion where id_direccion=" + id_direccion);
            Mensaje = "Direccion eliminada exitosamente";
        } catch (SQLException ex) {
           System.err.println("SQLException: " + ex.getMessage());
           Mensaje="Error al eliminar la dirección"; 
        }
        return Mensaje;
    }
    
    /*
    public ResultSet BuscarExistente busca una dirección desde su ID en la base de datos y si lo encuentra regresa los datos de su tupla.
    */
    public ResultSet BuscarExistente(int id_direccion) throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT * FROM DIRECCION WHERE id_direccion = " + id_direccion);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Mensaje="Error al obtener el registro. El ID no existe en la base de datos.";
            return null;
        }
        return resultado;
    }
    
    /*
    public int CantidadRegistros hace una consulta para contar la cantidad de direcciones registradas en la base de datos.
    */
    public int CantidadRegistros() throws Exception {
        try {
            getStmt();
            resultado = stmt.executeQuery("SELECT COUNT(*) FROM DIRECCION");
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
    
    public ResultSet ListarIdDireccion()throws Exception {
        try{
            getStmt();
	    resultado = stmt.executeQuery("select id_direccion, calle, numero_interior, numero_exterior, colonia from direccion");
            return resultado;
            
        } catch (SQLException ex){
                System.err.println("SQLException: " + ex.getMessage());
                return null;
        }
    }
}
