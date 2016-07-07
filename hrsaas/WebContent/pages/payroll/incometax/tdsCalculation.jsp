<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script>
var eCode = new Array();
</script>
<style>
</style>
<script type="text/javascript"
	src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/syncscroll.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/superTables.js"></script>
<link href="../pages/common/include/javascript/superTables_Default.css"
	rel="Stylesheet" type="text/css" />
<s:form action="TDSCal" id="paraFrm" theme="simple">
	<s:hidden name="tdscal.branchCode" />
	<s:hidden name="tdscal.deptCode" />
	<s:hidden name="tdscal.divisionCode" />
	<s:hidden name="tdscal.typeCode" />
	<s:hidden name="tdscal.chkShwDetail" />
	<input type="hidden" name="prvSearchId" id="prvSearchId"/>
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">TDS
					Calculation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td><s:if test="%{tdscal.viewFlag}">
						<s:submit cssClass="search" action="TDSCal_view" theme="simple"
							value="   View   " onclick="return callView()" />
						<s:submit cssClass="reset" action="TDSCal_input" theme="simple"
							value=" Reset"  />

						<!--<s:submit cssClass="token" action="TDSCal_calculate"
							theme="simple" value="   Calculate  " onclick="return callView()" />
					-->
					</s:if> <s:if test="savebutFlag">
						<s:if test="%{tdscal.insertFlag}">
							<!--<s:submit cssClass="  save" action="TDSCal_save" theme="simple"
								value="   Save  " />
						-->
						</s:if>
					</s:if></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="15%" colspan="1"><label class="set" id="taxFrmYr"
						name="taxFrmYr" ondblclick="callShowDiv(this);"><%=label.get("taxFrmYr")%></label> : </td>
					<td width="25%" colspan="1"><s:textfield size="5" maxlength="4"
						name="tdscal.fromYear" onkeypress="return numbersonly(this);"
						onblur="add()" /></td>
					<td width="15%" colspan="1"><label class="set" id="taxToYr"
						name="taxToYr" ondblclick="callShowDiv(this);"><%=label.get("taxToYr")%></label> : </td>
					<td width="50%" colspan="1"><s:textfield size="5" maxlength="4" name="tdscal.toYear"
						onkeypress="return numbersonly(this);" readonly="true" /></td>
				</tr>
				<tr>
					<td width="15%"><label class="set" id="division"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td width="25%"><s:textfield size="25" name="tdscal.divisionName"
						size="25" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'TDSCal_f9Div.action');"></td>
					<td width="15%"><label class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> : </td>
					<td width="50%"><s:textfield size="25" name="tdscal.branchName" size="25"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'TDSCal_f9Branch.action');"></td>
				</tr>
				<tr>
					<td width="15%"><label class="set" id="department"
						name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> : </td>
					<td width="25%"><s:textfield size="25" name="tdscal.deptName" size="25"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="callsF9(500,325,'TDSCal_f9Dept.action');"></td>
					<td width="15%"><label class="set" id="employee.type"
						name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%> </label> : </td>
					<td width="50%"><s:textfield size="25" name="tdscal.typeName" size="25"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'TDSCal_f9type.action');"></td>
				</tr>
				<tr>
					<td colspan="4" align="center"></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><strong>OR</strong></td>
				</tr>
				<tr>
					<td colspan="4" align="center"></td>
				</tr>
				<tr>
					<td width="15%">
						<label name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee") %></label> : 
					</td>
					<td width="85%" colspan="3">
						<s:hidden name="employeeId" />
						<s:textfield name="employeeToken" size="12" readonly="true"/>
						<s:textfield name="employeeName" size="30" readonly="true"/>
						<img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="callsF9(500,325,'TDSCal_f9Employee.action');">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<%
						if((String[][]) request
								.getAttribute("data") != null && ((String[][]) request
								.getAttribute("data")).length > 0)
						{
					%>
					<tr>
					<td>
					<table width="100%" class="formbg">
					<tr>
						<td width="15%"><label class="set" id="empSearch"
							name="empSearch" ondblclick="callShowDiv(this);"><%=label.get("empSearch")%>:</label></td>
						<td width="85%" colspan="3"><s:hidden name="tdscal.empSearchId" /><s:textfield
							name="tdscal.empSearchToken" size="12" theme="simple"/><s:textfield
							name="tdscal.empSearchName" theme="simple"
							readonly="true" maxlength="50" size="30" /> 
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TDSCal_f9SearchEmp.action');">
						&nbsp;&nbsp; <input type="button" class="token"  
						value="Search" onclick="searchEmp();" /></td>
					</tr>
					</table>
					</td>
					</tr>
					<%
						}
					%>
						
		<s:hidden name="emptypeFlag" />
		<s:hidden name="paybillFlag" />
		<s:hidden name="branchFlag" />
		<s:hidden name="departmentFlag" />
		<s:hidden name="divisionFlag" />



		<s:if test="chkList">
			<tr>
				<td>
				<table id="thetable" cellspacing="0" cellpadding="0" width="100%">
					<tr height="25">
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="10" value="Employee Id"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="22" value="Employee Name"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="10" value="Total Income"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="8" value="Exemptions"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="10" value="Other Income"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="9" value="ChapterVI-A"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="7" value="ChapterVI"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="5" value="PTax"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="17" value="Net Taxable Income"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="7" value="Total Tax"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="7" value="Surcharge"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="12" value="Education Cess"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="7" value="Net Tax"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="7" value="Tax Paid"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px; font-weight: bold"
							size="12" value="Tax Per Month"></td>
						<td><input type="text" class="tokenPay" readonly="readonly"
							style="text-align: left; border: none; margin: 1px; font-weight: bold"
							size="8" value="Details"></td>

					</tr>
					<%!int total = 0;
	int count = 0;%>
					<%
									try {

									String[][] centerChk = (String[][]) request
									.getAttribute("data");

									for (int i = 0, k = 0; k < centerChk.length; k++, i++) {
							%>

					<tr height="25" id="trId"<%=k %>>
						<td><input type="hidden" readonly="readonly" name="empId"
							id="empId<%=k %>" value="<%= String.valueOf(centerChk[k][0]) %>">
						<input type="hidden" readonly="readonly" name="remainMonthToSave"
							value="<%= String.valueOf(centerChk[k][23]) %>"> <input
							type="text" readonly="readonly" size="12" name="tokenNo"
							id="tokenNo<%=k %>"
							style="text-align: left;border: none; background-color: #FFFFFF"
							value="<%=String.valueOf(centerChk[k][1])%>"></td>
						<td><input type="text" size="22" name="empName"
							id="empName<%=k %>" readonly="readonly"
							style="text-align: left;border: none; background-color: #FFFFFF; cursor: none;"
							value="<%=String.valueOf(centerChk[k][2])%>"></td>

						<td><input type="text" size="10" readonly="readonly"
							name="grossSal" id="grossSal<%=k %>"
							value="<%= String.valueOf(centerChk[k][3]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF"></td>


						<td><input type="text" size="8" name="excemp"
							id="excemp<%=k %>" value="<%= String.valueOf(centerChk[k][4]) %>"
							onkeypress="return numbersOnly();" readonly="readonly"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>

						<td><input type="text" size="10" readonly="readonly"
							name="othIncome" id="othIncome<%=k %>"
							value="<%= String.valueOf(centerChk[k][5]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>

						<td><input type="text" size="9" name="deductions"
							id="deductions<%=k %>" readonly="readonly"
							value="<%= String.valueOf(centerChk[k][6]) %>"
							onkeypress="return numbersOnly();"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;">
						</td>

						<td><input type="text" size="7" readonly="readonly"
							name="rebate" id="rebate<%=k %>"
							value="<%= String.valueOf(centerChk[k][7]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>

						<td><input type="text" size="5" readonly="readonly"
							name="ptax" id="ptax<%=k %>"
							value="<%= String.valueOf(centerChk[k][8]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>
						<td><input type="text" size="17" readonly="readonly"
							name="taxIncome" id="taxIncome<%=k %>"
							value="<%= String.valueOf(centerChk[k][9]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>
						<td><input type="text" size="7" readonly="readonly"
							name="totalTax" id="totalTax<%=k %>"
							value="<%= String.valueOf(centerChk[k][10]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>
						<td><input type="text" size="7" readonly="readonly"
							name="surCharge" id="surCharge<%=k %>"
							value="<%= String.valueOf(centerChk[k][12]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>
						<td><input type="text" size="12" readonly="readonly"
							name="edcCess" id="edcCess<%=k %>"
							value="<%= String.valueOf(centerChk[k][11]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>
						<td><input type="text" size="7" readonly="readonly"
							name="netTax" id="netTax<%=k %>"
							value="<%= String.valueOf(centerChk[k][13]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>
						<td><input type="text" size="7" readonly="readonly"
							name="taxPaid" id="taxPaid<%=k %>"
							value="<%= String.valueOf(centerChk[k][14]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #FFFFFF;"></td>
						<td><input type="text" size="12" readonly="readonly"
							name="taxPerMonth" id="taxPerMonth<%=k %>"
							value="<%= String.valueOf(centerChk[k][15]) %>"
							style="text-align: right; border: none; margin: 1px; background-color: #F2F2F2;"></td>
						<td align="right"><a href="#"
							onclick="abc('<%= String.valueOf(centerChk[k][17]) %>','<s:property value="<%= String.valueOf(centerChk[k][0]) %>"/>','<s:property value="<%= String.valueOf(centerChk[k][18]) %>"/>','<%= String.valueOf(centerChk[k][1]) %>','<%= String.valueOf(centerChk[k][2]) %>','<%= String.valueOf(centerChk[k][19]) %>','<%= String.valueOf(centerChk[k][20]) %>','<s:property value="<%= String.valueOf(centerChk[k][21]) %>"/>','<%= String.valueOf(centerChk[k][22]) %>')">Details</a></td>

					</tr>

					<script>
					eCode[<%=k%>] = "<%=String.valueOf(centerChk[k][0])%>";
			</script>

					<%
									count = k;
									}
									total = count;
							%>

				</table>
				</td>
			</tr>


			<%
				request.removeAttribute("chkFlag");
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>

		</s:if>
		<s:else></s:else>

		<s:hidden name="tdscal.detailCheck" />
		<s:hidden name="tdscal.invId" />
		<s:hidden name="tdscal.paraId" />
		<s:hidden name="tdscal.salary" />
		<s:hidden name="tdscal.empName" />
		<s:hidden name="tdscal.str" />
		<s:hidden name="tdscal.lop" />
		<s:hidden name="tdscal.nda" />
		<s:hidden name="tdscal.daArr" />
		<s:hidden name="tdscal.taxParaDebitCode" />

	</table>

</s:form>

<script>


function callPage(id){
var process= document.getElementById('hdView').value;
if(id=='P'){
var p=document.getElementById('hdPage').value;
id=eval(p)-1;
}

if(id=='N'){
var p=document.getElementById('hdPage').value;
id=eval(p)+1;
}

document.getElementById('hdPage').value=id;
if(process==''){
	var con=confirm("Do you want to save the page and proceed to the next page");
	if(con){
		document.getElementById('paraFrm').action="TDSCal_save.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').action="TDSCal_calculate.action";
		
	}
	else{
	//document.getElementById('paraFrm').action="NonIndustrialSalary_getNonIndustrialSalaries.action";
	return false;
	}
	}
else{

	var con=confirm("Do you want to proceed to the next page");
	if(con){
		document.getElementById('paraFrm').action="TDSCal_view.action";
		document.getElementById('paraFrm').submit();
	}
	else{
	//document.getElementById('paraFrm').action="NonIndustrialSalary_getNonIndustrialSalaries.action";
	return false;
	}
}

document.getElementById('paraFrm').submit();
}
 
 function callCheckFlag(id){
 //alert('id==='+id);
	 if(document.getElementById('pmChkFlag'+id).checked==true){
	 	document.getElementById('hdChkFlag'+id).value='Y';
	 }
	 else{
	 	document.getElementById('hdChkFlag'+id).value='N';
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
	
	
	 function callView(){
	  var from = document.getElementById('paraFrm_tdscal_fromYear').value;
	 // var payBill = document.getElementById('paraFrm_tdscal_payBillNo').value;
	   
	   	 var finYrFrm=document.getElementById('taxFrmYr').innerHTML.toLowerCase();	   	 
	     var division=document.getElementById('division').innerHTML.toLowerCase();
	     
	     var branch=document.getElementById('branch').innerHTML.toLowerCase();
	     
	   
	    if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm+".");
	    	return false;
	    }
	    if(eval(from)<2000){
	    	alert(finYrFrm+" cannot be less than 2000");
	    	return false;
	    }
	 /* if(document.getElementById('paraFrm_emptypeFlag').value == 'true' && document.getElementById('paraFrm_tdscal_typeCode').value == "")
		{
			alert("Please select the Employee Type!");
			return false;
		}
		if(document.getElementById('paraFrm_paybillFlag').value == 'true' && document.getElementById('paraFrm_tdscal_payBillNo').value == "")
		{
			alert("Please select the PayBill!");
			return false;
		} */
	/*	if(document.getElementById('paraFrm_departmentFlag').value == 'true' && document.getElementById('paraFrm_tdscal_deptCode').value == "")
		{
			alert("Please select the Department!");
			return false;
		} */
	    document.getElementById('hdView').value='xxx';
	  // alert('in alert'+document.getElementById('hdView').value);
   }
   
   function add()
   {
    var from = document.getElementById('paraFrm_tdscal_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_tdscal_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_tdscal_toYear').value=x;
	  }
   }
   
   
	       
	    function abc(gender,empId,age,token,empName,center,rank,centerId,joinedMonth)
	    {
	    	//alert('joinedMonth'+joinedMonth);
	     	//alert('token'+token);
	     // alert('empName'+empName);
	       //	alert('center'+center);
	       // alert('rank'+rank);
	       var detail = document.getElementById('paraFrm_tdscal_detailCheck').value;
	       var check = document.getElementById('paraFrm_tdscal_chkShwDetail').value;
	    var frmYear = document.getElementById('paraFrm_tdscal_fromYear').value;
	    var toYear = document.getElementById('paraFrm_tdscal_toYear').value;
	     var path = '<%=request.getContextPath() %>';
	     //alert('detail'+detail);
	     if(detail=="true"){
	     	alert('Please save the records for view detail');
	     }
	     if(check == 'v'){
	     	window.open(path+'/incometax/TdsCalculator_viewCalculator.action?gender='+gender+'&bbb='+empId+'&frYr='+frmYear+'&toYr='+toYear+'&age='+age+'&token='+token+'&name='+empName+'&center='+center+'&rank='+rank+'&centerId='+centerId+'&joinedMonth='+joinedMonth,'','width=800,height=500,scrollbars=yes,resizable=yes,top=50,left=100');
	     }
	     else{
	     	return false;
	     }
  		
	    }
	    
	    
	
