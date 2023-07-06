let userRegisterDiv = document.getElementById('user-register-div');
let submitBtn = document.getElementById("user-register-form-submit-btn");
let usernameInput = document.getElementById('username');
let passwordInput = document.getElementById('password');
let passwordRepeatInput = document.getElementById('password-repeat');
let nameInput = document.getElementById('name');
let familyInput = document.getElementById('family');
let errorMessageDiv = document.getElementById('error-message-div');
let registerDoneMessageDiv = document.getElementById('register-done-message-div');
let profilePictureFileInput = document.getElementById('profilePictureFile');
let profilePictureImg = document.getElementById('img-profile-picture');
let registerForm = document.getElementById('register-form');

let registerObj = {
    username: "", password: "", name: "", family: "", profilePicture: ""
};

registerDoneMessageDiv.hidden = true;

submitBtn.onclick = function (e) {
    if (!validateInputs()) {
        return;
    }

    //body
    registerObj.username = usernameInput.value;
    registerObj.password = passwordInput.value;
    registerObj.name = nameInput.value;
    registerObj.family = familyInput.value;

    (async () => {
        let response = await fetch("/pub/user/", {
            method: "POST", headers: {
                Accept: "application/json", "Content-Type": "application/json"
            }, body: JSON.stringify(registerObj)
        });

        let responseCode = JSON.stringify(response.status);
        console.log(responseCode);
        if (responseCode === '200') {
            usernameInput.value = '';
            passwordInput.value = '';
            nameInput.value = '';
            familyInput.value = '';
            profilePictureFileInput.value = '';
            userRegisterDiv.hidden = true;
            registerDoneMessageDiv.hidden = false;
        } else if (responseCode === '500') {
            errorMessageDiv.innerText = 'خطا سرور'
        } else {
            operationResultDiv.hidden = false;
            operationResultDiv.style.color = 'red';
            operationResultDiv.innerText = 'نامشخص';
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

/***************************************************UPLOAD*************************************************************/
function uploadFile(fileInput, subDirectories) {
    let file = fileInput.files[0];
    console.log("subDirectories :" + subDirectories);

    let fileFormData = new FormData();
    fileFormData.set("file", file);
    fileFormData.set("subDirectories", subDirectories);

    let response;
    (async () => {
        await fetch("/pub/files/upload", {
            method: "POST", body: fileFormData
        })
            .then(e => e.text())
            .then(text => {
                profilePictureImg.setAttribute('src', text);
                registerObj.profilePicture = text;
                response = text;
                return text;
            });
    })();
}

let uploadProfilePictureBtn = document.getElementById('upload-profile_picture_btn');
uploadProfilePictureBtn.onclick = () => {
    uploadFile(profilePictureFileInput, "/pub/profilePictures/");
};