package com.ssm.backstage.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.util.StringUtils;


/**
 * 
 * @ClassName: LoginFilter
 * @Description: 登陆过滤器
 * @author xiaoxiaofeng
 * @date 2016年9月12日 上午9:30:22
 *
 */
public class LoginFilter implements Filter {

	/**
	 * 过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession(false);
		StringBuffer requestUrl = servletRequest.getRequestURL();
		if(requestUrl.indexOf("login.html") == -1 && requestUrl.indexOf("register.html") == -1){
			//无用户登陆
			if(session == null || StringUtils.isEmpty(session.getAttribute("loginUser"))){
				String path = servletRequest.getContextPath();  
				String basePath = servletRequest.getScheme()+"://"+servletRequest.getServerName()+":"+servletRequest.getServerPort()+path+"/";  
			        PrintWriter out = servletResponse.getWriter();  
			        out.println("<html>");      
			        out.println("<script>");      
			        out.println("window.open ('"+basePath+"user/login.html','_top')");      
			        out.println("</script>");      
			        out.println("</html>");    
			}else{
				chain.doFilter(servletRequest, servletResponse);
			}
		}else{
			chain.doFilter(servletRequest, servletResponse);
		}
		return;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
