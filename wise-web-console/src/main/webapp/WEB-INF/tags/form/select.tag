<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" description="id"%>
<%@ attribute name="name" type="java.lang.String" description="name"%>
<%@ attribute name="classes" type="java.lang.String" description="样式"%>
<%@ attribute name="style" type="java.lang.String" description="样式"%>
<%@ attribute name="items" type="java.util.List" description="选项列表"%>
<%@ attribute name="itemLabel" type="java.lang.String" description="标签属性名称"%>
<%@ attribute name="itemValue" type="java.lang.String" description="值属性名称"%>
<%@ attribute name="hintLabel" type="java.lang.String" description="提示标签"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认选中"%>
<select class="${classes}" name="${name }" id="${id }" style="${style}">
	<option value="">${hintLabel }</option>
	<c:forEach items="${items }" var="item">
		<c:choose>
			<c:when test="${item[itemValue] == defaultValue }">
				<option value="${item[itemValue] }" selected>${item[itemLabel] }</option>
			</c:when>
			<c:otherwise>
				<option value="${item[itemValue] }">${item[itemLabel] }</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>