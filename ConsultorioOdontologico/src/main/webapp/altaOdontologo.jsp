<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="components/header.jsp"%>
<%@ include file="components/bodyInicio.jsp"%>

<h1>Alta Odontólogo</h1>
<p>...</p>

<form class="user">
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control" id="nombre"
                   placeholder="Nombre">
        </div>
        <div class="col-sm-6">
            <input type="text" class="form-control " id="apellido"
                   placeholder="Apellido">
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control " id="dni"
                   placeholder="DNI">
        </div>
        <div class="col-sm-6">
            <input type="text" class="form-control"
                   id="exampleDireccion" placeholder="Dirección">
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control "
                   id="telefono" placeholder="Teléfono">
        </div>
        <div class="col-sm-6">
            <select class="form-control " id="especialidad" aria-label="default select example" name="Especialidad">
                <option value="" disabled selected>Especialidad</option>
                <option value="odontogeneral">Odontología General</option>
                <option value="maxilofa">Cirugía Oral y Maxilofacial</option>
                <option value="endodoncia">Endodoncia</option>
                <option value="odonEste">Odontología Estética</option>
                <option value="ortodoncia">Ortodoncia</option>
                <option value="odonpedia">Odontopediatría</option>PERIODONCIA,PROSTODONCIA_Y_REHABILITACION_ORAL
                <option value="patobucal">Patología Bucal</option>
                <option value="perio">Periodoncia</option>
                <option value="prosto">Prostodoncia y Rehabilitación Oral</option>
                <option value="estetica">Odontología Estética</option>
            </select>

        </div>
    </div>

    <a href="" class="btn btn-primary btn-user btn-block">
        Crear Odontólogo
    </a>
    <hr>
</form>

<%@ include file="components/bodyFinal.jsp"%>