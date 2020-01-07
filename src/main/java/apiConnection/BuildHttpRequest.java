package apiConnection;

import okhttp3.HttpUrl;

public class BuildHttpRequest {
    private static final String ROOT_URL = "https://www.googleapis.com";
    private static final String YOUTUBE_URL = "https://www.youtube.com";
    private static final String KEY = "AIzaSyDsxIyAMEYNxF5s4KqcP2hA0trTYzi5ZaU";

    public static HttpUrl buildHttpUrl(String searchText) {
        return buildHttpUrl(searchText , "25");
    }

    private static HttpUrl buildHttpUrl(String searchText, String maxResults) {
        return HttpUrl.parse(ROOT_URL).newBuilder()
                .addPathSegment("youtube")
                .addPathSegment("v3")
                .addPathSegment("search")
                .addQueryParameter("part","snippet")
                .addQueryParameter("maxResults", maxResults)
                .addQueryParameter("q", searchText)
                .addQueryParameter("key", KEY)
                .build();
    }

    public static HttpUrl buildChannelHttpUrl(String channelID) {
        return HttpUrl.parse(ROOT_URL).newBuilder()
                .addPathSegment("youtube")
                .addPathSegment("v3")
                .addPathSegment("channels")
                .addQueryParameter("part","snippet%contentDetails%statistics")
                .addQueryParameter("id",channelID)
                .addQueryParameter("MaxResults", "10")
                .build();
    }

    public static HttpUrl buildYouTubeWatchUrl(String videoID) {
        return HttpUrl.parse(YOUTUBE_URL).newBuilder()
                .addPathSegment("watch")
                .addQueryParameter("v",videoID)
                .build();
    }
}
