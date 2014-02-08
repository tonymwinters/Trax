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