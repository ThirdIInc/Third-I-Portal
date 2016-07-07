<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="LTAMisReport" method="post" id="paraFrm" theme="simple">  
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
 	    <tr>
			<td colspan="3" width="100%">   
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">LTA MIS Report</strong></td>
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
					        <s:submit cssClass="reset" action="LTAMisReport_reset" theme="simple" value="    Reset"  /> 
					        
					  </td> 
					</tr>
				</table>
			</td>
		 </tr>
		 
		  <tr>
			<td colspan="3"><s:hidden name="myPage"/>  <s:hidden name='aId'/> <s:hidden name='divId'/> <s:hidden name='settingNameDup'/> 
			
		         <table   border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr> 
							<td class="btn-middlebg">
							<div id="tabnav" style="vertical-align: bottom">
							 <ul>
								<li><a id="filtOpt" href="javascript:callTab('filtOpt','filter');">
									<div align="center"><span>Filter </span> </div></a>
								</li>
								
								<li><a  id="colDef" href="javascript:callTab('colDef','column');">
									<div align="center"><span>Column Defintion</span></div></a>
								</li> 
								
								<li><a id="sortOpt" href="javascript:callTab('sortOpt','sort');">
									<div align="center"><span>Sorting option</span></div></a>
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
							
		<tr>
			<td width="25%"><label  class = "set" name="applDiv" id="applDiv" ondblclick="callShowDiv(this);"><%=label.get("applDiv")%></label> :</td>
			<td width="20%"><s:hidden name="applDivisionCode"></s:hidden>
			<s:textarea name="applDivisionName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'LTAMisReport_f9applDiv.action'); "></td>
			<td  width="25%"><label  class = "set" name="applBranches" id="applBranches" ondblclick="callShowDiv(this);"><%=label.get("applBranches")%></label> :</td>
			<td width="20%"><s:hidden name="applBranchCode"></s:hidden>
			<s:textarea name="applBranchName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'LTAMisReport_f9applBranch.action'); "></td>
		</tr>
		
		<tr>
			
			<td  width="25%"><label  class = "set" name="applDept" id="applDept" ondblclick="callShowDiv(this);"><%=label.get("applDept")%></label> :</td>
			<td width="20%"><s:hidden name="applDeptCode"></s:hidden>
			<s:textarea name="applDeptName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'LTAMisReport_f9applDept.action'); "></td>
			<td  width="25%"><label  class = "set" name="applDesg" id="applDesg" ondblclick="callShowDiv(this);"><%=label.get("applDesg")%></label> :</td>
			<td width="20%"><s:hidden name="applDesgCode"></s:hidden>
			<s:textarea name="applDesgName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'LTAMisReport_f9applDesg.action'); "></td>
		
		</tr>
		<tr>
			
			<td  width="25%"><label  class = "set" name="applEType" id="applEType" ondblclick="callShowDiv(this);"><%=label.get("applEType")%></label> :</td>
			<td width="20%"><s:hidden name="applETypeCode"></s:hidden>
			<s:textarea name="applETypeName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'LTAMisReport_f9applEType.action'); "></td>
			<td  width="25%"><label  class = "set" name="applGrade" id="applGrade" ondblclick="callShowDiv(this);"><%=label.get("applGrade")%></label> :</td>
			<td width="20%"><s:hidden name="applGradeCode"></s:hidden>
			<s:textarea name="applGradeName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'LTAMisReport_f9applGrade.action'); "></td>
		</tr>
		
		<tr>
			
			<td  width="25%"><label  class = "set" name="applEmp" id="applEmp" ondblclick="callShowDiv(this);"><%=label.get("applEmp")%></label> :</td>
			<td width="20%"><s:hidden name="applEmpCode"></s:hidden>
			<s:textarea name="applEmpName" cols="25" rows="2" readonly="true"></s:textarea></td>
			<td width="4%"><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'LTAMisReport_f9applEmp.action'); "></td>
			
			<td  width="25%"></td>
			<td width="20%"></td>
			<td width="4%"></td>
		</tr>    
						  	
					</table> 
					
					
			<table id="sort" width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="505">
					<table width="100%" border="0" cellpadding="0" cellspacing="3"
						class="formbg">
						<tr>								
								<td width="100%" colspan="2" class="formtext"> <b> <label  class = "set"  id="selectSort"  name="selectSort" ondblclick="callShowDiv(this);"><%=label.get("selectSort")%></label></b> </td>
							 </tr>  
							<tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="sortByLabel"  name="sortByLabel" ondblclick="callShowDiv(this);"><%=label.get("sortByLabel")%></label> : </td>
								<td width="83%" colspan="1"> <select id='sortBy' name="sortBy1">
								<option value="1">--Select--</option>  
								<option value="Employee Id">Employee Id</option>
								<option value="Employee">Employee Name</option>
								</select>  </td>							
						 	</tr>  
						 	 <tr>								
								<td width="17%" colspan="1" class="formtext"></td>
								<td width="83%" colspan="1"> <s:radio  name="sortByOrder1" list="#{'A':'Ascending','D':'Descending'}"  /> </td>							
						 	</tr>  
						 	
						 	<tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="thenByLabel"  name="thenByLabel" ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label> : </td>
								<td width="83%" colspan="1">  <select id='thenBy1' name="sortBy2"> 
								<option value="1">--Select--</option> 
								<option value="Employee Id">Employee Id</option>
								<option value="Employee">Employee Name</option>
								</select>    </td>							
						 	</tr>  
						 	<tr>								
								<td width="17%" colspan="1" class="formtext"></td>
								<td width="83%" colspan="1"><s:radio  name="sortByOrder2" list="#{'A':'Ascending','D':'Descending'}" /></td>							
						 	  </tr>  
						 	 <tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="thenByLabel"  name="thenByLabel" ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label> : </td>
								<td width="83%" colspan="1"> <select id='thenBy2' name="sortBy3">
								<option value="1">--Select--</option> 
								<option value="Employee Id">Employee Id</option>
								<option value="Employee">Employee Name</option>
								</select>  </td>							
						 	  </tr>  
						  	 <tr>								
								<td width="17%" colspan="1" class="formtext"></td>
								<td width="83%" colspan="1"> <s:radio  name="sortByOrder3" list="#{'A':'Ascending','D':'Descending'}"  /></td>							
						 	 </tr>  
					</table>
					</td>

					<td width="397" valign="top">
					<table width="366" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td class="text_head" colspan="5"><strong
								class="forminnerhead">Select appropriate order of
							columns for report generation</strong></td>
						</tr>

						<tr>
							<td width="123"><select id="columnOrdering" size="10"
								name="columnOrdering" multiple="true">

							</select></td>
							<td width="242"><input type="button" class="shuffleUp"
								onclick="listbox_move('columnOrdering', 'up');" /> <br />
							<br />
							<input type="button" class="shuffleDown"
								onclick="listbox_move('columnOrdering', 'down');" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
					
					<table id="column" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
							 <tr>								
								<td width="100%" colspan="6" class="formtext"> <b> <label  class = "set"  id="selectCoumn"  name="selectCoumn" ondblclick="callShowDiv(this);"><%=label.get("selectCoumn")%></label></b> </td>
							 </tr>  
							 <s:hidden name='checkedCount' value='11'/>
							<tr>								
								<td width="5%" colspan="1" class="formtext"><s:checkbox name="empTokenChk" disabled="true"  />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="employee.id"  name="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label> </td>
							    <td width="5%" colspan="1" class="formtext"><s:checkbox name="empNameChk" disabled="true" />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="employee"  name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> </td>
						        <td width="5%" colspan="1" class="formtext"><s:checkbox name="branchChk" onclick="addItem('branch',this);"   />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="branch"  name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> </td>
							</tr>   
						  	
						  	<tr>	
						  		<td width="5%" colspan="1" class="formtext"><s:checkbox name="blockYrChk"  onclick="addItem('blockYearLabel',this);"  />  </td>
							    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="blockYearLabel"  name="blockYearLabel" ondblclick="callShowDiv(this);"><%=label.get("blockYearLabel")%></label> </td>
						        <td width="5%" colspan="1" class="formtext"><s:checkbox name="visitYearChk"  onclick="addItem('yearOfVisitLabel',this);"  />  </td>
							    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="yearOfVisitLabel"  name="yearOfVisitLabel" ondblclick="callShowDiv(this);"><%=label.get("yearOfVisitLabel")%></label> </td>
					            <td width="5%" colspan="1" class="formtext"><s:checkbox name="claimDateChk" onclick="addItem('claimDateLabel2',this);"  />  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="claimDateLabel2"  name="claimDateLabel" ondblclick="callShowDiv(this);"><%=label.get("claimDateLabel")%></label> </td>
					        </tr>   
						  	
						  	<tr>	
						  	
						  	<td width="5%" colspan="1" class="formtext"><s:checkbox name="claimTypeChk" onclick="addItem('claimTypeLabel',this);"  />  </td>
						    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="claimTypeLabel"  name="claimTypeLabel" ondblclick="callShowDiv(this);"><%=label.get("claimTypeLabel")%></label> </td>
					    	<td width="5%" colspan="1" class="formtext"><s:checkbox name="claimAmtChk"  onclick="addItem('claimAmtLabel',this);" />  </td>
					        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="claimAmtLabel"  name="claimAmtLabel" ondblclick="callShowDiv(this);"><%=label.get("claimAmtLabel")%></label> </td>
						    <td width="5%" colspan="1" class="formtext"><s:checkbox name="exemptedChk" onclick="addItem('isTaxExemptedLabel',this);"  />  </td>
						    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="isTaxExemptedLabel"  name="isTaxExemptedLabel" ondblclick="callShowDiv(this);"><%=label.get("isTaxExemptedLabel")%></label> </td>
					 	  	</tr>   
						  	
						   <tr>  
						  	 <td width="5%" colspan="1" class="formtext"><s:checkbox name="exemptedYearChk" onclick="addItem('yearOfExemptionLabel',this);"   />  </td>
						     <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="yearOfExemptionLabel"  name="yearOfExemptionLabel" ondblclick="callShowDiv(this);"><%=label.get("yearOfExemptionLabel")%></label> </td>
							 
						 	 </tr>  
				</table> 
				
				<table id="advance" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
				              <tr>								
								<td width="100%" colspan="3" class="formtext"> <b> <label  class = "set"  id="selectAdvanceFilter"  name="selectAdvanceFilter" ondblclick="callShowDiv(this);"><%=label.get("selectAdvanceFilter")%></label> </b> </td>
							 </tr> 
							
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="claimDateLabel1"  name="claimDateLabel" ondblclick="callShowDiv(this);"><%=label.get("claimDateLabel")%></label> : </td>
								   <td width="10%" colspan="1" class="formtext"> 
								    <s:select headerKey="1" headerValue="--Select--" name="claimDateCompare"  list="#{'2':'On','3':'Before','4':'After' ,'5':'On before' ,'6':'On after','7':'From'}"  theme="simple"  onchange="callToDateForAdvance('claimDate');" />
								    </td>
							    <td width="68%" colspan="1" class="formtext"> 
							     <table width="100%" border="0">
							       <tr>
							          <td width="32%"> <s:textfield name="claimDateFrom" size="14" onkeypress="return numbersWithHiphen();" maxlength="10"  onfocus="clearText('paraFrm_claimDateFrom','dd-mm-yyyy');" onblur="setText('paraFrm_claimDateFrom','dd-mm-yyyy');" />  <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_claimDateFrom','DDMMYYYY');"  >  </td>
							          <td   nowrap="nowrap" id="claimDateToLabel">  <label  class = "set"  id="toDateLabel"  name="toDateLabel" ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> : </td>
							          <td id="claimDateToField" >  <s:textfield name="claimDateTo" size="15" onkeypress="return numbersWithHiphen();" maxlength="10" onfocus="clearText('paraFrm_claimDateTo','dd-mm-yyyy');" onblur="setText('paraFrm_claimDateTo','dd-mm-yyyy');"   />   <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_claimDateTo','DDMMYYYY');">  </td>
							     </tr>
							     </table>
							    
							     </td>
						    </tr>
						    
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="yearOfVisitLabel"  name="yearOfVisitLabel" ondblclick="callShowDiv(this);"><%=label.get("yearOfVisitLabel")%></label> : </td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="yearOfVisitOperator"  list="#{'=':'=','<':'<','>':'>','<=':'<=','>=':'>='}"  onchange="callAdvCombo('yearOfVisitOperator','yearOfVisitFilter');"  /> </td>
							    <td width="68%" colspan="1" class="formtext"> &nbsp;<s:textfield name="yearOfVisitFilter" size="14" onkeypress="return numbersOnly();" maxlength="10"  />  </td>
						    </tr> 
						    
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="claimAmtLabel1"  name="claimAmtLabel" ondblclick="callShowDiv(this);"><%=label.get("claimAmtLabel")%></label> : </td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="claimAmtOperator"  list="#{'=':'=','<':'<','>':'>','<=':'<=','>=':'>='}"  onchange="callAdvCombo('claimAmtOperator','claimAmtFilter');"  /> </td>
							    <td width="68%" colspan="1" class="formtext"> &nbsp;<s:textfield name="claimAmtFilter" size="14" onkeypress="return numbersOnly();" maxlength="10"  />  </td>
						    </tr> 
						    
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="yearOfExemptionLabel1"  name="yearOfExemptionLabel" ondblclick="callShowDiv(this);"><%=label.get("yearOfExemptionLabel")%></label> : </td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="exemptionOperator"  list="#{'=':'=','<':'<','>':'>','<=':'<=','>=':'>='}"  onchange="callAdvCombo('exemptionOperator','claimAmtFilter');"  /> </td>
							    <td width="68%" colspan="1" class="formtext"> &nbsp;<s:textfield name="exemptionFilter" size="14" onkeypress="return numbersOnly();" maxlength="10"  />  </td>
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
								<td width="83%" colspan="1" class="formtext" > <div id="reportTypeDiv"> <s:select  headerKey="1" headerValue="--Select--" name="reportType" list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}"  /> </div>  </td>
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
							<s:submit cssClass="reset" action="LTAMisReport_reset" theme="simple" value="    Reset"  />  
							
							</td> 
						  </tr>
					  </table>
					</td>					
			    </tr>
			    <s:hidden name='hiddenColumns' />		
			     <s:hidden name='backFlag' />
			     <s:hidden name="hiddenSortBy" />
			     <s:hidden name="hiddenThenBy1" />
			      <s:hidden name="hiddenThenBy2" />	
			      <input type="hidden" name='sortByOrder1' value="%{sortByOrder1}"/>
			      <input type="hidden" name='sortByOrder2' value="%{sortByOrder2}"/>
			      <input type="hidden" name='sortByOrder3' value="%{sortByOrder3}"/>
			      		
			</table>  
