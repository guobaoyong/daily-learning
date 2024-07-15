package org.study.accountactivate.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.study.accountactivate.dao.UserDao;
import org.study.accountactivate.dao.impl.UserDaoImpl;
import org.study.accountactivate.domail.User;
import org.study.accountactivate.util.GenerateLinkUtils;

/**
 * 帐户激活
 */
public class ActivateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idValue = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idValue);
		} catch (NumberFormatException e) {
			throw new RuntimeException("无效的用户！");
		}
		UserDao userDao = UserDaoImpl.getInstance();
		User user = userDao.findUserById(id);
		user.setActivated(GenerateLinkUtils.verifyCheckcode(user, request));
		userDao.updateUser(user);
		
		request.getSession().setAttribute("user", user);
		
		request.getRequestDispatcher("/accountActivateUI").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
