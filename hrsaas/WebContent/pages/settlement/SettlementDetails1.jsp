<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>

<%
String note = "";
String note1 = "";
try {
note = (String) request.getAttribute("note");
note1 = (String) request.getAttribute("note1");
System.out.println("String note =" + note);
System.out.println("String note1 =" + note1);
}catch(Exception e){
	
}

%>

<s:form action="SettlmentDetails" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Settlement
					Details </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:hidden name="resignCode" value="%{resignCode}" />
		<s:hidden name="settCode" />
		<s:hidden name="settDtlCode" />
		<s:hidden name="settFlag" />
		<s:hidden name="lockFlag" />
		<s:hidden name="noticeDate" />
		<s:hidden name="joinDate" />
		<s:hidden name="myPage" />
		<s:hidden name="decodedStatus" />
		<s:hidden name="status" />
		

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="82%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="18%" nowrap="nowrap">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
			<table border="0" class="formbg" width="100%">
				<tr>
					<td colspan="4" width="100%">
					<table border="0" width="100%">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td width="12%" colspan="1"><s:textfield name="empToken"
								size="12" value="%{empToken}" readonly="true" /></td>
							<td width="68%" colspan="2"><s:textfield name="empName"
								size="68" value="%{empName}" readonly="true" /></td>
							<s:hidden name="empCode" value="%{empCode}" />
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4" width="100%">
					<table border="0" width="100%">
					<tr>
					
					<td width="20%" colspan="1"><label class="set"
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield size="22"
						name="desgn" value="%{desgn}" readonly="true" /></td>
					
					<td width="20%" colspan="1"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="30%" colspan="1"><s:textfield size="22"
						name="branch" value="%{branch}" readonly="true" /></td>
					
				</tr>
				
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="grade" id="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:
					</td>
					<td width="30%" colspan="1">
						<s:textfield 	name="cadreName" value="%{cadreName}" size="22" readonly="true" />
					</td>
					<td width="20%" colspan="1" class="formtext">
						<label	class="set" name="doj" id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>:</td>
					<td width="30%" colspan="1">
						<s:textfield name="dateOfJoin"	size="20" onkeypress="return numbersWithHiphen();"	
									 theme="simple" value="%{dateOfJoin}" maxlength="10"	readonly="true" />
					</td>
				</tr>
				<tr>
						
					<td width="20%" colspan="1"><label class="set"
						name="resignation.date" id="resignation.date"
						ondblclick="callShowDiv(this);"><%=label.get("resignation.date")%></label><font
						color="red">*</font> :</td>
					<td width="30%" colspan="1"><s:textfield size="22"
						name="resignDate" readonly="true" /></td>
					<td width="20%" colspan="1"><label class="set"
						name="seperation.date" id="seperation.date"
						ondblclick="callShowDiv(this);"><%=label.get("seperation.date")%></label><font
						color="red">*</font> :</td>
					<td width="30%" colspan="1"><s:textfield size="22"
						name="sepDate" readonly="true" /></td>
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set" name="period"
						id="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label><font
						color="red">*</font> :</td>
					<td width="80%" colspan="3"><s:textfield size="5"
						theme="simple" name="noticePeriod"
						onkeypress="return numbersOnly();" readonly="true" /><s:select
						theme="simple" name="noticePeriodStatus" cssStyle="width:67"
						list="#{'D':'Days','M':'Month'}" disabled="true" /><s:hidden
						name="noticeStatus" /> <s:hidden name="noticePeriodStatus" /></td>
				</tr>
				</table>
				</td>
			</table>
			</td>
		</tr>
		
		<%
								try {
								
						%>
					<tr>
						<td width="100%" colspan="4"><font color="red"> <%
									if ((note != null && !note.equals("null") && !note.equals(""))
									|| (note1 != null && !note1.equals("null") && !note1
											.equals(""))) {
							%> <b>Note:</b> <%
							 }
							 %> <%
							 if (note != null && !note.equals("null") && !note.equals("")) {
							 %> <%=note%><br />
						<%
										}
										if (note1 != null && !note1.equals("null") && !note1.equals("")) {
								%> <%=note1%> <%
							 }
							 %> </font></td>
					</tr>
					<%
								} catch (Exception e) {
								e.printStackTrace();
							}
						%>
		
		<s:if test="finalFlag"></s:if><s:else>
		<tr class="td_bottom_border">
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap" width="90%" colspan="3" align="right"><s:submit
						cssClass="add" value="  Add Row"
						action="SettlmentDetails_addRow" id="ctrlHide"/></td>
					<td nowrap="nowrap" width="10%" colspan="1">&nbsp;<input
						type="button" class="delete" value="  Remove"
						onclick="return deleteSkill();" id="ctrlHide"/></td>
				</tr>
			</table>
			</td>
		</tr>
		</s:else>

		<tr class="td_bottom_border">
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" id="tblRef">

				<tr>
					<td width="5%" class="formth" height="15" width="5%"><label class="set"
						name="sr.no" id="sr.no1" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
					<td width="18%" class="formth" height="15"><label class="set" name="mnth"
						id="mnth" ondblclick="callShowDiv(this);"><%=label.get("mnth")%></label></td>
					<td width="10%" class="formth" height="15"><label class="set" name="yr"
						id="yr" ondblclick="callShowDiv(this);"><%=label.get("yr")%></label></td>
					<td width="15%" class="formth" height="15"><label class="set"
						name="max.days" id="max.days" ondblclick="callShowDiv(this);"><%=label.get("max.days")%></label></td>
					<td width="22%" class="formth" height="15"><label class="set" name="days"
						id="days1" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></td>
					<td width="30%" class="formth" height="15"><label class="set"
						name="select" id="select" ondblclick="callShowDiv(this);"><%=label.get("select")%></label></td>
					
					
					<td width="8%" valign="top" class="formth" align="center" >
					<label class="set" name="recal" id="recal"
						ondblclick="callShowDiv(this);"> <%=label.get("recal")%></label><br>
					<input class="classcheck" style="text-align: center; margin: 1px"
						type="checkbox" size="2" name="chkRecalAll" id="chkRecalAll"
						onclick="return callForRecalAll();"></td>
						
						
					<td width="7%" valign="top" class="formth" align="center" >
					<label class="set" name="deletes" id="deletes"
						ondblclick="callShowDiv(this);"> <%=label.get("deletes")%></label><br>
					<input class="classcheck" style="text-align: center; margin: 1px"
						type="checkbox" size="2" name="chkRemoveAll" id="chkRemoveAll"
						onclick="return callForRemoveAll();"></td>
				</tr>
				
				<s:if test="noDtlData">
					<tr>
						<td width="100%" colspan="7" align="center"><font
							color="red">No Data To Display</font></td>
					</tr>
				</s:if>

				<%
					int i = 1;
					int j = 1;
				%>

				<s:hidden name="onholdFlag" />
				<s:iterator value="typeList">
					<tr>
						<td width="5%" class="sortableTD" height="15"><%=i%>
						
						</td>
						<s:if test="onholdFlag"></s:if>
						<s:else>
							<td width="18%" class="sortableTD" height="15" ><s:hidden name="payMonth" />
							 
							 <input type="hidden" name="hdPayByMnth" id="payByMnth<%=j%>" 
							 value="<s:property value="payByMnth" />"/>
							
							
							<s:select name="payByMnth" cssStyle="width:95" id='<%="combo"+j%>' 
								list="#{'':'--Select--','1':'January','2':'February','3':'March','4':'April','5':'May'
										,'6':'June','7':'July','8':'August','9':'September','10':'October'
										,'11':'November','12':'December'}"  
								onchange="onMonthSelect(this);" />
							
							</td>
							
							<td width="10%" class="sortableTD" height="15" >
							<input type="text"
								name="payByYr" size="5" value="<s:property value="payByYr" />"
								maxlength="4" onkeypress="return numbersOnly();" id="payByYr<%=j%>"/> <input
								type="hidden" name="hiddenYr"
								value="<s:property value="payByYr" />" />
								
								</td>
								
							<td width="15%" align="center" class="sortableTD" height="15">
							<s:select name="maxDays" cssStyle="width:150" theme="simple" id='<%="maxCombo"+j%>' 
								list="#{'':'--Select--','30':'30 days of month','31':'Actual days of month'}"
								value="%{maxDays}" onchange="onDaysSelect(this);"/> <!--<s:property value="maxDays" />--> <input type="hidden"
								name="hdMaxDays" id="maxDays<%=j%>" value="<s:property value="maxDays" />"/>
								
								</td>
								
							<td width="22%" align="center" class="sortableTD" height="15">
							<input type="hidden" name="hdDays"
								value='<s:property value="calPayByDays"/>' id="hdDays<%=j%>" />
							<input type="text" size="5" name="calPayByDays"
								onkeypress="return numbersWithDot();"
								value="<s:property value="calPayByDays"/>"
								style="text-align: right;" id="calPayByDays<%=j%>"
								onkeyup="daysValidate(this,'<%=j%>');" />

							</td>
							
							<td width="30%" align="center" class="sortableTD" height="15">
							<s:select name="calPayByType" cssStyle="width:175" theme="simple"
								list="#{'':'--Select--','EM':'To be paid by Employee','CO':'To be paid by Company'}"
								value="%{calPayByType}" id='<%="typeCombo"+j%>' 
								onchange="calTypeFun(this);" /><input type="hidden" name="hdType"
								value='<s:property value="calPayByType"/>' id="hdType<%=j%>" />
								
								</td>

							<td width="8%" class="sortableTD" align="center" height="15" ><input type="hidden"
								name="<%="hrecal"+j%>" id="hrecal<%=j%>" value="N"/>&nbsp;
								<input
								type="checkbox" class="classcheck" value="N"
								style="text-align: center; margin: 1px"
								name=<%="confChkRecal"+j%> id="confChkRecal<%=j%>"
								onclick="callForRecal('<%=j%>')" />
								
								</td>
								
							<td width="7%" class="sortableTD" align="center" height="15" ><input type="hidden"
								name="<%="hdeleteSkill"+j%>" id="hdeleteSkill<%=j%>" />&nbsp;<input
								type="checkbox" class="classcheck" value="N"
								style="text-align: center; margin: 1px"
								name=<%="confChkSkill"+j%> id="confChkSkill<%=j%>"
								onclick="callForSkill('<%=j%>')" />
								
								</td>

						</s:else>
						<s:if test="onholdFlag">
							<td width="18%" class="sortableTD"><s:hidden name="calMonth" />
								<input type="hidden" name="hdOhMnth" id="ohMnth<%=j%>" 
							 value="<s:property value="ohMonth" />"/>
								
								<s:select name="ohMonth" cssStyle="width:95" 
								list="#{'1':'January','2':'February','3':'March','4':'April','5':'May'
										,'6':'June','7':'July','8':'August','9':'September','10':'October'
										,'11':'November','12':'December'}"  disabled="true"/>
								
								</td>
							
							<td width="10%" class="sortableTD"><input type="text"
								name="ohYear" size="5" value="<s:property value="ohYear" />"
								disabled="true" /> 
								<input type="hidden"
								name="hdOhYear" size="7" value="<s:property value="ohYear" />"
								 /> 
								
								</td>
							
							<td width="15%" align="center" class="sortableTD" height="15">
							<s:select name="ohMaxDays" cssStyle="width:150" theme="simple"
								list="#{'31':'Actual days of month','30':'30 days of month'}"
								value="%{ohMaxDays}" disabled="true" /> 
								<input type="hidden"
								name="hdOhMaxDays" id="ohMaxDays<%=j%>" value="<s:property value="ohMaxDays" />"/>
								
							</td>
							
							<td width="22%" align="center" class="sortableTD" height="15">
							<input type="hidden" name="hdcalDays"
								value='<s:property value="calDays"/>' id="hdcalDays<%=j%>" />

							<input type="text" name="calDays" size="5"
								onkeypress="return numbersWithDot();"
								value="<s:property value="calDays"/>" style="text-align: right;"
								disabled="true"
								onkeyup="daysValidate('<s:property value="ohMonth" />','<s:property 
											value="ohYear" />',this,'<%=j%>');" />
							<s:hidden name="calDays" /></td>
							<td width="30%" align="center" class="sortableTD" height="15">
							<s:select name="calType" cssStyle="width:175"
								list="#{'PD':'Paid in salary','OH':'OnHold',
						'EM':'To be paid by Employee','CO':'To be paid by Company'}"
								value="%{calType}" id="calFlag" onchange="calTypeFun();"
								disabled="true" /> 
								
								<input type="hidden" name="hdcalType"
								value='<s:property value="calType"/>' id="hdcalType<%=j%>" />
								
								<s:hidden name="calType" /></td>

							<td width="8%" class="sortableTD">
							<input type="hidden"
								name="<%="hrecal"+j%>" id="hrecal<%=j%>" value="N"/>&nbsp;
								<input type="hidden"
								name="<%="confChkRecal"+j%>" id="confChkRecal<%=j%>" value="N"/>
							
							</td>
							
							<td width="7%" class="sortableTD">
							<input type="hidden"
								name="<%="hdeleteSkill"+j%>" id="hdeleteSkill<%=j%>" value="N"/>&nbsp;
								<input type="hidden"
								name="<%="confChkSkill"+j%>" id="confChkSkill<%=j%>" value="N"/>
							
							</td>
						</s:if>

					</tr>
					<%
						i++;
						j++;
					%>
				</s:iterator>
				<s:hidden name="listCode" />
				<s:hidden name="payListCode" />

				<%
					i = 1;
					j = i;
				%>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="82%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="18%" nowrap="nowrap">
					</td>
				</tr>

			</table>
			</td>
		</tr>

	</table>
