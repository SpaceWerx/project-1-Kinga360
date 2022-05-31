function logout(){
    localStorage.removeItem("current-user");
    window.location.href = "Login.html";
}


document.getElementById("submit-button").addEventListener("click",attemptSubmit);

async function attemptSubmit(){
    console.log(10);
    var username = sessionStorage.getItem("usern");
    console.log(username);
    const url = "http://localhost:3000/"
    const type = document.getElementById("typeInput").value;
    
   
    const description = document.getElementById("description").value;
    const amount = document.getElementById("amount").value;

    const userID = "3";
    
    if (!userID){
        window.location.href = "Login.html";
    }
    else if(description ==""){
        //const messageDiv = document.getElementById("message");
       // messageDiv.hidden = false;
       // messageDiv.innerText = "Cannot submit a request without a description, please specify your reason.";
       console.log("Cannot submit a request without a description, please specify your reason.");
    }
    else if (amount ==""){
        const messageDiv= document.getElementById("message");
       // messageDiv.hidden = false;
        messageDiv.innerText = "Please specify an amount you need to be reimbursed";
    }else{
       
        const reimbursement = {id:0, author:userID, resolver:0, Description:description,type:type,amount:amount};
        console.lo
        
        let response = await fetch(url + "reimbursement",{
            method: "POST", //This is what we are doing, we are created an employee, so it is a post request
            body: JSON.stringify(reimbursement), //This will turn our user object into JSON we can send
            credentials: "include"
        });
    
        
        if (response.status == 208){
            console.log(response.status + "Reimbursement Successful.");
           // window.location.href = "Employee.html";
        }
        
    }
}
 async function submitSuccessful(xhr){
    const messageDiv= document.getElementById("message");
    messageDiv.hidden = false;
   
}
async function submitSuccessful(xhr){
    const messageDiv= document.getElementById("message");
    messageDiv.hidden = false;
    messageDiv.innerText = 'Sorry reimbursment has failed.';
}