let authoritiesTable = document.getElementById('authorities-table');

function refreshAuthoritiesTable() {
    fetch("/adm/user/authority/", {
        method: "GET", headers: {}
    }).then(response => response.json())
        .then(json => {
            console.log("authority : ", JSON.parse(JSON.stringify(json)))
            fillTable(JSON.parse(JSON.stringify(json)));
            console.log("id" + JSON.parse(JSON.stringify(json))[0].id)
        });
}

function fillTable(data) {
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
        deleteButton.onclick = ()=>{deleteItem(data[i].id)}
        deleteButton.className = "btn btn-danger";
        deleteButtonTd.appendChild(deleteButton);

        tr.appendChild(deleteButtonTd);
        authoritiesTable.appendChild(tr);
    }
}

function deleteItem(id) {
    let response = fetch("/adm/user/authority/" + id, {
        method: "DELETE", headers: {}
    })

    let responseCode = JSON.stringify(response.status);
    if (responseCode === '200') {
        console.log('item removed');
    }
}

refreshAuthoritiesTable();