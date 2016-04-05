
// class loader part lib
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

import java.net.URL;
import java.net.URLClassLoader;
import  java.net.MalformedURLException;

// reflection part lib
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;
import static java.lang.System.out;
import static java.lang.System.err;

public class SecureTester {

    public static void main(String[] args){
        try{
            // class loader part
            SSLContext sslCtx = SSLContext.getInstance("SSL");
            TrustManager trustManager[] = getBypassingTrustManager();
            sslCtx.init(null, trustManager, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            URL[] classLoaderUrls = new URL[]{
                //new URL("http://localhost:8080/signalmap/ClassToReflect.jar")
                new URL("https://localhost:8443/signalmap/ClassToReflect.jar")
            };

            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

            Class<?> c = urlClassLoader.loadClass("ClassToReflect");

            System.out.println(c.getName());


            // reflection part
            // to dynamicly load jar from network, could not use Class.forName
            // have to use ClassLoader.loadClass
            //Class<?> c = Class.forName("ClassToReflect");
            System.out.println(c.getName());
            Method[] methods = c.getDeclaredMethods();
            for(Method method : methods){
                System.out.println(method.getName());
            }
            Class[] staticLockBandArgTypes = new Class[]{Integer.class};
            Method staticLockBand = c.getDeclaredMethod("staticLockBand", staticLockBandArgTypes);
            System.out.println(staticLockBand.getName());
            staticLockBand.setAccessible(true);
            staticLockBand.invoke(null, 100);
            Method staticUnlockBand = c.getDeclaredMethod("staticUnlockBand");
            System.out.println(staticUnlockBand.getName());
            staticUnlockBand.setAccessible(true);
            staticUnlockBand.invoke(null);


            Object o = c.newInstance();
            Class[] lockBandArgTypes = new Class[]{Integer.class};
            Method lockBand = c.getDeclaredMethod("lockBand", lockBandArgTypes);
            System.out.println(lockBand.getName());
            lockBand.setAccessible(true);
            lockBand.invoke(o, 100);
            Method unlockBand = c.getDeclaredMethod("unlockBand");
            System.out.println(unlockBand.getName());
            unlockBand.setAccessible(true);
            unlockBand.invoke(o);

        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x){
            x.printStackTrace();
        } catch (NoSuchAlgorithmException x){
            x.printStackTrace();
        } catch (KeyManagementException x){
            x.printStackTrace();
        } catch (MalformedURLException x){
            x.printStackTrace();
        }
    }

    private static TrustManager[] getBypassingTrustManager(){

        TrustManager[] certs = new TrustManager[] {
            new X509TrustManager(){
                public X509Certificate[] getAcceptedIssuers(){
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String t){
                }
                public void checkServerTrusted(X509Certificate[] certs, String t){
                }
            }
        };
        return certs;
    }

    private static HostnameVerifier hv = (urlHostName, section) -> {

        //return urlHostName.equals(section.getPeerHost());
        return true;
    };

}
