import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import twitter4j.ProfileImage;
import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class OPTwitter {

	private static final OPTwitter instance = new OPTwitter();

	private static final String consumer_key = "zjBVPSrszgiJI76bc7I5Dw";
	private static final String consumer_secret = "R1qaXieTTpyLnKJoTtZrjvDzcLfG39JeUxZlHXkkI";
	public Twitter twitter;
	public AccessToken accessToken;

	private OPTwitter() {

	}

	public static OPTwitter sharedInstace() {
		return instance;
	}

	public void login() throws TwitterException, IOException {
		this.twitter = new TwitterFactory().getInstance();
		this.twitter.setOAuthConsumer(consumer_key, consumer_secret);
		
		/*
		 * fetch accessToken and secretToken from console and put into under the code
		 * then comment out marked area
		 */
//		this.accessToken = new AccessToken("accessToken", "secretToken");
		
		/* start mark */
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
					this.accessToken = this.twitter.getOAuthAccessToken(requestToken, pin);
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
		/* end mark */
		
		System.out.println("accessToken : " + this.accessToken.getToken());
		System.out.println("secretToken : " + this.accessToken.getTokenSecret());
		
		System.out.println("Login Success!!");
		this.twitter.setOAuthAccessToken(this.accessToken);
		
		RateLimitStatus rateLimit = twitter.getRateLimitStatus();
		System.out.println("=======================");
		System.out.println("hour limit count : " + rateLimit.getHourlyLimit());
		System.out.println("remain count : " + rateLimit.getRemainingHits());
		System.out.println("untill reset sec : " + rateLimit.getSecondsUntilReset());
		System.out.println("=======================");
				
//		this.getHomeTimeline();
	}

	public void getHomeTimeline() throws TwitterException {
		List<twitter4j.Status> statuses = twitter.getHomeTimeline();
		System.out.println("Showing home timeline.");
		for (twitter4j.Status status : statuses) {
			System.out.println(status.getUser().getName() + ":" + status.getText());
		}
	}

	public URL getCurrentUserIconURL() throws TwitterException, MalformedURLException, IllegalStateException {
		URL url = new URL(twitter.getProfileImage(twitter.getScreenName(), ProfileImage.NORMAL).getURL());
		return url;
	}

	public ArrayList<URL> getFriendsList(int useUserCount) throws TwitterException, MalformedURLException {
		/* useUserCount is fetch use count but less than 100 */
		int count = Math.min(useUserCount, 100);
		long[] allIds = twitter.getFollowersIDs(twitter.getScreenName(), -1).getIDs();
		long[] useIds = new long[count];
		
		ArrayList<URL> urls = new ArrayList<URL>();
		
		for (int i = 0; i < useIds.length; i++)
			useIds[i] = allIds[i];
		
		for (User user : twitter.lookupUsers(useIds)) {
			urls.add(user.getProfileImageURL());
		}

		return urls;
	}

}
