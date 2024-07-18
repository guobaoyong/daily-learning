package org.wlgzs.web;

import org.wlgzs.dao.UserDao;
import org.wlgzs.model.User;
import org.wlgzs.util.DbUtil;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("login".equals(action)){
			login(request,response);
		}else if("logout".equals(action)){
			logout(request,response);
		}
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		HttpSession session=request.getSession();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			User user=new User(userName,password);
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				request.setAttribute("error", "用户名密码错误");
				request.setAttribute("password", password);
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("page/background/login.jsp").forward(request, response);
			}else{
				session.setAttribute("currentUser", currentUser);
				request.setAttribute("mainPage", "default.jsp");
				request.getRequestDispatcher("page/background/mainTemp.jsp").forward(request, response);
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

	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();
		System.out.println(request.getContextPath()+"/page/background/login.jsp");
		response.sendRedirect(request.getContextPath()+"/page/background/login.jsp");
	}


}
