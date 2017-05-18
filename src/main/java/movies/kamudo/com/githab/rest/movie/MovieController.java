package movies.kamudo.com.githab.rest.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import movies.kamudo.com.githab.common.CommonController;

@RestController
public class MovieController extends CommonController{
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/movies/{id}")
	public ResponseEntity<?> getMovie(@PathVariable String id){
		return handleServiceCall(movieService.getMovie(id));	
	}

	
	@RequestMapping("/movies")
	public ResponseEntity<?> getMovie(){
		return handleServiceCall(movieService.getMovies());
	}
		
	@RequestMapping(method=RequestMethod.POST, value="/movies")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> getMovie(@RequestBody Movie movie){
		return handleServiceCall(movieService.ddMovie(movie));		
	}
}
