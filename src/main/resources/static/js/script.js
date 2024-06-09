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
        if (response.ok) {
            console.log('Registration successful');
            appendSuccessMessage('Registration successful');
        } else {
            console.error('Registration failed');
            appendErrorMessage('Registration failed');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        appendErrorMessage('Registration failed');
    });
}