<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

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





<s:form action="TaxationHome" id="paraFrm" theme="simple">


<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr >
			<td  width="70%" valign="top"><script>
			
			
			dashlet('0','Pending Queries','175','1','0');		
			 
			dashlet('1','Tax Statistics,08','175','1','0');	
			
		  
			
				
		</script></td>
		</tr>

		
</table>
<script type="text/javascript">


SUGAR.sugarHome.maxCount = 	15;
dashletIds = ['0','1'];
dashletLoaders = ['<%=request.getContextPath()%>/common/TaxationHome_pending.action','<%=request.getContextPath()%>/common/TaxationHome_taxSt.action'];
</script> <script src='../pages/common/include/javascript/mybotdashlet.js'></script>

</s:form>