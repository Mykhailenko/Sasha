

window.setInterval(function(){
    $.ajax({url: "/rest/rules", success: function(result){
            $("#main").html(result);
        }});
}, 3000);