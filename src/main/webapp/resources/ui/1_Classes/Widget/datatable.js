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

        //only create new header if one is already there
        var header = this.table.down('thead');
        if(header != null){
            //create extra column
            var actionColumnHeader = new Element('th');
            actionColumnHeader.innerHTML = "Actions";
            this.table.down('thead').down('tr').insert(actionColumnHeader);
        }

        this.rowActions = actions;

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