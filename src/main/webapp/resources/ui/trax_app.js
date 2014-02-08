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
            var value = element.value;
            obj[attr] = value;
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
        if(options != null){
            this.text = options.text;
            this.classes = options.classes;
            this.action = options.action;
            this.create();
        }
    },

    create: function(){
        var button = new Element('button');
        button.update(this.text);
        var classes = this.classes;
        for(var i = 0; i < classes.length; i++){
            jQuery(button).addClass(classes[i]);
        }
        jQuery(button).addClass("button");
        jQuery(button).addClass('btn');
        if(this.action != null){
            //add click css
            jQuery(button).addClass('clickable');
            button.stopObserving('click');
            button.observe('click', this.action);
        }
        this.button = button;
        return this;
    },

    getElement: function(){
        if(this.button == null){
            this.create();
        }
        return this.button;
    }

});

/*********************************************
 * Trigger WIDGET
 *********************************************/
Trax.Widget.Trigger = Class.create({

    initialize: function(event, action){
        this.event = event;
        this.action = action;
    },

    getRowClasses: function() {
        if(this.rowClasses != null){
            return this.rowClasses;
        }
        return [];
    },

    create: function(){
        return new Trax.Widget.Trigger(this.event, this.action);
    }
});

/*********************************************
 * INPUT WIDGET
 *********************************************/
Trax.Widget.Input = Class.create({
    initialize: function(options){
        if(options != null){
        }
    }
});

/*********************************************
 * WRAPPER WIDGET
 *********************************************/
Trax.Widget.Wrapper = Class.create({
    initialize: function(title, containerId, contents){
        this.title = title;
        this.container = $(containerId);
        jQuery(this.container).addClass("widget_wrapper");
        this.container.innerHTML = "";
        this.setTitle(title);
        this.addContents(contents)
    },

    setTitle: function(title){
        var el = new Element('h1');
        jQuery(el).addClass("wrapper_title_h1");
        el.innerHTML = title;
        this.container.insert(el);
    },

    addContents: function(contents){
        var self = this;
        if(contents != null){
            contents.each(function(item){
                self.container.insert(item);
            });
        }
    },

    getElement: function(){
        return this.container;
    }
});

/*********************************************
 * DATASOURCE WIDGET
 *********************************************/
Trax.Widget.Datasource = Class.create({

    initialize: function(){
    },

    refreshData: function(){
        if(this.fetchData != null){
            this.data = this.fetchData();
            return this.data;
        }
        return {};
    },

    getData: function(){
        if(this.data == null){
            this.data = this.refreshData();
        }
        return this.data;
    }
});

/*********************************************
 * DATATABLE WIDGET
 *********************************************/
Trax.Widget.DataTable = Class.create({
    initialize: function(template, datasource){
        this.template = template
        this.datasource = datasource;
        this.initTable();
        this.refreshTable();

    },

    refreshTable: function(){
        if(this.table == null){
            this.initTable();
        }
        var table = this.table;
        table.innerHTML = "";

        EJS.config({cache: false});
        new EJS({url: contextPath + this.template}).update(table, this.datasource.getData());
        this.initRowActions(this.rowActions);
        this.initRowTriggers(this.rowTriggers);
    },

    initTable: function(){
        var table = new Element('table');
        jQuery(table).addClass("table");
        jQuery(table).addClass("trax_table");
        jQuery(table).addClass("table-hover");
        this.table = table;
    },

    initRowActions: function(actions){
        if(actions == null || actions.size() < 1){return;}
        this.rowActions = actions;
        //create extra column
        var actionColumnHeader = new Element('th');
        actionColumnHeader.innerHTML = "Actions";
        this.table.down('thead').down('tr').insert(actionColumnHeader);

        //add buttons
        var rows = this.table.down('tbody').rows;
        for(var i = 0; i < rows.length; i++){
            var row = rows[i];
            var actionColumn = new Element('td');
            actionColumn.id = row.id;
            jQuery(actionColumn).addClass("action");

            //add action buttons
            actions.each(function(action){

                var a = action.create();
                var el = a.getElement();
                el.id = row.id;
                actionColumn.insert(el);
            });
            row.insert(actionColumn);

        };
        this.rowActions = actions;
    },

    initRowTriggers: function(triggers){
        if(triggers == null || triggers < 1){return;}

        //get rows
        var rows = this.table.down('tbody').rows;
        for(var i = 0; i < rows.length; i++){
            var row = rows[i];
            triggers.each(function(trigger){
                //add click css
                if(trigger.event = 'click'){
                    jQuery(row).addClass('clickable');
                }
                //create new trigger and add to row event
                var t = trigger.create();
                t.id = row.id;
                row.stopObserving(t.event);
                row.observe(t.event, t.action);
            });
        };
        this.rowTriggers = triggers;
    },

    getElement: function(){
        return this.table;
    }

});

