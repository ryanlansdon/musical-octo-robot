let baseUrl = '/Project1';
let mainDiv = document.getElementById("mainDiv");
let loggedInUser = null;
let role = null;
checkLogin();
setMain();

function setMain() {
    mainDiv.innerHTML = `
        <h2>Welcome to PitchIt</h2><br><br>
    `;
    if (!loggedInUser) {
        mainDiv.innerHTML += `
            <form class="form">
                <button type="button" class="button" id="loginBtn">Log In</button><br>
                <button type-"button" class="button" id="registerBtn">Register</button>
            </form>
        `;
        let loginBtn = document.getElementById("loginBtn");
        loginBtn.onclick = loginDisplay;
        let registerBtn = document.getElementById("registerBtn");
        registerBtn.onclick = registerDisplay;
    } else {
        mainDiv.innerHTML += `
            <h3>Successfully logged in!</h3>
        `;
        if (role == "author") {
            mainDiv.innerHTML += `
                <a href="myPitches.html"><strong>My Pitches</strong></a>
            `;
        } else {

        }
    }
}

function loginDisplay() {
    mainDiv.innerHTML = `
        <h2>Welcome to PitchIt</h2><br><br>
        <form class="form">
            <label for="username">Username: </label><br>
            <input id="username" name="username" type="text" /><br><br>
            <label for="pass">Password: </label><br>
            <input id="pass" name="pass" type="password" /><br><br>
            <input type="radio" name="role" value="author">Author
            <input type="radio" name="role" value="editor">Editor<br><br>
            <button type="button" class="button" id="loginBtn">Log In</button>
        </form>
    `;

    let loginBtn = document.getElementById("loginBtn");
    if (!loggedInUser) loginBtn.onclick = login;
}

function registerDisplay() {
    mainDiv.innerHTML = `
        <h2>Welcome to PitchIt</h2><br><br>
        <form class="form">
            <label for="username">Username: </label><br>
            <input id="username" name="username" type="text" /><br><br>
            <label for="pass">Password: </label><br>
            <input id="pass" name="pass" type="password" /><br><br>
            <label for="firstName">First Name: </label><br>
            <input id="firstName" name="firstName" type="text" /><br><br>
            <label for="lastName">Last Name: </label><br>
            <input id="lastName" name="lastName" type="text" /><br><br>
            <button type="button" class="button" id="registerBtn">Register</button>
        </form>
    `;

    let registerBtn = document.getElementById("registerBtn");
    if (!loggedInUser) registerBtn.onclick = register;
}

async function login() {
    let url = baseUrl + '/user/login?';
    url += 'user=' + document.getElementById("username").value + '&';
    url += 'pass=' + document.getElementById("pass").value + '&';
    role = document.querySelector('input[name="role"]:checked').value;
    url += 'role=' + role;
    let response = await fetch(url, {method: 'POST'});

    switch (response.status) {
        case 200: // successful
            loggedInUser = await response.json();
            console.log(loggedInUser);
            setMain();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('user').value = '';
            document.getElementById('pass').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function register() {
    let author = {user: {}};
    author.user.username = document.getElementById("username");
    author.user.password = document.getElementById("password");
    author.user.firstName = document.getElementById("firstName");
    author.user.lastName = document.getElementById("lastName");
    role = "author";

    let url = baseUrl + '/user';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(author)});
    
    switch (response.status) {
        case 200: // successful
            loggedInUser = await response.json();
            console.log(loggedInUser);
            setMain();
            break;
        case 409: // Username already taken
            alert('That username is already taken, try again.');
            document.getElementById('username').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function logout() {
    let url = baseUrl + '/user';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedInUser = null();
    setMain();
}

async function checkLogin() {
    let url = baseUrl + '/user';
    let response = await fetch(url);
    if (response.status === 200) loggedInUser = await response.json();
    setMain();
}