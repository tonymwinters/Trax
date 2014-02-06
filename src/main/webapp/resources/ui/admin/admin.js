/**
 * ADMIN TABLE PAGE
 * All functionality for Admin Tables - Maza Who?
 */

document.observe("dom:loaded", function() {
});


function initTable(type, title, editaction){
    var userTableDatasource = new Trax.Widget.Datasource();
    userTableDatasource.fetchData = function(){
        var data = {};
        data[type+"s"] = Trax.getResource("resources/"+type+"/list");
        return data;
    };

    var table = new Trax.Widget.DataTable('/resources/ui/templates/admin/'+type+'/table.ejs', userTableDatasource);

    var deleteButton = new Trax.Widget.Button();
    deleteButton.text = "Delete";
    deleteButton.classes = ["delete"];
    deleteButton.action = function (){
        Trax.getResource("resources/"+type+"/delete/" + this.id);
        table.datasource.refreshData();
        table.refreshTable();
    };

    var editButton = new Trax.Widget.Button();
    editButton.text = "Edit";
    editButton.classes = ["edit"];
    editButton.action = function(){
        var modal = initModal("user", "Edit User", "editUser", function(){
            var data = {};
            data.user = Trax.getResource("/resources/user/"+self.id);
            data.availableRoles =  new Trax.Model.Role().getRoles();
            return data;
        });

        var user = modal.datasource.getData().user;
        if(user.roles != null){
            user.roles.each(function(role){
                $$('.role').each(function(element){
                    if(element.value == role.id){
                        element.checked = true;
                    }
                });
            });
        }
        modal.show();
    };

    table.initRowActions([editButton,deleteButton]);

    var refreshButton = new Trax.Widget.Button();
    refreshButton.text = "Refresh Table";
    refreshButton.classes = ["refresh"];
    refreshButton.action = function (){
        table.datasource.refreshData();
        table.refreshTable();
    };

    var widgetWrapper = new Trax.Widget.Wrapper(title,'main-admin-table', [table.getElement(), refreshButton.getElement()]);

    return table;
}

function initModal(type, title, formId, fetchData){
    var self = this;
    var modalDatasource = new Trax.Widget.Datasource();
    if(fetchData){
        modalDatasource.fetchData = fetchData;
    }else{
        modalDatasource.fetchData = function(){
            var data = {};
            data[type] = Trax.getResource("/resources/"+type+"/"+self.id);
            return data;
        };
    }

    var modal = new Trax.Widget.Modal(title,'/resources/ui/templates/modal/'+type+'/edit.ejs', modalDatasource);

    var saveButton = new Trax.Widget.Button();
    saveButton.text = "Save";
    saveButton.classes = ["save"];
    saveButton.action = function (){
        var response = Trax.postResource('/resources/'+type+'/save', Trax.formToObject(formId));
        if(response.success){
            table.datasource.refreshData();
            table.refreshTable();
            modal.hide();
        }
    };

    modal.initButtons([saveButton]);

    return modal;
}

$("usersTab").observe("click", function(){
    removeActive();
    this.addClassName("active");
    var type = 'user';
    var userTableDatasource = new Trax.Widget.Datasource();
    userTableDatasource.fetchData = function(){
        var data = {};
        data.users = Trax.getResource("resources/"+type+"/list");
        return data;
    };

    var table = new Trax.Widget.DataTable('/resources/ui/templates/admin/'+type+'/table.ejs', userTableDatasource);

    var deleteButton = new Trax.Widget.Button();
    deleteButton.text = "Delete";
    deleteButton.classes = ["delete"];
    deleteButton.action = function (){
        Trax.getResource("resources/"+type+"/delete/" + this.id);
        table.datasource.refreshData();
        table.refreshTable();
    };

    var editButton = new Trax.Widget.Button();
    editButton.text = "Edit";
    editButton.classes = ["edit"];
    editButton.action = function(){
        var self = this;
        var modalDatasource = new Trax.Widget.Datasource();
        modalDatasource.fetchData = function(){
            var data = {};
            data.user = Trax.getResource("/resources/user/"+self.id);
            data.availableRoles =  new Trax.Model.Role().getRoles();
            return data;
        };

        var modal = new Trax.Widget.Modal(title,'/resources/ui/templates/modal/'+type+'/edit.ejs', modalDatasource);

        var saveButton = new Trax.Widget.Button();
        saveButton.text = "Save";
        saveButton.classes = ["save"];
        saveButton.action = function (){
            var response = Trax.postResource('/resources/'+type+'/save', Trax.formToObject(formId));
            if(response.success){
                table.datasource.refreshData();
                table.refreshTable();
                modal.hide();
            }
        };

        modal.initButtons([saveButton]);

        var user = modal.datasource.getData().user;
        if(user.roles != null){
            user.roles.each(function(role){
                $$('.role').each(function(element){
                    if(element.value == role.id){
                        element.checked = true;
                    }
                });
            });
        }
        modal.show();
    };

    table.initRowActions([editButton,deleteButton]);

    var refreshButton = new Trax.Widget.Button();
    refreshButton.text = "Refresh Table";
    refreshButton.classes = ["refresh"];
    refreshButton.action = function (){
        table.datasource.refreshData();
        table.refreshTable();
    };

    var widgetWrapper = new Trax.Widget.Wrapper("Users",'main-admin-table', [table.getElement(), refreshButton.getElement()]);
});

