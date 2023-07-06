let createForm = document.getElementById('messaging-create-form');
let titleInput = document.getElementById('title');
let receiverInput = document.getElementById('receiver');
let textInput = document.getElementById('text');
let selectMessageType = document.getElementById('select-message-type');
let submitButton = document.getElementById('submit-button');
let operationResultDiv = document.getElementById('operation-result-div');

// ++++++++++++++++++++++++++++++++++++++++++++++++++++Create Request+++++++++++++++++++++++++++++++++++++++++++++++++++
submitButton.onclick = (e) => {
    e.preventDefault();

    let createObj = {
        receiver: "",
        text: "",
        subject: "",
        messageTypeId: -1
    };

    createObj.receiver = receiverInput.value;
    createObj.text = textInput.value;
    createObj.subject = titleInput.value;
    createObj.messageTypeId = selectMessageType.value.split('.')[0];

    createForm.hidden = true;
    operationResultDiv.hidden = false;
    operationResultDiv.style.color = 'black';
    operationResultDiv.innerText = 'در حال ارسال لطفا صبر کنید...';

    (async () => {
        let response = await fetch("/adm/messaging/send", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(createObj)
        });

        if (response.status === 200) {
            console.log("message sent")
            operationResultDiv.style.color = 'green';
            operationResultDiv.innerText = 'پیام با موفقیت ارسال شد';
        } else if (response.status === 500) {
            createForm.hidden = false;
            operationResultDiv.hidden = false;
            operationResultDiv.style.color = 'red';
            operationResultDiv.innerText = 'خطا سرور';
        }
    })();
}

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++Fill Select++++++++++++++++++++++++++++++++++++++++++++++++++++

function refreshSelect() {
    fetch("/pub/enum/message-type/", {
        method: "GET", headers: {}
    }).then(response => response.json())
        .then(json => {
            let data = JSON.parse(JSON.stringify(json));
            console.log(data);
            fillMessageSelectElement(data);
        });
}

//execute at load
refreshSelect();

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++Fill Select++++++++++++++++++++++++++++++++++++++++++++++++++++
function fillMessageSelectElement(data) {
    for (let i = 0; i < data.length; i++) {
        let option = document.createElement('option');
        option.innerHTML = data[i].id + '.' + data[i].title;
        selectMessageType.appendChild(option);
    }
}