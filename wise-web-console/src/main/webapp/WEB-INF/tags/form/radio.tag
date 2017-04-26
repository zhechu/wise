<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<%@ attribute name="name" required="true" type="java.lang.String" description="name"%>
<%@ attribute name="classes" type="java.lang.String" description="样式"%>
<%@ attribute name="items" required="true" type="java.util.List" description="选项列表"%>
<%@ attribute name="itemLabel" required="true" type="java.lang.String" description="标签属性名称"%>
<%@ attribute name="itemValue" required="true" type="java.lang.String" description="值属性名称"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认选中"%>
<c:forEach items="${items }" var="item" varStatus="status">
	<div class="${classes }">
		<c:choose>
			<c:when test="${item[itemValue] == defaultValue }">
				<input id="_${name }${status.index + 1}" type="radio" value="${item[itemValue] }" name="${name }" checked />
			</c:when>
			<c:otherwise>
				<input id="_${name }${status.index + 1}" type="radio" value="${item[itemValue] }" name="${name }" />
			</c:otherwise>
		</c:choose>
	    <label for="_${name }${status.index + 1}"> ${item[itemLabel] } </label>
	</div>
</c:forEach>