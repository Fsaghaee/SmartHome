package com.example.smarthome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CorridorTab extends Fragment {
    View view;
    public CorridorTab() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_corridor, container, false);


        ToggleButton toggle01 = view.findViewById(R.id.tg01);
        toggle01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    OkHttpClient client=new OkHttpClient();
                    String url="http://farzadsaghaee.dynu.net:90/index.php?ip=192.168.0.219&status=on";
                    Request request=new Request.Builder().url(url).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        }
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        }
                    });
                } else {
                    OkHttpClient client=new OkHttpClient();
                    String url="http://farzadsaghaee.dynu.net:90/index.php?ip=192.168.0.219&status=off";
                    Request request=new Request.Builder().url(url).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        }
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        }
                    });
                }
            }
        });



        ToggleButton toggle02 = view.findViewById(R.id.tg02);
        toggle02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    OkHttpClient client=new OkHttpClient();
                    String url="http://farzadsaghaee.dynu.net:90/index.php?ip=192.168.0.126&status=on";
                    Request request=new Request.Builder().url(url).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        }
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        }
                    });
                } else {
                    OkHttpClient client=new OkHttpClient();
                    String url="http://farzadsaghaee.dynu.net:90/index.php?ip=192.168.0.126&status=off";
                    Request request=new Request.Builder().url(url).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        }
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        }
                    });
                }
            }
        });


        Thread thread = new Thread(new Runnable() {


            @Override
            public void run() {
                try {
                    getxmldata("192.168.0.219","192.168.0.126");
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
        });

        thread.start();



        return view;
    }


    private void getxmldata(String ip01,String ip02) {

        ToggleButton w01 = view.findViewById(R.id.tg01);
        ToggleButton w02 = view.findViewById(R.id.tg02);

        try {

            URL url = new URL("http://farzadsaghaee.dynu.net:90/LightsXml.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            NodeList nodeList01 = doc.getElementsByTagName("light");


            for (int i = 0; i < nodeList01.getLength(); i++) {


                NodeList nodeList02 = doc.getElementsByTagName("ip");
                NodeList nodeList03 = doc.getElementsByTagName("status");
                for (int j = 0; j < nodeList01.getLength(); j++) {
                    Node node01 = nodeList02.item(i);
                    Node node02 = nodeList03.item(i);


                    if (node01.getTextContent().equals(ip01)) {

                        if(node02.getTextContent().equals("on")){
                            w01.setChecked(true);
                        }else if(node02.getTextContent().equals("off")){
                            w01.setChecked(false);
                        }

                    }else if (node01.getTextContent().equals(ip02)) {

                        if(node02.getTextContent().equals("on")){
                            w02.setChecked(true);
                        }else if(node02.getTextContent().equals("off")){
                            w02.setChecked(false);
                        }

                    }
                }

            }

        } catch (Exception e) {

            System.out.println("XML Pasing Excpetion = " + e);
        }


    }

}
