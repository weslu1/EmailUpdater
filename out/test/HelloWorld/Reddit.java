import net.dean.jraw.RedditClient;
import net.dean.jraw.oauth.*;
import net.dean.jraw.http.*;
import net.dean.jraw.models.*;
import net.dean.jraw.pagination.*;

public class Reddit {

    public void getFrontPage() {
        UserAgent userAgent = new UserAgent("desktop", "com.dailyupdater", "v0.1", "truepwner");
        Credentials credentials = Credentials.script("truepwner", "Wafflecream123",
                "dpNYUbspXiMzfw", "FpWN8n6ao6zq46gYBqIgSs96Om0");
        NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
        RedditClient reddit = OAuthHelper.automatic(adapter, credentials);

        DefaultPaginator<Submission> frontPage = reddit.frontPage()
                .sorting(SubredditSort.TOP)
                .timePeriod(TimePeriod.DAY)
                .limit(30)
                .build();

        Listing<Submission> submissions = frontPage.next();
        for (Submission s : submissions) {
            System.out.println(s.getTitle());
        }
    }
}
