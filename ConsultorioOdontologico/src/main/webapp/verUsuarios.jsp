<%@page import="java.util.List"%>
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <!---->
    <%@ include file="components/header.jsp"%>
    <%@ include file="components/bodyInicio.jsp"%>
    <!--Boton-->
    <form action="SvUsuario" method="GET">
        <button class="btn btn-primary btn-user "type="submit">Mostrar Usuarios</button>
    </form>

    <!--Tabla-->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista de Usuarios</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Contraseña</th>
                            <th>Rol</th>
                        </tr>
                    </thead>
                    <%
                        List<Usuario> listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios");
                        for (Usuario user : listaUsuarios) {
                    %>
                    <tbody>
                        <tr>
                            <td><%=user.getNombre_user()%></td>
                            <td><%=user.getContrasenia()%></td>
                            <td><%=user.getRol()%></td>
                        </tr>
                        <% }%>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <br>

    <%@ include file="components/bodyFinal.jsp"%>
