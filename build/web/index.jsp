
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Índice</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <% 
        /*Aquí se utiliza javascript para comprobar si existe un mensaje para mostrar, se hace dentro de un try-catch*/    
        try {
        if(!(request.getAttribute("mensaje") == null)) {
            String mensaje = request.getAttribute("mensaje").toString();
            out.println("<script type=\"text/javascript\">"
                    + "alert('" + mensaje + "');"
                    + "</script>");
        }
        } catch (Exception e) {
            
        }
        %>
    </head>
    <body>
        <div class="container">
            <section class="titulo">
                <h1>Índice</h1>
            </section>
            <section>
                <h4>Clientes</h4>
                <form action="CRUD" method="post">
                    <button type="submit" class="btn btn-info" id="btn_agregar_cliente" name="opcion" value="btn_agregar_cliente_index-0">Agregar Cliente</button>
                    <button type="submit" class="btn btn-info" id="btn_listado_cliente" name="opcion" value="btn_listado_cliente-0">Listado Clientes</button>
                </form>
            </section>
            <section>
                <h4>Direcciones</h4>
                <form action="CRUD" method="post">
                    <button type="submit" class="btn btn-info" id="btn_agregar_direccion" name="opcion" value="btn_agregar_direccion_index-0">Agregar Direccion</button>
                    <button type="submit" class="btn btn-info" id="btn_listado_direccion" name="opcion" value="btn_listado_direccion-0">Listado Direcciones</button>
                </form>
            </section>
            <section>
                <h4>Pedidos</h4>
                <form action="CRUD" method="post">
                    <button type="submit" class="btn btn-info" id="btn_agregar_pedido" name="opcion" value="btn_agregar_pedido_index-0">Agregar Pedido</button>
                    <button type="submit" class="btn btn-info" id="btn_listado_pedido" name="opcion" value="btn_listado_pedido-0">Listado Pedidos</button>
                </form>
            </section>
            <section>
                <h4>Productos</h4>
                <form action="CRUD" method="post">
                    <button type="submit" class="btn btn-info" id="btn_agregar_producto" name="opcion" value="btn_agregar_producto_index-0">Agregar Producto</button>
                    <button type="submit" class="btn btn-info" id="btn_listado_producto" name="opcion" value="btn_listado_producto-0">Listado Productos</button>
                </form>
            </section>
        </div>
    </body>
</html>
