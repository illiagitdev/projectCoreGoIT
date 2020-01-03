package result;

public class SearchResult {
    private String videoName;
    private String channelName;
    private String publicationDate;
    private String urlID;
    private String urlIDChannel;

    public String getUrlIDChannel() {
        return urlIDChannel;
    }

    public void setUrlIDChannel(String urlIDChannel) {
        this.urlIDChannel = urlIDChannel;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getUrlID() {
        return urlID;
    }

    public void setUrlID(String urlID) {
        this.urlID = urlID;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "videoName='" + videoName + '\'' +
                ", channelName='" + channelName + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", urlID='" + urlID + '\'' +
                ", urlIDChannel='" + urlIDChannel + '\'' +
                '}';
    }
}
