let user;
let courses;
let addta_form = document.getElementById('addtaform');
let listta_form = document.getElementById('listtaform');
let delta_form = document.getElementById('deltaform');

window.onload = function(){
    loginTest();
   fetch_courses();
   hideForms();
   hideAlerts();
   showListTAform();
}

function loginTest(){

    if(localStorage.getItem('id')==undefined){
        location.href = "index.html";
    }else{
        console.log(localStorage.getItem('id'));
        // console.log(localStorage.getItem('user'));
        user = JSON.parse(localStorage.getItem('user'));
    }

}

async function fetch_courses(){

    // http://example.com/myContextRoot/resources/{name1}/{name2}/
    let response = await fetch("api/emp/getCourses/"+user["employee_id"]+"/"+user["email"]);
    courses = await response.json(); // read response body and parse as JSON
    console.log(courses);
    let courses_option_add = document.getElementById('addta_courses');
    let courses_option_list = document.getElementById('listta_courses');
    let courses_option_del = document.getElementById('delta_courses');


    courses_option_add.innerHTML = '<option value=""> Choose...</option>';
    courses_option_list.innerHTML = '<option value=""> Choose...</option>';
    courses_option_del.innerHTML = '<option value=""> Choose...</option>';

    for(let i = 0 ; i<courses.length ; i++){
        courses_option_add.innerHTML += '<option value="'+courses[i]['course_id']+'">'+courses[i]['course_code']+' '+courses[i]['name']+'</option>';
        courses_option_list.innerHTML += '<option value="'+courses[i]['course_id']+'">'+courses[i]['course_code']+' '+courses[i]['name']+'</option>';
        courses_option_del.innerHTML += '<option value="'+courses[i]['course_id']+'">'+courses[i]['course_code']+' '+courses[i]['name']+'</option>';
    }


}

addta_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    document.getElementById("addtadiv-success").style.display = "none";
    document.getElementById("addtadiv-error").style.display = "none";
    if (addta_form.checkValidity() === true) {
        let response = await fetch('api/emp/addTA', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                course_id: document.getElementById('addta_courses').value,
                students: [{ 'roll_number' :document.getElementById('addta_rollno').value}],
            })
        });
        let result = await response.json();
        console.log(result);
        addta_form.reset();
        if(result['success']){
            document.getElementById("addtadiv-success").style.display = "block";
            document.getElementById("addtadiv-success-msg").innerText = result['success'];

        } else if(result['error']){
            document.getElementById("addtadiv-error").style.display = "block";
            document.getElementById("addtadiv-error-msg").innerText = result['error'];
        }
    }else{
        addta_form.classList.add('was-validated');
    }
});

listta_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    document.getElementById("listtadiv-success").style.display = "none";
    document.getElementById("listtadiv-error").style.display = "none";
    if (listta_form.checkValidity() === true) {
        let response = await fetch('api/emp/listTA', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                course_id: document.getElementById('listta_courses').value,
            })
        });
        let result = await response.json();
        console.log(result);
        listta_form.reset();
        if(result["error"]){
            document.getElementById("listtadiv-error").style.display = "block";
            document.getElementById("listtadiv-error-msg").innerText = result['error'];
        }
        $('#listta-data').empty();

        var htmlText = result.map(function(o){
            return `
                         
                            <tr class="item-cart">
                            <td class="product-name" data-title="Name Product"><a href="#">${o.first_name}</a></td>
                            <td class="product-name" data-title="Price">${o.roll_number}</td>
                            <td class="product-name" data-title="Stock status">${o.email}</td>
                            </tr>
              `;
        });

        $('#listta-data').append(htmlText);

    }else{
        listta_form.classList.add('was-validated');
    }
});


delta_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    document.getElementById("deltadiv-success").style.display = "none";
    document.getElementById("deltadiv-error").style.display = "none";
    if (delta_form.checkValidity() === true) {
        let response = await fetch('api/emp/delTA', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                course_id: document.getElementById('delta_courses').value,
                students: [{ 'roll_number' :document.getElementById('delta_rollno').value}],
            })
        });
        let result = await response.json();
        delta_form.reset();
        if(result['success']){
            document.getElementById("deltadiv-success").style.display = "block";
            document.getElementById("deltadiv-success-msg").innerText = result['success'];

        } else if(result['error']){
            document.getElementById("deltadiv-error").style.display = "block";
            document.getElementById("deltadiv-error-msg").innerText = result['error'];
        }
        console.log(result);
    }else{
        addta_form.classList.add('was-validated');
    }
});



document.getElementById('logout').onclick = function(e){
    sessionStorage.setItem('id',undefined);
    sessionStorage.setItem('user', undefined);
    location.href = "index.html";
}
document.getElementById('addTa').onclick = function(e){

    hideForms();
    hideAlerts();
    document.getElementById("addtadiv").style.display = "block";
}
document.getElementById('delTa').onclick = function(e){

    hideForms();
    hideAlerts();
    document.getElementById("deltadiv").style.display = "block";
}
document.getElementById('listTa').onclick = function(e){

    hideForms();
    hideAlerts();
    document.getElementById("listtadiv").style.display = "block";
}
function hideForms(){
    document.getElementById("listtadiv").style.display = "none";
    document.getElementById("addtadiv").style.display = "none";
    document.getElementById("deltadiv").style.display = "none";

}
function hideAlerts(){
    document.getElementById("addtadiv-success").style.display = "none";
    document.getElementById("addtadiv-error").style.display = "none";
    document.getElementById("deltadiv-success").style.display = "none";
    document.getElementById("deltadiv-error").style.display = "none";
    document.getElementById("listtadiv-success").style.display = "none";
    document.getElementById("listtadiv-error").style.display = "none";

}
function showListTAform(){
    document.getElementById("listtadiv").style.display = "block";

}



