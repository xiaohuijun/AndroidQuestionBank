 
Activity的启动模式、任务栈以及使用场景？
Volley能否进行数据量很大的请求，比如说加载100M的图片？
手写快速排序
Volley的优缺点
final修饰一个对象，能否调用对象修改属性的方法
子线程中如何使用Handler
如何进行单元测试,如何保证APP稳定。

TabLayout如何设置指示器的宽度包裹内容？
公差为1的等差数列求和，如何优化

自己的优势在哪里
注解如何获取，反射为何耗性能？
Java的GC机制，分代回收策略
Binder机制：ServiceManager什么时候注册的？
int,long的取值范围以及BigDecimal，数值越界了如何处理？
ConcurrentHashMap的原理，分的段数是多少？
APP是如何适配的？
Android中如何查看一个对象的回收情况？
最大一次线上Bug处理措施

APK的大小如何压缩
APP的崩溃率，多渠道包

MVP如何管理Presenter的生命周期，何时取消网络请求
用过的框架及原理
天猫的Tangram布局，vLayout原理

APP的启动流程
回形打印二维数组
Java为何引入泛型，泛型边界
常见的Java数据结构
ArrayMap跟SparseArray在HashMap上面的改进
Java的四种引用及使用场景
Webview性能优化

Binder机制
ClassLoader的理解
Arouter的原理
组件化原理，组件化中路由的实现
热修复跟插件化的原理
线程池的几个参数的理解，四种线程池的使用场景
常用的设计模式，手写DCL单例模式
进程间为什么不能直接进行通信
应用跟系统之间通信什么时候用Socket什么时候用Binder

Debug跟Release的APK的区别
软引用跟弱引用的区别
ConcurrentHashmap的原理
Listview跟Recyclerview的区别（刷新，缓存，各自的使用场景）
对谷歌新推出的Room架构
动画的两种实现：setX跟setTranslationX区别
事件分发：Activity跟window依次到viewgroup
Volley源码，优缺点
Picasso传入的Activity跟Application的context的区别

数据库的升级，表结构的修改
常见的排序算法，手写二分查找，二分查找的复杂度分析
点击APP图标启动APP的大致流程
SplashActivity中进行初始化MainActivity的参数，Splash没有初始化，AMS直接启动了MainActivity怎么办
设计一个多线程，可以同时读，读的时候不能写，写的时候不能读(读写锁)
设计断点续传，如何判断服务端是否支持断点续传
Binder机制：Binder内核所在的进程是如何找到到Server跟Client进行通信的
对JNI是否了解
组件化的原理，组件间通信

一个线程能否创建多个Handler，Handler跟Looper之间的对应关系
ArrayList跟HashMap是够线程安全，如何保证线程安全
Volley返回数据量比较大的请求时怎么办
ListView跟RecyclerView刷新数据原理，使用到的设计模式
适配如何做，有没有适配过全面屏
Fragment跟Activity的通信方式，你知道几种？

内存泄露的种类
ActivityA启动ActivityB时生命周期的变化
AMS是如何管理Activity的
JVM中的GC回收机制
常见的跨进程通信方式，AIDL的实现原理
APP以及Activity的启动流程
Hook以及插桩技术

从长度为m的int数组中随机取出n个元素，每次取的元素都是之前未取过的，如何优化
https是如何保证安全的，证书如何校验
Android的签名机制，APK包含哪些东西
是否遇到过OOM，如何解决
是否做过逆向
对JNI了解多少
手写单例(DCL)
是否写过so文件
热修复原理，如何进行dex替换的

简单说下项目
内存泄露，如何检测以及检测的原理
点击Launcher跟点击微信支付启动微信有什么区别
逆序一个字符串，不能调用String的reverse方法(考察编码风格)
组件化原理，模块化机制
没有给权限如何定位，特定机型定位失败，如何解决
同步跟异步机制(是业务上的，不知道他想问什么)
线程池中核心线程数跟最大线程数如何定义

StringBuffer跟StringBuilder之间的区别
并发相关，各种锁
View的绘制流程，事件分发
Hashmap,Arraymap，SparseArray
四种引用
MVP跟MVC的区别
LV跟RV之间的区别
Service相关，startService以及bindService
Fragment不同于Activity的生命周期
生产者消费者问题：一对多，多对多情况
常见的设计模式，并选择一种描述一下
Scroller的使用

