# ForeginChinese
（1）创建activity与fragment
新建activity放弃继承安卓原生的Activity。一律继承BaseActivity。
BaseActivity功能包括：
手势返回（开/关）
Activty任务栈管理
统一的日志打印方法
同一的toast提醒方法
Activty的跳转方法与动画
实现：继承BaseActivity并实现下面的方法
 	void onInitViews();//初始化页面布局
 	void onInitListeners();//初始化页面接口
 	void onInitData();//初始化页面数据
void onClick();//初始化页面点击事件
fragment继承BaseFragment并实现下面的方法
int getLayoutId()//加载页面布局
void onInitViews();//初始化页面布局
void onInitListeners();//初始化页面接口
 	void onInitData();//初始化页面数据
void onClick();//初始化页面点击事件
（2）图片加载方法（本地/网络）
ImageUtils类是封装好的网络图片加载方法，使用方法也简单如下：
 .getInstance().display(imageView, img.src);
（3）网络连接方法
	网络连接封装了OkHTTP。使用方法如下
1、实现HttpListener<T>接口
2、发出请求
 Request<String> goodRequest = 
NoHttp.createStringRequest(ConstanInterface.RECOMMEND_URL, RequestMethod.GET);
CallServer.getInstance().add(this, GOOD_REQUEST, goodRequest, this, true, true);
//GOOD_REQUEST是自定义的请求码
3、处理返回结果
实现void onSucceed(int what, Response<String> response){
 switch (what) {
            case GOOD_REQUEST:
Break;
}
void onFailed(int what, Response<String> response){
 switch (what) {
            case GOOD_REQUEST:
Break;
}
两个方法
（4）全局信息存储
全局信息以及一些全局方法的初始化放在MyApplication类中
（5）网络请求接口
网络请求接口一律写在ConstanInterface类中，且声明为常量
（6）一些工具类的说明
DateUtil日期工具类处理日期，以及计算时间差距
DialogUtil对话框类，实现对话框，同一调用接口
FileUtils文件处理类，读写本地文件，assets文件，raw下文件
Utils获取屏幕信息，尺寸转换等一些方法
JsonUtil一些特殊json格式的解析
ImageUtils图片管理类，加载中、失败、成功下的反应
PermissionHelper权限类，权限的获取，声明，处理
（7）一些包的说明
Commmon 常用方法集合
Listener常用接口声明
Model javabean集合
Service 服务的集合
Ui 具体功能实现

