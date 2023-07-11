let messageProvidersTable = document.getElementById('message-providers-table');
let messageProvidersTableBody = document.getElementById('message-providers-table-body');
let createBtn = document.getElementById('create-btn');

let GET_ALL = "/adm/message/provider/";
let DELETE = "/adm/message/provider/";

// ++++++++++++++++++++++++++++++++++++Refresh++++++++++++++++++++++++++++++++++++++++++
function refreshTable() {
    (async () => {
        let response = fetch(GET_ALL, {
            method: "GET", headers: {}
        }).then(response => response.json())
            .then(json => {
                fillTable(JSON.parse(JSON.stringify(json)));
            });

        // let responseCode = JSON.stringify(response.status);
        // if (responseCode === '400') {
        //     console.log('error in retrieving data');
        // }
    })();


}

// ++++++++++++++++++++++++++++++FilleTableWithData+++++++++++++++++++++++++++++++++++++
function fillTable(data) {
    //
    while (messageProvidersTableBody.firstChild) {
        messageProvidersTableBody.removeChild(messageProvidersTableBody.lastChild);
    }

    for (let i = 0; i < data.length; i++) {
        let tr = document.createElement('tr');
        for (let key in data[i]) {
            let td = document.createElement('td');
            td.innerText = data[i][key];
            tr.appendChild(td);
        }
        //buttons
        let deleteButtonTd = document.createElement('td');

        let deleteButton = document.createElement('button');
        deleteButton.innerText = 'حذف';
        deleteButton.onclick = () => {
            deleteItem(data[i].id)
        }
        deleteButton.className = "btn btn-danger";
        deleteButtonTd.appendChild(deleteButton);

        tr.appendChild(deleteButtonTd);
        messageProvidersTableBody.appendChild(tr);
    }
}

// +++++++++++++++++++++++++++++++++Delete Item++++++++++++++++++++++++++++++++++++++++
function deleteItem(id) {
    let response = fetch(DELETE + id, {
        method: "DELETE", headers: {}
    })

    let responseCode = JSON.stringify(response.status);
    if (responseCode === '200') {
        console.log('item removed');
    }
}

refreshTable();

// +++++++++++++++++++++++++++++++++Create button++++++++++++++++++++++++++++++++++++++++

createBtn.onclick = (e) => {
    window.location.href = "/view/adm/messaging/provider/create";
}