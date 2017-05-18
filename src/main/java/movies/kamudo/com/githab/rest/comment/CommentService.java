package movies.kamudo.com.githab.rest.comment;

import java.util.concurrent.Future;

import org.apache.commons.lang3.tuple.Pair;

public interface CommentService {

	/**
	 * Gets all comments associated with the movie.
	 * @param movieId
	 * @return List of comments associated with the movie
	 */
	Future<Pair<Integer, ?>> getAllCommentsForMovie(String movieId);
	
	/**
	 * Adding new comment to "Comments DB"
	 * @param comment
	 *
	 * @return Pair <SuccessCode, List<Comment>>
	 *  returned: 
	 *  Success Code 
	 *  	409 Conflict - returned if a given comment already exist for user/movie pair.
	 *  	500 Internal Error - returned if error during the add operation.
	 *  	201 Created - returned when new comment was successfully added.
	 *  Comment Itself
	 */
	
	Future<Pair<Integer, ?>> addComment(final Comment comment);
}
