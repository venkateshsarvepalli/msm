App = {
    validateForm : function (){
        var valid = true;
        $("input[type=date]").each(function() {
            var self = $(this);
            if(self.val() == '' ){
                valid = false;
            }
        });
        return valid;
    },

    formSubmit : function (){
        $("#submit").click(function(){
            if (App.validateForm()){
                var fromDate = $("#fromDate").val();
                var toDate = $("#toDate").val();
                $.ajax({
                    url: '/fetchInfo',
                    method: "GET",
                    data : {
                        fromDate : fromDate,
                        toDate : toDate
                    },
                    success : function(result){
                        $("#h1").text("Data Processing..");
                        App.storeData(JSON.parse(result));
                    }
                });
            }
            else{
                alert("please enter the data");
            }
        });
    },

    storeData : function (data){
        var adapter = {};
        adapter.userAdapters = data;
        $.ajax({
            url: 'http://localhost:8080/api/v1/storeDBExpressUserInfo',
            method: "POST",
            datatype:'json',
            data : "adapter="+JSON.stringify(data),
            success : function(result){
                if(result.message == 'success'){
                    $("#h1").text("Data processed successfully..!");
                }
                else{
                    $("#h1").text("Some thing went wrong please try again later...!");
                }
            },
        });
    }
}
