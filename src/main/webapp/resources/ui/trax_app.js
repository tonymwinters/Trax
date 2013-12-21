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

Trax.Model = {};

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
        var json = JSON.parse(data);
        console.log(json);

        var table = $('main-admin-table');
        new EJS({url: contextPath + '/resources/ui/templates/admin/venue.ejs'}).update(table, json);



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

        $$('.delete_button').each(function(button){
            button.observe("click", function(){
                var userId = this.readAttribute('id');
                self.deleteUser(userId);
            });
        });
        $$('.edit_button').each(function(button){
            button.observe("click", function(){
                var userId = this.readAttribute('id');
                self.editUser(userId);
            });
        });

    },

    populateTable: function(data){
        var json = JSON.parse(data);
        EJS.config({cache: false});
        var table = $('main-admin-table');
        new EJS({url: contextPath + '/resources/ui/templates/admin/user.ejs'}).update(table, json);

    },

    deleteUser: function(userId){
        return Trax.ajax("resources/user/delete/" + userid, 'post');
    },

    editUser: function(userId){
        var data = Trax.ajax(contextPath + "/resources/user/"+userId,'get', {});
        var user = JSON.parse(data);
        EJS.config({cache: false});
        var modal = $('modal');
        new EJS({url: contextPath + '/resources/ui/templates/admin/editUser.ejs'}).update(modal, user);
        modal.show();
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