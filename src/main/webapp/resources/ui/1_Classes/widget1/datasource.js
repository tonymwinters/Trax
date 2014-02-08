/*********************************************
 * DATASOURCE WIDGET
 *********************************************/
Trax.Widget.Datasource = Class.create({

    initialize: function(){
    },

    refreshData: function(){
        if(this.fetchData != null){
            this.data = this.fetchData();
            return this.data;
        }
        return {};
    },

    getData: function(){
        if(this.data == null){
            this.data = this.refreshData();
        }
        return this.data;
    }
});