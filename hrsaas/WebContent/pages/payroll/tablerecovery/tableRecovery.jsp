<%@ taglib prefix="s" uri="/struts-tags"%>


<s:form action="TabRec" id="paraFrm" validate="true"
	 theme="simple" target="main"  >

	<table  width="100%" border="0">
      <tr>
  			<td width="100%" colspan="6" class="pageHeader" align="center" >Table Recovery</td>
  	  </tr>
  	   <tr>
	    <td >&nbsp;</td>
	  </tr>
	  
	
  	   <tr>
  	    <td width="8%" >&nbsp;</td>
	    <td width="25%">Select Debit <font color="red">*</font>:&nbsp;</td>
	    
	  		<td><s:textfield name="tabRec.debitName" size="30" readonly="true"/>
	  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
	  		width="18" onclick="javascript:callsF9(500,325,'TabRec_f9action.action');">
	  		<s:hidden name="tabRec.debitCode" /></td>
	  </tr>
	  
	  <tr><td width="8%" >&nbsp;</td>
	  		<td  width="25%"   >Select Pay Bill Group <font color="red">*</font>:&nbsp;</td>
	  		<td><s:textfield  size="25"	name="tabRec.payBillNo" size="30" readonly="true"  />
	  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'TabRec_f9payBill.action');"></td>
	 
	  </tr>   	
	    <tr><td width="8%" >&nbsp;</td>
	    <td width="25%">
	    Select Month <font color="red">*</font>:&nbsp;</td>
	  		<td>
	    <s:select theme="simple" name="tabRec.month" cssStyle="width:130" list="#{'':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"  /></td>
</tr>
	 
	  
	  <tr><td width="8%" >&nbsp;</td>
	    <td width="25%" >
	    Year <font color="red">*</font>:&nbsp;</td>
	  		<td>
	    
	  		<s:textfield name="tabRec.year" size="30" onkeypress="return numbersonly(this);" maxlength="4" />
	  		</td>
	  </tr>
	  
	  <tr>
	    <td colspan="4" class="seperator"></td>
	  </tr>
	  
	 <tr><td width="8%" >&nbsp;</td>
<td width="25%" >
	    Upload :</td>
	  		<td>
	
		<input type="text" name="uploadFileName" readonly="true" size="30"  />
		<input type="button" class="pagebutton" styleClass="button" property="upload" value="Upload" onclick="javascript:uploadFile();"/>
 </td>
</tr>

  <tr>
	    <td colspan="4" class="seperator"></td>
	  </tr>

	    
	   <tr>
	    <td >&nbsp;</td>
	  </tr>
	   <tr>
	    <td colspan="6" align="center" ><s:submit cssClass="pagebutton" action="TabRec_display" theme="simple" value=" Go " onclick="return callGo();"  /></td>
	  </tr>
	  <s:if test="%{tabRec.display}">
	<tr>
	  <table width="100%" align="center"  border="0">
	  <tr>  			
  		<td class="headercell" width="10%">Sr No.</td>
		<td class="headercell" width="15%">Token No.</td>
		
		<td class="headercell" width="25%">Employee Name</td>
		<td class="headercell" width="15%">Employee Debit Amount</td>
		<td class="headercell" width="15%">Employee Arrears Amount</td>
		<td class="headercell" width="10%">Loan Amount</td>
		<td class="headercell" width="15%">Total</td>
		
	</tr>	
	<% try{
		
	String[][] centerChk=(String[][])request.getAttribute("data");
	
	for(int k =0; k < centerChk.length; k++){ %>
	 <tr>	 
	      <td class="bodyCell" width="10%"><%=k+1%></td>
	      
	     			<s:hidden name="empId" value="<%= String.valueOf(centerChk[k][0]) %>"/>
	       <td class="bodyCell" width="15%"><%= String.valueOf(centerChk[k][1]) %></td>
	       <td class="bodyCell" width="25%"><%= String.valueOf(centerChk[k][2]) %></td>
	       <td class="bodyCell" width="15%"><input type="text" name="debAmt" id="deb<%=k %>" value="<%= String.valueOf(centerChk[k][3]) %>"  onkeyup="sum(<%=k%>,<%= String.valueOf(centerChk[k][3]) %>)"/>
	       </td>
	      <td class="bodyCell" width="15%"><input type="text"  name="salAmt" id="sal<%=k %>" value="<%= String.valueOf(centerChk[k][4]) %>"  onkeyup="sum(<%=k%>,<%= String.valueOf(centerChk[k][4]) %>)"/></td>
	           <td class="bodyCell" width="10%"><input type="text"   value="<%= String.valueOf(centerChk[k][5]) %>" readonly="readonly" /></td>
	           <td class="bodyCell" width="15%"><input type="text" name="netPay<%=k%>" value="<%= String.valueOf(centerChk[k][6]) %>" readonly="readonly"/></td>
	 
	     
	  </tr>    
	  
	  <%
	  }
	}catch(Exception e)
	{e.printStackTrace();}
	  %>   
	  <tr>
	    <td >&nbsp;</td>
	  </tr>
	   <tr>
	    <td colspan="8" align="center" ><s:submit cssClass="pagebutton" action="TabRec_save" theme="simple" value=" Save " /></td>
	  </tr>
	  </table>
	  
	</tr>
	 </s:if>
	 <s:else></s:else>
	  
</table>
</s:form>

<script>

function sum(k,val) {
		 
		var a=document.getElementById("deb"+k).value;
		var b=document.getElementById("sal"+k).value;
		
		if(a=="")
		{
			a=0;
		}
		if( b=="")
		{
		b=0;
		}
		document.getElementById("netPay"+k).value=(eval(a) + eval(b)); 

  
	}
	
	
	function numbersonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}

function callGo()
{
	var debitName=document.getElementById("tabRec.debitName").value;
	var month=document.getElementById("tabRec.month").value;
	var year=document.getElementById("tabRec.year").value;
	
	
	
	if(debitName=="")
	{
		alert("Please Select Dedit");
		return false; 
	}
		if(document.getElementById("tabRec.payBillNo").value=="")
	{
		alert("Please Select Paybill Group");
		return false; 
	}
	
	if(month=="")
	{
		alert("Please Enter Month");
		return false; 
	}
	
	if(year=="")
	{
		alert("Please Enter Year");
		return false; 
	}
		
}


function uploadFile() {
	
		var path="files/Recovery";
		window.open('../pages/common/uploadFile.jsp?path='+path,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
</script>