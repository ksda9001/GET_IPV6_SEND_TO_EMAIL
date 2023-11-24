package org.ipget;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IP {
    public static String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    public static String getLocalIP() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String[] getLocalIPs() throws SocketException {
        List<String> list = new ArrayList<>();
        Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
        while (enumeration.hasMoreElements()) {
            NetworkInterface intf = enumeration.nextElement();
            if (intf.isLoopback() || intf.isVirtual()) {
                continue;
            }
            Enumeration<InetAddress> inets = intf.getInetAddresses();
            while (inets.hasMoreElements()) {
                InetAddress addr = inets.nextElement();
                if (addr.isLoopbackAddress() || addr.isSiteLocalAddress() || addr.isAnyLocalAddress() || addr.isLinkLocalAddress() || addr.isMulticastAddress()) {
                    continue;
                }
                list.add(addr.getHostAddress());
            }
        }
        return list.toArray(new String[0]);
    }
}