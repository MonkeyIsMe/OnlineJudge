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

import org.apache.struts2.ServletActionContext;



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
        String useraccount = (String) request.getSession().getAttribute("useraccount");
        //System.out.println("1.useraccount = " + useraccount);

/*		String[] names = session.getValueNames();
		for(int i = 0;i < names.length;i++){
			System.out.println(names[i] + "," + session.getValue(names[i]));
		}*/

         if((useraccount==null || useraccount == "" )){
        	 response.sendRedirect("http://vlab.csu.edu.cn/portal/index.html?sysId=3"); 
         }
         else{
        	 chain.doFilter(req, res);
         }
        
        
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
