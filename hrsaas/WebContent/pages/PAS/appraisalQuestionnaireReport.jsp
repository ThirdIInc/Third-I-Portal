<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf' %>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="QuestionnaireReportForm" validate="true" id="paraFrm" theme="simple">
 <s:hidden name="report" />
 <s:hidden name="reportAction" value='AppraisalQuestionnaireReport_getReport.action' />
 <table width="100%" border="0" cellpadding="2" cellspacing="2">
	<tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			<tr>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">

					<tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt">
							<strong class="text_head">
							<label  class = "set" name="questionnairereport.heading"  id="questionnairereport.heading" ondblclick="callShowDiv(this);"><%=label.get("questionnairereport.heading")%></label> 
							</strong>
						</td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/help.gif"
							width="16" height="16" /></div>
						</td>
					</tr>

				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	  
	 <tr>
	<td colspan="3">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap"><a href="#" onclick="resetFun()">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
	   </table>
	  </td>
	</tr>
	<tr>
	 <td>		
        <div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp"
			scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
			name="htmlReport" width="100%" height="200"></iframe></div>
	 </td>
	</tr>
	   <tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr> 
    
 	
 	<tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2" id="reportBodyTable">
            
            <!--  
             <tr>
					<td width="20%" colspan="1"> 
					Question Category:</td>
					<td width="13%" colspan="1">
					<s:hidden name="sQuestionCategoryCode" />
					<s:textarea cols="30" rows="1" name="QuestionCategory" readonly="true" />
					</td>
					<td width="20%" colspan="1"> 
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_QuestionCategory',350,350,'AppraisalQuestionnaireReport_f9QuestionCategory.action',event,'false','no','right')">
					</td>
                     <td></td>
             <tr>-->
     
              
              
            <tr>
              <td width="15%" colspan="1" height="20" class="formtext" nowrap="nowrap">
              	<label  class = "set" name="questionnairereport.questioncategory"  id="questionnairereport.questioncategory" ondblclick="callShowDiv(this);"><%=label.get("questionnairereport.questioncategory")%></label> :
              </td>
			 <!--  <td width="35%" colspan="1" height="20" align="left">
			  	<s:select name="sQuestionCategoryCode" list="lstQuestionCategory" headerKey="All Category" headerValue="--All Category--" />
				</select>
             </td> -->
             <td width="13%" colspan="1">
			<s:hidden name="sQuestionCategoryCode" />
			<s:textarea cols="30" rows="1" name="QuestionCategory" readonly="true" />
			<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
			onclick="javascript:callDropdown('paraFrm_QuestionCategory',350,350,'AppraisalQuestionnaireReport_f9QuestionCategory.action',event,'false','no','right')">
			</td>
             
			  <td width="15%" colspan="1" height="20">
			  	<label  class = "set" name="questionnairereport.questiontype"  id="questionnairereport.questiontype" ondblclick="callShowDiv(this);"><%=label.get("questionnairereport.questiontype")%></label> :
			  </td> 
			  
			  <td width="35%" colspan="1" height="20">
			  	<s:select name="sQuestionType" list="#{'D':'Descriptive','O':'Objective'}" headerKey="All" headerValue="--All--" />
				</select>
			  </td>
            </tr>
            
            <tr>
           	  <td width="15%" colspan="1" height="20">
			  	<label  class = "set" name="questionnairereport.status"  id="questionnairereport.status" ondblclick="callShowDiv(this);"><%=label.get("questionnairereport.status")%></label> :
			  </td> 
			  
			  <td width="35%" colspan="1" height="20">
			  	<s:select name="sQuestionStatus" list="#{'A':'Active','D':'De-Active'}" headerKey="All" headerValue="--All--" />
				</select>
			  </td>
            
              <td width="15%" colspan="1" height="20">
			  	<label  class = "set" name="questionnairereport.mandatory"  id="questionnairereport.mandatory" ondblclick="callShowDiv(this);"><%=label.get("questionnairereport.mandatory")%></label> :
			  </td> 
			  
			  <td width="35%" colspan="1" height="20">
			  	<s:select name="sQuestionMandatory" list="#{'Y':'Yes','N':'No'}" headerKey="All" headerValue="--All--" />
				</select>
			  </td>
            </tr>
        
            </table></td>
          </tr>
          
      </table></td>
    </tr>
      
    <tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<!-- <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /> -->
		   </td>
            <td width="22%"></td>
          </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
    <tr>
	<td colspan="3">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap"><a href="#" onclick="resetFun();">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
		</td>
		</tr>
		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
</table>
</s:form>
<SCRIPT LANGUAGE="JavaScript">
var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);
</SCRIPT>
<script type="text/javascript">
	function reportFun()
	{
		//var fields = ["paraFrm_sAppCode"];
		//var labels = [document.getElementById("questionnairereport.appraisalcode").innerHTML]
		//var types = ["select"];
		//if(!(checkMandatory(fields,labels,types)))
		//{
		//	return false;
		//}
		
		callReport('AppraisalQuestionnaireReport_getReport.action');
	}

	function resetFun()
	{
		
		document.getElementById('paraFrm').action='AppraisalQuestionnaireReport_reset.action';
		document.getElementById("paraFrm").target="_self";
	    document.getElementById("paraFrm").submit();


	}


//Added by Tinshuk Banafar ...Begin....
	
	function callReport(type){	
	try{
		
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		 }
		 catch (e){
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
	 
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='AppraisalQuestionnaireReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
	//Added by Tinshuk Banafar ...End....


</script>