let userRegisterDiv = document.getElementById('user-register-div');
let submitBtn = document.getElementById("user-register-form-submit-btn");
let usernameInput = document.getElementById('username');
let emailInput = document.getElementById('email');
let passwordInput = document.getElementById('password');
let passwordRepeatInput = document.getElementById('password-repeat');
let nameInput = document.getElementById('name');
let familyInput = document.getElementById('family');
let errorMessageDiv = document.getElementById('error-message-div');
let registerDoneMessageDiv = document.getElementById('register-done-message-div');
let profilePictureFileInput = document.getElementById('profilePictureFile');
let profilePictureImg = document.getElementById('img-profile-picture');
let registerForm = document.getElementById('register-form');

///
let activationCodeDiV = document.getElementById('activation-code-div');
let activationCodeForm = document.getElementById('activation-code-form');
let otpInput = document.getElementById('otp');
let activationFormButton = document.getElementById('activation-code-form-btn');
///

let registerObj = {
    username: "", password: "", name: "", family: "", profilePicture: "", email: ""
};

registerDoneMessageDiv.hidden = true;

//activation
let userId = -1;

submitBtn.onclick = function (e) {
    if (!validateInputs()) {
        return;
    }

    //body
    registerObj.email = emailInput.value;
    registerObj.username = usernameInput.value;
    registerObj.password = passwordInput.value;
    registerObj.name = nameInput.value;
    registerObj.family = familyInput.value;
    let response
    (async () => {
        await fetch("/pub/user/", {
            method: "POST", headers: {
                Accept: "application/json", "Content-Type": "application/json"
            }, body: JSON.stringify(registerObj)
        }).then(resp => {
            response = resp;
            return resp.json();
        }).then(json => userId = json);

        let responseCode = JSON.stringify(response.status);
        console.log(responseCode);
        if (responseCode === '200') {
            usernameInput.value = '';
            emailInput.value = '';
            passwordInput.value = '';
            nameInput.value = '';
            familyInput.value = '';
            profilePictureFileInput.value = '';
            userRegisterDiv.hidden = true;
            registerDoneMessageDiv.hidden = true;
            activationCodeDiV.hidden = false;
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

    let uploadResponse;
    (async () => {
        await fetch("/pub/files/upload", {
            method: "POST", body: fileFormData
        })
            .then(e => e.text())
            .then(text => {
                profilePictureImg.setAttribute('src', text);
                registerObj.profilePicture = text;
                uploadResponse = text;
                return text;
            });
    })();
}

let uploadProfilePictureBtn = document.getElementById('upload-profile_picture_btn');
uploadProfilePictureBtn.onclick = () => {
    uploadFile(profilePictureFileInput, "/pub/profilePictures/");
};

/*************************************************ACTIVATION***********************************************************/
activationCodeDiV.hidden = true;

let activationObj = {
    otp: "",
    userId: -1
};

activationFormButton.onclick = (e) => {
    e.preventDefault();

    activationObj.userId = userId;
    activationObj.otp = otpInput.value;

    console.log("otp : " + activationObj.otp)
    console.log('activationObj : ' + JSON.stringify(activationObj));
    let activationResponse;
    (async () => {
        activationResponse = await fetch("/pub/user/enable", {
            method: "POST", headers: {
                Accept: "application/json", "Content-Type": "application/json"
            }, body: JSON.stringify(activationObj)
        });

        let responseCode = JSON.stringify(activationResponse.status);
        console.log('activation response : ' + responseCode);
        if (responseCode === '200') {
            activationCodeDiV.hidden = true;
            registerDoneMessageDiv.hidden = false;
            registerDoneMessageDiv.style.color = 'green';
            registerDoneMessageDiv.innerText = 'حساب کاربری با موفقیت فعال شد.'
        } else if (responseCode === '400') {
            registerDoneMessageDiv.hidden = false;
            registerDoneMessageDiv.style.color = 'red';
            registerDoneMessageDiv.innerText = 'داده های ارسالی معتبر نمی باشد'
        } else if (responseCode === '500') {
            registerDoneMessageDiv.hidden = false;
            registerDoneMessageDiv.style.color = 'red';
            registerDoneMessageDiv.innerText = 'خطای سرور'
        }
        // .then(e => e.text())
        // .then(text => {});
    })();
}