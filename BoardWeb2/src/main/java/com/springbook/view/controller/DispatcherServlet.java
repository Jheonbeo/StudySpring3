package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

//Front Controller
public class DispatcherServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Get������� ��û�� ȣ��
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	//Post������� ��û�� ȣ��
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("euc-kr");
		process(req, resp);
	}
	//Post Get �Ѵ� ��������� Serivce�� �Լ��� ó��
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		
		if(path.equals("/login.do")){
			System.out.println("�α��� ó��");
			String id = request.getParameter("id");
			String pwd = request.getParameter("password");
			
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(pwd);
			
			UserDAO userDAO = new UserDAO();
			UserVO user = userDAO.getUser(vo);
			
			if(user != null){
				response.sendRedirect("getBoardList.do");
			}
			else{
				response.sendRedirect("login.jsp");
			}
		}
		else if(path.equals("/logout.do")){
			System.out.println("�α׾ƿ� ó��");
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
		else if(path.equals("/insertBoard.do")){
			System.out.println("�� ��� ó��");
			request.setCharacterEncoding("euc-kr");

			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(vo);
			
			response.sendRedirect("getBoardList.jsp");
		}
		else if(path.equals("/updateBoard.do")){
			System.out.println("�� ���� ó��");
			request.setCharacterEncoding("euc-kr");
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			vo.setTitle(title);
			vo.setContent(content);

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(vo);
			
			response.sendRedirect("getBoardList.do");
		}
		else if(path.equals("/deleteBoard.do")){
			System.out.println("�� ���� ó��");
			String seq = request.getParameter("seq");

			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);

			response.sendRedirect("getBoardList.do");
		}
		else if(path.equals("/getBoard.do")){
			System.out.println("�� �� ��ȸ ó��");
			String seq = request.getParameter("seq");

			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			
			HttpSession session = request.getSession();
			session.setAttribute("board", board);	//������ session�αٿ� boardList ����
			response.sendRedirect("getBoard.jsp");
		}
		else if(path.equals("/getBoardList.do")){
			System.out.println("�� ��� �˻� ó��");
			BoardVO vo = new BoardVO();
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);	//������ session�αٿ� boardList ����
			response.sendRedirect("getBoardList.jsp");
		}
	}
}
//HttpServletRequest, HttpServletResponse -��û�� ���� ������ �Ź� �������(Ŭ���̾�Ʈ���� ��û). �Լ� ������ ����Ǹ� ������. doget, dopost, service��
//HttpSession -client ���ӽ�(request.getSession()), client �����(�������� �����, invalidate(), ��ȿ�ð� ���), �α���/�ƿ�/��ٱ��� ��ɵ ������
//ServletContext -���� ���۽�, ���� �����, Web application
//���� : setAttribute(name, value)
//���� : getAttribute(name)
//���� : removeAttribute(name)
