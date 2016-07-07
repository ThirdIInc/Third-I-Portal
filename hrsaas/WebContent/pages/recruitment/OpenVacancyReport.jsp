   <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="OpenVacReport" method="post" id="paraFrm" theme="simple">  
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
 	    <tr>
			<td colspan="3" width="100%">   
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Open Vacancy Analysis</strong></td>
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
					           <input type="button" class="token" onclick="return callReportVacancies();"	value=" Generate Report" />
					         </s:if>
					        <s:submit cssClass="reset" action="OpenVacReport_reset" theme="simple" value="    Reset"  /> 
					        <input type="button" class="token"  	value=" Save report criteria" onclick="callSave();"/>
					  </td> 
					</tr>
				</table>
			</td>
		 </tr>
		 
		  <tr>
	        <td colspan="3">
	           <table width="100%" border="0" cellpadding="1" cellspacing="1" >
					<tr>
					    <td   nowrap="nowrap"><label  class = "set"  id="save.setting"  name="save.setting" ondblclick="callShowDiv(this);"><%=label.get("save.setting")%></label> :</td> 
					    <td width="85%"> <s:select headerKey="B" headerValue="--Select--" name="searchSetting" list="map" onchange="callShowRecord();"  />
					      <s:hidden name="reqCode" /> <s:hidden name="hiringManagerId" />   
					      <s:hidden name="recruiterCode"/>   <s:hidden name="positionId"/> 
					      <s:hidden name="common"/>  <s:hidden name="divCount"/>  
					       <s:hidden name="selectedReq"/>   <s:hidden name="hidSelectedReqName"/>   <s:hidden name="editReqFlag"/>  
					     <s:hidden name="checkedCount" value="14"/>  <s:hidden name="reqStatus"/>  <s:hidden  name="exportAll" id="exportAll" value="on"/> 
					    <s:hidden name="radioStatus"/>  <s:hidden name="radioReq"/>  <s:hidden name="radioRecr"/> <s:hidden name="radioHirMng"/> <s:hidden name="radioPosition"/> 
					    </td> 
					</tr>
				</table>
			</td>
		 </tr>   
		  <tr>
			<td colspan="3">  <s:hidden name='aId'/> <s:hidden name='divId'/> <s:hidden name='backFlag'/>  <s:hidden name='settingNameDup'/> 
			
		         <table   border="0" cellpadding="0" cellspacing="0" class="formbg" width="100%">
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
									<div align="center"><span>Column Definition</span></div></a>
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
							  <td   nowrap="nowrap" >  <input type="radio" value="C"  <s:property value="radioReq"/> name="radioOption"  onclick="callRadioOptionFun('C');" >   </td>							
								<td width="20%" colspan="1" nowrap="nowrap" class="formtext"> <label  class = "set"  id="RequisitioncodeLabel1"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> : </td>
								<td width="20%" colspan="1"   ><s:textfield name="reqname" size="25" theme="simple" readonly="true" />  </td>
								<td width="60%" colspan="1" id="f9ReqCode"  > <img src="../pages/images/recruitment/search2.gif" height="15" align="absmiddle" width="16" 
									onclick="callF9ReqCode('selF9');"></td>							
						 	</tr>   
						 	<tr>	
						 		 <td   nowrap="nowrap" ><input type="radio" value="N"  <s:property value="radioRecr"/> name="radioOption" onclick="callRadioOptionFun('N');"  >   </td>		
								<td width="20%" colspan="1" class="formtext"><label  class = "set"  id="RecruiternameLabel1"  name="rec.name" ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label> : </td>
								<td width="20%" colspan="1"   ><s:textfield name="recruiterName" size="25" theme="simple" readonly="true" />  </td>
								<td width="60%" colspan="1" id="f9RecruiterName" > <img src="../pages/images/recruitment/search2.gif" height="15" align="absmiddle" width="16"  onclick="callF9Recruiter('selF9');"></td>		 
						  	</tr>  
						   
						  	<tr>			
						  	    <td  nowrap="nowrap" ><input type="radio" value="M"  <s:property value="radioHirMng"/> name="radioOption"  onclick="callRadioOptionFun('M');"  >  </td>			
								<td width="20%" colspan="1" class="formtext"><label  class = "set"  id="HiringmanagerLabel1"  name="hiring.mgr" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> : </td>
								<td width="20%" colspan="1"   ><s:textfield name="hiringManagerName" size="25" theme="simple" readonly="true" />  </td>
								<td width="60%" colspan="1" id="f9HiringManger" > <img src="../pages/images/recruitment/search2.gif" height="15" align="absmiddle" width="16"  onclick="callF9HiringManager('selF9');"></td>		 
						  	</tr>   
						  	<tr>	
						  	    <td  nowrap="nowrap" > <input type="radio" value="P" name="radioOption" <s:property value="radioPosition"/>  onclick="callRadioOptionFun('P');" > </td>					
								<td width="20%" colspan="1" class="formtext"> <label  class = "set"  id="PositionLabel1"  name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> : </td>
								<td width="20%" colspan="1" ><s:textfield name="position" size="25" theme="simple" readonly="true" />  </td>
								<td width="60%" colspan="1" id="f9Position" > <img src="../pages/images/recruitment/search2.gif" height="15" align="absmiddle" width="16"  onclick="callF9Position('selF9');">
								 </td>		 
						  	</tr>  
						  	<tr>	
						  	 <td >  </td>
						  	<td width="20%" colspan="1" class="formtext"><label  class = "set"  id="reqs.date"  name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> : </td>					
								 <td width="80%" colspan="2" >
								   <table width="100%">
								      <tr> 
								         <td nowrap="nowrap"> <s:select headerKey="1" headerValue="--Select--" name="dateFilter" list="#{'O':'On','B':'Before','A':'After' ,'OB':'On before' ,'OA':'On after','F':'From'}" onchange="callToDateDispOnClick();" />  </td>
								         <td width="10%" colspan="1" > <s:textfield name="frmDate"  size="9" maxlength="10"  onkeypress="return numbersWithHiphen();" onkeypress="return numbersOnly();" onfocus="clearText('paraFrm_frmDate','dd-mm-yyyy')" onblur="setText('paraFrm_frmDate','dd-mm-yyyy')"  />   </td>
								         <td width="10%" colspan="1" align="left"> <img src="../pages/images/recruitment/Date.gif" id="fromDateIcon" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">  </td>
								         <td width="15%" colspan="1" > <div id="toDateDivLabel"> <label  class = "set"  id="toDateLabel"  name="toDateLabel" ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> : </div> </td>
								         <td width="50%" colspan="2" ><div id="toDateDiv">  <s:textfield name="toDate"  size="10" maxlength="10"  onkeypress="return numbersWithHiphen();" onkeypress="return numbersOnly();"  onfocus="clearText('paraFrm_toDate','dd-mm-yyyy')" onblur="setText('paraFrm_toDate','dd-mm-yyyy')"  /> &nbsp; 
								                       <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								                       </div>
								            </td>
								      </tr>  
								   </table>
								</td>
						  	</tr>    
						  	<tr>	 
						  	 <td >  </td>
								 <td width="100%" colspan="3" >
								   <table width="100%" border="0">
								      <tr> 
								        <td width="19%" colspan="1"> <label  class = "set"  id="selectReqLabel"  name="selectReqLabel" ondblclick="callShowDiv(this);"><%=label.get("selectReqLabel")%></label> :    </td>
								        <td width="41%" colspan="1" align="left" > <s:textarea name="selectedReqName" cols="40" rows="2" readonly="true" theme="simple"/>  </td>
								         <td width="40">
								         <table width="100%">
								          <tr>
								            <td width="40%"> <div  id="selectReqDiv"> <input type="button" class="token" value=" Select Requisition " onclick="callSelectReq('simple');">   </div> 
								          </td>
								           <td width="60%" colspan="1" > <div id="textAreaId1" > <input type="button" class="token" value=" Edit Selection " onclick="callSelectReq('edit');">     </td>
							
								          </tr> 
								         </table> 
								         </td>
								     </tr>  
								   </table>
								</td>
						  	</tr>    
					</table> 
					
					   <table id="sort"  width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
					        <tr>								
								<td width="100%" colspan="2" class="formtext"> <b> <label  class = "set"  id="selectSort"  name="selectSort" ondblclick="callShowDiv(this);"><%=label.get("selectSort")%></label></b> </td>
							 </tr>  
							<tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="sortByLabel"  name="sortByLabel" ondblclick="callShowDiv(this);"><%=label.get("sortByLabel")%></label> : </td>
								<td width="83%" colspan="1">  <s:select headerKey="1" headerValue="--Select--" name="sortBy" list="#{'R':'Requisition code' ,'P':'Position','D':'Requisition date','H':'Hiring manager','T':'Total number of vacancy','N':'Recruiter name','B':'Required by date','V':'Number of vacancy assigned',
                                       'O':'Open vacancy','C':'Closed vacancy'}"  />  </td>							
						 	</tr>  
						 	 <tr>								
								<td width="17%" colspan="1" class="formtext">&nbsp; </td> <s:hidden name="sortByAsc"/> <s:hidden name="sortByDsc"/>
								<td width="83%" colspan="1"> <input type="radio" value="A" name="sortByOrder" <s:property value="sortByAsc"/>  > Ascending    <input type="radio" value="D" name="sortByOrder" <s:property value="sortByDsc"/> > Descending     </td>							
						 	</tr>  
						 	
						 	<tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="thenByLabel"  name="thenByLabel" ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label> : </td>
								<td width="83%" colspan="1">  <s:select headerKey="1" headerValue="--Select--" name="thenBy1" list="#{'R':'Requisition code' ,'P':'Position','D':'Requisition date','H':'Hiring manager','T':'Total number of vacancy','N':'Recruiter name','B':'Required by date','V':'Number of vacancy assigned',
                                       'O':'Open vacancy','C':'Closed vacancy'}"  />    </td>							
						 	</tr>  
						 	<tr>								
								<td width="17%" colspan="1" class="formtext">&nbsp; </td> <s:hidden name="thenByOrder1Asc"/> <s:hidden name="thenByOrder1Dsc"/>
								<td width="83%" colspan="1"> <input type="radio" value="A" name="thenByOrder1" <s:property value="thenByOrder1Asc"/> > Ascending    <input type="radio" value="D" name="thenByOrder1" <s:property value="thenByOrder1Dsc"/>> Descending     </td>							
						 	  </tr>  
						 	 <tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="thenByLabel"  name="thenByLabel" ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label> : </td>
								<td width="83%" colspan="1">   <s:select headerKey="1" headerValue="--Select--" name="thenBy2" list="#{'R':'Requisition code' ,'P':'Position','D':'Requisition date','H':'Hiring manager','T':'Total number of vacancy','N':'Recruiter name','B':'Required by date','V':'Number of vacancy assigned',
                                       'O':'Open vacancy','C':'Closed vacancy'}"  />  </td>							
						 	  </tr>  
						  	 <tr>								
								<td width="17%" colspan="1" class="formtext">&nbsp; <s:hidden name="thenByOrder2Asc"/> <s:hidden name="thenByOrder2Dsc"/> </td>
								<td width="83%" colspan="1"> <input type="radio" value="A" name="thenByOrder2"  <s:property value="thenByOrder2Asc"/>  > Ascending    <input type="radio" value="D" name="thenByOrder2" <s:property value="thenByOrder2Dsc"/> > Descending     </td>							
						 	 </tr>  
					</table> 
					
					<table id="column" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
							 <tr>								
								<td width="100%" colspan="6" class="formtext"> <b> <label  class = "set"  id="selectCoumn"  name="selectCoumn" ondblclick="callShowDiv(this);"><%=label.get("selectCoumn")%></label></b> </td>
							 </tr>  
							 
							<tr>								
								<td width="5%" colspan="1" class="formtext"> <s:hidden name="reqCodeChk" id="reqCodeChk" value="on" /> <input type="checkbox" name="reqCodeChk1" id="reqCodeChk1" <s:property value="reqCodeChk"/> onclick="checkedCounter('reqCodeChk');" disabled="disabled"  >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="RequisitioncodeLabel2"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </td>
							    <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="recruiterChk" id="recruiterChk"  <s:property value="recruiterChk"/>  onclick="checkedCounter('recruiterChk');" >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="RecruiternameLabel2"  name="rec.name" ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label> </td>
						        <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="openVacChk" id="openVacChk" <s:property value="openVacChk"/>  onclick="checkedCounter('openVacChk');"  >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="OpenvacancyLabel1"  name="OpenvacancyLabel" ondblclick="callShowDiv(this);"><%=label.get("OpenvacancyLabel")%></label> </td>    
							</tr>   
						  	
						  	<tr>	
						  	    <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="postionChk"  id="postionChk"  <s:property value="postionChk" /> onclick="checkedCounter('postionChk');"   >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="PositionLabel2"  name="PositionLabel" ondblclick="callShowDiv(this);"><%=label.get("PositionLabel")%></label> </td>
						         <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="requiredDateChk"  id="requiredDateChk"   <s:property value="requiredDateChk"/> onclick="checkedCounter('requiredDateChk');"  >  </td>
							    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="RequiredDateLabel1"  name="RequiredDateLabel" ondblclick="callShowDiv(this);"><%=label.get("RequiredDateLabel")%></label> </td>
					            <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="closedvacChk" id="closedvacChk"  <s:property value="closedvacChk"/> onclick="checkedCounter('closedvacChk');"  >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="ClosedvacancyLabel1"  name="ClosedvacancyLabel" ondblclick="callShowDiv(this);"><%=label.get("ClosedvacancyLabel")%></label> </td>
					        </tr>   
						  	
						  	<tr>	
						  	
						  	<td width="5%" colspan="1" class="formtext"><input type="checkbox" name="dateChk" id="dateChk"  <s:property value="dateChk"/>  onclick="checkedCounter('dateChk');"  >  </td>
						    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="RequisitiondateLabel2"  name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> </td>
					    	<td width="5%" colspan="1" class="formtext"><input type="checkbox" name="totalVacChk"  id="totalVacChk"  <s:property value="totalVacChk"/>  onclick="checkedCounter('totalVacChk');" >  </td>
					        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="TotalvacancyLabel1"  name="TotalvacancyLabel" ondblclick="callShowDiv(this);"><%=label.get("TotalvacancyLabel")%></label> </td>
						    <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="closedDateChk" id="closedDateChk"  <s:property value="closedDateChk"/> onclick="checkedCounter('closedDateChk');"  >  </td>
						    <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="ClosedDateLabel1"  name="ClosedDateLabel" ondblclick="callShowDiv(this);"><%=label.get("ClosedDateLabel")%></label> </td>
					 	  	</tr>   
						  	
						  	<tr>  
						  	 <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="hiringMangerChk" id="hiringMangerChk"   <s:property value="hiringMangerChk"/>  onclick="checkedCounter('hiringMangerChk');"   >  </td>
						     <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="HiringmanagerLabel2"  name="hiring.mgr" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> </td>
							 <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="vacAssignChk" id="vacAssignChk"  <s:property value="vacAssignChk"/> onclick="checkedCounter('vacAssignChk');"  >  </td>
					         <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="vacLabelAssigned1"  name="vacLabelAssigned" ondblclick="callShowDiv(this);"><%=label.get("vacLabelAssigned")%></label> </td>
							<td width="5%" colspan="1" class="formtext"><input type="checkbox" name="overDueDayChk" id="overDueDayChk"  <s:property value="overDueDayChk"/> onclick="checkedCounter('overDueDayChk');"  >  </td>
					         <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="overDueDayLabel"  name="overDueDayLabel" ondblclick="callShowDiv(this);"><%=label.get("overDueDayLabel")%></label> </td>
						 	 </tr> 
						 	 
						 	 <tr>  
						  	 <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="approvarChk" id="approvarChk"   <s:property value="approvarChk"/>  onclick="checkedCounter('approvarChk');"   >  </td>
						     <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="ApprovarLabel2"  name="approvar.name" ondblclick="callShowDiv(this);"><%=label.get("approvar.name")%></label> </td>
							 
						 	 </tr>   
				</table> 
				
				<table id="advance" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
				              <tr>								
								<td width="100%" colspan="3" class="formtext"> <b> <label  class = "set"  id="selectAdvanceFilter"  name="selectAdvanceFilter" ondblclick="callShowDiv(this);"><%=label.get("selectAdvanceFilter")%></label> </b> </td>
							 </tr> 
							<tr>								
								<td width="28%" colspan="1" class="formtext"> <label  class = "set"  id="TotalvacancyLabel2"  name="TotalvacancyLabel" ondblclick="callShowDiv(this);"><%=label.get("TotalvacancyLabel")%></label> :</td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="totalVacAdvCom" list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}" onchange="callAdvCombo('totalVacAdvCom','totalVacAdvTxt');"  /> </td>
							    <td width="62%" colspan="1" class="formtext"> <s:textfield name="totalVacAdvTxt" size="15" onkeypress="return numbersOnly();" maxlength="10" />  </td>
						    </tr>   
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="vacLabelAssigned2"  name="vacLabelAssigned" ondblclick="callShowDiv(this);"><%=label.get("vacLabelAssigned")%></label> : </td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="asgnVacAdvCom"  list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}"  onchange="callAdvCombo('asgnVacAdvCom','asgnVacAdvTxt');" /> </td>
							    <td width="68%" colspan="1" class="formtext"> <s:textfield name="asgnVacAdvTxt" size="15" onkeypress="return numbersOnly();" maxlength="10" />  </td>
						    </tr> 
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="OpenvacancyLabel2"  name="OpenvacancyLabel" ondblclick="callShowDiv(this);"><%=label.get("OpenvacancyLabel")%></label> : </td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="openVacAdvCom"  list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}"  onchange="callAdvCombo('openVacAdvCom','openVacAdvTxt');"  /> </td>
							    <td width="68%" colspan="1" class="formtext"> <s:textfield name="openVacAdvTxt" size="15" onkeypress="return numbersOnly();" maxlength="10"  />  </td>
						    </tr> 
						     <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="ClosedvacancyLabel2"  name="ClosedvacancyLabel" ondblclick="callShowDiv(this);"><%=label.get("ClosedvacancyLabel")%></label> :</td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="closeVacAdvCom"  list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}"  onchange="callAdvCombo('closeVacAdvCom','closeVacAdvTxt');"  /> </td>
							    <td width="68%" colspan="1" class="formtext"> <s:textfield name="closeVacAdvTxt" size="15" onkeypress="return numbersOnly();" maxlength="10"  />  </td>
						    </tr> 
						     <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="RequiredDateLabel2"  name="RequiredDateLabel" ondblclick="callShowDiv(this);"><%=label.get("RequiredDateLabel")%></label> : </td>
								   <td width="10%" colspan="1" class="formtext"> 
								    <s:select headerKey="1" headerValue="--Select--" name="requirdeByDateCom"  list="#{'O':'On','B':'Before','A':'After' ,'OB':'On before' ,'OA':'On after','F':'From'}"  theme="simple"  onchange="callToDateDispInAdvance();" />
								    </td>
							    <td width="68%" colspan="1" class="formtext"> 
							     <table width="100%" border="0">
							       <tr>
							          <td width="32%"> <s:textfield name="requirdeByFromDateTxt" size="14" onkeypress="return numbersWithHiphen();" onkeypress="return numbersOnly();"  maxlength="10"  onfocus="clearText('paraFrm_requirdeByFromDateTxt','dd-mm-yyyy');" onblur="setText('paraFrm_requirdeByFromDateTxt','dd-mm-yyyy');" />  <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_requirdeByFromDateTxt','DDMMYYYY');"  >  </td>
							          <td   nowrap="nowrap" id="advanceToDateLabel">  <label  class = "set"  id="toDateLabel"  name="toDateLabel" ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> : </td>
							          <td id="advanceToDateField" >  <s:textfield name="requirdeByToDateTxt" size="15" onkeypress="return numbersWithHiphen();" onkeypress="return numbersOnly();"  maxlength="10" onfocus="clearText('paraFrm_requirdeByToDateTxt','dd-mm-yyyy');" onblur="setText('paraFrm_requirdeByToDateTxt','dd-mm-yyyy');"   />   <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_requirdeByToDateTxt','DDMMYYYY');">  </td>
							     </tr>
							     </table>
							    
							     </td>
						    </tr>						      
				</table> 
				 </td>
				</tr>	  
			  <tr>
			     <td colspan="3"> 
			       <table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"> 
			                   <tr>								
								 <td width="100%" colspan="2" class="formtext"> <b>Display option</b> </td>
							   </tr>  
							  <tr>								
								<td width="100%" colspan="2" class="formtext"> <s:hidden name="hidReportView" />  <s:hidden name="hidReportRadio" />   <input type="radio" value="V" name="reportView"  id="reportViewV" <s:property value="hidReportView"/>  onclick="callReportChk('N');" > <label  class = "set"  id="view.screen"  name="view.screen" ondblclick="callShowDiv(this);"><%=label.get("view.screen")%></label>  </td>
							 </tr>  
							  <tr>								
								<td width="17%" colspan="1" class="formtext"> <input type="radio" value="R" name="reportView"  id="reportViewR"  <s:property value="hidReportRadio"/> onclick="callReportChk('Y');"> <label  class = "set"  id="report.type"  name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>  </td>
								<td width="83%" colspan="1" class="formtext" > <div id="reportTypeDiv"> <s:select  headerKey="1" headerValue="--Select--" name="reportType" list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}"  /> </div>  </td>
							 </tr>  
							  <tr>								
								<td nowrap="nowrap" colspan="1" class="formtext"> <label  class = "set"  id="setting.name"  name="setting.name" ondblclick="callShowDiv(this);"><%=label.get("setting.name")%></label> : </td>
							    <td width="83%" colspan="1" ><s:textfield name="settingName" size="25" theme="simple" maxlength="40" />  </td>
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
							  <input type="button" class="token" onclick="return callReportVacancies();"	value=" Generate Report" />
							 </s:if>
							<s:submit cssClass="reset" action="OpenVacReport_reset" theme="simple" value="    Reset"  />  
							<input type="button" class="token"  	value=" Save report criteria" onclick="callSave();"/>
							</td> 
						  </tr>
					  </table>
					</td>					
			    </tr>					
			</table>  
