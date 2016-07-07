<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="LeaveDCE" method="post" id="paraFrm" validate="true" theme="simple">
		
  		<table class="bodyTable" width="100%">
  	<tr>
  			<td width="100%" colspan="6" class="pageHeader" align="center" >Leave DCE List Generation</td>
  	</tr>
  	<tr><td colspan="6">&nbsp;</td></tr>
  	<tr><td colspan="6">&nbsp;</td></tr>
 	<tr><td>&nbsp;</td><td width="50%" align="center"> Select Status :<s:select  name="status"  list="#{'':'-----Select-----','A':'Approved','N':'Cancelled'}"  theme="simple"/>
 	&nbsp;&nbsp;<s:submit cssClass="pagebutton" action="LeaveDCE_dceLIst" onclick="generateStatus()"  value="  Go  " />
 	</td>
 	</tr>
  	
  	<tr><td colspan="6">&nbsp;</td></tr>
  	<tr><td colspan="6">&nbsp;</td></tr>
  	
  	<tr><td width="100%" colspan="6" >
  			<table class="bodyTable" width="100%">
  				<tr><td class="headercell" width="15%">Token No.</td>
					<td class="headercell" width="50%">Employee Name</td>
					<td class="headercell" width="15%">Application Date</td>
					<td class="headercell" width="15%">Status</td>
					<td class="headercell" width="15%">Flag</td>
					<td class="headercell" width="15%">&nbsp;</td>
  				</tr>
  				<s:iterator value="cancelList" > 
  					<tr>
  						<td width="15%"><s:property value="empCode" /></td>
						<td width="50%"><s:property value="empName" /></td>
						<td width="15%"><s:property value="applicationDate" /></td>
						<td width="15%"><s:property value="status" /></td>
						<td width="15%"><s:checkbox name="leaveCode" fieldValue="%{leaveCode}" /></td>
						<td width="15%"><input type="button" class="pagebutton" style="cursor:hand"  onclick="callCancel('<s:property value="leaveCode" />');"  name="detail" value="See Detail" /></td>
  					</tr>   	
  				</s:iterator>
  			</table>
  		</td>
  	</tr>
  	<tr><td colspan="6">&nbsp;</td></tr>
	<tr><td >DCE List No.<font color="red">*</font> :</td>
		<td><s:textfield theme="simple" name="leaveDce.dceNo" size="30" />
		</td>
		<td  >DCE Date<font color="red">*</font> :</td>
		<td colspan="3" ><s:textfield theme="simple" name="leaveDce.dceDate" size="30" />
		<s:a href="javascript:NewCal('leaveDce.dceDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle" width="16" ></s:a>
		</td>
  	</tr>
  	<tr><td colspan="6">&nbsp;</td></tr>
  	<tr><td colspan="6"><center><s:submit cssClass="pagebutton" action="LeaveDCE_gerateDCENo" onclick="return generateDCE();" name="save" value=" Generate DCE List " />
  								<s:submit cssClass="pagebutton" action="LeaveDCE_generateDce" onclick="return generateMe();" name="save" value=" Save " /></center> </td></tr>
  								
<tr><td colspan="6">&nbsp;</td></tr> 								
<tr><td colspan="6" class="seperator" ></td></tr>
<tr><td colspan="6">&nbsp;</td></tr>
</table>


<table class="bodyTable" width="100%">
	
<tr> 
<td align="left">Select Authority<font color="red">*</font> :
<s:textfield theme="simple" name="authName" size="28" /><s:hidden name="authTok"/><s:if test="leaveDce.viewFlag"><img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18" 
  		onclick="javascript:callsF9(500,325,'LeaveApplication_f9action.action');"></s:if> 
</td>
</tr>
<tr>
<td align="left">Enter DCE No.<font color="red">*</font> :
<s:textfield theme="simple" name="dceNumber" size="30" />
</td>

		<s:if test="leaveDce.viewFlag">
	<td>&nbsp;</td><td>&nbsp;</td><td align="left"><input type="button" class="pagebutton"  onclick="generateDCERep()" value="           DCE List Report " /></td>
</s:if>
</tr>

</table>
  
  
  <s:hidden name="leave.empCode" />
  <s:hidden name="leave.department" />
  <s:hidden name="leave.center" />
  <s:hidden name="leaveApplication.paracode" />
 </s:form>
 <script type="text/javascript">
 function callCancel(id){
 		document.getElementById("paraFrm").target ="_blank";
		document.getElementById("paraFrm").action="LeaveApplication_callByCanceller.action";
		document.getElementById('leaveApplication.paracode').value=id;
		document.getElementById("paraFrm").submit();
		document.getElementById("paraFrm").target ="main";
	}
	function generateMe(){
			var dceList =document.getElementById('leaveDce.dceNo').value;
			if(dceList == ""){
				alert (" Enter DCEList No. !");
				return false;
			}
			var tDate =document.getElementById('leaveDce.dceDate').value;
	 		var checkDat = validateDate(tDate);
	 		if(!checkDat){
				alert (" Enter valid date");
				return false;
			}	
	 			return true;
	}
	 function validateDate(fld) {
		    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
		        	
		    if (!((fld.match(RegExPattern)) && (fld!=''))){
		        
		        return false;
		    
		    }
		    return true;
		  }
		  
		  
		
		
	function generateDCE(){
	
	if(document.getElementById('leaveDce.dceNo').value=="") {  
 			alert("Please enter DCE List No.");
 			return false;
 			}
 			
 	if(document.getElementById('leaveDce.dceDate').value==""){
 			alert("Please select the DCE Date.");
 			return false;
 			}
			return true;
			
 			
  			}	
		
		  
		  
	function generateDCERep(){
	
	
	if(document.getElementById('authName').value==""){
 			alert("Please select the Authority");
 			return false;
 			}
	if(document.getElementById('dceNumber').value=="") {  
 			alert("Please Enter the DCE No.");
 			return false;
 			}
 			
 		else {	
 			
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='LeaveDCE_report.action';	
			document.getElementById('paraFrm').submit();
			return true;
			}
 			
  			}
  			
  			
  			
  	function generateStatus(){
	
	if(document.getElementById('status').value=="") {  
 			alert("Please select the Status.");
 			return false;
 			}
 			
 			return true;
			
 			
  			}		
	
	
		
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>