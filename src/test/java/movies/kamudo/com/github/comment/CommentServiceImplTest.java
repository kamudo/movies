package movies.kamudo.com.github.comment;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CommentServiceImplTest {

	  @Autowired
	   private TestRestTemplate restTemplate;

	@Test
	public void testGetAllCommentsForMovie() {
		fail("Not yet implemented");
	}


	@Test
	public void testAddComment() {
		 this.restTemplate.getForEntity("/movies/{id}/comments", String.class, "0");
	}

}
