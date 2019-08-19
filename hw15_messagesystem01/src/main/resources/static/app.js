let stompClient = null;

const setConnected = (connected) => {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#userLine").show();

    }
    else {
        $("#userLine").hide();

    }
    $("#users").html("");

}

const connect = () => {
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/response', (users) => showUser(JSON.parse(users.body)));

    });
}

const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

const sendUser = () => stompClient.send("/app/message", {}, JSON.stringify({'Name': $("#name").val()}))

const showUser = (users) => $("#userLine").append("<tr><td>" + users.name + "</td></tr>")



$(function () {
    $("form").on('submit', (event) => {
        event.preventDefault();
    });
    $("#connect").click(connect);
    $("#disconnect").click(disconnect);
    $("#send").click(sendUser);

});