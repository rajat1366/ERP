let liststu_form = document.getElementById('liststuform');
window.onload = function(){

    loginCheck();
    fetch_courses();
    hideAlerts();

}

async function fetch_courses(){

    // http://example.com/myContextRoot/resources/{name1}/{name2}/
    let response = await fetch("api/stu/getTACourses/"+user["student_id"]+"/");
    courses = await response.json(); // read response body and parse as JSON
    console.log(courses);
    let courses_option_add = document.getElementById('listta_courses');

    courses_option_add.innerHTML = '<option value=""> Choose...</option>';

    for(let i = 0 ; i<courses.length ; i++){
        courses_option_add.innerHTML += '<option value="'+courses[i]['course_id']+'">'+courses[i]['course_code']+' '+courses[i]['name']+'</option>';
    }
}
function loginCheck(){

    if(localStorage.getItem('id')==undefined){
        location.href = "index.html";
    }else{
        console.log(localStorage.getItem('id'));
        // console.log(localStorage.getItem('user'));
        user = JSON.parse(localStorage.getItem('user'));
    }

}

liststu_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    document.getElementById("liststudiv-success").style.display = "none";
    document.getElementById("liststudiv-error").style.display = "none";
    if (liststu_form.checkValidity() === true) {
        let response = await fetch('api/stu/getCourseStu', {
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
        liststu_form.reset();
        if(result["error"]){
            document.getElementById("liststudiv-error").style.display = "block";
            document.getElementById("liststudiv-error-msg").innerText = result['error'];
        }
        $('#listStu-data').empty();

        var htmlText = result.map(function(o){
            return `
                         
                            <tr class="item-cart">
                            <td class="product-name" data-title="Name Product"><a href="#">${o.student.first_name}</a></td>
                            <td class="product-name" data-title="Price">${o.student.roll_number}</td>
                            <td class="product-name" data-title="Stock status">${o.student.email}</td>
                            <td class="product-name" data-title="Stock status">${o.grade}</td>
                            </tr>
              `;
        });

        $('#listStu-data').append(htmlText);

    }else{
        liststu_form.classList.add('was-validated');
    }
});

document.getElementById('logout').onclick = function(e){
    sessionStorage.setItem('id',undefined);
    sessionStorage.setItem('user', undefined);
    location.href = "index.html";
}
function hideAlerts(){
    document.getElementById("liststudiv-success").style.display = "none";
    document.getElementById("liststudiv-error").style.display = "none";
}