</s:form>

<script>
function saveandnextFun(){
	if(!formValidate()){
    	return false;
    }
	document.getElementById("paraFrm").action ='SettlmentDetails_saveAndNextFirst.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function saveandpreviousFun(){
	if(!formValidate()){
    	return false;
    }
	document.getElementById("paraFrm").action ='SettlmentDetails_saveAndPreviousFirst.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function previousFun(){
	document.getElementById("paraFrm").action ='SettlmentDetails_previousFirst.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function nextFun(){
	document.getElementById("paraFrm").action ='SettlmentDetails_nextFirst.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function saveFun(){
	try{
	var sCode=document.getElementById('paraFrm_resignCode').value;
	var lockFlag = document.getElementById('paraFrm_lockFlag').value;
	if(sCode !="" || trim(sCode.toString())!="null"){
		if(lockFlag=='Y'){  
		  	alert('Settlement has been already locked so you can not update !');
		  	return false;
	  	}
	}
	
	if(!formValidate()){
    	return false;
    }
	
	}catch(e){
		alert(e);
	}
	document.getElementById("paraFrm").action='SettlmentDetails_saveFirst.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function formValidate(){
	var payListCode = document.getElementById('paraFrm_payListCode').value;
	//alert(payListCode);
	var listCode = document.getElementById('paraFrm_listCode').value;
	var tbl = document.getElementById('tblRef');
	var rowLen = tbl.rows.length;
	
	if(rowLen=="2" && payListCode=="0" && listCode=="0"){
		alert("Please add atleast one row.");
		return false;
	}
	var monthLabel = document.getElementById('mnth').innerHTML.toLowerCase();
	var yrLabel = document.getElementById('yr').innerHTML.toLowerCase();
	var maxDaysLabel = document.getElementById('max.days').innerHTML.toLowerCase();
	var daysLabel = document.getElementById('days1').innerHTML.toLowerCase();
	var sepDate = document.getElementById('paraFrm_sepDate').value;
	var noticeDate = document.getElementById('paraFrm_noticeDate').value;
	var joinDate = document.getElementById('paraFrm_joinDate').value;
	try{
	var strDate1 = sepDate.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]);
	var sepMonth =  strDate1[1];
	var sepYear = strDate1[2];

	var strDate2 = noticeDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]);
	var noticeMonth =  strDate2[1];
	var noticeYear = strDate2[2]; 
	var strDate3 = joinDate.split("-"); 
	var joinMonth =  strDate3[1];
	var joinYear = strDate3[2];
	}catch(e){
		//alert('ggg'+e);
	}
	
    for(var a=1;a<=payListCode;a++){
    	try{
    	var month = document.getElementById('payByMnth'+a).value;
    	}catch(e){
    		break;
    	}
    	var year = document.getElementById('payByYr'+a).value;
    	var type = document.getElementById('hdType'+a).value;
    	var days = document.getElementById('calPayByDays'+a).value;
    	var maxDays = document.getElementById('maxDays'+a).value;
   		if(month=="" || month=="0"){
   			alert('Please select '+monthLabel);
   			document.getElementById('payByYr'+a).focus();
   			return false;
   		}
   		if(year==""){
   			alert('Please enter '+yrLabel);
   			document.getElementById('payByYr'+a).focus();
   			return false;
   		}
   		if(year.length<4){
   			alert(yrLabel+" should have atleast 4 digits");
   			document.getElementById('payByYr'+a).focus();
   			return false;
   		}
   		if(maxDays==""){
   			alert('Please select '+maxDaysLabel);
   			document.getElementById('payByYr'+a).focus();
   			return false;
   		}
   		/*if(days=="0"){
   			alert(daysLabel+' should be greater than 0');
   			document.getElementById('hdDays'+a).focus();
   			return false;
   		}*/
   		if(type==""){
   			alert('Please select type');
   			document.getElementById('hdType'+a).focus();
   			return false;
   		}
		if(endtime <= starttime) 
		{ 
			//SEP GREATER THAN NOTICE
			if(month<10)
				month="0"+month;
			//if(month>strDate1[1]){
			//	alert('Month cannot be greater than separation month');
			//	document.getElementById('payByMnth'+a).focus();
			//	return false;
			//}
			//if(year>strDate1[2]){
			//	alert('Year cannot be greater than separation year');
			//	document.getElementById('payByYr'+a).focus();
			//	return false;
			//}
		}else{
			//SEP LESS THAN NOTICE
			if(month<10)
				month="0"+month;
			if((year+month)>(noticeYear+noticeMonth)){
				alert('Month and year cannot be greater than notice date');
				document.getElementById('payByYr'+a).focus();
				return false;
			}
		}
		/*if(month<joinMonth){
			alert('Month cannot be less than joining month '+strDate3[1]-1);
			document.getElementById('payByMnth'+a).focus();
			return false;
		}
		if(year<joinYear){
			alert('Year cannot be less than joining year: '+strDate3[2]);
			document.getElementById('payByYr'+a).focus();
			return false;
		}*/
	}
	
	
	/*for(var a=1;a<=payListCode;a++){
		try{
    	var month = document.getElementById('payByMnth'+a).value;
    	alert("month : "+month);
    	}catch(e){
    		break;
    	}
    	var year = document.getElementById('payByYr'+a).value;
    	var type = document.getElementById('hdType'+a).value;	
		for(var j=a+1;j<=payListCode;j++){
			var nextMonth = trim(document.getElementById('payByMnth'+j).value);
			alert('next month..'+nextMonth);
			var nextYear = trim(document.getElementById('payByYr'+j).value);
			alert('next year..'+nextYear);
			var nextType = trim(document.getElementById('hdType'+j).value);
			alert('next type..'+nextType);
			
			if(month==nextMonth && year==nextYear && type==nextType){
				alert("Two rows with same month, same year and same type cannot be saved");
				document.getElementById('payByYr'+j).focus();
				return false;
			}
		}
	}*/
	
	/*for(var a=1;a<=payListCode;a++){
		month = document.getElementById('payByMnth'+a).value;
    	year = document.getElementById('payByYr'+a).value;
		for(var j=a+1;j<=payListCode;j++){
			alert("month : "+month);
			nextMonth = trim(document.getElementById('payByMnth'+j).value);
			alert('next month..'+nextMonth);
			nextYear = trim(document.getElementById('payByYr'+j).value);
			alert('next year..'+nextYear);
			if(document.getElementById('payByMnth'+j).value==document.getElementById('payByMnth'+x).value){
				if(document.getElementById('payByYr'+j).value==document.getElementById('payByYr'+x).value){
					alert("Same month cannot be saved more than two times for same year.");
					document.getElementById('payByYr'+x).focus();
					return false;
				}
			}
		}
	} */
	
	return true;
}

