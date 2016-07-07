<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.headerTable{
background-color: #AAAAAA;

}
</style>
<table  width="100%">

	<tr bordercolor="black">
		<td width="100%" colspan="7" align="center" bordercolor="black"><b><u>Profile
		Management Report</u></b></td>
	</tr>

	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;&nbsp;&nbsp;&nbsp;
		<b>Profile List</b></td>
	</tr>

<tr>
<td width="100%" colspan="7" align="center">&nbsp;</td>
</tr>

<tr><td colspan="7"><table border="1" cellpadding="0" cellspacing="0" bordercolor="black"  width="100%">


	<tr>
		<td  width="5%" nowrap class="headerTable" align="center" height="22"><b>Sr. No.</td>
		<td  width="35%"  class="headerTable" align="center" height="22"><b>Menu Name</td>
		<td  width="10%"  class="headerTable" align="center"><b>Insert</td>
		<td  width="10%"  class="headerTable" align="center"><b>Update</td>
		<td  width="10%"  class="headerTable" align="center"><b>Delete</td>
		<td  width="10%"  class="headerTable" align="center"><b>View</td>
		<td  width="10%"  class="headerTable" align="center"><b>General</td>

	</tr>
	
	
	<%
			String obj[][] = (String[][]) request.getAttribute("menu");
			if (obj != null && !obj.equals("")) {
				System.out.println("length is---------------" + obj.length);
				for (int j = 0; j < obj.length; j++) {
					
					if(String.valueOf(obj[j][2]).equals("true")){
						obj[j][2] = "Yes";
					}else obj[j][2] = "No";
					
					if(String.valueOf(obj[j][3]).equals("true")){
						obj[j][3] = "Yes";
					}else obj[j][3] = "No";
					
					if(String.valueOf(obj[j][4]).equals("true")){
						obj[j][4] = "Yes";
					}else obj[j][4] = "No";
					
					if(String.valueOf(obj[j][5]).equals("true")){
						obj[j][5] = "Yes";
					}else obj[j][5] = "No";
					
					if(String.valueOf(obj[j][6]).equals("true")){
						obj[j][6] = "Yes";
					}else obj[j][6] = "No";
					
					
				
				}
				%>

<% for(int k=0;k<obj.length;k++){ %>
<tr>
								<td  nowrap width="5%" ><%=k + 1%>
								</td>
								<td  width="30%">
								
								<%= String.valueOf(obj[k][1]) %></td>
							      
							      <td width="5%" align="center"><%= String.valueOf(obj[k][2]) %>
							        </td> 
							        
							        <td width="5%"  align="center"><%= String.valueOf(obj[k][3]) %>
							      </td>
							        
							         <td width="5%" align="center"><%= String.valueOf(obj[k][4]) %>
							        </td>
							        
							        <td width="5%" align="center"><%= String.valueOf(obj[k][5]) %>
							      </td>
							        
							        <td width="5%"  align="center"><%= String.valueOf(obj[k][6]) %></td>
							        </tr>
							        

<%} %>


</table></td></tr>








	
	

	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>
</table>


<table  width="100%">

	<tr>
		<td width="100%" colspan="3" align="center">&nbsp;</td>
	</tr>
	
	
	<tr>
		<td width="100%" colspan="3" align="center"><b>User List</b></td>
	</tr>
	
	<tr><td colspan="7"><table border="1" cellpadding="0" cellspacing="0" bordercolor="black"  width="100%">

	<tr>
		<td  width="5%" nowrap class="headerTable" align="center" height="22"><b>Sr. No.</td>
		<!--  <td  width="10%"><b>Login Code</td>-->
		<td  width="10%" class="headerTable" align="center" height="22"><b>Employee ID</td>
		<td  width="30%" class="headerTable" align="center" height="22"><b>Employee Name</td>
		<td  width="20%" class="headerTable" align="center" height="22"><b>Employee Desg</td>
		<td  width="20%" class="headerTable" align="center" height="22"><b>User Name</td>


	</tr>

	
	<%
	int m= 1;
	%>
	<s:iterator value="profileBean.employeeList" status="stat">
		<tr>
			<td  width="5%"  nowrap><%=m++%></td>
			<!-- <td  width="10%"><s:property value="loginID" /></td>-->
			<td  width="10%" ><s:property value="empToken" /></td>
			<td  width="30%"><s:property value="empName" /></td>
			<td  width="20%"><s:property value="empDesg" /></td>
			<td  width="20%"><s:property value="loginName" /></td>


		</tr>

	</s:iterator>


<%} %>
</table></td></tr>

</table>



