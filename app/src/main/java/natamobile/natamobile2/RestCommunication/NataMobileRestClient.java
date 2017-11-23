package natamobile.natamobile2.RestCommunication;


import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;
public class NataMobileRestClient {


    public static final String REST_NATAGORA = "http://192.168.128.13:8081/NatagoraRestRogVer/resources";
    public static final String REST_STORAGE = "https://www.googleapis.com/storage/v1/b";
    private static final String CONTENT_TYPE = "application/json";
    private static AsyncHttpClient client = new AsyncHttpClient();

   // public static void get(String rest,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
   //     client.get(rest+url, params, responseHandler);
   // }
    public static void get(String rest, String url, StringEntity jsonObject, AsyncHttpResponseHandler responseHandler){
        client.setURLEncodingEnabled(false);
        client.get(null,rest+url,jsonObject,CONTENT_TYPE,responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }
    public static void post(Context context, String url, StringEntity jsonObject, AsyncHttpResponseHandler responseHandler){
        client.setURLEncodingEnabled(false);
        client.post(context,url,jsonObject,CONTENT_TYPE,responseHandler);
    }
    public static void put(Context context, String url, StringEntity jsonObject, AsyncHttpResponseHandler responseHandler){
        client.setURLEncodingEnabled(false);
        client.put(context,url,jsonObject,CONTENT_TYPE,responseHandler);
    }


}