<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
	
	<%
		String trvlFlag = "";
		String accomFlag = "";
		String convFlag = "";
		
		String trRadio = "";
		String accomRadio = "";
		String locRadio = "";
		
		
		 
		try{
			trvlFlag = (String)request.getAttribute("travel");
			accomFlag = (String)request.getAttribute("accom");
			convFlag = (String)request.getAttribute("conv");
			
			trRadio = (String)request.getAttribute("trRadio");
			accomRadio = (String )request.getAttribute("accomRadio");
			locRadio = (String )request.getAttribute("locRadio");
			
			 
			//out.println("trRadio::"+trRadio);
			//out.println("accomRadio::"+accomRadio);
			//out.println("locRadio::"+locRadio);
			//out.println(" 1."+trvlFlag);
			//out.println(" 2."+accomFlag);
			//out.println(" 3."+convFlag);
			
		}finally{
			
			if(trvlFlag==null){trvlFlag = "off";}
			if(accomFlag==null){accomFlag = "off";}
			if(convFlag==null){convFlag = "off";}
			
			if(trRadio==null){trRadio="";}
			if(accomRadio==null){accomRadio="";}
			if(locRadio==null){locRadio="";}
			
			
		}
		
		
	%>
	
	
<s:form name="" action="" validate="" id="paraFrm" theme="simple">


<input type="hidden" name="fieldId" id="fieldId">
<input type="hidden" name="fieldValue" id="fieldValue">

<s:hidden name="appStatus" />
<s:hidden name="listType" />
<s:hidden name="appId" />
<s:hidden name="appCode"  />
<s:hidden name="hAppFor"  />
<s:hidden name="empId" /> 
<s:hidden name="initId" />


