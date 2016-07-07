<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="TrvlOutstationMedReport" method="post" id="paraFrm"
	validate="true" target="main" theme="simple">
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Outstation Car
					Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><label class="set" name="from.date" id="from.date"
						ondblclick="callShowDiv(this);"><%=label.get("from.date")%>
					</label> <font color="red">*</font> :</td>
					<td><s:textfield name="fromDate" id="paraFrm_fromDate"
						theme="simple" size="10" maxlength="10"
						onkeypress="return numbersWithHiphen();" /> <a
						href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"></td>
					<td><label class="set" name="to.date" id="to.date"
						ondblclick="callShowDiv(this);"><%=label.get("to.date")%>
					</label> <font color="red">*</font> :</td>
					<td><s:textfield name="toDate" id="paraFrm_toDate"
						theme="simple" size="10" maxlength="10"
						onkeypress="return numbersWithHiphen();" /> <a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"></td>
				</tr>
				<tr>
					<td><label class="set" id="report.type" name="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					:</td>
					<td><s:select theme="simple" name="reportType"
						cssStyle="width:152" list="#{'Xls':'Xls','Pdf':'Pdf'}" /></td>
				</tr>

				<tr>
					<td colspan="3" align="center">
					<input type ="button" value=" Generate Report" name="Generate Report" class="token"
						onclick="javascript:return callReport('outstationMediumReport');">
					</td>
				</tr>


			</table>
			</td>
		</tr>

	</table>

</s:form>

<script>
function callReport(action){
	if(!validateFilter()){
		return false;
	}else{
	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='TrvlOutstationMedReport_'+action+'.action';
	document.getElementById('paraFrm').submit();
	}
}

	function validateFilter(){
		if(document.getElementById('paraFrm_fromDate').value==""){
			alert("Please select from date");
			return false;
		}
		if(!validateDate("paraFrm_fromDate", "from.date")){
			return false;
		}
		if(document.getElementById('paraFrm_toDate').value==""){
			alert("Please select to date");
			return false;
		}
		if(!validateDate("paraFrm_toDate", "to.date")){
			return false;
		}
		var datediff = checkDateForApplication('paraFrm_fromDate','paraFrm_toDate','From date', 'To date');
	  	  	if(!datediff){
	  			return false;
	  		}
			return true;
	}
	
	function checkDateForApplication(fromDate,todate,fromlabel,tolabel){
 		var fromDt =document.getElementById(fromDate).value ;
		var toDate = document.getElementById(todate).value ;
 		try{
 			var datediff = dateDifferenceChk(fromDt,toDate,fromDate,fromlabel,tolabel);
  	  		if(!datediff){
  				return false;
  			}
  		}catch(e){alert(e);}
 		return true;
 	}
 	
 	function dateDifferenceChk(fromDate, toDate, fieldName, fromLabName, toLabName){
		var strDate1 = fromDate.split("-");
		var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		var strDate2 = toDate.split("-"); 
		var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		
		if(endtime < starttime) { 
			alert(""+toLabName+" should be greater than or equal to "+fromLabName);
			document.getElementById(fieldName).focus();
			return false;
		}
		return true;
	}
</script>