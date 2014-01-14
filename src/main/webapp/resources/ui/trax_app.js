// Default NameSpace
Trax = {};

/*********************************************
 * HELPER FUNCTIONS
 *********************************************/
Trax.printTheWordSwag = function(){
    console.log("swag");
};

Trax.ajax = function(url, method, contentType , postBody, params){
    var self = this;
    var response;
    new Ajax.Request(url, {
        asynchronous: false,
        contentType: contentType,
        postBody: postBody,
        method: method,
        parameters: params,
        onSuccess: function(transport) {
            self.response = transport.responseText || "no response text";
        }
    });

     return self.response;
};

Trax.getResource = function (url) {
    var response = {};
    if (url) {
        response = JSON.parse(Trax.ajax(url, 'GET'));
    }
    if (response.success) {
        return response.object;
    }
    alert(response.message.toString());
    return {};
};

Trax.postResource = function(url, obj){
    var response = {};
    if (obj) {
        response = JSON.parse(Trax.ajax(url, 'POST', 'application/json', JSON.stringify(obj)));
    }
    if (response.success) {
        return response;
    }
    alert(response.message.toString());
    return {};
};

function toTitleCase(str){
    return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}

function arrayContains(array, str){
    return array.indexOf(str) > -1;
}

function setAttr(obj, keys, element){

    var attr = keys.pop();

    if(keys.size() > 0){
        if(obj[attr] == null){
            obj[attr] = {};
        }
        setAttr(obj[attr], keys, element);
    }else{
        //check for an array
        if(attr.substring(attr.length - 2, attr.length) == "[]"){
            attr = attr.substring(0,attr.length - 2);

            if(obj[attr] == null){
                obj[attr] = [];
            }
            var newItem = {};
            newItem[element.id] = element.value;
            obj[attr].push(newItem);
        }else{
            var value = element.value ;
            obj[attr] = (value == "" ? null : value);
        }
    }
}

Trax.formToObject = function(formId) {
    var object = $(formId);
    var result = {};
    object.getElements().each(function (element){
        if(element.checked || element.type != "checkbox"){
            setAttr(result, element.name.split(".").reverse(), element);
        }
    });
    return result;
};


/*********************************************
 * WIDGETS
 *********************************************/
Trax.Widget = {};
/*********************************************
 * BUTTON WIDGET
 *********************************************/
Trax.Widget.Button = Class.create({

    initialize: function(options){
        this.options = options;
        var button = new Element('a');
        button.href = "#";
        button.update(this.options.text);
        jQuery(button).addClass(this.options.class);
        jQuery(button).addClass("button");
        button.stopObserving("click");
        button.observe("click", this.options.action);
        this.button = button;
    },

    getElement: function(){
        return this.button;
    }

});
/*********************************************
 * DATA TABLE WIDGET
 *********************************************/
