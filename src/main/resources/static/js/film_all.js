$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/films', // Replace with your API endpoint
        method: 'GET',
        dataType: 'json',
        success: function(data) {
            data.forEach(function(film) {
                renderFilmData(film);
            });
        },
        error: function(error) {
            console.error('Error fetching data:', error);
        }
    });

    function renderFilmData(film) {
        var filmContainer = $('#film-container');

        var filmHtml = `
            <div class="film">
                <h1>${film.title || 'N/A'}</h1>
                <p><strong>Description:</strong> ${film.description || 'N/A'}</p>
                <p><strong>Release Year:</strong> ${film.releaseYear || 'N/A'}</p>
                <p><strong>Rental Duration:</strong> ${film.rentalDuration || 'N/A'} days</p>
                <p><strong>Rental Rate:</strong> $${film.rentalRate || 'N/A'}</p>
                <p><strong>Length:</strong> ${film.length || 'N/A'} minutes</p>
                <p><strong>Replacement Cost:</strong> $${film.replacementCost || 'N/A'}</p>
                <p><strong>Rating:</strong> ${film.rating || 'N/A'}</p>
                <p><strong>Last Update:</strong> ${film.lastUpdate || 'N/A'}</p>
                <p><strong>Special Features:</strong> ${film.specialFeatures || 'N/A'}</p>
                <h2>Language</h2>
                <p><strong>Name:</strong> ${film.language ? film.language.name : 'N/A'}</p>
                <p><strong>Last Update:</strong> ${film.language ? film.language.lastUpdate : 'N/A'}</p>
                <h2>Inventories</h2>
        `;

        if (film.inventories && film.inventories.length > 0) {
            film.inventories.forEach(function(inventory) {
                filmHtml += `
                    <div>
                        <p><strong>Inventory ID:</strong> ${inventory.inventoryId || 'N/A'}</p>
                        <p><strong>Last Update:</strong> ${inventory.lastUpdate || 'N/A'}</p>
                        <h3>Rentals</h3>
                `;

                if (inventory.rentals && inventory.rentals.length > 0) {
                    inventory.rentals.forEach(function(rental) {
                        filmHtml += `
                            <div>
                                <p><strong>Rental ID:</strong> ${rental.rentalId || 'N/A'}</p>
                                <p><strong>Rental Date:</strong> ${rental.rentalDate || 'N/A'}</p>
                                <p><strong>Return Date:</strong> ${rental.returnDate || 'N/A'}</p>
                                <p><strong>Last Update:</strong> ${rental.lastUpdate || 'N/A'}</p>
                                <h4>Customer</h4>
                                <p><strong>First Name:</strong> ${rental.customer ? rental.customer.firstName : 'N/A'}</p>
                                <p><strong>Last Name:</strong> ${rental.customer ? rental.customer.lastName : 'N/A'}</p>
                                <p><strong>Active:</strong> ${rental.customer ? rental.customer.active : 'N/A'}</p>
                                <p><strong>Created Date:</strong> ${rental.customer ? rental.customer.createdDate : 'N/A'}</p>
                                <p><strong>Last Update:</strong> ${rental.customer ? rental.customer.lastUpdate : 'N/A'}</p>
                            </div>
                        `;
                    });
                } else {
                    filmHtml += `<p>No rentals available.</p>`;
                }

                filmHtml += `</div>`;
            });
        } else {
            filmHtml += `<p>No inventories available.</p>`;
        }

        filmHtml += `<h2>Categories</h2>`;
        if (film.filmCategories && film.filmCategories.length > 0) {
            film.filmCategories.forEach(function(filmCategory) {
                filmHtml += `
                    <div>
                        <p><strong>Category Name:</strong> ${filmCategory.category ? filmCategory.category.name : 'N/A'}</p>
                        <p><strong>Last Update:</strong> ${filmCategory.lastUpdate || 'N/A'}</p>
                    </div>
                `;
            });
        } else {
            filmHtml += `<p>No categories available.</p>`;
        }

        filmHtml += `<h2>Actors</h2>`;
        if (film.filmActors && film.filmActors.length > 0) {
            film.filmActors.forEach(function(filmActor) {
                filmHtml += `
                    <div>
                        <p><strong>Actor Name:</strong> ${filmActor.actor ? filmActor.actor.firstName + ' ' + filmActor.actor.lastName : 'N/A'}</p>
                        <p><strong>Last Update:</strong> ${filmActor.lastUpdate || 'N/A'}</p>
                    </div>
                `;
            });
        } else {
            filmHtml += `<p>No actors available.</p>`;
        }

        filmHtml += `</div>`; // Close the film div

        filmContainer.append(filmHtml);
    }
});
