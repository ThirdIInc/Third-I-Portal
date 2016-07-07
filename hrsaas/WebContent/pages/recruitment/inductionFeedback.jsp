<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="com.lowagie.text.Document"%>
<s:form action="InductionFeedback" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<!-- Final Table -->

		<tr>
			<!--start Induction Feedback-->
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!--Table 1-->
				<tr>
					<td colspan="5" valign="bottom" class="txt">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td width="4%" valign="bottom" class="txt"><strong
								class="formhead"><img
								src="../pages/common/css/default/images/review_shared.gif"
								width="25" height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Induction
							 Feedback</strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img
								src="../pages/images/recruitment/help.gif" width="16"
								height="16" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<!-- End of Table 1--></td>
		</tr>
		<!--End Induction Feedback-->

		<tr>
			<td width="78%" colspan="2"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

			<td width="22%">
			<div align="right"><span class="style2"> <font
				color="red">*</font> </span> Indicates Required</div>
			</td>
		</tr>

		<tr>
			<!--Schedule interview-->
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!--Table 2-->
				<tr>
					<td width="100%"></td>
				</tr>
				<tr>
					<td width="100%"><strong>Induction Feedback</strong></td>
				</tr>
				<tr>
					<td width="100%"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<!--Table header-->
						<tr>
							<td width="15%">
								<label class="set" name="indname" id="indname" ondblclick="callShowDiv(this);"><%=label.get("indname")%></label>							:</td>
							<td width="25%"><s:property value="inductionName" /><s:hidden
								name="inductionCode" /> <s:hidden name="inductionDtlCode" /><s:hidden
								name="inductionName" /></td>
							<td width="15%">
								<label class="set" name="cntperson" id="cntperson" ondblclick="callShowDiv(this);"><%=label.get("cntperson")%></label>
							:</td>
							<td width="25%"><s:hidden name="cntPersonId" /><s:hidden
								name="cntPersonToken" /><s:property value="contactPerson" /> <s:hidden
								name="contactPerson" /></td>
						</tr>
						<tr>
							<td width="15%">
								<label class="set" name="indfrom" id="indfrom" ondblclick="callShowDiv(this);"><%=label.get("indfrom")%></label>
							:</td>
							<td width="25%"><s:property value="inductionFrom" /><s:hidden
								name="inductionFrom" /></td>
							<td width="15%">
								<label class="set" name="indto" id="indto" ondblclick="callShowDiv(this);"><%=label.get("indto")%></label>
							:</td>
							<td width="25%"><s:property value="inductionTo" /><s:hidden
								name="inductionTo" /></td>
						</tr>
						<tr>
							<td width="15%" colspan="1">
								<label class="set" name="inddesc" id="inddesc" ondblclick="callShowDiv(this);"><%=label.get("inddesc")%></label>
							:</td>
							<td colspan="3"><s:property value="inducDesc" /><s:hidden
								name="inducDesc" /></td>
						</tr>
						<tr>
							<td width="15%" colspan="1">
								<label class="set"	name="indvenue" id="indvenue" ondblclick="callShowDiv(this);"><%=label.get("indvenue")%></label>
							:</td>
							<td width="81%" colspan="3"><s:property value="inducVenue" /><s:hidden
								name="inducVenue" /></td>

						</tr>
					</table>
					<!--Table header--></td>
				</tr>
			</table>
			<!--Table 2--></td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%"></td>
				</tr>
				<tr>
					<td width="100%"><strong>Please give your comments
					about the Induction Program</strong></td>
				</tr>
				<tr>
					<td width="100%"></td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td width="2%" valign="top" class="formth"><b> 
								<label	class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label> </b>
							</td>
							<td width="15%" valign="top" class="formth"><b> 
								<label	class="set" name="parameter" id="parameter"	ondblclick="callShowDiv(this);"> <%=label.get("parameter")%></label> </b>
							</td>
							<td width="10%" valign="top" class="formth"><b> 
								<label class="set" name="rating" id="rating" ondblclick="callShowDiv(this);"> <%=label.get("rating")%></label> </b> <font color="red">*</font></td>
							<td width="30%" valign="top" class="formth"><b> 
								<label	class="set" name="rec.comments" id="rec.comments" ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b>
							</td>
						</tr>
						<%!int p = 1, s = 0;%>
						<s:iterator value="questionList">
							<tr>
								<td width="2%" class="sortableTD"><%=p%></td>
								<td width="15%" class="sortableTD"><s:hidden
									name="quesCode" /> <s:property value="quesName" /></td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:select
									name="rating" cssStyle="width:200" headerKey=""
									headerValue="--- Select Rating---" id='<%="h"+p%>' list="tmap"
									size="1" /></td>
						
								<td class="sortableTD" width="30%" nowrap="nowrap">
									<s:textarea	name="comments" id='<%="comments"+p%>' rows="3" cols="40" onkeyup="callcommentLength(id,'500');" /> &nbsp;&nbsp; 
										<img src="../pages/images/zoomin.gif" height="12" align="absmiddle"	width="12" theme="simple" onclick="javascript:callWindow('<%="comments"+p%>','<%="comments"+p%>','','paraFrm_remaincharComments<%=p%>','500');	" />&nbsp;&nbsp;
											Remaining chars <s:textfield id='<%="remaincharComments"+p%>' readonly="true" size="5" />
								</td>
							</tr>
							<%
							p++;
							%>
						</s:iterator>
						<input type="hidden" name="count" id="count" value="<%=p%>">
						<%
							s = p;
							p = 1;
						%>
					</table>
				</td>

				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<label class="set" name="suggestion" id="suggestion" ondblclick="callShowDiv(this);"> 
									<%=label.get("suggestion")%>
								</label> :
							</td>
							<td>
								<s:textarea cols="60" rows="2" name="suggestion" onkeyup="callLength('suggestion','remaincharsuggetion','500');" />&nbsp;&nbsp;
									<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple" onclick="javascript:callWindow('paraFrm_suggestion','suggestion','','paraFrm_remaincharsuggetion','500');">
									&nbsp;&nbsp;Remaining chars <s:textfield name="remaincharsuggetion" readonly="true" size="5" />
							</td>
						</tr>
						
						
						<!-- Updated by Anantha lakshmi -->
						<tr>
							<td width="20%">
								<label name="attach" id="attach" ondblclick="callShowDiv(this);"><%=label.get("attach")%></label>	:
							</td>
							<td colspan="1">
								<s:hidden name="dataPath" />
								<s:textfield size="20" name="attachFile" readonly="true" />
							<s:if test="showUploadButton">	
								<input type="button" class="token" name="Browse" value="Attach" onclick="uploadFile('attachFile');" />
							</s:if>
							<s:else>
								
							</s:else>	
							</td>
							<td>
								
							</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>
			<!--Table 3--></td>
		</tr>
		
		<tr>
			<td width="78%" colspan="2">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
			<td width="22%">&nbsp;</td>
		</tr>
	</table>
	<!-- Final Table -->
