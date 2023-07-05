//******************************************************************* upload
// function uploadFile(fileInput, subDirectories) {
//     let file = fileInput.files[0];
//     console.log("subDirectories :" + subDirectories);
//
//     let fileFormData = new FormData();
//     fileFormData.set("file", file);
//     fileFormData.set("subDirectories", subDirectories);
//
//     let response;
//     (async () => {
//         response = await fetch("/pub/files/upload", {
//             method: "POST",
//             body: fileFormData
//         })
//             .then(e => e.text())
//             .then(response => {
//                 profilePictureImg.setAttribute('src', response);
//                 return response;
//             });
//     })();
//
//     console.log("file response : " + response);
// }