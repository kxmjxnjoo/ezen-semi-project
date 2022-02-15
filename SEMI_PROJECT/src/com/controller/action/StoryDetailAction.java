package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StoryDao;
import com.dto.MemberDto;
import com.dto.ReplyDto;
import com.dto.StoryDto;

public class StoryDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "storyDetail.jsp";
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginAdmin");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
				int story_num = Integer.parseInt(request.getParameter("story_num"));
				StoryDao sdao = StoryDao.getInstance();
				StoryDto sdto = sdao.getStory(story_num);
				String userid = sdto.getUserid();
				int result = sdao.storyLikeCheck(story_num, userid);
				String fileName = "";
				if(result == 0) {
					fileName = "../images/beforeLike.png";
				} else {
					fileName = "../images/Like.png";
				}

				int prev = sdao.searchPrevStory(story_num, userid);
				int next = sdao.searchNextStory(story_num, userid);
				
				////////////테스트용 코드 
				System.out.println("///");
				MemberDto mdto = new MemberDto();
				mdto.setUserid("hong");
				session.setAttribute("loginUser", mdto);
				String loginUser = ((MemberDto) session.getAttribute("loginUser")).getUserid();
				System.out.println(loginUser);
				System.out.println(sdto.getUserid());
				System.out.println("prev : " + prev + ", next=" + next);
				////////////테스트용 코드 여기까지 /////////////////////////
				
				request.setAttribute("likeResult", result);
				request.setAttribute("fileName", fileName);
				request.setAttribute("story_num", story_num);
				request.setAttribute("StoryDto", sdto);
				request.setAttribute("prev", prev);
				request.setAttribute("next", next);
		//}
		request.getRequestDispatcher(url).forward(request, response);
	}
}