<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Application</strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%" colspan="2"> 
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
		 <td colspan="3">
		  <table width="100%" border="0" class="formbg">
		   <tr>
		    <td width="20%">
		    	<label class="set" name="traappfor" id="traappfor" 	ondblclick="callShowDiv(this);"><%=label.get("traappfor")%></label>:
		    </td>
		    <td width="20%">
		     <s:if test='%{hAppFor=="S"}'>
			    		Self
			 </s:if>
			 <s:elseif test='%{hAppFor=="O"}'>
			    		Others
			 </s:elseif>
			 <s:else>
			    		Guest
			 </s:else>
		    </td>
		    <td width="15%">
	    	<s:if test='%{hAppFor=="S"}'>
		    	<label class="set" name="traaccom" id="traaccom" ondblclick="callShowDiv(this);"><%=label.get("traaccom")%></label>:
		    </s:if>
		    <s:elseif test='%{hAppFor=="G" || hAppFor=="O"}'>
			    	<label class="set" name="traaccom" id="traaccom1" ondblclick="callShowDiv(this);"><%=label.get("traaccom1")%></label>:
			</s:elseif>		    
		    </td>
		    <td>
		     <s:property value="accompWith"/>
		     <s:hidden name="accompWith" />
		    </td>
		   </tr>
		   <tr>
		    <td>
		    	<label class="set" name="traappdate" id="traappdate" ondblclick="callShowDiv(this);"><%=label.get("traappdate")%></label>:
		    </td>
		    <td>
		     <s:property value="appDate"/>
		     <s:hidden name="appDate" />
		    </td>
		    <td>
		    	<label class="set" name="traStatus" id="traStatus" ondblclick="callShowDiv(this);"><%=label.get("traStatus")%></label>:
		    </td>
		    <td>		     
		     <s:if test='%{appStatus=="N"||appStatus=="" || appStatus=="W"}'>
		      	 Draft
		      </s:if>
		      <s:if test='%{appStatus=="A"}' >
		       	Approved
		      </s:if>
		      <s:if test='%{appStatus=="AC"}' >
			       		Cancellation Approved
			  </s:if>		
		      <s:if test='%{appStatus=="H"}' >
			    On Hold
			  </s:if>
		      <s:if test='%{appStatus=="R"}' >
		       	Rejected
		      </s:if>
		      <s:if test='%{appStatus=="P" ||appStatus=="FT"}' >
		       	Pending
		      </s:if>
		      <s:if test='%{appStatus=="B"}' >
		       	Returned
		      </s:if>		
		      <s:if test='%{appStatus=="PC" || appStatus=="FC"}' >
			  	Pending For Cancellation
	      	  </s:if>
			  <s:if test='%{appStatus=="CC"}' >
			  	Cancelled
			  </s:if>     	     
		    </td>
		   </tr>
		   <tr>
		    <td>
		    	<label class="set" name="trainitor" id="trainitor"	ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label>:
		    </td>
		    <td colspan="3">			    
			    <s:property value="initToken"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="initName"/>
			    <s:hidden name="initToken"/>
			    <s:hidden name="initName"/>
		    </td> 
		   </tr>
		  </table>
		 </td>
		</tr>
				
		<!---------------------------------------------------- TOUR DETAILS [BEGINS] ---------------------------->
		<s:hidden theme="simple" name="tourId" />
	 <s:if test='%{appStatus=="N" || appStatus=="B" || appStatus=="W" || appStatus=="" }'>
		<tr>
		 <td>
		  <table width="100%" class="formbg" border="0">
		   <tr>
		    <td colspan="4" width="100%"><b>Tour Details</b></td>
		   </tr> 
		   <tr> 
		    <td width="25%">
		    	<label class="set" name="Trastdate" id="Trastdate" 	ondblclick="callShowDiv(this);"><%=label.get("Trastdate")%></label>  <font color="red">*</font> :
		    </td>
		    <td width="25%">
			    <s:textfield name="startDate" theme="simple" size="10" maxLength="10" onkeypress="return numbersWithHiphen();" />
			    <s:a href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18">
					</s:a>
		    </td> 
		     <td width="25%">
		      <label class="set" name="Traenddate" id="Traenddate" ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label><font color="red">*</font>: 
		     </td>
		    <td width="25%">
		    <s:textfield name="endDate" theme="simple" size="10" maxLength="10"  onkeypress="return numbersWithHiphen();" />
		    <a href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');"> 
			     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
				</a>
		    </td>
		   </tr>		  
		   <tr>
		    <td>
		    	<label class="set" name="TraReqname" id="TraReqname" ondblclick="callShowDiv(this);"><%=label.get("TraReqname")%></label>
		    	<font color="red">*</font>:</td>
		    <td><s:textfield name="trvlReqName" theme="simple" size="30" maxlength="100" /></td>
		    <td>
		    	<label class="set" name="Trapurpose" id="Trapurpose" ondblclick="callShowDiv(this);"><%=label.get("Trapurpose")%></label>
		    <font color="red">*</font>:</td>
		    <td>
		    	<s:hidden name="purposeId"/>
		    	<s:textfield name="purpose" theme="simple" size="20" readonly="true" />
		    	<img src="../pages/images/recruitment/search2.gif" 
				 class="iconImage" height="16" align="absmiddle" width="16"
				 onclick="javascript:callsF9(500,325,'TravelApplication_f9Purpose.action');">		    	
		    </td>
		   </tr> 
		   <tr>
		    <td>
		    	<label class="set" name="Ftype" id="Ftype" ondblclick="callShowDiv(this);"><%=label.get("Ftype")%></label> :
		    </td>
		    <td width="20%">
		    	<s:hidden name="foodTypeId"/> 
		    	<s:textarea name="foodType" theme="simple" rows="3" cols="24" readonly="true" />
		    	<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9local(50,420,'TravelApplication_f9FoodType.action?foodTypeList='+document.getElementById('paraFrm_foodTypeId').value);">
		    </td>
		    <td>
		    	<label class="set" name="TravelType" id="TravelType" ondblclick="callShowDiv(this);"><%=label.get("TravelType")%></label>
			 	<font color="red">*</font>:</td>  
		    <td>
		     <s:hidden name="trvlTypeId"/>
		     <s:textfield name="trvlType" theme="simple" size="20" readonly="true" />
		     <img src="../pages/images/recruitment/search2.gif"
				 class="iconImage" height="16" align="absmiddle" width="16" 
				 onclick="javascript:callsF9(500,325,'TravelApplication_f9TravelType.action');">
		     </td>
		   </tr>
		   <script> 
		  
		   function callsF9local(width,height,action) {
				win=window.open('','win','top=260,left=250,width=400,height=450,scrollbars=yes,status=no,resizable=no');
				document.getElementById("paraFrm").target="win";
				document.getElementById("paraFrm").action=action;
				document.getElementById("paraFrm").submit();	
				document.getElementById("paraFrm").target="main";
			}
			
		    function displayDiv(chkId,divId,secId){
		    	 
		    	 
		    	 //alert(document.getElementById('trvChk').disabled==true);
		    	 
				 if(document.getElementById('trvChk').checked
				 	&& document.getElementById('trvChk').disabled==false){//Travel checked
				 	document.getElementById('paraFrm').action="TravelApplication_addJourneyDtls.action";
				 	document.getElementById('paraFrm').submit();
				 	return true;
				 	
				 }if(document.getElementById('accomChk').checked
				 	&& document.getElementById('accomChk').disabled==false){//Lodging checked
				 	document.getElementById('paraFrm').action="TravelApplication_addLodgingDtls.action";
				 	document.getElementById('paraFrm').submit();
				 	return true;
				 	
				 }if(document.getElementById('trConv').checked
				 	&& document.getElementById('trConv').disabled==false
				 	){//Local Conveyance checked
				 	document.getElementById('paraFrm').action="TravelApplication_addConveyanceDtls.action";
				 	document.getElementById('paraFrm').submit();
				 	return true;
				 	
				 }
				 
				 //UNRECHABLE CODE BELOW
			     if(document.getElementById(chkId).checked){
			     	
			     	document.getElementById(divId).style.display="block";
			     	document.getElementById(secId).style.display="block";
			     	
			     }else{
			     	
			     	document.getElementById(divId).style.display="none";
			     	document.getElementById(secId).style.display="none";
			     	
			    }
			    
		    }
		   </script>
		   
		   <tr>
		    <td width="18%">
		    	<input type="hidden" name="hTrvChk" value="<%=trvlFlag%>">
		    	<input type="checkbox" name="trvChk" id="trvChk" value="<%=trvlFlag%>" <%=trvlFlag.equals("on")?"disabled":"" %> <%=trvlFlag.equals("on")?"checked":"" %>  onclick="displayDiv('trvChk','trvlDiv','div1')">Travel Arrangement :
		    </td> 
		    <td width="15%">		     
		     <div id="trvlDiv" style="display: <%=trvlFlag.equals("on")?"block":"none" %>;">
		     <table width="100%">
		      <tr>
		       <td>
		        <input type="radio" name="trRadio" value="S" <%=trRadio.equals("S")?"checked":"" %>>Self
		        <input type="radio" name="trRadio" value="C" <%=trRadio.equals("C")||trRadio.equals("")?"checked":"" %>>Company		        	         
		       </td>
		      </tr>
		     </table>
		     </div>		     
		    </td>
		    <td width="15%">
		    	<input type="hidden" name="hAccomChk" value="<%=accomFlag%>">
		    	<input type="checkbox" name="accomChk"  id="accomChk" <%=accomFlag.equals("on")?"checked":"" %> <%=accomFlag.equals("on")?"disabled":"" %>      onclick="displayDiv('accomChk','accomDiv','div2')">Accommodation :</td>
		    <td width="15%">
		     <div id="accomDiv" style="display: <%=accomFlag.equals("on")?"block":"none" %>;">
		     <table width="100%">
		      <tr>
		       <td>
		        <input type="radio" name="accomRadio" value="S" <%=accomRadio.equals("S")?"checked":"" %>>Self
		        <input type="radio" name="accomRadio" value="C" <%=accomRadio.equals("C")||accomRadio.equals("")?"checked":"" %>>Company
		       </td>
		      </tr>
		     </table>
		     </div>	
		    </td>
		   </tr>
		   
		   <tr>
		    <td width="15%">
		    	<input type="hidden" name="hTrConv" value="<%=convFlag%>">
		    	<input type="checkbox" name="trConv" id="trConv" <%=convFlag.equals("on")?"checked":"" %> <%=convFlag.equals("on")?"disabled":"" %> onclick="displayDiv('trConv','locDiv','div3')">Local Conveyance  :</td>
		    <td>
		      <div id="locDiv" style="display: <%=convFlag.equals("on")?"block":"none" %>;">
		     <table width="100%">
		      <tr>
		       <td>
		        <input type="radio" name="locRadio" value="S" <%=locRadio.equals("S")?"checked":"" %>>Self
		        <input type="radio" name="locRadio" value="C" <%=locRadio.equals("C")||locRadio.equals("")?"checked":"" %>>Company
		       </td>
		      </tr>
		     </table>
		     </div>	
		    </td>
		   </tr>
		  </table>
		 </td>
		</tr>
		</s:if>
		<s:else>	
		 <tr>
		 <td>
		  <table width="100%" class="formbg" border="0">
		   <tr>
		    <td colspan="4" width="100%"><b>Tour Details</b></td>
		   </tr> 
		   <tr> 
		    <td width="5%">
		    	<label class="set" name="Trastdate" id="Trastdate1" ondblclick="callShowDiv(this);"><%=label.get("Trastdate")%></label>
		      <font color="red">*</font> :</td>
		    <td width="5%">
			   <s:property value="startDate"/>
		    </td> 
		     <td width="15%">
		     <label class="set" name="Traenddate" id="Traenddate1" 	ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label> <font color="red">*</font>: 
			</td>
		    <td width="25%">
		    	<s:property value="endDate"/>
		    </td>
		   </tr>		  
		   <tr>
		    <td>
		    	<label class="set" name="TraReqname" id="TraReqname1" ondblclick="callShowDiv(this);"><%=label.get("TraReqname")%></label>
		    	<font color="red">*</font>:</td>
		    <td><s:property value="trvlReqName"/></td>
		    <td>
		    	<label class="set" name="Trapurpose" id="Trapurpose1" ondblclick="callShowDiv(this);"><%=label.get("Trapurpose")%></label>
		    	<font color="red">*</font>:</td>
		    <td>
		    	<s:property value="purpose"/>	
		    </td>
		   </tr> 
		   <tr>
		    <td>
		    	<label class="set" name="Ftype" id="Ftype1" ondblclick="callShowDiv(this);"><%=label.get("Ftype")%></label> :
		    </td>
		    <td width="20%">
		    	<s:property value="foodType"/>	
		    </td> 
		    <td>
		    	<label class="set" name="TravelType" id="TravelType" ondblclick="callShowDiv(this);"><%=label.get("TravelType")%></label> <font color="red">*</font>:
		    </td> 
		    <td>
		     <s:property value="trvlType"/>	
		     </td>
		   </tr>
		   <script> 
		  
		   function callsF9local(width,height,action) {
				win=window.open('','win','top=260,left=250,width=400,height=450,scrollbars=yes,status=no,resizable=no');
				document.getElementById("paraFrm").target="win";
				document.getElementById("paraFrm").action=action;
				document.getElementById("paraFrm").submit();	
				document.getElementById("paraFrm").target="main";
			}
			 
		   </script>
		   <tr>
		    <td width="15%">
		    	<label class="set" name="TraArg" id="TraArg1" ondblclick="callShowDiv(this);"><%=label.get("TraArg")%></label> :
		    </td>
		    <td width="15%">		     
		     <table width="100%">
		      <tr>
		       <td>
			        <s:if test='%{trRadio=="S"}'>
			        	Self
			       </s:if>
			       <s:if test='%{trRadio=="C"}'>
			        	Company
			       </s:if> 		        	         
		       </td>
		      </tr>
		     </table>
		    </td>
		    <td width="15%">
		    	<label class="set" name="Accom" id="Accom1" ondblclick="callShowDiv(this);"><%=label.get("Accom")%></label> :
		    </td>
		    <td width="15%">
		     <table width="100%">
		      <tr>
		       <td>
			       <s:if test='%{accomRadio=="S"}'>
			        	Self
			       </s:if>
			       <s:if test='%{accomRadio=="C"}'>
			        	Company
			       </s:if>	       
		      </tr>
		     </table>
		    </td>
		   </tr>
		   
		   <tr>
		    <td width="15%">
		    	<label class="set" name="Locconv" id="Locconv1" ondblclick="callShowDiv(this);"><%=label.get("Locconv")%></label>  :
		    </td>
		    <td>
		     <table width="100%">
		      <tr>
		       <td>
			       	<s:if test='%{locRadio=="S"}'>
			        	Self
			       </s:if>
			       <s:if test='%{locRadio=="C"}'>
			        	Company
			       </s:if>			        
		       </td>
		      </tr>
		     </table>
		    </td>
		   </tr>
		  </table>
		 </td>
		</tr>
		</s:else>
		<!---------------------------------------- TOUR DETAILS [ENDS] ------------------------------------------>
		
		<!---------------------------------------- JOURNEY DETAILS [BEGINS] ------------------------------------>
		<tr>
		 <td>
		  <div id="div1" style="display:<%=trvlFlag.equals("on")?"block":"none" %>;" >
		   <table width="100%" class="formbg" border="0">
		    <tr>
		     <td colspan="3"><b>Journey Details</b></td>
		     <td colspan="4" align="right">
		     <s:if test='%{appStatus=="N" || appStatus=="B" || appStatus=="W" ||appStatus=="" }'>
		     	<s:submit value="  Add  " action="TravelApplication_addJourneyDtls" cssClass="add" theme="simple"  /> 
		     	<s:submit value="  Remove  " action="TravelApplication_removeJourneyDtls" cssClass="delete" theme="simple" onclick="return checkRemoveJourneyDtls();" />
		     </s:if>
		     <s:if test='%{appStatus=="A" || appStatus=="AC"}'>
		     		<!--<s:submit value="  Cancel  "  cssClass="cancel" theme="simple" onclick="return checkCancelJourneyDtls();" />
		     		-->
		     		<input type="button" value="  Cancel  " class="cancel" onclick="return checkCancelApplicationDtls();">
		     </s:if>
		     </td>
		    </tr>
		    <script>
		     function checkRemoveJourneyDtls(){
		       var count = 0; 
		     	for(i=1;i<document.getElementById('hJourCount').value;i++){
			        if(document.getElementById('chkJour'+i).checked){
			         	count++;
			         }
				 }
				 if(count==0){
				  		alert('Please select atleast one record to remove.');
				  		return false;
				 }else{
				 		
				        var conf = confirm('Do you really want to remove the record?');
				        if(!conf){
				        	
				        	for(i=1;i<document.getElementById('hJourCount').value;i++){
					        	document.getElementById('chkJour'+i).checked="";		         	
				 			}
				 			document.getElementById('chkJour').checked="";	
				        	return false; 
				        }
				        return true;
				        				 
				 }
				 
		     }
		     
		     function checkAllJourneyDtls(){
		     
		     		for(i=1;i<document.getElementById('hJourCount').value;i++){
		     			
				        if(document.getElementById('chkJour').checked){
				        
				         	if( !(document.getElementById('chkJour'+i).disabled==true) ){
					         	document.getElementById('chkJour'+i).checked='true';
					         	document.getElementById('jourFlag'+i).value="Y";
				         	}
				         	
				         }else{
				         	
				         	document.getElementById('chkJour'+i).checked='';
				         	document.getElementById('jourFlag'+i).value="N";
				         	
				         }
				         
			           }
			           
		     }
		     
		     function checkAJourney(chkId,flagId){
			              
			              if(document.getElementById(chkId).checked){
			              	document.getElementById(flagId).value="Y";
			              }else{
			                document.getElementById(flagId).value="N";
			              }
			              
		     }
		    
		    </script>
		    <tr>
		     <td class="formth" width="4%">
		      <b><label class="set" name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		     </td>
		     <td class="formth" width="20%">
		      <b><label class="set" name="Frplace" id="Frplace1" ondblclick="callShowDiv(this);"><%=label.get("Frplace")%></label></b>
		      <font color="red">*</font>
		     </td>
		     <td class="formth" width="20%">
		      <b><label class="set" name="Toplace" id="Toplace0"	ondblclick="callShowDiv(this);"><%=label.get("Toplace")%></label></b>
		      <font color="red">*</font>
		     </td>
		     <td class="formth" width="18%">
		      <b><label class="set" name="JMclass" id="JMclass" ondblclick="callShowDiv(this);"><%=label.get("JMclass")%></label></b>
		      <font color="red">*</font>
		     </td>
		     <td class="formth" width="18%">
		      <b><label class="set" name="Jourdate" id="Jourdate0" 	ondblclick="callShowDiv(this);"><%=label.get("Jourdate")%></label><br>(DD-MM-YYYY)</b> 
		      <font color="red">*</font>
		      </td>
		     <td class="formth" width="15%">
		      <b><label class="set" name="Timing" id="Timing" ondblclick="callShowDiv(this);"><%=label.get("Timing")%></label><br>(HH:MM)</b>
		     </td>
		     <!-- New/Sent Back applications -->
		     <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" || trvlApp.appStatus=="A" || trvlApp.appStatus=="AC"}'>
		     	<td class="formth" align="center" width="5%">
		     		<input type="checkbox" name="" id="chkJour" onclick="checkAllJourneyDtls();">
		     	</td>
		     </s:if>
		    </tr>
		    <%int i=1; %>
		    <s:iterator value="jourList">
		    <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" }'>
				     <tr>
				      <td  class="sortableTD" ><%=i %><input type="hidden" name="jourId" value="<s:property value="jourId" />"  /></td>
				      <td  class="sortableTD" align="center"><input type="text" name="frmPlace" size="15" id="frmPlace<%=i %>" value="<s:property value="frmPlace" />"></td>
				      <td  class="sortableTD" align="center"><input type="text" name="toPlace" size="15" id="toPlace<%=i %>" value="<s:property value="toPlace" />"></td>
				      <td class="sortableTD" align="center">		      	
				      	<input type="hidden" name="jourModeId" size="10" id="paraFrm_jourModeId<%=i %>" value="<s:property value="jourModeId" />">
				      	<input type="text" name="jourMode" size="10" id="paraFrm_jourMode<%=i %>"  value="<s:property value="jourMode" />" readonly="readonly">
				      	   <img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="setFieldId(<%=i %>,'TravelApplication_f9JourneyMode.action');"> 
				      </td>		      
				      <td align="center" class="sortableTD" align="center">
				      	<input type="text" name="jourDate" size="10" maxLength="10"  id="jourDate<%=i %>" value="<s:property value="jourDate" />" onkeypress="return numbersWithHiphen();">
				      	<a href="javascript:NewCal('jourDate<%=i%>','DDMMYYYY');">
					     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
						</a>
				      </td class="sortableTD">
				      <td class="sortableTD" align="center"><input type="text" name="jourTime" id="jourTime<%=i %>" size="5" maxlength="5" value="<s:property value="jourTime" />"  ></td>
				      <td align="center" class="sortableTD">
				        <input type="checkbox" name="journeychk" id="chkJour<%=i%>"  value="<s:property value='jourId' />@<s:property value='trvlApp.appId' />@<s:property value='trvlApp.appCode' />" onclick="checkAJourney('chkJour<%=i%>','jourFlag<%=i%>')">
				       <input type="hidden" name="jourFlag" id="jourFlag<%=i %>" value="N" />
				      </td>
				     </tr>
		     </s:if>
		     <s:else>
		     			<!-- SET BACKGROUND OF ROW TO RED WHERE A PARTICULAR JOURNEY IS CANCELLED -->
			     		<s:if test="%{jourStatus=='PC' || jourStatus=='CC' || jourStatus=='FC' || jourStatus=='AC'}">
							      <tr bgcolor="#FFC488" id="jourRow<%=i%>">
						</s:if>
						<s:else>
								  <tr id="jourRow<%=i%>">
						</s:else>
							      <td  class="sortableTD" ><%=i %></td>
							      <td  class="sortableTD" align="left"><s:property value="frmPlace" /></td>
							      <td  class="sortableTD" align="left"><s:property value="toPlace" /></td>
							      <td class="sortableTD" align="left">		      	
							      	<input type="hidden" name="jourModeId" size="10" id="paraFrm_jourModeId<%=i %>" value="<s:property value="jourModeId" />">
							      	<s:property value="jourMode" />		      	    
							      </td>		      
							      <td align="center" class="sortableTD" align="center">
							      	<s:property value="jourDate" />		      	
							      </td class="sortableTD">
							      <td class="sortableTD" align="center"><s:property value="jourTime" /></td>
							      <!-- New/Sent Back applications -->
							      <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" ||trvlApp.appStatus=="A" || trvlApp.appStatus=="AC"}'> 
								      <td align="center" class="sortableTD">
								      	<s:if test="%{jourStatus!='PC' && jourStatus!='CC'}">
							           	 <input type="checkbox" name="journeychk" id="chkJour<%=i%>"  value="<s:property value='jourId' />@<s:property value='trvlApp.appId' />@<s:property value='trvlApp.appCode' />" onclick="checkAJourney('chkJour<%=i%>','jourFlag<%=i%>')">
								       </s:if>
								       <s:else>
								       	 <input type="checkbox" disabled="disabled" name="journeychk" id="chkJour<%=i%>"   value="<s:property value='jourId' />@<s:property value='trvlApp.appId' />@<s:property value='trvlApp.appCode' />"  onclick="checkAJourney('chkJour<%=i%>','jourFlag<%=i%>')">
								       </s:else>
								       <input type="hidden" name="jourFlag" id="jourFlag<%=i %>" value="N" />
								      </td>
							      </s:if>
							     </tr>					   
		     </s:else>
		     <%i++; %>
		    </s:iterator>
		    <%if(i==1){ %>
		    	<tr>
		    	 <td colspan="7" align="center"><font color="red">No Data to display</font></td>
		    	</tr>
		    <%} %>
		    <input type="hidden" name="hJourCount" id="hJourCount" value="<%=i%>">
		    <s:hidden name="delJour" theme="simple" />
		   </table>
		  </div>
		 </td>
		</tr>
		<!---------------------------------------- JOURNEY DETAILS [ENDS] ------------------------------------>
		 
		<!---------------------------------------- LODGING DETAILS [BEGINS] ------------------------------------>
		 
		 <script>
					     function checkRemoveLodgingDtls(){
					       var count = 0; 
					     	for(i=1;i<document.getElementById('hLodgCount').value;i++){
						        if(document.getElementById('chkLodg'+i).checked){
						         	count++;
						         }
							 }
							 if(count==0){
							  		alert('Please select atleast one record to remove.');
							  		return false;
							 }else{
						 		
						        var conf = confirm('Do you really want to remove the record?');
						        if(!conf){
						        	
						        	for(i=1;i<document.getElementById('hLodgCount').value;i++){
						        		document.getElementById('chkLodg'+i).checked="";				         		
							 		}
							 		
							 		document.getElementById('chkLodg').checked="";		
						        
						        	return false; 
						        }
						        
						        return true;
						        
						 	}
					     }
					     
					     function checkAllLodgingDtls(){
					     		for(i=1;i<document.getElementById('hLodgCount').value;i++){
					     		
					     			if( !(document.getElementById('chkLodg'+i).disabled==true) ){
					     			
								        if(document.getElementById('chkLodg').checked){
								         	document.getElementById('chkLodg'+i).checked='true';
								         	document.getElementById('lodgFlag'+i).value="Y";
								         }else{
								            document.getElementById('chkLodg'+i).checked='';
								         	document.getElementById('lodgFlag'+i).value="N";
								         }
								         
							         }
							         else{
							         	document.getElementById('chkLodg'+i).checked='';
							         	document.getElementById('lodgFlag'+i).value="N";
							         }
							         
						           }
					     }
					     
					 function checkALodging(chkId,flagId){
					              
					              if(document.getElementById(chkId).checked){
					              	document.getElementById(flagId).value="Y";
					              }else{
					                document.getElementById(flagId).value="N";
					              }
					              
				     }   
				    </script>
		
		
		<s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" }'>
				<tr>
				 <td  width="100%">
				  <div id="div2" style="width=755;display:<%=accomFlag.equals("on")?"block":"none" %>">
				   <table width="100%" class="formbg">
				   <tr>
				     <td colspan="1"><b>Lodging Details</b></td> 
				     <td  align="right">
				     	<s:if test='%{appStatus=="N" || appStatus=="B" || appStatus=="W" ||appStatus=="" }'>
				     	
				     	<s:submit value="  Add  " action="TravelApplication_addLodgingDtls" cssClass="add" theme="simple"  /> 
				     	<s:submit value="  Remove  " action="TravelApplication_removeLodgingDtls" cssClass="delete" theme="simple" onclick="return checkRemoveLodgingDtls();" />
				     	</s:if>
				     	<s:if test='%{appStatus=="A" || appStatus=="AC"}'> 
		     				<!--<s:submit value="  Cancel  "  cssClass="cancel" theme="simple" onclick="return checkCancelLodgeDtls();" />
		     				--><input type="button" value="  Cancel  " class="cancel" onclick="return checkCancelLodgeDtls();">
		     			</s:if>
				     </td>
				    </tr>
				    <tr>
				     <td width="100%" colspan="2">
				     <div style="width:755;overflow:scroll">
				   <table width="100%"   border="0"> 		
				    <tr>
				     <td class="formth" >
				     	<b><label class="set" name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
				     </td>
				     <td class="formth"  >
				     	<b><label class="set" name="LodType" id="LodType" 	ondblclick="callShowDiv(this);"><%=label.get("LodType")%></label></b>
				     	 <font color="red">*</font>
				     </td>
				     <td class="formth" >
				     	<b><label class="set" name="RoomType" id="RoomType2" ondblclick="callShowDiv(this);"><%=label.get("RoomType")%></label></b>
				     	<font color="red">*</font></td>
				     <td class="formth" > 
				     	<b><label class="set" name="traCity" id="traCity" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label></b>
				     	<font color="red">*</font></td>
				     <td class="formth" >
				     	<b><label class="set" name="Prefloc" id="Prefloc" ondblclick="callShowDiv(this);"><%=label.get("Prefloc")%></label></b>
				     </td>
				     <td class="formth" >
				     	<b><label class="set" name="FrDate" id="FrDate" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label><br>(DD-MM-YYYY)</b>
				     	<font color="red">*</font> 
				     </td>
				     <td class="formth" >
				     	<b><label class="set" name="FrTime" id="FrTime" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label>(HH:MM)</b>
				     </td>
				     <td class="formth" >
				     	<b><label class="set" name="ToDate" id="ToDate" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label><br>(DD-MM-YYYY)</b><font color="red">*</font> 
				     </td>
				     <td class="formth" >
				     	<b><label class="set" name="ToTime" id="ToTime" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label>(HH:MM)</b>
				     </td>
				     <!-- New/Sent Back applications -->
				     <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" }'>
				     <td class="formth" align="center" >
				     	<input type="checkbox" name="" id="chkLodg" onclick="checkAllLodgingDtls();">
				     </td>
				     </s:if>
				     </tr>
				     <script>
							function checkRoomType(id,value){
								 
								 document.getElementById('fieldValue').value = value;
								 
								 if(document.getElementById('paraFrm_lodgType'+id).value==""){
									 	alert('Please select '+document.getElementById("LodType").innerHTML+'.');
									 	document.getElementById('paraFrm_lodgType'+id).focus();
									 	return false;
									}else{
									 	setFieldId(id,'TravelApplication_f9RoomType.action');
									 	
									}
								 
							}
							
							function clearRoomType(id){
								 document.getElementById('paraFrm_roomTypeId'+id).value="";
								 document.getElementById('paraFrm_roomType'+id).value="";
								 setFieldId(id,'TravelApplication_f9LodgingType.action');
							}
							
						</script>
				    <%int j=1; %>  
				    <s:iterator value="accomList">
				    <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" }'>
				     <tr>
				      <td  class="sortableTD" nowrap="nowrap"><%=j %><input type="hidden" name="lodgId" value="<s:property value="lodgId" />"  /></td>
				      <td class="sortableTD" nowrap="nowrap">
					      <input type="hidden" name="lodgTypeId" id="paraFrm_lodgTypeId<%=j %>" value="<s:property value="lodgTypeId" />">
					      <input type="text" name="lodgType" id="paraFrm_lodgType<%=j %>" size="10" value="<s:property value="lodgType" />" readonly="readonly">
					      <img src="../pages/images/recruitment/search2.gif" 
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="clearRoomType(<%=j%>);">
				      </td>
				      <td class="sortableTD" nowrap="nowrap">
				       <input type="hidden" name="roomTypeId" id="paraFrm_roomTypeId<%=j %>"    value="<s:property value="roomTypeId" />">
				       <input type="text" name="roomType" id="paraFrm_roomType<%=j %>" size="10" value="<s:property value="roomType" />" readonly="readonly">
				        <img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="return checkRoomType(<%=j %>,document.getElementById('paraFrm_lodgTypeId<%=j %>').value);">
						
				      </td>
				      <td class="sortableTD" nowrap="nowrap">
				       <input type="hidden" name="cityId" id="paraFrm_cityId<%=j %>" value="<s:property value="cityId" />">
				       <input type="text" name="city" id="paraFrm_city<%=j %>" size="10" maxlength="100" value="<s:property value="city" />" >		
				        <!--<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="setFieldId(<%=j %>,'TravelApplication_f9City.action');">       
				      --></td>
				      <td class="sortableTD" nowrap="nowrap">
				       <input type="text" name="prfrdLoc" size="10" maxlength="100" value="<s:property value="prfrdLoc" />">
				      </td>
				      <td class="sortableTD" nowrap="nowrap" >
				       <input type="text" name="frmDate" size="10" maxLength="10"  id="frmDate<%=j %>" value="<s:property value="frmDate" />" onkeypress="return numbersWithHiphen();">
				       <a href="javascript:NewCal('frmDate<%=j %>','DDMMYYYY');">
					     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
						</a>
				      </td>
				      <td class="sortableTD" nowrap="nowrap">
				       <input type="text" name="frmTime" size="5" maxlength="5" id="frmTime<%=j %>"  value="<s:property value="frmTime" />" >
				      </td>
				      <td class="sortableTD" nowrap="nowrap">
				       <input type="text" name="toDate" size="10" maxLength="10"  id="toDate<%=j %>" value="<s:property value="toDate" />" onkeypress="return numbersWithHiphen();">
				        <a href="javascript:NewCal('toDate<%=j %>','DDMMYYYY');">
					     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
						</a>
				      </td>
				      <td class="sortableTD" nowrap="nowrap">
				       <input type="text" name="toTime" size="5" maxlength="5"  id="toTime<%=j %>" value="<s:property value="toTime" />">
				      </td>
				      <td align="center" class="sortableTD" nowrap="nowrap">
				      	
				      	<input type="checkbox" name="lodge" id="chkLodg<%=j%>"  value= value="<s:property value='lodgId' />@<s:property value='trvlApp.appId' />@<s:property value='trvlApp.appCode' />" onclick="checkALodging('chkLodg<%=j%>','lodgFlag<%=j%>')">
						<input type="hidden" name="lodgFlag" id="lodgFlag<%=j %>" value="N" />
				      </td>		      
				     </tr>
				     <%j++; %>
				     </s:if>
				     <s:else>
					      <tr>
					      <td  class="sortableTD"><%=j %></td>
					      <td class="sortableTD" width="12%">
						      <s:property value="lodgType" />				      
					      </td>
					      <td class="sortableTD" width="12%">
					       <s:property value="roomType" />			        
					      </td>
					      <td class="sortableTD" width="12%">
					       <s:property value="city" />				             
					      </td>
					      <td class="sortableTD" width="15%">
					       <s:property value="prfrdLoc" />&nbsp; 
					      </td>
					      <td class="sortableTD" align="center">
					       <s:property value="frmDate" />			      
					      </td>
					      <td class="sortableTD" align="center"> 
					       <s:property value="frmTime" />&nbsp; 
					      </td>
					      <td class="sortableTD" align="center">
					       <s:property value="toDate" />			        
					      </td>
					      <td class="sortableTD" align="center"> 
					       <s:property value="toTime" />&nbsp;
					      </td> 			     	      
					     </tr>	
					     <%j++; %>	     
				     </s:else>		     
				    </s:iterator>
				    <%if(j==1){ %>
				    	<tr>
				    	 <td colspan="10" align="center"><font color="red">No Data to display</font></td>
				    	</tr>
				    <%} %>		    
				    <input type="hidden" name="hLodgCount" id="hLodgCount" value="<%=j%>">
				    <s:hidden name="delAccom" theme="simple" />
				   </table>
				   </div>
				   </td>
				   </tr>
				  </table>
				  </div>
				 </td> 
				</tr>
		</s:if>
		<s:else>
			<tr>
				 <td  width="100%">
				   <table width="100%" class="formbg">
				   <tr>
				     <td colspan="1"><b>Lodging Details</b></td>
				     <td  align="right">
				     	<!--<s:if test='%{appStatus=="A" || appStatus=="AC"}'>
		     				<input type="button" value="  Cancel  " class="cancel" onclick="return checkCancelLodgeDtls();">
		     			</s:if>-->
				     </td>
				    </tr>
				     <tr>
				     <td width="100%" colspan="2">
					<table width="100%"   border="0">
				    <tr>
				     <td class="formth" >
				     	<label class="set" name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
				     </td>
				     <td class="formth"  >
				     	<label class="set" name="LodType" id="LodType" 	ondblclick="callShowDiv(this);"><%=label.get("LodType")%></label>
				     	 <font color="red">*</font>
				     </td>
				     <td class="formth" >
				     	<label class="set" name="RoomType" id="RoomType2" ondblclick="callShowDiv(this);"><%=label.get("RoomType")%></label>
				     	<font color="red">*</font></td>
				     <td class="formth" > 
				     	<label class="set" name="traCity" id="traCity" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label>
				     	<font color="red">*</font></td>
				     <td class="formth" >
				     	<label class="set" name="Prefloc" id="Prefloc" ondblclick="callShowDiv(this);"><%=label.get("Prefloc")%></label>
				     </td>
				     <td class="formth" >
				     	<label class="set" name="FrDate" id="FrDate" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label><br>(DD-MM-YYYY)
				     	<font color="red">*</font> 
				     </td>
				     <td class="formth" >
				     	<label class="set" name="FrTime" id="FrTime" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label>(HH:MM)
				     </td>
				     <td class="formth" >
				     	<label class="set" name="ToDate" id="ToDate" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label><font color="red">*</font>(DD-MM-YYYY) 
				     </td>
				     <td class="formth" >
				     	<label class="set" name="ToTime" id="ToTime" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label>(HH:MM)
				     </td>
				     <!-- New/Sent Back applications -->
				     <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" || trvlApp.appStatus=="A" || trvlApp.appStatus=="AC"}'>
				     <td class="formth" align="center" >
				        <s:if test="%{lodgStatus!='PC' && lodgStatus!='CC'}">
				     		<input type="checkbox" name="" id="chkLodg" onclick="checkAllLodgingDtls();">
				     	</s:if>
				     </td>
				     </s:if>
				     </tr>
				    <%int j=1; %>
				    <s:iterator value="accomList">
					    <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" }'>
							     <tr>
							      <td  class="sortableTD" nowrap="nowrap"><%=j %><input type="hidden" name="lodgId" value="<s:property value="lodgId" />"  /></td>
							      <td class="sortableTD" nowrap="nowrap">
								      <input type="hidden" name="lodgTypeId" id="paraFrm_lodgTypeId<%=j %>" value="<s:property value="lodgTypeId" />">
								      <input type="text" name="lodgType" id="paraFrm_lodgType<%=j %>" size="10" value="<s:property value="lodgType" />" readonly="readonly">
								      <img src="../pages/images/recruitment/search2.gif" 
													class="iconImage" height="16" align="absmiddle" width="16"
													onclick="setFieldId(<%=j %>,'TravelApplication_f9LodgingType.action');">
							      </td>
							      <td class="sortableTD" nowrap="nowrap">
							       <input type="hidden" name="roomTypeId" id="paraFrm_roomTypeId<%=j %>"    value="<s:property value="roomTypeId" />">
							       <input type="text" name="roomType" id="paraFrm_roomType<%=j %>" size="10" value="<s:property value="roomType" />" readonly="readonly">
							        <img src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="16" align="absmiddle" width="16"
													onclick="return checkRoomType(<%=j %>,document.getElementById('paraFrm_lodgTypeId<%=j %>').value);">
									<script>
										function checkRoomType(id,value){
											 
											 document.getElementById('fieldValue').value = value;
											 
											 if(document.getElementById('paraFrm_lodgType'+id).value==""){
												 	alert('Please select '+document.getElementById("LodType").innerHTML+'.');
												 	document.getElementById('paraFrm_lodgType'+id).focus();
												 	return false;
												}else{
												 	setFieldId(id,'TravelApplication_f9RoomType.action');
												 	
												}
											 
										}
									</script>
							      </td>
							      <td class="sortableTD" nowrap="nowrap">
							       <input type="hidden" name="cityId" id="paraFrm_cityId<%=j %>" value="<s:property value="cityId" />">
							       <input type="text" name="city" id="paraFrm_city<%=j %>" size="10" maxlength="100" value="<s:property value="city" />" >		
							        <!--<img src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="18" align="absmiddle" width="18"
													onclick="setFieldId(<%=j %>,'TravelApplication_f9City.action');">       
							      --></td>
							      <td class="sortableTD" nowrap="nowrap">
							       <input type="text" name="prfrdLoc" size="10" maxlength="100" value="<s:property value="prfrdLoc" />">
							      </td>
							      <td class="sortableTD" nowrap="nowrap">
							       <input type="text" name="frmDate" size="10" maxLength="10"  id="frmDate<%=j %>" value="<s:property value="frmDate" />" onkeypress="return numbersWithHiphen();">
							       <a href="javascript:NewCal('frmDate<%=j %>','DDMMYYYY');">
								     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
									</a>
							      </td>
							      <td class="sortableTD" nowrap="nowrap">
							       <input type="text" name="frmTime" size="5" maxlength="5" id="frmTime<%=j %>"  value="<s:property value="frmTime" />" >
							      </td>
							      <td class="sortableTD" nowrap="nowrap">
							       <input type="text" name="toDate" size="10" maxLength="10"  id="toDate<%=j %>" value="<s:property value="toDate" />" onkeypress="return numbersWithHiphen();">
							        <a href="javascript:NewCal('toDate<%=j %>','DDMMYYYY');">
								     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
									</a>
							      </td>
							      <td class="sortableTD" nowrap="nowrap">
							       <input type="text" name="toTime" size="5" maxlength="5"  id="toTime<%=j %>" value="<s:property value="toTime" />">
							      </td> 
							      <td align="center" class="sortableTD" nowrap="nowrap">
							      	<input type="checkbox" name="" id="chkLodg<%=j%>" onclick="checkALodging('chkLodg<%=j%>','lodgFlag<%=j%>')">
							      	 <input type="hidden" name="lodgFlag" id="lodgFlag<%=j %>" value="N" />
							      </td>		      
							     </tr>
							     <%j++; %>
					     </s:if>
					     <s:else>
						      <!-- SET BACKGROUND OF ROW TO RED WHERE A PARTICULAR LODGE DTLS IS CANCELLED -->
			     		<s:if test="%{lodgStatus=='PC' || lodgStatus=='CC' || lodgStatus=='FC' || lodgStatus=='AC'}">
							      <tr bgcolor="#FFC488"  id="lodgRow<%=j%>">
						</s:if>
						<s:else>
								  <tr id="lodgRow<%=j%>">
						</s:else>
						      <td  class="sortableTD"><%=j %></td>
						      <td class="sortableTD" width="12%">
							      <s:property value="lodgType" />				      
						      </td>
						      <td class="sortableTD" width="12%">
						       <s:property value="roomType" />			        
						      </td>
						      <td class="sortableTD" width="12%">
						       <s:property value="city" />				             
						      </td>
						      <td class="sortableTD" width="15%">
						       <s:property value="prfrdLoc" />&nbsp; 
						      </td>
						      <td class="sortableTD" align="center">
						       <s:property value="frmDate" />			      
						      </td>
						      <td class="sortableTD" align="center"> 
						       <s:property value="frmTime" />&nbsp; 
						      </td>
						      <td class="sortableTD" align="center">
						       <s:property value="toDate" />			        
						      </td>
						      <td class="sortableTD" align="center">
						       <s:property value="toTime" />&nbsp;
						      </td>
						      <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" ||trvlApp.appStatus=="A" || trvlApp.appStatus=="AC"}'>
							      <s:if test="%{lodgStatus!='PC' && lodgStatus!='CC'}">
								      <td align="center" class="sortableTD" nowrap="nowrap">
								      	<input type="checkbox" name="lodge" id="chkLodg<%=j%>"   value="<s:property value='lodgId' />@<s:property value='trvlApp.appId' />@<s:property value='trvlApp.appCode' />" onclick="checkALodging('chkLodg<%=j%>','lodgFlag<%=j%>')">
								      	<input type="hidden" name="lodgFlag" id="lodgFlag<%=j %>" value="N" />
									  </td>
								  </s:if>
								  <s:else>
								  		<td align="center" class="sortableTD" nowrap="nowrap">
								      	<input type="checkbox" disabled="disabled" name="journeychk" id="chkLodg<%=j%>" onclick="checkALodging('chkLodg<%=j%>','lodgFlag<%=j%>')">
								      	<input type="hidden" name="lodgFlag" id="lodgFlag<%=j %>" value="N" />
									  </td>
								  </s:else>
							  </s:if>
							  							  
						     </tr>	
						     <%j++; %>	     
					     </s:else>		     
				    </s:iterator>
				    <%if(j==1){ %>
				    	<tr>
				    	 <td colspan="10" align="center"><font color="red">No Data to display</font></td>
				    	</tr>
				    <%} %>		    
				    <input type="hidden" name="hLodgCount" id="hLodgCount" value="<%=j%>">
				    <s:hidden name="delAccom" theme="simple" />
				   </table>
				</td>
 				</tr>
				</table>
			             </td>
			           </tr>
		</s:else>
		
		<!---------------------------------------- LODGING DETAILS [ENDS] ------------------------------------>
		
		<!---------------------------------------- LOCAL CONVEYANCE DETAILS [BEGINS] ------------------------------------>
		<tr>
		 <td>
		  <div id="div3" style="display:<%=convFlag.equals("on")?"block":"none" %>">
		   <table width="100%" class="formbg" border="0">
		    <tr>
		     <td colspan="3"><b>Local Conveyance Details</b></td>
		     <td colspan="7" align="right">
		     	<s:if test='%{appStatus=="N" || appStatus=="B" || appStatus=="W" ||appStatus=="" }'>
			     	<s:submit value="  Add  " action="TravelApplication_addConveyanceDtls" cssClass="add" theme="simple"  /> 
			     	<s:submit value="  Remove  " action="TravelApplication_removeConveyanceDtls" cssClass="delete" theme="simple" onclick="return checkRemoveConveyanceDtls();" />
		     	</s:if>
		     	<!--<s:if test='%{appStatus=="A" || appStatus=="AC"}'>
					<s:submit value="  Cancel  "  cssClass="cancel" theme="simple" onclick="return checkCancelConveyanceDtls();" />
					<input type="button" value="  Cancel  " class="cancel" onclick="return checkCancelConveyanceDtls();">
		     	</s:if>-->
		     </td>
		    </tr>
		    <script>
			     function checkRemoveConveyanceDtls(){
			       var count = 0;
			     	for(i=1;i<document.getElementById('hLocCount').value;i++){
				        if(document.getElementById('chkLoc'+i).checked){
				         	count++;
				         }
					 }
					 if(count==0){
					  		alert('Please select atleast one record to remove.');
					  		return false;
					 }else{
				 		
				        var conf = confirm('Do you really want to remove the record?');
				        if(!conf){
				        	for(i=1;i<document.getElementById('hLocCount').value;i++){
					        	document.getElementById('chkLoc'+i).checked="";
				        	}
				        	document.getElementById('chkLoc').checked="";
				        	return false; 
				 		}
				 		return true;
			     	}
			     }
			     
			     function checkAllConveyanceDtls(){
			     		for(i=1;i<document.getElementById('hLocCount').value;i++){
			     					     		
						        if(document.getElementById('chkLoc').checked){
						        
							        	if( !(document.getElementById('chkLoc'+i).disabled==true) ){
								         	document.getElementById('chkLoc'+i).checked='true';
								         	document.getElementById('locFlag'+i).value="Y";
								         }
						         	
						         }else{
						         	document.getElementById('chkLoc'+i).checked='';
						         	document.getElementById('locFlag'+i).value="N";
						         }
					         
				           }
			     }   
			     
			      function checkAConveyance(chkId,flagId){
			              
			              if(document.getElementById(chkId).checked){
			              	document.getElementById(flagId).value="Y";
			              }else{
			                document.getElementById(flagId).value="N";
			              }
			              
		    		 }     
		    </script>
		    <tr>
		     <td class="formth">
		     	<b><label class="set" name="sno" id="sno3" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="traCity" id="traCity1" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label></b><font color="red">*</font>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="TraDet" id="TraDet1" ondblclick="callShowDiv(this);"><%=label.get("TraDet")%></label></b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="locMedium" id="locMedium" ondblclick="callShowDiv(this);"><%=label.get("locMedium")%></label></b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="FrDate" id="FrDate3" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label><br>(DD-MM-YYYY)</b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="FrTime" id="FrTime3" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label><br>(HH:MM) </b> 
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="ToDate" id="ToDate3" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label><br>(DD-MM-YYYY)</b>
		     </td>
		     <td class="formth">
		     	 <b><label class="set" name="ToTime" id="ToTime4" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label> (HH:MM)</b>
		     </td> 
		     <!-- New/Sent Back applications --> 
		     <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" ||trvlApp.appStatus=="A" ||trvlApp.appStatus=="AC"}'>
			     <td class="formth" align="center">
			     	<input type="checkbox" name="" id="chkLoc" onclick="checkAllConveyanceDtls()">
			     </td>
			 </s:if> 
		    </tr>
		    <%int k=1; %>
		    <s:iterator value="locList">
		    <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" }'>
		     <tr>
		      <td class="sortableTD" nowrap="nowrap"><%=k%><input type="hidden" name="locId" value="<s:property value="locId" />"  /></td>
		      <td class="sortableTD" nowrap="nowrap"><input type="text" name="locCity" id="locCity<%=k %>" size="10" maxlength="50" value="<s:property value="locCity" />"></td>	      
		      <td class="sortableTD" nowrap="nowrap">
			       
			       <!--<textarea rows="2" cols="15" name="trvlDtls" id="paraFrm_trvlDtls<%=k%>"    onkeyup="callLength('trvlDtls<%=k %>','descCnt<%=k%>','100');"><s:property value="trvlDtls" /></textarea>-->
			       
			       <textarea rows="2" cols="15" name="trvlDtls" id="paraFrm_trvlDtls<%=k%>" onkeyup="textCounter(this,100)"   ><s:property value="trvlDtls" /></textarea>
			       
			       <img src="../pages/images/zoomin.gif" class="iconImage" height="12" align="absmiddle" width="12"
						 onclick="callWindow('paraFrm_trvlDtls<%=k%>','TraDet1','','paraFrm_descCnt<%=k %>',100);"> 
			       <input type="hidden" name="descCnt" id="paraFrm_descCnt<%=k %>" size="5" > 
		      </td>
		      <td class="sortableTD" nowrap="nowrap"><input type="text" name="medium" size="10" maxlength="100" value="<s:property value="medium" />" size="5" maxlength="50"></td>	
		      <td class="sortableTD" nowrap="nowrap">
		       <input type="text" size="10" maxLength="10"  name="locFrmDate" id="locFrmDate<%=k%>" value="<s:property value="locFrmDate" />" onkeypress="return numbersWithHiphen();" >
		        <a href="javascript:NewCal('locFrmDate<%=k %>','DDMMYYYY');">
			     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
				</a>
		      </td>
		      <td class="sortableTD" nowrap="nowrap"><input type="text" name="locFromTime" id="locFromTime<%=k %>"  size="5" maxlength="5" value="<s:property value="locFromTime" />" ></td>	
		      <td class="sortableTD" nowrap="nowrap">
		      	<input type="text"  size="10" maxLength="10"  name="locToDate" id="locToDate<%=k%>" value="<s:property value="locToDate" />" onkeypress="return numbersWithHiphen();" >
		      	 <a href="javascript:NewCal('locToDate<%=k %>','DDMMYYYY');">
			     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16" > 
				</a>	      
		      </td>	
		      <td class="sortableTD" nowrap="nowrap"><input type="text" name="locToTime" id="locToTime<%=k %>"  size="5" maxlength="5" value="<s:property value="locToTime" />" ></td>	
		      <td align="center" class="sortableTD">
	  		   <input type="checkbox" name="localconv" id="chkLoc<%=k%>"  value="<s:property value='locId' />@<s:property value='trvlApp.appId' />@<s:property value='trvlApp.appCode' />"  onclick="checkAConveyance('chkLoc<%=k%>','locFlag<%=k %>')" >
		      	 <input type="hidden" name="locFlag" id="locFlag<%=k%>" value="N" />
		      </td>
		     </tr>
		     <%k++; %>
		     </s:if>
		     <s:else>
		       <!-- SET BACKGROUND OF ROW TO RED WHERE A PARTICULAR JOURNEY IS CANCELLED -->
		     		<s:if test="%{locStatus=='PC' || locStatus=='CC' || locStatus=='FC' || locStatus=='AC'}">
						      <tr bgcolor="#FFC488" id="locRow<%=k%>">
					</s:if>
					<s:else>
							  <tr id="locRow<%=k%>">
					</s:else>
			      <td class="sortableTD"><%=k%></td>
			      <td class="sortableTD"><s:property value="locCity" />&nbsp;</td>	      
			      <td class="sortableTD"><s:property value="trvlDtls" />&nbsp;</td>	
			      <td class="sortableTD"><s:property value="medium" />&nbsp;</td>	
			      <td class="sortableTD" align="center">
			       <s:property value="locFrmDate" />&nbsp;			        
			      </td>
			      <td class="sortableTD" align="center"><s:property value="locFromTime" />&nbsp;</td>	
			      <td class="sortableTD" align="center">
			      	<s:property value="locToDate" />&nbsp;			      	 	      
			      </td>	
			      <td class="sortableTD" align="center"><s:property value="locToTime" />&nbsp;</td>				      
				  
			      <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" ||trvlApp.appStatus=="A" ||trvlApp.appStatus=="AC"}'>			      
			      <s:if test="%{locStatus!='PC' && locStatus!='CC'}">
				       <td align="center" class="sortableTD">
		  		   			<input type="checkbox" name="localconv" id="chkLoc<%=k%>"  value="<s:property value='locId' />@<s:property value='trvlApp.appId' />@<s:property value='trvlApp.appCode' />"  onclick="checkAConveyance('chkLoc<%=k%>','locFlag<%=k %>')" >
		  		   			<input type="hidden" name="locFlag" id="locFlag<%=k%>" value="N" />
			      	   </td>
		      	   </s:if>
		      	   <s:else>
		      	   	   <td align="center" class="sortableTD">
		  		   			<input type="checkbox" disabled="disabled" name="localconv" id="chkLoc<%=k%>"  value="<s:property value='locId' />@<s:property value='trvlApp.appId' />@<s:property value='trvlApp.appCode' />"  onclick="checkAConveyance('chkLoc<%=k%>','locFlag<%=k %>')" >
		  		   			<input type="hidden" name="locFlag" id="locFlag<%=k%>" value="N" />
			      	   </td>
		      	   </s:else>
		      	   </s:if>
		      	   
			     </tr>
			      <%k++; %> 		     
		     </s:else>		    
		    </s:iterator>
		    <%if(k==1){ %>
		    	<tr>
		    	 <td colspan="9" align="center"><font color="red">No Data to display</font></td>
		    	</tr>
		    <%} %>	
		    <input type="hidden" name="hLocCount" id="hLocCount" value="<%=k%>">
		    <s:hidden name="delLoc" theme="simple" />
		   </table>
		  </div>
		 </td> 
		</tr>
		<!---------------------------------------- LOCAL CONVEYANCE DETAILS [ENDS] ------------------------------------>
		
		<s:if test='appStatus=="A" || appStatus=="CC" || appStatus=="PC"'>
			<tr>
				<td>
				<table width="100%">
					<tr>
						<td width="8%"><b>Note : -</b></td>
						<td width="92%"><input type="text" disabled="disabled" style="background-color: #FFC488 ;height:15px;width: 15px" >&nbsp;&nbsp;  Indicates records either pending for cancellation or cancelled.</td>
					</tr>		
				</table>
				</td>
			</tr>
		</s:if>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="85%" colspan="2"> 
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="15%" align="right">
						<b>Page 2 of 3</b>
					</td>
				</tr>
			</table>
			</td>
		</tr>