/*function unlockFun(){
	 var lockFlag = document.getElementById('paraFrm_lockFlag').value;
	 if(lockFlag=='Y')
	 {
	  	var conf = confirm('Do you really want to unlock the settlement ?');
		if(conf){
			document.getElementById("paraFrm").action ='SettlmentDetails_unLock.action';
			document.getElementById("paraFrm").submit();
			document.getElementById('paraFrm').target = "main";
		}
		else
			return false;
	}else{
		alert('Please Lock the Settlement First!');
		return false;
	}
  	return true;
}*/

	function unlockFun() {
  		doAuthorisation('4', 'Settlement', 'U');
	}
	
	function doUnlock() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'SettlmentDetails_unLock.action';
		document.getElementById('paraFrm').submit();
		//document.getElementById('paraFrm').target = "main";
	}

function deleteFun()
{
	if(document.getElementById('paraFrm_settCode').value==""){
		alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
		return false;
	}
	
	var con=confirm('Do you really want to delete the record?')
	if(con){
		var del="SettlmentDetails_delete.action";
		document.getElementById('paraFrm').action=del;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	} else{
	     return false;
	}
}



function daysValidate(enteredDays,hdDays){
	//alert(hdDays);
	var month = document.getElementById('combo'+hdDays).value;
	var year = document.getElementById('payByYr'+hdDays).value;
	//alert(month);
	//alert(year);
	var days = getDaysInMonth(month,year);
	var actual = enteredDays.value;
	
	//alert(hdDays);
	try{
	var hiddenDays=document.getElementById('hdDays'+hdDays).value;
	//alert(hiddenDays);
	}catch(e1){
		alert(e1);
	}
	try{
	if(actual > days){
		alert(document.getElementById('days1').innerHTML.toLowerCase()+" cannot be greater than "+days);
		enteredDays.value=hiddenDays;
		}
	if(actual ==""){
		enteredDays.value=0;
	}
	if(actual.indexOf(".")!=actual.lastIndexOf(".")){
		//more than one dot
		//alert("Only one dot is allowed");
		enteredDays.value=actual.substring(0,actual.length-1);
	}
	}catch(e){
		alert(e);
	}
	//document.getElementById('hdDays'+hdDays).value=enteredDays.value;
}

