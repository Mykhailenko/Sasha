
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
    alert("createContainer " + evalutedRule.id)
    var div = document.createElement('div')
    div.id = evalutedRule.id
    if (evalutedRule.ok){
        div.className = 'rule rule-box-succeeded'
        div.innerHTML = evalutedRule.message
    } else {
        var details = document.createElement('details')
        details.className = 'rule rule-box-failed'
        var summary = document.createElement('summary')
        summary.className = 'rule-summary'
        summary.innerHTML = evalutedRule.message
        details.appendChild(summary)
        details.innerHTML += evalutedRule.extendedMessage
        div.appendChild(details)
    }
    document.getElementById('main').appendChild(div)

}

function updateContainer(evalutedRule) {
}
