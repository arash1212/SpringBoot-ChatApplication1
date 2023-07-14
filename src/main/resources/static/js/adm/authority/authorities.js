let authoritiesTable = document.getElementById('authorities-table');
let authoritiesTableBody = document.getElementById('authorities-table-body');
let createBtn = document.getElementById('create-btn');

let GET_ALL = "/adm/user/authority/";
let DELETE = "/adm/user/authority/";

// ++++++++++++++++++++++++++++++++++++Refresh++++++++++++++++++++++++++++++++++++++++++

function refreshTable() {
    (async () => {
        let response = await fetch(GET_ALL, {
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
    while (authoritiesTableBody.firstChild) {
        authoritiesTableBody.removeChild(authoritiesTableBody.lastChild);
    }

    for (let i = 0; i < data.length; i++) {
        let tr = document.createElement('tr');
        // for (let key in data[i]) {
        for (let j = 0; j <= 3; j++) {
            let td = document.createElement('td');
            if (j === 0) {
                td.innerText = data[i]['id'];
            } else if (j === 1) {
                td.innerText = data[i]['authority'];
            } else if (j === 2) {
                td.innerText = data[i]['createdAt'];
            } else if (j === 3) {
                td.innerText = data[i]['lastUpdateAt'];
            }
            tr.appendChild(td);
        }
        // }
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
        authoritiesTableBody.appendChild(tr);
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
    } else if (responseCode === '400') {
        console.log('bad request');
    }
}

refreshTable();

// +++++++++++++++++++++++++++++++++Create button++++++++++++++++++++++++++++++++++++++++

createBtn.onclick = (e) => {
    window.location.href = "/view/adm/authority/create";
}