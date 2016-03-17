package com.wns2.factory;

import java.io.File;
import java.util.Date;

import com.wns.push.admin.bean.WnsFile;
import com.wns2.base.bean.WnsSysProperty;
import com.wns2.util.WnsSpringHelper;

public class WnsFileFactory
{
    public static String[] addFile(String name, long size)
    {
//        NgsteamFileDao fileDao = (NgsteamFileDao) NgsteamSpringHelper
//                .getBean("dFileDao");
        String path = ((WnsSysProperty) WnsSpringHelper.getBean("bSysProperty"))
                .getUploadPath();

        String fileName = name.substring(name.lastIndexOf('\\') + 1, name
                .length());
        if (!(new File(path).exists()))
        {
            new File(path).mkdirs();
        }

        WnsFile file = new WnsFile();
        file.setFilename(fileName);
        file.setSize(size);
        fileName =   new Date().getTime() + fileName;
        while (new File(path + File.separatorChar + fileName).exists())
        {
            fileName =  new Date().getTime() + fileName;
//            fileName = NgsteamMd5.md5(fileName);
        }
        fileName = path + File.separatorChar + fileName;
        file.setFile(fileName);
//        long id = fileDao.insert(file);
        String [] ret = new String[2];
        ret[0] = fileName;
        ret[1] = fileName.replace(path + File.separatorChar, "");
        return ret;
    }
}
