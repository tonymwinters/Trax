/**
 * ADMIN TABLE PAGE
 * All functionality for Admin Tables - Maza Who?
 */

document.observe("dom:loaded", function() {
    initUserTable();
});


function initUserTable(){
    var userTableDatasource = new Trax.Widget.Datasource();
    userTableDatasource.fetchData = function(){
        var data = {};
        data.users = Trax.getResource("resources/user/list");
        return data;
    };

    var table = new Trax.Widget.DataTable('/resources/ui/templates/admin/user/table.ejs', userTableDatasource);

    var deleteButton = new Trax.Widget.Button();
    deleteButton.text = "Delete";
    deleteButton.classes = ["delete"];
    deleteButton.action = function (){
        Trax.getResource("resources/user/delete/" + this.id);
        table.datasource.refreshData();
        table.refreshTable();
    };

    var rowClick = new Trax.Widget.Trigger();
    rowClick.event = 'click';
    rowClick.action = function(){
        var self = this;
        var userModalDatasource = new Trax.Widget.Datasource();
        userModalDatasource.fetchData = function(){
            var availableRoles = new Trax.Model.Role().getRoles();
            var data = {};
            data.user = Trax.getResource(contextPath + "/resources/user/"+self.id);
            data.availableRoles = availableRoles;
            return data;
        };

        var modal = new Trax.Widget.Modal("User Edit",'/resources/ui/templates/modal/user/edit.ejs', userModalDatasource);

        var saveButton = new Trax.Widget.Button();
        saveButton.text = "Save";
        saveButton.classes = ["save"];
        saveButton.action = function (){
            var response = Trax.postResource('/resources/user/save', Trax.formToObject('editUser'));
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

    table.initRowActions([deleteButton]);
    table.initRowTriggers([rowClick]);

    var refreshButton = new Trax.Widget.Button();
    refreshButton.text = "Refresh Table";
    refreshButton.classes = ["refresh"];
    refreshButton.action = function (){
        table.datasource.refreshData();
        table.refreshTable();
    };

    var widgetWrapper = new Trax.Widget.Wrapper('Users','main-admin-table', [table.getElement(), refreshButton.getElement()]);
}

$("usersTab").observe("click", function(){
    removeActive();
    this.addClassName("active");
    initUserTable();
});

$('venuesTab').observe("click", function(){
    removeActive();
    this.addClassName("active");
    var options = {};
    options.containerId = "main-admin-table";
    options.title = "Venues";
    options.create = true;
    options.search = true;
    options.actions = ["edit", "delete"];
    new Trax.Model.Venue.Table(options);
});

$('rolesTab').observe("click", function(){
    removeActive();
    this.addClassName("active");
    var options = {};
    options.containerId = "main-admin-table";
    options.title = "Roles";
    options.create = true;
    options.search = true;
    options.actions = ["edit", "delete"];
    new Trax.Model.Role.Table(options);
});

function removeActive(){
    $$('li.model').each(function(el){
        el.removeClassName("active");
    });
}
