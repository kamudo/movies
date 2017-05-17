package movies.kamudo.com.githab.movie;

import java.util.List;

public interface MovieService {
	
	/**
	 * Gets Movie by id
	 * @param id
	 * @return Movie
	 */
	public Movie getMovie(String id);
	
	/**
	 * Adding new movie to "MovieDB"
	 * @param movie
	 * @return Success Code returned:  
	 *  409 Conflict - returned if a given movie already exist with movie.id.
	 *  500 Internal Error - returned if error during the add operation.
	 *  201 Created - returned when the new movie was successfully added.
	 */
	public int ddMovie(final Movie movie);

	/**
	 * Gets all movies
	 * @return List<Movie>
	 */
	public List<Movie> getMovies();
}
