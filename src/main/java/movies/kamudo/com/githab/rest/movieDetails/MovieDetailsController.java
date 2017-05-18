package movies.kamudo.com.githab.rest.movieDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movies.kamudo.com.githab.common.CommonController;
import movies.kamudo.com.githab.rest.comment.Comment;
import movies.kamudo.com.githab.rest.comment.CommentService;
import movies.kamudo.com.githab.rest.movie.Movie;
import movies.kamudo.com.githab.rest.movie.MovieService;

@RestController
public class MovieDetailsController extends CommonController{
	
	@Autowired
	private MovieService movieService;	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/movieDetails/{id}")
	public ResponseEntity<?> getMovieDetails(@PathVariable String id){
		
		final ResponseEntity<?> movieResponse= handleServiceCall(movieService.getMovie(id));
		final ResponseEntity<?> commentResponse = handleServiceCall(commentService.getAllCommentsForMovie(id));
		
		if(HttpStatus.OK.equals(movieResponse.getStatusCode()) && HttpStatus.OK.equals(commentResponse.getStatusCode())){
			
			final Movie movie = (Movie) movieResponse.getBody();
			final List<Comment> comments = (List<Comment>) commentResponse.getBody();
			
			return ResponseEntity.status(HttpStatus.OK).body(new MovieDetailsWrapper(movie, comments));
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eorror completing the request: +"
				+ "[ MovieServiceCall Status : " 
				+ movieResponse.getStatusCode().name() + " (" + movieResponse.getStatusCode() + ")] "
				+ "[ CommentServiceCall Status : " 
				+ commentResponse.getStatusCode().name() + " (" + commentResponse.getStatusCode() + ")]");
	}
	
}