</s:form>

<script>

/**
* Function callcommentLength(typr,maxLength)
* and if(document.getElementById("remaincharComments"+i).value<="0") then it will display 
* alert for please enter upto 500 characters or less than 500 
*/
function callcommentLength(type,maxLenght){

		try{
	 
		var count = type;
  			var lengthType=count.replace("comments","");
  			 
			var cmt = document.getElementById(type).value;
			var remain = eval(maxLenght) - eval(cmt.length);
			document.getElementById('remaincharComments'+lengthType).value = remain;
			if(eval(remain)< 0){
				document.getElementById(type).style.background = '#FFFF99';			
			}
			else 
				document.getElementById(type).style.background = '#FFFFFF';
			
			 
		}
		catch(e)
		{
			alert("Error is : "+e);	 
		}
  			
	}


function searchFun(){ 
		callsF9(500,325,'InductionFeedback_f9Search.action');
	}
	
	function cancelFun(){
		document.getElementById("paraFrm").action="InductionFeedback_input.action";
	    document.getElementById("paraFrm").submit();
	}
	
function saveFun()
{
  		
  		var count = document.getElementById("count").value;
  		
  		if(count == 1){
  			alert("No questions for feedback");
  			return false;
  		} 
  		 
     for(i=1;i<count;i++)
       {
  		 if(document.getElementById("h"+i).value=="")
  		 {  		   
  		   alert("please select "+document.getElementById('rating').innerHTML.toLowerCase()+" for row No. "+i);
  		   document.getElementById("h"+i).focus();
  		    return false;
  		 }
 		 
  		 if(document.getElementById("remaincharComments"+i).value<0)
  		 {
  		 	alert("Maximum length of 'Comments' is 500 characters.");
  		 	return false;
  		 }
  		 
  	   }
  	   
  	   var suggestion = document.getElementById('paraFrm_suggestion').value;
  	   if(suggestion.length>500)
  	   {
  	   	alert("Maximum length of 'Suggestion' is 500 characters.");
  	   	return false;
  	   }	
		document.getElementById("paraFrm").action="InductionFeedback_save.action";
	    document.getElementById("paraFrm").submit();
	    
}
	
	function uploadFile(fieldName) {
		//alert(fieldName);
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
/*	
	function uploadFile(fieldName) 
	{
		var path="upload";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+"paraFrm_"+fieldName+'&image='+"image",'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}  	
*/	
</script>