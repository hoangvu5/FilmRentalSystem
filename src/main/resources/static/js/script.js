$(document).ready(function() {
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