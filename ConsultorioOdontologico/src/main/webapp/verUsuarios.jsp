<%@page import="java.util.List"%>
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <!---->
    <%@ include file="components/header.jsp"%>
    <%@ include file="components/bodyInicio.jsp"%>
    <!--Boton<form action="SvUsuario" method="GET">
        <button class="btn btn-primary btn-user "type="submit">Mostrar Usuarios</button>
    </form>-->


    <!--Tabla-->
    <h1 class="h3 mb-2 text-gray-800">Lista de Usuarios</h1>
    <p class="mb-4">A continuación podra visualizar la lista completa de Usuarios.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">...</h6>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id Usuario</th>
                            <th>Nombre</th>
                            <th>Rol</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </thead>
                    <%
                        List<Usuario> listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios");
                        for (Usuario user : listaUsuarios) {
                    %>
                    <tbody>
                        <tr>
                            <!-- Cargamos las filas con los datos de los usuarios -->
                            <td id="id_usu<%=user.getId_user()%>"><%=user.getId_user()%></td>
                            <td><%=user.getNombre_user()%></td>
                            <td><%=user.getRol()%></td>
                            <th style="display: flex; width: 230px;">
                                <!-- Eliminamos el usuario llamando al servlet SvEliminarUsuarios con el método POST -->
                                <form name="eliminar" action="SvEliminarUsuarios" method="POST" onsubmit="return confirmarEliminar()">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" 
                                            style="background-color:red; margin-right: 5px;">
                                        <i class="fas fa-trash-alt"></i> Eliminar </button>
                                    <input type="hidden" name="id" value="<%=user.getId_user()%>">  
                                    <!-- UTILIZAMOS JAVASCRIPT -->
                                    <script> 
                                        function confirmarEliminar() {
                                            // Mostrar un cuadro de diálogo de confirmación
                                            var confirmacion = confirm("¿Estás seguro que deseas eliminar este usuario?");
                                            return confirmacion;
                                        }
                                    </script>
                                </form>
                                    <!-- Llamamos al servlet SvEditarUsaurios -->
                                <form name="editar" action="SvEditarUsuarios" method="GET">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" 
                                            style="margin-left: 5px;">
                                        <i class="fas fa-pencil-alt"></i> Editar </button>
                                    <input type="hidden" name="id" value="<%=user.getId_user()%>">   
                                </form> </th>
                                <% }%>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<br>


<%@ include file="components/bodyFinal.jsp"%>
