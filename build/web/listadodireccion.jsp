<%-- 
    Document   : listadocliente
    Created on : 11/12/2020, 03:47:01 PM
    Author     : lsfer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <title>Listado Direcciones</title>
    </head>
    <body>
        <div class="container_listado">
            <section class="titulo">
                <h1>Listado de Direcciones</h1>
            </section>
            <section>
                <form action="CRUD" method="post">  
                <% 
                String consulta[][] = (String[][]) request.getAttribute("consulta_array");
                int cantidad_registros = Integer.parseInt(request.getAttribute("cantidad_reg_array").toString());
                out.println("<table class=\"table table-borderer table-hover\">");
                out.println("<tr><td>ID</td><td>Cliente</td><td>Calle</td><td>Núm. Interior</td><td>Núm. Exterior</td><td>Fracc/Colonia</td><td>Referencia</td><td>Editar</td></tr>");
                for(int i=0; i<cantidad_registros; i++){
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(consulta[i][0]);
                    out.println("</td>");
                    out.println("<td>");
                    out.println(consulta[i][1]);
                    out.println("</td>");
                    out.println("<td>");
                    out.println(consulta[i][2]);
                    out.println("</td>");
                    out.println("<td>");
                    out.println(consulta[i][3]);
                    out.println("</td>");
                    out.println("<td>");
                    out.println(consulta[i][4]);
                    out.println("</td>");
                    out.println("<td>");
                    out.println(consulta[i][5]);
                    out.println("</td>");
                    out.println("<td>");
                    out.println(consulta[i][6]);
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<button type=\"submit\" class=\"btn btn-success\" name=\"opcion\"  value=\"btn_editarlistado_direccion-" + consulta[i][0] + "\">Editar</button>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                %>
                </form>  
            </section>
            <section>
                <form action="index.jsp" method="post">
                    <div class="trans">
                        <button type="submit" class="btn btn-success" id="btn_regresar" name="opcion" value="Regresar">Regresar</button>
                    </div>
                </form>
            </section>    
        </div>
    </body>
</html>
