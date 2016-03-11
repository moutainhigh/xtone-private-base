package com.wns.push.util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import com.wns.push.bean.WnsCountry;
import com.wns.push.dao.WnsCountryDao;
import com.wns2.util.WnsSpringHelper;

public class WnsLBSManager
{
    private static DatabaseReader dbReader = null;

    public static String getCountry(String mcc, String ip)
    {
        String ret = null;
        WnsCountryDao dao = (WnsCountryDao) WnsSpringHelper
                .getBean("dcountryDao");
        WnsCountry country = dao.findByMcc(mcc);
        if (country != null)
        {
            ret = country.getCountry();
        }
        else
        {
            ret = getCountryByIp(ip);
        }
        return ret;
    }

    public static String getUnit(String mcc, String ip)
    {
        String ret = null;
        WnsCountryDao dao = (WnsCountryDao) WnsSpringHelper
                .getBean("dcountryDao");
        WnsCountry country = dao.findByMcc(mcc);
        if (country != null)
        {
            ret = country.getUnit();
        }
        return ret;
    }

    public static String getCountryByIp(String ip)
    {
        if (dbReader == null)
        {
            String path = WnsLBSManager.class.getClassLoader().getResource("/")
                    .getPath()
                    + File.separator + "GeoLite2Country.mmdb";
            //GeoLite2Country.mmdb GeoLite2City.mmdb
            File database = new File(path);
            try
            {
                dbReader = new DatabaseReader.Builder(database).build();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            InetAddress ipAddress = InetAddress.getByName(ip);
//            CityResponse response = dbReader.city(ipAddress);
            CountryResponse response = dbReader.country(ipAddress);
            Country country = response.getCountry();
            return country.getIsoCode();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
