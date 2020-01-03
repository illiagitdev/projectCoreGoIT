package querryResponse.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchID {
    private String videoId;
    private String channelId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "SearchID{" + "\n" +
                ", videoId='" + videoId + '\'' + "\n" +
                ", channelId='" + channelId + '\'' + "\n" +
                '}';
    }
}
