<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PrejoiningActivities" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="bgStatus" />
	<s:hidden name="hiddencode" />
	<s:hidden name="DupcheckListType" />
	<s:hidden name="DupcheckListresponce" />
	<s:hidden name="DupbgCheckType" />
	<s:hidden name="rowId" />
	<s:hidden name="buttonpanelcode" />
	<!--<s:hidden name="Appstatus" />
		-->
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
					<td width="93%" class="txt"><strong class="text_head">Pre
					Joining Activities</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/common/css/default/images/help.gif" width="16"
						height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
 
		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					
						
					<td width="78%" colspan="2"><strong class="formhead">Pre Joining Activity Details</strong></td>	
				</tr>

				<tr>
					<td width="100%" colspan="3"><s:hidden name="prejoinCode" />
					<table width="100%" border="0" cellpadding="0" cellspacing="2">
						<!--Table 1-->
						<tr>
							<td width="20%"><label class="set" name="cand.name"
								id="prej.cname" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> :<font color="red">*</font></td>
							<td width="25%"><s:hidden name="candidateCode" /> <s:textfield
								name="candidateName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" height="16" align="absmiddle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'PrejoiningActivities_f9Candidate.action')"></td>
								
							<td width="20%"><label class="set" name="position"
								id="prej.postin" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:</td>
							<td width="25%"><s:textfield name="position" size="25"
								readonly="true" /></td>
									
							
						</tr>

						<tr>
							<td width="20%"><label class="set" name="reqs.code"
								id="prej.reqco" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> :<font color="red">*</font></td>
							<td width="25%"><s:hidden name="reqCode" /><s:textfield
								name="reqName" size="25" readonly="true" /> <!--  <img
										src="../pages/images/search2.gif" height="16" align="absmiddle"
										width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'PrejoiningActivities_f9Requistion.action')"
										>
										--></td>
							
							<td width="25%"><label class="set" name="prej.repto"
								id="prej.repto" ondblclick="callShowDiv(this);"><%=label.get("prej.repto")%></label> :<font color="red">*</font></td>
							<td width="25%" nowrap="nowrap"><s:hidden name="reportingTo" /> <s:textfield
								name="reportingName" size="25" readonly="true" /> <img
								src="../pages/images/search2.gif" height="16" align="absmiddle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'PrejoiningActivities_f9ReportingTo.action')"></td>
								

							
						</tr>


						<tr>
							<td width="20%"><label class="set" name="division"
								id="prej.divso" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td width="25%"><s:textfield name="division" size="25"
								readonly="true" /></td>

							<td width="25%"><label class="set" name="offer.date"
								id="prej.offdt" ondblclick="callShowDiv(this);"><%=label.get("offer.date")%> </label>
							:<font color="red">*</font></td>
							<td width="25%" nowrap="nowrap"><s:textfield size="25" name="offerDate"
								onblur="return validateDate1('paraFrm_offerDate','Date');"
								onkeypress="return numbersWithHiphen();" maxlength="10"  onfocus="clearText('paraFrm_offerDate','dd-mm-yyyy')"  onblur="setText('paraFrm_offerDate','dd-mm-yyyy');" readonly="true"/> 
								</td>
							
								

							
						</tr>

						<tr>
							<td width="20%"><label class="set" name="branch"
								id="prej.branc" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td width="25%"><s:textfield name="branch" size="25"
								readonly="true" /></td>
								
							<td width="25%"><label class="set" name="joining.date"
								id="prej.joidt" ondblclick="callShowDiv(this);"><%=label.get("joining.date")%></label> :<font color="red">*</font></td>
							<td width="25%" nowrap="nowrap"><s:textfield size="25" name="joinDate"
								onblur="return validateDate1('paraFrm_joinDate','Date');"
								onkeypress="return numbersWithHiphen();" maxlength="10" onfocus="clearText('paraFrm_joinDate','dd-mm-yyyy')"  onblur="setText('paraFrm_joinDate','dd-mm-yyyy');" readonly="true"/> 
								</td>
								
							
						</tr>
						<tr>
							<td width="20%"><label class="set" name="department"
								id="prej.deprt" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="25%"><s:textfield name="department" size="25"
								readonly="true" /></td>
								
								<td width="25%"><label class="set" name="prej.appst"
								id="prej.appst" ondblclick="callShowDiv(this);"><%=label.get("prej.appst")%></label>:</td>
							<td width="25%"><s:textfield name="Appstatus" size="25"
								readonly="true" /></td>
								
							
						</tr>

						<tr>
							<td width="25%" nowrap="nowrap"><label class="set" name="prej.seact"
								id="prej.seact" ondblclick="callShowDiv(this);"><%=label.get("prej.seact")%></label> :<font color="red">*</font></td>
							
							<!--
							<td width="25%" nowrap="nowrap"><s:select theme="simple"
								name="checkListType" onchange="return callChange();"
								headerKey="S" headerValue="   --Select--      "
								list="#{'J':'Joining CheckList','M':'Medical CheckList','T':'Transfer CheckList','I':'Interview CheckList','B':'Background Verification CheckList','P':'Prejoining CheckList'}" />
							</td>
							-->
							
							<td width="25%" nowrap="nowrap"><s:select theme="simple"
								name="checkListType" onchange="return callChange();"
								headerKey="S" headerValue="   --Select--      "
								list="#{'P':'Prejoining CheckList'}" />
							</td>
							<td width="25%">&nbsp;</td>
							<td width="25%"><s:submit cssClass="token" value="Show List"
								action="PrejoiningActivities_showCheckList"
								onclick="return checkList();" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<!--Table 6-->
				<tr>
					<td width="10%" colspan="3" class="formtxt"><strong
						class="formhead"> <%
 	String status = "";
 	try {
 		status = (String) request.getAttribute("listname");
 %> <%
 			if (status.equalsIgnoreCase("J")) {
 			out.println("Joining");
 		}
 		if (status.equalsIgnoreCase("M")) {
 			out.println("Medical");
 		}
 		if (status.equalsIgnoreCase("T")) {
 			out.println("Transfer");
 		}
 		if (status.equalsIgnoreCase("I")) {
 			out.println("Interview");
 		}
 		if (status.equalsIgnoreCase("B")) {
 			out.println("Background Verification");
 		}
 		if (status.equalsIgnoreCase("P")) {
 			out.println("Prejoining");
 		}
 %> <%
 	} catch (Exception e) {

 	}
 %> Check List Details:</strong> <s:hidden name="Chkstatus1" value='<%=status %>' />
					<s:hidden name="Chkstatus" /></td>
				</tr>



				<tr>
					<td width="8%" valign="top" class="formth" nowrap="nowrap"><!--Sr.No.-->
					<b> <label class="set" name="serial.no" id="serial.no"
						ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> </b>
					</td>
					<td width="20%" valign="top" class="formth" nowrap="nowrap"><!-- Activity Details -->

					<b> <label class="set" name="Adetails" id="Adetails"
						ondblclick="callShowDiv(this);"><%=label.get("Adetails")%></label> </b>
					</td>
					<td width="25%" valign="top" class="formth"><!-- Target Date  -->

					<label class="set" name="Tdate" id="Tdate"
						ondblclick="callShowDiv(this);"><%=label.get("Tdate")%></label></td>
					<td width="30%" valign="top" class="formth"><!-- Responsible Person -->

					<b> <label class="set" name="Respper" id="Respper"
						ondblclick="callShowDiv(this);"><%=label.get("Respper")%></label> </b>
					</td>
					<td width="10%" valign="top" class="formth"><!-- Activity Required -->

					<b> <label class="set" name="Areq" id="Areq"
						ondblclick="callShowDiv(this);"><%=label.get("Areq")%></label></b></td>
					<td width="15%" valign="top" class="formth"><!-- Activity Status -->

					<b> <label class="set" name="Astatus" id="Astatus"
						ondblclick="callShowDiv(this);"> <%=label.get("Astatus")%></label> </b>
					</td>
				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="7" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
				<%!int p = 1, zz = 0;%>
				<%
				int q = 1;
				%>

				<tr>
					<%
					Object[][] funcObj = (Object[][]) request.getAttribute("funcObj");
					%>

					<s:iterator value="prejoiningList">
						<s:hidden name="RespCode" />
						<s:hidden name="RespName" />

						<tr class="sortabletr">

							<td class="sortabletd" width="8%" nowrap="nowrap"><%=p%> <s:hidden
								name="prejoinListCode" /> <s:hidden name="checkListCode" />&nbsp;</td>

							<td class="sortabletd" width="20%" align="left"><s:property
								value="prejoinListName" /> &nbsp;</td>
							<td class="sortabletd" width="20%" align="center"><input type="text"
								name="prejoinTargetDate" id="prejoinTargetDate<%=zz%>"
								value='<s:property value="prejoinTargetDate"/>' size="10"
								onblur="return validateDate('prejoinTargetDate<%=zz%>','Tdate');"
								onkeypress="return numbersWithHiphen();" maxlength="10">
								<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:NewCal('<%="prejoinTargetDate"+zz%>','DDMMYYYY');" />
							&nbsp;</td>
							<td class="sortabletd" width="30%"><s:textfield
								name="<%="ResponsiblePersonName"+zz %>"
								value="<%=String.valueOf(funcObj[zz][0]).equals("null") ? "":String.valueOf(funcObj[zz][0])%>"
								readonly="true" /> <s:hidden
								name="<%="ResponsiblePersonCode"+zz %>"
								value="<%=String.valueOf(funcObj[zz][1]).equals("null") ? "":String.valueOf(funcObj[zz][1])%>" />
							<img src="../pages/images/recruitment/search2.gif" height="17"
								width="17"
								onclick="rowcallsF9('<s:property value="<%=""+zz%>"/>');">
							&nbsp;</td>

							<td class="sortabletd" width="10%"><select
								name="activityRequired" id="activityRequired<%=zz%>">
								<option value="Y" <%=funcObj[zz][2].equals("Y")?"selected":"" %>>Yes</option>
								<option value="N" <%=funcObj[zz][2].equals("N")?"selected":"" %>>No</option>
							</select> &nbsp;</td>
							<td class="sortabletd" width="15%"><s:select
								list=" #{'O':'Open','C':'Close'}" name="activityStatus" />
							&nbsp;</td>

						</tr>
						<%
							p++;
							zz++;
						%>
					</s:iterator>
					<%
						q = p;
						p = 1;
						zz = 0;
					%>
				
			</table>

			<s:hidden name="prejoiningListLength" value="%{prejoiningListLength}" />
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>


      function checkList()
       {
       var listtype=document.getElementById('paraFrm_checkListType').value;
       if(document.getElementById('paraFrm_checkListType').value!="S")
		{
	    	document.getElementById('paraFrm_Chkstatus').value="0"; 
		 return true;
		}
		else
		{ 
		alert("Please "+document.getElementById('prej.seact').innerHTML.toLowerCase());
		  return false;
		}
		
       }
