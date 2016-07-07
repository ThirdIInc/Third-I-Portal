<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<s:form action="InterviewAnalysis" id="paraFrm" theme="simple">

	<table border="0" width="100%" colspan="4" cellpadding="0" cellspacing="0" align="right" 
		 class="formbg">
	<s:hidden name="backFlag"/>
	<s:hidden name='selectedType'/><s:hidden name='divId'/>
	<s:hidden name='aId'/><s:hidden name="requisionFlag"/>
	<s:hidden name="positionFlag"/>
	<s:hidden name="quailificationFlag" />
     <s:hidden name="technicalFlag" />
      <s:hidden name="communicationFlag" /><s:hidden name="managmentFlag" />
	<s:hidden name="personalityFlag" /><s:hidden name="learningFlag" />
	<s:hidden name="relevantFlag" /><s:hidden name="sutablityFlag" />
	
	
	
	<s:hidden  name="settingCode"  />
	 <s:hidden name="selectedReq" /> <s:hidden name="selectedReqFlag"/>
	 <s:hidden name="editReqFlag" />
	 <s:hidden name="schdReqCode" />
	 <s:hidden name="selecteReqCode" />
	 <s:hidden name="selecteReqName" />
	 <s:hidden name="reqCode" />
	 <s:hidden name="evalCode"/><s:hidden name="evalScore"/>
	 
	 <s:hidden name="CandidateFlag" /> <s:hidden name="intRndTypeFlag" />
     <s:hidden name="intrviewerFlag" /> <s:hidden name="scoreFlag" />
     <s:hidden name="ratingFlag" /> 
      <s:hidden name="statusFlag" />
      <s:hidden name="quailificationFlag" />
      <s:hidden name="technicalFlag" />
      <s:hidden name="communicationFlag" /><s:hidden name="managmentFlag" />
	 <s:hidden name="personalityFlag" /><s:hidden name="learningFlag" />
	 <s:hidden name="relevantFlag" /><s:hidden name="sutablityFlag" />
	  <s:hidden name="makeOfferFlag" /><s:hidden name="nextRoundFlag" />
	  <s:hidden name="percentageFlag" /><s:hidden name="sutablityFlag" />
      
      
      
      
      <s:hidden name="userEmpId" />
      <s:hidden name="myChkFlag" id="myChkFlag"  />
      <s:hidden name="buttonFlag"  />

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0" 
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Interview Analysis </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
          <td colspan="3">
          		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
            		<tr>
	              		<td width="78%">
	              		<input type="button" class="token" theme="simple" value='Generate Report' onclick="call();" />
			 			<input type="button" class="reset" theme="simple" value=" Reset" onclick="return callClear();" target="main"/>
	             		<input type="button" class="add"  onclick="callVal();"
										theme="simple" value="Save report criteria"  target="main"/>
	             		</td>
	              		
            		</tr>
          		</table>
          </td>
        </tr>
     
        <tr><td  colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
        <td width="13%"> <label  name="save.setting" id="save.setting" ondblclick="callShowDiv(this);"><%=label.get("save.setting")%></label>:</td>
								<td  colspan="1" width="46%"><s:select headerKey="-1" headerValue="--Select--" name="settingSearch"   list="%{hashMap}"  onchange=" return callFilter();"/></td>
			
				</table></td></tr>
        
        
        <tr valign="top" >	
			<td><table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg">
				<div id="tabnav">
					<ul>
						<li>
							<a id="filtOpt" href="javascript:callDivLoad('filtOpt', 'filterOption');" >
								<div align="center"><span>Filter Option</span></div>
							</a>
						</li>
						
					</ul>
					
					<ul>
						<li>
							<a id="sortOpt" href="javascript:callDivLoad('sortOpt', 'sortingOption');" >
								<div align="center"><span>Sorting Option</span></div>
							</a>
						</li>
						
					</ul>
					
					<ul>
						<li>
							<a id="colDef" href="javascript:callDivLoad('colDef', 'columnDefinition');" >
								<div align="center"><span>Column Definition</span></div>
							</a>
						</li>
						
					</ul>
					
					
				</div>
			
				
        
        
		<tr valign="top">
			<td  colspan="3">
			<div id="filterOption">
			
			<table width="100%" border="0"  cellpadding="0" cellspacing="2" >
