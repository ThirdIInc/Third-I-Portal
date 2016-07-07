<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="EmpConfForPension" method="post" id="paraFrm" theme="simple">  
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
 	    <tr>
			<td colspan="3" width="100%">   
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Employee Configuration</strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>  
			</td>
		 </tr>  
	      <tr>
	        <td colspan="3">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
					   <td width="100%" colspan="3">
					        <s:if test="%{viewFlag}">
					           <input type="button" class="token" onclick="return callGenerateReport();"	value=" View Employee " />
					         </s:if>
					        <s:submit cssClass="reset" action="EmpConfForPension_reset" theme="simple" value="    Reset"  /> 
					        
					  </td> 
					</tr>
				</table>
			</td>
		 </tr>
		 
		  <tr>
			<td colspan="3"><s:hidden name="myPageEmpConf"/>  <s:hidden name='aId'/> <s:hidden name='divId'/> <s:hidden name='backFlag'/>  <s:hidden name='settingNameDup'/> 
			
		         <table   border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr> 
							<td class="btn-middlebg">
							<div id="tabnav" style="vertical-align: bottom">
							 <ul>
								<li><a id="filtOpt" href="javascript:callTab('filtOpt','filter');">
									<div align="center"><span>Filter </span> </div></a>
								</li>
								<li><a id="sortOpt" href="javascript:callTab('sortOpt','sort');">
									<div align="center"><span>Sorting option</span></div></a>
								</li> 
								
								<li><a  id="colDef" href="javascript:callTab('colDef','column');">
									<div align="center"><span>Column Defintion</span></div></a>
								</li> 
								
								<li><a  id="advFilter" href="javascript:callTab('advFilter','advance');">
									<div align="center"><span>Advanced Filter</span></div></a>
								</li>  
							 </ul>
							</div>
							</td>
						</tr>
					</table> 
					
	<table id="filterDisp" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"  > 
			<tr>								
				<td width="100%" colspan="4" class="formtext"> <b> <label  class = "set"  id="selectFilter"  name="selectFilter" ondblclick="callShowDiv(this);"><%=label.get("selectFilter")%></label></b> </td>
			</tr>  
							
		<tr >
			<td width="25%"><label  class = "set" name="applDiv" id="applDiv" ondblclick="callShowDiv(this);"><%=label.get("applDiv")%></label> :</td>
			<td width="20%"><s:hidden name="applDivisionCode"></s:hidden>
			<s:textarea name="applDivisionName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'EmpConfForPension_f9applDiv.action'); "></td>
			<td  width="25%"><label  class = "set" name="applBranches" id="applBranches" ondblclick="callShowDiv(this);"><%=label.get("applBranches")%></label> :</td>
			<td width="20%"><s:hidden name="applBranchCode"></s:hidden>
			<s:textarea name="applBranchName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'EmpConfForPension_f9applBranch.action'); "></td>
		</tr>
		
		<tr>
			
			<td  width="25%"><label  class = "set" name="applDept" id="applDept" ondblclick="callShowDiv(this);"><%=label.get("applDept")%></label> :</td>
			<td width="20%"><s:hidden name="applDeptCode"></s:hidden>
			<s:textarea name="applDeptName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'EmpConfForPension_f9applDept.action'); "></td>
			<td  width="25%"><label  class = "set" name="applDesg" id="applDesg" ondblclick="callShowDiv(this);"><%=label.get("applDesg")%></label> :</td>
			<td width="20%"><s:hidden name="applDesgCode"></s:hidden>
			<s:textarea name="applDesgName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'EmpConfForPension_f9applDesg.action'); "></td>
		
		</tr>
		<tr>
			
			<td  width="25%"><label  class = "set" name="applEType" id="applEType" ondblclick="callShowDiv(this);"><%=label.get("applEType")%></label> :</td>
			<td width="20%"><s:hidden name="applETypeCode"></s:hidden>
			<s:textarea name="applETypeName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'EmpConfForPension_f9applEType.action'); "></td>
			<td  width="25%"><label  class = "set" name="applGrade" id="applGrade" ondblclick="callShowDiv(this);"><%=label.get("applGrade")%></label> :</td>
			<td width="20%"><s:hidden name="applGradeCode"></s:hidden>
			<s:textarea name="applGradeName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'EmpConfForPension_f9applGrade.action'); "></td>
		</tr>
		
		<tr>
			
			<td  width="25%"><label  class = "set" name="applEmp" id="applEmp" ondblclick="callShowDiv(this);"><%=label.get("applEmp")%></label> :</td>
			<td width="20%"><s:hidden name="applEmpCode"></s:hidden>
			<s:textarea name="applEmpName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'EmpConfForPension_f9applEmp.action'); "></td>
			
			<td  width="25%"></td>
			<td width="20%"></td>
			<td width="4%"></td>
		</tr>    
						  	
					</table> 
					
					   <table id="sort"  width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
					        <tr>								
								<td width="100%" colspan="2" class="formtext"> <b> <label  class = "set"  id="selectSort"  name="selectSort" ondblclick="callShowDiv(this);"><%=label.get("selectSort")%></label></b> </td>
							 </tr>  
							<tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="sortByLabel"  name="sortByLabel" ondblclick="callShowDiv(this);"><%=label.get("sortByLabel")%></label> : </td>
								<td width="83%" colspan="1"> <s:select headerKey="1" headerValue="--Select--" name="sortBy1" list="#{'2':'Employee ID' ,'3':'Employee Name','4':'Date Of Birth','5':'Date Of Joining','7':'Date Of Retirement','6':'Age'}"  />  </td>							
						 	</tr>  
						 	 <tr>								
								<td width="17%" colspan="1" class="formtext"></td>
								<td width="83%" colspan="1"> <s:radio  name="sortByOrder1" list="#{'A':'Ascending','D':'Descending'}"  /> </td>							
						 	</tr>  
						 	
						 	<tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="thenByLabel"  name="thenByLabel" ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label> : </td>
								<td width="83%" colspan="1">  <s:select headerKey="1" headerValue="--Select--" name="sortBy2" list="#{'2':'Employee ID' ,'3':'Employee Name','4':'Date Of Birth','5':'Date Of Joining','7':'Date Of Retirement','6':'Age'}"  />    </td>							
						 	</tr>  
						 	<tr>								
								<td width="17%" colspan="1" class="formtext"></td>
								<td width="83%" colspan="1"><s:radio  name="sortByOrder2" list="#{'A':'Ascending','D':'Descending'}" /></td>							
						 	  </tr>  
						 	 <tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="thenByLabel"  name="thenByLabel" ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label> : </td>
								<td width="83%" colspan="1">   <s:select headerKey="1" headerValue="--Select--" name="sortBy3" list="#{'2':'Employee ID' ,'3':'Employee Name','4':'Date Of Birth','5':'Date Of Joining','7':'Date Of Retirement','6':'Age'}"  />  </td>							
						 	  </tr>  
						  	 <tr>								
								<td width="17%" colspan="1" class="formtext"></td>
								<td width="83%" colspan="1"> <s:radio  name="sortByOrder3" list="#{'A':'Ascending','D':'Descending'}"  /></td>							
						 	 </tr>  
					</table> 
					
					<table id="column" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
							 <tr>								
								<td width="100%" colspan="6" class="formtext"> <b> <label  class = "set"  id="selectCoumn"  name="selectCoumn" ondblclick="callShowDiv(this);"><%=label.get("selectCoumn")%></label></b> </td>
							 </tr>  
							 <s:hidden name='checkedCount' value='11'/>
							<tr>								
								<td width="5%" colspan="1" class="formtext"><s:checkbox name="empTokenChk" disabled="true" />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="employee.id"  name="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label> </td>
							    <td width="5%" colspan="1" class="formtext"><s:checkbox name="empNameChk" disabled="true" />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="employee"  name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> </td>
						        <td width="5%" colspan="1" class="formtext"><s:checkbox name="divisionChk" onclick="checkedCounter('divisionChk');"  />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="division"  name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> </td>    
							</tr>   
						  	
						  	<tr>	
						  	    <td width="5%" colspan="1" class="formtext"><s:checkbox name="branchChk"  onclick="checkedCounter('branchChk');"   />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="branch"  name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> </td>
						         <td width="5%" colspan="1" class="formtext"><s:checkbox name="desgChk"  onclick="checkedCounter('desgChk');"  />  </td>
							    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="designation"  name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> </td>
					            <td width="5%" colspan="1" class="formtext"><s:checkbox name="dobChk" onclick="checkedCounter('dobChk');"  />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="dob"  name="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label> </td>
					        </tr>   
						  	
						  	<tr>	
						  	
						  	<td width="5%" colspan="1" class="formtext"><s:checkbox name="dojChk" onclick="checkedCounter('dojChk');"  />  </td>
						    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="doj"  name="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label> </td>
					    	<td width="5%" colspan="1" class="formtext"><s:checkbox name="ageChk"  onclick="checkedCounter('ageChk');" />  </td>
					        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="ageLabel1"  name="ageLabel" ondblclick="callShowDiv(this);"><%=label.get("ageLabel")%></label> </td>
						    <td width="5%" colspan="1" class="formtext"><s:checkbox name="statusChk" onclick="checkedCounter('statusChk');"  />  </td>
						    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="statusLabel"  name="statusLabel" ondblclick="callShowDiv(this);"><%=label.get("statusLabel")%></label> </td>
					 	  	</tr>   
						  	
						   <!--<tr>  
						  	 <td width="5%" colspan="1" class="formtext"><s:checkbox name="yearsOfServiceChk" onclick="checkedCounter('yearsOfServiceChk');"   />  </td>
						     <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="QualfYearsService"  name="QualfYearsService" ondblclick="callShowDiv(this);"><//%=//label.get("QualfYearsService")%></label> </td>
							 
						 	 </tr>  -->
				</table> 
				
				<table id="advance" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
				              <tr>								
								<td width="100%" colspan="3" class="formtext"> <b> <label  class = "set"  id="selectAdvanceFilter"  name="selectAdvanceFilter" ondblclick="callShowDiv(this);"><%=label.get("selectAdvanceFilter")%></label> </b> </td>
							 </tr> 
							
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="dateOfBirthLabel"  name="dateOfBirthLabel" ondblclick="callShowDiv(this);"><%=label.get("dateOfBirthLabel")%></label> : </td>
								   <td width="10%" colspan="1" class="formtext"> 
								    <s:select headerKey="1" headerValue="--Select--" name="dobCompare"  list="#{'2':'On','3':'Before','4':'After' ,'5':'On before' ,'6':'On after','7':'From'}"  theme="simple"  onchange="callToDateForAdvance('dob');" />
								    </td>
							    <td width="68%" colspan="1" class="formtext"> 
							     <table width="100%" border="0">
							       <tr>
							          <td width="32%"> <s:textfield name="dobFrom" size="14" onkeypress="return numbersWithHiphen();" maxlength="10"  onfocus="clearText('paraFrm_dobFrom','dd-mm-yyyy');" onblur="setText('paraFrm_dobFrom','dd-mm-yyyy');" />  <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_dobFrom','DDMMYYYY');"  >  </td>
							          <td   nowrap="nowrap" id="dobToLabel">  <label  class = "set"  id="toDateLabel"  name="toDateLabel" ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> : </td>
							          <td id="dobToField" >  <s:textfield name="dobTo" size="15" onkeypress="return numbersWithHiphen();" maxlength="10" onfocus="clearText('paraFrm_dobTo','dd-mm-yyyy');" onblur="setText('paraFrm_dobTo','dd-mm-yyyy');"   />   <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_dobTo','DDMMYYYY');">  </td>
							     </tr>
							     </table>
							    
							     </td>
						    </tr>
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="dateOfJoinLabel"  name="dateOfJoinLabel" ondblclick="callShowDiv(this);"><%=label.get("dateOfJoinLabel")%></label> : </td>
								   <td width="10%" colspan="1" class="formtext"> 
								    <s:select headerKey="1" headerValue="--Select--" name="dojCompare"  list="#{'2':'On','3':'Before','4':'After' ,'5':'On before' ,'6':'On after','7':'From'}"  theme="simple"  onchange="callToDateForAdvance('doj');" />
								    </td>
							    <td width="68%" colspan="1" class="formtext"> 
							     <table width="100%" border="0">
							       <tr>
							          <td width="32%"> <s:textfield name="dojFrom" size="14" onkeypress="return numbersWithHiphen();" maxlength="10"  onfocus="clearText('paraFrm_dojFrom','dd-mm-yyyy');" onblur="setText('paraFrm_dojFrom','dd-mm-yyyy');" />  <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_dojFrom','DDMMYYYY');"  >  </td>
							          <td   nowrap="nowrap" id="dojToLabel">  <label  class = "set"  id="toDateLabel"  name="toDateLabel" ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> : </td>
							          <td id="dojToField" >  <s:textfield name="dojTo" size="15" onkeypress="return numbersWithHiphen();" maxlength="10" onfocus="clearText('paraFrm_dojTo','dd-mm-yyyy');" onblur="setText('paraFrm_dojTo','dd-mm-yyyy');"   />   <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_dojTo','DDMMYYYY');">  </td>
							     </tr>
							     </table>
							    
							     </td>
						    </tr>  
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="dateOfRetLabel"  name="dateOfRetLabel" ondblclick="callShowDiv(this);"><%=label.get("dateOfRetLabel")%></label> : </td>
								   <td width="10%" colspan="1" class="formtext"> 
								    <s:select headerKey="1" headerValue="--Select--" name="dorCompare"  list="#{'2':'On','3':'Before','4':'After' ,'5':'On before' ,'6':'On after','7':'From'}"  theme="simple"  onchange="callToDateForAdvance('dor');" />
								    </td>
							    <td width="68%" colspan="1" class="formtext"> 
							     <table width="100%" border="0">
							       <tr>
							          <td width="32%"> <s:textfield name="dorFrom" size="14" onkeypress="return numbersWithHiphen();" maxlength="10"  onfocus="clearText('paraFrm_dorFrom','dd-mm-yyyy');" onblur="setText('paraFrm_dorFrom','dd-mm-yyyy');" />  <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_dorFrom','DDMMYYYY');"  >  </td>
							          <td   nowrap="nowrap" id="dorToLabel">  <label  class = "set"  id="toDateLabel"  name="toDateLabel" ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> : </td>
							          <td id="dorToField" >  <s:textfield name="dorTo" size="15" onkeypress="return numbersWithHiphen();" maxlength="10" onfocus="clearText('paraFrm_dorTo','dd-mm-yyyy');" onblur="setText('paraFrm_dorTo','dd-mm-yyyy');"   />   <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_dorTo','DDMMYYYY');">  </td>
							     </tr>
							     </table>
							    
							     </td>
						    </tr> 
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="ageLabel"  name="ageLabel" ondblclick="callShowDiv(this);"><%=label.get("ageLabel")%></label> : </td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="ageOperator"  list="#{'=':'=','<':'<','>':'>','<=':'<=','>=':'>='}"  onchange="callAdvCombo('operator','ageFilter');"  /> </td>
							    <td width="68%" colspan="1" class="formtext"> &nbsp;<s:textfield name="ageFilter" size="14" onkeypress="return numbersOnly();" maxlength="10"  />  </td>
						    </tr> 
						     
						     					      
				</table> 
				 </td>
				</tr>	  
			  <tr>
			     <td colspan="3"> 
			       <table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"> 
			                   <tr>								
								 <td width="100%" colspan="2" class="formtext"> <b>Display Option</b> </td>
							   </tr>  
							  <tr>								
								<td width="100%" colspan="2" class="formtext"><s:hidden name="repStatus"/> <s:hidden name="hidReportView" />  <s:hidden name="hidReportRadio" />   <input type="radio" value="V" name="reportView"  id="reportViewV" <s:property value="hidReportView"/>  onclick="callReportChk('N');" > <label  class = "set"  id="view.screen"  name="view.screen" ondblclick="callShowDiv(this);"><%=label.get("view.screen")%></label>  </td>
							 </tr>  
							  <tr>								
								<td width="17%" colspan="1" class="formtext"> <input type="radio" value="R" name="reportView"  id="reportViewR"  <s:property value="hidReportRadio"/> onclick="callReportChk('Y');"> <label  class = "set"  id="report.type"  name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>  </td>
								<td width="83%" colspan="1" class="formtext" > <div id="reportTypeDiv"> <s:select  headerKey="1" headerValue="--Select--" name="reportType" list="#{'Pdf':'Pdf','Xls':'Xls' ,'Txt':'Doc'}"  /> </div>  </td>
							 </tr>  
					 </table>
			      </td>
		       </tr>  		
		       <tr> 
		          <td colspan="3">
			           <table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
							<td width="100%" colspan="3"> 
							<s:if test="%{viewFlag}">
							  <input type="button" class="token" onclick="return callGenerateReport();"	value=" View Employee " />
							 </s:if>
							<s:submit cssClass="reset" action="EmpConfForPension_reset" theme="simple" value="    Reset"  />  
							
							</td> 
						  </tr>
					  </table>
					</td>					
			    </tr>					
			</table>  
