// Default NameSpace
Trax = {};
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
        return response;
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

Trax.Modal = Class.create({
    initialize: function(){

    },

    getModal: function(type, containerId){
        this.id = "modal-"+type;
        if(!$(this.id) && type != null){
            if(containerId != null){
                $(containerId).insert("<div id='"+this.id+"' class='trax_modal' style='display: none'></div>")
            }else{
                $(document.body).insert("<div id='"+this.id+"' class='trax_modal' style='display: none'></div>")
            }
        }
        return $(this.id);
    },

    hideAll: function(){
      $$('.trax_modal').each(function(element){
          element.hide();
      });
    }
});

Trax.Model = {};


/*********************************************
 * USER MODEL
 *********************************************/
Trax.Model.User = {};
Trax.Model.User.Table = Class.create({


    initialize: function(){
        var self = this;
        var response = Trax.getResource("resources/user/list");
        var table = $('page-title-header').update("Users");
        $('table-title-header').update("Users");
        $('table-search').setAttribute("placeholder", "Search Users");
        this.populateTable(response.object);

        $$('.table_wrapper .add').each(function(button){
            button.update("New User");
            button.stopObserving("click");
            button.observe("click", function(){
                self.edit();
            });
        });

        $$('.trax_table .delete').each(function(button){
            button.observe("click", function(){
                self.delete(this.readAttribute('id'));
            });
        });

        $$('.trax_table .edit').each(function(button){
            button.observe("click", function(){
                self.edit(this.readAttribute('id'));
            });
        });

        new Trax.Modal().hideAll();

    },

    populateTable: function(users){
        var data = {};
        data.users = users;
        EJS.config({cache: false});
        var table = $('main-admin-table');
        new EJS({url: contextPath + '/resources/ui/templates/admin/user/table.ejs'}).update(table, data);

    },

    delete: function(id){
        return Trax.getResource("resources/user/delete/" + id);
    },

    edit: function(id){
        new Trax.Model.User.Edit(id);
    }

});

Trax.Model.User.Edit = Class.create({


    initialize: function(id){
        var response = {};

        if(id){
            response = Trax.getResource(contextPath + "/resources/user/"+id);
        }else{
            response = Trax.getResource(contextPath + "/resources/user/object");
        }
        this.populateModal(response.object);

    },

    populateModal: function(user){
        var self = this;
        var modalElement = new Trax.Modal().getModal("user", "admin-table");
        var availableRoles = new Trax.Model.Role().getRoles();
        var data = {};
        data.user = user;
        data.availableRoles = availableRoles;

        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/user/edit.ejs'}).update(modalElement, data);
        if(user.roles != null){
            user.roles.each(function(role){
                $$('.role').each(function(element){
                    if(element.value == role.id){
                        element.checked = true;
                    }
                });
            });
        }

        $$('.trax_modal .user.save').each(function(button){
            button.observe("click", function(){
                var response = self.save();
                if(response.success){
                    modalElement.hide();
                    new Trax.Model.User.Table();
                }
            });
        });

        modalElement.show();
    },

    save: function(){
        return Trax.postResource('/resources/user/save', Trax.formToObject('editUser'));
    }

});


/*********************************************
 * ROLE MODEL
 *********************************************/
Trax.Model.Role = Class.create({
    getRoles: function(){
        return Trax.getResource(contextPath + "/resources/role/list").object;
    }
});
Trax.Model.Role.Table = Class.create({


    initialize: function(){
        var self = this;
        var response = Trax.getResource("resources/role/list");
        var table = $('page-title-header').update("Roles");
        $('table-title-header').update("Roles");
        $('table-search').setAttribute("placeholder", "Search Roles");
        this.populateTable(response.object);

        $$('.table_wrapper .add').each(function(button){
            button.update("New Role");
            button.stopObserving("click");
            button.observe("click", function(){
                self.edit();
            });
        });

        $$('.trax_table .delete').each(function(button){
            button.observe("click", function(){
                self.delete(this.readAttribute('id'));
            });
        });

        $$('.trax_table .edit').each(function(button){
            button.observe("click", function(){
                self.edit(this.readAttribute('id'));
            });
        });

        new Trax.Modal().hideAll();

    },

    populateTable: function(roles){
        var data = {};
        data.roles = roles;
        EJS.config({cache: false});
        var table = $('main-admin-table');
        new EJS({url: contextPath + '/resources/ui/templates/admin/role/table.ejs'}).update(table, data);

    },

    delete: function(id){
        return Trax.getResource("resources/role/delete/" + id);
    },

    edit: function(id){
        new Trax.Model.Role.Edit(id);
    }

});

