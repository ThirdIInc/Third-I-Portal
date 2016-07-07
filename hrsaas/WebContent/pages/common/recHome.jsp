<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

<script type="text/javascript"
	src="../pages/common/include/javascript/yui/glo.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/log.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/dom.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/event.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/yahoo.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/lang.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/connection.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/dragdrop.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/yui/ygddlist.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/glo_3.js"></script>
<script type='text/javascript'
	src="../pages/common/modules/home/home.js"></script>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/mydashlet.js"></script>





<s:form action="MyPage" id="paraFrm" theme="simple">


	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td width="70%" valign="top"><script>
				dashlet('10','Services','400','1','1');	
				dashlet('0','Process Manager Alert !','100','1','0');		
				dashlet('1','My Performance','130','1','0');	
				dashlet('2','My Leave Status','130','1','0');		
				dashlet('3','My Training Program','100','1','0' );
		 </script></td>
		<td width="30%" valign="top">
				<script>
				dashlet('4','Value Added Services(VAS)','400','1','0');
			</script>					
			
			</td>
		</tr>

	</table>
	<script type="text/javascript">
//SUGAR.sugarHome.maxCount = 	15;
//dashletIds = ["0","1","2","3","4","5"];

SUGAR.sugarHome.maxCount = 	15;
dashletIds = ['0','1','2','3','4','10'];
dashletLoaders = ['<%=request.getContextPath()%>/srd/AlertMessage_input.action','<%=request.getContextPath()%>/common/MyPage_setmyPerformance.action','<%=request.getContextPath()%>/common/MyPage_setMyLeave.action','<%=request.getContextPath()%>/common/MyPage_setmyTrainingProgram.action','<%=request.getContextPath()%>/pages/common/profile/Rec_service.jsp','<%=request.getContextPath()%>/pages/common/profile/blank.jsp'];
</script>
	<script src='../pages/common/include/javascript/mybotdashlet.js'></script>

</s:form>