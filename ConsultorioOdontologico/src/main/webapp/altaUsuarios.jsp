<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyInicio.jsp"%>

<h1>Alta Usuarios</h1>
<p>...</p>

<form class="user" action="SvUsuario" method="POST">
    <div class="form-group row">
        <div class="col-sm-4 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control" id="user"
                   placeholder="Nombre Usuario">
        </div>
        <div class="col-sm-4">
            <input type="password" class="form-control " id="contrasenia"
                   placeholder="Contraseña">
        </div>
        <div class="col-sm-4">
            <select class="form-control " id="rol" aria-label="default select example" name="Rol">
                <option value="" disabled selected>Rol</option>
                <option value="odonto">Odontólogo/a</option>
                <option value="secre">Secretario/a</option>
            </select>
        </div>
    </div>
    <a href="" class="btn btn-primary btn-user btn-block">
        Crear Usuario
    </a>
    <hr>
</form>

<%@ include file="components/bodyFinal.jsp"%>
