<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<html>
<body>
<s:form name="" action="" validate="" id="paraFrm" theme="simple">

<s:hidden name="appStatus" />
<s:hidden name="listType" />
<s:hidden name="appId" />
<s:hidden name="appCode"  />
<s:hidden name="hAppFor"  />
<s:hidden name="empId" />
<s:hidden name="initId" />

<s:hidden  name="trRadio" />
<s:hidden  name="accomRadio" />
<s:hidden  name="locRadio" />
<s:hidden name="noData" />

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
					<td width="75%" colspan="2">					
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />						
						</td>
					<td width="25%">
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
		     <s:property value="abbrAppFor"/>
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
		     <s:hidden name="accompWith"/>
		    </td>
		   </tr>
		   <tr>
		    <td>
		    	<label class="set" name="traappdate" id="traappdate" ondblclick="callShowDiv(this);"><%=label.get("traappdate")%></label>:
		    </td>
		    <td>
		     <s:property value="appDate"/>
		      <s:hidden name="appDate"/>
		    </td>
		    <td>
		    	<label class="set" name="traStatus" id="traStatus" ondblclick="callShowDiv(this);"><%=label.get("traStatus")%></label>:
		    </td>
		    <td>
		      <s:if test='%{appStatus=="N"||appStatus=="" ||appStatus=="W"}'>
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
		       <label class="set" name="trainitor" id="trainitor" ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label>: 
		    </td>
		    <td colspan="2">			    
			    <s:property value="initToken"/>&nbsp;&nbsp;&nbsp;&nbsp;
			    <s:property value="initName"/>
			    <s:hidden name="initToken"/>
			    <s:hidden name="initName"/>
		    </td>
		    <script>
			      function previewApplication(appId){
			      	//alert("1 "+appId);
			      	//alert("2 "+document.getElementById('paraFrm_hAppFor').value);
				    //win=window.open('','win','top=60,left=90,width=750,height=600,scrollbars=yes,status=no,resizable=no');
				    //document.getElementById("paraFrm").target="win";
					document.getElementById("paraFrm").target="win";
					document.getElementById("paraFrm").action="TravelApplication_previewApplication.action?appId="+appId+"&hAppFor="+document.getElementById('paraFrm_hAppFor').value;
					document.getElementById("paraFrm").submit();	//action="TravelApplication_previewApplication"
					document.getElementById("paraFrm").target="main";
					
			      }
		     </script>
		    <td align="left">
		    	<s:if test='%{appStatus=="N"||appStatus==""||appStatus=="B" ||appStatus=="W" }'>
		      	 <input type="button" class="token" value="Preview Application"  onclick="previewApplication(document.getElementById('paraFrm_appId').value);"  />
		        </s:if>
		    
		    </td>
		   </tr>
		  </table>
		 </td>
		</tr>		
		
		<!---------------------------- ADVANCE DETAILS [BEGINS] ---------------------------------------------->
		<tr>
		 <td>
		  <table width="100%" class="formbg">
		    <tr>
		     <td colspan="5"><b>Advance Details</b></td>
		    </tr>
		    <tr>
		     <td class="formth" width="5%">
		     	<b><label class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		     </td>
		     <s:if test='%{hAppFor=="S" || hAppFor=="O"}'>
		     	<td class="formth" width="30%">
		     	 <b><label class="set" name="Empname" id="Empname0" ondblclick="callShowDiv(this);"><%=label.get("Empname")%></label></b>
		     	</td> 
		     </s:if> 
		     <s:else>
		     	<td class="formth" width="30%">
		     	  <b><label class="set" name="guest" id="guest1" ondblclick="callShowDiv(this);"><%=label.get("guest")%></label></b>
		     	</td>
		     </s:else>
		     <td class="formth" width="25%">
		     	<b><label class="set" name="AdvAmount" id="AdvAmount" ondblclick="callShowDiv(this);"><%=label.get("AdvAmount")%></label></b>
		     </td>
		     <td class="formth" width="20%">
		     	<b><label class="set" name="PrePaymode" id="PrePaymode" ondblclick="callShowDiv(this);"><%=label.get("PrePaymode")%></label> </b>
		     </td>
		     <td class="formth" width="20%">
		     	<b><label class="set" name="ExpSetDate" id="ExpSetDate" ondblclick="callShowDiv(this);"><%=label.get("ExpSetDate")%></label><br>(DD-MM-YYYY)</b>
			     <s:if test='%{hAppFor=="S" || hAppFor=="O"}'>		      
			     
			     </s:if>
		     </td>
		    </tr>
		    <%int i=1; %>
		    <s:iterator value="advList">
		     <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" || trvlApp.appStatus=="" }'>
			     <tr>
			      <td class="sortableTD"><%=i%>
			      	<s:hidden name="advAppCode" />
			      	<input type="hidden" name="policyViolation" id="policyViolation<%=i %>" value="<s:property value="policyViolation" />">
			      	<input type="hidden" name="approverId" id="approverId<%=i%>" value="<s:property value="approverId" />">
			      	<input type="hidden" name="trvlId" id="trvlId<%=i%>" value="<s:property value="trvlId" />">
			      </td>	
			      <s:if test='%{trvlApp.hAppFor=="S" ||trvlApp.hAppFor=="O"}'>
			     	<td class="sortableTD"><s:property value="empName" /><input type="hidden" name="empName" id="empName<%=i%>" value="<s:property value="empName" />"></td>
			     	</s:if>
			      <s:else>
			     	<td class="sortableTD"><s:property value="guestName" /> </td>
			      </s:else>
			      <td class="sortableTD" align="center">					
			      	<input type="text" name="advAmount" id="advAmount<%=i %>" size="8" maxlength="10"  value="<s:property value="advAmount" />" onkeypress="return numbersOnly();" >
			      	<s:select
					name="currencyId" headerKey=""
					list="%{currencyMap}"
					theme="simple"
					id=""
					/>
			      	
			      	
			      </td>
			      <td class="sortableTD" align="center"><s:select name="payMode" id='<%="payMode"+i%>' list="#{'S':'--Select--','C':'Cash','Q':'Cheque','T':'Transfer'}" /></td>
			      <td class="sortableTD" align="center">
			      	<input type="text" name="expStlmntDate" id="expStlmntDate<%=i%>"  value="<s:property value="expStlmntDate" />"  size="10" maxLength="10"  onkeypress="return numbersWithHiphen();">
				      	<a href="javascript:NewCal('expStlmntDate<%=i%>','DDMMYYYY');">  
					     <img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
						</a>
			      </td>  
			     </tr>
			  </s:if>
			  <s:else>
			     <tr>
			      <td class="sortableTD"><%=i%></td>
			      <s:if test='%{trvlApp.hAppFor=="S" ||trvlApp.hAppFor=="O"}'>
			     	<td class="sortableTD"><s:property value="empName" /></td>
			     	</s:if>
			      <s:else>
			     	<td class="sortableTD"><s:property value="guestName" /> </td>
			      </s:else>
			      <td class="sortableTD" align="right">			      	
			      	<s:property value="advAmount" />&nbsp;
			      	<s:property value="currency"/>&nbsp;
			      </td>
			      <td class="sortableTD" align="left">
			      	<s:if test='%{payMode=="C"}'>
			      	 Cash
			      	</s:if>
			      	<s:if test='%{payMode=="Q"}'>
			      	 Cheque
			      	</s:if>
			      	<s:if test='%{payMode=="T"}'>
			      	 Transfer
			      	</s:if>
			      	&nbsp;
			      </td>
			      <td class="sortableTD" align="center"><s:property value="expStlmntDate" />&nbsp;</td>  
			     </tr>
			  </s:else>
			     <%i++;%>
		    </s:iterator>
		    <input type="hidden" id="advCount" value="<%=i %>">
		  </table>
		 </td>
		</tr>
		<!---------------------------- ADVANCE DETAILS [ENDS] ---------------------------------------------->
		
		
		<!---------------------------- IDENTITY DETAILS [BEGINS] ---------------------------------------------->
		
		<tr>
		 <td>
		  <table width="100%"  class="formbg">
		  	 <tr>
		     <td colspan="5"><b>Identity Details</b></td>
		    </tr>
		    <tr>
		     <td class="formth" width="5%">
		     	<b><label class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		     </td>
			      <s:if test='%{hAppFor=="S" || hAppFor=="O"}'>
			     	<td class="formth" width="20%">
			     		<b><label class="set" name="Empname" id="Empname1" ondblclick="callShowDiv(this);"><%=label.get("Empname")%></label></b>
			     	</td>
			      </s:if>
			      <s:else>
			     	<td class="formth" width="20%">
			     		<b><label class="set" name="guest" id="guest" ondblclick="callShowDiv(this);"><%=label.get("guest")%></label>	</b>		     		
			     	</td>
			      </s:else>
		     <td class="formth" width="25%">
		     	<b><label class="set" name="IdproofName" id="IdproofName" ondblclick="callShowDiv(this);"><%=label.get("IdproofName")%></label></b>
		     </td>
		     <td class="formth" width="10%">
		     	<b><label class="set" name="IdNumer" id="IdNumer" ondblclick="callShowDiv(this);"><%=label.get("IdNumer")%></label></b>
		     </td>
		     <td class="formth" width="25%">
		     	<b><label class="set" name="IdComments" id="IdComments" ondblclick="callShowDiv(this);"><%=label.get("IdComments")%></label></b>
		     </td>		     
		    </tr>
		    <%int j=1;%>
		    <s:iterator value="identityList">
			     <s:if test='%{trvlApp.appStatus=="N" || trvlApp.appStatus=="B" || trvlApp.appStatus=="W" ||trvlApp.appStatus=="" }'>
				    <tr>
				     <td class="sortableTD"><%=j%><s:hidden name="idenAppCode" /></td>
				     
				     <s:if test='%{trvlApp.hAppFor=="S" ||trvlApp.hAppFor=="O"}'>
				     	<td class="sortableTD"><s:property value="empName" /></td>
				     </s:if>
				      <s:else>
				     	<td class="sortableTD"><s:property value="guestName" /></td>
				      </s:else>
				      <td class="sortableTD">
				       <s:select name="idProof" id="idProof" list="#{'':'-------------------------------Select-------------------------------','Voter Identity Card':'Voter Identity Card','Passport':'Passport','PAN Card':'PAN Card','Driving License':'Driving License','Photo identity cards issued by Central/State Government':'Photo identity cards issued by Central/State Government'}" />
				      </td> 
				      <td class="sortableTD"> 
				      	<input type="text" name="idNumber" id="idNumber" size="10" maxlength="20" value="<s:property value="idNumber" />"  >
				      </td>
				      <td class="sortableTD"> 
				       
				       <!-- <textarea rows="2" cols="23" name="comments" id="paraFrm_comments<%=j%>"    onkeyup="callLength('comments<%=j %>','descCnt<%=j %>','300');"><s:property value="comments" /></textarea>-->
				       
				       <textarea rows="2" cols="23" name="comments" id="paraFrm_comments<%=j%>"    onkeyup="textCounter(this,500);"><s:property value="comments" /></textarea>
				       
				       <img src="../pages/images/zoomin.gif" class="iconImage" height="12" align="absmiddle" width="12"
							 onclick="callWindow('paraFrm_comments<%=j%>','IdComments','','paraFrm_descCnt<%=j %>',500);"> 
				       <input type="hidden" name="descCnt" id="paraFrm_descCnt<%=j %>" size="5" readonly="readonly"> 
				      </td>
				    </tr>
				  </s:if>
				  <s:else>
				  <tr>
				     <td class="sortableTD"><%=j%></td>			     
				     <s:if test='%{trvlApp.hAppFor=="S" ||trvlApp.hAppFor=="O"}'>
				     	<td class="sortableTD"><s:property value="empName" /></td>
				     </s:if>
				      <s:else>
				     	<td class="sortableTD" align="left"><s:property value="guestName" /></td>
				      </s:else>
				      <td class="sortableTD" align="left">
				       <s:property value="idProof"/>&nbsp;
				      </td> 
				      <td class="sortableTD" align="left">
				      	<s:property value="idNumber"/>&nbsp;
				      </td>
				      <td class="sortableTD" align="left">
				       <s:property value="comments"/>&nbsp;
				      </td>
				    </tr>			  
				  </s:else>
		    <%j++;%>
		    </s:iterator>
		    <input type="hidden" id="identityCount" value="<%=j %>">
		  </table>
		 </td>
		</tr>
		<!---------------------------- IDENTITY DETAILS [ENDS] ---------------------------------------------->
	
	<script>
	 	function showTrail(){
	 		
		  	var applId=document.getElementById('paraFrm_appId').value;
		  	var applCode=document.getElementById('paraFrm_appCode').value;	
		  	var wind = window.open('','win','width=600,height=300,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100','location=no');	 
			document.getElementById('paraFrm').target = "win";
			document.getElementById('paraFrm').action = "TravelAppvr_showCmtsTrail.action?applicationId="+applId+"&applicationCode="+applCode;
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = "main";
			
	  	}
	</script>
	
	
	<!-- COMMENTS TRAIL [BEGINS] -->
	<!-- 
	<s:if test='%{appStatus!="N" || appStatus=="" }'>
	<tr>
	<td class="formbg">
	 <table>
		<tr>
		  <td colspan="2">
		    <a href="#" title="Click here" onclick="return showTrail();"><STRONG>Comments Trail</STRONG></a>
		  </td>
		</tr>
	</table>
	</td>
	</tr>	
	</s:if>
	-->
	<!-- COMMENTS TRAIL [ENDS] -->
	 
	
	
	<!-------------------------------- APPLICANT COMMENTS [BEGINS]--------------------------------------------------->
	<!-- NEW or RETURNED APPLICATIONS can only enter comments-->
	<s:if test='%{appStatus=="N" || appStatus=="B" || appStatus=="W" ||appStatus=="" }'>
		<tr>
		 <td class="formbg">
			<table width="100%">
			 <tr>
		  <td colspan="2" align="right">
		    <a href="#" title="Click here" onclick="return showTrail();"><STRONG>Comments Trail</STRONG></a>
		  </td>
		</tr>
			 <tr>
			  <td width="15%">
			  	<label class="set" name="applicantComments" id="applicantComments" ondblclick="callShowDiv(this);"><%=label.get("applicantComments")%></label> :
			  </td>
			  <td>
			    
			  	<!-- <textarea rows="4" cols="60" name="applComm" id="paraFrm_applComm" onkeyup="callLength('applComm','descCnt','300');"><s:property value="applComm" /></textarea> -->
			  	
			  	<textarea rows="4" cols="60" name="applComm" id="paraFrm_applComm" onkeyup="callLength('applComm','descCnt','500');"><s:property value="applComm" /></textarea>
			  	 
			  	 
			  	 <img src="../pages/images/zoomin.gif" class="iconImage" height="12" align="absmiddle" width="12"
								 onclick="callWindow('paraFrm_applComm','applicantComments','','paraFrm_descCnt',500);"> 
				Remaining chars  <input type="text" name="descCnt" id="paraFrm_descCnt" size="5" readonly="readonly" >  
			  </td>
			 </tr>
			 <!-- FOR Returned/Sent Back Applications only -->
		 	<s:if test='%{appStatus=="B"}'>
			  		<tr>
					  <td width="17%">
					  	<label class="set" name="cancelComments" id="cancelComments" ondblclick="callShowDiv(this);"><%=label.get("cancelComments")%></label> <font  color="red">*</font>:
					  </td>
					  <td>
						
					    <textarea name="withComm" id="paraFrm_withComm" rows="4" cols="60" onkeyup="callLength('withComm','descCnt11','500');"><s:property value="withComm" /></textarea>					   
			  	 		<img src="../pages/images/zoomin.gif" class="iconImage" height="12" align="absmiddle" width="12"
								 onclick="callWindow('paraFrm_withComm','cancelComments','','paraFrm_descCnt11',500);"> 
						Remaining chars  <input type="text" name="descCnt" id="paraFrm_descCnt11" size="5" readonly="readonly" >  
			  	   	    
					  </td>
				  </tr>
			 </s:if>
			</table>
		 </td>
		</tr>
	</s:if>
	<s:else>
		<tr>
		 <td class="formbg">
			<table width="100%">
			 <tr>
		  <td colspan="2" align="right">
		    <a href="#" title="Click here" onclick="return showTrail();"><STRONG>Comments Trail</STRONG></a>
		  </td>
		</tr>
			 <tr>
			  <td width="18%">
			  	<label class="set" name="applicantComments" id="applicantComments" ondblclick="callShowDiv(this);"><%=label.get("applicantComments")%></label> :
			  </td>
			  <td>
			  	
			  	<textarea rows="4" readonly="readonly" cols="50" name="applComm" id="paraFrm_applComm" ><s:property value="applComm" /></textarea>
			  	
			  </td>
			 </tr>
			 
			 <!-------------------------------- CANCELLATION COMMENTS [BEGINS] ------------------------------------------>	
			 <s:if test='%{appStatus=="CC" || appStatus=="B" || appStatus=="W" ||appStatus=="" }'>
	
				 <s:iterator value="cancellist">
			 		<tr>
						  <td width="17%">
						  	<label class="set" name="cancelComments" id="cancelComments" ondblclick="callShowDiv(this);"><%=label.get("cancelComments")%></label>:
						  </td>
						  <td>
						   <TEXTAREA rows="4" cols="50" readonly="readonly"><s:property value="cancelComm"/></TEXTAREA>
						  </td>
					</tr>
				 </s:iterator>
			</s:if>
			<!-------------------------------- CANCELLATION COMMENTS [ENDS] ------------------------------------------>
			 
			 <!-- FOR Pending Applications only [WITHDRAWAL COMMENTS]-->
			 
			 <s:if test='%{appStatus=="P"}'>
				 <tr>
				  <td width="15%">
				  	<label class="set" name="withComments" id="withComments" ondblclick="callShowDiv(this);"><%=label.get("withComments")%></label> <font  color="red">*</font>:
				  </td>
				  <td>
				   <textarea name="withComm" id="paraFrm_withComm" rows="4" cols="50" onkeyup="callLength('withComm','descCnt','500');"></textarea>
				   <img src="../pages/images/zoomin.gif" class="iconImage" height="12" align="absmiddle" width="12"
							 onclick="callWindow('paraFrm_withComm','withComments','','paraFrm_descCnt',500);"> 
							 &nbsp;Remaining chars<s:textfield name="descCnt" id="paraFrm_descCnt" readonly="true"
								size="5"></s:textfield>
				       
				       
				       <!--  <input type="text" name="descCnt" id="paraFrm_descCnt<%=j %>" size="5" readonly="readonly"> 
				       -->
				  </td>
				 </tr>
			 </s:if>			 
			</table>
		 </td>
		</tr>
	</s:else>
	<!-------------------------------- APPLICANT COMMENTS [ENDS]--------------------------------------------------->
	
	
