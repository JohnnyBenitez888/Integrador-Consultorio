<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyInicio.jsp"%>

<h1>Editar Usuarios</h1>
<p>...</p>

<form action="SvEditarUsuarios" method="POST" class="user" >
    <div class="form-group row">
        <%  Usuario user = (Usuario) request.getSession().getAttribute("user");%>      
        <div class="col-sm-4 mb-3 mb-sm-0">
            <input type="text" class="form-control" name="user" value="<%=user.getNombre_user()%>"
                   placeholder="Nombre Usuario">
        </div>
        <div class="col-sm-4">
            <input type="password" class="form-control" name="contrasenia" value="<%=user.getContrasenia()%>"
                   placeholder="Contraseña">
        </div>
        <div class="col-sm-4">
            <select class="form-control" id="rol" aria-label="default select example" name="Rol">
                <option value="" disabled selected><%=user.getRol()%></option>
                <option value="Odontologo/a">Odontólogo/a</option>
                <option value="Secretario/a">Secretario/a</option>
            </select>
        </div>
        <input type="hidden" name="id" value="<%=user.getId_user()%>"> 
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">Modificar Usuario</button>
    <hr>
</form>

<%@ include file="components/bodyFinal.jsp"%>
