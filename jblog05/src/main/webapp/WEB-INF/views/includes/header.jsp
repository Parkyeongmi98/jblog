<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="header">
	<h1>
		<a href="${pageContext.request.contextPath }" style="color:#fff">${blogvo.title }</a>
	</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a><li>
			</c:when>
			<c:otherwise>
				<c:if test="${blogvo.id eq authUser.id }">	
					<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/basic">블로그 관리</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<li><a>${authUser.name }님 안녕하세요 ٩(ˊᗜˋ*)و</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
	
