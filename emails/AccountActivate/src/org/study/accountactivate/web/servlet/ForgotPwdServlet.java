package org.study.accountactivate.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.study.accountactivate.dao.UserDao;
import org.study.accountactivate.dao.impl.UserDaoImpl;
import org.study.accountactivate.domail.User;
import org.study.accountactivate.util.EmailUtils;

/**
 * ���������������������
 */
public class ForgotPwdServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNameOrEmail = request.getParameter("userNameOrEmail");
		UserDao userDao = UserDaoImpl.getInstance();
		User user = userDao.findUserByNameOrEmail(userNameOrEmail);
		if (user == null) {
			request.setAttribute("errorMsg", userNameOrEmail + "�������ڣ�");
			request.getRequestDispatcher("/forgotPwdUI").forward(request, response);
			return;
		}
		
		// ���������������������
		EmailUtils.sendResetPasswordEmail(user);
		
		request.setAttribute("sendMailMsg", "�����������ύ�ɹ�����鿴����"+user.getEmail()+"���䡣");
		
		request.getRequestDispatcher("/WEB-INF/pages/forgotPwdSuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
