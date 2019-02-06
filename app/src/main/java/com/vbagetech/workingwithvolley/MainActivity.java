package com.vbagetech.workingwithvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.vbagetech.workingwithvolley.VolleyAPIPack.APIResponce;
import com.vbagetech.workingwithvolley.VolleyAPIPack.MainAPIClass;
import com.vbagetech.workingwithvolley.VolleyAPIPack.ParamsData;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //URL of the request we are sending
    String url = "https://jsonplaceholder.typicode.com/posts";
    TextView response_TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response_TextView=findViewById(R.id.response_TextView_id);

        ArrayList<ParamsData> paramsData=new ArrayList<>();
        paramsData.add(new ParamsData("POSTID","2"));
        paramsData.add(new ParamsData("POSTSEC","Details"));
        new MainAPIClass(url, paramsData, new APIResponce() {
            @Override
            public void onAPIResponse(String str) {
                response_TextView.setText(str);
            }

            @Override
            public void onAPIError(String str) {
                response_TextView.setText(str);
            }
        });
    }
}