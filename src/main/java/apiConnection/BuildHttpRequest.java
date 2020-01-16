package apiConnection;

import okhttp3.HttpUrl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BuildHttpRequest {
    private static final String ROOT_URL = "https://www.googleapis.com";
    private static final String YOUTUBE_URL = "https://www.youtube.com";
    private static final String KEY = "AIzaSyDsxIyAMEYNxF5s4KqcP2hA0trTYzi5ZaU";
    //todo: read other languages when do search

    public static HttpUrl buildHttpUrl(String searchText, String maxResults, String publishedDays) {
        HttpUrl httpUrl = null;
        try {
            httpUrl = HttpUrl.parse(ROOT_URL).newBuilder()
                    .addPathSegment("youtube")
                    .addPathSegment("v3")
                    .addPathSegment("search")
                    .addQueryParameter("part", "snippet")
                    .addQueryParameter("maxResults", maxResults)
                    .addQueryParameter("publishedAfter", publishedDays)
                    .addQueryParameter("q", URLEncoder.encode(searchText, "UTF-8"))
                    .addQueryParameter("key", KEY)
                    .build();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Failed in encoding (HttpUrl buildHttpUrl(3))");
        }
        System.out.println(httpUrl + " BuildHttpRequest.buildHttpUrl(searchText, maxResults, publishedDays)");
        return httpUrl;
    }

    public static HttpUrl buildHttpUrl(String searchText) {
        HttpUrl httpUrl = null;
        try {
            httpUrl = HttpUrl.parse(ROOT_URL).newBuilder()
                    .addPathSegment("youtube")
                    .addPathSegment("v3")
                    .addPathSegment("search")
                    .addQueryParameter("part", "snippet")
                    .addQueryParameter("maxResults", "25")
                    .addQueryParameter("q", URLEncoder.encode(searchText, "UTF-8"))
                    .addQueryParameter("key", KEY)
                    .build();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Failed in encoding (HttpUrl buildHttpUrl(1))");
        }
        System.out.println(httpUrl + " BuildHttpRequest.buildHttpUrl(searchText)");
        return httpUrl;
    }

    public static HttpUrl buildChannelHttpUrl(String channelID) {
        HttpUrl httpUrl = HttpUrl.parse(ROOT_URL).newBuilder()
                .addPathSegment("youtube")
                .addPathSegment("v3")
                .addPathSegment("channels")
                .addQueryParameter("part", "snippet")
                .addQueryParameter("channelID", channelID)
                .addQueryParameter("maxResults", "10")
                .addQueryParameter("order", "date")
                .addQueryParameter("key", KEY)
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
