/**
 * ADMIN TABLE PAGE
 * All functionality for Admin Tables - Maza Who?
 */

document.observe("dom:loaded", function() {

    var options = {};
    options.containerId = "main-admin-table";
    options.title = "Users";
    options.create = true;
    options.search = true;
    options.actions = ["edit", "delete"];
    new Trax.Model.User.Table(options);
});


$("usersTab").observe("click", function(){
    removeActive();
    this.addClassName("active");
    var options = {};
    options.containerId = "main-admin-table";
    options.title = "Users";
    options.create = true;
    options.search = true;
    options.actions = ["edit", "delete"];
    new Trax.Model.User.Table(options);
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