var fieldName = ["paraFrm_candidateCode","paraFrm_reqName","paraFrm_reportingTo"];//,"paraFrm_offerDate","paraFrm_joinDate"];
//var lableName = ["Candidate Name","Requisition Code","Reporting To","Offer Date","Joining Date"];


var lableName = ['prej.cname','prej.reqco','prej.repto'];//,'prej.offdt','prej.joidt'];

var badflag = ["select","select","select"];//,"enter/select","enter/select"];

function listValidation()
{
 var flag=document.getElementById('paraFrm_prejoiningListLength').value;
  
  
	  for(var a=0;a<flag;a++){
	  
	   	if(document.getElementById('activityRequired'+a).value=="N")
	   	{
	   	
	   	}else
	   	{
	   if(document.getElementById('prejoinTargetDate'+a).value=="")
	    { 
	    
	     
	       alert("please enter/select "+document.getElementById('Tdate').innerHTML.toLowerCase()); 
	      document.getElementById('prejoinTargetDate'+a).focus();
	     return false;
	    }
	   else if(!validateDate('prejoinTargetDate'+a,'Tdate'))
			return false;
	   if(!dateCheckWithToday('prejoinTargetDate'+a,'Tdate'))
		return false; 		
	    if(document.getElementById('paraFrm_ResponsiblePersonCode'+a).value=="")
	    {
	       
	      alert("please select "+document.getElementById('Respper').innerHTML.toLowerCase()); 
	      document.getElementById('paraFrm_ResponsiblePersonCode'+a).focus();
	    
	     return false;
	    }
	  }
	  }
	  return true;
}

   
function addnewFun()
{
	document.getElementById('paraFrm').action="PrejoiningActivities_addNew.action";
	document.getElementById('paraFrm').submit();
}



