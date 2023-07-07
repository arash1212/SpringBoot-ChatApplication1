let messageTextArea = document.getElementById('message-text-area');
let chatForm = document.getElementById('chat-form');
let chatFormSubmitButton = document.getElementById('chat-form-submit-button');
let messageInput = document.getElementById('message-input');
let resultDiv = document.getElementById('operation-result-div');


hideChatForm();

resultDiv.innerText = 'در حال اتصال به سرور';
//++++++++++++++++++++++++++++++++++++++++++++++++++++++SOCK-JS+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

let socksJs = new SockJS("/chat");
let stompJs = Stomp.over(socksJs);

socksJs.onclose = () => {
    stompJs.disconnect();
    hideChatForm();
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++STOMP-JS+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

stompJs.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    showChatForm();

    stompJs.subscribe('/topic/chat', function (message) {
        printMessage(message);
    });
});

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++GetChat++++++++++++++++++++++++++++++++++++++++++++++++++++++++

let chat = {
    id: -1
}

let chatId = -1;
let chatTitle = '';

(async () => {
    let arr = window.location.pathname.split('/');
    chatTitle = arr[arr.length - 1];

    console.log(arr);

    if (chatTitle == null || chatTitle === '') {
        return;
    }

    let response = await fetch("/pub/chat/title/" + chatTitle, {
        method: "GET"
    }).then(response => response.json())
        .then(json => {
            console.log(json);
            fillMessagesTextArea(JSON.parse(JSON.stringify(json)));
        });

})();

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
chatFormSubmitButton.onclick = (e) => {
    e.preventDefault();

    //todo chatId
    let message = {
        text: "",
        chatId: 1
    };

    message.text = messageInput.value;
    send(message);
}

function send(message) {
    stompJs.send("/app/chat", {}, JSON.stringify(message));
    messageInput.value = '';
}

function printMessage(message) {
    let json = JSON.parse(JSON.parse(JSON.stringify(message.body)));
    console.log("received : " + json);
    messageTextArea.append(json.text + "\n");
}

function showChatForm() {
    chatForm.hidden = false;
    resultDiv.hidden = true;
}

function hideChatForm() {
    chatForm.hidden = true;
    resultDiv.hidden = false
}

function fillMessagesTextArea(data) {
    console.log("data : " + data);
    for (let obj in data.messages) {
        messageTextArea.append(data.messages[obj].creator.id + " - " + data.messages[obj].creator.name + " " +  data.messages[obj].creator.family + " گفته :  " + data.messages[obj].text + "\n");
    }
}
