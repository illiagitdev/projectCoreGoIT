package controlers;

import okhttp3.OkHttpClient;

public interface Controls {
    OkHttpClient client = new OkHttpClient();
    String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    String DATE_FORMAT_SHOW = "dd/L/yyyy 'Time:' HH:mm";
}