xxx();
function xxx(){
try {
myST = superTable("thetable", { fixedCols : 0,rightCols:2,viewCols:6});
}catch(e)
{
	// alert(e);
}

}  


	/*function doAll(type)
	{
		
		cntExist = 0;
			
		if(type == 'select')
		{
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById("pmChkFlag"+i).checked = true;
				document.getElementById('hdChkFlag'+i).value='Y';
			}
			document.getElementById('allSel').style.display = 'none';
			document.getElementById('allClr').style.display = 'inline';
			
			cntExist = eCode.length;
		}
		else
		{
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById("pmChkFlag"+i).checked = false;
				document.getElementById('hdChkFlag'+i).value='N';
			}
			document.getElementById('allSel').style.display = 'inline';
			document.getElementById('allClr').style.display = 'none';
		}
	}*/
	
	
	 function selectAll()
	       {
	      
	      	
	      if(document.getElementById("selectId").checked){  
		       for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById("pmChkFlag"+i).checked = true;
					document.getElementById('hdChkFlag'+i).value="Y";
				}
	      }
	      else{
	      for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById("pmChkFlag"+i).checked = false;
					document.getElementById('hdChkFlag'+i).value="N";
				}
	      }
	       
	       }
	       
	  
	  function searchEmp(){
	  	
	  	var empCode=document.getElementById("paraFrm_tdscal_empSearchId").value;
	  	var empName=document.getElementById("paraFrm_tdscal_empSearchName").value;
	  	if(empCode==""){
	  		alert("Please select an Employee for searching");
	  		return false;
	  	}
	  	if(empName==""){
	  		alert("Please select an Employee for searching");
	  		return false;
	  	}
	  	var prvId = document.getElementById('prvSearchId').value;
	  	if(prvId!='')
	  	{
	  		document.getElementById('tokenNo'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('empName'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('grossSal'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('excemp'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('othIncome'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('deductions'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('rebate'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('ptax'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('taxIncome'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('totalTax'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('surCharge'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('edcCess'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('netTax'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('taxPaid'+prvId).style.backgroundColor = '#FFFFFF';
  			document.getElementById('taxPerMonth'+prvId).style.backgroundColor = '#F2F2F2';
	  	}
	  	
	  	var id = '<%=total +1  %>';
	  	
	  	for(var i =0; i< id ; i++){
	  	
	  		var employeeid = document.getElementById("empId"+i).value;
	  		var searchEmpId = document.getElementById("paraFrm_tdscal_empSearchId").value;
	  		if(eval(employeeid) == eval(searchEmpId)){
	  			document.getElementById('tokenNo'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('empName'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('grossSal'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('excemp'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('othIncome'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('deductions'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('rebate'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('ptax'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('taxIncome'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('totalTax'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('surCharge'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('edcCess'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('netTax'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('taxPaid'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('taxPerMonth'+i).style.backgroundColor = '#FF8383';
	  			document.getElementById('prvSearchId').value = i;
	  			document.getElementById('tokenNo'+i).focus();
	  			document.getElementById('taxPerMonth'+i).focus();
	  			return;
	  		}
	  	}
	  	
	  	alert('The selected record not found in the list.');
	  	return false;
	  }
</script>