</s:form> 
 

<script type="text/javascript">    
 

 callOnLoad();
 function callOnLoad()
 {
   document.getElementById('filterDisp').style.display='';
   document.getElementById('sort').style.display='none'; 
   document.getElementById('column').style.display='none';  
   document.getElementById('advance').style.display='none';
   document.getElementById('paraFrm_repStatus').value ="V";
   document.getElementById('reportViewV').checked=true;
   document.getElementById('reportTypeDiv').style.display='none';
   
    document.getElementById('filtOpt').className = '';
	document.getElementById('sortOpt').className = '';
	document.getElementById('colDef').className = '';
	document.getElementById('advFilter').className = '';
	document.getElementById('filtOpt').className = 'on';
	document.getElementById('paraFrm_aId').value =  'filtOpt';
	document.getElementById('paraFrm_divId').value = 'filter';
	
	document.getElementById('paraFrm_sortByOrder1A').checked=true;
	document.getElementById('paraFrm_sortByOrder2A').checked=true;
	document.getElementById('paraFrm_sortByOrder3A').checked=true; 
	
	document.getElementById('paraFrm_empTokenChk').checked=true;
	document.getElementById('paraFrm_empNameChk').checked=true;
	document.getElementById('paraFrm_divisionChk').checked=true;
	document.getElementById('paraFrm_branchChk').checked=true;
	document.getElementById('paraFrm_desgChk').checked=true;
	document.getElementById('paraFrm_dobChk').checked=true;
	document.getElementById('paraFrm_dojChk').checked=true;
	document.getElementById('paraFrm_ageChk').checked=true;
	document.getElementById('paraFrm_statusChk').checked=true;
	
	
	   document.getElementById('dobToField').style.display='none'; 
	   document.getElementById('dobToLabel').style.display='none'; 
	   document.getElementById('dojToField').style.display='none'; 
	   document.getElementById('dojToLabel').style.display='none';
	   document.getElementById('dorToField').style.display='none'; 
	   document.getElementById('dorToLabel').style.display='none'; 
	   var fieldName =["paraFrm_dobFrom","paraFrm_dojFrom","paraFrm_dorFrom"];
   setTextArray(fieldName,"dd-mm-yyyy");
   
 }