Trax.Widget.DataTable = Class.create({

    initialize: function(options){
        this.options = options;
        this.initContainer();
        this.initTitle();
        this.initTableControls();
        this.initTable();
        this.refreshData();
        this.refreshTableData();

        jQuery(this.tableConatiner).addClass("table_wrapper");
    },

    getData: function(){
        return {};
    },

    initContainer: function(){
        var container = $(this.options.containerId);
        container.innerHTML = "";
        this.tableConatiner = container
    },

    initTitle: function(){
        if(this.options.title != null){
            var title = new Element('h1');
            jQuery(title).addClass("table_title_h1");
            title.innerHTML = this.options.title;
            this.tableConatiner.insert(title);
            this.tableTitle = title;
        }
    },

    initTableControls: function(){
        if(this.options.create){
            this.initCreate();
        }
        if(this.options.search){
            this.initSearch();
        }
    },

    initTable: function(){
        var table = new Element('table');
        jQuery(table).addClass("table");
        jQuery(table).addClass("trax_table");
        jQuery(table).addClass("table-hover");
        this.tableConatiner.insert(table);
        this.table = table;
    },

    refreshTableData: function(){
        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/'+this.options.dataType.toString().toLowerCase()+'/table.ejs'}).update(this.table, this.data);

        var actions = this.options.actions;
        if(actions != null && actions.length > 0){
            this.initActions();
        }
    },

    refreshData: function(){
        this.data = this.getData();
        this.refreshTableData();
    },

    initSearch: function(){
        var search = new Element('input');
        search.type = "text";
        jQuery(search).addClass("table-search")
        search.setAttribute("placeholder", "Search " + toTitleCase(this.options.dataType.toString()) + "s");
        this.tableConatiner.insert(search);
        this.searchBox = search;
    },

    initCreate: function(){
        var self = this;
        var options = {};
        options.class = "add";
        options.text = "New " + toTitleCase(self.options.dataType.toString());
        options.action = function(){
            self.create();
        };
        var button = new Trax.Widget.Button(options);


        this.tableConatiner.insert(button.getElement());
        this.addButton = button;
    },

    create: function(){

    },

    initActions: function(){
        var actionColumnHeader = new Element('th');
        actionColumnHeader.innerHTML = "Actions";
        this.table.down('thead').down('tr').insert(actionColumnHeader);
        var rows = this.table.down('tbody').rows;
        for(var i = 0; i < rows.length; i++){
            var row = rows[i];
            var actionColumn = new Element('td');
            actionColumn.id = row.id;
            jQuery(actionColumn).addClass("action");
            row.insert(actionColumn);
        }
        if(arrayContains(this.options.actions, "edit")){
            this.initEdit();
        }
        if(arrayContains(this.options.actions, "delete")){
            this.initDelete();
        }
    },

    initEdit: function(){
        var self = this;
        var buttons = [];
        this.table.select('td.action').each(function(container){
            var id = container.id;
            var options = {};
            options.class = "edit";
            options.text = "Edit";
            options.action = function(){
                self.edit(id);
            };
            var button = new Trax.Widget.Button(options);
            container.insert(button.getElement());
            buttons[id] = button;
        });
        this.editButtons = buttons;
    },

    edit: function(id){
        console.log("default " + id);
    },

    initDelete: function(){
        var self = this;
        var buttons = [];
        this.table.select('td.action').each(function(container){
            var id = container.id;
            var options = {};
            options.class = "delete";
            options.text = "Delete";
            options.action = function(){
                self.delete(id);
            };
            var button = new Trax.Widget.Button(options);
            container.insert(button.getElement());
            buttons[id] = button;
        });
        this.deleteButtons = buttons;
    },

    delete: function(id){
        Trax.getResource("resources/" + this.options.dataType.toString() + "/delete/" + id);
        this.refreshData();
        this.refreshTableData();
    }
});
/*********************************************
 * MODAL WIDGET
 *********************************************/
Trax.Widget.Modal = Class.create({
    initialize: function(options, parent){
        this.options = options;
        this.parent = parent;
        this.createModal();
        this.populateModal();
        this.afterLoad();
        this.initActions();
    },

    getData: function(){
    },

    createModal: function(){
        var type = this.options.dataType;
        var id = "modal-"+type;
        var container = $(this.options.containerId);

        if(!$(id) && type != null){
            var modal = jQuery('<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>')[0];
            modal.id = "modal-"+type;

            if(container != null){
                container.insert(modal)
            }else{
                $(document.body).insert(modal)
            }
        }
        this.modalElement = $(id);
    },

    populateModal: function(){
        var self = this;

        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/user/edit.ejs'}).update(self.modalElement, self.getData());

    },

    afterLoad: function(){
    },

    initActions: function(){
        if(arrayContains(this.options.actions, "save")){
            this.initSave();
        }
    },

    initSave: function(){
        var self = this;
        $$('.modal .save').each(function(button){
            button.observe("click", function(){
                var response = self.save();
                if(response.success){
                    self.hide();
                    self.parent.refreshData();
                }
            });
        });
    },

    save: function(){
    },

    getElement: function(){
        return this.modalElement;
    },

    show: function(){
        jQuery(this.getElement()).modal("show");
    },

    hide: function(){
        jQuery(this.getElement()).modal("hide");
    }
});


/*********************************************
 * MODELS
 *********************************************/
Trax.Model = {};
/*********************************************
 * USER MODEL
 *********************************************/
