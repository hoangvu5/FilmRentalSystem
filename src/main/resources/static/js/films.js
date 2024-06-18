$(document).ready(function() {
    const API_URL = 'http://localhost:8080/api/v1/films';

    // Fetch Films
    $('#fetchFilms').click(function() {
        $.ajax({
            url: API_URL,
            type: 'GET',
            success: function(films) {
                populateFilmTable(films);
            },
            error: function(error) {
                console.error('Error fetching films:', error);
            }
        });
    });

    // Function to populate the film table
    function populateFilmTable(films) {
        let tableRows = '';
        films.forEach(film => {
            tableRows += `<tr>
                            <td>${film.filmId}</td>
                            <td>${film.title}</td>
                            <td>${film.description}</td>
                            <td>
                                <button class="btn btn-sm btn-warning" onclick="editFilm(
                                    ${film.filmId},
                                    '${film.title}',
                                    '${film.description}',
                                    ${film.releaseYear},
                                    ${film.replacementCost},
                                    ${film.rentalDuration},
                                    '${film.fullText}',
                                    ${film.rating},
                                    '${film.specialFeatures}',
                                    ${film.rentalRate},
                                    ${film.length}
                                )">Edit</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteFilm(${film.filmId})">Delete</button>
                            </td>
                        </tr>`;
        });
        $('#filmTableBody').html(tableRows);
    }

    // Create Film
    $('#createFilmForm').submit(function(event) {
        event.preventDefault();

        const film = {
            title: $('#title').val(),
            description: $('#description').val(),
            releaseYear: $('#releaseYear').val(),
            replacementCost: $('#replacementCost').val(),
            rentalDuration: $('#rentalDuration').val(),
            fullText: $('#fullText').val(),
            rating: $('#rating').val(),
            specialFeatures: $('#specialFeatures').val(),
            rentalRate: $('#rentalRate').val(),
            length: $('#length').val(),
            lastUpdate: new Date().toISOString()
        };

        $.ajax({
            url: API_URL,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(film),
            success: function(newFilm) {
                alert(`Film created with ID: ${newFilm.filmId}`);
                $('#createFilmForm')[0].reset();
                $('#fetchFilms').click();
            },
            error: function(error) {
                console.error('Error creating film:', error);
            }
        });
    });

    // Edit Film
    window.editFilm = function(id, title, description, releaseYear, replacementCost, rentalDuration, fullText, rating, specialFeatures, rentalRate, length) {
        $('#updateId').val(id);
        $('#updateTitle').val(title);
        $('#updateDescription').val(description);
        $('#updateReleaseYear').val(releaseYear);
        $('#updateReplacementCost').val(replacementCost);
        $('#updateRentalDuration').val(rentalDuration);
        $('#updateFullText').val(fullText);
        $('#updateRating').val(rating);
        $('#updateSpecialFeatures').val(specialFeatures);
        $('#updateRentalRate').val(rentalRate);
        $('#updateLength').val(length);
        $('#updateModal').modal('show');
    };

    // Update Film
    $('#updateFilmForm').submit(function(event) {
        event.preventDefault();

        const id = $('#updateId').val();
        const film = {
            filmId: $('#updateId').val(),
            title: $('#updateTitle').val(),
            description: $('#updateDescription').val(),
            releaseYear: $('#updateReleaseYear').val(),
            replacementCost: $('#updateReplacementCost').val(),
            rentalDuration: $('#updateRentalDuration').val(),
            fullText: $('#updateFullText').val(),
            rating: $('#updateRating').val(),
            specialFeatures: $('#updateSpecialFeatures').val(),
            rentalRate: $('#updateRentalRate').val(),
            length: $('#updateLength').val(),
            lastUpdate: new Date().toISOString()
        };

        console.log(JSON.stringify(film, null, 2));
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(film),
            success: function(updatedFilm) {
                alert(`Film updated with ID: ${updatedFilm.filmId}`);
                $('#updateModal').modal('hide');
                $('#fetchFilms').click();
            },
            error: function(error) {
                console.error('Error updating film:', error);
            }
        });
    });

    // Delete Film
    window.deleteFilm = function(id) {
        if (confirm('Are you sure you want to delete this film?')) {
            $.ajax({
                url: `${API_URL}/${id}`,
                type: 'DELETE',
                success: function(response) {
                    alert(response);
                    $('#fetchFilms').click(); // Refresh table after deletion
                },
                error: function(error) {
                    console.error('Error deleting film:', error);
                }
            });
        }
    };
});