Gradle生命周期
Java&Android关于Classloader源码解析
Python的class文件
DCL中Volatile的作用，如何防止反射实例化单例对象
抽象类能否实例化，理论依据是什么？
如何通过Gradle配置差异较大(20%差异)的多渠道包
class文件如何转化成dex
Service先start再bind如何关闭service，为什么bindService可以跟Activity生命周期联动？
JVM相关,DVM以及ART是如何进行优化的，具体步骤
长连接常见的库，短链接
静态同步锁与普通同步锁的区别
直接在Activity中创建一个thread跟在service中创建一个thread之间恩区别

View的生命周期
View的绘制流程跟事件分发
Activity跟Window之间的关系
对C跟JNI是否熟悉
热修复原理，插件化是如何进行加载插件的
Java中的锁，各种同步方式之间的区别
ANR在四大组件中的时间，场景
OOM，如何避免
内存泄露原理，如何避免
MVC跟MVP之间的区别
ListView针对多种item的缓存是如何实现的
Android绘制二维跟三维的View的区别
是否了解硬件加速
ListView是如何实现对不同type的item的管理的

Android中IPC方式，各种方式优缺点，为什么选择用Binder
主流的热修复框架流派，原理，是否研究过classloader源码
Android为什么要设计两种classloader，为什么不用一种，通过type来区分
Bundle传递数据为什么需要序列化
手写一个快排，分析原理
广播传输的数据是否有限制，是多少，为什么要限制？
Activity启动时生命周期是如何回调的？
MVP.MVC.MVVM之间对比分析
组件化通讯:有几种通信方式，Arouter原理
插件化加载流程，原理
关于反射混淆，耗性能的解决方式
天猫七巧板布局原理
热修复流派，原理

RecyclerView的itemdecoration如何处理点击事件
单例模式如何修改数据
android事件传递，非常细
多线程

JVM内存分布，Classloader
静态代理跟动态代理
线程的生命周期
是否用过DataBinding
是否接触过JNI
对热修复跟插件化了解多少
对RN了解多少
二分查找
说下多态
编译期注解跟运行时注解
线程跟进程间通信
Android中的IPC
java中的传递
equals跟==
Picasso的存储
项目中的难点
项目中用到了哪些第三方
MVC,MVVM,Clean架构
抽象类跟接口的区别，接口的默认修饰符

Canvas.save()跟Canvas.restore()的调用时机
程序A能否接收到程序B的广播？接入微信支付的时候，微信是如何跟当前程序进行通信？
对Java.nio了解多少
ConcurrentHashMap是如何实现分段锁？
现在需要遍历SD卡下所有的文件打印出后缀名为.txt文件名称，如何提高时间效率？
为什么复写equals方法的同时需要复写hashcode方法，前者相同后者是否相同，反过来呢？为什么？
Android4.0～8.0之间大的变化，如何处理？
说一下Measurespec这个类
ViewHolder有什么用？
Gradle的Flavor能否配置sourceset？
线程池核心线程数一般定义多少，为什么？
Intentservice有什么用
弱引用什么时候被回收
Android中提供了哪些类来帮忙处理线程
http的状态码，常见的请求头，http缓存协议https如何加密
多进程的优缺点，多线程的优缺点
热修复如何修复资源文件？
有没有遇到64k问题，为什么，如何解决？
httpUrlconnection跟httpClient有什么区别

两个非基本数据类型数组：
array1=[x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,×11]
array2=[x6,x3,x10,x5]
计算结果：[x6,x7,x8,x9,x1,x2,x3,x4,x10,x11,x5],写出你的算法
类的初始化过程
点击事件穿透原则
线程池使用的是哪一种，使用原则
LeakedCanary原理
LinerLayout跟RelativeLayout的绘制原理
Android新版本的一些新特性6.0,7.0,8.0
类的初始化顺序依次是（静态变量、静态代码块）>（变量、代码块）>构造方法

聊聊RecyclerView，动画，缓存，数据绑定底层是如何实现的。
聊了聊我的开源项目实现原理
View在屏幕中的移动底层是如何实现的
Binder跟Socket之间的区别，什么时候用哪一个，Binder的底层实现
Activity,Window跟View之间的关系
setContentView都干了啥
Activity的启动模式
MVC跟MVP之间的区别
算法：将一个有序数组去重得到一个新数组(空间复杂度为O(N))

LruCache底层实现
Hashmap的hash算法
Bitmap在decode的时候申请的内存如何复用，释放时机
注解如何实现一个findViewById
Android是如何学习的
sycronized关键字的类锁对象锁，如何保证线程安全？
重入锁跟sycronized关键字之间的区别
除了notify还有什么别的方式可以唤醒线程
说说你对Context的理解

