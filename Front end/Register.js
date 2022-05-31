

const url = "http://localhost:3000/";
document.getElementById("back-button").addEventListener("click", backFunction);
document.getElementById("submit-button").addEventListener("click", registerFunction);

 
async function backFunction(){
    window.location.href = "Login.html";
}
 async function registerFunction(){
    let roles = document.getElementById("typeInput").value;
    let usernames = document.getElementById("createUsername").value;
    let passwords = document.getElementById("createPassword").value;
    console.log(usernames);
    console.log(passwords);
    console.log(roles);
    let user = {
        userName: usernames,
        Password: passwords,
        role: roles
    }
    console.log(user);
    
    
        let response = await fetch(url + "User",{
            method: "POST", //This is what we are doing, we are created an employee, so it is a post request
            body: JSON.stringify(user), //This will turn our user object into JSON we can send
            credentials: "include"
        });
       // var data = await response.json();
       // console.log(data);
    
}