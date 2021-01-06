let login_form = document.getElementById('login-validation');
document.getElementById("email").value = "murali@iiitb.ac.in";
//murali@iiitb.ac.in rajat@iiitb.org
login_form.addEventListener('submit', async (e) => {

    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        document.getElementById("login-alert").style.display = "none";
        let response = await fetch('api/login/user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                email: document.getElementById('email').value,
            })
        });
        if (response.ok) { // if HTTP-status is 200-299
                           // get the response body (the method explained below)
            let result = await response.json();
            console.log(result);
            console.log(result["student_id"])
            console.log(result["employee_id"])
            if(result["student_id"] != undefined){
                    localStorage.setItem('id', result["student_id"]);
                    localStorage.setItem('user',JSON.stringify(result));
                    location.href = "student.html";
            } else if(result["employee_id"] != undefined){
                 localStorage.setItem('id', result["employee_id"]);
                localStorage.setItem('user', JSON.stringify(result));

                    location.href = "employee.html";
            } else{

                document.getElementById("login-alert").style.display = "block";

            }

        } else {

             document.getElementById("login-alert").style.display = "block";

        }


    }
});