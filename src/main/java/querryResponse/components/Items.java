package querryResponse.components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import responseAll.components.SearchID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    private SearchID id;
    private Snippet snippet;

    public SearchID getId() {
        return id;
    }

    public void setId(SearchID id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "Items{" + "\n"+
                "id=" + id + "\n"+
                ", snippet=" + snippet + "\n"+
                '}';
    }
}
