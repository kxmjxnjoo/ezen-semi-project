package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.action.Action;
import com.dao.AdminDao;
import com.dao.MemberDao;
import com.dao.PostDao;
import com.dao.StoryDao;
import com.dto.ReportDto;

public class HandleReportAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/report/HandleReportDone.jsp";
		
		int report_num = Integer.parseInt(request.getParameter("report_num"));
		System.out.println(report_num);
		int result = 0;
		AdminDao adao = AdminDao.getInstance();
		
		if(request.getParameter("post_num") != null) {
			int post_num = Integer.parseInt(request.getParameter("post_num"));
			ReportDto rdto = AdminDao.getInstance().getReport(report_num);
			PostDao.getInstance().deletePost(post_num);
			result = adao.reportDone(report_num);
			
			request.setAttribute("post_num", post_num);
			request.setAttribute("ReportDto", rdto);
			
		} else if(request.getParameter("story_num") != null ) {
			int story_num = Integer.parseInt(request.getParameter("story_num"));
			ReportDto rdto = AdminDao.getInstance().getReport(report_num);
			StoryDao.getInstance().deleteStory(story_num);
			result = adao.reportDone(report_num);
		} else {
			String userid = request.getParameter("userid");
			ReportDto rdto = AdminDao.getInstance().getReport(report_num);
			MemberDao.getInstance().blockUser(userid);
			adao.reportSameUser(userid);
			result = adao.reportDone(report_num);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
