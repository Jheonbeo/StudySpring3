package com.springbook.view.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.impl.BoardDAO;
import org.springframework.web.servlet.mvc.Controller;

public class UpdateBoardController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�� ���� ó��");
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String seq = request.getParameter("seq");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		vo.setTitle(title);
		vo.setContent(content);

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.updateBoard(vo);
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:getBoardList.do");
		return mav;
	}
}
