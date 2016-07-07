<%--Pankaj_Jain--%>
<%--September 4, 2008--%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/arrearsAjax.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/syncscroll.js"></script>
<div id="msgDiv"
	style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 70px; left: 250px;'></div>

<%!int k = 1, c, d;%>
<%
int count = 0;
%>
<s:form action="CashArrears" name="CashArrears" id="paraFrm"
	theme="simple">
	<s:hidden name="divFlag" />
	<s:hidden name="typeFlag" />
	<s:hidden name="payBillFlag" />
	<s:hidden name="brnFlag" />
	<s:hidden name="deptFlag" />
	<s:hidden name="arrCode" />
	<s:hidden name="proccessDate" />
	<s:hidden name="status" />
	<s:hidden name="monthRef" />
	<s:hidden name="empChkFlag" />
	<s:hidden name="savedFlag" />
	<table width="100%" align="center" border="0" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Cash
					Arrears</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<td><input type="button" class="search" value=" Search"
						onclick="javascript:callsF9(500,325,'CashArrears_f9Search.action');" />
					<s:submit cssClass="save" action="CashArrears_saveArrears"
						value=" Save" onclick="return saveValidate();" /> <input
						type="button" class="token" onclick="return callRecalculate();"
						theme="simple" value="Recalculate" /> <input type="button"
						class="lock" onclick="return lockArrears('lock');" theme="simple"
						value=" Lock" /> <input type="button" class="token" onclick=""
						theme="simple" value="Report" /> <s:submit cssClass="reset"
						action="CashArrears_reset" theme="simple" value=" Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" class="formbg" border="0" cellpadding="2"
				cellspacing="2">
				<tr>
					<s:hidden name="divCode" />
					<td width="12%"><label class="set" id="division"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
						color="red"> *</font> :</td>
					<td><s:textfield name="divName" readonly="true" maxlength="50"
						size="30" /> <s:if test="empChkFlag" /> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'CashArrears_f9Div.action');">
					</s:else></td>
				</tr>
				<tr>
					<s:hidden name="brnCode" />
					<td width="12%"><label class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font
						color="red"> *</font> :</td>
					<td><s:textfield name="brnName" readonly="true" maxlength="50"
						size="30" /> <s:if test="empChkFlag" /> <s:else>
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'CashArrears_f9Branch.action');">
					</s:else></td>
				</tr>
				<s:if test="savedFlag" />
				<s:else>
					<tr>
						<td width="12%"></td>
						<td><s:submit cssClass="token"
							action="CashArrears_calArrears" theme="simple" value="Process"
							onclick="return validateProcess();" /></td>
					</tr>
				</s:else>
				<s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
			</table>
			</td>
		</tr>
		<%
				try {
				Object[][] rows = (Object[][]) request.getAttribute("rows");
				if (rows != null && rows.length > 0) {
		%>
		<tr>
		<td>
		<table id="thetable" class="formbg" cellspacing="2" cellpadding="2">
			<tr>
				<td><input class="tokenPay" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="text"
					size="8" value="Employee Id" /></td>
				<td><input class="tokenPay" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="text"
					size="24" value="Employee Name" /></td>
				<td><input class="tokenPay" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="text"
					size="8" value="Arr Days" /></td>
				<s:iterator value="crHDList">
					<td class="formth" align="center" nowrap="nowrap"><input
						class="tokenPay" name="creditName" readonly="readonly"
						style="text-align: center; border: none; margin: 1px" type="text"
						size="6" value='<s:property value="creditName" />' /><s:hidden
						name="creditCode" /></td>
				</s:iterator>
				<td><input class="tokenPay" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="text"
					size="8" value="Total Credit" /></td>
				<td><input class="classCheck" type="checkbox" name="chkEmpAll"
					id="chkEmpAll" onclick="callChkAll();" size="2" /></td>
			</tr>
			<%
					for (int z = 0; z < rows.length; z++) {
					if (rows[z] != null && rows[z].length > 0) {
			%>
			<%
						int i = 1;
						int j = 0;
						int col = 4;
			%>
			<s:if test="empOnHoldFlag">
				<tr>
					<td><input type="hidden" name="eId" id='<%="eId"+k%>'
						value="<%= rows[z][1] %>" /> <input type="hidden"
						name="promotionCode" value="<%= rows[z][0] %>" /><s:hidden
						name="empOnHoldFlag" id='<%="empOnHoldFlag"+k%>' value="Y" /> <input
						name="eToken" id="eToken<%=k %>" readonly="readonly"
						style="background-color: #C0EDFE" type="text" size="8"
						value="<%= rows[z][2] %>" /></td>
					<td><input name="eName" id="eName<%=k %>" readonly="readonly"
						style="background-color: #C0EDFE" type="text" size="24"
						value="<%= rows[z][3] %>" /></td>
					<td><input name="arrDays" id="arrDays<%=k %>"
						readonly="readonly" style="background-color: #C0EDFE" type="text"
						size="8" value="<%= rows[z][rows[z].length-1] %>" /></td>
					<s:iterator value="crHDList">
						<td><input type="text" name="<%=k %>" size="6"
							style="text-align: right; background-color: #C0EDFE"
							id="<%= k+"c"+i %>" value="<%=rows[z][col] %>"
							onkeypress="return numbersOnly();" /></td>
						<%
									i++;
									j++;
									col++;
						%>
					</s:iterator>
					<td><input type="text" name="totCredit" size="8"
						style="text-align: right" id="<%= k+"c"+i %>" readonly="readonly"
						value="<%= rows[z][col]%>" /></td>
					<td><input class="classCheck" type="checkbox" size="2"
						name="chkEmp" id='<%="chkEmp"+k %>'
						value="<%=rows[z][1] %>&<%=rows[z][0] %>&<%=k %>"
						onclick="callChkEmp();" /></td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td><input type="hidden" name="eId" id='<%="eId"+k%>'
						value="<%= rows[z][1] %>" /> <input type="hidden"
						name="promotionCode" value="<%= rows[z][0] %>" /><s:hidden
						name="empOnHoldFlag" id='<%="empOnHoldFlag"+k%>' value="N" /> <input
						name="eToken" id="eToken<%=k %>" readonly="readonly"
						style="background-color: #F2F2F2" type="text" size="8"
						value="<%= rows[z][2] %>"></td>
					<td><input name="eName" id="eName<%=k %>" readonly="readonly"
						style="background-color: #F2F2F2" type="text" size="24"
						value="<%= rows[z][3] %>"></td>
					<td><input name="arrDays" id="arrDays<%=k %>"
						readonly="readonly" style="background-color: #F2F2F2" type="text"
						size="8" value="<%= rows[z][rows[z].length-1] %>"></td>
					<s:iterator value="crHDList">
						<td><input type="text" name="<%=k %>" size="6"
							style="text-align: right" id="<%= k+"c"+i %>"
							value="<%=rows[z][col] %>" onkeyup="sum(<%=k %>)"
							onkeypress="return numbersOnly();" /></td>
						<%
									i++;
									j++;
									col++;
						%>
					</s:iterator>
					<td><input type="text" name="totCredit" size="8"
						style="text-align: right" id="<%= k+"c"+i %>" readonly="readonly"
						value="<%= rows[z][col]%>" /></td>
					<td><input class="classCheck" type="checkbox" size="2"
						name="chkEmp" id='<%="chkEmp"+k %>'
						value="<%=rows[z][1] %>&<%=rows[z][0] %>&<%=k %>"
						onclick="callChkEmp();" /></td>
				</tr>
			</s:else>
			<%
						k++;
						c = i;
						d = j;
			%>
			<%
						} // END OF IF
						} // END OF FOR LOOP
			%>
			<%
						count = k;
						k = 1;
			%>
		</table>
		</td>
		</tr>
		<%
			} // END FOR IF
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</table>
</s:form>

<script type="text/javascript">
	gridScroll();
	
	function gridScroll()
	{
		myST = superTable("thetable", { fixedCols : 3,rightCols:2,viewCols:3});
	}

	function validateProcess()
	{    var division=document.getElementById('division').innerHTML.toLowerCase();
	    var branch=document.getElementById('branch').innerHTML.toLowerCase();
	     var divCode= document.getElementById('paraFrm_divCode').value;
	    var brnCode=document.getElementById('paraFrm_brnCode').value;
		if(divCode=="")
		{
			alert("Please select the "+division);
			return false;
		}
		if(brnCode=="")
		{
			alert("Please select the "+branch);
			return false;
		}
		return true;
	}
	
	function sum(rowNo)
	{
		var i = <%=c%>
		var totalCr = 0;
		for(count=1;count<i;count++)
		{
			if(document.getElementById(rowNo+'c'+count).value == "")
				document.getElementById(rowNo+'c'+count).value = 0;
			totalCr=totalCr+eval(document.getElementById(rowNo+'c'+count).value);
		}		
		document.getElementById(rowNo+'c'+i).value = totalCr;
	}
	
	function callPage(id,totalPage)
	{
		var con;
		if(id=='P'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)-1;
		}
		
		if(id=='N'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)+1;
		}
		
		document.getElementById('hdPage').value=id;
		document.getElementById('paraFrm').action="CashArrears_calArrears.action";
				
		document.getElementById('paraFrm').submit();
	}
	
	function lockArrears(type)
	{
		var rowCount = <%=count %>;
		if(type == "lock")
		{
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				if(rowCount > 1)
				{
					alert("Please save the record first to lock");
					return false;
				}
				else
				{
					alert("Please select the record to lock");
					return false;
				}
		    }
			if(document.getElementById('paraFrm_status').value == "Locked")
			{
				alert("Arrears already locked");
				return false;
			}
		}
		else
		{
			if(rowCount > 1)
			{
				alert("Please save the record first to unlock");
				return false;
			}
			else
			{
				alert("Please select the record to unlock");
				return false;
			}
			if(document.getElementById('paraFrm_status').value == "Pending")
			{
				alert("Arrears already unlocked");
				return false;
			}
		}
		if(confirm("Are you sure want to "+type+" these arrears"))
		{
			document.getElementById('paraFrm').action = "CashArrears_lockArrears.action?type="+type;
			document.getElementById('paraFrm').submit();
		}
		return false;
	}
	
	function saveValidate()
	{
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears is locked so cannot update the record");
			return false;
		}
		var rowCount  = <%= count%>
		if(rowCount <= 1)
		{
			alert("No records found to save. Please either select a record or proccess the arrears");
			return false;
		}
		return true;
	}
	
	function callChkAll()
	{
		try{
		rowCount = <%= count %>
		var chkVal = true;
		if(document.getElementById('chkEmpAll').checked == false)
			chkVal = false;
		for(i = 1; i < rowCount; i++)
			document.getElementById('chkEmp'+i).checked = chkVal;
		}
		catch(e){
			alert(e);
		}
	}
	
	function callChkEmp()
	{
		var flag = true;
		rowCount = <%= count %>
		for(i = 1; i < rowCount; i++)
		{
			if(document.getElementById('chkEmp'+i).checked == false)
			{
				flag = false;
				break;
			}
		}
		document.getElementById('chkEmpAll').checked = flag;
	}
	
	function callOnHold()
	{
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrears record ");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears is locked so cannot on hold the records");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				alert("Please save the record first");
				for(i = 1; i < rowCount; i++)
					document.getElementById('chkEmp'+i).checked = false;
				document.getElementById('chkEmpAll').checked = false;
				return false;
			}
			var k = <%=c%>
			var count=0;
			rowCount = <%=count%>;
			for(i = 1; i < rowCount; i++)
			{
				if(document.getElementById('chkEmp'+i).checked == true)
				{
					document.getElementById('empOnHoldFlag'+i).value = "Y";
					document.getElementById('eToken'+i).style.backgroundColor = '#C0EDFE';
					document.getElementById('eName'+i).style.backgroundColor = '#C0EDFE';
					document.getElementById('month'+i).style.backgroundColor = '#C0EDFE';
					document.getElementById('year'+i).style.backgroundColor = '#C0EDFE';
			//		document.getElementById('salDays'+i).style.backgroundColor = '#C0EDFE';
					document.getElementById('arrDays'+i).style.backgroundColor = '#C0EDFE';
					for(j=1;j<=k+2;j++)
						document.getElementById(i+'c'+j).style.backgroundColor = '#C0EDFE';
					count++;
				}
			}
			if(count==0)
			{
				alert("Please select atleast one record");
				return false;
			}
			retrieveURLArrears('CashArrears_onHoldSave.action?','paraFrm');
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
	}
	
	function callRemoveOnHold()
	{
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrears record ");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot remove on hold");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				alert("Please save the Record first");
				for(i = 1; i < rowCount; i++)
					document.getElementById('chkEmp'+i).checked = false;
				document.getElementById('chkEmpAll').checked = false;
				return false;
			}
			var count=0;
			rowCount = <%=count%>;
			var k = <%=c%>
			for(i = 1; i < rowCount; i++)
			{
				if(document.getElementById('chkEmp'+i).checked == true)
				{
					document.getElementById('empOnHoldFlag'+i).value = "N";
					document.getElementById('eToken'+i).style.backgroundColor = '#F2F2F2';
					document.getElementById('eName'+i).style.backgroundColor = '#F2F2F2';
					document.getElementById('month'+i).style.backgroundColor = '#F2F2F2';
					document.getElementById('year'+i).style.backgroundColor = '#F2F2F2';
			//		document.getElementById('salDays'+i).style.backgroundColor = '#F2F2F2';
					document.getElementById('arrDays'+i).style.backgroundColor = '#F2F2F2';
					for(j=1;j<=k+2;j++)
						document.getElementById(i+'c'+j).style.backgroundColor = '#FFFFFF';
					count++;
				}
			}
			if(count==0)
			{
				alert("Please select atleast one record");
				return false;
			}
			retrieveURLArrears('CashArrears_removeOnHoldSave.action?','paraFrm');
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
	}
	
	function callRecalculate()
	{
	try{
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrear record to recalculate");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot recalculate");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
		var count=0;
		rowCount = <%=count%>;
		var k = <%=c%>
		for(i = 1; i < rowCount; i++)
		{
			if(document.getElementById('chkEmp'+i).checked == true)
				count++;
		}
		if(count==0)
		{
			return false;
		}
		retrieveURLCashArrRecal('CashArrears_recalculate.action?','paraFrm');
		for(i = 1; i < rowCount; i++)
			document.getElementById('chkEmp'+i).checked = false;
		document.getElementById('chkEmpAll').checked = false;
		}catch(e)
		{
			alert(e);
		}
	}
</script>
