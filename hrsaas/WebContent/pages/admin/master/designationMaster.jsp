
<%@ taglib prefix="s" uri="/struts-tags" %>  


  	
  	<s:form action="DesignationMaster"  validate="true" id="paraFrm" validate="true" theme="simple">
	<table class="bodyTable" width="100%" colspan="3">
	<tr>
  			<td width="100%" colspan="3" class="pageHeader" align="center" >Designation1</td>
  	</tr>
  	
  
	 <tr align="left" class="buttontr">			
	  		<td width="100%"  colspan="4">
	  		<s:if test="desgMaster.insertFlag">
	  		<s:if test="desgMaster.updateFlag"><s:submit cssClass="pagebutton" action="DesignationMaster_save"  value="   Save   " onclick="return validate();" /></s:if></s:if>
	  		<s:submit cssClass="pagebutton" action="DesignationMaster_reset"  value="  Reset  " />
	  		<s:if test="desgMaster.deleteFlag"><s:submit cssClass="pagebutton" action="DesignationMaster_delete"  value="  Delete  " onclick="return del();" /></s:if>
	  		<s:if test="desgMaster.viewFlag"><input type="button" class="pagebutton"  onclick="callReport('DesignationMaster_report.action')" value="  Report  "/>&nbsp;</s:if></td>
	 </tr>	
	<tr>
			
	  		<td width="30%">Select Designation :
	  		<td width="60%" ><s:hidden label="%{getText('desgID')}" name="desgMaster.desgID" id="desgId" />  	
	  		<s:if test="desgMaster.viewFlag"><img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
	  		width="18" onclick="javascript:callsF9(500,325,'DesignationMaster_f9action.action');"></s:if></td>
	  		<td width="10%" ></td>  								
	</tr>
	  	
	<tr>	
			
	  		<td width="30%" >Designation Name <font color="red">*</font>:</td>
	  		<td width="60%"><s:textfield size="30" maxlength="50" label="%{getText('desgName')}" name="desgMaster.desgName" id="desgName"onkeypress="return allCharacters();"/></td>
	  		<td width="10%" ></td>
	</tr>
	
	
	<tr>	
		
	  		<td width="30%" >Designation Parent Code :</td>
	  		<td width="60%"><s:textfield size="30" id="desgParCode" label="%{getText('desgParCode')}" name="desgMaster.desgParCode" onkeypress="return allCharacters();"/></td>
	  		<td width="10%" ></td>
	</tr>
	<tr>	
			
	  		<td width="30%" >Designation Higher Code :</td>
	  		<td width="60%"><s:textfield size="30" id="desgHighCode" label="%{getText('desgHighCode')}" name="desgMaster.desgHighCode" onkeypress="return allCharacters();"/></td>
	  		<td width="10%" ></td>
	</tr>
	<tr>	
			
	  		<td width="30%" >Designation Abbr :</td>
	  		<td width="60%"><s:textfield size="30" maxlength="50" label="%{getText('desgAbbr')}" id="desgAbbr" name="desgMaster.desgAbbr"onkeypress="return allCharacters();"/></td>
	  		<td width="10%" ></td>
	</tr>
	<tr>	
			
	  		<td width="30%" >Designation Desc :</td>
	  		<td width="60%"><s:textfield size="30" maxlength="200" label="%{getText('desgDesc')}" name="desgMaster.desgDesc"/></td>
	  		<td width="10%" ></td>
	</tr>
	<tr>	
		
	  		<td width="30%" >Designation Level :</td>
	  		<td width="60%"><s:textfield size="30" maxlength="100" label="%{getText('desgLevel')}" id="desgLevel" name="desgMaster.desgLevel"/></td>
	  		<td width="10%" ></td>
	</tr>
	<tr>	
		
	  		<td width="30%" >Recommending Authority:</td>
	  		<td width="60%">	  			
	  			   <s:select  name="rcmndAuth"  list="#{'N':'No','Y':'Yes'}"  theme="simple"/>	  			
	  		</td>
	  		<td width="10%" ></td>
	</tr>
	<tr>	
			
	  		<td width="30%" >Approving Authority :</td>
	  		<td width="60%">
	  				<s:select  name="apprAuth"  list="#{'N':'No','Y':'Yes'}"   theme="simple"/>	  			
	  		</td>
	  		<td width="10%" ></td>
	</tr>
	

	
  	</table>  
 
