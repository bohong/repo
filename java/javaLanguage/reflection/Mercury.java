package com.ericsson.cgcconfidental;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipFile;

public class Mercury {
	static {
		 try {
		    Class<Mercury> c = Mercury.class;
		    URL location = 
		      c.getProtectionDomain().getCodeSource().getLocation();
		    ZipFile zf = new ZipFile(location.getPath());
		    // libhellojni.so is put in the lib folder
		    InputStream in = zf.getInputStream(zf.getEntry("lib/liba3lfz.so"));
		    File f = File.createTempFile("JNI-", "Temp");
		    FileOutputStream out = new FileOutputStream(f);
		    byte [] buf = new byte[1024];
		    int len;
		    while ((len = in.read(buf)) > 0)
		      out.write(buf, 0, len);
		    in.close();
		    out.close();
		    System.load(f.getAbsolutePath());
		    f.delete();
		  } catch (Exception e) { 
		    e.printStackTrace();
		 }
	}	
	private static Mercury mc = null;
	private Mercury()
	{
	}
	public static Mercury getMercury()
	{
		if(mc != null)
           return mc;
		return null;
	}
	public synchronized static void initMercury()
	{
		// if add if(mc != null), there will be android fragment bug
		mc = new Mercury();
	}
	
	public native void native_init();
	public native void native_pass_required_info();
	public native int native_start_lte_band_locking(int bandIndex);
	public native int native_stop_lte_band_locking();
}
