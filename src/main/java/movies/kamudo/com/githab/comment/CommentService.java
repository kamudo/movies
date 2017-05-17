package movies.kamudo.com.githab.comment;

import java.util.List;

public interface CommentService {

	/**
	 * Gets all comments associated with the movie.
	 * @param movieId
	 * @return List of comments associated with the movie
	 */
	List<Comment> getAllCommentsForMovie(String movieId);
	
	/**
	 * Adding new comment to "Comments DB"
	 * @param comment
	 * @return Success Code returned:  
	 *  409 Conflict - returned if a given comment already exist for user/movie pair.
	 *  500 Internal Error - returned if error during the add operation.
	 *  201 Created - returned when new comment was successfully added.
	 */
	int addComment(final Comment comment);
}
