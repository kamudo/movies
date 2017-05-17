package movies.kamudo.com.githab.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/movies/{id}")
	public @ResponseBody Movie getMovie(@PathVariable String id){
		final Movie result = movieService.getMovie(id);
		return result;
	}
	

	@RequestMapping("/movies")
	public @ResponseBody List<Movie> getMovie(){
		return movieService.getMovies();
	}
		
	@RequestMapping(method=RequestMethod.POST, value="/movies")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?>  getMovie(@RequestBody Movie movie){
		return  ResponseEntity.status(movieService.ddMovie(movie)).body(null);
	}
		
}