</s:form> 
 

<script type="text/javascript">    
 
var misCounter=0;
 callOnLoad();
 setFieldsOnload();
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
	//document.getElementById('paraFrm_divisionChk').checked=true;
	/*alert(1);
	document.getElementById('paraFrm_branchChk').checked=true;
	document.getElementById('paraFrm_blockYrChk').checked=true;
	alert(2);
	document.getElementById('paraFrm_visitYearChk').checked=true;
	document.getElementById('paraFrm_claimDateChk').checked=true;
	alert(3);
	document.getElementById('paraFrm_claimTypeChk').checked=true;
	document.getElementById('paraFrm_claimAmtChk').checked=true;
	alert(4);
	document.getElementById('paraFrm_exemptedChk').checked=true;
	document.getElementById('paraFrm_exemptedYearChk').checked=true;
	
	alert(5);*/
	   document.getElementById('claimDateToField').style.display='none'; 
	   document.getElementById('claimDateToLabel').style.display='none'; 
	   var fieldName =["paraFrm_claimDateFrom"];
   setTextArray(fieldName,"dd-mm-yyyy");
   
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
  // alert(dateCom);
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
 
   function setFieldsOnload(){
    	try{
    	
    	var columnOrderValues = document.getElementById('paraFrm_hiddenColumns').value;
    	//alert(columnOrderValues);
    	
    	if(columnOrderValues!=""){
	    	columnOrderValues=columnOrderValues.substr(0,columnOrderValues.length-1);
	       	var splittedValue=columnOrderValues.split(",");
	       	//alert(2);
	       	for(var i=0;i<splittedValue.length;i++){
	       		//alert(splittedValue[i]);
	       		var sortBy = document.getElementById("sortBy");
		        var option = document.createElement("option");
		        var thenBy1 = document.getElementById('thenBy1');
		        var option1 = document.createElement("option");
		   	  	var thenBy2 = document.getElementById('thenBy2');
		   	  	var option2 = document.createElement("option"); 
		   	  	var opt = document.createElement("option");
	   	  	
	       		var splitDash = splittedValue[i].split("-");
	       		
	       		// Assign text and value to Option object
		        opt.text = splitDash[1];
		        opt.value = splitDash[1];
		        
	       		option.text = splitDash[1];
		        option.value = splitDash[1];
		        
		        option1.text = splitDash[1];
		        option1.value = splitDash[1];
		        
		        option2.text = splitDash[1];
		        option2.value = splitDash[1];
		        
		        // Add an Option object to Drop Down/List Box
		       	document.getElementById("columnOrdering").options.add(opt); 
		        
		        try {
					sortBy.add(option, null); //Standard 
				}catch(error) {
					sortBy.add(option); // IE only
				}
				try {
					thenBy1.add(option1, null);
				}catch(error) {
					thenBy1.add(option1);
				}
				try {
					thenBy2.add(option2, null);
				}catch(error) {
					thenBy2.add(option2);
				}
	   		}
       	}
       	//SET SELECTED OPTION ON BACK AND SEARCH (SORTING OPTIONS)
       	//alert(3);
       	var hidSortBy = document.getElementById('paraFrm_hiddenSortBy').value;
       	document.getElementById('sortBy').value=hidSortBy;
		var hidThenBy1 = document.getElementById('paraFrm_hiddenThenBy1').value;
			document.getElementById('thenBy1').value=hidThenBy1;
		var hidThenBy2 = document.getElementById('paraFrm_hiddenThenBy2').value;
			document.getElementById('thenBy2').value=hidThenBy2;
		var sortByOrder1=document.getElementById('sortByOrder1').value;
		var sortByOrder2=document.getElementById('sortByOrder2').value;
		var sortByOrder3=document.getElementById('sortByOrder3').value;
		document.getElementById('paraFrm_sortByOrder1'+sortByOrder1).checked=true;
		document.getElementById('paraFrm_sortByOrder2'+sortByOrder2).checked=true;
		document.getElementById('paraFrm_sortByOrder3'+sortByOrder3).checked=true;
    	}catch(e){
    		//alert(e);
    	}
    }
    
    function callSortBy(){
    	var sortBy = document.getElementById('sortBy').value;
    	document.getElementById('paraFrm_hiddenSortBy').value=sortBy;
    	
    }
    function callThenBy1(){
    	var thenBy1 = document.getElementById('thenBy1').value;
   		document.getElementById('paraFrm_hiddenThenBy1').value=thenBy1;
    	
    }
    function callThenBy2(){
    	var thenBy2 = document.getElementById('thenBy2').value;
   		document.getElementById('paraFrm_hiddenThenBy2').value=thenBy2;
    	
    }
