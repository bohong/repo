import java.net.*;
class DnsTesting {

    public static void main(String [] args) {
        InetAddress add = null;
        //System.setProperty("sun.net.spi.nameservice.nameservers", "ns1-03.azure-dns.com");
        String str = System.getProperty("sun.net.spi.nameservice.nameservers");
        System.out.println(str);
        str = System.getProperty("sun.net.spi.nameservice.provider.1");
        System.out.println(str);

        System.setProperty("sun.net.spi.nameservice.nameservers", "40.90.4.3");
        System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");

        try{
        add = InetAddress.getByName("service.smlogparse.com");
        }catch(Exception e){}
        System.out.println(add);

        //System.clearProperty("sun.net.spi.nameservice.nameservers");
        //System.clearProperty("sun.net.spi.nameservice.provider.1");
        System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8");
        System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");

        InetAddress add2 = null;
        try{
        add2 = InetAddress.getByName("www.microsoft.com");
        }catch(Exception e){}
        System.out.println(add2);
        try{
        add2 = InetAddress.getByName("www.oracle.com");
        }catch(Exception e){}
        System.out.println(add2);


    }


}
