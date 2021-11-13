package com.example.smartcity.Web;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class RequestImage {
    public static void request(ImageView image, String url, Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        ImageRequest imageRequest = new ImageRequest(JsonThread.ip+JsonThread.port+url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                image.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        queue.add(imageRequest);
    }
}
