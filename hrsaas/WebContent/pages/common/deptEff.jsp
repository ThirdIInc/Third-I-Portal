
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<%
Object rating [][]  =null;
String depteffCount[][]  = null;
	try{
			 rating   = (Object[][])request.getAttribute("rating");
			 depteffCount = (String[][])request.getAttribute("deptEffCount");
			if(rating!=null)
			{
				System.out.println("Rating Length is------------------ "+rating.length);
			}
			if(depteffCount != null)
			{
				System.out.println("Count Length is------------------ "+depteffCount.length);
			}
		}catch(Exception e)
		{
			System.out.println("Error Exception--------------------- is "+e);
		}
%>

<table width="100%">
<tr>
		<td colspan="2" width="100%" valign="top">
		
		<table width="100%">
	<tr>
		<td width="50%" class="formth"><b>Department</b></td>
		<%
			if(rating !=null)
			{
				for(int i = 0;i<rating.length;i++)
				{
			%>		
				<td width="7%" class="formth"><b><%= String.valueOf(rating[i][0])%></b></td>
		<%
				}
			}
		%>
		
	</tr>
	<%
		if(depteffCount !=null)
		{
			for(int j = 0; j<depteffCount.length;j++)
			{
				
			%>
			<tr>
			<td width = "50%" class="sortabletd"><%= String.valueOf(depteffCount[j][0])%></td>
			<%
				for(int k = 0 ; k < rating.length ;k++)
				{
			%>
				<td width="7%" class="sortabletd"><%= String.valueOf(depteffCount[j][k+1])%></td>
				<%
				}
				%>
			</tr>
			<%
			}
		}
			%>
</table>
</td>
</tr>
</table>



