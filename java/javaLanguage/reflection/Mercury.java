import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipFile;

public class Mercury {
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
	
	public void native_init() {
            System.out.println("native_init called");
        }
	public void native_pass_required_info(){
            System.out.println("native_pass_required_info called");
        }

	public Integer native_start_lte_band_locking(Integer bandIndex){
            System.out.println("native_start_lte_band_locking called");
            return 99;
        }
	public Integer native_stop_lte_band_locking(){
            System.out.println("native_stop_lte_band_locking called");
            return 999;
        }
}
