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
    initialize: function(type){
        this.id = "modal-"+type;
        $(document.body).insert("<div id='"+this.id+"' class='trax_modal' style='display: none'></div>")
    }
});

Trax.Model = {};


/*********************************************
 * ROLE MODEL
 *********************************************/
Trax.Model.Role = Class.create({
    getRoles: function(){
        var data = Trax.ajax(contextPath + "/resources/role/list", 'get');
        return JSON.parse(data);
    }
});


/*********************************************
 * VENUE MODEL
 *********************************************/
Trax.Model.Venue = {};
Trax.Model.Venue.VenueTable = Class.create({


    initialize: function(){
        var data = Trax.ajax("resources/venue/list",'get', {});
        var table = $('page-title-header').update("Venues");
        $('table-title-header').update("Venues");
        $('table-add-button').update("New Venue");
        $('table-search').setAttribute("placeholder", "Search Venues");
        $('table-href').setAttribute("href", "http://venuesURL");
        this.populateTable(data);

    },

    populateTable: function(data){
        var self = this;
        var json = JSON.parse(data);

        var table = $('main-admin-table');
        new EJS({url: contextPath + '/resources/ui/templates/admin/venue/table.ejs'}).update(table, json);

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
    },

    delete: function(id){
        return Trax.ajax("resources/venue/delete/" + id, 'post');
    },

    edit: function(id){
        new Trax.Model.Venue.Edit(id);
    }

});

Trax.Model.Venue.Edit = Class.create({


    initialize: function(id){
        var data = Trax.ajax(contextPath + "/resources/venue/"+id,'get');

        this.populateModal(data);
    },

    populateModal: function(data){
        var self = this;
        var venue = JSON.parse(data).object;
        var modalElement = $(new Trax.Modal("venue").id);

        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/venue/edit.ejs'}).update(modalElement, venue);

        $$('.trax_modal .venue.save').each(function(button){
            button.observe("click", function(){
                self.save(modalElement);
            });
        });

        $$('.trax_modal .room.edit').each(function(button){
            button.observe("click", function(){
                new Trax.Model.Room.Edit(this.readAttribute('id'));
            });
        });

        modalElement.show();
    },

    save: function(modalElement){
        var obj = Trax.formToObject('editVenue');
        var json = JSON.stringify(obj);
        var result = JSON.parse(Trax.ajax('/resources/venue/save','POST', 'application/json', json));
        if(result.success){
            modalElement.hide();
        }
    }

});



/*********************************************
 * USER MODEL
 *********************************************/
Trax.Model.User = {};
Trax.Model.User.UserTable = Class.create({


    initialize: function(){
        var self = this;
        var data = Trax.ajax("resources/user/list", 'get', {});
        var table = $('page-title-header').update("Users");
        $('table-title-header').update("Users");
        $('table-add-button').update("New User");
        $('table-search').setAttribute("placeholder", "Search Users");
        $('table-href').setAttribute("href", "http://usersURL");
        this.populateTable(data);

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

    },

    populateTable: function(data){
        var json = JSON.parse(data);
        EJS.config({cache: false});
        var table = $('main-admin-table');
        new EJS({url: contextPath + '/resources/ui/templates/admin/user/table.ejs'}).update(table, json);

    },

    delete: function(id){
        return Trax.ajax("resources/user/delete/" + id, 'post');
    },

    edit: function(id){
        new Trax.Model.User.Edit(id);
    }

});

Trax.Model.User.Edit = Class.create({


    initialize: function(id){
        var data = Trax.ajax(contextPath + "/resources/user/"+id,'get');

        this.populateModal(data);

    },

    populateModal: function(data){
        var self = this;
        var user = JSON.parse(data).object;
        var modalElement = $(new Trax.Modal("user").id);
        var availableRoles = new Trax.Model.Role().getRoles().object;
        var userdata = {};
        userdata.user = user;
        userdata.availableRoles = availableRoles;

        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/user/edit.ejs'}).update(modalElement, userdata);
        user.roles.each(function(role){
            $$('.role').each(function(element){
               if(element.value == role.id){
                   element.checked = true;
               }
            });
        });

        $('save_button').observe("click", function(){
            self.save();
        });

        modalElement.show();
    },

    save: function(){
        var obj = Trax.formToObject('editUser');
        var json = JSON.stringify(obj);
        var result = JSON.parse(Trax.ajax('/resources/user/save','POST', 'application/json', json));
        if(result.success){
            $('modal').hide();
        }
    }

});



/*********************************************
 * ROOM MODEL
 *********************************************/
