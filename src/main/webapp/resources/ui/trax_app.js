// Default NameSpace
Trax = {};
Trax.printTheWordSwag = function(){
    console.log("swag");
};

Trax.ajax = function(url, method, params){
    var self = this;
    var response;
    new Ajax.Request(url, {
        asynchronous: false,
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
        var template = new EJS({url: 'resources/ui/templates/venue.ejs'}).update(table, json);



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

    },

    populateTable: function(data){
        var json = JSON.parse(data);
        console.log(json);
        EJS.config({cache: false});
        var table = $('main-admin-table');
        var template = new EJS({url: 'resources/ui/templates/user.ejs'}).update(table, json);

    },

    deleteUser: function(userId){
        return Trax.ajax("resources/user/delete", 'post', {userId: userId});
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
        var template = new EJS({url: 'resources/ui/templates/session.ejs'}).update(table, json);



    }

});

