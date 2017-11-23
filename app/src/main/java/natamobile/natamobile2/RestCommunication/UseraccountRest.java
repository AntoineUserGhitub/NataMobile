package natamobile.natamobile2.RestCommunication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import natamobile.natamobile2.Entities.Useraccount;
import natamobile.natamobile2.Models.UseraccountCredentials;


public class UseraccountRest {
    public static final String URL_USER = "/user";
    public static final String URL_BIRD = "/rogver-bird/o/Birds%2F";
    public static void getMember(UseraccountCredentials credentials, final OnLoopjCompleted oljc){

        Gson gson =new GsonBuilder().create();
        String jsonString = gson.toJson(credentials,UseraccountCredentials.class);

        StringEntity entity = new StringEntity(jsonString,"UTF-8");
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        NataMobileRestClient.get(NataMobileRestClient.REST_NATAGORA,URL_USER, entity, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                oljc.taskCompleted(response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                oljc.taskCompleted(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,  Throwable throwable, JSONObject jsonObject) {
                oljc.taskCompleted(null);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                if (statusCode==404)oljc.taskCompleted("404");
                else oljc.taskCompleted(null);

            }

        });
    }
    public static void putMember(Useraccount useraccount, final OnLoopjCompleted oljc){

        Gson gson =new GsonBuilder().create();
        String jsonString = gson.toJson(useraccount,Useraccount.class);

        StringEntity entity = new StringEntity(jsonString,"UTF-8");
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        NataMobileRestClient.put(null,URL_USER, entity, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        oljc.taskCompleted("success");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        if(statusCode==406)oljc.taskCompleted("406");
                        else oljc.taskCompleted("cummunication error");
                    }

    });

    }
    public static void getImage(final String URL,String fileName,final OnLoopjCompleted oljc){

        NataMobileRestClient.get(NataMobileRestClient.REST_STORAGE,URL+fileName, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    oljc.taskCompleted(response.getString("mediaLink"));
                }catch(Exception e){
                    oljc.taskCompleted(e.getMessage());
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                oljc.taskCompleted(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,  Throwable throwable, JSONObject jsonObject) {
                oljc.taskCompleted(null);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                if (statusCode==404)oljc.taskCompleted("404");
                else oljc.taskCompleted(null);

            }

        });
    }
}
