jni例子：为了测试-Wl,-rpath参数

native.c 依赖 world.c

1、编译java文件
javac JNIDemo.java
2、编译world.c
gcc -shared  -fPIC -o libworld.so world.c 
3、编译native.c
gcc -I /root/soft/jdk1.7.0_80/include/ -I /root/soft/jdk1.7.0_80/include/linux  -fPIC -shared -o libnative.so native.c -lworld -L. -Wl,-rpath=./
4、
mkdir lib
mv lib*.so lib
java -Djava.library.path=/root/project/JNIDemo/lib  JNIDemo
发现会找不到so文件
[root@Test185 JNIDemo]# java -Djava.library.path=/root/project/JNIDemo/lib  JNIDemo
Exception in thread "main" java.lang.UnsatisfiedLinkError: /root/project/JNIDemo/lib/libnative.so: libworld.so: 无法打开共享对象文件: 没有那个文件或目录
        at java.lang.ClassLoader$NativeLibrary.load(Native Method)
        at java.lang.ClassLoader.loadLibrary0(ClassLoader.java:1941)
        at java.lang.ClassLoader.loadLibrary(ClassLoader.java:1857)
        at java.lang.Runtime.loadLibrary0(Runtime.java:870)
        at java.lang.System.loadLibrary(System.java:1122)
        at JNIDemo.<clinit>(JNIDemo.java:4)
需要export LD_LIBRARY_PATH=/root/project/JNIDemo/lib/ 才能找到

5、但是如果指定绝对路径
gcc -I /root/soft/jdk1.7.0_80/include/ -I /root/soft/jdk1.7.0_80/include/linux  -fPIC -shared -o libnative.so native.c -lworld -L. -Wl,-rpath=/root/project/JNIDemo/lib
mv libworld.so lib
java JNIDemo
可以成功，不需要export LD_LIBRARY_PATH

-rpath=/root/project/JNIDemo/lib  此参数不支持相对路径

6、查看so包rpath参数
objdump -x libnative.so |grep RPATH

7、总结，所有so文件所在的路径尽量都export LD_LIBRARY_PATH， Java里给java.library.path参数增加没有export的路径，是不会生效的，除非编译so时，rpath指定绝对路径