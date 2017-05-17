package movies.kamudo.com.githab.comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	
	public List<Comment> getAllCommentsForMovie(String movieId){
			
		return commentDB
				.stream()
				.filter(c-> c.getMovieId().equals(movieId))
				.collect(Collectors.toList());
	}
	
	public int addComment(final Comment comment){
		return getAllCommentsForMovie(comment.getMovieId())
				.stream()
				.filter(c -> c.getUserName().equals(comment.getUserName()))
				.findFirst()
				.isPresent()
			? 409
			: (commentDB.add(comment)) 
				? 201 
				: 500 ;
	}	
}
