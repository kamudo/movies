package movies.kamudo.com.githab.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{

	List<Movie> movieDB = new ArrayList<Movie>(Arrays.asList(
			new Movie ("0", "Super Troopers", "Five Vermont state troopers, avid pranksters with a knack for screwing up, try to save their jobs and out-do the local police department by solving a crime."),
			new Movie ("1", "The Grand Budapest Hotel", "The adventures of Gustave H, a legendary concierge at a famous hotel from the fictional Republic of Zubrowka between the first and second World Wars, and Zero Moustafa, the lobby boy who becomes his most trusted friend."),
			new Movie ("2", "After living a long and colorful life, Allan Karlsson finds himself stuck in a nursing home. On his 100th birthday, he leaps out a window and begins an unexpected journey.", "ive Vermont state troopers, avid pranksters with a knack for screwing up, try to save their jobs and out-do the local police department by solving a crime.")
			)); 
	
	//TODO: Add a caching mechanism, assumption is movieDB is external DataBase or a Table in DB
	private Optional<Movie> getMoveViaChachedStrategy(final String id){
		return movieDB.stream().filter(m -> m.getId().equals(id)).findFirst();
	}
	
	
	@Async
	public Future<Pair<Integer, ?>> getMovie(String id){
		final Optional<Movie> foudMovie = getMoveViaChachedStrategy(id); 
		return new AsyncResult<>(Pair.of(200, foudMovie.isPresent() 
				? foudMovie.get() 
				: null)) ;
				
	}
	

	@Async
	public Future<Pair<Integer, ?>> ddMovie(final Movie movie){
		Optional<Movie> result = getMoveViaChachedStrategy(movie.getId()); 
		return new AsyncResult<> (result.isPresent()
				? Pair.of(409, null)
				: Pair.of((movieDB.add(movie) 
						? 201 
						: 500), movie)) ;
	}

	@Async
	public Future<Pair<Integer, ?>> getMovies(){
		return new AsyncResult<>(Pair.of(200, movieDB));
	}
}
