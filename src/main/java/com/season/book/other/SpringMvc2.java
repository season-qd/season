package com.season.book.other;Spring Web MVC
一 Sprintmvc
 1.Springmvc是什么
   是一个用来基于mvc架构的web开发框架，springmvc属于spring框架的一部分。
 2.五大组件
   a.DispatcherServlet(前端控制器）
     主控制器，相当于以前写的MainServlet
   b.HandlerMapping。
     请求地址和model的对应关系。
   c.Controller (处理器)。
     处理业务逻辑。
   d.ModelAndView。
     封装处理结果(包含有视图名)。
   e.ViewResolver (视图解析器)。
     负责将视图名解析成真正的视图对象，比如jsp。
  step1: DispatcherServlet收到请求之后，依据HandlerMapping的配置，调用相应的Controller来
          处理。
  step2:Controller将处理结果封装成ModelAndView对象，返回给DispatcherServlet。
  step3:DispatcherServlet依据ViewResolver的解析，调用相应的视图对象（比如某个jsp）,生成相
         应的页面。 
  
  3.编程步骤（*）
    step1:导包 spring-webmvc
    step2:添加配置文件
         例：
           在main/resource中写spring-mvc.xml配置文件
    step3:配置DispatcherServlet
         例：1.在web.xml中写配置文件
           <!-- 配置springmvc的主控制器 -->
          <servlet>
             <servlet-name>springmvc</servlet-name>
             <servlet-class>
                org.springframework.web.servlet.DispatcherServlet
             </servlet-class>
             <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:spring-mvc.xml</param-value>
             </init-param>
             <load-on-startup>1</load-on-startup>
         </servlet>
         <servlet-mapping>
            <servlet-name>springmvc</servlet-name>
            <url-pattern>*.form</url-pattern>
         </servlet-mapping>
    step4:写Controller
       a.实现Controller接口
       b.在handleRequest方法当中，编写处理逻辑。
       例：
         //view：一个JSP页面的名  如：msg.jsp
	String view = "msg";
	//model:表示需要在页面上显示的数量信息
	//最终在JSP页面显示model的信息  ${message}  ->Hello World!
	Map<String,Object> model = new HashMap<String,Object>();
	model.put("message", "Hello World!");
	ModelAndView mv = new ModelAndView(view,model);
	return mv;
    step5:写jsp
        例：<h1>${message}</h1>
        
    step6:配置HandleMapping和ViewResolver
        例：
          <bean class =           
              "org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
             <!-- setMappings(Properties p) -->
                <property name = "mappings">
                    <props>
                    <!-- key:webUrl  value:beanId 
                          请求/hello.form时，执行控制器helloBean的方法
                    -->
                      <prop key = "/hello.form"> helloBean</prop>
                    </props>
                </property>
            </bean>
            <bean id = "helloBean" class = "day01.HelloController"/>
        
        <bean id = "jspViewResolver" class = 
           "org.springframework.web.servlet.view.InternalResourceViewResolver">
           <property name = "prefix" value = "/WEB-INF/"/>
           <property name = "suffix" value = ".jsp"/>
        </bean>

  4.基于注解的spring mvc
    1)编程步骤
      step1:导包：spring-webmvc
      step2:添加配置文件
      step3:配置DispatcherServlet
      step4:写Controller
        a.不用实现Controller接口
        b.可以添加多个方法
        c.方法名不做要求，返回值类型可以是ModelAndView，也可以是String
        d.在类名前添加@Controller
        e.使用@RequestMapping来告诉DispatcherServlet请求路径与处理器的对应关系
        f.返回值要有视图名称
        例：
          @Controller
          @RequestMapping("/day02")
          public class HelloController {
		@RequestMapping("/hello.do")
		public String excute(){
			System.out.println("Hello World!");
			return "msg";//返回视图名称
		}
           }
      step5:写jsp
      step6:Spring配置文件中，需要配置：组件扫描，mvc注解扫描，视图解析器。
            例：<!-- 开启注解扫描，支持组件注解 @Controller @Service... -->
               <context:component-scan base-package = "day02.web"/>
                <!-- 开启注解版spring mcx 支持@RequestMapping -->
               <mvc:annotation-driven/>
               <!-- 视图处理器 -->
               <bean class = 
                 "org.springframework.web.servlet.view.InternalResourceViewResolver">
                 <property name = "prefix" value = "/WEB-INF/jsp/"/>
                 <property name = "suffix" value = ".jsp"/>
               </bean>
   2）如何读取请求参数值
      方式一：通过request对象（HttpServletRequest)
          缺点：数据的编码，数据类型都需要手动处理。ServletAPI与控制器紧耦合，不方便独立测试
          优点：可以直接访问Servlet底层API，可以获取通讯底层详细信息。如：读取请求头信息
          例：
             @RequestMapping("/login1.form")
	      public String login1(HttpServletRequest request) throws  
	          UnsupportedEncodingException{
		 request.setCharacterEncoding("utf-8");
		 String ua = request.getHeader("user-Agent");//获取请求头
		 String name = request.getParameter("username");
		 String pwd = request.getParameter("pwd");
		 System.out.println(name+","+pwd);
		 return "login";
	      }
      方式二：通过@ReqeustParam注解
              控制器方法参数名与表单中输入框name属性一致，SpringMVC就自动将表单数据解码并且
                 转换数据类型，然后注入到变量中。
              @RequestParam(String param)映射不一致的名称  param是实际请求参数名
              注：建议，即使实际请求参数名与形参名一致，也要添加@RequestParam进行说明
              优点是参数类型自动转换，但可能出现类型转换异常
             例：
              @RequestMapping("/login2.form")
	public String login2(String username,@RequestParam("pwd")String password){
		System.out.println(username+","+pwd);
		return "login";
	}
      方式三：封装成一个javabean(使用自动机制封装成Bean对象,即值对象：用于封装一组值的对象)
         step1:写一个java类，要求属性名与实际请求参数名一致，并提供相应的get/set方法
         step2:在方法中添加该java类型的参数  
          例：
            先定义一个值对象UserInfo 里面的属性名与表单name名一致。 
            @RequestMapping("/login3.form")
	    public String login3(UserInfo user){
		System.out.println(user);
		return "login";
	    }
             
   3）向页面传值
      方式一：通过request对象
             将数据绑定到request对象，然会转发给jsp来展现
             注：springmvc默认使用转发机制
      方式二：通过session对象
             将数据绑定到session对象
             req.getSession().setAttribute(key,value);
      方式三：通过ModelMap对象--最常用做法（*）
             在方法当中，添加ModelMap对象作为参数，然后调用该对象的addAttribute方法
             例：@RequestMapping("/login4.form")
	        public String login4(String username,String password,ModelMap model){
		   System.out.println(username = ","+password);
		   model.addAttribute("nameError","用户名错误");
		   model.addAttribute("passwordError","密码错误");
		   return "login";
	         }
      方法四：通过ModelAndView对象
             将处理结果添加到ModelAndView对象里面。

      方法五：通过@ModelAttribute(String param) 置于方法参数部分或Bean属性方法上。（了解）
              数据既可以接收也可以返回，常用于保持表单中页面不变。
           例：在Controller中写方法
             @RequestMapping("/login4.form")
	     public String login4(@ModelAttribute("username")String username,String  
	       password,ModelMap model){
		 System.out.println(username + ","+password);
		 model.addAttribute("nameError","用户名错误");
		 model.addAttribute("passwordError","密码错误");
		 return "login";
	      }
	      在jsp文件中可获得username
	      <input type = "text" name = "username" value = "${username }">

   4)如何重定向
     spring MVC默认采用转发的方式定位视图，如果需要重定向可以采取以下方式
     情况一：如果方法的返回值是String,在重定向地址前，添加"redirect:"
             如:"redirect:logi.form"
             例：@RequestMapping("/checkLogin2.form")
	         public String checkLogin2(){
		    return "redirect:../demo/form.form";
	         }
     情况二：如果方法的返回值是ModelAndView
             例：
             @Controller
             @RequestMapping("/user")
             public class DemoController {
	         @RequestMapping("/checkLogin1.form")
	         public ModelAndView checkLogin1(){
		 RedirectView view = new RedirectView("../demo/form.form");
		 return new ModelAndView(view);
	         }
              }

 5.登录
   
