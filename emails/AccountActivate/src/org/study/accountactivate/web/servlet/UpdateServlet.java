package org.study.accountactivate.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.study.accountactivate.dao.UserDao;
import org.study.accountactivate.dao.impl.UserDaoImpl;
import org.study.accountactivate.domail.User;
import org.study.accountactivate.util.EmailUtils;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDao userDao = UserDaoImpl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		Map<String, String> errors = new HashMap<String,String>();
		
		if (email == null || "".equals(email)) {
			errors.put("email", "email不能为空!");
			
		} else if (email != null && !email.matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
			errors.put("email", "email格式不正确!");
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/updateUI").forward(request, response);
			return;
		}
		
		
		User user = (User) request.getSession().getAttribute("user");
		user.setEmail(email);
		user.setActivated(false);
		user.setRandomCode(UUID.randomUUID().toString());
		
		//userDao.updateUser(user);
		
		// 注册成功后,发送帐户激活链接
		EmailUtils.sendAccountActivateEmail(user);
		
		// 注册成功直接将当前用户保存到session中
		request.getSession().setAttribute("user", user);
		
		request.getRequestDispatcher("/WEB-INF/pages/updateSuccess.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
