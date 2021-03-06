<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스토리 자세히 보기</title>
<link href="/css/spring.css" rel="stylesheet"> 
<link href="/css/story.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="/js/follow.js"></script>
<script type="text/javascript">

function story_like(story_num){
	var url = "spring.do?command=checkStoryLike&story_num=" + story_num;
	document.frm.action=url;
	document.frm.submit();
}

function deleteCheck(story_num){
	var answer = confirm("스토리를 삭제할까요?")
	if(answer){
		location.href="spring.do?command=deleteStory&story_num="+story_num;
	}
}

function setting(){
	document.getElementById("setting").style.display="flex";
}

function setting_close(){
	document.getElementById("setting").style.display="none";
}


function goReport(story_num) {
	var url="spring.do?command=reportForm&story_num=" + story_num;
	var _width = '400';
	var _height = '500';
	var _left = Math.ceil((window.screen.width - _width)/2); 
	var _top = Math.ceil((window.screen.width - _height)/2); 
	var opt = "toolbar=no, menubar=no, resizable=no, fullscreen=yes, location=no, " +
		"width="+_width+", height="+_height+", left="+_left;
	window.open(url, "reportPost", opt);
}
</script>
</head>
<body>
<form name="frm" method="post">
	
		
<!-- setting popup -->
	<div id="setting">
		<c:choose> 
			<c:when test="${StoryDto.userid == loginUser.userid}">
				<div id="setting_menu">
					<div class="setting_btn" onclick="location.href='spring.do?command=editStoryForm&story_num=${story_num}'"> 수정</div>
					<div class="setting_btn" onclick="deleteCheck(${story_num});"> 삭제 </div>
					<div class="setting_btn" onclick="setting_close();">닫기</div>
					<div class="setting_layer"></div>
				</div>
			</c:when>
			<c:otherwise>
					<div id="setting_menu">
						<c:choose>
							<c:when test="${ isFollowing == 1 }">
								<div class="setting_btn" onclick="unfollow('${ StoryDto.userid }')"> 언팔로우</div>
							</c:when>
							<c:otherwise>
								<div class="setting_btn" onclick="follow('${ StoryDto.userid }')">팔로우</div>
							</c:otherwise>
						</c:choose>
						<div class="setting_btn" onclick="goReport(${story_num});">신고</div>
						<div class="setting_btn" onclick="setting_close()">닫기</div>
						<div class="setting_layer"></div>
					</div>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="wrap" style="position:absolute">
	
		
		<div id=storyArea>
<!-- 로고 -->
			<div id="logo" onclick="location.href='spring.do?command=main'"> <img src="../images/logo.png" width="50px"> </div>
			
<!-- 화살표 -->
			<c:if test="${prev != 0}">
				<div id=goBefore style=cursor:pointer onclick="location.href='spring.do?command=followArrows&story_num=${prev}'">
					<span class="material-icons"> chevron_left </span> 
				</div>
			</c:if>
			
			<c:if test="${next != 0}">
				<div id=goNext style=cursor:pointer onclick="location.href='spring.do?command=followArrows&story_num=${next}'"> 
					<span class="material-icons"> chevron_right </span> 
				</div>
			</c:if>
			
<!-- 클릭시 유저 프로필로 이동 -->
			<div id="goUserprofile" onClick="location.href='spring.do?command=userpage&userid=${StoryDto.userid}'"> <!-- 클릭 시 유저 프로필로 이동하도록 function 추가 -->
				<img id="userprofile" src="/images/${ userImg == null || userImg.equals("") ? "tmpUserIcon.png" : userImg }">
			</div> 
			
			
			<div class=story_content>
<!-- 스토리 이미지 -->
				<img id="story_img" src="../images/${StoryDto.story_img}" > 
<!-- 글 작성자 프로필 -->
				<div id="story_user" onClick="location.href='spring.do?command=userpage&userid=${StoryDto.userid}'">
					<div id="userprofile" onClick="location.href='#'"> <!-- 클릭 시 유저 프로필로 이동하도록 function 추가 -->
						<img class="userImg" width=50px height=50px src="../images/${ userImg == null || userImg.equals("") ? "tmpUserIcon.png" : userImg }">
					</div> 
					<div id="userid"><b> ${StoryDto.userid}</b></div>
					<span id="story_date"><fmt:formatDate value="${StoryDto.create_date}"/></span>
				</div>
				
				<div id="buttons" onclick="setting();">
					<span class="material-icons" onClick="openSetting();"> more_vert </span>
				</div>
				
<!-- 작성한 글 내용 -->
			<c:choose>
				<c:when test="empty ${fontColor}">
					<div id="story_content">  <h2>  ${StoryDto.content}  </h2>  </div>
				</c:when>
				<c:otherwise>
					<div id="story_content" style="color:${StoryDto.fontColor}"> <h2>  ${StoryDto.content}  </h2>  </div> 
				</c:otherwise>
			</c:choose> 
<!-- 좋아요 버튼 -->
				<div id="reaction">
					<span class="material-icons" style="color:${likeColor}" onclick="story_like(${story_num});"> favorite_border </span>
				</div>
			</div>
			</div>  
		</div>
</form>
</body>
</html>