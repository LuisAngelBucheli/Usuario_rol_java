<%-- 
    Document   : users
    Created on : 11/04/2021, 12:24:42 AM
    Author     : ANGEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="js/main.js"></script>
        <title>Gestión de usuarios</title>
    </head>
    <body>
        <div class="alert alert-danger alert-dismissible fade show" style="display:none; position: fixed; z-index: 9999; width: 100%;" role="alert">
            <div class="alert-content"></div>
            <button type="button" class="close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="alert alert-success alert-dismissible fade show" style="display:none; position: fixed; z-index: 9999; width: 100%;" role="alert">
            <div class="alert-content"></div>
            <button type="button" class="close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="jumbotron"><h2>Gestión de usuarios</h2></div>
        <div class="container">
            <div class="m-panel">
                <div class="m-panel-heading">Parámetros de búsqueda</div>
                <div class="m-panel-body">
                    <form id="search">
                        <button class="btn btn-primary">Consultar</button>
                        <button class="btn btn-danger">Limpiar</button>
                        <hr>
                        <div class="form-group">
                            <label for="name">Nombre</label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                    </form>
                </div>
            </div>
            <div class="m-panel">
                <div class="m-panel-heading">Lista de usuarios</div>
                <div class="m-panel-body">
                    <form id="list">
                        <button class="btn btn-success">Crear</button>
                        <div id="usersList">
                            <table class="table table-info table-hover" style="margin-top: 5px; display:none;">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Rol</th>
                                        <th>Activo</th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                            <div class="alert alert-secondary" role="alert" style="display: none; margin-top: 10px;">
                                No se encontraron resultados
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="m-panel" style="display:none;">
                <div class="m-panel-heading">Información del usuario</div>
                <div class="m-panel-body">
                    <form id="crud">
                        <button class="btn btn-success">Guardar</button>
                        <button class="btn btn-warning" disabled>Editar</button>
                        <button class="btn btn-danger" disabled>Eliminar</button>
                        <hr>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Id</span>
                            </div>
                            <input type="number" class="form-control" id="iduser" name="iduser" readonly="true">
                        </div>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Nombre</span>
                            </div>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Rol</span>
                            </div>
                            <select class="form-control" id="rol" name="rol"></select>
                        </div>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Activo</span>
                            </div>
                                <input type="radio" class="form-control offset-md-1 col-md-1" id="active" name="active" value="Si">
                                <label class="col-md-2" style="padding-top: 8px;">Si</label>
                                <input type="radio" class="form-control offset-md-1 col-md-1" id="active" name="active" value="No">
                                <label class="col-md-2" style="padding-top: 8px;">No</label>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