function onMonthSelect(obj){
	try{
	var comboId=obj.id;
	comboId=comboId.replace("combo","");
	//alert('payByMnth'+comboId);
	var year=document.getElementById('payByYr'+comboId).value;
	if(year==""){
		alert('Please enter year');
		document.getElementById('combo'+comboId).value="";
		document.getElementById('payByYr'+comboId).focus();
		return false;
	}
	if(year.length<4){
		alert("Year should have atleast 4 digits");
		document.getElementById('payByYr'+comboId).focus();
		return false;
	}
	var month=document.getElementById('combo'+comboId).value;
	document.getElementById('payByMnth'+comboId).value=document.getElementById('combo'+comboId).value
	
	//alert(month);
	//alert(year);
	var calDays=getDaysInMonth(month,year);
	//alert('calDays..'+calDays);
	document.getElementById('hdDays'+comboId).value=calDays;
	document.getElementById('calPayByDays'+comboId).value=calDays;
	}catch(e){
		alert(e);
	}
}

function onDaysSelect(obj){
	try{
	var comboId=obj.id;
	comboId=comboId.replace("maxCombo","");
	document.getElementById('maxDays'+comboId).value=document.getElementById('maxCombo'+comboId).value;
	}catch(e){
		alert(e);
	}
}

function calTypeFun(obj){
	try{
	var comboId=obj.id;
	comboId=comboId.replace("typeCombo","");
	document.getElementById('hdType'+comboId).value=document.getElementById('typeCombo'+comboId).value;
	}catch(e){
		alert(e);
	}
}
             

