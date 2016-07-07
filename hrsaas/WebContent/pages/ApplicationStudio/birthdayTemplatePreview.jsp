<%@ taglib uri="/struts-tags" prefix="s"%>
<%Object[][] templateBody = (Object[][])request.getAttribute("templateBody");
%>
<%=String.valueOf(templateBody[0][0]) %>