function saveFun ()
{
    if(!validateBlank(fieldName,lableName,badflag))
        return false;  
    
	if(trim(document.getElementById('paraFrm_joinDate').value)=="dd-mm-yyyy"){
	
		alert("Please enter/select "+document.getElementById('prej.joidt').innerHTML.toLowerCase());
		return false;
	
	}    
   
   if(trim(document.getElementById('paraFrm_offerDate').value)=="dd-mm-yyyy"){
	
		alert("Please enter/select "+document.getElementById('prej.offdt').innerHTML.toLowerCase());
		return false;
	
	}       
       var joindate=document.getElementById('paraFrm_joinDate').value;
	   var offerdate=document.getElementById('paraFrm_offerDate').value;
	   
	   
	   if(joindate!="dd-mm-yyyy"){
	       if(!validateDate('paraFrm_joinDate','prej.joidt'))
	          return false;
	    }  
	    
	    if(offerdate!="dd-mm-yyyy"){    
			if(!validateDate('paraFrm_offerDate','prej.offdt'))
			return false;
		}	
			
			
	   
	  
	  if(joindate!="dd-mm-yyyy" && offerdate!="dd-mm-yyyy"){
       if(!dateDifferenceEqual(offerdate,joindate,'paraFrm_joinDate','prej.offdt','prej.joidt'))
		return false;
	  }	
        
        var listtype=document.getElementById('paraFrm_checkListType').value;
       if(document.getElementById('paraFrm_checkListType').value!="S")
		{
		}
		else
		{ 
	//	alert("Please select check list type");
	alert("Please "+document.getElementById('prej.seact').innerHTML.toLowerCase());
		  return false;
		}
        
        if(document.getElementById('paraFrm_prejoiningListLength').value=="" || document.getElementById('paraFrm_prejoiningListLength').value=="0"){
		alert("Please click on Show List.");
		return  false;}		
		else if(document.getElementById('paraFrm_prejoiningListLength').value>0 && document.getElementById('paraFrm_checkListType').value !=document.getElementById('paraFrm_Chkstatus1').value){
		alert("Please click on Show List.");
		return false;
		}
		 if(document.getElementById('paraFrm_Appstatus').value=="Close")
		{
		 alert("You can not modify the closed application.");
		 return false;
		}
	 if(listValidation())
	  {
	     if(document.getElementById('paraFrm_Chkstatus').value=="1")
		  {
		  var st=document.getElementById('paraFrm_checkListType').value;
		 // alert("st"+st);
		   try{
		  var st1=document.getElementById('paraFrm_Chkstatus1').value;
		  // alert("st1"+st1);
		  
		   if((st!=""&& st1!="") && st1!=st)
		 {  alert("Please select proper check list type.");
		    return false;
		    }else
		    {
		    // alert("no problem");
		    }
		  }
		  catch(e)
		  {
		 
		  }
		 
		  }
		 
		 document.getElementById('paraFrm').action="PrejoiningActivities_save.action";
	     document.getElementById('paraFrm').submit();
		}else
		{
		 return false;
		}
	
}

