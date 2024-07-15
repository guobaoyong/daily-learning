package org.study.accountactivate.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.study.accountactivate.dao.UserDao;
import org.study.accountactivate.dao.impl.UserDaoImpl;
import org.study.accountactivate.domail.User;
import org.study.accountactivate.util.EmailUtils;

/**
 * 用户注册
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao = UserDaoImpl.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");
		
		Map<String, String> errors = new HashMap<String,String>();
		if (userName == null || "".equals(userName)) {
			errors.put("userName", "用户名不能为空!");
		} else if (userName != null && userDao.findUserByName(userName) != null) {
			errors.put("userName", "该用户已注册!");
		}
		
		if (password == null || "".equals(password)) {
			errors.put("password","密码不能为空!");
		} else if (password != null && password.length() < 3) {
			errors.put("password","密码长度不能低于3位!");
		}
		
		if (password2 == null || "".equals(password2)) {
			errors.put("password2", "确认密码不能为空!");
		} else if (password2 != null && !password2.equals(password)) {
			errors.put("password2", "两次输入的密码不一致!");
		}
		
		if (email == null || "".equals(email)) {
			errors.put("email", "email不能为空!");
			
		} else if (email != null && !email.matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
			errors.put("email", "email格式不正确!");
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/registerUI").forward(request, response);
			return;
		}
		
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setActivated(false);
		
		userDao.addUser(user);
		
		// 注册成功后,发送帐户激活链接
		EmailUtils.sendAccountActivateEmail(user);
		
		// 注册成功直接将当前用户保存到session中
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/pages/registerSuccess.jsp").forward(request,response);
	}

}
