package com.wns2.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.xmlpull.v1.XmlPullParser;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;

public class WnsAnalysisApk
{

    @SuppressWarnings("unchecked")
    public static List<String> unZipIcon(String fileUrl, String logoUrl,
            String iconName)
    {
      //[0]:版本号;[1]包名
        boolean find = false;
        byte b[] = new byte[65535];
        List<String> result = new ArrayList<String>();
        int length;
        ZipFile zipFile;
        try
        {
            zipFile = new ZipFile(fileUrl);
            Enumeration enumeration = zipFile.entries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements())
            {
                zipEntry = (ZipEntry) enumeration.nextElement();
                if (zipEntry.isDirectory())
                {

                }
                else
                {
                    if (iconName.equals(zipEntry.getName()))
                    {
                        OutputStream outputStream = new FileOutputStream(
                                logoUrl);
                        InputStream inputStream = zipFile
                                .getInputStream(zipEntry);
                        while ((length = inputStream.read(b)) > 0)
                            outputStream.write(b, 0, length);
                        outputStream.close();
                        find = true;
                        break;
                    }
                    // if("META-INF/CERT.RSA".equals(zipEntry.getName())){
                    // InputStream is = zipFile.getInputStream(zipEntry);
                    // PKCS7 pkcs7 = new PKCS7(is);
                    // X509Certificate publicKey = pkcs7.getCertificates()[0];
                    // result.add(publicKey.getSerialNumber().toString());
                    // }
                }
            }
            zipFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (find)
        {
            result.add("Y");
        }
        else
        {
            result.add("N");
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static String[] unZip(String fileUrl, String logoUrl)
    {
      //[0]:版本号;[1]包名
        String[] st = new String[3];
        byte b[] = new byte[65535];
        int length;
        ZipFile zipFile;
        try
        {
            zipFile = new ZipFile(fileUrl);
            Enumeration enumeration = zipFile.entries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements())
            {
                zipEntry = (ZipEntry) enumeration.nextElement();
                if (zipEntry.isDirectory())
                {

                }
                else
                {
                    if ("AndroidManifest.xml".equals(zipEntry.getName()))
                    {
                        try
                        {
                            AXmlResourceParser parser = new AXmlResourceParser();
                            parser.open(zipFile.getInputStream(zipEntry));
                            while (true)
                            {
                                int type = parser.next();
                                if (type == XmlPullParser.END_DOCUMENT)
                                {
                                    break;
                                }
                                switch (type)
                                {
                                    case XmlPullParser.START_TAG:
                                    {
                                        for (int i = 0; i != parser
                                                .getAttributeCount(); ++i)
                                        {
                                            System.out.println(parser
                                                    .getAttributeName(i));
                                            System.out.println(parser
                                                    .getAttributeValueType(i));
                                            System.out.println(parser
                                                    .getAttributeValueData(i));
                                            if ("versionName".equals(parser
                                                    .getAttributeName(i)))
                                            {
                                                st[0] = getAttributeValue(
                                                        parser, i);
                                                System.out.println(st[0]);
                                            }
                                            else if ("package".equals(parser
                                                    .getAttributeName(i)))
                                            {
                                                st[1] = getAttributeValue(
                                                        parser, i);
                                            }
                                            else if ("icon".equals(parser
                                                    .getAttributeName(i)))
                                            {
                                                st[2] = getAttributeValue(
                                                        parser, i);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if ("res/drawable-ldpi/icon.png".equals(zipEntry.getName()))
                    {
                        OutputStream outputStream = new FileOutputStream(
                                logoUrl);
                        InputStream inputStream = zipFile
                                .getInputStream(zipEntry);
                        while ((length = inputStream.read(b)) > 0)
                            outputStream.write(b, 0, length);
                    }

                }
            }
            zipFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return st;
    }

    private static String getAttributeValue(AXmlResourceParser parser, int index)
    {
        int type = parser.getAttributeValueType(index);
        int data = parser.getAttributeValueData(index);
        if (type == TypedValue.TYPE_STRING)
        {
            return parser.getAttributeValue(index);
        }
        if (type == TypedValue.TYPE_ATTRIBUTE)
        {
            return String.format("?%sX", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_REFERENCE)
        {
            System.out.println(getPackage(data));
            return String.format("@%sX", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_FLOAT)
        {
            return String.valueOf(Float.intBitsToFloat(data));
        }
        if (type == TypedValue.TYPE_INT_HEX)
        {
            return String.format("0xX", data);
        }
        if (type == TypedValue.TYPE_INT_BOOLEAN)
        {
            return data != 0 ? "true" : "false";
        }
        if (type == TypedValue.TYPE_DIMENSION)
        {
            return Float.toString(complexToFloat(data))
                    + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type == TypedValue.TYPE_FRACTION)
        {
            return Float.toString(complexToFloat(data))
                    + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type >= TypedValue.TYPE_FIRST_COLOR_INT
                && type <= TypedValue.TYPE_LAST_COLOR_INT)
        {
            return String.format("#X", data);
        }
        if (type >= TypedValue.TYPE_FIRST_INT
                && type <= TypedValue.TYPE_LAST_INT)
        {
            return String.valueOf(data);
        }
        return String.format("<0x%X, type 0xX>", data, type);
    }

    private static String getPackage(int id)
    {
        System.out.println(id >>> 24);
        if (id >>> 24 == 1)
        {
            return "android:";
        }
        return "android:";
    }

    // ///////////////////////////////// ILLEGAL STUFF, DONT LOOK :)
    public static float complexToFloat(int complex)
    {
        return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
    }

    private static final float  RADIX_MULTS[]     = { 0.00390625F,
            3.051758E-005F, 1.192093E-007F, 4.656613E-010F };
    private static final String DIMENSION_UNITS[] = { "px", "dip", "sp", "pt",
            "in", "mm", "", ""                   };
    private static final String FRACTION_UNITS[]  = { "%", "%p", "", "", "",
            "", "", ""                           };

}
