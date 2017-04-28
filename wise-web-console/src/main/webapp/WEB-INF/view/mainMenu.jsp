<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<!-- 一级菜单 -->
<c:forEach items="${sysResourceList }" var="sysResource">
	<c:set var="firstIsExistChildren" value="${sysResource.childrenList.size()>0 }"/>
	<%-- <c:set var="sysResourceUrl" value="${sysResource.url }"/>
	<c:if test="${ ! empty sysResource.url }">
		<c:set var="sysResourceUrl" value="${ctx }${sysResource.url }"/>
	</c:if> --%>
	<li>
     <a class="J_menuItem" href="<c:if test="${ ! empty sysResource.url }">${ctx }</c:if>${sysResource.url }">
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
   			<%-- <c:set var="secondSysResourceUrl" value="${secondSysResource.url }"/>
			<c:if test="${ ! empty secondSysResource.url }">
				<c:set var="secondSysResourceUrl" value="${ctx }${secondSysResource.url }"/>
			</c:if> --%>
   			<li>
		           	<a class="J_menuItem" href="<c:if test="${ ! empty secondSysResource.url }">${ctx }</c:if>${secondSysResource.url }">${secondSysResource.name }<c:if test="${secondIsExistChildren }"><span class="fa arrow"></span></c:if></a>
		           	<!-- 若存在三级菜单，则遍历 -->
		   				<c:if test="${secondIsExistChildren }">
		   					<ul class="nav nav-third-level">
		   						<c:forEach items="${secondSysResource.childrenList }" var="thirdSysResource">
		   							<%-- <c:set var="thirdSysResourceUrl" value="${thirdSysResource.url }"/>
									<c:if test="${ ! empty thirdSysResource.url }">
										<c:set var="thirdSysResourceUrl" value="${ctx }${thirdSysResource.url }"/>
									</c:if> --%>
		   							<li><a class="J_menuItem" href="<c:if test="${ ! empty thirdSysResource.url }">${ctx }</c:if>${thirdSysResource.url }">${thirdSysResource.name }</a></li>
		   						</c:forEach>
		   					</ul>
		   				</c:if>
           </li>
     		</c:forEach>
      </ul>
     </c:if>
 </li>
</c:forEach>