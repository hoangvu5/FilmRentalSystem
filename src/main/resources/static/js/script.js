document.addEventListener('DOMContentLoaded', function() {

});

function appendSuccessMessage(message) {
    warnings_div = document.querySelector('#warnings');
    message_div = document.createElement('div');
    message_div.className = 'alert alert-success alert-dismissible fade show';
    message_div.innerHTML = `
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        ${message}
    `
    warnings_div.appendChild(message_div);
}

function appendErrorMessage(message) {
    warnings_div = document.querySelector('#warnings');
    message_div = document.createElement('div');
    message_div.className = 'alert alert-danger alert-dismissible fade show';
    message_div.innerHTML = `
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        ${message}
    `
    warnings_div.appendChild(message_div);
}

function submitRegisterForm() {
    const form = document.getElementById('register-form');
    const formData = new FormData(form);
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
    .then(response => {
        return response.json().then(data => ({
            status: response.status,
            ok: response.ok,
            data: data
        }));
    })
    .then(({ status, ok, data }) => {
        if (ok) {
            console.log('Registration successful');
            appendSuccessMessage('Registration successful');
            window.location.href = data.redirectUrl;
        } else {
            console.error('Registration failed');
            appendErrorMessage(data.error || 'Registration failed');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        appendErrorMessage('Registration failed');
    });
}

function submitLoginForm() {
    const form = document.getElementById('login-form');
    const formData = new FormData(form);
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
    .then(response => {
        if (response.ok) {
            console.log('Login successful');
            appendSuccessMessage('Login successful');
        } else {
            console.error('Login failed');
            appendErrorMessage('Login failed');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        appendErrorMessage('Login failed');
    });
}