function addItem(labelName,id)
    {
        try{
        	var labelNameCheck = document.getElementById(labelName).innerHTML;
	        var checkedValue = id.checked;
	        // Create an Option object                
	        var opt = document.createElement("option");
	        
	        var sortBy = document.getElementById("sortBy");
	        var option = document.createElement("option");
	        var thenBy1 = document.getElementById('thenBy1');
	        var option1 = document.createElement("option");
	   	  	var thenBy2 = document.getElementById('thenBy2');
	   	  	var option2 = document.createElement("option"); 
	   	  	var backFlag=document.getElementById('paraFrm_backFlag').value;
	        //alert("backFlag .. "+backFlag);
	        if(checkedValue){
	        	//IF VALUE PRESENT INITIAL COUNTER SHOULD BE MAX KEY
	        	var checkValue=document.getElementById('paraFrm_hiddenColumns').value;
	        	//alert("backFlag .. "+backFlag);
	        	//alert("checkValue .. "+checkValue);
	        	if(checkValue!="" && backFlag=="true" ){
	        		checkValue=checkValue.substr(0,checkValue.length-1);
	        		//alert("checkValue .. "+checkValue);
	        		var splitComma=checkValue.split(",");
	        		//alert("splitComma .. "+splitComma);
	        		var splitHiphen = "";
	        		for(var i=0;i<splitComma.length;i++){
	        			splitHiphen = splitComma[i].split("-");
	        		}
	        		//alert(splitHiphen[0]);
	        		misCounter = splitHiphen[0];
	        	}
	        
	        
	        	misCounter++;
	        	//alert('misCounter='+misCounter);
		        // Assign text and value to Option object
		        opt.text = labelNameCheck;
		        opt.value = labelNameCheck;
		        
		        option.text = labelNameCheck;
		        option.value = labelNameCheck;
		        
		        option1.text = labelNameCheck;
		        option1.value = labelNameCheck;
		        
		        option2.text = labelNameCheck;
		        option2.value = labelNameCheck;
		        
		        // Add an Option object to Drop Down/List Box
		        document.getElementById("columnOrdering").options.add(opt); 
		        
		        try {
					sortBy.add(option, null); //Standard 
				}catch(error) {
					//alert('1');
					sortBy.add(option); // IE only
				}
				try {
					thenBy1.add(option1, null);
				}catch(error) {
					thenBy1.add(option1);
				}
				try {
					thenBy2.add(option2, null);
				}catch(error) {
					thenBy2.add(option2);
				}
		        
		        var hiddenValue=document.getElementById('paraFrm_hiddenColumns').value+
		        			misCounter+"-"+labelNameCheck+",";
		        					
		        document.getElementById('paraFrm_hiddenColumns').value=hiddenValue;
		        
		        //alert("hiddenValue .. "+hiddenValue);
	        }else{
	        	var hiddenValue=document.getElementById('paraFrm_hiddenColumns').value;
	        	hiddenValue=hiddenValue.substr(0,hiddenValue.length-1);
	        	var splittedValue=hiddenValue.split(",");
	        	var finalValue="";
	        	var count=1;
	        	misCounter--;
	        	for(var i=0;i<splittedValue.length;i++){
	        		var splitDash = splittedValue[i].split("-");
	        	
	        		if(labelNameCheck!=splitDash[1]){
	        			finalValue+=count+"-"+splitDash[1]+",";
	        			count++;
	        		}else{
	        			for(var j = 0; j < document.getElementById("columnOrdering").childNodes.length; j++) {
	        				var opt1 = document.getElementById("columnOrdering").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("columnOrdering").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("sortBy").childNodes.length; j++) {
	        				var opt1 = document.getElementById("sortBy").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("sortBy").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("thenBy1").childNodes.length; j++) {
	        				var opt1 = document.getElementById("thenBy1").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("thenBy1").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("thenBy2").childNodes.length; j++) {
	        				var opt1 = document.getElementById("thenBy2").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("thenBy2").removeChild(opt1);
	        					break;
	        				}
	        			}
	        		}
	        	}
	        	document.getElementById('paraFrm_hiddenColumns').value=finalValue;
	        }
	   }catch(e){
	   		alert(e);
	   }
    }
