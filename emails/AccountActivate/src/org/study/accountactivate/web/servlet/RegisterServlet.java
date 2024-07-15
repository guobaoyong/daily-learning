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
 * �û�ע��
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
			errors.put("userName", "�û�������Ϊ��!");
		} else if (userName != null && userDao.findUserByName(userName) != null) {
			errors.put("userName", "���û���ע��!");
		}
		
		if (password == null || "".equals(password)) {
			errors.put("password","���벻��Ϊ��!");
		} else if (password != null && password.length() < 3) {
			errors.put("password","���볤�Ȳ��ܵ���3λ!");
		}
		
		if (password2 == null || "".equals(password2)) {
			errors.put("password2", "ȷ�����벻��Ϊ��!");
		} else if (password2 != null && !password2.equals(password)) {
			errors.put("password2", "������������벻һ��!");
		}
		
		if (email == null || "".equals(email)) {
			errors.put("email", "email����Ϊ��!");
			
		} else if (email != null && !email.matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
			errors.put("email", "email��ʽ����ȷ!");
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
		
		// ע��ɹ���,�����ʻ���������
		EmailUtils.sendAccountActivateEmail(user);
		
		// ע��ɹ�ֱ�ӽ���ǰ�û����浽session��
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/pages/registerSuccess.jsp").forward(request,response);
	}

}