二 中文乱码解决方案
   1.乱码问题产生的原因是提交表单时,浏览器会对表单中的中文进行编码(浏览器会按照打开表单所在的页面所使用的字符集来编码),而服务器端默认使用"iso-8859-1"来解码.
   2.如何解决:在web.xml中,配置CharacterEncodingFilter过滤器(该过滤器由spring提供)
     注:表单提交方式必须为post方式
        页面编码与过滤器所使用的编码要一致.
    <!-- 配置filter -->
  <filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter
    </filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-vlaue>UTF-8</param-vlaue>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</servlet-name>
  </filter-mapping>
 
三 异常处理
   方式一：使用简单异常处理器
    step1:在配置文件当中，添加简单异常处理器(可控制所有控制器里的异常)
       例：其中key是异常类包 value是视图页面
        <bean class = 
          "org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
          <property name = "exceptionMappings">
            <props>
               <prop key = "java.lang.ArrayIndexOutOfBoundsException">error1</prop> 
               <prop key = "java.lang.NullPointerException">error2</prop>
            </props>
         </property>
       </bean>
    方式二：使用@ExceptionHandler(*)（只控制本控制器类的异常）
       step1:在控制器中添加一个异常处理方法，在该方法前添加@ExceptionHandler
       step2:在异常处理方法当中，依据异常类型，分别进行不同的处理。
       step3:添加对应的异常处理页面
         例:
          @ExceptionHandler
	  public String excute(HttpServletRequest req,Exception e){
		System.out.println("发现了异常");
		e.printStackTrace();
		return "error1";
	  }
   