Trax.Model.User = {};
Trax.Model.User.Table = Class.create(Trax.Widget.DataTable, {

    initialize: function($super, options){
        options.dataType = "user";
        $super(options);
    },

    getData: function(){
        var data = {};
        data.users = Trax.getResource("resources/user/list");;
        return data;
    },

    edit: function($super, id){
        var options = {};
        options.userId = id;
        options.containerId = "main-admin-table";
        options.title = "User Edit";
        options.actions = ["save"];
        var modal = new Trax.Model.User.Edit(options, this);
        modal.show();
    }

});
Trax.Model.User.Edit = Class.create(Trax.Widget.Modal, {

    initialize: function($super, options, parent){
        options.dataType = "user";
        $super(options, parent);
    },

    getData: function(){
        var availableRoles = new Trax.Model.Role().getRoles();
        var data = {};
        data.type = this.options.dataType;
        var id = this.options.userId;
        if(id){
            data.user = Trax.getResource(contextPath + "/resources/user/"+id);
        }else{
            data.user = Trax.getResource(contextPath + "/resources/user/object");
        }
        data.availableRoles = availableRoles;
        this.user = data.user;
        return data;
    },

    afterLoad: function(){
        var user = this.user;
        if(user.roles != null){
            user.roles.each(function(role){
                $$('.role').each(function(element){
                    if(element.value == role.id){
                        element.checked = true;
                    }
                });
            });
        }
    },

    save: function(){
        return Trax.postResource('/resources/'+this.options.dataType+'/save', Trax.formToObject('edit'+this.options.dataType));
    }

});


/*********************************************
 * ROLE MODEL
 *********************************************/
Trax.Model.Role = Class.create({
    getRoles: function(){
        return Trax.getResource(contextPath + "/resources/role/list");
    }
});
Trax.Model.Role.Table = Class.create(Trax.Widget.DataTable, {

    initialize: function($super, options){
        options.dataType = "role";
        $super(options);
    },

    getData: function(){
        var data = {};
        data.roles = Trax.getResource("resources/role/list");;
        return data;
    },

    edit: function(id){
        new Trax.Model.Role.Edit(id);
        this.refreshData();
    }

});
Trax.Model.Role.Edit = Class.create({


    initialize: function(id){
        var role = {};

        if(id){
            role = Trax.getResource(contextPath + "/resources/role/"+id);
        }else{
            role = Trax.getResource(contextPath + "/resources/role/object");
        }
        this.populateModal(role);

    },

    populateModal: function(role){
        var self = this;
        var modalElement = new Trax.Widget.Modal().getModal("role");
        var availablePermissions = new Trax.Model.Permission().getPermissions();
        var data = {};
        data.type = "Role";
        data.role = role;
        data.availablePermissions = availablePermissions;

        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/role/edit.ejs'}).update(modalElement, data);
        if(role.permissions != null){
            role.permissions.each(function(permission){
                $$('.permission').each(function(element){
                    if(element.value == permission.id){
                        element.checked = true;
                    }
                });
            });
        }

        $$('.modal .role.save').each(function(button){
            button.observe("click", function(){
                var response = self.save();
                if(response.success){
                    jQuery(modalElement).modal("hide");
                    new Trax.Model.Role.Table();
                }
            });
        });

        jQuery(modalElement).modal("show");
    },

    save: function(){
        return Trax.postResource('/resources/role/save', Trax.formToObject('editRole'));
    }

});


/*********************************************
 * Permission MODEL
 *********************************************/
Trax.Model.Permission = Class.create({
    getPermissions: function(){
        return Trax.getResource(contextPath + "/resources/permission/list").object;
    }
});


/*********************************************
 * VENUE MODEL
 *********************************************/
