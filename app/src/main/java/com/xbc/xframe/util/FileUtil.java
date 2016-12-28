package com.xbc.xframe.util;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by xiaobo.cui on 2016/9/23.
 */
public class FileUtil {

    /**
     * 创建文件
     *
     * @param filePath
     */
    public static void createFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        createFile(new File(filePath));
    }

    public static void createFile(String directory, String fileName) {
        if (TextUtils.isEmpty(directory) || TextUtils.isEmpty(fileName)) {
            return;
        }
        createFile(directory + File.separator + fileName);
    }

    public static void createFile(File file) {
        if (file == null) {
            return;
        }
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        deleteFile(new File(filePath));
    }

    public static void deleteFile(File file) {
        try {
            if (file != null && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹
     *
     * @param file
     */
    public static void deleteDir(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        } else {
            file.delete();
        }
    }

    /**
     * 复制文件
     */
    public static boolean copyFile(String fromFilePath, String toFilePath) {
        return copyFile(fromFilePath, toFilePath, false);
    }

    /**
     * 复制文件
     *
     * @param fromFilePath
     * @param toFilePath
     * @param deleteFromFile
     * @return
     */
    public static boolean copyFile(String fromFilePath, String toFilePath, boolean deleteFromFile) {
        boolean ret = false;
        if (TextUtils.isEmpty(fromFilePath) || TextUtils.isEmpty(toFilePath)) {
            return false;
        }
        File fromFile = new File(fromFilePath);
        if (!fromFile.exists()) {
            return false;
        }
        File toFile = new File(toFilePath);
        createFile(toFile);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(fromFile);
            fos = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];
            int c = -1;
            while ((c = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, c);
            }
            fos.flush();
            ret = true;
            if (deleteFromFile) {
                deleteFile(fromFile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


    /**
     * 解压文件
     *
     * @param zipFilePath
     * @param toDirectory
     * @return
     */
    public static boolean unZipFile(String zipFilePath, String toDirectory) {
        boolean ret = false;
        if (TextUtils.isEmpty(zipFilePath) || TextUtils.isEmpty(toDirectory)) {
            return false;
        }
        if (!new File(zipFilePath).exists()) {
            return false;
        }
        try {
            File desDir = new File(toDirectory);
            if (!desDir.exists()) {
                desDir.mkdirs();
            }
            ZipFile zf = new ZipFile(new File(zipFilePath));
            Enumeration entries = zf.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                InputStream in = zf.getInputStream(entry);
                String str = toDirectory + File.separator + entry.getName();
                str = new String(str.getBytes("8859_1"), "GB2312");
                if (entry.isDirectory()) {
                    if (str.endsWith("/")) {
                        str = str.substring(0, str.lastIndexOf("/"));
                    }
                    new File(str).mkdirs();
                    continue;
                }
                File desFile = new File(str);
                createFile(desFile);
                OutputStream out = new FileOutputStream(desFile);
                byte buffer[] = new byte[1024];
                int c = -1;
                while ((c = in.read(buffer)) > 0) {
                    out.write(buffer, 0, c);
                }
                out.close();
                in.close();
            }
            ret = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }


}
