let createForm = document.getElementById('authority-create-form');
let authorityInput = document.getElementById('authority');
let formSubmitButton = document.getElementById('submit-button');
let operationResultDiv = document.getElementById('operation-result-div');


operationResultDiv.hidden = true;

function create() {
    let createObj = {
        authority: ""
    };

    // ++++++++++++++++++fill create obj++++++++++++++++

    createObj.authority = authorityInput.value;

    // +++++++++++++++++++Fetch++++++++++++++++++++++++++
    let response;
    (async () => {
        response = await fetch('/adm/user/authority/', {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(createObj)

        });

        console.log(response);
        // ++++++++++++++++Check response++++++++++++++++++++
        if (response.status === 200) {
            createForm.hidden = true;
            operationResultDiv.hidden = false;
            operationResultDiv.style.color = 'black';
            operationResultDiv.innerText = 'اختیارات جدید با موفقیت ثبت شد';
        }else if(response.status === 500){
            operationResultDiv.hidden = false;
            operationResultDiv.style.color = 'red';
            operationResultDiv.innerText = 'خطا سرور';
        }else{
            operationResultDiv.hidden = false;
            operationResultDiv.style.color = 'red';
            operationResultDiv.innerText = 'نامشخص';
        }
    })();
}

// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
formSubmitButton.onclick = (e) => {
    e.preventDefault();
    create();
}

