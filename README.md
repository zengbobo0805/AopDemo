
# AopDemo

## LogCallAspect.java 自动匹配

## LogCall2Aspect.java  annotation注解匹配

实现步骤
 <h4>1.@Aspect    </h4>
      必须使用@AspectJ标注
    
 <h4>2.@Pointcut </h4>
       pointcut也变成了一个注解，这个注解是针对一个函数的，比如此处的logForActivity()
       其实它代表了这个pointcut的名字。如果是带参数的pointcut，则把参数类型和名字放到
       代表pointcut名字的logForActivity中，然后在@Pointcut注解中使用参数名。
       基本和以前一样，只是写起来比较奇特一点。后面我们会介绍带参数的例子

          private static final String POINTCUT_METHOD =
              "call(@com.bobo.aop.annotation.LogTrace * *(..))";

          private static final String POINTCUT_CONSTRUCTOR =
              "call(@com.bobo.aop.annotation.LogTrace  *.new(..))";

 <h4> 3.@Before/@After/@Around     </h4>

      @Before：这就是Before的advice，对于after，after -returning，和after-throwing。对于的注解格式为
      @After，@AfterReturning，@AfterThrowing。Before后面跟的是pointcut名字，然后其代码块由一个函数来实现。比如此处的log。

  <h2>root目录下build.gradle    </h2>
    
         classpath 'org.aspectj:aspectjtools:1.8.1' 
        
    
 <h2> module下build.gradle </h2>
  
           implementation 'org.aspectj:aspectjrt:1.8.1'
  
            //AndroidAopDemo.build.gradle
            //此处是编译一个App，所以使用的applicationVariants变量，否则使用libraryVariants变量
            //这是由Android插件引入的。所以，需要
            //import com.android.build.gradle.AppPlugin
            android.applicationVariants.all { variant ->
                /*
                  这段代码之意是：
                  当app编译个每个variant之后，在javaCompile任务的最后添加一个action。此action
                  调用ajc函数，对上一步生成的class文件进行aspectj处理。
                */
            //    AppPlugin plugin = project.plugins.getPlugin(AppPlugin)
                JavaCompile javaCompile = variant.javaCompile
                javaCompile.doLast {
                    String bootclasspath = project.android.bootClasspath.join(File.pathSeparator)
                    //ajc是一个函数，位于utils.gradle中
                    ajc(bootclasspath, javaCompile)
                }
            }

            import org.aspectj.bridge.IMessage
            import org.aspectj.bridge.MessageHandler
            import org.aspectj.tools.ajc.Main

            def ajc(String androidbootClassFiles,JavaCompile javaCompile){
                String[] args = ["-showWeaveInfo",
                                 "-1.8", //1.8是为了兼容java 8。请根据自己java的版本合理设置它
                                 "-inpath",javaCompile.destinationDir.toString(),
                                 "-aspectpath",javaCompile.classpath.asPath,
                                 "-d",javaCompile.destinationDir.toString(),
                                 "-classpath",javaCompile.classpath.asPath]//,
            //                     "-bootclasspath", androidbootClassFiles]
                MessageHandler handler = new MessageHandler(true)
                new Main().run(args,handler)

                def log = project.logger
                for(IMessage message : handler.getMessages(null, true)) {
                    switch (message.getKind()) {
                        case IMessage.ABORT:
                        case IMessage.ERROR:
                        case IMessage.FAIL:
                            log.error message.message, message.thrown
                            throw message.thrown
                            break
                        case IMessage.WARNING:
                        case IMessage.INFO:
                            log.info message.message, message.thrown
                            break
                        case IMessage.DEBUG:
                            log.debug message.message, message.thrown
                            break
                    }
                }
            }
   
   #参考文档：
   
       1.http://blog.csdn.net/innost/article/details/49387395
       
       2.http://www.jianshu.com/p/0fa8073fd144
