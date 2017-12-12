package nnu.net.foreginchinese.util;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import nnu.net.foreginchinese.common.MyApplication;


public class FileUtils {

    /**
     * 以文件分隔符结尾"/"
     */
    private static final String ROOT_PATH = MyApplication.ROOT_PATH;

    public static String readTextFromDisk(String fileName) {

        String path = fileName;
        if (!path.contains(ROOT_PATH)) {
            path = ROOT_PATH + path;
        }
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        if (file.exists()) {
            try {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    result.append(lineTxt);
                }
                bufferedReader.close();
                read.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    public static void saveText2Disk(String fileName, String content) {

        String path = fileName;
        if (!path.contains(ROOT_PATH)) {
            path = ROOT_PATH + path;
        }
        File file = new File(path);
        try {
            // 如果文件不存在，则在磁盘创建文件
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.append(content);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readAssertsFile(Context context, String name) {
        try {
            AssetManager assetManager = context.getResources().getAssets();
            InputStream inputStream = assetManager.open(name);
            String result = readInputStream(inputStream);
            inputStream.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readInputStream(InputStream inputStream) {

        StringBuilder sBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line = br.readLine();
            while (line != null) {
                sBuilder.append(line);
                sBuilder.append("\n");
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                br = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sBuilder.toString();
    }
    public static void chmod(String permission, String path) {
        try {
            String command = "chmod " + permission + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
