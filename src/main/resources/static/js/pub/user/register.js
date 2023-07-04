let userRegisterDiv = document.getElementById('user-register-div');
let submitBtn = document.getElementById("user-register-form-submit-btn");
let usernameInput = document.getElementById('username');
let passwordInput = document.getElementById('password');
let passwordRepeatInput = document.getElementById('password-repeat');
let errorMessageDiv = document.getElementById('error-message-div');
let registerDoneMessageDiv = document.getElementById('register-done-message-div');

let registerObj = {
    username: "",
    password: ""
};

registerDoneMessageDiv.hidden = true;

submitBtn.onclick = function (e) {
    if (!validateInputs()) {
        return;
    }

    registerObj.username = usernameInput.value;
    registerObj.password = passwordInput.value;

    (async () => {
        let response = await fetch("/pub/user/", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(registerObj)
        });

        let responseCode = JSON.stringify(response.status);
        console.log(responseCode);
        if (responseCode === '201') {
            usernameInput.value = '';
            passwordInput = '';
            userRegisterDiv.hidden = true;
            registerDoneMessageDiv.hidden = false;
        }
    })();
}

function validateInputs() {
    let password = passwordInput.value;
    let passwordRepeat = passwordRepeatInput.value;
    if (password === null || password === '' || passwordRepeat === null || passwordRepeat === '') {
        errorMessageDiv.innerText = 'رمز عبور و تکرار آن نمی تواند خالی باشد'
        return false;
    } else if (password !== passwordRepeat) {
        errorMessageDiv.innerText = 'رمز عبور و تکرار آن باید یکسان باشد'
        return false;
    }
    return true;
}