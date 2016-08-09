
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.spi.*;
import java.util.*;

public class DNSInfo {

    public static String[] getRecords(String hostName, String type){

        Set<String> results = new TreeSet<String>();

        try{
            Hashtable<String, String> envProps = new Hashtable<String, String>();
            envProps.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
            envProps.put(Context.PROVIDER_URL, "dns://ns1-03.azure-dns.com"); 
            //envProps.put(Context.PROVIDER_URL, "dns://8.8.8.8 dns://ns1-03.azure-dns.com"); 
            //envProps.put(Context.PROVIDER_URL, "dns://ns1-03.azure-dns.com dns://ns1-03.azure-dns.net"); 
            DirContext dnsContext = new InitialDirContext(envProps);
            Attributes dnsEntries = dnsContext.getAttributes(hostName, new String[]{type});
            if(dnsEntries != null){
                NamingEnumeration<?> dnsEntryIterator = dnsEntries.get(type).getAll();
                while(dnsEntryIterator.hasMoreElements()){
                    results.add(dnsEntryIterator.next().toString());

                }

            }
        } catch(NamingException e){

        }
        return results.toArray(new String[results.size()]);

    }

    public static void main(String[] args){
        for(int i = 0; i < args.length; i++){

            System.out.println(args[i]);
        }
        String[] strarr = getRecords(args[0], args[1]);
        for(int j = 0; j < strarr.length; j++){
            System.out.println(strarr[j]);
        }

    }
}
