import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;



public class OPGameMain {

	/**
	 * @param args
	 */
	private Twitter twitter;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("final!!");
		OPGameMain main = new OPGameMain();
		main.twitterAPITest();
	}
	
	public OPGameMain(){
		this.getInstance();
	}

	private void getInstance(){
		TwitterFactory factory = new TwitterFactory();
		twitter = factory.getInstance();
	}

	public void twitterAPITest(){
		try{
			this.getHomeTimeline();
		}catch(TwitterException te){
			te.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getHomeTimeline() throws TwitterException{
		List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" + status.getText());
	    }
	}

}
