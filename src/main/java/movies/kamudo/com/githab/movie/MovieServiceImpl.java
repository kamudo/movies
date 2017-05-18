package movies.kamudo.com.githab.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import movies.kamudo.com.githab.comment.Comment;

@Service
public class MovieServiceImpl implements MovieService{

	//Least Frequently Used (LFU) caching strategy implementation 
	private int MAX_LFU_CACHE_SIZE = 3;
	private List<Movie> lfuCache = Collections.synchronizedList(new ArrayList<Movie>());
	private HashMap<String, Integer> lfuCacheRequestCount = new HashMap<String, Integer>();

	//Mock of a "DB" data
	private List<Movie> movieDB = new ArrayList<Movie>(Arrays.asList(
			new Movie ("0", "Super Troopers", "Five Vermont state troopers, avid pranksters with a knack for screwing up, try to save their jobs and out-do the local police department by solving a crime."),
			new Movie ("1", "The Grand Budapest Hotel", "The adventures of Gustave H, a legendary concierge at a famous hotel from the fictional Republic of Zubrowka between the first and second World Wars, and Zero Moustafa, the lobby boy who becomes his most trusted friend."),
			new Movie ("2", "After living a long and colorful life, Allan Karlsson finds himself stuck in a nursing home. On his 100th birthday, he leaps out a window and begins an unexpected journey.", "ive Vermont state troopers, avid pranksters with a knack for screwing up, try to save their jobs and out-do the local police department by solving a crime.")
			));  
	
		
	
	@Async
	public Future<Pair<Integer, ?>> getMovie(String id){
		final Optional<Movie> foudMovie = getMoveViaChachedStrategy(id); 
		return new AsyncResult<>(foudMovie.isPresent() 
				? Pair.of(200, foudMovie.get()) 
				: Pair.of(404, "No Movie in the db!")) ;
				
	}
	

	@Async
	//Could use a set to ensure unique data, but in case we have no control over how data 
	//is persisted and we want to avoid redundant data, we can do our own due diligence.
	public Future<Pair<Integer, ?>> ddMovie(final Movie movie){
		final Optional<Movie> result = getMoveViaChachedStrategy(movie.getId()); 
		return new AsyncResult<> (result.isPresent()
				? Pair.of(409, "Error Adding Movie: movie already exists in the DB!")
				: Pair.of((movieDB.add(movie) 
						? 201 //Successfully Created
						: 500), //Could not add a movie
						movie)) ;
	}

	@Async
	public Future<Pair<Integer, ?>> getMovies(){
		return new AsyncResult<>(Pair.of(200, movieDB));
	}

	

	//TODO: Finish LFU caching strategy implementation, 
	//assumption is movieDB is external DataBase or a Table in DB
	private Optional<Movie> getMoveViaChachedStrategy(final String id){
		
		Optional<Movie> result;
		
		//Update frequency count so that later we can decide which items to keep in the cache
		lfuCacheRequestCount.put(id, lfuCacheRequestCount.get(id) !=null 
				? new Integer(lfuCacheRequestCount.get(id).intValue() + 1) 
				: 1 );
		
		//Get value out of the cache
		result = lfuCache.stream().filter(m -> m.getId().equals(id)).findFirst();
		
		//GetValue from the "DB" if it is not yet in the cache
		if(!result.isPresent()){
			result =  movieDB.stream().filter(m -> m.getId().equals(id)).findFirst();
			
			//TODO: Work out if result should be added in the lfuCache based on lfuCacheRequestCount
			//The maximum size of the cache is specified by MAX_LFU_CACHE_SIZE
			//Update cache accordingly.
		}
		
		return result;
		
	}
	

}