Listview的adapter是什么adapter，如何复用。
RV是如何布局的
算法：如何从1T的无序数组(长度为n)里面找出前k大的数据，复杂度要求为O(logN)
由A启动BActivity，A为栈内复用模式，B为标准模式，然后再次启动A或者杀死B，说说A，B的生命周期变化，为什么


1.APP启动流程
2.Webview内存泄露
3.组件间通信，如何传递对象
4.Arouter原理
5.如何取消一个网络请求
6.两个activity之间来回切换的生命周期变化
7.进程间如何通信，Binder机制
8.内存泄露及优化
9.自定义view以及事件冲突
10.线程池参数及定义，多线程是否一定会高效
11.电量优化

请例举Android中常用布局类型，并简述其用法以及排版效率
区别Animation和Animator的用法，概述其原理
Thread,Looper，MessageQueue，Handler，Message每个类的功能以及这些类之间的关系
如何加载NDK库？如何在jni中注册native函数，有几种注册方法？
操作系统中进程和线程有什么联系和区别？系统会在什么情况下会在用户态好内核态中切换。
如果一个APP里面有多进程存在，请列举你所知道的全部IPC方法
请画出MVC、MVP模式的差异
对于Android APP闪退，可能的原因有哪些？请针对每种情况简述分析过

listview跟recyclerview之间的区别，然后上拉加载的时候分别应该如何处理
项目中用过哪些技术
同步普通方法跟static方法之间的区别
不用锁如何保证int自增安全
内存为什么会发生泄露
做过的性能优化，原理
DVK跟JVM之间的区别

Sycronized原理
ReentrantLock原理
静态内部类为什么能保证单例，JVM是如何实现的
ART跟DVM做了哪些优化
View是如何绘制到屏幕上的
写一个快排，有时间限制

深拷贝浅拷贝
输出一个集合{A,B,C,D}的全部子集
自定义View及注意事项
如何自动化部署打包发包流程
JNI是否了解过
内存回收
classloader
对热修复以及插件化了解多少
微信支付宝支付调用时上层是如何封装AIDL的
如何给一个app瘦身

如何实现一个推送，极光推送原理


说说Http协议，以及Tcp/Udp


常见的设计模式，如何实现一个观察者模式，如果需要有序通知观察者，该如何操作


实现一个图片加载框架应该考虑哪些


线程池有哪些参数，应该如何设计


性能优化是如何做的


Java为什么要推出HashMap，它是如何解决hash冲突的


JVM内存分配，编码时如何注意内存泄露


Binder机制原理

VideoView、ExoPlayer、IjkPlayer和Vitamio这几种主流播放器的比较；
垃圾回收机制，JVM内存分配、如何判定对象可回收；
算法：n（很大）个无序数中找最大的10个数；
谈谈内存优化；
如何反编译，对代码逆向分析；
自定义View流程，主要的方法及各自作用；
如何防止过度绘制；
LruCache原理；
事件分发及举例说明；
混合开发用过哪些框架，自己又是如何处理、封装的；
介绍一个你所了解的开源框架及原理；
谈谈对http缓存的了解。

安卓中方法数不能超过64k的原因，及如何处理；
Intent传值有大小限制吗，为什么，如何处理；
如何实现圆形ImageView；
说说动态代理的作用；
注解；
对数字进行或计算；
对View中的onMesurse方法的详细介绍和使用；
如何自己实现RecyclerView的侧滑删除；
TabLayout中如何让当前标签永远位于屏幕中间；
垃圾回收机制；
Activity跳转时的生命周期问题；
LruCache和DisLruCache的原理；
EventBus原理；
线程池的参数和工作原理；
对js互调如何使用，做过什么优化；
算法：归并排序


手写代码：遍历文件目录；
电梯运行的算法分析；
谈谈自己项目的架构，如何优化；
算法：手写冒泡排序；
ijkPlayer播放器源码；
视频播放器的手势控制如何处理；
ArrayList、Vector、LinkedList的区别；
遇到过哪些关于Fragment的问题；
图片的处理和优化；
Android实现异步的几种方式；
JVM垃圾回收机制；
如何对Android应用进行性能分析；
介绍一个你比较欣赏的app或者开源框架。

自定义View流程；
MVP的优点与确点；
ClassLoader的双亲委派；
手写实现单链表的get操作；
用过哪些插件化方案；
图片处理和缓存；
生产者、消费者模式；
如何对应用进行性能优化；
64k出现的原因及如何解决；
对ART的认识；
动态代理的作用；
Retrofit和EventBus的源码分析；
EventBus如何做到黏性发送的；
TextView调用setText方法的内部执行流程；
对线程池的认识和它的几个子类；