Trax.Model.Venue = {};
Trax.Model.Venue.Table = Class.create(Trax.Widget.DataTable, {

    initialize: function($super, options){
        options.dataType = "venue";
        $super(options);
    },

    getData: function(){
        var data = {};
        data.venues = Trax.getResource("resources/venue/list");
        return data;
    },

    edit: function(id){
        new Trax.Model.Venue.Edit(id);
        this.refreshData();
    }

});
Trax.Model.Venue.Edit = Class.create({


    initialize: function(id){
        var venue = {};

        if(id){
            venue = Trax.getResource(contextPath + "/resources/venue/"+id);
        }else{
            venue = Trax.getResource(contextPath + "/resources/venue/object");
        }
        this.populateModal(venue);
    },

    populateModal: function(venue){
        var self = this;
        var modalElement = new Trax.Widget.Modal().getModal("venue");
        var data = {};
        data.type = "Venue";
        data.venue = venue;
        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/venue/edit.ejs'}).update(modalElement, data);


        var options = {};
        options.containerId = "rooms-table";
        options.venueId = venue.id;
        options.title = "Rooms";
        options.create = true;
        options.search = false;
        options.actions = ["edit", "delete"];
        new Trax.Model.Room.Table(options);

        $$('.modal .venue.save').each(function(button){
            button.observe("click", function(){
                var response = self.save();
                if(response.success){
                    jQuery(modalElement).modal("hide");
                    new Trax.Model.Venue.Table();
                }
            });
        });

        $$('.modal .room.edit').each(function(button){
            button.observe("click", function(){
                new Trax.Model.Room.Edit(this.readAttribute('id'), venue);
            });
        });

        $$('.modal .room.add').each(function(button){
            button.observe("click", function(){
                new Trax.Model.Room.Edit(null, venue);
            });
        });

        jQuery(modalElement).modal("show");
    },

    save: function(){
        var obj = Trax.formToObject('editVenue');
        return Trax.postResource('/resources/venue/save', obj);

    }

});


/*********************************************
 * ROOM MODEL
 *********************************************/
Trax.Model.Room = {};
Trax.Model.Room.Table = Class.create(Trax.Widget.DataTable, {

    initialize: function($super, options){
        options.dataType = "room";
        $super(options);
    },

    getData: function(){
        var data = {};
        if(this.options.venueId != null){
            var venue = Trax.getResource("resources/venue/" + this.options.venueId);
            data.rooms = venue.rooms;
        }else{
            data.rooms = Trax.getResource("resources/room/list");
        }
        return data;
    },

    edit: function(id){
        new Trax.Model.Room.Edit(id);
    },

    create: function(){
        new Trax.Model.Room.Add(this.options.venueId, this);
    }

});
Trax.Model.Room.Edit = Class.create({


    initialize: function(id, venue){
        this.venue = venue;
        var room = {};
        var self = this;
        var modalElement = new Trax.Widget.Modal().getModal("room");

        if(id){
            room = Trax.getResource(contextPath + "/resources/room/"+id);
        }else{
            room = Trax.getResource(contextPath + "/resources/room/object");
        }

        this.populateModal(modalElement, room);
        if(room.id){
            this.editModal(venue, modalElement);
        }else{
            this.addModal(modalElement)
        }

        $$('.modal .room.delete').each(function(button){
            button.update("Delete");
            button.stopObserving("click");
            button.observe("click", function(){
                var result = self.delete(this.readAttribute('id'));
                if(result.success){
                    jQuery(modalElement).modal("hide");
                    new Trax.Model.Venue.Edit(venue.id)
                }
            });
        });

        jQuery(modalElement).modal("show");
    },

    addModal: function(modal){
        var self = this;
        $$('.modal .room.submit').each(function(button){
            button.update("Add");
            button.stopObserving("click");
            button.observe("click", function(){
                var room = Trax.formToObject('editRoom');
                var response = self.add(room);
                if(response.success){
                    jQuery(modal).modal("hide");
                    var venue = response.object;
                    new Trax.Model.Venue.Edit(venue.id);
                }
            });
        });
    },

    editModal: function(venue, modal){
        var self = this;
        $$('.modal .room.submit').each(function(button){
            button.update("Save");
            button.stopObserving("click");
            button.observe("click", function(){
                var room = Trax.formToObject('editRoom');
                var response = self.save(room);
                if(response.success){
                    jQuery(modal).modal("hide");
                    new Trax.Model.Venue.Edit(venue.id)
                }
            });
        });
    },

    populateModal: function(modal, room){
        var data = {};
        data.type = "Room";
        data.room = room;
        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/room/edit.ejs'}).update(modal, data);
    },

    delete: function(id){
        return Trax.getResource("resources/room/delete/" + id);
    },

    save: function(room){
        return Trax.postResource('/resources/room/save', room);
    },

    add: function(room){
        var rooms = [room];
        var postObject = {};
        postObject.id = this.venue.id;
        postObject.rooms = rooms;
        return Trax.postResource('/resources/venue/save', postObject);
    }

});
Trax.Model.Room.Add = Class.create({


    initialize: function(venueId, parent){
        this.venueId = venueId;
        this.parentTable = parent;
        var room = {};
        var self = this;
        var modalElement = new Trax.Widget.Modal().getModal("room");
        room = Trax.getResource(contextPath + "/resources/room/object");

        this.populateModal(modalElement, room);
        this.addModal(modalElement)

        $$('.modal .room.delete').each(function(button){
            button.update("Delete");
            button.stopObserving("click");
            button.observe("click", function(){
                var result = self.delete(this.readAttribute('id'));
                if(result.success){
                    jQuery(modalElement).modal("hide");
                    new Trax.Model.Venue.Edit(venue.id)
                }
            });
        });

        jQuery(modalElement).modal("show");
    },

    addModal: function(modal){
        var self = this;
        $$('.modal .room.submit').each(function(button){
            button.update("Add");
            button.stopObserving("click");
            button.observe("click", function(){
                var room = Trax.formToObject('editRoom');
                var response = self.add(room);
                if(response.success){
                    jQuery(modal).modal("hide");
                    var venue = response.object;
                    self.parentTable.refreshData();
                }
            });
        });
    },

    populateModal: function(modal, room){
        var data = {};
        data.type = "Room";
        data.room = room;
        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/room/edit.ejs'}).update(modal, data);
    },

    add: function(room){
        var rooms = [room];
        var postObject = {};
        postObject.id = this.venueId;
        postObject.rooms = rooms;
        return Trax.postResource('/resources/venue/save', postObject);
    }

});


