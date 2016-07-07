<%@ taglib prefix="s" uri="/struts-tags" %>


<s:form action="EmpCredit" id="paraFrm" validate="true" target="_top">
<table class="tableBody" width="100%">
    <tr>
      <td width="25%" colspan="6" class="pageHeader">
        <p align="center"> Employee Credit Detail</td>
      </tr>
      <tr>
        <td width="25%" colspan="3"></td>
      </tr>
      
     <tr><td class= "COMMONLABEL" >Emp Id</td>
     
		<td  width="25%" colspan="3">
  		<s:textfield label="%{getText('empId')}"   theme="simple"  name="empId"/>
  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callsF9(500,400,'empCredit_f9action.action');">
  		</td>
  		</tr>
  		<tr><td class= "COMMONLABEL" >Employee  Code</td>
		<td colspan="3">
  		<s:textfield label="%{getText('empCredit')}"   theme="simple"  name="empCredit"/>
  		</td>
  		</tr>
  		
  		<tr><td class= "COMMONLABEL" >Employee  Name</td>
		<td colspan="3">
  		<s:textfield   theme="simple"  name="empName"/>
  		</td>
  		<td class= "COMMONLABEL" >Amount</td>
  		<td width="25%" colspan="3">
  		<s:textfield label="%{getText('empAmount')}"   theme="simple"  name="empAmount"/>
  		</td>
  		</tr>
  		<tr><td class= "COMMONLABEL" >Department</td>
		<td width="25%" colspan="3">
  		<s:textfield   theme="simple"  name="empDepart"/>
  		</td>
  		<td class= "COMMONLABEL" >Center</td>
  		<td width="25%" colspan="3">
  		<s:textfield label="%{getText('empDepart')}"   theme="simple"  name="empCenter"/>
  		</td>	</tr>
  		<tr><td class= "COMMONLABEL" >Trade</td>
  		<td width="25%" colspan="3">
  		<s:textfield label="%{getText('empTrade')}"   theme="simple"  name="empTrade"/>
  		</td>
  		<td class= "COMMONLABEL" >Rank</td>
  		<td width="25%" colspan="3">
  		<s:textfield label="%{getText('empRank')}"   theme="simple"  name="empRank"/>
  		</td>
  		</tr>
  		<tr><td></td></tr>
  		<tr>
  		<tr>
  		<td align="center" colspan="3"><s:submit cssClass="pagebutton" action="EmpCreditAction_save" theme="simple"  value="   Save   " />&nbsp;
  		<s:submit cssClass="pagebutton" action="EmpCreditAction_reset" theme="simple"   value="  Reset  " />&nbsp;
  		<s:submit cssClass="pagebutton" action="EmpCreditAction_delete" theme="simple"   value="  delete  " />&nbsp;
  		 <input type="button" class="pagebutton" onclick="call('EmpCreditAction_report.action')"  theme="simple"   value="  Report " />	
  		 </td>
  		</tr>
  		
  		  </table>
  		 </s:form>
  		 <!--
  			
  			 
  		<table class="tableBody" width="100%">
  		<tr>
  		<td width="25%" colspan="6" class="pageHeader">
        <p align="center"> Sr.no</td>
        <td width="25%" colspan="6" class="pageHeader">
        <p align="center"> Salary Header</td>
        <td width="25%" colspan="6" class="pageHeader">
        <p align="center"> Amount</td>
        <td width="25%" colspan="6" class="pageHeader">
        <p align="center"> Applicable</td>
  		</tr>
  		<s:iterator value="nomDetail.nomList" status="stat"> 
	  <tr>
	   	<td width="10%" class="bodyCell"><s:property value="srno"/></td>
	    <td width="20%" class="bodyCell"><s:property value="salhead"/></td>
	    <td width="25%" colspan="3">
  		<s:textfield label="%{getText('empAmount')}"   theme="simple"  name="empAmount"/>
  		</td>
  		<td>
		<s:checkbox label="checkbox test" name="check" value="aBoolean" fieldValue="true"/>

  		</td>
	    </tr>
	</s:iterator>	 	
     </table>
     
     
     -->
      <script>
  	function call(name){
  	
  	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action=name;
  	document.getElementById('paraFrm').submit();
  	}
  	</script>