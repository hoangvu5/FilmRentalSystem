$(document).ready(function() {
    $('#register-form').on('submit', function(event) {
        event.preventDefault();
        submitRegisterForm();
    });

    $('#login-form').on('submit', function(event) {
        event.preventDefault();
        submitLoginForm();
    });
});

function appendSuccessMessage(message) {
    const messageDiv = $('<div></div>')
        .addClass('alert alert-success alert-dismissible fade show')
        .html(`<button type="button" class="btn-close" data-bs-dismiss="alert"></button>${message}`);
    $('#warnings').append(messageDiv);
}

function appendErrorMessage(message) {
    const messageDiv = $('<div></div>')
        .addClass('alert alert-danger alert-dismissible fade show')
        .html(`<button type="button" class="btn-close" data-bs-dismiss="alert"></button>${message}`);
    $('#warnings').append(messageDiv);
}

function submitRegisterForm() {
    const form = $('#register-form')[0];
    const formData = new FormData(form);
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    $.ajax({
        url: '/auth/register',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(jsonData),
        success: function(data) {
            appendSuccessMessage('Registration successful');
            window.location.href = data.redirectUrl;
        },
        error: function(xhr) {
            appendErrorMessage(xhr.responseJSON.message || 'Registration failed');
        }
    })
}

function submitLoginForm() {
    const form = $('#login-form')[0];
    const formData = new FormData(form);
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    $.ajax({
        url: '/auth/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(jsonData),
        success: function(data) {
            appendSuccessMessage('Login successful');
        },
        error: function(xhr) {
            appendErrorMessage('Incorrect username or password');
        }
    })
}