</s:form> 
 

<script type="text/javascript">    

function callRadioOptionFun(id)
{
if(id=="C")
	{
	  callF9ReqCode('radio');
	}
	if(id=="N")
	{
	  callF9Recruiter('radio');
	}
	
	if(id=="M")
	{
	  callF9HiringManager('radio');
	}
	if(id=="P")
	{
	  callF9Position('radio');
	}
	document.getElementById('paraFrm_radioStatus').value=id;
	
}
 callMeOnload(); 
function callMeOnload()
{ 
   
  radioStatus =document.getElementById('paraFrm_radioStatus').value;
  callRadioOptionFun(radioStatus);
  if(radioStatus=="")
   {
   document.getElementById('f9ReqCode').style.display='none'; 
   document.getElementById('f9RecruiterName').style.display='none';
   document.getElementById('f9HiringManger').style.display='none'; 
   document.getElementById('f9Position').style.display='none'; 
  } 
  if(radioStatus=="C")
  { 
   document.getElementById('paraFrm_radioReq').value="checked";
  } 
  if(radioStatus=="N")
  { 
   document.getElementById('paraFrm_radioRecr').value="checked";
  }
  
    if(radioStatus=="M")
  { 
   document.getElementById('paraFrm_radioHirMng').value="checked";
  } 
    if(radioStatus=="P")
  { 
   document.getElementById('paraFrm_radioPosition').value="checked";
  }
  
  
   var fieldName =["paraFrm_frmDate","paraFrm_toDate","paraFrm_requirdeByFromDateTxt","paraFrm_requirdeByToDateTxt"];
   setTextArray(fieldName,"dd-mm-yyyy");
  var settingVal = document.getElementById('paraFrm_searchSetting').value;
  var dateFilter = document.getElementById('paraFrm_dateFilter').value;
  var requirdeByDateCom = document.getElementById('paraFrm_requirdeByDateCom').value;  
  var hidReportRadio = document.getElementById('paraFrm_hidReportRadio').value;   
  var divCount = document.getElementById('paraFrm_divCount').value;  
  
	var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ;  
    var backFlag =   document.getElementById('paraFrm_backFlag').value;
     
    if((settingVal!="B" ||backFlag=='true') && selectedReqName!="" )
	 {   
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    }
	if(dateFilter=="F")
	 { 
	   document.getElementById('toDateDiv').style.display=''; 
	   document.getElementById('toDateDivLabel').style.display='';     
	 }else{
	   document.getElementById('toDateDiv').style.display='none'; 
	   document.getElementById('toDateDivLabel').style.display='none';    
	 }
	 
	 if(requirdeByDateCom=="F")
	 { 
	   document.getElementById('advanceToDateField').style.display=''; 
	   document.getElementById('advanceToDateLabel').style.display='';     
	 }else{
	   document.getElementById('advanceToDateField').style.display='none'; 
	   document.getElementById('advanceToDateLabel').style.display='none';    
	 }
	 if(hidReportRadio=="checked")
	 { 
	  document.getElementById('reportTypeDiv').style.display=''; 
	 }else{
	  document.getElementById('reportTypeDiv').style.display='none'; 
	 }
}


  callOnBackTab();
 function callOnBackTab()
 {  
   
 var backFlag =   document.getElementById('paraFrm_backFlag').value;
 var aId =   document.getElementById('paraFrm_aId').value;
 var tabId =   document.getElementById('paraFrm_divId').value;  
  if(backFlag=='true'){ 
   callTab(aId,tabId);
   document.getElementById('paraFrm_backFlag').value="false";
   } else{
    callOnLoad(); 
   }
   var reqCode = document.getElementById('paraFrm_reqCode').value;
   var recruiterCode = document.getElementById('paraFrm_recruiterCode').value;
   var hiringManagerId = document.getElementById('paraFrm_hiringManagerId').value;
   var positionId = document.getElementById('paraFrm_positionId').value;
   if(reqCode!=""){
     callF9ReqCode('radio');
   } 
   if(recruiterCode!=""){
     callF9Recruiter('radio');
   } 
   if(hiringManagerId!=""){
    callF9HiringManager('radio');
   } 
   if(positionId!=""){
    callF9Position('radio');
   }
   callToDateDisp();
   
 } 

 
 function callOnLoad()
 {
   document.getElementById('filterDisp').style.display='';
   document.getElementById('sort').style.display='none'; 
   document.getElementById('column').style.display='none';  
   document.getElementById('advance').style.display='none';    
   
    document.getElementById('filtOpt').className = '';
	document.getElementById('sortOpt').className = '';
	document.getElementById('colDef').className = '';
	document.getElementById('advFilter').className = '';
	document.getElementById('filtOpt').className = 'on';
	document.getElementById('paraFrm_aId').value =  'filtOpt';
	document.getElementById('paraFrm_divId').value = 'filter'; 
   
 }