/*********************************************
 * SESSION MODEL
 *********************************************/
Trax.Model.Session = {};
Trax.Model.Session.Page = Class.create({

    initialize: function(venueId){
        EJS.config({cache: false});
        var self = this;
        self.venueId = venueId;
        var venue = Trax.getResource(contextPath + "/resources/venue/"+venueId);
        this.populateSessionList(venue);
        this.populateInitialSession(venue);

        // New Session Click Listener
        $$('div.new_session')[0].observe("click", function(){
            self.createNewSessionTemplate(venue);
        });

        jQuery('.edit').editable(contextPath + '/resources/session/save', {
            indicator : 'Saving...',
            tooltip   : 'Click to edit...'
        });

        this.refreshListeners();

    },

    refreshListeners: function(){
        var self = this;
        // Click Listeners for All Session Divs
        $$('.single_session_container').each(function(element){
            element.observe("click", function(){
                var id = element.readAttribute('id');
                self.removeActive();
                element.addClassName('active_session');
                self.populateSessionDetail(id);
            });
        });

    },

    createNewSessionTemplate: function(venue){
        var self = this;
        var postObject = {};
        var newSession = {};
        var newSessions = [];
        newSession.name = "Untitled";
        newSession.startTime = new XDate().toString("yyyy-MM-dd'T'HH:mm:sszz");
        newSession.endTime = new XDate().toString("yyyy-MM-dd'T'HH:mm:sszz");
        newSession.description = "Enter description here."
        newSessions.push(newSession);
        postObject.id = venue.id;
        postObject.sessions = newSessions;
        var response = Trax.postResource('/resources/venue/save', postObject);
        newSession.id = response.object.sessions[0].id;

        venue.sessions.unshift(newSession);

        this.populateSessionList(venue);
        this.refreshListeners();
        $$('.single_session_container')[0].click();

    },

    // Remove Active Class From all Sessions
    removeActive: function(){
        $$('.single_session_container').each(function(element){
            element.removeClassName('active_session');
        });
    },

    populateSessionList: function(venue){
        var target = $('all_sessions_container');
        new EJS({url: contextPath + '/resources/ui/templates/session/session-item.ejs'}).update(target, venue);
    },

    populateInitialSession: function(venue){
        var session = venue.sessions[0];
        $$('.single_session_container')[0].addClassName('active_session');
        var target = $$('div.session_info_container')[0];
        var header = $$('div.session_page_header')[0];
        new EJS({url: contextPath + '/resources/ui/templates/session/session-header.ejs'}).update(header, venue);
        new EJS({url: contextPath + '/resources/ui/templates/session/session-detail.ejs'}).update(target, session);

    },

    populateSessionDetail: function(sessionId){
        var target = $$('div.session_info_container')[0];

        if(sessionId != ""){
            var session = Trax.getResource(contextPath + "/resources/session/" + sessionId);
            new EJS({url: contextPath + '/resources/ui/templates/session/session-detail.ejs'}).update(target, session);
        }


    }
});