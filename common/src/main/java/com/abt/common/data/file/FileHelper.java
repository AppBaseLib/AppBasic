package com.abt.common.data.file;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述： @FileHelper
 * @作者： @黄卫旗
 * @创建时间： @29/08/2018
 */
public interface FileHelper<T> {

    void saveStorage2SDCard(List<T> arrayList, String fileName);

    ArrayList<T> getStorageEntities(String fileName);

}
