<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.HashMap"%>
<s:form action="DaArrearAudit"  id="paraFrm">
<s:hidden name="daArrearAudit.typeCode" />

<table width="100%" border="0"> 
	<tr>
		<td class="pageHeader" colspan="3" ><center>DA Arrear Audit</center></td>
	</tr>
	<tr>
	 <td colspan="3">&nbsp;</td>
	</tr>
 <tr>
 <td width="30%" align="right"><font color="red">*</font></td>
  <td align="left">DA Date : </td>
  <td>
  <s:textfield name="daArrearAudit.daDate" theme="simple" maxlength="10" size="30" id="date"></s:textfield>
  <s:a href="javascript:NewCal('daArrearAudit.daDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>	
  </td>
 </tr>
 <tr>
  <td width="20%" align="right">&nbsp;</td>
  <td align="left">Select Employee Type :</td>  
  <td><s:textfield name="daArrearAudit.empType" theme="simple" maxlength="40" size="30" readonly="true"></s:textfield>
  <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'DaArrearAudit_f9type.action');">	
  </td>
 </tr>
 <tr>
  <td width="20%" align="right">&nbsp;</td>
  <td align="left">Select Pay Bill :</td>
  <td>
      <s:textfield name="daArrearAudit.payBillNo"  theme="simple" maxlength="40" size="30" readonly="true"></s:textfield>
	  <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'DaArrearAudit_f9payBill.action');">	
  </td>
 </tr>
 <tr>	
    <td colspan="2">&nbsp;</td>
	<td  align="left">
	  <s:submit cssClass="pagebutton"   theme="simple"   action="DaArrearAudit_view" value="  View  " onclick="return validate()" />
	  <s:if test="daList">
	    <s:submit cssClass="pagebutton" theme="simple"   action="DaArrearAudit_save" value="  Save  " onclick="return validate()" />
	  </s:if>
	</td>
</tr>
</table>

<s:if test="daList">
<table width="100%">
 <tr>
   <td  class="headercell" width="20%">Sr NO</td>
   <td  class="headercell" width="20%">Employee Name</td>
   <td  class="headercell" width="15%">Paid Da</td>
   <td  class="headercell">Due DA</td>
   <td  class="headercell">Effective Date</td>
   <td  class="headercell">Audit</td>
 </tr>

 <% int i=0,s=1; HashMap hmArr=(HashMap)request.getAttribute("AUDIT"); %>
  <s:iterator value="daList">
  <% String audit=(String)hmArr.get(""+i); %>
    <s:hidden name="empId" />
    <tr>
     <td align="center"><%=s%></td>
     <td><s:property value="empName" /></td>
     <td>
	   <s:textfield name="paidDa"  theme="simple"   maxlength="30" size="10"> </s:textfield> 
	 </td>
	 <td align="middle">
	   <s:textfield  name="dueDa"  theme="simple"   maxlength="30" size="10"> </s:textfield> 
	 </td>
	 <td align="middle">
	   <s:property value="effectiveDate" />
	 </td>
	 <td>

    <input type="checkbox" name="chk" <%=audit.equals("true")?"checked":"" %> onclick="callChk(<%=i%>)"  >

	   <input  type="hidden" name="audit" id="<%=i%>" value="<%=audit%>" >
	 </td>
	</tr>  
	<%i++;s++;%>  
  </s:iterator>
</table>
</s:if>
</s:form>

<script>
function isDate(dt){
 l=dt.length;
 for(i=0;i<l;i++){
	  c=dt.charAt(i);
	  if((i==2 || i==5) && !(c=="-")){
		return false;
	  }
  }
 return true; 
}

function validate(){
    if(document.getElementById("date").value==""){
        alert('Please enter DA date');
        document.getElementById("date").focus();
 		return false;	 	
    }    
	if(!isDate(document.getElementById("date").value)){     
	 	alert('Invalid DA date entered.');
 		document.getElementById("date").focus();
 		return false;	 	
	}
	return true;
}
 function callChk(id){
  if(document.getElementById(id).value=='false'){
	   document.getElementById(id).value='true';
       //alert(document.getElementById(id).value);
  }else{
       document.getElementById(id).value='false';
       //alert(document.getElementById(id).value);
  }
  
 }

</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js" >
</script>