// Returns the number of days in the month in a given year (January=0)
function getDaysInMonth(month,year){
	 if(month=='1'||month=='3'||month=='5'||month=='7'||
	 		month=='8'||month=='10'||month=='12'){
	 		return 31;
	 }
	 if(month=='4'||month=='6'||month=='9'||month=='11'){
	 		return 30;
	 }
	    if ((month=='2')&&(year%4==0)&&((year%100!=0)||(year%400==0))){
	    		return 29;
	 }else{
	     	return 28;
	 }
	 
}

function getIntMonth(month){

	 	if(month=="Jan" || month=="January")
	 		return 1;
	 	if(month=="Feb" || month=="February")
	 		return 2;
	 	if(month=="Mar" || month=="March")
	 		return 3;
	 	if(month=="Apr" || month=="April")
	 		return 4;
	 	if(month=="May" || month=="May")
	 		return 5;
	 	if(month=="Jun" || month=="June")
	 		return 6;
	 	if(month=="Jul" || month=="July")
	 		return 7;
	 	if(month=="Aug" || month=="August")
	 		return 8;
	 	if(month=="Sep" || month=="September")
	 		return 9;
	 	if(month=="Oct" || month=="October")
	 		return 10;
	 	if(month=="Nov" || month=="November")
	 		return 11;
	 	if(month=="Dec" || month=="December")
	 		return 12;
	 }

