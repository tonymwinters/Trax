/**
 * ADMIN TABLE PAGE
 * All functionality for Admin Tables - Maza Who?
 */

document.observe("dom:loaded", function() {
    new Trax.Model.User.Table();
});


$("usersTab").observe("click", function(){
   removeActive();
   this.addClassName("active");
    var userTable = new Trax.Model.User.Table();
});

$('venuesTab').observe("click", function(){
    removeActive();
    this.addClassName("active");
    Trax.printTheWordSwag();
    var venueTable = new Trax.Model.Venue.Table();
});

$('rolesTab').observe("click", function(){
    removeActive();
    this.addClassName("active");
    var roleTable = new Trax.Model.Role.Table();
});

$('permissionsTab').observe("click", function(){
    removeActive();
    this.addClassName("active");
    var permissionTable = new Trax.Model.Permission.Table();
});

function removeActive(){
    $$('li.model').each(function(el){
        el.removeClassName("active");
    });
}
