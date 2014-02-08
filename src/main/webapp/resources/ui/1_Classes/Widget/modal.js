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