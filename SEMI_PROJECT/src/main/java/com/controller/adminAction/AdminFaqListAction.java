package com.controller.adminAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.FaqDao;
import com.dto.AdminDto;
import com.dto.FaqDto;
import com.util.Paging;

public class AdminFaqListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String url = "admin/faq/adminFaqList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto == null) url = "spring.do?command=admin";
		else {			
			Paging paging = new Paging();
			int page=1;  // 처음 게시판을 열었을때
			
			if( request.getParameter("page") != null)
				page = Integer.parseInt( request.getParameter("page") );
			paging.setPage(page);
			
			ArrayList<FaqDto> list = FaqDao.getInstance().listFaq();
			
			
			
			int count = 1;
			paging.setTotalCount(count);
			
			/*
			 * for( MemberDto mdto : list) { int cnt = mdao.getReplycnt( bdto.getNum() );
			 * bdto.setReplycnt(cnt); }
			 */
			
			request.setAttribute("listFaq" , list);
			request.setAttribute("paging", paging);	
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}