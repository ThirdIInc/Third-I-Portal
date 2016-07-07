<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="FundBalance" id="paraFrm" theme="simple" target="main">
	<table width="100%" border="0">
		<tr>
			<td width="100%" colspan="2" class="pageHeader" align="center">Fund
			Balance</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="32%" align="right" colspan="1"> Fund &nbsp;&nbsp;<font color="red">*</font>:</td>
			<td><s:textfield size="25" name="fundBalance.debitName"
				readonly="true" /><s:if test="%{fundBalance.generalFlag}">
               </s:if><s:else> <img src="../pages/images/search.gif" class="iconImage"
				height="18" align="absmiddle" width="18"
				onclick="javascript:callsF9(500,325,'FundBalance_f9action.action');"></s:else>
			<s:hidden name="fundBalance.debitCode" /></td>
		</tr>

		<tr>
			<td width="32%" align="right" colspan="1"> Pay Bill<font color="red">*</font>:</td>
			<td><s:textfield size="25" name="fundBalance.paybillID"
				readonly="true" /><s:if test="%{fundBalance.generalFlag}">
               </s:if><s:else> <img src="../pages/images/search.gif" class="iconImage"
				height="18" align="absmiddle" width="18"
				onclick="javascript:callsF9(500,325,'FundBalance_f9payaction.action');"></s:else>
			<s:hidden name="fundBalance.paybillGroup" /></td>
		</tr>


		<tr>
			<td >&nbsp;</td>
		</tr>

		
		<tr>
			<td colspan="6" align="center"><s:if test="%{fundBalance.viewFlag}"><s:submit cssClass="pagebutton"
				action="FundBalance_display" theme="simple" value=" Go "
				 onclick="return callGo()" /> <s:submit cssClass="pagebutton"
				action="FundBalance_reset" theme="simple" value=" Reset "
				 /></s:if><s:if test="saveFlag"><s:if test="%{fundBalance.insertFlag}"><s:submit cssClass="pagebutton" action="FundBalance_save"
				theme="simple" value="   Save   " onclick=""/></s:if><s:if test="%{fundBalance.viewFlag}"><input type="button" class="pagebutton" theme="simple" value="   Report  "
						onclick="callReport()" /></s:if></s:if>
				
				</td>
		</tr>

	</table>
	<s:if test="flag">
	<table width="100%" align="center" border="0">
		<tr>
			<td class="headercell" width="5%">Sr No.</td>
			<td class="headercell" width="15%">Token No.</td>

			<td class="headercell" width="30%">Employee Name</td>
			
			<td class="headercell" width="15%">Previous Balance</td>
			<td class="headercell" width="15%">Current Balance</td>
			<td class="headercell" width="15%">Total</td>

		</tr>

		<%
					try {
					System.out
					.println("----------------------------------value of=");
					String[][] centerChk = (String[][]) request
					.getAttribute("data");
					System.out
					.println("----------------------------------value of="
					+ centerChk.length);
					if (centerChk.length > 0) {
				for (int k = 0; k < centerChk.length; k++) {
		%>
		<tr>
			<td class="bodyCell" width="10%"><%=k + 1%></td>

			<s:hidden name="empId" value="<%= String.valueOf(centerChk[k][0]) %>" />
			<td class="bodyCell" width="15%"><%=String.valueOf(centerChk[k][1])%></td>
			<td class="bodyCell" width="25%"><%=String.valueOf(centerChk[k][2])%></td>
			
			<td class="bodyCell" width="25%"><input type="text" name="prebal" id="pre<%=k %>"
				value="<%= String.valueOf(centerChk[k][3]) %>" onkeypress="return numbersonly(this);" onblur="sum(<%=k%>,<%= String.valueOf(centerChk[k][3]) %>)" /></td>
			<td class="bodyCell" width="25%"><input type="text" name="curbal" id="cur<%=k %>"
				value="<%= String.valueOf(centerChk[k][4]) %>"onblur="sum(<%=k%>,<%= String.valueOf(centerChk[k][4]) %>)" readonly="readonly" /></td>
			<td class="bodyCell" width="25%"><input type="text" name="total<%=k%>"
				value="<%= String.valueOf(centerChk[k][5]) %>" readonly="readonly" /></td>
			


		</tr>

		<%
					}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Incatch----------------------");
				}
		%>

	

	</table>
	</s:if>
</s:form>

<script>
	
	
function sum(k,val) {
		 
		var a=document.getElementById("pre"+k).value;
		var b=document.getElementById("cur"+k).value;
		
		document.getElementById("total"+k).value=(eval(a) + eval(b)); 

  
	}
	
	
	
	
	function callGo()
{  
	var debitName=document.getElementById("fundBalance.debitName").value;
	var month=document.getElementById("fundBalance.paybillID").value;
		
	if(debitName=="")
	{
		alert("Please Select Fund");
		return false; 
	}
	
	if(month=="")
	{
		alert("Please Select Paybill");
		return false; 
	}
	
	
	
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
	
	function callReport(){
					
			    document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action="FundBalance_report.action"
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
			
			
			}

</script>


