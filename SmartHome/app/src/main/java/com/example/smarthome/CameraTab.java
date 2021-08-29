package com.example.smarthome;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class CameraTab extends Fragment {
    View view;


    public CameraTab() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_camera, container, false);


         WebView myWebView = view.findViewById(R.id.webview);
         myWebView.loadUrl("http://IP/Ipcam.html");


        myWebView.getSettings().setLoadWithOverviewMode(true);
         myWebView.getSettings().setUseWideViewPort(true);
         myWebView.getSettings().setJavaScriptEnabled(true);



        return view;
    }





}
