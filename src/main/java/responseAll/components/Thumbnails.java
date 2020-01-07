package responseAll.components;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Thumbnails {

    @JsonProperty("default")
    private VideoImage random;
    private VideoImage medium ;
    private VideoImage high ;
    private VideoImage standard ;
    private VideoImage maxres ;

    public VideoImage getRandom() {
        return random;
    }

    public void setRandom(VideoImage random) {
        this.random = random;
    }

    public VideoImage getMedium() {
        return medium;
    }

    public void setMedium(VideoImage medium) {
        this.medium = medium;
    }

    public VideoImage getHigh() {
        return high;
    }

    public void setHigh(VideoImage high) {
        this.high = high;
    }

    public VideoImage getStandard() {
        return standard;
    }

    public void setStandard(VideoImage standard) {
        this.standard = standard;
    }

    public VideoImage getMaxres() {
        return maxres;
    }

    public void setMaxres(VideoImage maxres) {
        this.maxres = maxres;
    }

    @Override
    public String toString() {
        return "Thumbnails{" + "\n" +
                "random=" + random + "\n" +
                ", medium=" + medium + "\n" +
                ", high=" + high + "\n" +
                ", standard=" + standard + "\n" +
                ", maxres=" + maxres + "\n" +
                '}';
    }
}