/*********************************************
 * MODAL WIDGET
 *********************************************/
Trax.Widget.Modal = Class.create({
    initialize: function(title, template, datasource){
        this.title = title;
        this.template = template
        this.datasource = datasource;
        this.createModal();
        this.populateModal();
        this.initGenericListeners();
    },

    createModal: function(){
        this.initContainer();
        this.initDialog();
        this.initContent();
        this.initHeader();
        this.initBody();
        this.initFooter();
    },

    initContainer: function(){
        var id;
        var modal;
        if(this.type != null){
            id = "modal-"+this.type;
        }else{
            id = "modal-plane";
        }
        modal = jQuery('<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>')[0];
        modal.id = id;
        $(document.body).insert(modal)
        this.container = $(id);


    },

    initDialog: function(){
        var div = new Element('div');
        jQuery(div).addClass("modal-dialog");
        jQuery(div).addClass("trax-modal-dialog");
        this.container.update(div);
        this.dialog = div;
    },

    initContent: function(){
        var div = new Element('div');
        jQuery(div).addClass("modal-content");
        jQuery(div).addClass("trax-modal-content");
        this.dialog.insert(div);
        this.dialogContent = div;
    },

    initHeader: function(){
        var div = new Element('div');
        jQuery(div).addClass("modal-header");
        jQuery(div).addClass("trax-modal-header");
        div.insert(jQuery('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>')[0]);
        div.insert(jQuery('<h4 class="modal-title">'+this.title+'</h4>')[0]);
        this.dialogContent.insert(div);
        this.header = div;
    },

    initBody: function(){
        var div = new Element('div');
        jQuery(div).addClass("modal-body");
        jQuery(div).addClass("trax-modal-body");
        this.dialogContent.insert(div);
        this.body = div;
    },

    initFooter: function(){
        var self = this;
        var div = new Element('div');
        jQuery(div).addClass("modal-footer");
        jQuery(div).addClass("trax-modal-footer");

        div.insert(jQuery('<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>')[0]);
        this.dialogContent.insert(div);
        this.footer = div;
    },

    initButtons: function(buttons){
        var self = this;
        if(buttons != null){
            buttons.each(function(button){
                var b = button.create();
                self.footer.insert(b.getElement());
            });
        }
    },

    initGenericListeners: function(){
        $$('.modal .rolesContainer ul li input:checkbox').each(function(box){

            if(jQuery(box).prop('checked') == true){
                jQuery(box).parent().addClass('checkbox_checked');
            }

            var row = jQuery(box).parent()[0];
            if(row){
                row.stopObserving("click");
                row.observe("click", function(ev){
                    var row = ev.target;
                    if(jQuery(row).hasClass('checkbox_checked')){
                        jQuery(row).removeClass('checkbox_checked');
                        jQuery(row).children(0).prop('checked', false);
                    }else{
                        jQuery(row).addClass('checkbox_checked');
                        jQuery(row).children(0).prop('checked', true);
                    }
                });
            }
        });
    },

    populateModal: function(){
        EJS.config({cache: false});
        new EJS({url: contextPath + this.template}).update(this.body, this.datasource.fetchData());
    },

    getElement: function(){
        return this.container;
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


/*********************************************
 * ROLE MODEL
 *********************************************/
Trax.Model.Role = Class.create({
    getRoles: function(){
        return Trax.getResource(contextPath + "/resources/role/list");
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
        new EJS({url: contextPath + '/resources/ui/templates/admin/venue/adminModal.ejs'}).update(modalElement, data);


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
        new EJS({url: contextPath + '/resources/ui/templates/admin/room/adminModal.ejs'}).update(modal, data);
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
        new EJS({url: contextPath + '/resources/ui/templates/admin/room/adminModal.ejs'}).update(modal, data);
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
            var self = this;
            var options = {};
            options.dataType = "session";
            options.title = "New Session";
            options.save = function(){
                var response = Trax.postResource('/resources/'+this.dataType+'/save', Trax.formToObject('edit'+this.dataType));
                if(response.success){
                }
            };
            options.afterLoad = function(){
                jQuery("#startDate").pickadate();
                jQuery("#startTime").pickatime();
                jQuery("#endDate").pickadate();
                jQuery("#endTime").pickatime();
            };
            options.getData = function(){
                var data = {};
                data.type = this.dataType;
                data.session = Trax.getResource(contextPath + "/resources/session/object");
                data.venue = venue;
                return data;
            };
            var modal = new Trax.Widget.Modal(options);
            modal.show();
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