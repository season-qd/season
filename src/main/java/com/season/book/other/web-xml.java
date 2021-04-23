package com.season.book.other;

<?xml version="1.0" encoding="UTF-8"?>
<web-app version="3.0" 
	xmlns="http://java.sun.com/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">
  <display-name></display-name>	
  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
  <context-param>
  <param-name>contextConfigLocation</param-name>
  <param-value>classpath:application-context.xml</param-value>
  </context-param>
  <listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  
  <filter>
  <filter-name>OpenSessionInViewFilter</filter-name>
  <filter-class>org.springframework.orm.hibernate3.support.OpenSessionInViewFilter</filter-class>
  </filter>
  <filter-mapping>
  <filter-name>OpenSessionInViewFilter</filter-name>
  <url-pattern>*.action</url-pattern>
  </filter-mapping>
  
  <filter>
  <filter-name>struts2</filter-name>
  <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
  </filter>
  <filter-mapping>
  <filter-name>struts2</filter-name>
  <url-pattern>/*</url-pattern>
  </filter-mapping>
  
</web-app>