function callShowRecord()
{ 
 
var settingVal = document.getElementById('paraFrm_searchSetting').value;
if(settingVal=="B")
{
 document.getElementById('paraFrm').action="OpenVacReport_reset.action";
}else{
 document.getElementById('paraFrm').action="OpenVacReport_viewSavedRecord.action";
 }
 document.getElementById('paraFrm').submit();
}

function callSave()
{    
   
    var settingVal = document.getElementById('paraFrm_searchSetting').value;
    if(settingVal=="B")
    { var settingName = document.getElementById('paraFrm_settingName').value;
      if(LTrim(settingName)=="")
      {
        alert("Please enter the "+document.getElementById('setting.name').innerHTML.toLowerCase());
	    document.getElementById('paraFrm_settingName').focus();
	    return false; 	 
      } 
    }
    
    // filter on tab
    
	       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
	       var frmDate = document.getElementById('paraFrm_frmDate').value;
	        var reqCode = document.getElementById('paraFrm_reqCode').value;
	      
	    if(reqCode=="")
	       {
		      if(dateFilter!="1")
		      { 
		        if(frmDate=="dd-mm-yyyy")
		         { 
		          callTab('filtOpt','filter');
		         alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase()); 
		          clearText("paraFrm_frmDate","dd-mm-yyyy");
			     document.getElementById('paraFrm_frmDate').focus();
			     return false; 	 
		        }   
		       if(!validateDate('paraFrm_frmDate', 'reqs.date'))
		        {  return false;
		        }
		        if(dateFilter=="F")
		         {
		           
		           var toDate = document.getElementById('paraFrm_toDate').value;  
		              clearText("paraFrm_toDate","dd-mm-yyyy");
			         if(!validateDate('paraFrm_toDate', 'toDateLabel'))
		             {  return false;
		               }
			         callTab('filtOpt','filter');
		             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
		           {
		            return false;
		           }
		          setText("paraFrm_toDate","dd-mm-yyyy");
		         }
		     }
     	}	    
     	     		    
    // for sort the filter   
   	  var sortBy = document.getElementById('paraFrm_sortBy').value;
   	  var thenBy1 = document.getElementById('paraFrm_thenBy1').value;
   	  var thenBy2 = document.getElementById('paraFrm_thenBy2').value; 
   	  
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
 
	         var totalVacAdvCom = document.getElementById('paraFrm_totalVacAdvCom').value;
	         var totalVacAdvTxt = document.getElementById('paraFrm_totalVacAdvTxt').value;  
	         var asgnVacAdvCom = document.getElementById('paraFrm_asgnVacAdvCom').value;
	         var asgnVacAdvTxt = document.getElementById('paraFrm_asgnVacAdvTxt').value;  
	         var openVacAdvCom = document.getElementById('paraFrm_openVacAdvCom').value;
	         var openVacAdvTxt = document.getElementById('paraFrm_openVacAdvTxt').value;  
	         var closeVacAdvCom = document.getElementById('paraFrm_closeVacAdvCom').value;
	         var closeVacAdvTxt = document.getElementById('paraFrm_closeVacAdvTxt').value;  
	         
	         if(totalVacAdvCom!="1")
	         {
	           if(totalVacAdvTxt==""){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('TotalvacancyLabel2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_totalVacAdvTxt').focus();
			     return false; 	
			     } 
	         }    
	        
	         if(asgnVacAdvCom!="1")
	         {
	           if(asgnVacAdvTxt==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('vacLabelAssigned2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_asgnVacAdvTxt').focus();
			     return false; 	
			     } 
	        }   
	         if(openVacAdvCom!="1")  { 
	           if(openVacAdvTxt==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('OpenvacancyLabel2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_openVacAdvTxt').focus();
			     return false; 	
			     }  
		     }    
		     
		     if(closeVacAdvCom!="1")  {
	           if(closeVacAdvTxt==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('ClosedvacancyLabel2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_closeVacAdvTxt').focus();
			     return false; 	
			     }  
		     }    
		     
		     
		   var requirdeByDateCom = document.getElementById('paraFrm_requirdeByDateCom').value;
	       var requirdeByFromDateTxt = document.getElementById('paraFrm_requirdeByFromDateTxt').value;
	      
	      if(requirdeByDateCom!="1")
	      {
	        if(requirdeByFromDateTxt=="dd-mm-yyyy")
	         {
	            callTab('advFilter','advance');
		         alert("Please enter/select the "+document.getElementById('RequiredDateLabel2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_requirdeByFromDateTxt').focus();
			     return false; 	 
	         }   
	         if(!validateDate('paraFrm_requirdeByFromDateTxt', 'RequiredDateLabel2'))
	          {  return false;
	         }
	        if(requirdeByDateCom=="F")
	         {
		           var requirdeByToDateTxt = document.getElementById('paraFrm_requirdeByToDateTxt').value;  
			        callTab('advFilter','advance');
			        clearText('paraFrm_requirdeByToDateTxt',"dd-mm-yyyy");
			          if(!validateDate('paraFrm_requirdeByToDateTxt', 'toDateLabel'))
	                  {  return false;
	                 }
			        if(!dateDifferenceEqual(requirdeByFromDateTxt,requirdeByToDateTxt,"paraFrm_requirdeByToDateTxt",'RequiredDateLabel2','toDateLabel'))
			           {
			            return false;
			           }
			            setText('paraFrm_requirdeByToDateTxt',"dd-mm-yyyy");
	         }
	     }  
	var fieldName =["paraFrm_frmDate","paraFrm_toDate","paraFrm_requirdeByFromDateTxt","paraFrm_requirdeByToDateTxt"];
      clearTextArray(fieldName,"dd-mm-yyyy");
   document.getElementById('paraFrm').action="OpenVacReport_save.action";
   document.getElementById('paraFrm').submit();
   document.getElementById('paraFrm').target="main";
}
function checkedCounter(fieldName)
{ 
	var checkedCount =  document.getElementById('paraFrm_checkedCount').value; 
	 if(checkedCount=="" || checkedCount=='null')
	 {
	  checkedCount="0";
	 }else{  
		 if(document.getElementById(fieldName).checked==true){ 
		 checkedCount= eval(eval(checkedCount)+1); 
		 document.getElementById('paraFrm_checkedCount').value =checkedCount;
		 }else{ 
		checkedCount= eval(eval(checkedCount)-1);
		 document.getElementById('paraFrm_checkedCount').value =checkedCount;
		 }
	 }
}
function callF9ReqCode(status) {

   document.getElementById('f9ReqCode').style.display=''; 
   document.getElementById('f9RecruiterName').style.display='none';
   document.getElementById('f9HiringManger').style.display='none'; 
   document.getElementById('f9Position').style.display='none'; 
   document.getElementById('selectReqDiv').style.display='none';   
   document.getElementById('textAreaId1').style.display='none';    
   document.getElementById('fromDateIcon').style.display='none'; 
   document.getElementById('paraFrm_frmDate').readOnly='true'; 
   document.getElementById('paraFrm_frmDate').value=''; 
   document.getElementById('toDateDiv').style.display='none'; 
   document.getElementById('toDateDivLabel').style.display='none';  
   document.getElementById('paraFrm_dateFilter').value='1';   
     
   document.getElementById('paraFrm_selectedReqName').value="";
   document.getElementById('paraFrm_selectedReq').value="";
   document.getElementById('paraFrm_recruiterName').value="";
   document.getElementById('paraFrm_recruiterCode').value=""; 
   document.getElementById('paraFrm_hiringManagerName').value="";
   document.getElementById('paraFrm_hiringManagerId').value=""; 
   document.getElementById('paraFrm_positionId').value="";
   document.getElementById('paraFrm_position').value="";  
      if(status=='selF9'){
   callsF9(500,325,'OpenVacReport_f9actionReqName.action'); 
   }
}