//For Edit Button

function editFun()
{       
     
      if(document.getElementById('paraFrm_Appstatus').value=="Close")
		{
	  	 alert("You can not edit the closed application.");
	 	 return false;
		}

	document.getElementById('paraFrm').action="PrejoiningActivities_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record?')
	if(con){
		   document.getElementById('paraFrm').action="PrejoiningActivities_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}

}

//For F9Window

function searchFun()
{
	callsF9(500,300,"PrejoiningActivities_f9search.action");
	
}

//For Cancel Button

function cancelFun()
{
    if(document.getElementById('paraFrm_buttonpanelcode').value=="2")
	  {
	   document.getElementById('paraFrm').action="PrejoiningActivities_cancelSec.action";
	  }else if(document.getElementById('paraFrm_buttonpanelcode').value=="3")
	  {
	   document.getElementById('paraFrm').action="PrejoiningActivities_cancelThrd.action";
	  }
	  else
	  {
	  document.getElementById('paraFrm').action="PrejoiningActivities_cancelFirst.action";
	  }
	  document.getElementById('paraFrm').submit();
	  return true;
	
} 	
    	
   function rowcallsF9(id)
    {
      document.getElementById('paraFrm_rowId').value=id; 
       callsF9(500,325,'PrejoiningActivities_f9Responsible.action'); 
      
   
     
    }
	function callChange(){
	
	document.getElementById('paraFrm_Chkstatus').value="1"; 
		
	}
	
	function validateDate1(fieldName,labName){

	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+labName+" should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}
	
window.onLoad=onLoad;	
function onLoad(){
var fieldName =["paraFrm_joinDate","paraFrm_offerDate"];
setTextArray(fieldName,"dd-mm-yyyy");


}	
 	
</script>