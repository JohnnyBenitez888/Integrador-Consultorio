<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyInicio.jsp"%>

<h1>Alta Usuarios</h1>
<p>...</p>

<form action="SvUsuario" method="POST" class="user" >
    <div class="form-group row">
        <div class="col-sm-4 mb-3 mb-sm-0">
            <input type="text" class="form-control" name="user"
                   placeholder="Nombre Usuario">
        </div>
        <div class="col-sm-4">
            <input type="password" class="form-control" name="contrasenia"
                   placeholder="Contraseña">
        </div>
        <div class="col-sm-4">
            <select class="form-control" id="rol" aria-label="default select example" name="Rol">
                <option value="" disabled selected>Rol</option>
                <option value="Odontologo/a">Odontólogo/a</option>
                <option value="Secretario/a">Secretario/a</option>
            </select>
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">Crearar Usuario</button>
    <hr>
</form>

<%@ include file="components/bodyFinal.jsp"%>