function callF9Recruiter(status)
{ 
       setText("paraFrm_frmDate","dd-mm-yyyy");
   document.getElementById('paraFrm_reqname').value="";
   document.getElementById('paraFrm_reqCode').value=""; 
   document.getElementById('paraFrm_hiringManagerName').value="";
   document.getElementById('paraFrm_hiringManagerId').value=""; 
   document.getElementById('paraFrm_positionId').value="";
   document.getElementById('paraFrm_position').value=""; 
   
   // document.getElementById('paraFrm_selectedReqName').value="";
  //  document.getElementById('paraFrm_selectedReq').value="";
    document.getElementById('f9RecruiterName').style.display='';
   document.getElementById('f9ReqCode').style.display='none'; 
   document.getElementById('f9HiringManger').style.display='none'; 
   document.getElementById('f9Position').style.display='none'; 
   document.getElementById('selectReqDiv').style.display='';  
    var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ;
    var settingVal = document.getElementById('paraFrm_searchSetting').value; 
    var backFlag =   document.getElementById('paraFrm_backFlag').value;
     if((settingVal!="B" ||backFlag=='false') && selectedReqName!="" )
	 {  
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    }
   document.getElementById('fromDateIcon').style.display=''; 
   document.getElementById('paraFrm_frmDate').readOnly='';   
   document.getElementById('paraFrm_frmDate').value=''; 
    document.getElementById('paraFrm_toDate').value='';   
   
    var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   if(dateFilter=="F"){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';    
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
     if(status=='selF9'){
   callsF9(500,325,'OpenVacReport_f9actionRecruiter.action');
   }
}

function callF9HiringManager(status)
{ 
     setText("paraFrm_frmDate","dd-mm-yyyy");
   document.getElementById('paraFrm_reqname').value="";
   document.getElementById('paraFrm_reqCode').value="";  
   document.getElementById('paraFrm_positionId').value="";
   document.getElementById('paraFrm_position').value=""; 
   document.getElementById('paraFrm_recruiterName').value="";
   document.getElementById('paraFrm_recruiterCode').value=""; 
   document.getElementById('f9HiringManger').style.display=''; 
   document.getElementById('f9ReqCode').style.display='none'; 
   document.getElementById('f9RecruiterName').style.display='none';
   document.getElementById('f9Position').style.display='none';   
   document.getElementById('selectReqDiv').style.display='';   
    var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ;
    var settingVal = document.getElementById('paraFrm_searchSetting').value; 
    var backFlag =   document.getElementById('paraFrm_backFlag').value;
     if((settingVal!="B" ||backFlag=='false') && selectedReqName!="" )
	 {  
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    }
   document.getElementById('fromDateIcon').style.display=''; 
   document.getElementById('paraFrm_frmDate').readOnly='';   
  //  document.getElementById('paraFrm_selectedReqName').value="";
  // document.getElementById('paraFrm_selectedReq').value="";
    var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   if(dateFilter=="F"){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';    
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
   if(status=='selF9'){
   callsF9(500,325,'OpenVacReport_f9HiringManger.action');
   }
}

function callF9Position(status)
{ 
    setText("paraFrm_frmDate","dd-mm-yyyy");
   document.getElementById('paraFrm_reqname').value="";
   document.getElementById('paraFrm_reqCode').value=""; 
   document.getElementById('paraFrm_hiringManagerName').value="";
   document.getElementById('paraFrm_hiringManagerId').value=""; 
   document.getElementById('paraFrm_recruiterName').value="";
   document.getElementById('paraFrm_recruiterCode').value=""; 
   
   document.getElementById('f9Position').style.display=''; 
   document.getElementById('f9ReqCode').style.display='none'; 
   document.getElementById('f9RecruiterName').style.display='none';
   document.getElementById('f9HiringManger').style.display='none';   
 //  document.getElementById('paraFrm_selectedReqName').value="";
 //  document.getElementById('paraFrm_selectedReq').value=""; 
   document.getElementById('selectReqDiv').style.display=''; 
    var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ;
    var settingVal = document.getElementById('paraFrm_searchSetting').value; 
      var backFlag =   document.getElementById('paraFrm_backFlag').value;
     if((settingVal!="B" ||backFlag=='false') && selectedReqName!="" )
	 {  
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    }
   document.getElementById('fromDateIcon').style.display=''; 
   document.getElementById('paraFrm_frmDate').readOnly='';   
   
 var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   if(dateFilter=="F"){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';    
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
   if(status=='selF9'){
   callsF9(500,325,'OpenVacReport_f9Position.action');
   }
} 

  function callToDateDisp()
 {
   var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   var  reqCode = document.getElementById('paraFrm_reqCode').value;
   if(dateFilter=="F"){
    if(reqCode==""){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';   
     }
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
 }
 
   function callToDateDispOnClick()
 {
   var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   var  reqCode = document.getElementById('paraFrm_reqCode').value;
   document.getElementById('paraFrm_toDate').value="";  
   if(dateFilter=="F"){
    if(reqCode==""){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';  
   //  document.getElementById('paraFrm_toDate').value="";  
     }
      setText("paraFrm_toDate","dd-mm-yyyy");
   }else {
      setText("paraFrm_toDate","dd-mm-yyyy");
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
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
   function callToDateDispInAdvance()
 {
  filedClick = document.getElementById('paraFrm_requirdeByDateCom').value; 
   if(filedClick=="1") 
   { 
   setText('paraFrm_requirdeByFromDateTxt','dd-mm-yyyy');
   }
   
   var requirdeByDateCom= document.getElementById('paraFrm_requirdeByDateCom').value;
   if(requirdeByDateCom=="F")
	 { 
	   document.getElementById('advanceToDateField').style.display=''; 
	   document.getElementById('advanceToDateLabel').style.display='';    
	   document.getElementById('paraFrm_requirdeByToDateTxt').value="dd-mm-yyyy"; 
	 }else{
	   document.getElementById('advanceToDateField').style.display='none'; 
	   document.getElementById('advanceToDateLabel').style.display='none';    
	 }
 }
 
 function callReportChk(status)
 { 
	 if(status=="Y"){
	   document.getElementById('reportTypeDiv').style.display=''; 
	   document.getElementById('paraFrm_reqStatus').value ="R"; 
	  }else{
	   document.getElementById('reportTypeDiv').style.display='none';  
	   document.getElementById('paraFrm_reqStatus').value ="V"; 
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
     document.getElementById('paraFrm_divCount').value ="someThing"; 
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
	   
	   var requirdeByDateCom= document.getElementById('paraFrm_requirdeByDateCom').value;
     if(requirdeByDateCom=="F")
	  { 
	   document.getElementById('advanceToDateField').style.display=''; 
	   document.getElementById('advanceToDateLabel').style.display='';   
	   }else{
	   document.getElementById('advanceToDateField').style.display='none'; 
	   document.getElementById('advanceToDateLabel').style.display='none';    
	 }
	 
	  }
	     
 }

function callReportVacancies()
 { 	  
	
    var reqCode= document.getElementById('paraFrm_reqCode').value; 
    if(reqCode!=""){
       document.getElementById('paraFrm_dateFilter').value="1";
     }  
	 
	  // filter on tab
         // filter on tab
    
	       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
	       var frmDate = document.getElementById('paraFrm_frmDate').value;
	       var reqCode = document.getElementById('paraFrm_reqCode').value;
	      
	    if(reqCode=="")
	       {
		      if(dateFilter!="1")
		      { 
		        if(frmDate=="dd-mm-yyyy")
		         { 
		          callTab('filtOpt','filter');
		         alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase()); 
		          clearText("paraFrm_frmDate","dd-mm-yyyy");
			     document.getElementById('paraFrm_frmDate').focus();
			     return false; 	 
		        }   
		       if(!validateDate('paraFrm_frmDate', 'reqs.date'))
		        {  return false;
		        }
		        if(dateFilter=="F")
		         {
		           
		           var toDate = document.getElementById('paraFrm_toDate').value;  
		              clearText("paraFrm_toDate","dd-mm-yyyy");
			         if(!validateDate('paraFrm_toDate', 'toDateLabel'))
		             {  return false;
		               }
			         callTab('filtOpt','filter');
		             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
		           {
		            return false;
		           }
		          setText("paraFrm_toDate","dd-mm-yyyy");
		         }
		     }
     	}	
	     
	  // for sort the filter   
   	  var sortBy = document.getElementById('paraFrm_sortBy').value;
   	  var thenBy1 = document.getElementById('paraFrm_thenBy1').value;
   	  var thenBy2 = document.getElementById('paraFrm_thenBy2').value; 
   	  
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
 
	         var totalVacAdvCom = document.getElementById('paraFrm_totalVacAdvCom').value;
	         var totalVacAdvTxt = document.getElementById('paraFrm_totalVacAdvTxt').value;  
	         var asgnVacAdvCom = document.getElementById('paraFrm_asgnVacAdvCom').value;
	         var asgnVacAdvTxt = document.getElementById('paraFrm_asgnVacAdvTxt').value;  
	         var openVacAdvCom = document.getElementById('paraFrm_openVacAdvCom').value;
	         var openVacAdvTxt = document.getElementById('paraFrm_openVacAdvTxt').value;  
	         var closeVacAdvCom = document.getElementById('paraFrm_closeVacAdvCom').value;
	         var closeVacAdvTxt = document.getElementById('paraFrm_closeVacAdvTxt').value;  
	         
	         if(totalVacAdvCom!="1")
	         {
	           if(totalVacAdvTxt==""){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('TotalvacancyLabel2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_totalVacAdvTxt').focus();
			     return false; 	
			     } 
	         }    
	        
	         if(asgnVacAdvCom!="1")
	         {
	           if(asgnVacAdvTxt==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('vacLabelAssigned2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_asgnVacAdvTxt').focus();
			     return false; 	
			     } 
	        }   
	         if(openVacAdvCom!="1")  {  
	           if(openVacAdvTxt==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('OpenvacancyLabel2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_openVacAdvTxt').focus();
			     return false; 	
			     }  
		     }    
		     
		     if(closeVacAdvCom!="1")  {
	           if(closeVacAdvTxt==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('ClosedvacancyLabel2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_closeVacAdvTxt').focus();
			     return false; 	
			     }  
		     }    
		     
		     
		   var requirdeByDateCom = document.getElementById('paraFrm_requirdeByDateCom').value;
	       var requirdeByFromDateTxt = document.getElementById('paraFrm_requirdeByFromDateTxt').value;
	      
	      if(requirdeByDateCom!="1")
	      {
	        if(requirdeByFromDateTxt=="dd-mm-yyyy")
	         {
	            callTab('advFilter','advance');
		         alert("Please enter/select the "+document.getElementById('RequiredDateLabel2').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_requirdeByFromDateTxt').focus();
			     return false; 	 
	         }   
	        if(requirdeByDateCom=="F")
	         {
		           var requirdeByToDateTxt = document.getElementById('paraFrm_requirdeByToDateTxt').value; 
			       
			        callTab('advFilter','advance');
			        clearText('paraFrm_requirdeByToDateTxt',"dd-mm-yyyy");
		         if(!dateDifferenceEqual(requirdeByFromDateTxt,requirdeByToDateTxt,"paraFrm_requirdeByToDateTxt",'RequiredDateLabel2','toDateLabel'))
		           {
		            return false;
		           }
		             setText('paraFrm_requirdeByToDateTxt',"dd-mm-yyyy");
	         }
	     }   
var fieldName =["paraFrm_frmDate","paraFrm_toDate","paraFrm_requirdeByFromDateTxt","paraFrm_requirdeByToDateTxt"];
      clearTextArray(fieldName,"dd-mm-yyyy");
	if(document.getElementById("reportViewV").checked)
	{
	 document.getElementById('paraFrm').action="OpenVacReport_viewOnScreen.action";
     document.getElementById('paraFrm').submit();
	}else{	
	   var reportType = document.getElementById("paraFrm_reportType").value; 
	   if(reportType=="1"){
	    alert("Please select "+document.getElementById('report.type').innerHTML.toLowerCase());
	    document.getElementById("paraFrm_reportType").focus();
	   return false;
	   }
  	  callReport('OpenVacReport_report.action');   		
  	}
 }
 
 function callSelectReq(status)
 {
      // filter on tab 
       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
       var frmDate = document.getElementById('paraFrm_frmDate').value; 
	      if(dateFilter!="1")
	      { 
	        if(frmDate=="dd-mm-yyyy")
	         { 
	          callTab('filtOpt','filter');
	         alert("Please enter/select  the "+document.getElementById('reqs.date').innerHTML.toLowerCase()); 
	          clearText("paraFrm_frmDate","dd-mm-yyyy");
		     document.getElementById('paraFrm_frmDate').focus();
		     return false; 	 
	        }   
	       if(!validateDate('paraFrm_frmDate', 'reqs.date'))
	        {  return false;
	        }
	        if(dateFilter=="F")
	         {
	           
	           var toDate = document.getElementById('paraFrm_toDate').value; 
		        clearText("paraFrm_toDate","dd-mm-yyyy");
		         if(!validateDate('paraFrm_toDate', 'toDateLabel'))
	             {  return false;
	               }
		         callTab('filtOpt','filter');
	             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
	           {
	            return false;
	           }
	             setText("paraFrm_toDate","dd-mm-yyyy");
	         }
	     }
	     
   document.getElementById('f9ReqCode').style.display='none'; 
  if(status=='edit'){ 
    document.getElementById('paraFrm_selectedReqName').value =document.getElementById('paraFrm_hidSelectedReqName').value; 
    document.getElementById('paraFrm_editReqFlag').value="true";    
  }else{
   document.getElementById('paraFrm_editReqFlag').value="false"; 
   document.getElementById('paraFrm_selectedReqName').value="";
   document.getElementById('paraFrm_selectedReq').value=""; 
  }  
   document.getElementById('paraFrm').target="wind";
   var wind = window.open('','wind','width=700,height=450,scrollbars=yes,resizable=no,status=yes,menubar=no,top=200,left=150');	 
   document.getElementById('paraFrm').action="OpenVacReport_displayReq.action";
   document.getElementById('paraFrm').submit();
   document.getElementById('paraFrm').target="";
 }
 
</script>
 