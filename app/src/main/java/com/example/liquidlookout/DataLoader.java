package com.example.liquidlookout;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DataLoader{

    private static DataLoader dt = null;

    private static boolean loaded = false;

    private static Thread loader = null;

    private LOL lol;
    private CSGO csgo;

    private DataLoader(final Thread caller) {
        if(loader == null) {
            loader = new Thread(new Runnable() {
                @Override
                public void run() {
                    lol = new LOL();
                    csgo = new CSGO();
                    synchronized (caller) {
                        caller.notify();
                    }
                }
            });
        }
        loader.start();
    }

    public static synchronized void reloadData(Thread caller) {
        dt = new DataLoader(caller);
    }

    public static synchronized void loadData(Thread caller) {
        if(dt == null) {
            synchronized (caller) {
                try {
                    dt = new DataLoader(caller);
                    caller.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            loaded = true;
        }
    }

    public static DataLoader getInstance() {
        if(loaded)
            return dt;
        else {
            System.err.println("loadData() must be called before getInstance()");
            return null;
        }
    }

    public LOL getLol() {
        return lol;
    }

    public CSGO getCsgo() {
        return csgo;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String getWebPageAsString(String purl) {
        String result = "-1";
        try {
            URL url = new URL(purl);
            HttpURLConnection hurl = (HttpURLConnection) url.openConnection();
            try {
                InputStream is = hurl.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                result = readAll(rd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