<!-- POLICY VIOLATION DIV FOR DISPLAYING THE MESSAGE [BEGINS] -->		 
		 <tr>
		  <td>
		  <script>
		   function hideWindow(){
		    document.getElementById('policyDiv').style.display='none';
		   } 
		   
		   function sendForApproval(){
		   					   
			 	    document.getElementById('paraFrm').action="TravelApplication_sendForApproval.action";
			  		document.getElementById('paraFrm').submit();
			  		return true;
			  		
		   }
		   
		  </script>
		   	<div id="policyDiv"	style='position: absolute; z-index: 3; width: 520px;height:260px; display:none; border: 2px solid; top: 100px; left: 100px; padding: 10px;' class="formbg">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
						<tr>
						 <td  colspan="2">
						 	<font color="red" size="2">Policy violated by following:</font>
						 </td>
						 <td>
						  &nbsp;
						 </td>
						</tr>		
						<%int index=1; %>
						 <tr>
						  <td colspan="3" valign="top">
						   	<div id="policyDiv1"	style=' width: 500px;height:200px; display:block; border: 2px solid;overflow: scroll;' class="formbg">
						  	<table width="100%" >
						  	 <tr>
						  	  <td>
									  <label id="spanId">
									 </label>
							  </td>
							 </tr>
						 	</table>
						 	</div>
						  </td>
						 </tr>
						 
						 <tr>
						  <td>
						    <b>Do you really want to send for approval?</b> 
						  	<input type="button" value="   Yes   " onclick="sendForApproval();" class="token"> 
						  	<input type="button" value="    No   " onclick="hideWindow();" class="token">
						  </td>
						 </tr>
						 
					</table>
			</div>
		  </td>
		 </tr>