</table>
</s:form>
<script>

	
	function cancelFun(){
		var conf = confirm('Do you really want to cancel the application?');
		if(conf){
		 	document.getElementById('paraFrm').action="TravelApplication_cancelApplication.action";
		  	document.getElementById('paraFrm').submit();
	  	}else{
	  	
	  	    return false;
	  	}
	  	
 	} 
	
	//Travel Apllication withdrawal functionality 
	function withdrawFun(){
    			var conf = confirm('Do you really want to withdraw the application?');
    	
    			if(conf){
    	    		document.getElementById('paraFrm').action="TravelApplication_withdraw.action";
	    			document.getElementById('paraFrm').submit();
    			}else{
    	    		return false;
    			}
    	
   	}
	
	 function checkCancelApplicationDtls()
	 {
	// alert('enter');
	 var count = 0;
	
		     	for(i=1;i<document.getElementById('hJourCount').value;i++){
			        if(document.getElementById('chkJour'+i).checked){
			         	count++;
			         }
				 }	 
				 
				 for(i=1;i<document.getElementById('hLodgCount').value;i++){
			        if(document.getElementById('chkLodg'+i).checked){
			         	count++;
			         }
				 }	 
				 
				 for(i=1;i<document.getElementById('hLocCount').value;i++){
			        if(document.getElementById('chkLoc'+i).checked){
			         	count++;
			         }
				 }	
				 
				 			 
				// alert(count+"--count");
				 if(count==0){
				  		alert('Please select atleast one record to cancel.');
				  		return false;
				 }		
				 else{
				 		
				        var conf = confirm('Do you really want to cancel the record?');
				        var appType=document.getElementById('paraFrm_hAppFor').value;
				        //alert('---'+appType+'----');
				        if(conf)
				        {
				           //alert('in');
				           calAjaxForCancel('TravelApplication_cancelApplicationDtls.action?appType='+appType,'paraFrm');
				             //alert('in ---------');
				             
				             //document.getElementById('paraFrm').action="TravelApplication_getApprovedList.action";
				             //document.getElementById('paraFrm').submit();
				             
				              
				             for(i=1;i<document.getElementById('hJourCount').value;i++){
								if(document.getElementById('chkJour'+i).checked){
								    //document.getElementById('locRow'+i).bgcolor="#FFC488";
								    document.getElementById('jourRow'+i).style.background="#FFC488";
								    document.getElementById('chkJour'+i).checked="";
								    document.getElementById('chkJour'+i).disabled="true";
								    document.getElementById('chkJour').checked="";
									count++;
								 }
  							 }
  							 
  							  for(i=1;i<document.getElementById('hLodgCount').value;i++){
								if(document.getElementById('chkLodg'+i).checked){
							   	 //document.getElementById('locRow'+i).bgcolor="#FFC488";
							   	 document.getElementById('lodgRow'+i).style.background="#FFC488";
							   	 document.getElementById('chkLodg'+i).checked="";
							   	 document.getElementById('chkLodg'+i).disabled="true";
							   	 document.getElementById('chkLodg').checked="";
									count++;
							 	}
							}
							
							
							
							 for(i=1;i<document.getElementById('hLocCount').value;i++){
							        if(document.getElementById('chkLoc'+i).checked){
							            //document.getElementById('locRow'+i).bgcolor="#FFC488";
							            document.getElementById('locRow'+i).style.background="#FFC488";
							            document.getElementById('chkLoc'+i).checked="";
							            document.getElementById('chkLoc'+i).disabled="true";
							            document.getElementById('chkLoc').checked="";
							         	count++;
							         }
				     		  	  }
  							 
  							 
  							 
  							 
  							 alert('Application details cancelled successfully.');
  							 
				             
				       }else{
				        	//alert('else');
				        	for(i=1;i<document.getElementById('hJourCount').value;i++){
					        	document.getElementById('chkJour'+i).checked="";		         	
				 			}
				 			for(i=1;i<document.getElementById('hLodgCount').value;i++){
					        	document.getElementById('chkLodg'+i).checked="";		         	
				 			}
				 			for(i=1;i<document.getElementById('hLocCount').value;i++){
					        	document.getElementById('chkLoc'+i).checked="";		         	
				 			}
				 			document.getElementById('chkJour').checked="";
				 			document.getElementById('chkLodg').checked="";	 
				 			document.getElementById('chkLoc').checked="";	 	    
				        	return false; 
				        }
				       
				        				 
				 }
	  
	 	return false;
	 
	 }  
	 
	 function checkCancelLodgeDtls(){
	 	   
	       var count = 0; 
	     	for(i=1;i<document.getElementById('hLodgCount').value;i++){
		        if(document.getElementById('chkLodg'+i).checked){
		         	count++;
		         }
			 }
			 
			 if(count==0){
			  		alert('Please select atleast one record to cancel.');
			  		return false;
			 }else{
		 		
		        var conf = confirm('Do you really want to cancel the record?');
		        
		        if(conf)
		        {
		        	
		        	
		        	
		        var appType=document.getElementById('paraFrm_hAppFor').value;
	               //alert('---'+appType);
		        calAjaxForLodgeCancel('TravelApplication_cancelLodgeDtls.action?appType='+appType,'paraFrm');
		        
		         
	             //document.getElementById('paraFrm').action="TravelApplication_getApprovedList.action";
	             //document.getElementById('paraFrm').submit();
		        
			        for(i=1;i<document.getElementById('hLodgCount').value;i++){
							if(document.getElementById('chkLodg'+i).checked){
							    //document.getElementById('locRow'+i).bgcolor="#FFC488";
							    document.getElementById('lodgRow'+i).style.background="#FFC488";
							    document.getElementById('chkLodg'+i).checked="";
							    document.getElementById('chkLodg'+i).disabled="true";
							    document.getElementById('chkLodg').checked="";
								count++;
							 }
						}
					
					alert('Lodging details cancelled successfully.');
		        	
		        }
		        	
			        else{
			        	
			        	for(i=1;i<document.getElementById('hLodgCount').value;i++){
			        		document.getElementById('chkLodg'+i).checked="";				         		
				 		}
				 		
				 		document.getElementById('chkLodg').checked="";		
			        
			        	return false; 
			        }
			        //return true;
			        
			 }
		}
		
     function checkCancelConveyanceDtls(){
	       var count = 0;
	     	for(i=1;i<document.getElementById('hLocCount').value;i++){
		        if(document.getElementById('chkLoc'+i).checked){
		         	count++;
		         }
			 }
			 if(count==0){
			  		alert('Please select atleast one record to cancel.');
			  		return false;
			 }else{
		 		
		        var conf = confirm('Do you really want to cancel the record?');
		        
		        
		        		  if(conf)		        
		            	  {
		            	  		  
			                  var appType=document.getElementById('paraFrm_hAppFor').value;
						      //alert('in');
						      calAjaxForLocalConvCancel('TravelApplication_cancelConveyanceDtls.action?appType='+appType,'paraFrm');
						      //alert('in ---------');
			        		  
			        		  
		             		  //document.getElementById('paraFrm').action="TravelApplication_getApprovedList.action";
		                      //document.getElementById('paraFrm').submit();
			        		  
			        		   for(i=1;i<document.getElementById('hLocCount').value;i++){
							        if(document.getElementById('chkLoc'+i).checked){
							            //document.getElementById('locRow'+i).bgcolor="#FFC488";
							            document.getElementById('locRow'+i).style.background="#FFC488";
							            document.getElementById('chkLoc'+i).checked="";
							            document.getElementById('chkLoc'+i).disabled="true";
							            document.getElementById('chkLoc').checked="";
							         	count++;
							         }
				     		  	  }
				     		  	  
				     		  	  alert('Conveyance details cancelled successfully.');
			        		  
			        		  
		             	 }
		        
		        else{
		        	for(i=1;i<document.getElementById('hLocCount').value;i++){
			        	document.getElementById('chkLoc'+i).checked="";
		        	}
		        	document.getElementById('chkLoc').checked="";
		        	return false; 
		 		}
		 		//return true;
	     	}
	     }
		
	 
	 function calAjaxForCancel(url,nameOfForm) {
    	//alert('url---------'+url+'---'+nameOfForm);
   //formName = nameOfForm;
    	try {
    	url=url+getForm(nameOfForm);
      		
      		//alert('url pattern--'+url);
     	} catch (e) {
	     alert('bean problem');
     	}
    	//Do the Ajax call
   	 	if (window.XMLHttpRequest) { // Non-IE browsers
      		req = new XMLHttpRequest();
      
     // req.onreadystatechange = processStateChange;
      
    		//alert('req12--'+req);
      		try {
      			//alert('url -'+url);
      			req.open("POST", url, true);
      			//alert('he1'); //was get 
      		} catch (e) {
        		alert("Problem Communicating with Server\n"+e);
      		}
      		req.send(null);
     	 	//alert('cc');
    	} else if (window.ActiveXObject) { // IE
      		//alert('process IE');
      		req = new ActiveXObject("Microsoft.XMLHTTP");
      		if (req) {
        		req.onreadystatechange = processStateChange;    
        		//alert('url in ie -'+url);    
        		req.open("POST", url, true);
        		req.send();
      	}
    } 
    //alert('last');    
    return false;
  }
	 
 function getForm(formName)
  {
	try
	{
	//alert('formName'+formName);
		var returnString = "";
		var formElements = document.forms[formName].elements;
		returnString='&journeyName=';
		for (var i = formElements.length - 1; i >= 0; i--)
 		{
	 		
	 		if(formElements[i].name=='journeychk')
	 		{ 	//alert('value is---'+formElements[i].value);	
	 		if(formElements[i].checked){
	 		
	 		 		returnString+=formElements[i].value+',';
	 		 		//alert('returnString--'+returnString);
	 		} 
	 		 }
 			
 		}
 		returnString+='&accomName=';
 		for (var i = formElements.length - 1; i >= 0; i--)
 		{
	 		
	 		if(formElements[i].name=='lodge')
	 		{ 	//alert('value is---'+formElements[i].value);	
	 		if(formElements[i].checked){
	 		
	 		 		returnString+=formElements[i].value+',';
	 		 		//alert('returnString--'+returnString);
	 		} 
	 		 }
 			
 		}
 		
 		returnString+='&localName=';
 		for (var i = formElements.length - 1; i >= 0; i--)
 		{
	 		
	 		if(formElements[i].name=='localconv')
	 		{ 	//alert('value is---'+formElements[i].value);	
	 		if(formElements[i].checked){
	 		
	 		 		returnString+=formElements[i].value+',';
	 		 		//alert('returnString--'+returnString);
	 		} 
	 		 }
 			
 		}
 		
	}
	catch(e)
	{
		alert(e.description);
	}
	//alert('returnString'+returnString);
	
	return returnString;
	} 
	 
	/*function calAjaxForLocalConvCancel(url,nameOfForm) {
    	
    	//alert('url---------'+url);
   //formName = nameOfForm;
   	    
    	try {
    		
    		url=url+'&local= '+getLocalForm(nameOfForm);      		
      		//alert('url pattern--'+url);
      		
     	}catch (e) {
	     alert('bean problem');
     	}
     	
    	//Do the Ajax call
   	 	if (window.XMLHttpRequest) { // Non-IE browsers
      		req = new XMLHttpRequest();
      
     // req.onreadystatechange = processStateChange;
      
    		//alert('req12--'+req);
      		try {
      			//alert('url -'+url);
      			req.open("POST", url, true);
      			//alert('he1'); //was get 
      		} catch (e) {
        		alert("Problem Communicating with Server\n"+e);
      		}
      		req.send(null);
     	 	//alert('cc');
    	} else if (window.ActiveXObject) { // IE
      		//alert('process IE');
      		req = new ActiveXObject("Microsoft.XMLHTTP");
      		if (req) {
        		req.onreadystatechange = processStateChange;    
        		//alert('url in ie -'+url);    
        		req.open("POST", url, true);
        		req.send();
      	}
    } 
    //alert('last');    
    return false;
  }
   
   function getLocalForm(formName)
{
	try
	{
	//alert('formName'+formName);
		var returnString = "";
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--)
 		{
	 		
	 		if(formElements[i].name=='localconv' || formElements[i].name=='localconv')
	 		{ 	//alert('value is---'+formElements[i].value);	
	 		if(formElements[i].checked){
	 		 		returnString+=formElements[i].value+',';
	 		 		//alert('returnString--'+returnString);
	 		} 
	 		 }
 			
 		}
	}
	catch(e)
	{
		alert(e.description);
	}
	//alert('out of try');
	return returnString;
	}
	
	//for lodge Details
	
	function  calAjaxForLodgeCancel(url,nameOfForm) {
    	//alert('url---------'+url);
   //formName = nameOfForm;
    	try {
    	url=url+'&lodgename= '+getLodgeForm(nameOfForm);
      		
      		//alert('url pattern--'+url);
     	} catch (e) {
	     alert('bean problem');
     	}
    	//Do the Ajax call
   	 	if (window.XMLHttpRequest) { // Non-IE browsers
      		req = new XMLHttpRequest();
      
     // req.onreadystatechange = processStateChange;
      
    		//alert('req12--'+req);
      		try {
      			//alert('url -'+url);
      			req.open("POST", url, true);
      			//alert('he1'); //was get 
      		} catch (e) {
        		alert("Problem Communicating with Server\n"+e);
      		}
      		req.send(null);
     	 	//alert('cc');
    	} else if (window.ActiveXObject) { // IE
      		//alert('process IE');
      		req = new ActiveXObject("Microsoft.XMLHTTP");
      		if (req) {
        		req.onreadystatechange = processStateChange;    
        		//alert('url in ie -'+url);    
        		req.open("POST", url, true);
        		req.send();
      	}
    } 
    //alert('last');    
    return false;
  }
  
  
   function getLodgeForm(formName)
{
	try
	{
	//alert('formName'+formName);
		var returnString = "";
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--)
 		{
	 		
	 		if(formElements[i].name=='lodge' || formElements[i].name=='lodge')
	 		{ 	//alert('value is---'+formElements[i].value);	
	 		if(formElements[i].checked){
	 		 		returnString+=formElements[i].value+',';
	 		 		//alert('returnString--'+returnString);
	 		} 
	 		 }
 			
 		}
	}
	catch(e)
	{
		alert(e.description);
	}
	//alert('out of try');
	return returnString;
	}*/
  
	 
	 
	 
	 
	// Method to set field id, so that the action could retrieve the 
	// value for using it in f9 method
 	function textCounter(field,  maxlimit) {
		
		if (field.value.length > maxlimit){
			//alert('Maximum characters allowed for comments are '+maxlimit);
			field.value = field.value.substring(0, maxlimit);
			//field.focus;
			return false;
		}
		return true;
	}
 	
 	
 	function setFieldId(id,action){
	        document.getElementById('fieldId').value=id;
	        callsF9(500,325,action);
	}


 function chkAdd(){
 		
 	  if(document.getElementById('paraFrm_hAppFor').value=="S"){//SELF
 	  	
 	   	if(document.getElementById('paraFrm_empName').value==""){
		   alert('Please select an employee, to add to the list.');
		   document.getElementById('paraFrm_empName').focus();
		   return false;
	  	}else{
	  	   document.getElementById('paraFrm').action="TravelApplication_addEmployee.action";
	  	   document.getElementById('paraFrm').submit();
	  	}
	  	
 	  }if(document.getElementById('paraFrm_hAppFor').value=="G"){//GUEST 	   	
 	   	
 	   	if(document.getElementById('paraFrm_guestName').value==""){
		   alert('Please enter guest details, to add to the list.');
		   document.getElementById('paraFrm_guestName').focus();
		   return false;
	  	}else{
	  	   document.getElementById('paraFrm').action="TravelApplication_addGuest.action";
	  	   document.getElementById('paraFrm').submit();
	  	}
	  	
 	   	
 	  }
	  
  
 }
 
 function chkRemove(){
  alert('Add code for remove....');
 }
 
 function saveFun(){
  
  if(!validateTourDtls()){
    return false;
    
  }if(!validateJoureyDtls()){
    return false;
    
  }if(!validateLodgingDtls()){
    return false;
  }if(!validateConveyanceDtls()){
	    return false;
  }if(document.getElementById('hJourCount').value=="1"
		&& document.getElementById('hLodgCount').value=="1"
		&& document.getElementById('hLocCount').value=="1"
		){
		   alert('Please provide details for any one among\nTravel Arrangement/Accommodation/Local Conveyance .');
		   return false;
		   
  	}
  	
  for(var i = 0; i < document.all.length; i++) {
  	if(document.all[i].id == 'save') {
  		//alert(document.all[i].id);
  		document.getElementById('save').disabled=true;
  	}
  }
  document.getElementById('paraFrm').action="TravelApplication_saveTourDtls.action";
  document.getElementById('paraFrm').submit();
  
  
  
 }
 
  function saveandnextFun(){
  	  
	  if(!validateTourDtls()){
	    return false;
	    
	  }if(!validateJoureyDtls()){
	    return false;
	    
	  }if(!validateLodgingDtls()){
	    return false;
	  }if(!validateConveyanceDtls()){
	    return false;
	  }if(document.getElementById('hJourCount').value=="1"
		&& document.getElementById('hLodgCount').value=="1"
		&& document.getElementById('hLocCount').value=="1"
		){
		   alert('Please provide details for any one among\nTravel Arrangement/Accommodation/Local Conveyance .');
		   return false;
		   
  	  }
	  
  	document.getElementById('paraFrm').action="TravelApplication_saveAndNextTourDtls.action";
  	document.getElementById('paraFrm').submit();
  	
 }
 
  function saveandpreviousFun(){
    
   	if(!validateTourDtls()){
    	return false;
    	
  	}if(!validateJoureyDtls()){
    	return false;
    	
  	}if(!validateLodgingDtls()){
    	return false;
    	
  	}if(!validateConveyanceDtls()){
	    return false;
	    
	}if(document.getElementById('hJourCount').value=="1"
		&& document.getElementById('hLodgCount').value=="1"
		&& document.getElementById('hLocCount').value=="1"
		){
		   alert('Please provide details for any one among\nTravel Arrangement/Accommodation/Local Conveyance .');
		   return false;
		   
  	}
    
	  document.getElementById('paraFrm').action="TravelApplication_saveAndPreviousTourDtls.action";
	  document.getElementById('paraFrm').submit();
    
 }
 
 function nextFun(){
  document.getElementById('paraFrm').action="TravelApplication_nextTourDtls.action";
  document.getElementById('paraFrm').submit();
  
 } 
 
 function previousFun(){
  document.getElementById('paraFrm').action="TravelApplication_edit.action";
  document.getElementById('paraFrm').submit();
  
 } 
 
 function validateTourDtls(){
  
   if(trim(document.getElementById('paraFrm_startDate').value)==""){
	    alert('Please enter '+document.getElementById("Trastdate").innerHTML+'');
	    document.getElementById('paraFrm_startDate').focus();
	    return false;
	    
   }if(!validateDate('paraFrm_startDate',"Trastdate")){
		document.getElementById('paraFrm_startDate').focus();
		return false;
   	
   }if(trim(document.getElementById('paraFrm_endDate').value)==""){    
   	    alert('Please enter '+document.getElementById("Traenddate").innerHTML+'');
	    document.getElementById('paraFrm_endDate').value="";
	    document.getElementById('paraFrm_endDate').focus();
	    return false;
	    
   }if(!validateDate('paraFrm_endDate',"Traenddate")){ 
		document.getElementById('paraFrm_endDate').focus();
		return false;
   	
   }if(!dateDifferenceEqual(document.getElementById('paraFrm_startDate').value, document.getElementById('paraFrm_endDate').value, 'paraFrm_startDate', 'Trastdate', 'Traenddate')){
	      document.getElementById('paraFrm_startDate').focus();
	      return false;
					      
   }if(trim(document.getElementById('paraFrm_trvlReqName').value)==""){    
	    alert('Please enter '+document.getElementById("TraReqname").innerHTML+'');
	    document.getElementById('paraFrm_trvlReqName').value="";
	    document.getElementById('paraFrm_trvlReqName').focus();
	    return false;
	    
   }if(trim(document.getElementById('paraFrm_purpose').value)==""){    
	    alert('Please select '+document.getElementById("Trapurpose").innerHTML+'');
	    document.getElementById('paraFrm_purpose').focus();
	    return false;
	    
   }if(trim(document.getElementById('paraFrm_trvlType').value)==""){    
	    alert('Please select '+document.getElementById("TravelType").innerHTML+'');
	    return false;
	    
   } 
  return true;
 }
 
 function validateJoureyDtls(){
  	  
	  
	  for(i=1;i<document.getElementById('hJourCount').value;i++){
	  	  
		  var fieldNameStart = ["paraFrm_startDate","paraFrm_endDate","jourDate"+i]; 
		  var lableNameStart = ["Trastdate","Traenddate","Jourdate0"];
	   
		   if(trim(document.getElementById('frmPlace'+i).value)==""){
			     alert('Please enter '+document.getElementById("Frplace1").innerHTML+'');
			     document.getElementById('frmPlace'+i).value="";
			     document.getElementById('frmPlace'+i).focus();
			     return false;
		    
		   }else if(trim(document.getElementById('toPlace'+i).value)==""){
			     alert('Please enter '+document.getElementById("Toplace0").innerHTML+'');			     
			     document.getElementById('toPlace'+i).value="";
			     document.getElementById('toPlace'+i).focus();
			     return false;
		    
		   }else if(trim(document.getElementById('paraFrm_jourMode'+i).value)==""){
			    alert('Please select '+document.getElementById("JMclass").innerHTML+'');
			    document.getElementById('paraFrm_jourMode'+i).focus();
		    	return false;
		    
		   }else if(trim(document.getElementById('jourDate'+i).value)==""){
			    alert('Please enter/select '+document.getElementById("Jourdate0").innerHTML+'');
			    document.getElementById('jourDate'+i).focus();
		    	return false;
		    	
		   }else if(!validateDate('jourDate'+i,'Jourdate0')){ 
				document.getElementById('jourDate'+i).focus();
				return false;
   				
   			}else if(!dateBetweenTwoDates(fieldNameStart, lableNameStart)){
   				document.getElementById('jourDate'+i).focus();
				return false;
				
			}else if(!validateTime('jourTime'+i,'Timing')){ 
				document.getElementById('jourTime'+i).focus();
				return false;
   				
   			}
	   
	  }
  return true;
 }
 
 function validateLodgingDtls(){
  	
  	
   for(i=1;i<document.getElementById('hLodgCount').value;i++){
	   	  
	      var fieldNameStart = ["paraFrm_startDate","paraFrm_endDate","frmDate"+i];
   		  var lableNameStart = ["Trastdate","Traenddate","FrDate"];	      
	      var fieldNameStart1 = ["paraFrm_startDate","paraFrm_endDate","toDate"+i];
   		  var lableNameStart1 = ["Trastdate","Traenddate","ToDate"];
   		  
   		  
   		  
	      
		   if(trim(document.getElementById('paraFrm_lodgType'+i).value)==""){
			    alert('Please select '+document.getElementById("LodType").innerHTML+'');
			    document.getElementById('paraFrm_lodgType'+i).focus();
			    return false;
		    	
		   }else if(trim(document.getElementById('paraFrm_roomType'+i).value)==""){
			    alert('Please select '+document.getElementById("RoomType2").innerHTML+'');
			    document.getElementById('paraFrm_roomType'+i).focus();
			    return false;
		    	
		   }else if(trim(document.getElementById('paraFrm_city'+i).value)==""){
			    alert('Please enter '+document.getElementById("traCity").innerHTML+'.');
			    document.getElementById('paraFrm_city'+i).value="";
			    document.getElementById('paraFrm_city'+i).focus();
			    return false;
		    
		   }else if(trim(document.getElementById('frmDate'+i).value)==""){
			    alert('Please select/enter '+document.getElementById("FrDate").innerHTML+'');
			    document.getElementById('frmDate'+i).focus();
			    return false;
		    
		   }else if(!validateDate('frmDate'+i,'FrDate')){ 
				document.getElementById('frmDate'+i).focus();
				return false;
   	
   			}else if(trim(document.getElementById('toDate'+i).value)==""){
			    alert('Please select/enter '+document.getElementById("ToDate").innerHTML+'');
			    document.getElementById('toDate'+i).focus();
			    return false;
		    	
		   }else if(!validateDate('toDate'+i,'ToDate')){ 
				document.getElementById('toDate'+i).focus();
				return false;
   				
   		   }else if(!dateDifferenceEqual(document.getElementById('frmDate'+i).value, document.getElementById('toDate'+i).value, 'frmDate'+i, 'FrDate', 'ToDate')){
		      	document.getElementById('frmDate'+i).focus();
		      	return false;
				
   			}else if(!dateBetweenTwoDates(fieldNameStart, lableNameStart)){
   				document.getElementById('frmDate'+i).focus();
				return false;
				
			}else if(!dateBetweenTwoDates(fieldNameStart1, lableNameStart1)){
   				document.getElementById('toDate'+i).focus();
				return false;
				
			}else if(!validateTime('frmTime'+i,'FrTime')){
				document.getElementById('frmTime'+i).focus();
				return false;
				
			}else if(!validateTime('toTime'+i,'ToTime')){
				document.getElementById('toTime'+i).focus();
				return false;
				
			}if(trim(document.getElementById('frmTime'+i).value)!=""
				&& trim(document.getElementById('toTime'+i).value)!=""
				&& (document.getElementById('frmTime'+i).value!="" && document.getElementById('toTime'+i).value!="")
				&& (document.getElementById('frmDate'+i).value== document.getElementById('toDate'+i).value)
			){
					var frmTime = document.getElementById('frmTime'+i).value;
					var toTime = document.getElementById('toTime'+i).value;
					
					if(!timeDifference(frmTime,toTime,'frmTime'+i,'FrTime','ToTime')){
						return false;
					}
					
			}
	   
	  }
  return true;
  
 }
 
  function validateConveyanceDtls(){
  
	   for(i=1;i<document.getElementById('hLocCount').value;i++){
		  
		  var fieldNameStart = ["paraFrm_startDate","paraFrm_endDate","locFrmDate"+i];
   		  var lableNameStart = ["Trastdate","Traenddate","FrDate3"];	      
	      var fieldNameStart1 = ["paraFrm_startDate","paraFrm_endDate","locToDate"+i];
   		  var lableNameStart1 = ["Trastdate","Traenddate","ToDate"];
   		  
   		  
		   	
		   	if(eval(document.getElementById('paraFrm_descCnt'+i).value)<0){
		   			//alert('Please select/enter '+document.getElementById("ToDate").innerHTML+'');
		   	        alert('Maximum length of '+document.getElementById("TraDet1").innerHTML+' is 100');
		   	        document.getElementById('paraFrm_trvlDtls'+i).focus();
		   	        return false;
		   	}if(trim(document.getElementById('locCity'+i).value)==""){
		   			alert('Please enter '+document.getElementById("traCity1").innerHTML+'.');
		   			document.getElementById('locCity'+i).value="";
		   			document.getElementById('locCity'+i).focus();
		   			return false;
		   	}if(!validateDate('locFrmDate'+i,'FrDate')){
					document.getElementById('locFrmDate'+i).focus();
					return false;
	   				
	   		}else if(!dateBetweenTwoDates(fieldNameStart, lableNameStart)){
	   				document.getElementById('locFrmDate'+i).focus();
					return false;
					
			}if(!validateDate('locToDate'+i,'ToDate')){
					document.getElementById('locToDate'+i).focus();
					return false;
					
	   	    }else if(!dateBetweenTwoDates(fieldNameStart1, lableNameStart1)){
	   				document.getElementById('locToDate'+i).focus();
					return false;
					
			}if(!validateTime('locFromTime'+i,'FrTime')){
					document.getElementById('locFromTime'+i).focus();
					return false;
					
			}if(!validateTime('locToTime'+i,'ToTime4')){
					document.getElementById('locToTime'+i).focus();
					return false;
					
			}if(trim(document.getElementById('locFromTime'+i).value)!=""
				&& trim(document.getElementById('locToTime'+i).value)!=""
				&& (document.getElementById('locFrmDate'+i).value!="" && document.getElementById('locToDate'+i).value!="")
				&& (document.getElementById('locFrmDate'+i).value== document.getElementById('locToDate'+i).value)
			){
					var frmTime = document.getElementById('locFromTime'+i).value;
					var toTime = document.getElementById('locToTime'+i).value;
					
					if(!timeDifference(frmTime,toTime,'locFromTime'+i,'FrTime3','ToTime4')){
						return false;
					}
					
			}
			
   		}
   		
   		//function timeDifference(fromTime,toTime,fieldName,fromLabName,toLabName){   
   		
   		return true;
 }
 
