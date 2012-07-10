import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import twitter4j.ProfileImage;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


public class OPTwitter {

	private static final OPTwitter instance = new OPTwitter();
	
	private static final String consumer_key = "zjBVPSrszgiJI76bc7I5Dw";
	private static final String consumer_secret = "R1qaXieTTpyLnKJoTtZrjvDzcLfG39JeUxZlHXkkI";
	public Twitter twitter;
	public AccessToken accessToken;

	private OPTwitter(){

	}
	
	public static OPTwitter sharedInstace(){
		return instance;
	}
	
	public void login() throws TwitterException, IOException{
		this.twitter = new TwitterFactory().getInstance();
		this.twitter.setOAuthConsumer(consumer_key, consumer_secret);
		RequestToken requestToken = this.twitter.getOAuthRequestToken();
		this.accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (this.accessToken == null) {
			System.out.println("Open the following URL and grant access to your account:");
			System.out.println(requestToken.getAuthorizationURL());
			System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
			String pin = br.readLine();
			try {
				if (pin.length() > 0) {
					this.accessToken = this.twitter
							.getOAuthAccessToken(requestToken, pin);
				} else {
					this.accessToken = this.twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (te.getStatusCode() == 401) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
		// storeAccessToken(twitter.verifyCredentials().getId(), accessToken);
		// Status status = twitter.updateStatus(args[0]);
		// System.out.println("Successfully updated the status to ["
		// + status.getText() + "].");
		// System.exit(0);
		System.out.println("Login Success!!");
		this.twitter.setOAuthAccessToken(this.accessToken);
		this.getHomeTimeline();
	}
	
	public void getHomeTimeline() throws TwitterException {
		List<Status> statuses = twitter.getHomeTimeline();
		System.out.println("Showing home timeline.");
		for (Status status : statuses) {
			System.out.println(status.getUser().getName() + ":" + status.getText());
		}
	}
	
	public String getCurrentUserIconURL() throws TwitterException{
		return twitter.getProfileImage(twitter.getScreenName(), ProfileImage.NORMAL).getURL();
	}
	
	
}
