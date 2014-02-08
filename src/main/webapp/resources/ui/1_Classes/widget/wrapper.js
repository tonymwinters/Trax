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