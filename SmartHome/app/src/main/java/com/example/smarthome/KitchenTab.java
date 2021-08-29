package com.example.smarthome;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KitchenTab extends Fragment {
    View view;
    public KitchenTab() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kitchen, container, false);

        ToggleButton toggle01 = view.findViewById(R.id.tg01);
        toggle01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    OkHttpClient client=new OkHttpClient();
                    String url="http://IP/index.php?ip=192.168.0.104&status=on";
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
                    String url="http://IP/index.php?ip=192.168.0.104&status=off";
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
                    getxmldata("192.168.0.104");
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
        });

        thread.start();





        return view;
    }

    private void getxmldata(String ip) {

        ToggleButton w = view.findViewById(R.id.tg01);

        try {

            URL url = new URL("http://IP/LightsXml.xml");
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


                    if (node01.getTextContent().equals(ip)) {

                        if(node02.getTextContent().equals("on")){
                            w.setChecked(true);
                        }else if(node02.getTextContent().equals("off")){
                            w.setChecked(false);
                        }



                    }
                }

            }

        } catch (Exception e) {

            System.out.println("XML Pasing Excpetion = " + e);
        }


    }



}
