package com.example.restSpring.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//If we were using Spring elsewhere in our application we probably already had a WebApplicationInitializer 
//that is loading our Spring Configuration. If we use the previous configuration we would get an error. 
//Instead, we should register Spring Security with the existing ApplicationContext. 
//For example, if we were using Spring MVC our SecurityWebApplicationInitializer would look something like the following:
//Es necesario registrar la configuración de spring
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
