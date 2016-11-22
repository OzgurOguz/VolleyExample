package com.example.ozgur.volleyexample;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class GetContent extends Activity {

    CoordinateVariables coordinateVariables = new CoordinateVariables();

    public void GetContent() {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=khulna,bd";
        RequestQueue queue = Volley.newRequestQueue(this);

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONObject JCoordinate = null;
                try {
                    JCoordinate = response.getJSONObject("coord");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    coordinateVariables.setLon(JCoordinate.getString("lon"));
                    coordinateVariables.setLat(JCoordinate.getString("lat"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("lon: " + coordinateVariables.getLon() + " lat: " + coordinateVariables.getLat());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error ");
            }
        });
        queue.add(jsObjRequest);
    }
}
