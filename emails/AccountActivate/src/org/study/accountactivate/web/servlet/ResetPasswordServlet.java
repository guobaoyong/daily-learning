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

/**
 * ������������
 */
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String newPassword = request.getParameter("newPassword");
		String newPassword2 = request.getParameter("newPassword2");
		Map<String,String> errors = new HashMap<String, String>();
		if (newPassword == null || "".equals(newPassword)) {
			errors.put("newPassword", "�����벻��Ϊ�գ�");
		}
		
		if (newPassword2 == null || "".equals(newPassword2)) {
			errors.put("newPassword2", "ȷ�������벻��Ϊ�գ�");
		}
		
		if (!newPassword.equals(newPassword2)) {
			errors.put("passwordError", "������������벻һ�£�");
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/resetPasswordUI?userName=" + userName).forward(request, response);
			return;
		}
		
		UserDao userDao = UserDaoImpl.getInstance();
		User user = userDao.findUserByName(userName);
		user.setPassword(newPassword);
		
		request.getRequestDispatcher("/WEB-INF/pages/resetPasswordSuccess.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