function callGenerateReport()
 { 	  
     // for sort the filter   
   	  var sortBy = document.getElementById('sortBy').value;
   	  var thenBy1 = document.getElementById('thenBy1').value;
   	  var thenBy2 = document.getElementById('thenBy2').value; 
   	  
   	  if(sortBy!=1){
   	    if(sortBy==thenBy1){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('thenBy1').focus();
   	     return false;
   	    } 
   	    if(sortBy==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('thenBy2').focus();
   	     return false;
   	    } 
   	    }
   	 
  if(thenBy1!=1){
   	   
   	    if(thenBy1==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('thenBy2').focus();
   	     return false;
   	    } 
   	   }
   	  
   	
   	  // == end of sort======
   	  
     		    
     //for advance Tab
	         var claimDateCompare = document.getElementById('paraFrm_claimDateCompare').value;
	         var claimDateTo = document.getElementById('paraFrm_claimDateTo').value;  
	         var claimDateFrom = document.getElementById('paraFrm_claimDateFrom').value;
	         	         
	         var yearOfVisitOperator = document.getElementById('paraFrm_yearOfVisitOperator').value;
	         var yearOfVisitFilter = document.getElementById('paraFrm_yearOfVisitFilter').value; 
	         var claimAmtOperator = document.getElementById('paraFrm_claimAmtOperator').value;
	         var claimAmtFilter = document.getElementById('paraFrm_claimAmtFilter').value;  
	         var exemptionOperator = document.getElementById('paraFrm_exemptionOperator').value;
	         var exemptionFilter = document.getElementById('paraFrm_exemptionFilter').value;  
	          
	          //alert("dobCompare="+dobCompare);
	          //alert("dobFrom="+dobFrom);
	         if(claimDateCompare!="1")
	         {
	           if(claimDateFrom=="" || claimDateFrom=="dd-mm-yyyy"){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('claimDateLabel1').innerHTML);
			     document.getElementById('paraFrm_claimDateFrom').focus();
			     return false; 	
			     }else{
			     	if(!validateDate('paraFrm_claimDateFrom', "claimDateLabel1")){
			     		return false;
			     	}
			     }
			     if(claimDateCompare=="7"){
			      if(claimDateTo=="" || claimDateTo=="dd-mm-yyyy"){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('toDateLabel').innerHTML);
			     document.getElementById('paraFrm_claimDateTo').focus();
			     return false; 	
			     } else{
			     	if(!validateDate('paraFrm_claimDateTo', "toDateLabel")){
			     		return false;
			     	}
			     }
			     
			     if(!dateDifferenceEqual(claimDateFrom,claimDateTo,"paraFrm_claimDateFrom",'claimDateLabel1','toDateLabel'))
		           {
		            return false;
		           }
			     
	         }    
	        }
	         if(yearOfVisitOperator!="1")
	         {
	           if(yearOfVisitFilter==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('yearOfVisitLabel').innerHTML);
			     document.getElementById('paraFrm_yearOfVisitFilter').focus();
			     return false; 	
			     } 
	        }  
	        if(claimAmtOperator!="1")
	         {
	           if(claimAmtFilter==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('claimAmtLabel').innerHTML);
			     document.getElementById('paraFrm_claimAmtFilter').focus();
			     return false; 	
			     } 
	        }
	         if(exemptionOperator!="1")
	         {
	           if(exemptionFilter==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('yearOfExemptionLabel').innerHTML);
			     document.getElementById('paraFrm_exemptionFilter').focus();
			     return false; 	
			     } 
	        }   
		    if(document.getElementById('reportViewV').checked){
		     document.getElementById('paraFrm').action="LTAMisReport_viewOnScreen.action";
		     document.getElementById('paraFrm').submit();
		    }
		    else{
		    	if(document.getElementById('paraFrm_reportType').value=="1"){
		    		alert("Please select Report Type");
		    		document.getElementById('paraFrm_reportType').focus();
		    		return false;
		    		}
				callReport('LTAMisReport_generateReport.action');    
  		}
 }
 
 function listbox_move(listID, direction) {
		try{
			var listbox = document.getElementById(listID);
			var selIndex = listbox.selectedIndex;

			if(-1 == selIndex) {
				alert("Please select an option to move.");
				return;
			}
			
			//alert(selIndex);

			var increment = -1;
			if(direction == 'up')
				increment = -1;
			else
				increment = 1;

			/*if((selIndex + increment) < 0 ||
				(selIndex + increment) > (listbox.options.length-1)) {
				alert('in if');
				return;
			}*/
			if((selIndex + increment) < 0){
				alert("You cannot move up the record");
				return;
			}
			if((selIndex + increment) > (listbox.options.length-1)){
				alert("You cannot move down the record");
				return;
			}

			var selValue = listbox.options[selIndex].value;
			var selText = listbox.options[selIndex].text;
			listbox.options[selIndex].value = listbox.options[selIndex + increment].value
			listbox.options[selIndex].text = listbox.options[selIndex + increment].text

			listbox.options[selIndex + increment].value = selValue;
			listbox.options[selIndex + increment].text = selText;

			listbox.selectedIndex = selIndex + increment;
			
        	var finalValue="";
        	var count=1;
        	for(var i=0;i<listbox.options.length;i++){
        		var opt1 = listbox.options[i];
        		//alert('Values in listbox : '+opt1.text);
        		finalValue+=count+"-"+opt1.text+",";
        		count++;
        	}
			document.getElementById('paraFrm_hiddenColumns').value=finalValue;
			}catch(e){
				alert(e);
			}
		}
 
 
</script>
 