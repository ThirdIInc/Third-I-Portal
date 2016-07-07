<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="Arrears" id="paraFrm" theme="simple">
	<table width="100%">
		<tr>
			<td class="pageHeader">Arrears</td>
		</tr>
		<tr>
			<td colspan="1" align="center">
			<table>
				
				<tr>
					<td>Year <font color="red" >*</font> :</td>
					<td colspan="1"><s:textfield label="%{getText('year')}"
						maxLength="4" theme="simple" name="year"
						onkeypress="return numbersonly(this);" /></td>
				</tr>
				<tr>
					<td>From Month <font color="red" >*</font> :</td>
					<td colspan="1"><s:select theme="simple" name="frmmonth"
						cssStyle="width:130"
						list="#{'0':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" /></td>
				</tr>
				<tr>
					<td>To Month <font color="red" >*</font> :</td>
					<td colspan="1"><s:select theme="simple"
						name="tomonth" cssStyle="width:130"
						list="#{'0':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" /></td>
				</tr>

				<tr>
					<td>Paybill Id <font color="red" >*</font> :</td>
					<s:hidden theme="simple" name="pbgrp" />
					<td colspan="2"><s:textfield label="%{getText('pbId')}"
						theme="simple" name="pbId" readonly="true" /><img
						src="../pages/images/search.gif" height="18" align="absmiddle"
						width="18"
						onclick="javascript:callsF9(500,325,'Arrears_f9Group.action');">
					</td>

				</tr>
				<tr>
				<td>&nbsp;</td></tr>

				<tr align="center">
					<s:hidden theme="simple" name="empType" />
					<td align="center" colspan="2"><s:submit cssClass="pagebutton" action="Arrears_view"
						theme="simple" onclick="return callAdd();" value="   View  " />
						<s:submit cssClass="pagebutton" action="Arrears_process"
						theme="simple" onclick="return callAdd();" value="   Process   " />
					<s:if test="creditHeader">
						<s:submit cssClass="pagebutton" theme="simple" action="Arrears_saveData"
							value="  Save  " />
					</s:if> <s:if test="creditHeader">
						<input type="button" class="pagebutton" theme="simple" value="Report" onclick="callReport()" />
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="creditHeader">
			<tr>
				<td colspan="1" align="center">
				<div align="center">
				<table>
					<tr>
						<td width="160" class="headercell">Employee Name</td>
						<td width="90" class="headercell">Month</td>
						<s:iterator value="creditHeader">

							<td align="middle" width="40" class="headercell"><s:property
								value="creditName" /><s:hidden /></td>
						</s:iterator>
						<td class="headercell">Total Credit Paid</td>
						<s:iterator value="creditHeader">

							<td align="middle" width="40" class="headercell"><s:property
								value="creditName" /><s:hidden /></td>
						</s:iterator>
						<td class="headercell">Total Credit Due</td>
							<s:iterator value="debitHeader">

							<td align="middle" width="40" class="headercell"><s:property
								value="debitName" /><s:hidden /></td>
						</s:iterator>
						
						<td class="headercell">Total Debit</td>
						
						<s:iterator value="debitHeader">

							<td align="middle" width="40" class="headercell"><s:property
								value="debitName" /><s:hidden /></td>
						</s:iterator>
						
						<td class="headercell">Total Debit Due</td>
						<td class="headercell">Net Paid</td>

						<%
							System.out.println("   rows +++++++++ ");
							Object[][] rows = (Object[][]) request.getAttribute("rows");
							Object[][] c = (Object[][]) request.getAttribute("creditLength");
							Object[][] d = (Object[][]) request.getAttribute("debitLength");
							int total_column = 2 *(c.length);
						%>
					</tr>

					<%		for (int k = 0,j=0; k < rows.length; k++,j++) {
							//System.out.println("the employee name is "+rows[k][1]+"then month is"+rows[k][0]);
							Object[][] row = (Object[][]) rows[k][0];
							for (int s = 0; s < row.length; s++) {
								%>
					<tr>
						<input type="hidden" name="emp_id" value="<%=row[s][0] %>" />
						<td width="160"><input type="text" size="24" name="empName"
							value="<%=row[s][1] %>" /></td>
						<td width="90" align="middle"><input type="text" size="8"
							name="mon" value="<%=row[s][2] %>" /></td>
						<%
						int l = 3;
						%>

						<s:iterator value="creditHeader">
							<td align="middle" width="40"><input type="text" size="4"
								name="<%=k%>" value="<%=String.valueOf(row[s][l]) %>" /></td>
						
							<%		l++;
							%>
						</s:iterator>
						
						<td><input type="text" size="6" name="totalCredit<%=k%>"
							value="<%=row[s][l] %>"></td>
						

						<s:iterator value="creditHeader">
							<%l++;
							%>

							<td align="middle" width="40"><input type="text" size="4"
								name="<%=k%>" value="<%=String.valueOf(row[s][l]) %>"
								onkeypress="return numbersonly(this);"
								onkeyup="sum(<%=c.length%>,<%=k%>)" /></td>
						</s:iterator>
						<%l++;
							%>
						<td><input type="text" size="6" name="totalCredDue<%=k%>"
							value="<%=row[s][l]  %>"></td>
							
						
						<s:iterator value="debitHeader">
							<%
							l++;
							%>

							<td align="middle" width="40"><input type="text" size="4" readonly="true"
								name="<%=j%>" value="<%=String.valueOf(row[s][l]) %>"
								onkeypress="return numbersonly(this);"
								onkeyup="sum1(<%=d.length%>,<%=j%>)" /></td>
						</s:iterator>
						<%
							l++;
							%>
						<td><input type="text" size="6" name="totalDebit<%=k%>" readonly="true"
							value="<%=row[s][l] %>"></td>
							
						<s:iterator value="debitHeader">
							<%l++;
							%>

							<td align="middle" width="40"><input type="text" size="4" readonly="true"
								name="<%=j%>" value="<%=String.valueOf(row[s][l]) %>"
								onkeypress="return numbersonly(this);"
								onkeyup="sum1(<%=d.length%>,<%=j%>)" /></td>
						</s:iterator>
						
						<%
							l++;
							%>
						<td><input type="text" size="6" name="totalDebitDue<%=k%>" readonly="true"
							value="<%=row[s][l] %>"></td>
						
						<%
							l++;
							%>						
						<td><input type="text" size="6" name="netPay<%=k%>" readonly="true"
							value="<%=row[s][l] %>"></td>
					</tr>
					<%
						}
						}
					%>

				</table>
				</div>
				</td>
			</tr>
		</s:if>
	</table>
