package com.abt.common.data.file;

import android.util.Log;

import com.abt.common.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @描述： @FileHelperImpl
 * @作者： @黄卫旗
 * @创建时间： @29/08/2018
 */
public class FileHelperImpl<T> implements FileHelper<T> {

    private static final String TAG = FileHelperImpl.class.getSimpleName();
    private static final String projectPath = "april";

    /**
     * 数据存放在本地
     */
    public void saveStorage2SDCard(List<T> arrayList, String fileName) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            File file = FileUtil.getFilePath(File.separator + FileHelperImpl.projectPath + File.separator, fileName);
            Log.d(TAG, "saveStorage2SDCard file: "+file.getAbsolutePath().toString());
            fileOutputStream = new FileOutputStream(file.toString());  //新建一个内容为空的文件
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            Log.d(TAG, "objectOutputStream.writeObject(arrayList)");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (objectOutputStream != null) {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取本地的List数据
     */
    public ArrayList<T> getStorageEntities(String fileName) {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        ArrayList<T> savedArrayList = new ArrayList<>();
        try {
            File file = FileUtil.getFilePath(File.separator + FileHelperImpl.projectPath + File.separator, fileName);
            Log.d(TAG, "getStorageEntities file: "+file.getAbsolutePath().toString());
            fileInputStream = new FileInputStream(file.toString());
            objectInputStream = new ObjectInputStream(fileInputStream);
            savedArrayList = (ArrayList<T>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedArrayList;
    }
}