<tr>								
								<td width="100%" colspan="3" class="formtext"> <b> <label  class = "set"  id="selectFilter"  name="selectFilter" ondblclick="callShowDiv(this);"><%=label.get("selectFilter")%></label></b> </td>
							 </tr>  
						<s:hidden name="candCode"  />
						<s:hidden name="intrvCode"  />
						<s:hidden name="intStatus" />
						<tr>
							<td colspan="1" width="19%"><label  name="cand.name" id="cand" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> :</td>
							<td colspan="1" width="20%"><s:textfield
								name="candName" theme="simple" readonly="true" size="25" /></td>
								<td id="CandId" width="54%"> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="15" align="absmiddle" width="16"
								onclick="return callCand();">
							</td>

					  </tr>
					  <tr><td width="19%" colspan="1"></td><td width="20%" colspan="1" align="center"> <strong class="forminnerhead">(OR)</strong></td></tr>
					  <tr >
							<td colspan="1" width="20%"><label  name="intrvName" id="intrvName" ondblclick="callShowDiv(this);"><%=label.get("intrvName")%></label> :</td>
							<td colspan="1"  width="20%"><s:textfield
								name="interviewerName" theme="simple" readonly="true" size="25" /></td>
								<td width="54%" id="intrvId"> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="15" align="absmiddle" width="16"
								onclick="callInterviewer();">
							</td>
					  </tr>
					  <tr >
							<td width="19%"><label  name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
							<td width="20%"><s:select name="interviewstatus"
								theme="simple" cssStyle="width:154"
								list="#{'':'--Select--','S':'Selected','R':'Rejected','O':'On Hold'}"  />
							</td>
					</tr>
					<tr>						
								<td width="19%" colspan="1" class="formtext"><label  class = "set"  id="intvRound"  name="intvRound" ondblclick="callShowDiv(this);"><%=label.get("intvRound")%></td>
								<td width="20%" colspan="1" nowrap="nowrap"><s:select name="intRound" cssStyle="width:70" 
						            			list="#{'':'--Select--', 'E':'=', 'L':'<', 'G':'>', 'LE':'<=',
						            			'GE':'>='}" /><s:textfield name="interviewRound" size="10"  theme="simple" maxlength="10" /></td>
						
									
									</tr>
					
					</table>
				</div>
				</td>
				</tr>
		<tr><td colspan="3">
		<div id="sortingOption">
			  <table  width="100%" border="0"  cellpadding="2" cellspacing="2" >
			  <tr>								
								<td width="100%" colspan="2" class="formtext"> <b> <label  class = "set"  id="selectSort"  name="selectSort" ondblclick="callShowDiv(this);"><%=label.get("selectSort")%></label></b> </td>
							 </tr> 
			<s:hidden name="sortRadioInt" />
			<s:hidden name="thenRadioInt"  />
			<s:hidden name="thanRadioInt" />
			<tr>
			<td width="21%"><label  class = "set"  id="sortBy1"  name="sortBy" ondblclick="callShowDiv(this);"><%=label.get("sortBy")%> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="sortByInt" cssStyle="width:140" 
						            			list="#{'':'--Select--','C':'Candidate Name',
						            			'I':'Interviewer Name'}" /></td></tr>
			<tr><td width="21%" ></td>
								    
								
			<td>
			<s:radio name="RadioOneInt" theme="simple" list="#{'A':'Ascending', 'D':'Dscending'}"   onclick="return checkIntRadio();"/></td>
			</tr>
			<tr>  
			
			<td width="21%"><label  class = "set"  id="thenBy1"  name="thenBy" ondblclick="callShowDiv(this);"><%=label.get("thenBy")%> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="thenBYInt" cssStyle="width:140" 
						            			list="#{'':'--Select--','C':'Candidate Name',
						            			'I':'Interviewer Name'}" /></td></tr>
			<tr><td width="21%" ></td>
		
			
								
			<td>
			 
			<s:radio name="RadioTwoInt" theme="simple" list="#{'A':'Ascending', 'D':'Descending'}"    onclick="return checkIntRadio();"/>
			</td>
	
			</tr>
			<tr>
			
			<td width="21%"><label  class = "set"  id="thenBy1"  name="thenBy" ondblclick="callShowDiv(this);"><%=label.get("thenBy")%> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="secondByInt" cssStyle="width:140" 
						            			list="#{'':'--Select--','C':'Candidate Name',
						            			'I':'Interviewer Name'}" /></td></tr>
			<tr><td width="21%" ></td>
									
		 <td>
		 <s:radio name="RadioThreeInt" theme="simple" list="#{'A':'Ascending', 'D':'Descending'}"    onclick="return checkIntRadio();"/></td>
			
		</tr>
		</table>
	</div></td></tr>
					
			<tr valign="top">
			<td width="100%">
			<div id="columnDefinition" >
			<table  width="100%" border="0" cellpadding="2" cellspacing="2" >
			 <tr>								
								<td width="100%" colspan="6" class="formtext"> <b> <label  class = "set"  id="selectCoumn"  name="selectCoumn" ondblclick="callShowDiv(this);"><%=label.get("selectCoumn")%></label></b> </td>
							 </tr>  
			
					<tr>   
		      	      	            <s:hidden name="intRoundCheck" id="intRoundCheck" />
									<s:hidden name="statusCheck" id="statusCheck" />
									<s:hidden name="ratingCheck" id="ratingCheck" />
									<s:hidden name="scoreCheck" id="scoreCheck" />
									<s:hidden name="interviewerCheck" id="interviewerCheck" />
						    		<s:hidden name="candidateCheck" id="candidateCheck" />
						    		<s:hidden name="qualificationCheck" id="qualificationCheck" />
						    		<s:hidden name="technicalCheck" id="technicalCheck" />
						    		<s:hidden name="communicationCheck" id="communicationCheck" />
						    		<s:hidden name="mangmentCheck" id="mangmentCheck" />
						    		<s:hidden name="personalityCheck" id="personalityCheck" />
						    		<s:hidden name="relevantCheck" id="relevantCheck" />
						    		<s:hidden name="makeOffferCheck" id="makeOffferCheck" />
						    		<s:hidden name="nextRoundCheck" id="nextRoundCheck" />
						    		<s:hidden name="learningCheck" id="learningCheck" />
						    		<s:hidden name="suitablityCheck" id="suitablityCheck" />
						    		<s:hidden name="percentageCheck" id="percentageCheck" />
						    
									
									<td width="19%"><input type="checkbox" name="candidate" id="candidate"  onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label
										class="set" name="cand.name" id="cand.name1"
										ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>
			
			                     <td width="40%"><input type="checkbox"  name="interviewer"
										id="interviewer" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="intrvName" id="intrvName1"
								ondblclick="callShowDiv(this);"><%=label.get("intrvName")%></label></td>
			
			
									
									
									</tr>
									
								<tr>
								
								<td width="19%"><input type="checkbox" name="intRoundType" id="intRoundType"  onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label
										class="set" name="intvRound" id="intvRound1"
					                       ondblclick="callShowDiv(this);"><%=label.get("intvRound")%></label></td>
										                      
							          <td width="40%"><input type="checkbox"  name="scoreParameter"   id="scoreParameter"  onclick="callInterviewCheck();"/>
									&nbsp;&nbsp;&nbsp;<label class="set"
										name="score.Parameter" id="score.Parameter"
										ondblclick="callShowDiv(this);"><%=label.get("score.Parameter")%></label>
									</td>
									</tr> 
		                      <tr> 
								
								
									<td width="19%"><input type="checkbox"  name="statusInt"
										id="statusInt" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="intstatus" id="intstatus1"
								ondblclick="callShowDiv(this);"><%=label.get("intstatus")%></label></td>
								<td width="19%"><input type="checkbox"  name="QF"
										id="QF" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="qualification" id="qualification1"
								ondblclick="callShowDiv(this);"><%=label.get("qualification")%></label></td>
									
							
									</tr>
									 <tr> 
								
								
									<td width="19%"><input type="checkbox"  name="TC"
										id="TC" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="technical" id="technical1"
								ondblclick="callShowDiv(this);"><%=label.get("technical")%></label></td>
								<td width="19%"><input type="checkbox"  name="CMM"
										id="CMM" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="communication" id="communication1"
								ondblclick="callShowDiv(this);"><%=label.get("communication")%></label></td>
									
							
									</tr>
									
								
									<td width="19%"><input type="checkbox"  name="Mgm"
										id="Mgm" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="managment" id="managment1"
								ondblclick="callShowDiv(this);"><%=label.get("managment")%></label></td>
								<td width="19%"><input type="checkbox"  name="Persn"
										id="Persn" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="prsonality" id="prsonality1"
								ondblclick="callShowDiv(this);"><%=label.get("prsonality")%></label></td>
									
							
									</tr>
									</tr>
									
								
									<td width="19%"><input type="checkbox"  name="Learn"
										id="Learn" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="learning" id="learning1"
								ondblclick="callShowDiv(this);"><%=label.get("learning")%></label></td>
								<td width="19%"><input type="checkbox"  name="Relev"
										id="Relev" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="relevant" id="relevant1"
								ondblclick="callShowDiv(this);"><%=label.get("relevant")%></label></td>
			
							
									</tr>
									</tr>
									
								
									<td width="19%"><input type="checkbox"  name="Suitab"
										id="Suitab" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="suitablity" id="suitablity1"
								ondblclick="callShowDiv(this);"><%=label.get("suitablity")%></label></td>
								<td width="19%"><input type="checkbox"  name="makeOffe"
										id="makeOffe" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="makeOff" id="makeOff1"
								ondblclick="callShowDiv(this);"><%=label.get("makeOff")%></label></td>
									
							
									</tr>
									</tr>
									
								
									<td width="19%"><input type="checkbox"  name="NextR"
										id="NextR" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="forwardNext" id="forwardNext1"
								ondblclick="callShowDiv(this);"><%=label.get("forwardNext")%></label></td>
								<td width="19%"><input type="checkbox"  name="Percent"
										id="Percent" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="percentage" id="percentage1"
								ondblclick="callShowDiv(this);"><%=label.get("percentage")%></label></td>
									
							
									</tr>
					
				
			</table>
			
			</div></td></tr>
			</td></tr></table>
			
		
  	<tr>
			   <td colspan="3">
			   		<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg">
			   		              <tr>								
								 <td width="100%" colspan="2" class="formtext"> <b>Display option</b> </td>
							   </tr>    
			   		        
			   		              
			   				 <tr><td align="left" width="21%">
								
								<input type="radio" name="onScrn" id="onScrn" value="V"  onclick="return viewOnScrn();"/>&nbsp;&nbsp;<label  name="viewScreen" id="viewScreen" ondblclick="callShowDiv(this);"><%=label.get("viewScreen")%></label>
									
						    </td></tr>
									
							<tr><td width="21%">
								
								<input type="radio" name="genRep" id="genRep"  onclick="return generateDropDown();"/>&nbsp;&nbsp;<label  name="report.type" id="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label></td>
								<td id="showDropDown" width="19%">
								<s:select name="seltype" id="seltype" cssStyle="width:154" list="#{'':'--Select--','Pdf':'Pdf','Txt':'Txt','Xls':'Xls'}"  />
								
							
							</td>
							
							</tr>
							 <tr>
							<td align="left" width="21%"><label  name="setting.name" id="setting.name" ondblclick="callShowDiv(this);"><%=label.get("setting.name")%></label> :</td>
							<td width="78%"><s:textfield
								name="settingName" theme="simple" readonly="false" size="25" maxlength="50" /></td>
					
						</td>			   		           
			   		           </tr>   
							</table> 
							</td>
					

			   
					
			<tr >
				   		 <td colspan="4">
					 				<input type="button" class="token" theme="simple" value="Generate Report" onclick="call();" />
									<input type="button" class="reset" theme="simple" value=" Reset" onclick="return callClear();" target="main"/>
									<input type="button" class="add"  onclick="callVal();"
										theme="simple" value="Save report criteria"  target="main"/></td>
			</tr>
		
