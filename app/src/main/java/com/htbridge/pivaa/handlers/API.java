package com.htbridge.pivaa.handlers;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class API extends AppCompatActivity {
    public API() {

    }

    /**
     * Perform login to API by HTTP
     * @return
     */
    public String getURL(String inputedURL) {
        HttpURLConnection urlConnection = null;
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(inputedURL);

            if (url.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                urlConnection = https;
            } else {
                urlConnection = (HttpURLConnection) url.openConnection();
            }

            InputStreamReader inputStream = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader in = new BufferedReader(inputStream);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
                sb.append(inputLine).append("\n");
            }
            in.close();
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return "";
    }
}
