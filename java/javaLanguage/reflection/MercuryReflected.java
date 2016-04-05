package com.signalmapsrs;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import dalvik.system.DexClassLoader;

/**
 * Created by ebohong on 2016/4/5.
 */
public class MercuryReflected {

    public MercuryReflected(Context context){

        this.context = context;

        // normally network/io activity needs to be performed on thread other than the main thread.
        // below code to allow io on main thread.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            SignalMapLibDownloader signalMapLibDownloader = new SignalMapLibDownloader(context);
            signalMapLibDownloader.download("https://192.168.1.213:8443/signalmap/Mercury.jar", "Mercury.jar");

            DexClassLoader dexClassLoader = new DexClassLoader(context.getFilesDir().getPath() + "/Mercury.jar", context.getFilesDir().getPath(), null, context.getClassLoader());

            Class<?> c = dexClassLoader.loadClass("com.ericsson.cgcconfidental.Mercury");

            Log.i("MercuryReflected", c.getName() + "loaded");

            Method initMercury = c.getMethod("initMercury");
            initMercury.invoke(null);
            Method getMercuryInstance = c.getMethod("getMercury");

            mercuryInstance = getMercuryInstance.invoke(null);

            native_init_mercury = c.getMethod("native_init");
            native_pass_required_info_mercury = c.getMethod("native_pass_required_info");
            native_start_lte_band_locking_mercury = c.getMethod("native_start_lte_band_locking", new Class[]{Integer.class});
            native_stop_lte_band_locking_mercury = c.getMethod("native_stop_lte_band_locking");

            signalMapLibDownloader.deleteFile("Mercury.jar");

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void native_init(){

        try {
            native_init_mercury.invoke(mercuryInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void native_pass_required_info(){

        try {
            native_pass_required_info_mercury.invoke(mercuryInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public int native_start_lte_band_locking(int bandIndex){

        int result = 0;
        try {
            result = (Integer) native_start_lte_band_locking_mercury.invoke(mercuryInstance, bandIndex);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int native_stop_lte_band_locking(){

        int result = 0;
        try {
            result = (Integer) native_stop_lte_band_locking_mercury.invoke(mercuryInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
    private Context context;
    private Object mercuryInstance;
    private Method native_init_mercury;
    private Method native_pass_required_info_mercury;
    private Method native_start_lte_band_locking_mercury;
    private Method native_stop_lte_band_locking_mercury;
}
