
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="TransferApplication"  id="paraFrm" validate="true" theme="simple">
<table class="bodyTable" width="100%">
      <tr>
  			<td width="100%" colspan="4" class="pageHeader" align="center" >Initial Appointment/Medical Check Up details</td>
  	  </tr>
  	   <tr>
	    <td width="100%" colspan="4">&nbsp;</td>
	  </tr>
	  
	  <tr><td>&nbsp;</td></tr>
	<tr>
	  <td width="25%" class= "COMMONLABEL" >Employee Code</td>
	  <td width="25%" >
	  <s:textfield label="%{getText('empId')}"  theme="simple"  name="empId"/> 
	  <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callsF9(500,325,'InitialAppointment_f9action.action');">
	  </td>
	  <td width="25%"></td>
	  <td width="25%"></td>
	</tr>
	
	
		
		<tr>
		<td class= "COMMONLABEL" >Employee Name</td>
		<td colspan="3">
  		<s:textfield label="%{getText('empName')}"   theme="simple"  name="empName"/>
  		</td>
  	</tr>
  	
  	<tr>
  		<td width="25%" class= "COMMONLABEL" >Department</td>
		<td width="25%">
  		<s:textfield label="%{getText('empDept')}"   theme="simple"  name="empDept"/>
  		</td>
  		
  		<td width="15%" class= "COMMONLABEL" >Center</td>
		<td width="25%" align="left">
  		<s:textfield label="%{getText('empCent')}"   theme="simple"  name="empCent"/>
  		</td>
  		
  </tr>
  
  		 		
  		
  		<tr>
  		<td width="25%" class= "COMMONLABEL" >Initial Designation</td>
		<td width="25%">
  		<s:textfield label="%{getText('initialDesg')}"   theme="simple"  name="initialDesg"/>
  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callsF9(500,325,'InitialAppointment_f9desgaction.action');">
  		<s:hidden name="desgCode" /> 
  		</td>
  		<td width="25%" class= "COMMONLABEL"  >D.O.J. Dockyard</td>
		<td width="25%">
  		<s:textfield label="%{getText('dtOfJnDockyard')}"   theme="simple"  name="dtOfJnDockyard"/>
  		</td>
  		</tr>
  		
  		<tr>
		<td width="25%" class= "COMMONLABEL" >D.O.J. Service</td>
		<td width="25%">
  		<s:textfield label="%{getText('dtOfJnServ')}"   theme="simple"  name="dtOfJnServ"/>
  		</td>	
  		
  		<td width="25%" class= "COMMONLABEL" >Initial Joining Date</td>
		<td width="25%">
  		<s:textfield label="%{getText('initialJnDate')}"   theme="simple"  name="initialJnDate"/>
  		</td>	
  			
  		</tr>
  		
  	<tr>
  		<td width="25%" class= "COMMONLABEL" >Super Annotation Date</td>
  		<td width="25%">
		<s:textfield label="%{getText('superAnnDt')}"   theme="simple"  name="superAnnDt"/>
  		</td>
  		<td width="25%" class= "COMMONLABEL" >PayScale</td>
		<td width="25%">
  		<s:textfield label="%{getText('payScale')}"   theme="simple"  name="payScale"/>
  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callsF9(500,325,'InitialAppointment_f9payaction.action');">
  		<s:hidden name="payCode" /> 
  		</td>
   </tr>
   
   
   <tr>
		<td class= "COMMONLABEL" >D.C.E. List</td>
		<td colspan="3">
  		<s:textfield label="%{getText('dceList')}"   theme="simple"  name="dceList"/>
  		</td>
  	</tr>
  	
  	
  	<tr>
		<td class= "COMMONLABEL" >Date of Medical Exam</td>
		<td colspan="3">
  		<s:textfield label="%{getText('dtOfExm')}"   theme="simple"  name="dtOfExm"/>
  		</td>
  	</tr>
  	
  	<tr>
		<td class= "COMMONLABEL" >Name Of Doctor</td>
		<td colspan="3">
  		<s:textfield label="%{getText('doctName')}"   theme="simple"  name="doctName"/>
  		</td>
  	</tr>
  	
  	<tr>
		<td class= "COMMONLABEL" >Name of Hospital</td>
		<td colspan="3">
  		<s:textfield label="%{getText('hspName')}"   theme="simple"  name="hspName"/>
  		</td>
  	</tr>
  	
  	
  	<tr>
		<td class= "COMMONLABEL" >Remarks</td>
		<td colspan="3">
  		<s:textfield label="%{getText('remarks')}"   theme="simple"  name="remarks"/>
  		</td>
  	</tr>
  		
  		
  		<tr align="center">
  		<td colspan="6">
  		<s:submit cssClass="pagebutton" action="InitialAppointment_save" theme="simple"  value="   Submit   " />&nbsp;
  		<s:submit cssClass="pagebutton" action="InitialAppointment_reset" theme="simple"   value="  Reset  " />
  		<s:submit cssClass="pagebutton" action="InitialAppointment_delete" theme="simple"   value="  Delete  " />
  		</td>
  		</tr>
  		
	
	
	
	</table>
	</s:form>
	
	
	
	
	