<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<%@ attribute name="name" required="true" type="java.lang.String" description="name"%>
<%@ attribute name="classes" type="java.lang.String" description="样式"%>
<%@ attribute name="items" required="true" type="java.util.List" description="选项列表"%>
<%@ attribute name="itemLabel" required="true" type="java.lang.String" description="标签属性名称"%>
<%@ attribute name="itemValue" required="true" type="java.lang.String" description="值属性名称"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认选中，多个则以逗号隔开，如：1,2,3"%>
<c:set var="values" value="${fn:split(defaultValue, ',') }" />
<c:forEach items="${items }" var="item" varStatus="status">
	<c:set var="checked" value="" />
	<div class="${classes }">
		<c:forEach var="value" items="${values }">
			<c:if test="${item[itemValue] == value }">
				<c:set var="checked" value="checked" />
			</c:if>
		</c:forEach>
		<input id="_${name }${status.index + 1}" type="checkbox" value="${item[itemValue] }" name="${name }" ${checked } />
	    <label for="_${name }${status.index + 1}"> ${item[itemLabel] } </label>
	</div>
</c:forEach>