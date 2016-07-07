<%@ taglib prefix="s" uri="/struts-tags" %> 

<s:form action="OfficerReport" theme="simple" id="paraFrm" >
<table width="100%" border="0">
<tr>
 <td width="100%" colspan="4" class="pageHeader" align="center" >Service Officer's Biodata</td>
 <br>
 <br>
</tr>
<!--<tr>
 <td align="right">Employee Token:</td>
 <td><s:textfield name="token" theme="simple"  maxlength="50" size="40" readonly="true"/></td>
</tr>
<tr> 	  
<td width="32%" align="right">Employee Name:
	<s:hidden name="empId" />
</td>
<td>
	<s:textfield name="empName" theme="simple"  maxlength="50" size="40" readonly="true"/>
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'OfficerReport_f9action.action');">	
</td>
</tr>

<tr>
 <td align="right">Center :</td>
 <td><s:textfield name="center" theme="simple"  maxlength="50" size="40" readonly="true"/></td>
</tr>
<tr>
 <td align="right">Rank :</td>
 <td><s:textfield name="rank" theme="simple"  maxlength="50" size="40" readonly="true"/></td>
</tr>

--><!--<tr>
	<td width="36%" align="right"> Report Type : </td>
		<td>
		<s:select  name="repType"  list="#{'Pdf':'PDF','Html':'HTML'}"  theme="simple"/>		
	</td>
</tr>

 <tr>
  <td width="45%">&nbsp;</td>
  <td>
   <s:submit cssClass="pagebutton"  theme="simple"  value='Report' action="OfficerReport_report" onclick="return callReport();" />
  </td>
 </tr>
 
-->
</table>

<table>
<tr>

			<td width="22%" class="headercell">Employee Token</td>
			<td width="22%" class="headercell">Employee Name</td>
			<td width="22%" class="headercell">Center</td>
			<td width="22%" class="headercell">Rank</td>
			<td width="12%" class="headercell"></td>



	</tr>

		<s:iterator value="offList" status="stat">
		<s:hidden value="empId" />
			<tr><td width="15%" class="bodyCell"><s:property value="token" /></td>
				<td width="15%" class="bodyCell"><s:property value="empName" /></td>
				<td width="15%" class="bodyCell"><s:property value="center" /></td>
				<td width="15%" class="bodyCell"><s:property value="rank" /></td>
				<td width="15%" class="bodyCell"><s:if test="sbk.viewFlag"> <input type="button" class="pagebutton"
					onclick="callReport('<s:property value="empId"/>')" value=" Report " /> </s:if> </td>
			</tr>
		</s:iterator>
	</table>
<s:hidden name="paraId" />
</s:form>

<script >

	  function callReport(id){
	    document.getElementById("paraFrm").target="_blank";
	   	document.getElementById("paraFrm").action="OfficerReport_report.action";
	  	document.getElementById('paraId').value=id;
	    document.getElementById("paraFrm").submit();
	    document.getElementById("paraFrm").target="_main";
   }

</script>