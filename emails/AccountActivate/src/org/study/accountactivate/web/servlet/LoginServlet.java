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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		Map<String, String> errors = new HashMap<String,String>();
		if (userName == null || "".equals(userName)) {
			errors.put("userName", "�û�������Ϊ��!");
		}
		
		if (password == null || "".equals(password)) {
			errors.put("password","���벻��Ϊ��!");
		} else if (password != null && password.length() < 3) {
			errors.put("password","���볤�Ȳ��ܵ���3λ!");
		}
		
		UserDao userDao = UserDaoImpl.getInstance();
		User user = userDao.findUserByName(userName);
		
		if (user == null || !user.getPassword().equals(password)) {
			errors.put("loginError", "�û��������벻��ȷ��");
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/loginUI").forward(request, response);
			return;
		}
		
		request.getSession().setAttribute("user",user);
		
		request.getRequestDispatcher("/WEB-INF/pages/loginSuccess.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
