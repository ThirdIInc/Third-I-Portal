<!-- Developed by @Saipavan voleti -->
<!-- Date:14-Oct-2008  -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="Form12Action" validate="true" id="paraFrm"
	theme="simple">
	
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">PF Form 12A</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right" width="100%">
				<div align="right"><font color="red">*</font> Indicates Required</div>
			</td>
		</tr>
		
		
		<tr>
			<td >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<tr>
		<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		
	</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" id="reportBodyTable">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" >
					<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select Period</strong></td>
						</tr>
						<tr>
							<td width="10%" nowrap="nowrap"><label  class = "set"  id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label><font color="red">
							*</font> :</td>
							<td width="15%"><s:select name="monthName" headerKey="1"
								headerValue="----Select----"
								list="#{'01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td width="10%"><label  class = "set"  id="finYearFrm" name="finYearFrm" ondblclick="callShowDiv(this);"><%=label.get("finYearFrm")%></label><font color="red">
							*</font> :</td>
							<td width="15%"><s:textfield size="6" name="fromYear"
								maxlength="4" onkeypress="return numbersonly(this);"
								 /></td>

					<!--  		<td width="10%"><label  class = "set"  id="finYearTo" name="finYearTo" ondblclick="callShowDiv(this);"><%=label.get("finYearTo")%></label><font color="red">
							*</font> :</td>
							<td width="15%"><s:textfield size="6" name="toYear"
								maxlength="4" onkeypress="return numbersonly(this);"
								readonly="true" /></td> -->
								
								<s:hidden name="reportType" />
						<s:hidden name="reportAction" value='Form12_report.action'/>
						</tr>
						
						

						
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" >
				<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select Filter</strong></td>
						</tr>
							<tr>
							<td class="formtext"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">
							*</font> :</td>
							<td><s:hidden name="divisioncode" /><s:textfield
								name="divisionName" size="25" theme="simple" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17"
								onclick="javascript:callsF9(500,325,'Form12_f9actionDiv.action');"></td>
						<!--	<td width="10%" nowrap="nowrap"><label  class = "set"  id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label><font color="red">
							*</font> :</td>
							<td width="15%"><s:select name="monthName" headerKey="1"
								headerValue="----Select----"
								list="#{'01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>  -->
						</tr>

					</table>
					</td>
				</tr>
				
			</table>
			</td>
		</tr>
		<tr>
			<td >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<tr>
		<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		
	</tr>
	</table>
</s:form>


<script>
function callReset(){
   		document.getElementById('paraFrm_divisioncode').value="";
   		document.getElementById('paraFrm_divisionName').value="";
   		document.getElementById('paraFrm_monthName').selectedIndex=0;
   		var current = new Date();
		var year =current.getFullYear();
	  	document.getElementById("paraFrm_fromYear").value =year;		
   }
	function validateFields()
	{
		if(document.getElementById('paraFrm_divisioncode').value=="")
		{
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_divisioncode').focus();
			return false;
		}
   	 
   	 if(document.getElementById('paraFrm_monthName').selectedIndex == 0)
		{
			alert("Please select the  "+document.getElementById('months').innerHTML.toLowerCase());
			document.getElementById('paraFrm_monthName').focus();
			return false;
		}
	  var from = document.getElementById('paraFrm_fromYear').value;
	  if(from=="")
	    {
	    	alert("Please Enter "+document.getElementById('finYearFrm').innerHTML.toLowerCase());
	    	return false;
	    }
	    if(!checkYear('paraFrm_fromYear','finYearFrm')){
	 		return false;	 
		 }
	 return true;
	}
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='Form12_reportMail.action';
			document.getElementById('paraFrm').submit();
			//callDropdown(obj.name,200,250,'Form6Report_mailReport.action','false');
			//return true;
		}	
	}  
	
	function callReport(type){
		document.getElementById('paraFrm_reportType').value=type;
		if(!validateFields()){
				return false;
			} else {
				/*document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="ESICReport_report.action";
				document.getElementById('paraFrm').submit();*/
				callReportCommon(type);
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
	
	
   function add()
   {
    var from = document.getElementById('paraFrm_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_toYear').value=x;
	  }
   }
   
   
   /*function callView()
   {
     if(document.getElementById('paraFrm_divisioncode').value=="")
		{
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_divisioncode').focus();
			return false;
		}
   	 
   	 if(document.getElementById('paraFrm_monthName').selectedIndex == 0)
		{
			alert("Please select the  "+document.getElementById('months').innerHTML.toLowerCase());
			document.getElementById('paraFrm_monthName').focus();
			return false;
		}
	  var from = document.getElementById('paraFrm_fromYear').value;
	  if(from=="")
	    {
	    	alert("Please Enter "+document.getElementById('finYearFrm').innerHTML.toLowerCase());
	    	return false;
	    }
	    if(!checkYear('paraFrm_fromYear','finYearFrm')){
	 		return false;	 
		 }
	    
	   		document.getElementById('paraFrm').target="_blank";	
			document.getElementById('paraFrm').action="Form12_report.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";
		
   
   }*/
   
   function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_fromYear").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_fromYear").value =year;
	  }
}


	getYear();
   
	   
   </script>