Trax.Model.Room = {};
Trax.Model.Room.Edit = Class.create({


    initialize: function(id){
        var data = Trax.ajax(contextPath + "/resources/room/"+id,'get');

        this.populateModal(data);
    },

    populateModal: function(data){
        var self = this;
        var room = JSON.parse(data).object;
        var modalElement = $(new Trax.Modal("room").id);

        EJS.config({cache: false});
        new EJS({url: contextPath + '/resources/ui/templates/admin/room/edit.ejs'}).update(modalElement, room);

        $$('.trax_modal .room.delete').each(function(button){
            button.observe("click", function(){
                self.delete(this.readAttribute('id'));
            });
        });

        $$('.trax_modal .room.save').each(function(button){
            button.observe("click", function(){
                self.save(modalElement);
            });
        });

        modalElement.show();
    },

    delete: function(id){
        return Trax.ajax("resources/room/delete/" + id, 'post');
    },

    save: function(modalElement){
        var obj = Trax.formToObject('editRoom');
        var json = JSON.stringify(obj);
        var result = JSON.parse(Trax.ajax('/resources/room/save','POST', 'application/json', json));
        if(result.success){
            modalElement.hide();
        }
    }

});



/*********************************************
 * SESSION MODEL
 *********************************************/
Trax.Model.Session = {};
Trax.Model.Session.SessionTable = Class.create({


    initialize: function(){
        var data = Trax.ajax("resources/session/list",'get', {});
        var table = $('page-title-header').update("Sessions");
        $('table-title-header').update("Sessions");
        $('table-add-button').update("New Session");
        $('table-search').setAttribute("placeholder", "Search Sessions");
        $('table-href').setAttribute("href", "http://sessionsURL");
        this.populateTable(data);

    },

    populateTable: function(data){
        var json = JSON.parse(data);
        console.log(json);

        var table = $('main-admin-table');
        new EJS({url: contextPath + '/resources/ui/templates/admin/session.ejs'}).update(table, json);
    }

});



Trax.Model.Session.Page = Class.create({

    initialize: function(venueId){
        EJS.config({cache: false});
        var self = this;
        self.venueId = venueId;
        var data = Trax.ajax(contextPath + "/resources/venue/"+venueId,'get', {});
        this.populateSessionList(data);
        this.populateInitialSession(data);

        // New Session Click Listener
        $$('div.new_session')[0].observe("click", function(){
            self.createNewSessionTemplate(data);
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

    createNewSessionTemplate: function(data){
        var newId = 0;
        var self = this;
        var newSession = {};
        newSession.name = "Untitled";
        newSession.startTime = new XDate().toString("yyyy-MM-dd'T'HH:mm:sszz");
        newSession.endTime = new XDate().toString("yyyy-MM-dd'T'HH:mm:sszz");
        newSession.description = "Enter description here."
        var response = Trax.ajax('/resources/venue/save','POST', 'application/json', '{"id": '+self.venueId+', "sessions" : ['+JSON.stringify(newSession)+']}');
        newSession.id = JSON.parse(response).object.sessions[0].id;


        var json = JSON.parse(data);
        json.object.sessions.unshift(newSession);

        this.populateSessionList(JSON.stringify(json));
        this.refreshListeners();
        $$('.single_session_container')[0].click();

    },

    // Remove Active Class From all Sessions
    removeActive: function(){
        $$('.single_session_container').each(function(element){
            element.removeClassName('active_session');
        });
    },

    populateSessionList: function(data){
        var json = JSON.parse(data);
        var target = $('all_sessions_container');
        new EJS({url: contextPath + '/resources/ui/templates/session/session-item.ejs'}).update(target, json);
    },

    populateInitialSession: function(data){
        var json = JSON.parse(data);
        $$('.single_session_container')[0].addClassName('active_session');
        var target = $$('div.session_info_container')[0];
        var header = $$('div.session_page_header')[0];
        new EJS({url: contextPath + '/resources/ui/templates/session/session-header.ejs'}).update(header, json.object);
        new EJS({url: contextPath + '/resources/ui/templates/session/session-detail.ejs'}).update(target, json.object.sessions[0]);

    },

    populateSessionDetail: function(sessionId){
        var target = $$('div.session_info_container')[0];

        if(sessionId != ""){
            var data = Trax.ajax(contextPath + "/resources/session/" + sessionId,'get');
            var json = JSON.parse(data);
            new EJS({url: contextPath + '/resources/ui/templates/session/session-detail.ejs'}).update(target, json.object);
        }


    }
});