function callForRemoveAll(){
	var tbl = document.getElementById('tblRef');
	var rowLen = tbl.rows.length;
	//alert(rowLen);
	try{
	if (document.getElementById("chkRemoveAll").checked == true){
		for (var idx = 1; idx < rowLen; idx++) {
			//alert(idx);
			//alert("delete code  :"+document.getElementById('hdeleteSkill'+idx).value)
			if(document.getElementById('hdeleteSkill'+idx).value!="N"){
				document.getElementById("confChkSkill"+idx).checked = true;
				document.getElementById('hdeleteSkill'+idx).value="Y";
			}
		}

 	}else{
		for (var idx = 1; idx < rowLen; idx++) {
			document.getElementById("confChkSkill"+idx).checked = false;
			document.getElementById('hdeleteSkill'+idx).value="";
     	}
  	}	
  	}catch(e){
  	//alert(e);
  	} 
}

function callForSkill(id){
	if(document.getElementById('confChkSkill'+id).checked == true){	  
		document.getElementById('hdeleteSkill'+id).value="Y";
	}else{
		document.getElementById('hdeleteSkill'+id).value="";
	}
}

function deleteSkill(){
	try{
	if(chkSkill()){
		var con=confirm('Do you want to remove the records?');
		if(con){
			document.getElementById('paraFrm').action="SettlmentDetails_deleteRows.action";
			document.getElementById('paraFrm').submit();
		} else{
			return true;
		}
	}else {
		alert('Please Select Atleast one Record To Remove');
		return false;
	}
	}catch(e){
	//alert(e);
	}
}