function returntolistFun(){
    
    if(document.getElementById('paraFrm_appStatus').value=="B" 
      || document.getElementById('paraFrm_appStatus').value=="N"
      || document.getElementById('paraFrm_appStatus').value==""
      || document.getElementById('paraFrm_appStatus').value=="P"
      || document.getElementById('paraFrm_appStatus').value=="S"
      ){
      document.getElementById('paraFrm').action="TravelApplication_input.action";
      
    }if(document.getElementById('paraFrm_appStatus').value=="A" || document.getElementById('paraFrm_appStatus').value=="AC" ){
      document.getElementById('paraFrm').action="TravelApplication_getApprovedList.action";
      
    }if(document.getElementById('paraFrm_appStatus').value=="R"){
      document.getElementById('paraFrm').action="TravelApplication_getRejectedList.action";
      
    }if(document.getElementById('paraFrm_appStatus').value=="PC"){
      document.getElementById('paraFrm').action="TravelApplication_getPendingForCancelledList.action";
      
    }if(document.getElementById('paraFrm_appStatus').value=="CC"){
      document.getElementById('paraFrm').action="TravelApplication_getCancelledList.action";
      
    }if(document.getElementById('paraFrm_appStatus').value=="W"){//Withdraw list
      document.getElementById('paraFrm').action="TravelApplication_input.action";
      
    }if(document.getElementById('paraFrm_appStatus').value=="H"){//Approved List
      document.getElementById('paraFrm').action="TravelApplication_getApprovedList.action";
      
    }
 	
  	document.getElementById('paraFrm').submit();
 	
 } 

 
</script>