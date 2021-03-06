<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 신고하기</title>
<link href="/css/spring.css" rel="stylesheet"> 
<link href="/css/report.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript">
function doPostReport(post_num){
	document.reportFrm.action="spring.do?command=postReport&post_num=" + post_num;
	document.reportFrm.submit();
}

function doStoryReport(story_num){
	document.reportFrm.action="spring.do?command=postReport&story_num=" + story_num;
	document.reportFrm.submit();
}
</script>
</head>
<body>
<form name="reportFrm" method="post">
	<div id="wrap">
		<div class="report_wrap"> 
			<div class="reportTitle"> <h3>신고</h3> </div>
			<div class="reportContentTitle"> <b>이 게시물을 신고하는 이유</b> </div>
			<div class="reportContent">
				<label><input type="radio" name="reportReson" class="checkbox-style" value="1" checked/> 스팸 혹은 반복되는 콘텐츠 </label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="2"/> 민감한 콘텐츠</label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="3"/> 폭력적인 콘텐츠</label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="4"/> 허위 사실 유포</label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="5"/> 자살 또는 자해 요소</label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="6"/> 불법 또는 규제 상품 판매</label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="7"/> 섭식 장애</label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="8"/> 혐오 발언 또는 상징</label>
				<label><input type="radio" name="reportReson" class="checkbox-style" value="9"/> 지식재산권 침해</label>
			</div>
			<div id="clear"></div>
			<label id="input-reset-button" class="inputButton" for="input-submit" > 신고하기 </label>
			
			<c:choose>
				<c:when test="${empty post_num}">
					<input type="button" value="신고하기" id="input-submit" onclick="doStoryReport(${story_num});">
				</c:when>
				<c:otherwise>
					<input type="button" value="신고하기" id="input-submit" onclick="doPostReport(${post_num});">
				</c:otherwise>
			</c:choose>
			<div id="clear"></div>
	</div>
	</div>
</form>
</body>
</html>