<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<style type="text/css">
table.mystyle
{
border-width: 0 0 1px 1px;
border-spacing: 0;
border-collapse: collapse;
border-style: solid;
}
 
.mystyle tr, .mystyle td
{
margin: 0;
padding: 4px;
border-width: 1px 1px 0 0;
border-style: solid;
}
</style>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<%
	
	Object[][] callData = null;
%>


<s:form action="PerformanceMetrics" id="paraFrm" validate="true"
	theme="simple" target="main">
	
	<s:hidden name="accountCode" />
<s:hidden name="myPage" id="myPage" />
	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Closed Call Summary</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr style="overflow: auto;">
			<td >
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="mystyle">

				
						
							<% int l,l1;
							try {
							%> <%
								 callData = (Object[][]) request.getAttribute("callData");
								 %>
									<%
									if (callData != null && callData.length != 0) {
									%> 
									<%								
											 for (l = 0; l < callData[0].length; l++) {
											 %> 
											<%if (l%10==0) { %> 
												<tr class="formth"> <%} %>
												<td>	 <%=String.valueOf(callData[0][l]).replace('_',' ')%></td>
											
											<%
												 if ((l+1)%10==0||l==(callData[0].length-1) ) {%>
												 <% int t= (int)Math.floor(l/10) * 10;  %>
												 </tr> 
												 <tr>												 
												
												 		<%for (l1 = t; l1 <= l; l1++) {%>
												    <td>  <%=String.valueOf(callData[1][l1])%> &nbsp;</td> 
												  <% }%>
												  </tr>
								 				
								 				
								 				
											<%
											
											}
											
											
									 	} 
									
									
									}
								 } catch (Exception e) {
								 %> <%
								 	e.printStackTrace();
								 	}
								 %> 
								
							
						
						
				


			</table>
			</td>
			</tr>
		
		


		
	</table>
</s:form>


<script type="text/javascript">

		
</script>
