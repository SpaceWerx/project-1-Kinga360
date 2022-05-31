const url = "http://localhost:3000/";
document.getElementById("view-all-pending-button-anchor").addEventListener("click", pendingFunction);
document.getElementById("view-all-resolved-button-anchor").addEventListener("click", resolvedFunction);
document.getElementById("process-button-anchor").addEventListener("click", processFunction);
async function pendingFunction(){
    
    let response = await fetch(url + "getReimbursementByStatus",{
        method: "POST", //This is what we are doing, we are created an employee, so it is a post request
        body: JSON.stringify("Pending"), //This will turn our user object into JSON we can send
        credentials: "include"
    });
    const data = await response.json();
     let d = JSON.stringify(data); 
     let dd = JSON.parse(d);
     console.log(dd[0].ID);
     
    
   
    //  let response2 = await fetch(url + "getReimbursementByID",{
    //      method: "POST", //This is what we are doing, we are created an employee, so it is a post request
    //      body: JSON.stringify(ID), //This will turn our user object into JSON we can send
    //      credentials: "include"
    //  });
    //   const description1 =  JSON.stringify(response2);
    //   const description = description1.Description;
    //  console.log(description);
     let table = document.getElementById("display-table");
     
     for (var i = 0; i < (dd.length /2); i++){
         var row = `<td>${dd[i].ID}</td>
         <td>${dd[i].Description}</td>
         <td>${dd[i].type}</td>
         <td>${'$' + dd[i].amount}</td>
         <td>${dd[i].status}</td>`
            table.innerHTML += row;
           
     }
}
async function resolvedFunction(){
    
    let response = await fetch(url + "getReimbursementByStatus",{
        method: "POST", //This is what we are doing, we are created an employee, so it is a post request
        body: JSON.stringify("Approved"), //This will turn our user object into JSON we can send
        credentials: "include"
    });
    const data = await response.json();
    let d = JSON.stringify(data); 
    let dd = JSON.parse(d);
    console.log(dd[0].ID);
    
   
  
   //  let response2 = await fetch(url + "getReimbursementByID",{
   //      method: "POST", //This is what we are doing, we are created an employee, so it is a post request
   //      body: JSON.stringify(ID), //This will turn our user object into JSON we can send
   //      credentials: "include"
   //  });
   //   const description1 =  JSON.stringify(response2);
   //   const description = description1.Description;
   //  console.log(description);
    let table = document.getElementById("display-table");
    
    for (var i = 0; i < (dd.length /2); i++){
        var row = `<td>${dd[i].ID}</td>
        <td>${dd[i].Description}</td>
        <td>${dd[i].type}</td>
        <td>${'$' + dd[i].amount}</td>
        <td>${dd[i].status}</td>`
           table.innerHTML += row;
          
    }
}
async function processFunction(){
    window.location.href = "process.html";
}