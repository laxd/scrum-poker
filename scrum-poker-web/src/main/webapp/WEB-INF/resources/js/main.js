$(document).ready(function(){
    connect();
});

var stomp = null;

function connect() {
    var socket = new SockJS(context + "/stomp")
    stomp = Stomp.over(socket);
    stomp.connect({}, function(frame) {
        console.log("Connected: " + frame);

        stomp.subscribe("/topic/useradded", function(greeting) {
            var response = JSON.parse(greeting.body);
            showGreeting(response.greeting);
        });
    })
}

function showGreeting(greeting) {
    $("#response").html(greeting);
}

function submitName() {
    var name = $("#name").val();
    stomp.send(context + "/addUser", {}, JSON.stringify({'name':name}));
}