<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="InitialAppointment" id="paraFrm" validate="true"
	theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Appointment</td>
		</tr>
		
		<tr align="left">
			<td colspan="6" valign="middle" class="buttontr"><s:if test="%{initialAppt.insertFlag}">
				<s:submit cssClass="pagebutton" action="InitialAppointment_save" theme="simple"
					value="   Save   " onclick="return callAdd();" />
			</s:if> <s:elseif test="%{initialAppt.updateFlag}">
				<s:submit cssClass="pagebutton" action="InitialAppointment_save" theme="simple"
					value="  Save  " onclick="return callAdd();" />
			</s:elseif> <!--<input type="button" class="pagebutton"  onclick="call('InitialAppointment_save.action')" value="  Save " />
  		--><s:if test="%{initialAppt.generalFlag}">
			</s:if> <s:else>

				<s:submit cssClass="pagebutton" action="InitialAppointment_reset" theme="simple"
					value="  Reset  " />
			</s:else> <s:if test="%{initialAppt.deleteFlag}">


				<!--<s:submit cssClass="pagebutton" action="InitialAppointment_delete" theme="simple"   value="  Delete  " />
  		-->
				<input type="button" class="pagebutton"
					onclick="calldel('InitialAppointment_delete.action')"
					value=" Delete " />
			</s:if></td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>


		<tr>
			<td width="25%">Employee<font color="red">*</font> :</td>
			<td width="75%" colspan="3"><s:hidden name="empId"
				theme="simple" id="empId" /> <s:if
				test="%{initialAppt.generalFlag}">
			</s:if> <s:else>
				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
					width="18"
					onclick="javascript:callsF9(500,325,'InitialAppointment_f9action.action');" />
			</s:else> <s:textfield readonly="true" name="employeeToken" theme="simple"
				id="employeeToken" size="10" /><s:textfield readonly="true"
				label="%{getText('empName')}" name="empName" size="77" /></td>
		</tr>
		
		<tr>
			<td width="25%" colspan="1">Current Center :</td>

			<td width="25%" colspan="1"><s:textfield
				label="%{getText('empCent')}" name="empCent" size="30"
				readonly="true" /></td>
			<td colspan="1" width="25%">Current Rank :</td>
			<td width="25%"><s:textfield label="%{getText('empRank')}"
				name="empRank" size="25" readonly="true" /></td>


		</tr>

		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
			<tr>
			<td colspan="4" class="sectionhead">Initial Appointment Details</td>
		</tr>
		
		
		
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		
		
		<tr>
			<td width="25%" colspan="1">Initial Center :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('initCentName')}" theme="simple"
				name="initCentName" size="25" readonly="true" /> <img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'InitialAppointment_f9initialCentaction.action');">
			<s:hidden name="initCentCode" /></td>
			<td width="25%" colspan="1">Initial Rank :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('initRankName')}" theme="simple"
				name="initRankName" readonly="true" /> <img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'InitialAppointment_f9initialRankaction.action');">
			<s:hidden name="initRankCode" /></td>
		</tr>



		<tr>
			<td width="25%" colspan="1">Initial Trade :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('initTradeName')}" theme="simple"
				name="initTradeName" size="25" readonly="true" /> <img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'InitialAppointment_f9initialTradeaction.action');">
			<s:hidden name="initTradeCode" /></td>
			<td width="25%" colspan="1">Rt Type :</td>
			<td width="25%" colspan="1"><s:select name="rtType"
				list="#{'R':'Regular','T':'Temporary'}" cssStyle="width:157" /></td>


		</tr>

		<tr>
			<td width="25%" colspan="1">Initial Designation :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('initialDesg')}" theme="simple" name="initialDesg"
				size="25" readonly="true" /> <img src="../pages/images/search.gif" class="iconImage"
				height="18" align="absmiddle" width="18"
				onclick="javascript:callsF9(500,325,'InitialAppointment_f9desgaction.action');">
			<s:hidden name="desgCode" /></td>
			<td width="25%" colspan="1">D.O.J. Dockyard :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('dtOfJnDockyard')}" theme="simple"
				name="dtOfJnDockyard" /> <s:a
				href="javascript:NewCal('dtOfJnDockyard','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>
		</tr>

		<tr>
			<td width="25%" colspan="1">D.O.J. Service :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('dtOfJnServ')}" theme="simple" name="dtOfJnServ"
				size="25" /> <s:a
				href="javascript:NewCal('dtOfJnServ','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>

			<td width="25%" colspan="1">Initial Joining Date :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('initialJnDate')}" theme="simple"
				name="initialJnDate" /> <s:a
				href="javascript:NewCal('initialJnDate','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>

		</tr>

		<tr>
			<td width="25%" colspan="1">Super Annuation Date :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('superAnnDt')}" theme="simple" name="superAnnDt"
				size="25" /> <s:a href="javascript:NewCal('superAnnDt','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>
			<td width="25%" colspan="1">PayScale :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('payScale')}" theme="simple" name="payScale"
				readonly="true" /> <img src="../pages/images/search.gif" class="iconImage" height="18"
				align="absmiddle" width="18"
				onclick="javascript:callsF9(500,325,'InitialAppointment_f9payaction.action');">
			<s:hidden name="payCode" /></td>
		</tr>


		<tr>
			<td colspan="1">D.C.E. Letter No. :</td>
			<td colspan="1"><s:textfield label="%{getText('dceList')}"
				theme="simple" name="dceList"  size="30"
				 /></td>
			<td width="25%" colspan="1">DCE Date :</td>
			<td width="25%" colspan="1"><s:textfield
				label="%{getText('dceDate')}" theme="simple" name="dceDate" /> <s:a
				href="javascript:NewCal('dceDate','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>
		</tr>

		<tr>

			<td width="100%" colspan="4">&nbsp;</td>

		</tr>
		
		<tr><td colspan="4" class="sectionhead">Initial
			Medical Check up details</td></tr>
		<!--<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Initial
			Medical Check up details</td>
		</tr>

		--><tr>

			<td width="100%" colspan="4">&nbsp;</td>

		</tr>

		<tr>
			<td>Date of Medical Exam :</td>
			<td><s:textfield label="%{getText('dtOfExm')}" theme="simple"
				name="dtOfExm" size="25" /> <s:a
				href="javascript:NewCal('dtOfExm','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>

			<td>Name Of Doctor :</td>
			<td><s:textfield label="%{getText('doctName')}" theme="simple"
				name="doctName" maxlength="150" size="25" /></td>

		</tr>

		<!--<tr>
		<td>Name Of Doctor :</td>
		<td colspan="3">
  		<s:textfield label="%{getText('doctName')}"   theme="simple"  name="doctName"  maxlength="150" size="30"/>
  		</td>
  	</tr>
  	
  	-->
		<tr>
			<td>Name of Hospital :</td>
			<td><s:textfield label="%{getText('hspName')}" theme="simple"
				name="hspName" maxlength="150" size="30" /></td>
			<td>Remarks :</td>
			<td><s:textfield label="%{getText('remarks')}" theme="simple"
				name="remarks" maxlength="150" size="25" /></td>

		</tr>


		<!--<tr>
		<td>Remarks :</td>
		<td colspan="3">
  		<s:textfield label="%{getText('remarks')}"   theme="simple"  name="remarks" maxlength="150" size="30"/>
  		</td>
  	</tr>
  	
  		  -->
		<tr>

			<td width="100%" colspan="4">&nbsp;</td>

		</tr>


		




	</table>