</td></tr></table>
			
		
	
</s:form>
<script>


callOnLoad();
 genReport();
 callBack();
function callBack(){

if(document.getElementById('paraFrm_backFlag').value=="true"){
       var divID = document.getElementById('paraFrm_divId').value;
       var aID = document.getElementById('paraFrm_aId').value;
       if(aID=="" || aID=="null"){
       	callDivLoad('filtOpt', 'filterOption');
       }else{
       callDivLoad(aID,divID);
       }
		
     }else{
     
     callDivLoad('filtOpt', 'filterOption');
     } 

      
}






	function callOnLoad() {
		var divID= document.getElementById('paraFrm_divId').value;
		var aID = document.getElementById('paraFrm_aId').value;
		
		if(document.getElementById('paraFrm_divId').value=="") {
			divID = 'filterOption';
		}
		
		if(document.getElementById('paraFrm_aId').value=="") {
			aID = 'filtOpt';
		}
		
		document.getElementById('filterOption').style.display = 'none';
		document.getElementById('sortingOption').style.display = 'none';
		document.getElementById('columnDefinition').style.display = 'none';		
			
		document.getElementById(divID).style.display = '';
		document.getElementById('filtOpt').className = '';
		document.getElementById('sortOpt').className = '';
		document.getElementById('colDef').className = '';
		
		document.getElementById(aID).className = 'on';
	}
	
