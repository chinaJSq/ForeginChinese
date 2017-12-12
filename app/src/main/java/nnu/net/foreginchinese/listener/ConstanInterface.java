package nnu.net.foreginchinese.listener;

import android.os.Build;
import android.os.Environment;

/**
 * Created by Administrator on 2017/11/15.
 */

public interface ConstanInterface {
    public  static final  String LOCALPATH = Environment.getExternalStorageState();
    public  static final  String BASEURL = "http://222.192.6.54/UpLoadResource/";
    public  static final  String IMGURL = "http://222.192.6.55/MyIMG/";
    public  static final  String AIDAZI ="http://222.192.6.56/Writing1.0/CalligraphProject.apk";
    public  static final  String ZILEYUAN ="http://222.192.6.54/CharacterDemo1.0/CharacterLibrary.apk";
    public  static final  String NEWS = BASEURL+"GetNewsServlet";
    public  static final String version = Build.VERSION.RELEASE;
    public  static final double versionId =  Integer.parseInt(version.substring(0, 1))+ Integer.parseInt(version.substring(2, 3))*0.1;
}
