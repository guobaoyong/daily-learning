package org.wlgzs.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.wlgzs.dao.UserDao;
import org.wlgzs.model.User;

import com.wlgzs.util.DbUtil;


public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("exit".equals(action)){
			exit(request, response);
		}else {
			HttpSession session=request.getSession();
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			String remember=request.getParameter("remember");
			
			Connection con=null;
			try{
				con=dbUtil.getCon();
				User user=new User(userName,password);
				User currentUser=userDao.login(con, user);
				if(currentUser==null){
					request.setAttribute("user", user);
					request.setAttribute("error", "用户名或密码错误");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					if("remember-me".equals(remember)){
						rememberMe(userName,password,response);
					}
					session.setAttribute("currentUser", currentUser);
					request.getRequestDispatcher("main").forward(request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	private void rememberMe(String userName,String password,HttpServletResponse response){
		Cookie user=new Cookie("user",userName+"-"+password);
		user.setMaxAge(1*60*60*24*7);
		response.addCookie(user);
	}
	
	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.removeAttribute("currentUser");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	

}