function callDivLoad(aID, divID) {

		document.getElementById('filterOption').style.display = 'none';
		document.getElementById('sortingOption').style.display = 'none';
		document.getElementById('columnDefinition').style.display = 'none';	
		
		//document.getElementById('SettingNameId').style.display = 'none';	
				
		document.getElementById('filtOpt').className = '';
		document.getElementById('sortOpt').className = '';
		document.getElementById('colDef').className = '';
		
		document.getElementById(aID).className = 'on';
		document.getElementById(divID).style.display = '';
		document.getElementById('paraFrm_divId').value =divID;
		document.getElementById('paraFrm_aId').value =aID;
		
	   //document.getElementById('paraFrm_settingName').value="";
	   
	  if(document.getElementById('paraFrm_buttonFlag').value){
	   
	   if(document.getElementById('paraFrm_sortRadioInt').value=='D')
	       	document.getElementById('paraFrm_RadioOneIntD').checked=true;
	   else 
	   document.getElementById('paraFrm_RadioOneIntA').checked=true;
	   if( document.getElementById('paraFrm_thenRadioInt').value=='D')
	       	document.getElementById('paraFrm_RadioTwoIntD').checked=true;
	 	else document.getElementById('paraFrm_RadioTwoIntA').checked=true;
	   if( document.getElementById('paraFrm_thanRadioInt').value=='D')
	  		 document.getElementById('paraFrm_RadioThreeIntD').checked=true;
	   else document.getElementById('paraFrm_RadioThreeIntA').checked=true;
	   }
	   else{
	  
	    document.getElementById('paraFrm_RadioOneIntA').checked=true;
	    document.getElementById('paraFrm_sortRadioInt').value='A'
	    document.getElementById('paraFrm_RadioTwoIntA').checked=true;
	    document.getElementById('paraFrm_thenRadioInt').value='A'
	    document.getElementById('paraFrm_RadioThreeIntA').checked=true;
	    document.getElementById('paraFrm_thanRadioInt').value='A'
	    }
	  if(document.getElementById('myChkFlag').value)
	  {
	   if(document.getElementById('candidateCheck').value=='Y')
	          document.getElementById('candidate').checked=true;
	   if(document.getElementById('interviewerCheck').value=='Y')
	          document.getElementById('interviewer').checked=true;
	   if(document.getElementById('intRoundCheck').value=='Y')
	           document.getElementById('intRoundType').checked=true;
	   if(document.getElementById('scoreCheck').value=='Y')
	              document.getElementById('scoreParameter').checked=true;
	   if(document.getElementById('statusCheck').value=='Y')
	              document.getElementById('statusInt').checked=true;
	  if( document.getElementById('qualificationCheck').value='Y')
	              document.getElementById('QF').checked=true;
	              
	   if(document.getElementById('technicalCheck').value='Y')           
	              document.getElementById('TC').checked=true;
        if( document.getElementById('communicationCheck').value='Y')
        document.getElementById('CMM').checked=true;
      if( document.getElementById('mangmentCheck').value='Y')
        document.getElementById('Mgm').checked=true;
       if(document.getElementById('relevantCheck').value='Y')
        document.getElementById('Relev').checked=true;
        if( document.getElementById('learningCheck').value='Y')
        document.getElementById('Learn').checked=true;
      if(document.getElementById('suitablityCheck').value='Y')
        document.getElementById('Suitab').checked=true;
        if( document.getElementById('makeOffferCheck').value='Y')
        document.getElementById('makeOffe').checked=true;
       if(document.getElementById('nextRoundCheck').value='Y')
        document.getElementById('NextR').checked=true;
        if(document.getElementById('personalityCheck').value='Y')
        document.getElementById('Persn').checked=true;
        if(document.getElementById('percentageCheck').value='Y')
        document.getElementById('Percent').checked=true;
        
       
	   
	  }
	  else{
	  
	    document.getElementById('candidate').checked=true;
        document.getElementById('candidateCheck').value='Y'
        document.getElementById('interviewer').checked=true;
        document.getElementById('interviewerCheck').value='Y'
        document.getElementById('intRoundType').checked=true;
        document.getElementById('intRoundCheck').value='Y'
        document.getElementById('scoreParameter').checked=true;
        document.getElementById('scoreCheck').value='Y'
        document.getElementById('statusInt').checked=true;
        document.getElementById('statusCheck').value='Y'
        document.getElementById('QF').checked=true;
        document.getElementById('qualificationCheck').value='Y'
        document.getElementById('TC').checked=true;
        document.getElementById('technicalCheck').value='Y'
        document.getElementById('CMM').checked=true;
        document.getElementById('communicationCheck').value='Y'
        document.getElementById('Mgm').checked=true;
        document.getElementById('mangmentCheck').value='Y'
        document.getElementById('Relev').checked=true;
        document.getElementById('relevantCheck').value='Y'
        document.getElementById('Learn').checked=true;
        document.getElementById('learningCheck').value='Y'
        document.getElementById('Suitab').checked=true;
        document.getElementById('suitablityCheck').value='Y'
        document.getElementById('makeOffe').checked=true;
        document.getElementById('makeOffferCheck').value='Y'
        document.getElementById('NextR').checked=true;
        document.getElementById('nextRoundCheck').value='Y'
        document.getElementById('Persn').checked=true;
        document.getElementById('personalityCheck').value='Y'
        document.getElementById('Percent').checked=true;
        document.getElementById('percentageCheck').value='Y'
        
        
        }
	     		
	}
	
