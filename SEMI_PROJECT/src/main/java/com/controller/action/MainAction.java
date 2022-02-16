package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FollowViewDao;
import com.dao.PostViewDao;
import com.dao.StoryDao;
import com.dto.FollowViewDto;
import com.dto.MemberDto;
import com.dto.PostViewDto;
import com.dto.StoryDto;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "main.jsp";
		HttpSession session = request.getSession();
		
		// Force login to hong while testing
//		MemberDto tmplogin = MemberDao.getInstance().getMember("hong");
//		session.setAttribute("loginUser", tmplogin);
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		} else {
			// Get my profile
			MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
			String userid = mdto.getUserid();
			
			// Get following list
			ArrayList<FollowViewDto> followingList = FollowViewDao.getInstance().getFollowing(userid);
			request.setAttribute("followingList", followingList);
			
			// Get follower's story
			List<List<StoryDto>> memberStoryList = new ArrayList<List<StoryDto>>();
			if(followingList != null) {
				for(FollowViewDto fdto : followingList) {
						if(fdto.getFollowing() != null) {
						String followingId = fdto.getFollowing();
						
						ArrayList<StoryDto> storyList = StoryDao.getInstance().getAllStory(followingId);
						memberStoryList.add(storyList);
					}
				}
			}
			request.setAttribute("memberStoryList", memberStoryList);
			
			// Get follower's post
			ArrayList<PostViewDto> postList = new ArrayList<PostViewDto>();
			if(followingList != null) {
				ArrayList<PostViewDto> tmpPostList = PostViewDao.getInstance().getAllPost(userid);
				if(tmpPostList != null) {
					for(PostViewDto pdto : tmpPostList) {
						for(FollowViewDto fdto : followingList) {
							if(fdto.getFollowing().equals(pdto.getUserid()) || fdto.getFollowing().equals(userid)) {
								postList.add(pdto);
								break;
							}
						}
					}
				}
			}
			request.setAttribute("postList", postList);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}