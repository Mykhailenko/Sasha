
var server = {}
server.pullInterval = 3000;
server.endpoint = "/rest/rules"

window.setInterval(() => {
    $.ajax({url: server.endpoint, success: (result) => {
        result.forEach((evalutedRule) => {
            if(document.getElementById(evalutedRule.id) == null){
                createContainer(evalutedRule)
            } else {
                updateContainer(evalutedRule)
            }
        })
    }});
}, server.pullInterval);

function createContainer(evalutedRule) {

}

function updateContainer(evalutedRule) {
}


//var div = document.createElement("div");
//            div.style.color = "red";
//            div.innerHTML = result;
//            document.getElementById("main").appendChild(div);
