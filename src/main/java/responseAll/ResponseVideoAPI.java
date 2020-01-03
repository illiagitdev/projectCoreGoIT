package responseAll;

import responseAll.components.PageInfo;
import responseAll.components.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseVideoAPI {
    private String kind;
    private String etag;
    private String nextPageToken;
    private String prevPageToken;
    private String regionCode;
    private PageInfo pageInfo;
    private List<Items> items = new ArrayList<>();

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

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

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "APIResponse{" + "\n" +
                "kind='" + kind + '\'' + "\n" +
                ", etag='" + etag + '\'' + "\n" +
                ", nextPageToken='" + nextPageToken + '\'' + "\n" +
                ", prevPageToken='" + prevPageToken + '\'' + "\n" +
                ", regionCode='" + regionCode + '\'' + "\n" +
                ", pageInfo=" + pageInfo + "\n" +
                ", items=" + items.toString() + "\n" +
                '}';
    }
}
