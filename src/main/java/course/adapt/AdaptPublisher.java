package course.adapt;

import javax.xml.ws.Endpoint;

/**
 * Created by JR on 05.05.2016.
 */
public class AdaptPublisher {
    public static void main(String[] args) {
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

        Endpoint.publish("http://127.0.0.1:8080/", new Adapt());
    }
}
