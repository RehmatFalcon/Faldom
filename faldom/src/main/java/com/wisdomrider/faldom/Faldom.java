package com.wisdomrider.faldom;

import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wisdomrider.faldom.interfaces.BuilderInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wisdomrider (Avishek Adhikari) on 3/22/2018 *********** ...
 */
//indent hus
public class Faldom {


    public static BuilderInterface Builder(Context c) {
        return new Builder(c);
    }


    public static void Request(Context context,
                               final HashMap<String, String> params,
                               String url,
                               int Method,
                               final HashMap<String, String> headers,
                               @Nullable final Action callback) {

        //inform when you 1get back    1 min

        RequestQueue queue = Volley.newRequestQueue(context);

        Response.Listener successListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);

            }
        };

        Response.ErrorListener errorListerner = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError erro) {
                FaldomError error = new FaldomError(erro);
                callback.onError(error);
            }
        };

        StringRequest sr = new StringRequest(Method, url,successListener,errorListerner)
        {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        queue.add(sr);

    }

}