Trax.Model.Role.Edit = Class.create({


    initialize: function(id){
        var response = {};

        if(id){
            response = Trax.getResource(contextPath + "/resources/role/"+id);
        }else{
            response = Trax.getResource(contextPath + "/resources/role/object");
        }
        this.populateModal(response.object);

    },

    populateModal: function(role){
        var self = this;
        var modalElement = new Trax.Modal().getModal("role");
        var availablePermissions = new Trax.Model.Permission().getPermissions();
        var data = {};
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

        $$('.trax_modal .role.save').each(function(button){
            button.observe("click", function(){
                var response = self.save();
                if(response.success){
                    modalElement.hide();
                    new Trax.Model.Role.Table();
                }
            });
        });

        modalElement.show();
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
Trax.Model.Venue.Table = Class.create({


    initialize: function(){
        var self = this;
        var response = Trax.getResource("resources/venue/list");
        var table = $('page-title-header').update("Venues");
        $('table-title-header').update("Venues");
        $('table-search').setAttribute("placeholder", "Search Venues");
        this.populateTable(response.object);

        $$('.table_wrapper .add').each(function(button){
            button.update("New Venue");
            button.stopObserving("click");
            button.observe("click", function(){
                self.edit();
            });
        });

        $$('.trax_table .delete').each(function(button){
            button.observe("click", function(){
                self.delete(this.readAttribute('id'));
            });
        });

        $$('.trax_table .edit').each(function(button){
            button.observe("click", function(){
                self.edit(this.readAttribute('id'));
            });
        });

        new Trax.Modal().hideAll();

    },

    populateTable: function(venues){
        var data = {};
        data.venues = venues;

        var table = $('main-admin-table');
        new EJS({url: contextPath + '/resources/ui/templates/admin/venue/table.ejs'}).update(table, data);
    },

    delete: function(id){
        return Trax.getResource("resources/venue/delete/" + id);
    },

    edit: function(id){
        new Trax.Model.Venue.Edit(id);
    }

});

Trax.Model.Venue.Edit = Class.create({


    initialize: function(id){
        var response = {};

        if(id){
            response = Trax.getResource(contextPath + "/resources/venue/"+id);
        }else{
            response = Trax.getResource(contextPath + "/resources/venue/object");
        }
        this.populateModal(response.object);
    },

    populateModal: function(venue){
        var self = this;
        var modalElement = new Trax.Modal().getModal("venue");

        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/venue/edit.ejs'}).update(modalElement, venue);

        $$('.trax_modal .venue.save').each(function(button){
            button.observe("click", function(){
                var response = self.save();
                if(response.success){
                    modalElement.hide();
                    new Trax.Model.Venue.Table();
                }
            });
        });

        $$('.trax_modal .room.edit').each(function(button){
            button.observe("click", function(){
                new Trax.Model.Room.Edit(this.readAttribute('id'), venue);
            });
        });

        $$('.trax_modal .room.add').each(function(button){
            button.observe("click", function(){
                new Trax.Model.Room.Edit(null, venue);
            });
        });

        modalElement.show();
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
Trax.Model.Room.Edit = Class.create({


    initialize: function(id, venue){
        this.venue = venue;
        var response = {};
        var self = this;
        var modalElement = new Trax.Modal().getModal("room");

        if(id){
            response = Trax.getResource(contextPath + "/resources/room/"+id);
        }else{
            response = Trax.getResource(contextPath + "/resources/room/object");
        }

        var room = response.object;

        this.populateModal(modalElement, room);
        if(room.id){
            this.editModal(venue, modalElement);
        }else{
            this.addModal(venue, modalElement)
        }

        $$('.trax_modal .room.delete').each(function(button){
            button.update("Delete");
            button.stopObserving("click");
            button.observe("click", function(){
                var result = self.delete(this.readAttribute('id'));
                if(result.success){
                    modalElement.hide();
                    new Trax.Model.Venue.Edit(venue.id)
                }
            });
        });

        modalElement.show();
    },

    addModal: function(venue, modal){
        var self = this;
        $$('.trax_modal .room.submit').each(function(button){
            button.update("Add");
            button.stopObserving("click");
            button.observe("click", function(){
                var room = Trax.formToObject('editRoom');
                var response = self.add(room);
                if(response.success){
                    modal.hide();
                    new Trax.Model.Venue.Edit(venue.id)
                }
            });
        });
    },

    editModal: function(venue, modal){
        var self = this;
        $$('.trax_modal .room.submit').each(function(button){
            button.update("Save");
            button.stopObserving("click");
            button.observe("click", function(){
                var room = Trax.formToObject('editRoom');
                var response = self.save(room);
                if(response.success){
                    modal.hide();
                    new Trax.Model.Venue.Edit(venue.id)
                }
            });
        });
    },

    populateModal: function(modal, room){
        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/room/edit.ejs'}).update(modal, room);
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



/*********************************************
 * SESSION MODEL
 *********************************************/
Trax.Model.Session = {};
Trax.Model.Session.Page = Class.create({

    initialize: function(venueId){
        EJS.config({cache: false});
        var self = this;
        self.venueId = venueId;
        var response = Trax.getResource(contextPath + "/resources/venue/"+venueId);
        var venue = response.object;
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
            var response = Trax.getResource(contextPath + "/resources/session/" + sessionId);
            var session = response.object;
            new EJS({url: contextPath + '/resources/ui/templates/session/session-detail.ejs'}).update(target, session);
        }


    }
});