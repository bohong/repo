package com.signalmapsrs;


import android.content.Context;

import java.io.*;
import java.net.*;

import javax.net.ssl.*;
import java.security.cert.*;
/**
 * Created by ebohong on 2016/3/15.
 */
public class SignalMapLibDownloader {
    SignalMapLibDownloader(Context context) {
        this.context = context;

    }

    //for testing if url successfully download and saved to file
    public void utilTestFunc(String filename){
        FileInputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        int charRead = 0;
        char[] inputBuffer = new char[2048];
        String s = "";

        try{
            inputStream = context.openFileInput(filename);
            inputStreamReader = new InputStreamReader(inputStream);
            while((charRead = inputStreamReader.read(inputBuffer)) > 0){
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            System.out.println(s);

            inputStream.close();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public boolean download(String urlstr, String filename){
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] tms = {ignoreCertificationTrustManger};
            sslContext.init(null, tms, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(urlstr);
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            HttpsURLConnection conn = ((HttpsURLConnection) url.openConnection());
            conn.setSSLSocketFactory(ssf);

            FileOutputStream outputStream = null;
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            copyInputStreamToOutputStream(conn.getInputStream(), outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Boolean.TRUE;
    }

    public boolean deleteFile(String filename){
        return context.deleteFile(filename);
    }

    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {

        private X509Certificate[] certificates;

        @Override
        public void checkClientTrusted(X509Certificate certificates[],
                                       String authType) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = certificates;
            }
        }

        @Override
        public void checkServerTrusted(X509Certificate[] ax509certificate,
                                       String s) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = ax509certificate;
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    private static HostnameVerifier hv = new HostnameVerifier(){
        public boolean verify(String urlHostName, SSLSession section){
            return urlHostName.equals(section.getPeerHost());
            //return true;
        }
    };

    private static void copyInputStreamToOutputStream(final InputStream in,
                                                     final OutputStream out) throws IOException
    {
        try
        {
            try
            {
                final byte[] buffer = new byte[1024];
                int n;
                while ((n = in.read(buffer)) != -1)
                    out.write(buffer, 0, n);
            }
            finally
            {
                out.close();
            }
        }
        finally
        {
            in.close();
        }
    }

    private Context context;
}
