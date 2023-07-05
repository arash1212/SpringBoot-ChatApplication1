let usernameInput = document.getElementById('username');
let userProfileImage = document.getElementById('user-profile-img');

let userId = -1;
let username = "";

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
userId = urlParams.get('id');
username = urlParams.get('username');

let userInfo = {
    id: -1,
    username: "",
    createdAt: "",
    profilePicture: ""

};

(async () => {
    if (username == null || username === '') {
        return;
    }
    let response = await fetch("/pub/user/by-username?username=" + username, {
        method: "GET"
    }).then(response => response.json())
        .then(json => {
            console.log(json);

            userInfo.id = json.id;
            userInfo.username = json.username;
            userInfo.createdAt = json.createdAt;
            userInfo.profilePicture = json.profilePicture;
        });

    usernameInput.innerText = 'پروفایل : ' + userInfo.username;
    userProfileImage.src = userInfo.profilePicture;
})();