function checkedCounter(fieldName)
{ 
	var checkedCount =  document.getElementById('paraFrm_checkedCount').value; 
	 if(checkedCount=="" || checkedCount=='null')
	 {
	  checkedCount="0";
	 }  
		 if(document.getElementById("paraFrm_"+fieldName).checked==true){ 
			checkedCount= eval(eval(checkedCount)+1); 
		  }else{ 
			checkedCount= eval(eval(checkedCount)-1);
		 }
		 
	 document.getElementById('paraFrm_checkedCount').value =checkedCount;
}

 
  function callAdvCombo(filedName,resetFiled)
  {
  filedClick = document.getElementById('paraFrm_'+filedName).value;
  resetFiledName= document.getElementById('paraFrm_'+resetFiled).value;
   if(filedClick=="1") 
   {
    document.getElementById('paraFrm_'+resetFiled).value="";
   }
  }
   
 
 function callReportChk(status)
 { 
	 if(status=="Y"){
	   document.getElementById('reportTypeDiv').style.display=''; 
	   document.getElementById('paraFrm_repStatus').value ="R"; 
	  }else{
	   document.getElementById('reportTypeDiv').style.display='none';  
	   document.getElementById('paraFrm_repStatus').value ="V"; 
	  }
 }

 function callTab(aId,tabId)
 { 
        document.getElementById('filtOpt').className = ''; 
		document.getElementById('sortOpt').className = ''; 
		document.getElementById('colDef').className = ''; 
		document.getElementById('advFilter').className = ''; 
		document.getElementById(aId).className = 'on'; 
		document.getElementById('paraFrm_aId').value =  aId; 
		document.getElementById('paraFrm_divId').value = tabId; 
	  if(tabId=="filter")
	  {   
	   document.getElementById('filterDisp').style.display='';
	   document.getElementById('sort').style.display='none'; 
	   document.getElementById('column').style.display='none';  
	   document.getElementById('advance').style.display='none';  
	  }
	  if(tabId=="sort")
	  {   
	   document.getElementById('sort').style.display=''; 
	   document.getElementById('filterDisp').style.display='none'; 
	   document.getElementById('column').style.display='none';  
	   document.getElementById('advance').style.display='none';    
	  }
	   if(tabId=="column")
	   { 
	   document.getElementById('column').style.display='';   
	   document.getElementById('sort').style.display='none';
	   document.getElementById('filterDisp').style.display='none'; 
	   document.getElementById('advance').style.display='none';  
	  } 
	   if(tabId=="advance")
	   { 
	   document.getElementById('advance').style.display='';  
	   document.getElementById('column').style.display='none';   
	   document.getElementById('sort').style.display='none';
	   document.getElementById('filterDisp').style.display='none';  
	   
	 
	  }
	     
 }
 
 
  function callToDateForAdvance(type)
 {
   var dateCom= document.getElementById("paraFrm_"+type+"Compare").value;
   if(dateCom=="7")
	 { 
	   document.getElementById(type+'ToField').style.display=''; 
	   document.getElementById(type+'ToLabel').style.display='';    
	   document.getElementById("paraFrm_"+type+"To").value="dd-mm-yyyy";
	   document.getElementById("paraFrm_"+type+"To").style.color = '#ACACAC';
	 }else{
	   document.getElementById(type+'ToField').style.display='none'; 
	   document.getElementById(type+'ToLabel').style.display='none';   
	 }
 }