function genReport(){
	    document.getElementById('onScrn').checked=true;
		document.getElementById('showDropDown').style.display ='none';

}

function viewOnScrn(){
		document.getElementById('genRep').checked=false;
		
	    document.getElementById('showDropDown').style.display ='none';

}



 function call(){ 

	/*if((!(document.getElementById("onScrn").checked)) && (!(document.getElementById("genRep").checked)))
	{
  			alert("Please select view on screen or report type");
  			return false;
  	 	}*/
  	 var seltype=document.getElementById("seltype").value;
  	 
  	if(document.getElementById("genRep").checked)
  	{ 
  	
  	 if(document.getElementById("seltype").value=="")
  	 	{
			alert('Please select report type');
			return false;
		}
		else
		{   document.getElementById('paraFrm').target="_blank"; 
			document.getElementById('paraFrm').action='InterviewAnalysis_generateReport.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}
	}
	
	else {
			document.getElementById('paraFrm').action='InterviewAnalysis_callJspView.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 	 
	
	}

   }
function generateDropDown(){
		document.getElementById('onScrn').checked=false;
		document.getElementById('showDropDown').style.display ='';

}


function callInterviewCheck(){
    
    if(document.getElementById('candidate').checked ){
		   document.getElementById('candidateCheck').value='Y'
		}
	else{	
		  document.getElementById('candidateCheck').value='N';
		}
		
	if(document.getElementById('intRoundType').checked ) {
        document.getElementById('intRoundCheck').value='Y'
		}
	else{		
		  document.getElementById('intRoundCheck').value='N';
		}
	if(document.getElementById('interviewer').checked ){
		   document.getElementById('interviewerCheck').value='Y'
		}
	else{		
		  document.getElementById('interviewerCheck').value='N';
		}
		
	if(document.getElementById('scoreParameter').checked ){
		   document.getElementById('scoreCheck').value='Y'
		}
	else{		
		  document.getElementById('scoreCheck').value='N';
		}
	
       if(document.getElementById('statusInt').checked ){
		   document.getElementById('statusCheck').value='Y'
		}
	else{		
		  document.getElementById('statusCheck').value='N';
		}
		
		 if(document.getElementById('QF').checked ){
		   document.getElementById('qualificationCheck').value='Y'
		}
	else{		
		  document.getElementById('qualificationCheck').value='N';
		}
		if(document.getElementById('TC').checked ){
		   document.getElementById('technicalCheck').value='Y'
		}
	else{		
		  document.getElementById('technicalCheck').value='N';
		}
		if(document.getElementById('CMM').checked ){
		   document.getElementById('communicationCheck').value='Y'
		}
	else{		
		  document.getElementById('communicationCheck').value='N';
		}
		if(document.getElementById('Mgm').checked ){
		   document.getElementById('mangmentCheck').value='Y'
		}
	else{		
		  document.getElementById('mangmentCheck').value='N';
		}
		if(document.getElementById('Persn').checked ){
		   document.getElementById('personalityCheck').value='Y'
		}
	else{		
		  document.getElementById('personalityCheck').value='N';
		}
		if(document.getElementById('Relev').checked ){
		   document.getElementById('relevantCheck').value='Y'
		}
	else{		
		  document.getElementById('relevantCheck').value='N';
		}
		if(document.getElementById('makeOffe').checked ){
		   document.getElementById('makeOffferCheck').value='Y'
		}
	else{		
		  document.getElementById('makeOffferCheck').value='N';
		}
		if(document.getElementById('Suitab').checked ){
		   document.getElementById('suitablityCheck').value='Y'
		}
	else{		
		  document.getElementById('suitablityCheck').value='N';
		}
		if(document.getElementById('NextR').checked ){
		   document.getElementById('nextRoundCheck').value='Y'
		}
	else{		
		  document.getElementById('nextRoundCheck').value='N';
		}
		if(document.getElementById('Learn').checked ){
		   document.getElementById('learningCheck').value='Y'
		}
	else{		
		  document.getElementById('learningCheck').value='N';
		}
		if(document.getElementById('Percent').checked ){
		   document.getElementById('percentageCheck').value='Y'
		}
	else{		
		  document.getElementById('percentageCheck').value='N';
		}
		
}


function callCand(){
		document.getElementById('intrvId').style.display ='none';
		
		document.getElementById('paraFrm_interviewerName').value="";
		callsF9(500,325,'InterviewAnalysis_f9Candidate.action');
}	
function callInterviewer(){
		
		document.getElementById('CandId').style.display ='none';
		document.getElementById('paraFrm_candName').value="";
		
		
		callsF9(500,325,'InterviewAnalysis_f9Interviewer.action');
}	
		function callVal(){
		
		var setting =document.getElementById('paraFrm_settingName').value;
		if(setting==""){
		alert("Please enter setting name.");
		return false;
		}
		var reqCode=document.getElementById('paraFrm_settingSearch').value;
		document.getElementById('paraFrm_settingCode').value=reqCode;
		document.getElementById('paraFrm').action='InterviewAnalysis_saveSetting.action';
		document.getElementById('paraFrm').submit();
		  
		  
		  
		  }   
		  
function callFilter(){
		var reqCode=document.getElementById('paraFrm_settingSearch').value;
		document.getElementById('paraFrm_settingCode').value=reqCode; 
		document.getElementById('paraFrm').action='InterviewAnalysis_getFilterDetails.action';
		document.getElementById('paraFrm').submit();
}
//checkIntRadio();
function checkIntRadio(){
	     if (document.getElementById('paraFrm_RadioOneIntD').checked ) {
        document.getElementById('paraFrm_sortRadioInt').value='D';
	    }
	 else{
	    document.getElementById('paraFrm_sortRadioInt').value='A';
	     
	   }
		  if( document.getElementById('paraFrm_RadioTwoIntD').checked){
	     document.getElementById('paraFrm_thenRadioInt').value='D';
	     }
	     else{
		  document.getElementById('paraFrm_thenRadioInt').value='A';
		  }
		  
		   if( document.getElementById('paraFrm_RadioThreeIntD').checked){
	     document.getElementById('paraFrm_thanRadioInt').value='D';
	     }
	     else{
		  document.getElementById('paraFrm_thanRadioInt').value='A';
		  }

	}


function callClear(){

           /* document.getElementById('paraFrm_candName').value="";
            document.getElementById('paraFrm_interviewerName').value="";
            document.getElementById('statusInt').value="";
            document.getElementById('scoreParameter').value="";
            document.getElementById('interviewer').value="";
            document.getElementById('intRoundType').value="";
            document.getElementById('candidate').value="";
           // document.getElementById('interviewerName').value="";
           // document.getElementById('interviewstatus').value="";
           // document.getElementById('intRound').value="";
            //document.getElementById('interviewRound').value="";
            //document.getElementById('sortByInt').value="";
            //document.getElementById('RadioOneInt').value="A";
            //document.getElementById('thenBYInt').value=""; 
            //document.getElementById('RadioTwoInt').value="A";
            //document.getElementById('secondByInt').value="";
            document.getElementById('RadioThreeInt').value="A";
            document.getElementById('onScrn').value="";  
            document.getElementById('genRep').value="";  
            document.getElementById('seltype').value="";
            document.getElementById('settingName').value=""; 
            document.getElementById('buttonFlag').value=false;
            document.getElementById('myChkFlag').value=false;
           //document.getElementById('candidateCheck').value='Y'
           //document.getElementById('candidate').value=true; */
                   
           document.getElementById('paraFrm').action='InterviewAnalysis_input.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
}
     
	</script>
