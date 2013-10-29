/**
 * ADMIN TABLE PAGE
 * All functionality for Admin Tables - Maza Who?
 */

document.observe("dom:loaded", function() {
    new Trax.Model.User.UserTable();
});


$("usersTab").observe("click", function(){
   console.log("clicked on user tab");
   removeActive();
   this.addClassName("active");
    var userTable = new Trax.Model.User.UserTable();
});

$('venuesTab').observe("click", function(){
    console.log("clicked on venue tab");
    removeActive();
    this.addClassName("active");
    Trax.printTheWordSwag();
    var venueTable = new Trax.Model.Venue.VenueTable();
});

$('sessionsTab').observe("click", function(){
    console.log("clicked on session tab");
    removeActive();
    this.addClassName("active");
    var sessionTable = new Trax.Model.Session.SessionTable();
});

function removeActive(){
    $$('li.model').each(function(el){
        el.removeClassName("active");
    });
}
