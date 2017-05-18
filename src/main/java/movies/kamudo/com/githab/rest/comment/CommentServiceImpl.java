package movies.kamudo.com.githab.rest.comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	//Mock of a "DB" data
	List<Comment> commentDB = new ArrayList<Comment>(Arrays.asList(
			new Comment("0", "user1", "Excellent 5*"),
			new Comment("0", "user2", "Childish"),
			new Comment("1", "user2", "Interesting."),
			new Comment("0", "user3", "Alright, not great!"),
			new Comment("1", "user3", "Thought-provoking"),
			new Comment("2", "user3", "Very entertaning")
			)); 
	
	//Cache
	final Set<Comment> cache = (Set<Comment>) Collections.synchronizedSet(new HashSet<Comment>());
	
	
	@Async
	public Future<Pair<Integer, ?>>getAllCommentsForMovie(String movieId){
		final List<Comment> comments = getMoveViaChachedStrategy(movieId); 
		return new AsyncResult<>(Pair.of(200,comments));
	}
	

	@Async
	//Could've use a set to ensure unique data, but in case we have no control over how data 
	//is persisted and we want to avoid redundant data, we can do our own due diligence.
	public Future<Pair<Integer, ?>> addComment(final Comment comment){
		
		//Note: Cache will be populated with this new comment when it is first requested 
		
		return getMoveViaChachedStrategy(comment.getMovieId())
				.stream().filter(c -> c.getUserName().equals(comment.getUserName()))
				.findFirst()
				.isPresent()
			? new AsyncResult<>(Pair.of(409, "Error Adding Comment: comment for movie/user combination already exist!"))
			: new AsyncResult<>(Pair.of((commentDB.add(comment)) 
				? 201 //Successfully Created
				: 500, //Could not add comment 
				comment));
	}	
	
	
	
	/**
     * The List of comments is returned for a given moveId.
     * request is made from the "DB", if successful the result is put in cache and returned
     * else, in case of any error the result is returned from the cache 
     * 
     * @param movieId
     * @return List<Comment>
     */ 
	private List<Comment> getMoveViaChachedStrategy(final String movieId){
		
		List<Comment> result = new ArrayList<Comment>();
		
		try{
			result = commentDB.stream()
					.filter(c-> c.getMovieId().equals(movieId))
					.collect(Collectors.toList());
			
			synchronized(cache) {
				cache.addAll(result); 
			}
			
		}
		catch(Exception e){
			synchronized(cache) {
				result = cache.stream()
						.filter(c-> c.getMovieId().equals(movieId))
						.collect(Collectors.toList());
			}
		}

		
		return result;
	}
	
}
 