
var UPDATE_INTERVAL = 3000;


window.setInterval(function(){
    $.ajax({url: "/rest/rules", success: function(result){
            var div = document.createElement("div");
            div.style.color = "red";
            div.innerHTML = "Hello";

            document.getElementById("main").appendChild(div);
        }});
}, 3000);

alert(UPDATE_INTERVAL)