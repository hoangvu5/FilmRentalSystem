$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/films', // Replace with your API endpoint
        method: 'GET',
        dataType: 'json',
        success: function(films) {
            populateFilmTable(films);
        },
        error: function(error) {
            console.error('Error fetching data:', error);
        }
    });

    function populateFilmTable(films) {
        let filmRows = '';
        films.forEach(film => {
            filmRows += `
                <div class="cols">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title overflow-hidden" style="text-overflow: ellipsis; white-space: nowrap; height:25px;">${film.title}</h5>
                            <p class="card-text overflow-hidden" style="text-overflow: ellipsis; height: 100px;">
                                ${film.description}
                            </p>
                        </div>
                    </div>
                </div>
            `;
        });

        var filmContainer = $('#film-container');
        filmContainer.append(filmRows);
    }
});
