package movies.kamudo.com.githab.comment;

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
public class CommentController  extends CommonController{
	
	@Autowired
	private CommentService commentService;

	@RequestMapping("/movies/{id}/comments")
	public  ResponseEntity<?>  getMovie(@PathVariable String id){
		return handleServiceCall(commentService.getAllCommentsForMovie(id));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/comments")
	@Secured("ROLE_USER")
	public ResponseEntity<?> addComment(@RequestBody Comment comment){
		return handleServiceCall(commentService.addComment(comment));
	}
		
}
