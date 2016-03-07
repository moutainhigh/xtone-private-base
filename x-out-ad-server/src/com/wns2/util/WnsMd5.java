package com.wns2.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public class WnsMd5
{
    public static Logger log = Logger.getLogger(WnsMd5.class);

    public static String md5Encryp(String source)
    {
        StringBuffer sb = new StringBuffer(32);
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++)
            {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .toUpperCase().substring(1, 3));
            }
        }
        catch (Exception e)
        {
            log.error("Can not encode the string '" + source + "' to MD5!", e);
            return null;
        }

        return sb.toString();
    }

    public static String md5(String source)
    {
        String result = null;
        try
        {
            result = DigestUtils.md5Hex(source.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            // logger.error("", e);
        }
        return result;
    }

    public static String ngsteamMd5(String source)
    {
        byte[] hash;

        try
        {
            hash = MessageDigest.getInstance("MD5").digest(
                    source.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash)
        {
            int i = (b & 0xFF);
            if (i < 0x10)
                hex.append('0');
            hex.append(Integer.toHexString(i));
        }

        return hex.toString();
    }

    public static String molMd5(String aValue)
    {
        aValue = aValue.trim();
        byte value[];
        try
        {
            value = aValue.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            value = aValue.getBytes();
        }
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        return toHex(md.digest(value));
    }

    public static String toHex(byte input[])
    {
        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++)
        {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }
}