四 拦截器
  1.什么是拦截器?
  spring定义的一种特殊的组件,DispatcherServlet在收到请求之后,会先调用拦截器的方法,然后再调用处理器(contoller).
  三个方法:boolean preHandler() postHandler() afterCompletion()
  浏览器发送请求,DispatcherServlet接收请求,执行prehandler(),若返回true,调用请求对应的Controller,获得返回值后,执行postHandler(),DispatcherServlet再调用视图处理器,执行afterCompletion(),再嗲用jsp
  注:过滤器是servlet规范当中定义的组件. 
  2.如何写一个拦截器?
   step1:写一个java类,实现HandlerInterCeptor接口
   step2:将拦截处理逻辑写在相应的接口方法里面:
     a:preHandler() 前端控制器(DispatcherServlet)收到请求之后,会先调用拦截器的preHandler
         ()方法,如果该方法的返回值为true,表示继续向后调用,如果返回false,表示请求处理完毕,不
         会向下执行.
     b:postHandler() 控制器方法已经执行完毕,准备将处理结果(ModelAndView对象)返回给前端控制
         器(DispatcherServlet)之前执行该方法.可以在该方法里面修改处理结果.
     c:afterCompletion() 整个请求处理完毕后，最后执行的方法
        注:只有当preHandle方法返回值为true时,该方法才会执行
   step3:配置拦截器
        <!-- 配置拦截器 -->
        <mvc:interceptors>
          <mvc:interceptor>
             <mvc:mapping path = "/user/*"/>
             <mvc:exclude-mapping path = ""/>
             <bean class = "dao.DemoInterceptor"/>
          </mvc:interceptor>
        </mvc:interceptors>

 
 
 
 
 
 
 
 
 
 








补：
1.
    //紧耦合：控制器方法紧紧绑定了Servlet API，
    //紧耦合时，方法只能在web容器测试！
    //优点：可以调用Servlet底层API
    
   //返回ModelAndView方式，是松耦合控制器与Servlet API
   //无关，可以脱离web容器单独测试，实现平台无关的测试。
   
    @RequestMapping("/demo1.do")
    public String demo1(HttpServletRequest req){
	req.setAttribute("name", "wdd");
	return "demo1";
   }

2.用JSTL标签写路径
    <%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <c:url var= "url" value = "/demo/login1.form"/>
       <form action = "${url }" method = "post">
       .......

3.现阶段接收的参数如果是中文，需要配置filter。后期就不用了
  <!-- 配置filter -->
  <filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter
    </filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-vlaue>UTF-8</param-vlaue>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encoding</filter-name>
    <servlet-name>springmvc</servlet-name>
  </filter-mapping>

4.Http协议 RFC标准文档

5.//运行期间spring自动将ID为dataSource的Bean注入到dataSource变量中
	@Resource
	private DataSource dataSource;


































































































