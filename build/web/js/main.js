$(document).ready(function(){
    //hide alerts
    $('.alert button').on('click',function(){
       $(this).closest('.alert').fadeOut(); 
    });

    //Cargar listado de roles
    listRoles();

    //crear
    $('form#list .btn-success').on('click',function(evt){
        evt.preventDefault();
        $('form#crud').closest('.m-panel').show();
        $('form#crud .btn-danger,.btn-warning').attr('disabled', 'disabled');
        $('form#crud .btn-success').removeAttr('disabled');
        $('form#crud [name="name"]').focus();
        cleanForm('crud');
    });
    
    //limpiar consulta
    $('form#search .btn-danger').on('click',function(evt){
        evt.preventDefault();
        $('form#crud').closest('.m-panel').hide();
        $('form#crud .btn-danger,.btn-warning').attr('disabled', 'disabled');
        $('form#crud .btn-success').removeAttr('disabled');
        $('#usersList .table').hide();
        cleanForm('search');
        cleanForm('crud');
    });
    
    //Guardar usuario
    $('form#crud .btn-success').on('click',function(evt){
        evt.preventDefault();
        saveUser();
    });
    
    //Editar usuario
    $('form#crud .btn-warning').on('click',function(evt){
        evt.preventDefault();
        modifyUser();
    });

    //Eliminar usuario
    $('form#crud .btn-danger').on('click',function(evt){
        evt.preventDefault();
        deleteUser();
    });
    
    //Listar Usuarios
    $('form#search .btn-primary').on('click',function(evt){
        evt.preventDefault();
        listUsers();
    });
});

function cleanForm(idForm){
    $('form[id="'+idForm+'"]').find('input[type="radio"]').prop('checked','');
    $('form[id="'+idForm+'"]').find('input[type!="radio"]').val('');
    $('form[id="'+idForm+'"]').find('select').val('');
}

function listRoles(){
    $.ajax({
        type: 'POST',
        url: 'Controller',
        dataType: 'JSON',
        data:{
            action: 'listRoles'
        },
        success: function(data, textStatus, jqXHR){
            $.each(data, function(ix, el){
                $('select#rol').append('<option value="'+el.idRol+'">'+el.nombre+'</option>');
            });
        },
        error: function (jqXHR, textStatus, errorThrown){
            $('select#rol').append('<option>---</option>');
        }
    });
}

function saveUser(){
    if (validateUserInfo()) {
        $.ajax({
            type: 'POST',
            url: 'Controller',
            data: $('form#crud').serialize() + '&action=saveUser',
            success: function(data, textStatus, jqXHR){
                messenger('success', 'Usuario creado de manera exitosa');
                cleanForm('crud');
            },
            error: function (jqXHR, textStatus, errorThrown){
                if (errorThrown) {
                    messenger('error', errorThrown);
                } else {
                    messenger('error', 'El usuario no pudo ser creado');
                }
            }
        });
    } else {
        messenger('error', 'Debe diligenciar los campos Nombre, Rol, y Activo');
    }
}

function modifyUser(){
    if (validateUserInfo()) {
        $.ajax({
            type: 'POST',
            url: 'Controller',
            data: $('form#crud').serialize() + '&action=modifyUser',
            success: function(data, textStatus, jqXHR){
                messenger('success', 'Usuario modificado de manera exitosa');
                cleanForm('crud');
                $('form#search .btn-primary').click();
                $('form#crud .btn-danger,.btn-warning').attr('disabled', 'disabled');
                $('form#crud .btn-success').removeAttr('disabled');
            },
            error: function (jqXHR, textStatus, errorThrown){
                if (errorThrown) {
                    messenger('error', errorThrown);
                } else {
                    messenger('error', 'El usuario no pudo ser modificado');
                }
            }
        });
    } else {
        messenger('error', 'Debe diligenciar los campos Nombre, Rol, y Activo');
    }
}

function deleteUser(){
    var name = $('form#crud input[name="name"]').val();
    var msg = name ? '¿Desea eliminar el usuario "' + name + '"?' : '¿Desea eliminar el usuario?'
    if (confirm(msg)) {
        $.ajax({
            type: 'POST',
            url: 'Controller',
            data: $('form#crud').serialize() + '&action=deleteUser',
            success: function(data, textStatus, jqXHR){
                messenger('success', 'Se eliminó al usuario');
                cleanForm('crud');
                $('form#search .btn-primary').click();
                $('form#crud .btn-danger,.btn-warning').attr('disabled', 'disabled');
                $('form#crud .btn-success').removeAttr('disabled');
            },
            error: function (jqXHR, textStatus, errorThrown){
                if (errorThrown) {
                    messenger('error', errorThrown);
                } else {
                    messenger('error', 'No se pudo eliminar al usuario');
                }
            }
        });
    }
}

function listUsers(){
    $.ajax({
        type: 'POST',
        url: 'Controller',
        data: $('form#search').serialize() + '&action=listUsers',
        success: function(data, textStatus, jqXHR){
            json = JSON.parse(data);
            if (!json.length) {
                $('.alert-secondary').show();
                $('#usersList .table-info').hide();
            } else {
                $('.alert-secondary').hide();
                $('#usersList .table-info').show().find('tbody').html('');
                $.each(json, function(ix, el){
                    activo = el.activo == 'S' ? 'Si' : 'No';
                    $('#usersList .table-info').find('tbody').append('<tr onclick="viewUser('+el.idUsuario+')" id="'+el.idUsuario+'"><td>'+el.idUsuario+'<input type="hidden" name="idUser" value="'+el.idUsuario+'"><input type="hidden" name="idRol" value="'+el.idRol+'"><input type="hidden" name="nombre" value="'+el.nombre+'"><input type="hidden" name="activo" value="'+activo+'"></td><td>'+el.nombre+'</td><td>'+el.rol+'</td><td>'+activo+'</td></tr>');
                });
            }
        }
    });
}

function viewUser(id){
    userId = $('#usersList tr#'+id).find('[name="idUser"]').val();
    rolId = $('#usersList tr#'+id).find('[name="idRol"]').val();
    nombre = $('#usersList tr#'+id).find('[name="nombre"]').val();
    activo = $('#usersList tr#'+id).find('[name="activo"]').val();
    $('form#crud [name="iduser"]').val(userId);
    $('form#crud [name="name"]').val(nombre);
    $('form#crud [name="rol"]').val(rolId);
    $('form#crud [name="active"][value="'+activo+'"]').prop('checked', true);
    $('form#crud .btn-danger,.btn-warning').removeAttr('disabled');
    $('form#crud .btn-success').attr('disabled', true);
    $('form#crud').closest('.m-panel').show();
    $('form#crud [name="name"]').focus();
}

function validateUserInfo(){
    var valid = true;
    if (!$('form#crud #name').val()) {
        valid = false;
    }
    if (!$('form#crud #rol').val()) {
        valid = false;
    }
    if (!$('form#crud #active:checked').length) {
        valid = false;
    }
    return valid;
}

function messenger (type, msg) {
    switch (type) {
        case "error":
            $('.alert-danger').fadeIn().find('.alert-content').text(msg);
            break;
        case "success":
            $('.alert-success').fadeIn().find('.alert-content').text(msg);
            break;
        default:
            break;
    }
}