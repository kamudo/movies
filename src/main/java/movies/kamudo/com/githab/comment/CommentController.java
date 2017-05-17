package movies.kamudo.com.githab.comment;

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
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@RequestMapping("/movies/{id}/comments")
	public @ResponseBody List<Comment> getMovie(@PathVariable String id){
		return commentService.getAllCommentsForMovie(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/comments")
	@Secured("ROLE_USER")
	public ResponseEntity<?> addComment(@RequestBody Comment comment){
		return ResponseEntity.status(commentService.addComment(comment)).body(null);
	}
		
}
