package movies.kamudo.com.githab.common;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class CommonController {

	private int MAX_TIMEOUT=1000;
	
	protected  ResponseEntity<?> handleServiceCall(Future<Pair<Integer,?>> serviceCall) {
		
		int processingTime = 0;
		
		while (!serviceCall.isDone() && processingTime < MAX_TIMEOUT) {
	        try {Thread.sleep(10);} 
	        catch (InterruptedException e) { } 
	    }
		
		if(processingTime >= MAX_TIMEOUT){
			//serviceCall.cancel(true);
			return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(null);
		}
		
		try {
			return ResponseEntity
    			.status(HttpStatus.valueOf(serviceCall.get().getLeft()))
    			.body(serviceCall.get().getRight());
		} 
		catch (InterruptedException | ExecutionException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
}