function chkSkill(){
	var tbl = document.getElementById('tblRef');
	var rowLen = tbl.rows.length;
	//alert(rowLen);
	flag=false;
   try{
    for(var a=1;a<rowLen;a++){
		//alert(a);	
		try{
		if(document.getElementById('confChkSkill'+a).checked == true){
			//alert('checked...'+a);	
		 	  flag=true;
		}	
		}catch(e){
		//alert(e);
	}   
	}
	}catch(e){
		//alert(e);
	}
	return flag;
}

function callForRecalAll(){
	var tbl = document.getElementById('tblRef');
	var rowLen = tbl.rows.length;
	//alert(rowLen);
	try{
	if (document.getElementById("chkRecalAll").checked == true){
		for (var idx = 1; idx < rowLen; idx++) {
			//alert(idx);
			//alert("code  :"+document.getElementById('hrecal'+idx).value)
			if(document.getElementById('hrecal'+idx).value=="N"){
				document.getElementById("confChkRecal"+idx).checked = true;
				document.getElementById('hrecal'+idx).value="Y";
			}
		}

 	}else{
		for (var idx = 1; idx < rowLen; idx++) {
			document.getElementById("confChkRecal"+idx).checked = false;
			document.getElementById('hrecal'+idx).value="N";
     	}
  	}	
  	}catch(e){
  	//alert(e);
  	} 
}

function callForRecal(id){
	if(document.getElementById('confChkRecal'+id).checked == true){	  
		document.getElementById('hrecal'+id).value="Y";
	}else{
		document.getElementById('hrecal'+id).value="N";
	}
}

function reportFun(){
	var settCode=document.getElementById('paraFrm_settCode').value;
	if(settCode=="" || trim(settCode.toString())=="null"){
		alert('Please Select a record to generate report');
		return false;
	}
	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action="SettlmentDetails_report.action";
  	document.getElementById('paraFrm').submit();  
  	document.getElementById('paraFrm').target="main"; 
}


</script>