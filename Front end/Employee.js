

document.getElementById("view-reimbursements-anchor").addEventListener("click",viewReimbursements);

async function viewReimbursements(){
    var userName = sessionStorage.getItem("usern");
    var userPassword = sessionStorage.getItem("userp");
    console.log(userName);
    console.log(userPassword);
    const url = "http://localhost:3000/"
    let user = {
        userName: userName,
        Password: userPassword
    }
    let response = await fetch(url + "getReimbursementByUserName",{
        method: "POST", //This is what we are doing, we are created an employee, so it is a post request
        body: JSON.stringify(user), //This will turn our user object into JSON we can send
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
{/* <td>$[data[i].ID]</td>
                        <td>'$[d[i].Description]'</td>
                        <td>'$[d[i].type]'</td>
                        <td>'$[d[i].amount]'</td>
                        <td>'$[d[i].status]'</td>  */}