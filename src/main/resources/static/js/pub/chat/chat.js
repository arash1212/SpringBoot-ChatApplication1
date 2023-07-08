let messageTextArea = document.getElementById('message-text-area');
let chatForm = document.getElementById('chat-form');
let chatFormSubmitButton = document.getElementById('chat-form-submit-button');
let messageInput = document.getElementById('message-input');
let resultDiv = document.getElementById('operation-result-div');


hideChatForm();

resultDiv.innerText = 'در حال اتصال به سرور';
//++++++++++++++++++++++++++++++++++++++++++++++++++++++SOCK-JS+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

let socksJs
let stompJs;


//+++++++++++++++++++++++++++++++++++++++++++++++++++++STOMP-JS+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

function connect(chatId) {
    socksJs = new SockJS("/chat");
    stompJs = Stomp.over(socksJs);
    socksJs.onclose = () => {
        stompJs.disconnect();
        hideChatForm();
    }


    console.log('Connecting Chat : ' + chatId);
    stompJs.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        showChatForm();

        console.log('subscribing to : /topic/chat/' + chatId);
        stompJs.subscribe('/topic/chat/' + chatId, function (message) {
            console.log('subscribed to : /topic/chat/' + chatId);
            printMessage(message);
        });
    });
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++GetChat++++++++++++++++++++++++++++++++++++++++++++++++++++++++

let chat = {
    id: -1
}

let chatId = -1;
let chatTitle = '';
let data;

function getChat() {
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
                data = JSON.parse(JSON.stringify(json));
                chatId = data.id;

                fillMessagesTextArea(data);
            });

        connect(1);
    })();
}

getChat();


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
chatFormSubmitButton.onclick = (e) => {
    e.preventDefault();

    //todo chatId
    let message = {
        text: "",
        chatId: -1
    };

    message.text = messageInput.value;
    message.chatId = chatId;
    send(message, message.chatId);
}

function send(message, chatId) {
    stompJs.send("/app/chat/" + chatId, {}, JSON.stringify(message));
    messageInput.value = '';
}

function printMessage(message) {
    let json = JSON.parse(JSON.parse(JSON.stringify(message.body)));
    console.log("received : " + json);
    // messageTextArea.append(json.text + "\n");
    messageTextArea.append(json.creator.id + " - " + json.creator.name + " " + json.creator.family + " گفته :  " + json.text + "\n");
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
        messageTextArea.append(data.messages[obj].creator.id + " - " + data.messages[obj].creator.name + " " + data.messages[obj].creator.family + " گفته :  " + data.messages[obj].text + "\n");
    }
}
