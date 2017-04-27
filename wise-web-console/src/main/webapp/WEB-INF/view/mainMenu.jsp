<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<!-- 一级菜单 -->
<c:forEach items="${sysResourceList }" var="sysResource">
	<c:set var="firstIsExistChildren" value="${sysResource.childrenList.size()>0 }"/>
	<li>
     <a class="J_menuItem" href="${sysResource.url }">
     	<i class="${sysResource.icon }"></i> <span class="nav-label">${sysResource.name }</span>
     	<!-- 若存在二级菜单，则显示箭头 -->
     	<c:if test="${firstIsExistChildren }">
     		<span class="fa arrow"></span>
     	</c:if>
     </a>
     <!-- 若存在二级菜单，则遍历 -->
     <c:if test="${firstIsExistChildren }">
     	<ul class="nav nav-second-level">
      	<c:forEach items="${sysResource.childrenList }" var="secondSysResource">
   			<c:set var="secondIsExistChildren" value="${secondSysResource.childrenList.size()>0 }"/>
   			<li>
		           	<a class="J_menuItem" href="${secondSysResource.url }">${secondSysResource.name }<c:if test="${secondIsExistChildren }"><span class="fa arrow"></span></c:if></a>
		           	<!-- 若存在三级菜单，则遍历 -->
		   				<c:if test="${secondIsExistChildren }">
		   					<ul class="nav nav-third-level">
		   						<c:forEach items="${secondSysResource.childrenList }" var="thirdSysResource">
		   							<li><a class="J_menuItem" href="${thirdSysResource.url }">${thirdSysResource.name }</a></li>
		   						</c:forEach>
		   					</ul>
		   				</c:if>
           </li>
     		</c:forEach>
      </ul>
     </c:if>
 </li>
</c:forEach>