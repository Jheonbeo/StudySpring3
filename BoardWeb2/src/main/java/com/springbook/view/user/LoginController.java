package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

public class LoginController implements Controller{
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("로그인 처리");
		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(pwd);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		ModelAndView mav = new ModelAndView();
		if(user != null){
			mav.setViewName("redirect:getBoardList.do");
		}
		else{
			mav.setViewName("login");
		}
		return mav;
	}
}