<!--  POLICY VIOLATION DIV FOR DISPLAYING THE MESSAGE [ENDS]-->	
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="75%" colspan="2">					
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />						
					</td>
					<td width="25%" align="right">
						<b>Page 3 of 3</b>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
   </table>
  </s:form>
 </body>

<script>
	
	
	//Cancellation of applciation
	function cancelFun(){		
		
		var conf = confirm('Do you really want to cancel the application?');
		if(conf){
			
			if(!(document.getElementById('paraFrm_withComm').value=="")){			
			 	document.getElementById('paraFrm').action="TravelApplication_cancelApplication.action";
			  	document.getElementById('paraFrm').submit();
		  	}else{
		  	    alert('Please enter cancellation comments.');
		  	    document.getElementById('paraFrm_withComm').focus();
		  	    return false;
		  	}
		  	
	  	}else{
	  	    return false;
	  	}
	  	
 	} 
	
	function withdrawFun(){
				try{
				if(document.getElementById('paraFrm_withComm').value==""){    				 
    				 alert('Please enter '+document.getElementById('withComments').innerHTML+'.');
    				 document.getElementById('paraFrm_withComm').focus();
    				 return false;    				 
    				}
    			if(document.getElementById('paraFrm_withComm').value.length > 500){
    			alert("Maximum length of "+document.getElementById('withComments').innerHTML.toLowerCase()+" is 500 characters.");
    			return false;
    			}
    			var conf = confirm('Do you really want to withdraw the application?');    			

    			if(conf){   
    	    		document.getElementById('paraFrm').action="TravelApplication_withdraw.action";
	    			document.getElementById('paraFrm').submit();	    			
    			}else{    				
    	    		return false;
    	    		
    			}
    			}catch(e){
    			}
    			
   	}
	
	
	function textCounter(field,  maxlimit) {
		
		if (field.value.length > maxlimit){
			//alert('Maximum characters allowed for comments are '+maxlimit);
			field.value = field.value.substring(0, maxlimit);
			//field.focus;
			return false;
		}
		
		return true;
		
	}


  function saveFun(){
  
  	if(!validateAdvanceDtls()){
  	 return false;
  	}
  
  	document.getElementById('paraFrm').action="TravelApplication_saveAdvanceIdentityDtls.action";
  	document.getElementById('paraFrm').submit();
 }

 function saveandpreviousFun(){
 
 	if(!validateAdvanceDtls()){
  	 	return false;
  	}
 	
  	document.getElementById('paraFrm').action="TravelApplication_saveAndPreviousAdvanceIdentity.action";
  	document.getElementById('paraFrm').submit();
 }
  
 function sendforapprovalFun(){
    
 	if(!validateAdvanceDtls()){
  	 return false;
  	}
    
 	if(document.getElementById('paraFrm_hAppFor').value=="S" || document.getElementById('paraFrm_hAppFor').value=="O"){
	 	empList = validatePolicy();
	 	
	 	document.getElementById('spanId').innerHTML=empList;
	 	//alert(document.getElementById('spanId').innerHTML);
	 	
	 	if(empList=="PND"){
	 	 	return false;
	 	}if(empList=="PMND"){
	 	 	return false;
	 	}if(empList!=""){
	 		 document.getElementById('policyDiv').style.display='block';	
	 		 return false;	 	
	 	}
    }
     
    
 	var conf = confirm('Do you really want to send for approval?');
 	if(conf){
 	 	document.getElementById('paraFrm').action="TravelApplication_sendForApproval.action";
  		document.getElementById('paraFrm').submit();
 	}else{
 	 return false;
 	}
    
  }
 
 function previousFun(){
  	document.getElementById('paraFrm').action="TravelApplication_nextEmpInfo.action";
  	document.getElementById('paraFrm').submit();
 }
 
 function validateAdvanceDtls(){
 	
 	//alert(document.getElementById('paraFrm_hAppFor').value=="S" || document.getElementById('paraFrm_hAppFor').value=="O");
 	
 	if(document.getElementById('paraFrm_hAppFor').value=="S" 
 		|| 	document.getElementById('paraFrm_hAppFor').value=="O"){
 					
		 	for(i=1;i<document.getElementById('advCount').value;i++){
		 	 	 
			 	 if(eval(document.getElementById('advAmount'+i).value)>0 && document.getElementById('payMode'+i).value=="S"){
			 	 			alert('Please select '+document.getElementById('PrePaymode').innerHTML+'.');
			 	 			document.getElementById('payMode'+i).focus();
			 	 			return false;
			 	 }if(trim(document.getElementById('expStlmntDate'+i).value)!=""){
					if(!validateDate('expStlmntDate'+i,'ExpSetDate')){
							document.getElementById('expStlmntDate'+i).focus();
							return false;							 
			   	  }
					 	else if(!dateDifferenceEqual(document.getElementById('paraFrm_appDate').value, document.getElementById('expStlmntDate'+i).value, 'expStlmntDate'+i, 'traappdate', 'ExpSetDate')){
					      	return false;
					      	
			   	  }else if( document.getElementById('paraFrm_descCnt'+i).value<0){
			   	  			alert('Maximum length of '+document.getElementById("IdComments").innerHTML+' is 500');
			   	  			document.getElementById('paraFrm_comments'+i).focus();
			   	            return false;
			   	  }    
			 	  }
		   		  
		 	}
 	}else{//GUESTS
 	
 		for(i=1;i<document.getElementById('advCount').value;i++){
	 		if( document.getElementById('paraFrm_descCnt'+i).value<0){
				   	  			alert('Maximum length of comments is 500');
				   	  			document.getElementById('paraFrm_comments'+i).focus();
				   	            return false;
			}
		}
 	
 	}
 	if( document.getElementById('paraFrm_descCnt').value<0){
			   	  			alert('Maximum length of '+document.getElementById("applicantComments").innerHTML+' is 500');
			   	  			document.getElementById('paraFrm_applComm').focus();
			   	            return false;
	}
  	return true;
  	
 }
 
 function validatePolicy(){
  
  var empPolicy = "\n";//Policy violated by :
  var empList = "";
  var empApprover = "\n\nSecond approver missing of following :";
  var apprList = "";
  var count=1; 
  
  for(i=1;i<document.getElementById('advCount').value;i++){
 	 
 	 
 	 if(trim(document.getElementById('policyViolation'+i).value)!=""){
 	   
	 	   if(document.getElementById('policyViolation'+i).value=="PND"){
		 	    alert('Policy not defined for the grade of '+document.getElementById('empName'+i).value+'.\nApplication cant be sent for approval.');
		 	    return "PND";
	 	   }if(document.getElementById('policyViolation'+i).value=="PMND"){
	 	   		alert('Policy effective date applicability not configured\n in Travel Process Manager. Application cant be sent for approval.');
	 	   		return "PMND";
	 	   }
	 	   
	 	   
 	   		empList+="\n<br><br><b>"+i+".  "+document.getElementById('empName'+i).value+"("+document.getElementById('trvlId'+i).value+")"+"</b> - "+document.getElementById('policyViolation'+i).value;
 	   	
	 	   	if(trim(document.getElementById('approverId'+i).value)==""){
	 	   		  	
	 	   		apprList+="\n"+count+".  "+document.getElementById('empName'+i).value 	  	
	 	    		
	 	  	}
 	   //	document.getElementById('policyDiv').style.display = "";
 	   	
 	  }
 	  
 	 
 	}
 	
 	if(empList!=""){
 	 	return empPolicy+empList;
 	}if(apprList!=""){
 	 	return empApprover+apprList;
 	}
 	
	return "";  	
 }
 
function returntolistFun(){
    
    if(document.getElementById('paraFrm_appStatus').value=="B" 
      || document.getElementById('paraFrm_appStatus').value=="N"
      || document.getElementById('paraFrm_appStatus').value==""
      || document.getElementById('paraFrm_appStatus').value=="P"
      || document.getElementById('paraFrm_appStatus').value=="S"
      ){
      document.getElementById('paraFrm').action="TravelApplication_input.action";
      
    }if(document.getElementById('paraFrm_appStatus').value=="A" || document.getElementById('paraFrm_appStatus').value=="AC"){
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
</html>	