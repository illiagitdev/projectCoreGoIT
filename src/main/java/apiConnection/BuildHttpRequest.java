package apiConnection;

import okhttp3.HttpUrl;

public class BuildHttpRequest {
    private static final String ROOT_URL = "https://www.googleapis.com";
    private static final String YOUTUBE_URL = "https://www.youtube.com";
    private static final String KEY = "AIzaSyDsxIyAMEYNxF5s4KqcP2hA0trTYzi5ZaU";

    public static HttpUrl buildHttpUrl(String searchText, String maxResults, String publishedDays) {
        HttpUrl httpUrl = HttpUrl.parse(ROOT_URL).newBuilder()
                .addPathSegment("youtube")
                .addPathSegment("v3")
                .addPathSegment("search")
                .addQueryParameter("part", "snippet")
                .addQueryParameter("maxResults", maxResults)
                .addQueryParameter("publishedAfter", publishedDays)
                .addQueryParameter("q", searchText)
                .addQueryParameter("key", KEY)
                .build();
        System.out.println(httpUrl + " BuildHttpRequest.buildHttpUrl(searchText, maxResults, publishedDays)");
        return httpUrl;
    }

    public static HttpUrl buildHttpUrl(String searchText) {
        HttpUrl httpUrl = HttpUrl.parse(ROOT_URL).newBuilder()
                .addPathSegment("youtube")
                .addPathSegment("v3")
                .addPathSegment("search")
                .addQueryParameter("part", "snippet")
                .addQueryParameter("maxResults", "25")
                .addQueryParameter("q", searchText)
                .addQueryParameter("key", KEY)
                .build();
        System.out.println(httpUrl + " BuildHttpRequest.buildHttpUrl(searchText)");
        return httpUrl;
    }

    public static HttpUrl buildChannelHttpUrl(String channelID) {
        HttpUrl httpUrl = HttpUrl.parse(ROOT_URL).newBuilder()
                .addPathSegment("youtube")
                .addPathSegment("v3")
                .addPathSegment("channels")
                .addQueryParameter("part", "snippet%contentDetails%statistics")
                .addQueryParameter("id", channelID)
                .addQueryParameter("MaxResults", "10")
                .build();
        System.out.println(httpUrl + " BuildHttpRequest.buildChannelHttpUrl ");
        return httpUrl;
    }

    public static String buildYouTubeWatchUrl(String videoID) {
        String path = YOUTUBE_URL + "/embed/" + videoID + "?autoplay=1";
        System.out.println(path + " BuildHttpRequest.buildYouTubeWatchUrl ");
        return path;
    }
}
