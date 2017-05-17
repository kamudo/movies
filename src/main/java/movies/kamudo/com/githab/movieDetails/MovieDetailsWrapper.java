package movies.kamudo.com.githab.movieDetails;

import java.util.List;

import movies.kamudo.com.githab.comment.Comment;
import movies.kamudo.com.githab.movie.Movie;

public class MovieDetailsWrapper {

	private Movie movieDetails;
	private List<Comment> comments;
	
	public MovieDetailsWrapper() {
	
	}

	public MovieDetailsWrapper(Movie movie, List<Comment> comments) {
		setMovieDetails(movie);
		this.setComments(comments);
	}

	public Movie getMovieDetails() {
		return movieDetails;
	}

	public void setMovieDetails(Movie movieDetails) {
		this.movieDetails = movieDetails;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
