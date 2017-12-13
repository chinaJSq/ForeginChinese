# ForeginChinese<br>
##（1）创建activity与fragment<br>
新建activity放弃继承安卓原生的Activity。一律继承BaseActivity。<br>
BaseActivity功能包括：<br>
手势返回（开/关）<br>
Activty任务栈管理<br>
统一的日志打印方法<br>
同一的toast提醒方法<br><br>
Activty的跳转方法与动画<br>
实现：继承BaseActivity并实现下面的方法<br>
 	>>>>void onInitViews();//初始化页面布局<br>
 	>>>>void onInitListeners();//初始化页面接口<br>
 	>>>>void onInitData();//初始化页面数据<br>
void onClick();//初始化页面点击事件<br>
fragment继承BaseFragment并实现下面的方法<br>
int getLayoutId()//加载页面布局<br>
void onInitViews();//初始化页面布局<br>
void onInitListeners();//初始化页面接口<br>
 	void onInitData();//初始化页面数据<br>
void onClick();//初始化页面点击事件<br>
##（2）图片加载方法（本地/网络）<br>
ImageUtils类是封装好的网络图片加载方法，使用方法也简单如下：<br>
 .getInstance().display(imageView, img.src);<br>
##（3）网络连接方法<br>
	网络连接封装了OkHTTP。使用方法如下<br>
###1、实现HttpListener<T>接口<br>
###2、发出请求<br>
 Request<String> goodRequest = <br>
NoHttp.createStringRequest(ConstanInterface.RECOMMEND_URL, RequestMethod.GET);<br>
CallServer.getInstance().add(this, GOOD_REQUEST, goodRequest, this, true, true);<br>
//GOOD_REQUEST是自定义的请求码<br>
###3、处理返回结果<br>
实现void onSucceed(int what, Response<String> response){<br>
 switch (what) {<br>
            case GOOD_REQUEST:<br>
Break;<br>
}<br>
void onFailed(int what, Response<String> response){<br>
 switch (what) {<br>
            case GOOD_REQUEST:<br>
Break;<br>
}<br>
两个方法<br>
##（4）全局信息存储<br>
全局信息以及一些全局方法的初始化放在MyApplication类中<br>
##（5）网络请求接口<br>
网络请求接口一律写在ConstanInterface类中，且声明为常量<br>
##（6）一些工具类的说明<br>
DateUtil日期工具类处理日期，以及计算时间差距<br>
DialogUtil对话框类，实现对话框，同一调用接口<br>
FileUtils文件处理类，读写本地文件，assets文件，raw下文件<br>
Utils获取屏幕信息，尺寸转换等一些方法<br>
JsonUtil一些特殊json格式的解析<br>
ImageUtils图片管理类，加载中、失败、成功下的反应<br>
PermissionHelper权限类，权限的获<br>取，声明，处理<br>
##（7）一些包的说明<br>
Commmon 常用方法集合<br>
Listener常用接口声明<br>
Model javabean集合<br>
Service 服务的集合<br>
Ui 具体功能实现<br>
![](http://222.192.6.54/UpLoadResource/1.png)

