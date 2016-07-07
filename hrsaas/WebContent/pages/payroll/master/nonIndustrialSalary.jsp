<%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="NonIndustrialSalary" id="paraFrm" >

<table width="100%" border="0">
<tr>
<td align="right">Month :</td> 
<td><s:textfield name="nonIndustrialSalary.month" theme="simple"  maxlength="50" size="40"> </s:textfield></td>
</tr>
<tr>
<td align="right">Year :</td><td><s:textfield name="nonIndustrialSalary.year" theme="simple"  maxlength="50" size="40"> </s:textfield></td>
</tr>



<tr>
<td width="32%" align="right">Sort by Employee Type :</td>
	<s:hidden name="nonIndustrialSalary.typeCode" />
	<td><s:textfield name="nonIndustrialSalary.typeName" theme="simple"  maxlength="50" size="40"> </s:textfield>
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9type.action');">	
</td>
</tr>
<tr>
<td width="32%" align="right">Sort by Pay Bill No :</td>

	<td><s:textfield name="nonIndustrialSalary.payBillNo" theme="simple"  maxlength="50" size="40"> </s:textfield>
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9payBill.action');">	
</td>
</tr>
<tr>

</tr>

 <tr>
 <td width="45%">&nbsp;</td>
<td><s:submit cssClass="pagebutton"  cssClass="button" theme="simple"  action="NonIndustrialSalary_getNonIndustrialSalaries" value="Non Industrial" />	 </td>
</tr>
</table>

 <table  border="0">
 <tr>
 <td width="90" class="headercell">Token No
 </td>
  <td  width="160" class="headercell">Employee Name
 </td>
<s:iterator value="creditHeader">  
<%int i=0; %>
<td align="middle" width="40" class="headercell">
<s:property value="creditName"/><s:hidden  />
</td>
 </s:iterator>
<s:iterator value="debitHeader">  
<td align="middle" width="40" class="headercell">
<s:property value="debitName"/>
</td>
 </s:iterator>


 </tr>
  <% 
  Object [][] rows=(Object[][])request.getAttribute("rows");
  %>
  <%for (int k=0;k<rows.length;k++) { %>
 <tr>
 <td width="90" class="headercell">Token No
 </td>
  <td  width="160" class="headercell">Employee Name
 </td><%i=0; %>

<s:iterator value="creditHeader">  

<td align="middle" width="40" class="headercell">
<input type="text" size="4" name="<%=i%>" value="<%=rows[0][i] %>" >
</td>
 <%i++; %>
 </s:iterator>
<s:iterator value="debitHeader">  
<td align="middle" width="40" class="headercell">
<s:property value="debitName"/>
</td>

 </s:iterator>


 </tr>
<% }%>
 </table>
</s:form>
 