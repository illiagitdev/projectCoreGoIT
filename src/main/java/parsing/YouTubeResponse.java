package parsing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import parsing.components.Items;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeResponse {
    private String nextPageToken;
    private String prevPageToken;
    private Items[] items;

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public void setPrevPageToken(String prevPageToken) {
        this.prevPageToken = prevPageToken;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "APIResponse{" + "\n" +
                ", nextPageToken='" + nextPageToken + '\'' + "\n" +
                ", prevPageToken='" + prevPageToken + '\'' + "\n" +
                ", items=" + Arrays.toString(items) + "\n" +
                '}';
    }
}
