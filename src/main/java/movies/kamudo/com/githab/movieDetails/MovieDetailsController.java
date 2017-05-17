package movies.kamudo.com.githab.movieDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import movies.kamudo.com.githab.comment.CommentService;
import movies.kamudo.com.githab.movie.MovieService;

@RestController
public class MovieDetailsController {
	
	@Autowired
	private MovieService movieService;	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/movieDetails/{id}")
	public @ResponseBody MovieDetailsWrapper getMovieDetails(@PathVariable String id){
		
		return new MovieDetailsWrapper(
				movieService.getMovie(id), 
				commentService.getAllCommentsForMovie(id));
		
	}
	
}
