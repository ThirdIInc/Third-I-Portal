<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.model.admin.srd.OfficialDetailModel"
	import="org.paradyne.bean.common.LoginBean"%>
<%try{ 
	String [][]hrmData=null;
%>
<%
try{
	hrmData=(String[][])session.getAttribute("hrmData");
}catch(Exception e){
	e.printStackTrace();
}
%>
<%if(hrmData==null ){
	OfficialDetailModel model=new OfficialDetailModel();
	model.initiate(config.getServletContext(),session);
	try{
		LoginBean loginBean=(LoginBean)session.getAttribute("loginBeanData");
		String code=loginBean.getProfileCode();
		String multipleCode=loginBean.getMultipleProfileCode();
		model.showHRM(multipleCode);
	}catch(Exception e){
		e.printStackTrace();
	}
	model.terminate();
	hrmData=(String[][])session.getAttribute("hrmData");
}
	%>
<%@page import="org.paradyne.bean.common.LoginBean"%>
<table cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<s:hidden name="menuCode" id="menuCodeLink" />
		<td width="100%">
		<div id="tabnav">
		<ul>
			<%
         					for (int i = 0; i < hrmData.length; i++) {
        	 			%>
			<li><a href="#" id="list<%=(i+1)%>"
				onclick="callPage('<%=hrmData[i][1]%>','<%=hrmData[i][2]%>');callCurrent('<%=(i+1)%>','<%=hrmData.length%>');">
			<span><%=hrmData[i][0]%>&nbsp;&nbsp;</span> </a></li>
			<script>
								if(document.getElementById('menuCode').value == '<%=hrmData[i][2]%>'){
									document.getElementById('<%="list"+(i+1)%>').className = "on";
									}
						</script>
			<% } %>
		</ul>
		</div>
		</td>
	</tr>
</table>
<%}catch(Exception e){
	e.printStackTrace();
}
%>
<script>
function callPage(menuAction,menuCode){
	var name='<%=request.getContextPath()%>';
	document.getElementById('menuCodeLink').value=menuCode;
	document.getElementById("paraFrm").target="main";
	document.getElementById("paraFrm").action=name+menuAction;
	document.getElementById("paraFrm").submit();	
}
</script>