</s:form>




<script type="text/javascript">
function callReport() {
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action="Arrears_report.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";						
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
function sum(cLen,s) {
		
		var formElements=document.getElementsByName(s);
		var creditAmount=0;
		var creditAmountDue=0;
 	
 	for (var i=formElements.length-1; i>=0; --i )
 				
		{
		if(i<(cLen))
		
 		 {
			creditAmount=creditAmount+eval(formElements[i].value*(100/100));
						
		}
		else
		{
	
				creditAmountDue = creditAmountDue +eval(formElements[i].value*(100/100));
				
		}
				
		} 		
 
  
 	   
 	var totalCreditDue=totalCreditDue+s;
 	
	document.getElementById("totalCredDue"+s).value=creditAmountDue;
		
	document.getElementById("netPay"+s).value=(creditAmountDue-creditAmount); 
	}
	function callAdd(){
	var yer = document.getElementById('year').value;
	var frmonth = document.getElementById('frmmonth').value;
	var tomonth = document.getElementById('tomonth').value;
	var paId = document.getElementById('pbId').value;
	if(paId=="")
	{
	if(yer==""){
			alert ("Please Enter a valid Year ");
			return false;
		}
		if(frmonth==0)
	{
	alert ("Please Select From Month ");
			return false;
	}
		if(tomonth==0)
	{
	alert ("Please Select To Month ");
			return false;
	}
	
	alert ("Please Select Pay Group ");
			return false;
	}
	
	
	
		if(!(yer==""))
		{
		
			var iChars = "!@#$%^&*()+=[]\\\';,/{}|\"<>?";
		  		for (var i = 0; i < yer.length; i++)
		  		 {		  	
			  	if (iChars.indexOf(yer.charAt(i)) != -1)
			  	 {
				  	alert ("Enter valid Year !");
				  	return false;
  					}
  				}
			}
			if(!(frmonth==""))
		{
		
			var iChars = "!@#$%^&*()+=[]\\\';,/{}|\"<>?";
		  		for (var i = 0; i < frmonth.length; i++)
		  		 {		  	
			  	if (iChars.indexOf(frmonth.charAt(i)) != -1)
			  	 {
				  	alert ("Enter valid month !");
				  	return false;
  					}
  				}
			}
			if(!(tomonth==""))
		{
		
			var iChars = "!@#$%^&*()+=[]\\\';,/{}|\"<>?";
		  		for (var i = 0; i < tomonth.length; i++)
		  		 {		  	
			  	if (iChars.indexOf(tomonth.charAt(i)) != -1)
			  	 {
				  	alert ("Enter valid month !");
				  	return false;
  					}
  				}
			}
			if(tomonth<frmonth)
			{
			alert ("Enter From month  less than  To month !");
				  	return false;
			}
	
	}
	
	
	function sum1(cLen,s) {
		
		var formElements=document.getElementsByName(s);
		var creditAmount=0;
		var creditAmountDue=0;
	
 	
 	for (var i=formElements.length-1; i>=0; --i )
 				
		{
		if(i<(cLen))
		
 		 {
			creditAmount=creditAmount+eval(formElements[i].value*(100/100));
						
		}
		else
		{
	
				creditAmountDue = creditAmountDue +eval(formElements[i].value*(100/100));
				
		}
				
		} 		
 
  
 	   
 	var totalCreditDue=totalCreditDue+s;
 	
	document.getElementById("totalDebit"+s).value=creditAmountDue;
		
	document.getElementById("netPay"+s).value=(creditAmountDue-creditAmount); 
	}
 
</script>