$('venuesTab').observe("click", function(){
    removeActive();
    this.addClassName("active");
    var type = 'venue';
    var userTableDatasource = new Trax.Widget.Datasource();
    userTableDatasource.fetchData = function(){
        var data = {};
        data.venues = Trax.getResource("resources/"+type+"/list");
        return data;
    };

    var table = new Trax.Widget.DataTable('/resources/ui/templates/admin/'+type+'/table.ejs', userTableDatasource);

    var deleteButton = new Trax.Widget.Button();
    deleteButton.text = "Delete";
    deleteButton.classes = ["delete"];
    deleteButton.action = function (){
        Trax.getResource("resources/"+type+"/delete/" + this.id);
        table.datasource.refreshData();
        table.refreshTable();
    };

    var editButton = new Trax.Widget.Button();
    editButton.text = "Edit";
    editButton.classes = ["edit"];
    editButton.action = function(){
        var self = this;
        var modalDatasource = new Trax.Widget.Datasource();
        modalDatasource.fetchData = function(){
            var data = {};
            data.venue = Trax.getResource("/resources/user/"+self.id);
            return data;
        };

        var modal = new Trax.Widget.Modal(title,'/resources/ui/templates/modal/'+type+'/edit.ejs', modalDatasource);

        var saveButton = new Trax.Widget.Button();
        saveButton.text = "Save";
        saveButton.classes = ["save"];
        saveButton.action = function (){
            var response = Trax.postResource('/resources/'+type+'/save', Trax.formToObject(formId));
            if(response.success){
                table.datasource.refreshData();
                table.refreshTable();
                modal.hide();
            }
        };

        modal.initButtons([saveButton]);

        modal.show();
    };

    table.initRowActions([editButton,deleteButton]);

    var refreshButton = new Trax.Widget.Button();
    refreshButton.text = "Refresh Table";
    refreshButton.classes = ["refresh"];
    refreshButton.action = function (){
        table.datasource.refreshData();
        table.refreshTable();
    };

    var widgetWrapper = new Trax.Widget.Wrapper("Venues",'main-admin-table', [table.getElement(), refreshButton.getElement()]);
});

$('rolesTab').observe("click", function(){
    removeActive();
    this.addClassName("active");
    var type = 'role';
    var userTableDatasource = new Trax.Widget.Datasource();
    userTableDatasource.fetchData = function(){
        var data = {};
        data.roles = Trax.getResource("resources/"+type+"/list");
        return data;
    };

    var table = new Trax.Widget.DataTable('/resources/ui/templates/admin/'+type+'/table.ejs', userTableDatasource);

    var deleteButton = new Trax.Widget.Button();
    deleteButton.text = "Delete";
    deleteButton.classes = ["delete"];
    deleteButton.action = function (){
        Trax.getResource("resources/"+type+"/delete/" + this.id);
        table.datasource.refreshData();
        table.refreshTable();
    };

    var editButton = new Trax.Widget.Button();
    editButton.text = "Edit";
    editButton.classes = ["edit"];
    editButton.action = function(){
        var self = this;
        var modalDatasource = new Trax.Widget.Datasource();
        modalDatasource.fetchData = function(){
            var data = {};
            data.role = Trax.getResource("/resources/user/"+self.id);
            return data;
        };

        var modal = new Trax.Widget.Modal(title,'/resources/ui/templates/modal/'+type+'/edit.ejs', modalDatasource);

        var saveButton = new Trax.Widget.Button();
        saveButton.text = "Save";
        saveButton.classes = ["save"];
        saveButton.action = function (){
            var response = Trax.postResource('/resources/'+type+'/save', Trax.formToObject(formId));
            if(response.success){
                table.datasource.refreshData();
                table.refreshTable();
                modal.hide();
            }
        };

        modal.initButtons([saveButton]);

        modal.show();
    };

    table.initRowActions([editButton,deleteButton]);

    var refreshButton = new Trax.Widget.Button();
    refreshButton.text = "Refresh Table";
    refreshButton.classes = ["refresh"];
    refreshButton.action = function (){
        table.datasource.refreshData();
        table.refreshTable();
    };

    var widgetWrapper = new Trax.Widget.Wrapper("Venues",'main-admin-table', [table.getElement(), refreshButton.getElement()]);
});

function removeActive(){
    $$('li.model').each(function(el){
        el.removeClassName("active");
    });
}
