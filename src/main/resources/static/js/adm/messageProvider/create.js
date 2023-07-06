let createForm = document.getElementById('message-provider-create-form');
let titleInput = document.getElementById('title');
let usernameInput = document.getElementById('username');
let passwordInput = document.getElementById('password');
let formSubmitButton = document.getElementById('message-provider-create-form-submit-btn');
let operationResultDiv = document.getElementById('operation-result-div');


operationResultDiv.hidden = true;

function create() {
    let createObj = {
        title: "",
        username: "",
        password: ""
    };

    // ++++++++++++++++++fill create obj++++++++++++++++

    createObj.title = titleInput.value;
    createObj.username = usernameInput.value;
    createObj.password = passwordInput.value;

    // +++++++++++++++++++Fetch++++++++++++++++++++++++++
    let response;
    (async () => {
        response = await fetch('/adm/message/provider/', {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(createObj)
        });

        // ++++++++++++++++Check response++++++++++++++++++++
        if (response.status === 200) {
            createForm.hidden = true;
            operationResultDiv.hidden = false;
            operationResultDiv.style.color = 'black';
            operationResultDiv.innerText = 'سروس با موفقیت ثبت شد';
        } else if (response.status === 500) {
            operationResultDiv.hidden = false;
            operationResultDiv.style.color = 'red';
            operationResultDiv.innerText = 'خطا سرور';
        } else {
            operationResultDiv.hidden = false;
            operationResultDiv.style.color = 'red';
            operationResultDiv.innerText = 'نامشخص';
        }
    })();
}

// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
formSubmitButton.onclick = () => {
    create();
}

