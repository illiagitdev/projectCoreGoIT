package result;

public class SearchResult {
    private final String videoName;
    private final String channelName;
    private final String publicationDate;
    private final String urlID;
    private final String urlIDChannel;

    private SearchResult(String videoName, String channelName, String publicationDate, String urlID, String urlIDChannel) {
        this.videoName = videoName;
        this.channelName = channelName;
        this.publicationDate = publicationDate;
        this.urlID = urlID;
        this.urlIDChannel = urlIDChannel;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getUrlID() {
        return urlID;
    }

    public String getUrlIDChannel() {
        return urlIDChannel;
    }

    public static class Builder{
        private String videoName;
        private String channelName;
        private String publicationDate;
        private String urlID;
        private String urlIDChannel;

        public Builder setVideoName(String videoName) {
            this.videoName = videoName;

            return this;
        }

        public Builder setChannelName(String channelName) {
            this.channelName = channelName;

            return this;
        }

        public Builder setPublicationDate(String publicationDate) {
            this.publicationDate = publicationDate;

            return this;
        }

        public Builder setUrlID(String urlID) {
            this.urlID = urlID;

            return this;
        }

        public Builder setUrlIDChannel(String urlIDChannel) {
            this.urlIDChannel = urlIDChannel;

            return this;
        }

        public SearchResult build(){
            return new SearchResult(videoName,
                    channelName,
                    publicationDate,
                    urlID,
                    urlIDChannel);
        }
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