<script>
function isSpclChar(str){
		exp="`~!@#$%^&*_+=|\}]{[:;',<.>?";
		l=str.length
		m=exp.length
		
		for(i=0;i<l;i++){
 	 		c=str.charAt(i);
			for(j=0;j<m;j++){			 	 			
 	 			s=exp.charAt(j);
 	 			if(c==s){return false;}
 	 		}
 		}
 		return true;
 	}

function isSpclChar(str,n){
		exp="`~!@#$%^&*_+=|\}]{[:;',<.>?";
		l=str.length
		m=exp.length
		
		for(i=0;i<l;i++){
 	 		c=str.charAt(i);
			for(j=0;j<m;j++){			 	 			
 	 			s=exp.charAt(j);
 	 			if(c==s){return false;}
 	 		}
 		}
 		return true;
 	}
 	 	
function validate(){
var desgName = document.getElementById("desgName").value;
var dpc=document.getElementById("desgParCode").value;
var dhc=document.getElementById("desgHighCode").value;


 if(document.getElementById("desgName").value==""){
	  alert('Please enter designation name.');
	  document.getElementById("desgName").focus();
	  return false;
 }
 
  if(!(desgName==""))
					{ 
					 
					var count =0;
					var iChars =" ";
		  			for (var i = 0; i < desgName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(desgName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==desgName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;	
  					}
				}
	if(!(dpc=="")) 
	{
	
		var count =0;
					var iChars =" ";
		  			for (var i = 0; i < dpc.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(dpc.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==dpc.length){
  					alert ("Blank Spaces Not Allowed in Designation Parent Code.");
  					return false;	
  					}
		
	}	
	
	if(!(dhc=="")) 
	{
	
		            var count =0;
					var iChars =" ";
		  			for (var i = 0; i < dhc.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(dhc.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==dhc.length){
  					alert ("Blank Spaces Not Allowed in Designation Higher Code.");
  					return false;	
  					}
		
	}			 
 		 
 
 
 
 if(!isSpclChar(document.getElementById("desgName").value)){
 	  alert('Invalid Designation Name.');
 	  document.getElementById("desgName").focus();
	  return false;
 }if(!isNumber(document.getElementById("desgParCode").value)){
  	  alert('Invalid Designation Parent Code Entered.');
 	  document.getElementById("desgParCode").focus();
	  return false;
 }if(!isNumber(document.getElementById("desgHighCode").value)){
  	  alert('Invalid Designation Higher Code Entered.');
 	  document.getElementById("desgHighCode").focus();
	  return false;
 }if(!isSpclChar(document.getElementById("desgAbbr").value,1)){
  	  alert('Invalid Designation Abbreviation.');
 	  document.getElementById("desgAbbr").focus();
	  return false;
 }if(!isSpclChar(document.getElementById("desgLevel").value,1)){
  	  alert('Invalid Designation Level.');
 	  document.getElementById("desgLevel").focus();
	  return false;
 }
 return true;
}
	function isNumber(no){
 		l=no.length;
 		for(i=0;i<l;i++){
 	 		c=no.charAt(i);
 	 		if(!(c>=0 || c<=9)|| c==' ' || c=='.'){return false;}
 		}
 		return true;
	}
function del(){
    if(document.getElementById("desgId").value==""){
   		 alert('Please Select Designation');
	     return false;
   	}else{
   		var conf=confirm("Do you really want to delete this record?");
		if(conf) {
			 document.getElementById('paraFrm').submit();
 			 return true;
		}
	} 
   return false;
}
function callReport(name)
 {
	document.getElementById('paraFrm').target="_blank";
	document.getElementById('paraFrm').action=name;	
	document.getElementById('paraFrm').submit();							
	document.getElementById('paraFrm').target="main";
}
	
  	</script>
 </s:form>	