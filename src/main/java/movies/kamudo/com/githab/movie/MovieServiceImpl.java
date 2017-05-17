package movies.kamudo.com.githab.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{

	List<Movie> movieDB = new ArrayList<Movie>(Arrays.asList(
			new Movie ("0", "Super Troopers", "Five Vermont state troopers, avid pranksters with a knack for screwing up, try to save their jobs and out-do the local police department by solving a crime."),
			new Movie ("1", "The Grand Budapest Hotel", "The adventures of Gustave H, a legendary concierge at a famous hotel from the fictional Republic of Zubrowka between the first and second World Wars, and Zero Moustafa, the lobby boy who becomes his most trusted friend."),
			new Movie ("2", "After living a long and colorful life, Allan Karlsson finds himself stuck in a nursing home. On his 100th birthday, he leaps out a window and begins an unexpected journey.", "ive Vermont state troopers, avid pranksters with a knack for screwing up, try to save their jobs and out-do the local police department by solving a crime.")
			)); 
	
	//@Async
	public Movie getMovie(String id){
		final Optional<Movie> foudMovie =  movieDB.stream().filter(m -> m.getId().equals(id)).findFirst();
		return foudMovie.isPresent() ? foudMovie.get() : null;
	}
	
	public int ddMovie(final Movie movie){
		return getMovie(movie.getId()) != null 	
				? 409
				: (movieDB.add(movie)) 
					? 201 
					: 500 ;
	}

	public List<Movie> getMovies(){
		return movieDB;
	}
}
