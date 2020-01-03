package querryResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import querryResponse.components.Items;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeResponse {
    private String nextPageToken;
    private String prevPageToken;
    private List<Items> items = new ArrayList<>();

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

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "YouTubeResponse{" + "\n" +
                "nextPageToken='" + nextPageToken + '\'' + "\n" +
                ", prevPageToken='" + prevPageToken + '\'' + "\n" +
                ", items=" + items + "\n" +
                '}';
    }
}
