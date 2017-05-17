package movies.kamudo.com.githab.comment;

public class Comment {

	private String movieId;
	private String userName;
	private String message;
	
	public Comment(String movieId, String userName, String message) {
		this.movieId = movieId;
		this.userName = userName;
		this.message = message;
	}
	
	public Comment(Comment c) {
		this.movieId = c.getMovieId();
		this.userName = c.getUserName();
		this.message = c.getMessage();
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Comment() {
	
	}

}