function callGenerateReport()
 { 	  
     // for sort the filter   
   	  var sortBy = document.getElementById('paraFrm_sortBy1').value;
   	  var thenBy1 = document.getElementById('paraFrm_sortBy2').value;
   	  var thenBy2 = document.getElementById('paraFrm_sortBy3').value; 
   	  if(sortBy!="1" &&thenBy1!="1"){
   	    if(sortBy==thenBy1){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy1').focus();
   	     return false;
   	    } 
   	  }
   	  if(sortBy!="1" &&thenBy2!="1"){
   	    if(sortBy==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  }
   	    if(thenBy1!="1" &&thenBy2!="1"){
   	    if(thenBy1==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  } 
   	  // == end of sort======
   	  
     		    
     //for advance Tab
	         var dobCompare = document.getElementById('paraFrm_dobCompare').value;
	         var dobTo = document.getElementById('paraFrm_dobTo').value;  
	         var dobFrom = document.getElementById('paraFrm_dobFrom').value;
	         var dojCompare = document.getElementById('paraFrm_dojCompare').value;
	         var dojFrom = document.getElementById('paraFrm_dojFrom').value;
	         var dojTo = document.getElementById('paraFrm_dojTo').value;  
	         
	         var dorCompare = document.getElementById('paraFrm_dorCompare').value;
	         var dorFrom = document.getElementById('paraFrm_dorFrom').value;
	         var dorTo = document.getElementById('paraFrm_dorTo').value;  
	         
	         var ageOperator = document.getElementById('paraFrm_ageOperator').value;
	         var ageFilter = document.getElementById('paraFrm_ageFilter').value;  
	          //alert("dobCompare="+dobCompare);
	          //alert("dobFrom="+dobFrom);
	         if(dobCompare!="1")
	         {
	           if(dobFrom=="" || dobFrom=="dd-mm-yyyy"){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('dateOfBirthLabel').innerHTML);
			     document.getElementById('paraFrm_dobFrom').focus();
			     return false; 	
			     }else{
			     	if(!validateDate('paraFrm_dobFrom', "dateOfBirthLabel")){
			     		return false;
			     	}
			     }
			     if(dobCompare=="7"){
			      if(dobTo=="" || dobTo=="dd-mm-yyyy"){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('toDateLabel').innerHTML);
			     document.getElementById('paraFrm_dobTo').focus();
			     return false; 	
			     } else{
			     	if(!validateDate('paraFrm_dobTo', "toDateLabel")){
			     		return false;
			     	}
			     }
			     
			     if(!dateDifferenceEqual(dobFrom,dobTo,"paraFrm_dobFrom",'dateOfBirthLabel','toDateLabel'))
		           {
		            return false;
		           }
			     
	         }    
	        }
	        
	         if(dojCompare!="1")
	         {
	           if(dojFrom=="" || dojFrom=="dd-mm-yyyy"){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('dateOfJoinLabel').innerHTML);
			     document.getElementById('paraFrm_dojFrom').focus();
			     return false; 	
			     }else{
			     	if(!validateDate('paraFrm_dojFrom', "dateOfJoinLabel")){
			     		return false;
			     	}
			     }
			     if(dojCompare=="7"){
			      if(dojTo=="" || dojTo=="dd-mm-yyyy"){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('toDateLabel').innerHTML);
			     document.getElementById('paraFrm_dojTo').focus();
			     return false; 	
			     } else{
			     	if(!validateDate('paraFrm_dojTo', "toDateLabel")){
			     		return false;
			     	}
			     }
			     if(!dateDifferenceEqual(dojFrom,dojTo,"paraFrm_dojFrom",'dateOfJoinLabel','toDateLabel'))
		           {
		            return false;
		           }
	         }    
	        }
	        
	        if(dorCompare!="1")
	         {
	           if(dorFrom=="" || dorFrom=="dd-mm-yyyy"){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('dateOfRetLabel').innerHTML);
			     document.getElementById('paraFrm_dorFrom').focus();
			     return false; 	
			     }else{
			     	if(!validateDate('paraFrm_dorFrom', "dateOfRetLabel")){
			     		return false;
			     	}
			     }
			     if(dorCompare=="7"){
			      if(dorTo=="" || dorTo=="dd-mm-yyyy"){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('toDateLabel').innerHTML);
			     document.getElementById('paraFrm_dorTo').focus();
			     return false; 	
			     } else{
			     	if(!validateDate('paraFrm_dorTo', "toDateLabel")){
			     		return false;
			     	}
			     }
			     if(!dateDifferenceEqual(dorFrom,dorTo,"paraFrm_dorFrom",'dateOfRetLabel','toDateLabel'))
		           {
		            return false;
		           }
	         }    
	        }
	         if(ageOperator!="1")
	         {
	           if(ageFilter==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('ageLabel').innerHTML);
			     document.getElementById('paraFrm_ageFilter').focus();
			     return false; 	
			     } 
	        }  
		     
		    if(document.getElementById('reportViewV').checked){
		     document.getElementById('paraFrm').action="EmpConfForPension_viewEmpOnScreen.action";
		     document.getElementById('paraFrm').submit();
		    }
		    else{
		    	if(document.getElementById('paraFrm_reportType').value=="1"){
		    		alert("Please select Report Type");
		    		document.getElementById('paraFrm_reportType').focus();
		    		return false;
		    		}
				callReport('EmpConfForPension_generateReport.action');    
  		}
 }
 
 
</script>
 