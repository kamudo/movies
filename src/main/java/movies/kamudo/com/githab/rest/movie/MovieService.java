package movies.kamudo.com.githab.rest.movie;

import java.util.concurrent.Future;

import org.apache.commons.lang3.tuple.Pair;

public interface MovieService {
	
	/**
	 * Gets Movie by id
	 * @param id
	 * @return Movie
	 */
	public Future<Pair<Integer, ?>> getMovie(String id);
	
	/**
	 * Adding new movie to "MovieDB"
	 * @param movie
	 * @return Pair <SuccessCode, Movie>
	 *  returned: 
	 *  Success Code 
	 *  	409 Conflict - returned if a given movie already exist with movie.id.
	 *  	500 Internal Error - returned if error during the add operation.
	 *  	201 Created - returned when the new movie was successfully added.
	 *  Movie Itself
	 */
	public Future<Pair<Integer, ?>> ddMovie(final Movie movie);

	/**
	 * Gets all movies
	 * @return List<Movie>
	 */
	public Future<Pair<Integer, ?>> getMovies();
}
