package movies.kamudo.com.githab.comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	List<Comment> commentDB = new ArrayList<Comment>(Arrays.asList(
			new Comment("0", "user1", "Excellent 5*"),
			new Comment("0", "user2", "Childish"),
			new Comment("1", "user2", "Interesting."),
			new Comment("0", "user3", "Alright, not great!"),
			new Comment("1", "user3", "Thought-provoking"),
			new Comment("2", "user3", "Very entertaning")
			)); 
	

	//TODO: Add a caching mechanism, assumption is commentDB is external DataBase or a Table in DB
	private List<Comment> getMoveViaChachedStrategy(final String movieId){
		return commentDB
				.stream()
				.filter(c-> c.getMovieId().equals(movieId))
				.collect(Collectors.toList());
	}
	
	
	@Async
	public Future<Pair<Integer, ?>>getAllCommentsForMovie(String movieId){
		final List<Comment> comments = getMoveViaChachedStrategy(movieId); 
		return new AsyncResult<>(Pair.of(200,comments));
	}
	

	@Async
	public Future<Pair<Integer, ?>> addComment(final Comment comment){
		return getMoveViaChachedStrategy(comment.getMovieId())
				.stream()
				.filter(c -> c.getUserName().equals(comment.getUserName()))
				.findFirst()
				.isPresent()
			? new AsyncResult<>(Pair.of(409, null))
			: new AsyncResult<>(Pair.of((commentDB.add(comment)) 
				? 201 
				: 500, 
				comment));
	}	
}
