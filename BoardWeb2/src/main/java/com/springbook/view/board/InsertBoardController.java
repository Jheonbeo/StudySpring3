package com.springbook.view.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.impl.BoardDAO;
import org.springframework.web.servlet.mvc.Controller;

public class InsertBoardController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 등록 처리");
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.insertBoard(vo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:getBoardList.do");
		return mav;
	}
}
