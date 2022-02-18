package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;
import com.dto.PostDto;

public class EditPostFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/editPost.jsp";
		HttpSession session = request.getSession();
		////////////테스트를 위한 코드입니다.	
		MemberDto mdto = new MemberDto();
		mdto.setUserid("hong");
		mdto.setImg("1.png");
		session.setAttribute("loginUser", mdto);
		///////////여기까지///////////////////////////
	
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginAdmin");
		//if(mdto==null) url = "spring.do?command=login";
		
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		request.setAttribute("post_num", post_num);
		PostDto pdto = PostDao.getInstance().getPost(post_num);
		request.setAttribute("PostDto", pdto);
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
