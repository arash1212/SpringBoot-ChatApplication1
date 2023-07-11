let chatSearchDiv = document.getElementById('chat-search-div');
let chatSearchForm = document.getElementById('chat-search-form');
let chatSearchInput = document.getElementById('chat-search-input');
let chatSearchTale = document.getElementById('chat-search-result-table');
let chatSearchResultBody = document.getElementById('chat-search-result-table-body');
// ++++++++++++++++++++++++++++++++++++GetChats++++++++++++++++++++++++++++++++++++++++++
function getChats() {
    let response;
    fetch('/pub/chat/', {
        method: 'GET'
    }).then(resp => {
        response = resp.status;
        console.log('response : ' + response);
        return resp.json();
    })
        .then(json => {
            console.log(JSON.stringify(json));
            fillTable(JSON.parse(JSON.stringify(json)));
        });
}

chatSearchInput.onchange = (e) => {
    getChats();
}

function fillTable(data) {
    //removeChildren
    while (chatSearchResultBody.firstChild) {
        chatSearchResultBody.removeChild(chatSearchResultBody.lastChild);
    }

    let tr = document.createElement('tr');
    for (let i = 0; i < data.length; i++) {
        for (let j = 0; j <= 2; j++) {
            let td = document.createElement('td');
            if (j === 0) {
                td.innerText = data[i]['id'];
            } else if (j === 1) {
                td.innerText = data[i]['title'];
            } else if (j === 2) {
                td.innerText = data[i]['createdAt'];
            }
            tr.appendChild(td);
        }
    }
    chatSearchResultBody.appendChild(tr);
}