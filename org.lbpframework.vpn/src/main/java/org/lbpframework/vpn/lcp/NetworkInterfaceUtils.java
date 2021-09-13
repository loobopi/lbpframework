package org.lbpframework.vpn.lcp;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 网络接口卡操作工具类 ；
 */
public class NetworkInterfaceUtils {

    /**
     * 根据网卡名称获取网卡
     * @param name
     * @return
     */
    public static NetworkInterface getNetworkInterfaceByName(String name){
        NetworkInterface[] deviceList = JpcapCaptor.getDeviceList();

        List<NetworkInterface> filters = Arrays.stream(deviceList)
                .filter(networkInterface -> networkInterface.name.equals(name))
                .collect(Collectors.toList());
        return filters.get(0);
    }

    /**
     * 根据ip获取网卡;
     * @param name
     * @return
     */
    public static NetworkInterface getNetworkIntefaceByAddress(String name){
        NetworkInterface[] deviceList = JpcapCaptor.getDeviceList();

        List<NetworkInterface> filters = Arrays.stream(deviceList)
                .filter(networkInterface -> {
                    NetworkInterfaceAddress[] addresses = networkInterface.addresses;
                    for (NetworkInterfaceAddress address : addresses) {
                        if(address.address.equals(name)){
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
        return filters.get(0);
    }

}
