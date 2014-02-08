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
        var icon = new Element('div');
        jQuery(icon).addClass("glyphicon");
        jQuery(icon).addClass("glyphicon-" + this.icon);
        jQuery(icon).addClass("trax-button-icon");
        button.update(icon);
        button.insert(this.text);
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