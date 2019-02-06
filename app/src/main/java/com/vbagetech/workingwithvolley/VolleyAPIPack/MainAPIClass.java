package com.vbagetech.workingwithvolley.VolleyAPIPack;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainAPIClass {
    public static int GETMETHOD = 0;
    public static int POSTMETHOD = 1;
    APIResponce apiResponce;

    public MainAPIClass(String URL, ArrayList<ParamsData> paramsDataArrayList,APIResponce _apiResponce) {
        int method = GETMETHOD;
        if (paramsDataArrayList != null && paramsDataArrayList.size()>0) {
            method = POSTMETHOD;
            JSONObject postparams = new JSONObject();
            try {
                for (ParamsData param : paramsDataArrayList) {
                    postparams.put(param.getKEY(), param.getVALUE());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            requestMethod(URL, method, postparams);
        } else {
            requestMethod(URL, method, null);
        }
        this.apiResponce=_apiResponce;
    }

    private void requestMethod(String url, int method, JSONObject postparams) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, url, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RequestResponce", "" + response);
                        apiResponce.onAPIResponse(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("RequestResponce_ERROR", "" + error);
                        apiResponce.onAPIError(error.toString());
                    }
                })
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                //headers.put("Content-Type", "application/json");
                headers.put("APIAUTH", "4133224383e21748b89724e01e3d5cbb");
                return headers;
            }
        }
        ;
        // Adding the request to the queue along with a unique string tag
        MainApplication.getInstance().addToRequestQueue(jsonObjReq, "VBage_Volley");
    }

}