</s:form>

<script>
	
	function callAdd() {
	
     var dojs = document.getElementById("dtOfJnServ").value;
	 var dojd = document.getElementById("dtOfJnDockyard").value;
	 var ijd  = document.getElementById("initialJnDate").value;
	 var sad  = document.getElementById("superAnnDt").value;
	 var dd   = document.getElementById("dceDate").value;
	 var dme  = document.getElementById("dtOfExm").value;
	if(document.getElementById("empName").value=="") {
  			
  			   alert("Please enter Employee Name");
  			 	return false;		
  			 } 
  			
  					
  			 if(dojd!="" ){
			var checkdojd = validateDate(dojd);
			 if(!checkdojd) {
		    alert("Date of Joining Dockyard is not a valid Date");
		    return false;
	 	      }
		      }
		
		if(dojs!="" ){
			var checkdojs = validateDate(dojs);
			 if(!checkdojs) {
		    alert("Date of Joining Service is not a valid Date");
		    return false;
	 	   }
		}
		
		if(ijd!="" ){
			var checkijd = validateDate(ijd);
			 if(!checkijd) {
		    alert("Initial Joining Date is not a valid Date");
		    return false;
	 	   }
		}
		
		if(sad!="" ){
			var checksad = validateDate(sad);
			 if(!checksad) {
		    alert("Super Annuation Date is not a valid Date");
		    return false;
	 	   }
		}
		
		
		if(dd!="" ){
			var checkdd = validateDate(dd);
			 if(!checkdd) {
		    alert("DCE Date is not a valid Date");
		    return false;
	 	   }
		}
		
		
		if(dme!="" ){
			var checkdme = validateDate(dme);
			 if(!checkdme) {
		    alert("Date of Medical Exam is not a valid Date");
		    return false;
	 	   }
		}
			
		return true;
		
				
	        }
	        
	        
	        function calldel(name) {
  			if(document.getElementById("empName").value=="") {
  			alert("First select the Employee Name");
  			} else {
  			var conf=confirm("Are you sure to delete this record ? ");
  			if(conf) {
  			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	
  			
  			}
  	        }
  			}
	
	
	
	 
	function charactersonly(myfield)
	{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}



function validateDate(fld) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
     
    if (!((fld.match(RegExPattern)) && (fld!=''))){
        
        return false;
    
    }
    return true;
   }







</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>




