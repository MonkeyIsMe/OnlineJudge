package CSU.OnlineJudge.Utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginFilter extends HttpServlet implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void destroy() {
    }
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println(111);
		HttpServletRequest request=(HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String url = request.getRequestURI();  //获取请求的地址
        String contextPath=request.getContextPath();  //得到相对路径
        String useraccount = (String) session.getAttribute("useraccount");

         if(url.indexOf("welcome.html")>-1&&(useraccount==null || useraccount == "" )){
        	 chain.doFilter(req, res);
         }
         else{
        	 chain.doFilter(req, res);
         }
        
        
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
