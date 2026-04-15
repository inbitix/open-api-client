package io.broker.api.client.constant;

public class Constants {

    // Test API Key
    public final static String ACCESS_KEY = "Oexzd7XoRrpZgJijGeKLRFZ4OsHTGZVU2GjXvY2n0M0iJd9B188c5FgvUwfxmH5u";
    public final static String SECRET_KEY = "a8FjxL0KqlbmIeBgvk2QXhV4CqQXQmApLwxzpgzSwAhmEUCLsUYexItIEvMbp5Lt";

    // server: https://openapi.inbitix.com/openapi
    public static final String BASE_DOMAIN = "openapi.inbitix.com";
    public static final String QUOTE_DOMAIN = "ws.inbitix.com";

    // api url format: https://BASE_DOMAIN/openapi/
    public static final String API_BASE_URL = "https://" + BASE_DOMAIN + "/openapi/";

    // for example: wss://QUOTE_DOMAIN/ws/quote/v1
    public static final String WS_API_BASE_URL =  "wss://" + QUOTE_DOMAIN +  "/ws/quote/v1";

    // Websocket user api url format: wss://BASE_DOMAIN/openapi/ws/
    public static final String WS_API_USER_URL = "wss://" + BASE_DOMAIN + "/openapi/ws/";
}
