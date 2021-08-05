/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Direccion;
import Modelo.Pedido;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lsfer
 */
public class CRUD extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*Las siguientes dos instrucciones permiten que se manejen caracteres latinos*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        /*Se instancian los modelos para poder utilizar las operaciones ABCD*/
        Cliente mod_cliente = new Cliente();
        Direccion mod_direccion = new Direccion();
        Pedido mod_pedido = new Pedido();
        Producto mod_producto = new Producto();
        /*Cascaron para los mensajes que regresan los modelos y que se muestran desde un alert javascript en vista index.jsp*/
        String Mensaje = "";
        /*Todas las operaciones van dentro de un try - catch*/
        try {
            /*Siempre se pide el parámetro opción de los botones de todas las vistas para saber qué acción realizar.
            El estandar es que tengan un nombre, un guion y un número que puede ser 0 o el ID de cada tupla en caso de los listados.
            se parte para identificar la acción y el ID según el caso.*/
            String cadena = request.getParameter("opcion");
            String[] cadena_partida = cadena.split("-");
            String operacion = cadena_partida[0];
            String idExtraido = cadena_partida[1];
            /*Comienza el switch para identificar la operación a realizar.*/
            switch (operacion) {
                /*Viene de la vista index y redirige a agregarcliente.*/
                case "btn_agregar_cliente_index": {
                    RequestDispatcher rd;
                    rd = request.getRequestDispatcher("/agregarcliente.jsp");
                    rd.forward(request, response);
                }
                
                /*Realiza la inserción de datos y regresa a index.*/
                case "btn_agregar_cliente_form": {
                    RequestDispatcher rd;
                    if(!(request.getParameter("rfc_cliente") == null)) {
                        String rfc_cliente = request.getParameter("rfc_cliente");
                        String nombre_cliente = request.getParameter("nombre_cliente");
                        String telefono_cliente = request.getParameter("telefono_cliente");
                        Mensaje = mod_cliente.Insertar(rfc_cliente, nombre_cliente, telefono_cliente);
                        request.setAttribute("mensaje", Mensaje);
                        rd = request.getRequestDispatcher("/index.jsp");
                        rd.forward(request, response);
                    }
                }
                
                /*Viene de index y realiza la consulta de lista para enviarsela a la vista listadocliente.*/
                case "btn_listado_cliente" : {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    int cantidad_registros = mod_cliente.CantidadRegistros();
                    String consulta[][] = new String[cantidad_registros][4]; 
                    res = mod_cliente.Listado();
                    int fila = 0;
                    while(res.next()){
                        consulta[fila][0] = res.getString("id_cliente");
                        consulta[fila][1] = res.getString("RFC_cliente");
                        consulta[fila][2] = res.getString("nombre_cliente");
                        consulta[fila][3] = res.getString("tel_cliente");
                        fila++;
                    }
                    request.setAttribute("consulta_array", consulta);
                    request.setAttribute("cantidad_reg_array", cantidad_registros);
                    rd = request.getRequestDispatcher("/listadocliente.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista listado y llama la vista editarcliente mandandole los datos del registro seleccionado.*/
                case "btn_editarlistado_cliente": {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    String id_cliente = idExtraido;
                    res = mod_cliente.BuscarExistente(Integer.parseInt(id_cliente));
                    if(res.next()){
                        request.setAttribute("id_cliente", res.getString("id_cliente"));
                        request.setAttribute("rfc_cliente", res.getString("RFC_cliente"));
                        request.setAttribute("nombre_cliente", res.getString("nombre_cliente"));
                        request.setAttribute("telefono_cliente", res.getString("tel_cliente"));
                    }
                    rd = request.getRequestDispatcher("/editarcliente.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista editarcliente y actualiza el cliente que se cargó en dicha vista y regresa a index.*/
                case "btn_editar_cliente_form": {
                    RequestDispatcher rd;
                    String id_cliente = request.getParameter("id_cliente");
                    String rfc_cliente = request.getParameter("rfc_cliente");
                    String nombre_cliente = request.getParameter("nombre_cliente");
                    String telefono_cliente = request.getParameter("telefono_cliente");
                    Mensaje = mod_cliente.Actualizar(Integer.parseInt(id_cliente),rfc_cliente, nombre_cliente, telefono_cliente);
                    request.setAttribute("mensaje", Mensaje);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista index y redirige a agregardireccion, además hace una consulta para listar los clientes.*/
                case "btn_agregar_direccion_index": {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    int cantidad_registros = mod_cliente.CantidadRegistros();
                    String consulta[][] = new String[cantidad_registros][2]; 
                    res = mod_cliente.ListarIdNombre();
                    int fila = 0;
                    while(res.next()){
                        consulta[fila][0] = res.getString("id_cliente");
                        consulta[fila][1] = res.getString("nombre_cliente");
                        fila++;
                    }
                    request.setAttribute("consulta_array", consulta);
                    request.setAttribute("cantidad_reg_array", cantidad_registros);
                    rd = request.getRequestDispatcher("/agregardireccion.jsp");
                    rd.forward(request, response);
                }
                
                /*Realiza la inserción de datos y regresa a index.*/
                case "btn_agregar_direccion_form": {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    if(!(request.getParameter("calle_direccion") == null)) {
                        String calle_direccion = request.getParameter("calle_direccion");
                        String numero_interior_direccion = request.getParameter("numero_interior_direccion");
                        String numero_exterior_direccion = request.getParameter("numero_exterior_direccion");
                        String colonia_direccion = request.getParameter("colonia_direccion");
                        String referencia_direccion = request.getParameter("referencia_direccion");
                        String cliente_id_cliente = request.getParameter("cliente_id_cliente");
                        Mensaje = mod_direccion.Insertar(calle_direccion,numero_interior_direccion,numero_exterior_direccion,colonia_direccion,referencia_direccion,Integer.parseInt(cliente_id_cliente));
                        request.setAttribute("mensaje", Mensaje);
                        rd = request.getRequestDispatcher("/index.jsp");
                        rd.forward(request, response);
                    }
                }
                
                /*Viene de index y realiza la consulta de lista para enviarsela a la vista listadodireccion.*/
                case "btn_listado_direccion" : {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    int cantidad_registros = mod_direccion.CantidadRegistros();
                    String consulta[][] = new String[cantidad_registros][7]; 
                    res = mod_direccion.ListadoCompleto();
                    int fila = 0;
                    while(res.next()){
                        consulta[fila][0] = res.getString("id_direccion");
                        consulta[fila][1] = res.getString("nombre_cliente");
                        consulta[fila][2] = res.getString("calle");
                        consulta[fila][3] = res.getString("numero_interior");
                        consulta[fila][4] = res.getString("numero_exterior");
                        consulta[fila][5] = res.getString("colonia");
                        consulta[fila][6] = res.getString("referencia");
                        fila++;
                    }
                    request.setAttribute("consulta_array", consulta);
                    request.setAttribute("cantidad_reg_array", cantidad_registros);
                    rd = request.getRequestDispatcher("/listadodireccion.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista listado y llama la vista editardireccion mandandole los datos del registro seleccionado.*/
                case "btn_editarlistado_direccion": {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    String id_direccion = idExtraido;
                    res = mod_direccion.BuscarExistente(Integer.parseInt(id_direccion));
                    if(res.next()){
                        request.setAttribute("id_direccion", res.getString("id_direccion"));
                        request.setAttribute("calle_direccion", res.getString("calle"));
                        request.setAttribute("numero_interior_direccion", res.getString("numero_interior"));
                        request.setAttribute("numero_exterior_direccion", res.getString("numero_exterior"));
                        request.setAttribute("colonia_direccion", res.getString("colonia"));
                        request.setAttribute("referencia_direccion", res.getString("referencia"));
                        request.setAttribute("cliente_id_cliente", res.getString("cliente_id_cliente"));
                    }
                    /*listar nuevamente clientes para el combobox o <select>*/
                    int cantidad_registros = mod_cliente.CantidadRegistros();
                    String consulta[][] = new String[cantidad_registros][2]; 
                    res = mod_cliente.ListarIdNombre();
                    int fila = 0;
                    while(res.next()){
                        consulta[fila][0] = res.getString("id_cliente");
                        consulta[fila][1] = res.getString("nombre_cliente");
                        fila++;
                    }
                    request.setAttribute("consulta_array", consulta);
                    request.setAttribute("cantidad_reg_array", cantidad_registros);
                    rd = request.getRequestDispatcher("/editardireccion.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista editardireccion y actualiza la direccion que se cargó en dicha vista y regresa a index.*/
                case "btn_editar_direccion_form": {
                    RequestDispatcher rd;
                    String id_direccion = request.getParameter("id_direccion");
                    String calle_direccion = request.getParameter("calle_direccion");
                    String numero_interior_direccion = request.getParameter("numero_interior_direccion");
                    String numero_exterior_direccion = request.getParameter("numero_exterior_direccion");
                    String colonia_direccion = request.getParameter("colonia_direccion");
                    String referencia_direccion = request.getParameter("referencia_direccion");
                    String cliente_id_cliente = request.getParameter("cliente_id_cliente");
                    
                    Mensaje = mod_direccion.Actualizar(Integer.parseInt(id_direccion),calle_direccion,numero_interior_direccion,numero_exterior_direccion,colonia_direccion,referencia_direccion,Integer.parseInt(cliente_id_cliente));
                    request.setAttribute("mensaje", Mensaje);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista index y redirige a agregarproducto*/
                case "btn_agregar_producto_index": {
                   RequestDispatcher rd;
                   rd = request.getRequestDispatcher("/agregarproducto.jsp");
                   rd.forward(request, response); 
                }
                
                /*Realiza la inserción de datos y regresa a index.*/
                case "btn_agregar_producto_form": {
                    RequestDispatcher rd;
                    if(!(request.getParameter("nombre_producto") == null)) {
                        String nombre_producto = request.getParameter("nombre_producto");
                        String unidad_medida_producto = request.getParameter("unidad_medida_producto");
                        Mensaje = mod_producto.Insertar(nombre_producto,unidad_medida_producto);
                        request.setAttribute("mensaje", Mensaje);
                        rd = request.getRequestDispatcher("/index.jsp");
                        rd.forward(request, response);
                    }
                }
                
                /*Viene de index y realiza la consulta de lista para enviarsela a la vista listadoproducto.*/
                case "btn_listado_producto": {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    int cantidad_registros = mod_producto.CantidadRegistros();
                    String consulta[][] = new String[cantidad_registros][3]; 
                    res = mod_producto.Listado();
                    int fila = 0;
                    while(res.next()){
                        consulta[fila][0] = res.getString("id_producto");
                        consulta[fila][1] = res.getString("nombre_producto");
                        consulta[fila][2] = res.getString("unidad_medida");
                        fila++;
                    }
                    request.setAttribute("consulta_array", consulta);
                    request.setAttribute("cantidad_reg_array", cantidad_registros);
                    rd = request.getRequestDispatcher("/listadoproducto.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista listado y llama la vista editarproducto mandandole los datos del registro seleccionado.*/
                case "btn_editarlistado_producto" : {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    String id_producto = idExtraido;
                    res = mod_producto.BuscarExistente(Integer.parseInt(id_producto));
                    if(res.next()){
                        request.setAttribute("id_producto", res.getString("id_producto"));
                        request.setAttribute("nombre_producto", res.getString("nombre_producto"));
                        request.setAttribute("unidad_medida_producto", res.getString("unidad_medida"));
                    }
                    rd = request.getRequestDispatcher("/editarproducto.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista editarproducto y actualiza el producto que se cargó en dicha vista y regresa a index.*/
                case "btn_editar_producto_form" : {
                    RequestDispatcher rd;
                    String id_producto = request.getParameter("id_producto");
                    String nombre_producto = request.getParameter("nombre_producto");
                    String unidad_medida_producto = request.getParameter("unidad_medida_producto");
                    Mensaje = mod_producto.Actualizar(Integer.parseInt(id_producto),nombre_producto, unidad_medida_producto);
                    request.setAttribute("mensaje", Mensaje);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista index y redirige a agregarpedido, además hace consultas a direccion y producto para listarlos y seleccionarlos*/
                case "btn_agregar_pedido_index": {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    /*Listar productos*/
                    int cantidad_registros_prod = mod_producto.CantidadRegistros();
                    String consulta_prod[][] = new String[cantidad_registros_prod][3]; 
                    res = mod_producto.ListarIdNombre();
                    int fila_prod = 0;
                    while(res.next()){
                        consulta_prod[fila_prod][0] = res.getString("id_producto");
                        consulta_prod[fila_prod][1] = res.getString("nombre_producto");
                        consulta_prod[fila_prod][2] = res.getString("unidad_medida");
                        fila_prod++;
                    }
                    request.setAttribute("consulta_array_prod", consulta_prod);
                    request.setAttribute("cantidad_reg_array_prod", cantidad_registros_prod);
                    /*Listar direcciones*/
                    int cantidad_registros_dir = mod_direccion.CantidadRegistros();
                    String consulta_dir[][] = new String[cantidad_registros_dir][5]; 
                    res = mod_direccion.ListarIdDireccion();
                    int fila_dir = 0;
                    while(res.next()){
                        consulta_dir[fila_dir][0] = res.getString("id_direccion");
                        consulta_dir[fila_dir][1] = res.getString("calle");
                        consulta_dir[fila_dir][2] = res.getString("numero_interior");
                        consulta_dir[fila_dir][3] = res.getString("numero_exterior");
                        consulta_dir[fila_dir][4] = res.getString("colonia");
                        fila_dir++;
                    }
                    request.setAttribute("consulta_array_dir", consulta_dir);
                    request.setAttribute("cantidad_reg_array_dir", cantidad_registros_dir);
                    rd = request.getRequestDispatcher("/agregarpedido.jsp");
                    rd.forward(request, response);
                }
                
                /*Realiza la inserción de datos y regresa a index.*/
                case "btn_agregar_pedido_form": {
                    RequestDispatcher rd;
                    if(!(request.getParameter("cantidad_pedido") == null)) {
                        String cantidad_pedido = request.getParameter("cantidad_pedido");
                        String fecha_pedido = request.getParameter("fecha_pedido");
                        String estado_pedido = request.getParameter("estado_pedido");
                        String producto_id_producto = request.getParameter("producto_id_producto");
                        String direccion_id_direccion = request.getParameter("direccion_id_direccion");
                        Mensaje = mod_pedido.Insertar(Integer.parseInt(cantidad_pedido),fecha_pedido,estado_pedido,Integer.parseInt(producto_id_producto),Integer.parseInt(direccion_id_direccion));
                        request.setAttribute("mensaje", Mensaje);
                        rd = request.getRequestDispatcher("/index.jsp");
                        rd.forward(request, response);
                    }
                }
                
                /*Viene de index y realiza la consulta de lista para enviarsela a la vista listadopedido.*/
                case "btn_listado_pedido": {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    int cantidad_registros = mod_pedido.CantidadRegistros();
                    String consulta[][] = new String[cantidad_registros][11]; 
                    res = mod_pedido.ListadoCompleto();
                    int fila = 0;
                    while(res.next()){
                        consulta[fila][0] = res.getString("id_pedido");
                        consulta[fila][1] = res.getString("fecha_pedido");
                        consulta[fila][2] = res.getString("cantidad_pedido");
                        consulta[fila][3] = res.getString("estado_pedido");
                        consulta[fila][4] = res.getString("nombre_producto");
                        consulta[fila][5] = res.getString("nombre_cliente");
                        consulta[fila][6] = res.getString("calle");
                        consulta[fila][7] = res.getString("numero_interior");
                        consulta[fila][8] = res.getString("numero_exterior");
                        consulta[fila][9] = res.getString("colonia");
                        consulta[fila][10] = res.getString("referencia");
                        fila++;
                    }
                    request.setAttribute("consulta_array", consulta);
                    request.setAttribute("cantidad_reg_array", cantidad_registros);
                    rd = request.getRequestDispatcher("/listadopedido.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista listado y llama la vista editarpedido mandandole los datos del registro seleccionado, 
                además vuelve a hacer consultas de direcciones y productos.*/
                case "btn_editarlistado_pedido": {
                    RequestDispatcher rd;
                    ResultSet res=null;
                    String id_pedido = idExtraido;
                    res = mod_pedido.BuscarExistente(Integer.parseInt(id_pedido));
                    if(res.next()){
                        request.setAttribute("id_pedido", res.getString("id_pedido"));
                        request.setAttribute("cantidad_pedido", res.getString("cantidad_pedido"));
                        request.setAttribute("fecha_pedido", res.getString("fecha_pedido"));
                        request.setAttribute("estado_pedido", res.getString("estado_pedido"));
                        request.setAttribute("producto_id_producto", res.getString("producto_id_producto"));
                        request.setAttribute("direccion_id_direccion", res.getString("direccion_id_direccion"));
                    }
                    /*listar nuevamente productos y direcciones para el combobox o <select>*/
                    /*Listar productos*/
                    int cantidad_registros_prod = mod_producto.CantidadRegistros();
                    String consulta_prod[][] = new String[cantidad_registros_prod][3]; 
                    res = mod_producto.ListarIdNombre();
                    int fila_prod = 0;
                    while(res.next()){
                        consulta_prod[fila_prod][0] = res.getString("id_producto");
                        consulta_prod[fila_prod][1] = res.getString("nombre_producto");
                        consulta_prod[fila_prod][2] = res.getString("unidad_medida");
                        fila_prod++;
                    }
                    request.setAttribute("consulta_array_prod", consulta_prod);
                    request.setAttribute("cantidad_reg_array_prod", cantidad_registros_prod);
                    /*Listar direcciones*/
                    int cantidad_registros_dir = mod_direccion.CantidadRegistros();
                    String consulta_dir[][] = new String[cantidad_registros_dir][5]; 
                    res = mod_direccion.ListarIdDireccion();
                    int fila_dir = 0;
                    while(res.next()){
                        consulta_dir[fila_dir][0] = res.getString("id_direccion");
                        consulta_dir[fila_dir][1] = res.getString("calle");
                        consulta_dir[fila_dir][2] = res.getString("numero_interior");
                        consulta_dir[fila_dir][3] = res.getString("numero_exterior");
                        consulta_dir[fila_dir][4] = res.getString("colonia");
                        fila_dir++;
                    }
                    request.setAttribute("consulta_array_dir", consulta_dir);
                    request.setAttribute("cantidad_reg_array_dir", cantidad_registros_dir);
                    rd = request.getRequestDispatcher("/editarpedido.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista editarpedido y actualiza el pedido que se cargó en dicha vista y regresa a index.*/
                case "btn_editar_pedido_form": {
                    RequestDispatcher rd;
                    String id_pedido = request.getParameter("id_pedido");
                    String cantidad_pedido = request.getParameter("cantidad_pedido");
                    String fecha_pedido = request.getParameter("fecha_pedido");
                    String estado_pedido = request.getParameter("estado_pedido");
                    String producto_id_producto = request.getParameter("producto_id_producto");
                    String direccion_id_direccion = request.getParameter("direccion_id_direccion");
                    
                    Mensaje = mod_pedido.Actualizar(Integer.parseInt(id_pedido),Integer.parseInt(cantidad_pedido),fecha_pedido,estado_pedido,Integer.parseInt(producto_id_producto),Integer.parseInt(direccion_id_direccion));
                    request.setAttribute("mensaje", Mensaje);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }
                
                /*Viene de la vista listado de pedidos y borra el registro seleccionado, es el único módulo que lo hace*/
                case "btn_eliminarlistado_pedido": {
                    RequestDispatcher rd;
                    String id_pedido = idExtraido;
                    Mensaje = mod_pedido.Eliminar(Integer.parseInt(id_pedido));
                    request.setAttribute("mensaje", Mensaje);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
