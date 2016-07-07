<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="EmployeeSurvey" name="EmployeeSurvey" id="paraFrm"
	validate="true" target="main" theme="simple">
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head"><label
						id="empSurvey.label1" name="empSurvey.label"
						ondblclick="callShowDiv(this);"><%=label.get("empSurvey.label")%></label></strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="empSurveyCode" />
		<s:hidden name="finalizedStatFlag" />
		<s:if test="finalizedStatFlag">
			<tr>
				<td width="100%"><font color="red">This record is
				finalized. You cannot make any changes in the record.</font></td>
			</tr>
		</s:if>
		<%
			int totalQuestions = 0;
				int qnPageNo = 0;
		%>
		<%
			totalQuestions = (Integer) request
						.getAttribute("totalQuestions");
				qnPageNo = (Integer) request.getAttribute("qnPageNo");
		%>
		<s:if test="questionExists">
			<tr>
				<td width="100%"><s:if test="finalizedStatFlag">
					 <s:hidden name="first" id="first"/>
					 <s:hidden name="first1" id="first1"/>
					<input type="button" name=" First" title="First Page"
						value=" First" class="saveandprevious"
						onclick="callPage('1', 'F', '<%=totalQuestions%>', 'EmployeeSurvey_callSurveyPage.action?finalizeStatus=Y');"
						height="15" />
					<input type="button" name=" Previous" title="Previous Page"
						value=" Previous" class="saveandprevious"
						onclick="callPage('P', 'P', '<%=totalQuestions%>', 'EmployeeSurvey_callSurveyPage.action?finalizeStatus=Y');"
						height="15" />
						
						
					<input type="hidden" name="qnPageNoField" id="qnPageNoField"
						value="<%=qnPageNo%>" />
					
					<input type="button" name=" Next" title="Next Page" value=" Next"
						class="saveandnext"
						onclick="callPage('N', 'N', '<%=totalQuestions%>', 'EmployeeSurvey_callSurveyPage.action?finalizeStatus=Y')"
						height="15" />
					<input type="button" name=" Last" title="Last Page" value=" Last"
						class="saveandnext"
						onclick="callPage('<%=totalQuestions%>', 'L', '<%=totalQuestions%>', 'EmployeeSurvey_callSurveyPage.action?finalizeStatus=Y');"
						height="15" />
						
				</s:if> <s:else>
				<b id="first" style="display: none">
					<input type="button" name=" Save & First" title="First Page"
						value=" Save & First" class="saveandprevious"
						onclick="callPage('1', 'F', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N');"
						height="15" />
					<input type="button" name=" Save & Previous" title="Previous Page"
						value=" Save & Previous" class="saveandprevious"
						onclick="callPage('P', 'P', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N');"
						height="15" />
				</b>
					<input type="hidden" name="qnPageNoField" id="qnPageNoField"
						value="<%=qnPageNo%>" />
					<input type="button" name="save " title="Save Page"
						value=" Save" class="save"
						onclick="callPage('S', '<s:property value="myQnPage"/>', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N');"
						height="15" />
					<s:if test="%{finalize}">
					
					</s:if>
					<s:else>
					<input type="button" name=" Save & Next" title="Next Page"
						value=" Save & Next" class="saveandnext"
						onclick="callPage('N', 'N', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N')"
						height="15" />
					<input type="button" name=" Save & Last" title="Last Page"
						value=" Save & Last" class="saveandnext"
						onclick="callPage('<%=totalQuestions%>', 'L', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N');"
						height="15" />
					</s:else>
					<s:if test="%{finalize}">
						<input type="button" name=" Finalize" value=" Finalize"
							class="save" onclick="callFinalize();" height="15" />
					</s:if>
				</s:else></td>
			</tr>
		</s:if>

		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<s:textfield name="myQnPage" id="myQnPage" />
					<td width="100%">
					<table width="100%">
						<s:if test="questionExists">
							<%
								int count = 0;
										int i = 0, j = 0;
										int cn = qnPageNo * 10 - 10;
							%>
							<%!int d = 0;%>
							<s:iterator value="questionList">
								<s:hidden name="sectionHide" />
								<s:if test="sectionHide">
									<tr>
										<td colspan="2" width="100%">
										<table class="formbg" width="100%">
											<tr>
												<td width="100%"><strong><s:property
													value="showSection" /></strong></td>
											</tr>
										</table>
										</td>
									</tr>
								</s:if>

								<tr>
									<td class="" nowrap="nowrap"><%=++cn%>
									<%
										++i;
									%><s:hidden name="questionCode" value="%{questionCode}"
										id='<%="questionCode" + i%>' />)<b><s:hidden
										value="qnSrNo" /></b></td>
									<td class="" nowrap="nowrap"><b><s:property
										value="surveyQuestion" /></b><s:hidden value="%{surveyQuestion}"
										id='<%="surveyQuestion" + i%>' /><s:hidden value="%{typeFlag}"
										id='<%="typeFlag" + i%>' /><s:hidden value="%{finalizedStat}"
										id='<%="finalizedStat" + i%>' /><s:hidden name="finalizedStatItrFlag" /></td>
								</tr>
								<tr>
									<td></td>
									<td class="" nowrap="nowrap" align="left"><s:hidden
										name="questionType" id='<%="questionType" + i%>' /><s:hidden name="optionType" 
										id='<%="optionType" + i%>' /> <s:if
										test="%{typeFlag == 'SUBJ'}">
										<s:if test="finalizedStatItrFlag">
										<s:property value="comment" />
										</s:if>
										<s:else>
											<s:textarea name="comment" id='<%="comment"+i%>' rows="3"
												cols="80" onkeyup="callLength(id,'1000');">
											</s:textarea>
											<img src="../pages/images/zoomin.gif" height="12"
												align="absmiddle" width="12" theme="simple"
												onclick="javascript:callWindow('<%="comment"+i%>','Comments','','paraFrm_descCnt<%=i%>','1000');">
											&nbsp;Remaining chars <input type="text" name="descCnt"
												id='paraFrm_descCnt<%=i%>' readonly="true" size="5" />
											</s:else>
										<input type="hidden" value='<s:property value="comment"/>' />
									</s:if>
									<s:else>
										<s:hidden name="comment" id='<%="comment" + i%>' />
										<s:iterator value="optionList">
											<%
												++j;
											%>
											<s:hidden name="optionCode" id='<%="optionCode" + j%>' />
											<s:if test="finalizedStatItrFlag">
												<input type="radio" name='<%="optionCheck"+i%>'
												id='<%="optionCheck"+j%>' disabled="true"  />
											</s:if>
											<s:else>
											
											<input type="radio" name='<%="optionCheck"+i%>'
												id='<%="optionCheck"+j%>'
												onclick="onCheck(<s:property value="optionCode"/>,'<%=j%>','<%=i%>', this)" />
												
												
											</s:else>
											<s:property value="optionName" />
											<script type="text/javascript">
										records[<%=j%>] = document.getElementById('optionCode' + '<%=j%>').value;
									</script>
										</s:iterator>
										<s:hidden name="optionLength" id='<%="optionLength" + i%>'/>
									</s:else></td>
								</tr>
								<tr>
									<td>&nbsp;<s:hidden name="optionValues"
										id='<%="optionValues" + i%>' /> <s:hidden name="sectionCode"
										id='<%="sectionCode" + i%>' /></td>
								</tr>
							</s:iterator>
							<%
								d = i;
							%>

						</s:if>
						<s:else>
							<tr>
								<td colspan="4" align="center"><font color="red">No
								Data to Display</font></td>
							</tr>
						</s:else>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="questionExists">
			<tr>
				<td width="100%"><s:if test="finalizedStatFlag">
				 
					<input type="button" name=" First" title="First Page"
						value=" First" class="saveandprevious"
						onclick="callPage('1', 'F', '<%=totalQuestions%>', 'EmployeeSurvey_callSurveyPage.action?finalizeStatus=Y');"
						height="15" />
					<input type="button" name=" Previous" title="Previous Page"
						value=" Previous" class="saveandprevious"
						onclick="callPage('P', 'P', '<%=totalQuestions%>', 'EmployeeSurvey_callSurveyPage.action?finalizeStatus=Y');"
						height="15" />
					
					<input type="hidden" name="qnPageNoField" id="qnPageNoField"
						value="<%=qnPageNo%>" />
					
					<input type="button" name=" Next" title="Next Page" value=" Next"
						class="saveandnext"
						onclick="callPage('N', 'N', '<%=totalQuestions%>', 'EmployeeSurvey_callSurveyPage.action?finalizeStatus=Y')"
						height="15" />
					<input type="button" name=" Last" title="Last Page" value=" Last"
						class="saveandnext"
						onclick="callPage('<%=totalQuestions%>', 'L', '<%=totalQuestions%>', 'EmployeeSurvey_callSurveyPage.action?finalizeStatus=Y');"
						height="15" />
					
				</s:if> <s:else>
				<b id="first1" style="display: none" >
					<input type="button" name=" Save & First" title="First Page"
						value=" Save & First" class="saveandprevious"
						onclick="callPage('1', 'F', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N');"
						height="15" />
					<input type="button" name=" Save & Previous" title="Previous Page"
						value=" Save & Previous" class="saveandprevious"
						onclick="callPage('P', 'P', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N');"
						height="15" />
					</b>
					<input type="hidden" name="qnPageNoField" id="qnPageNoField"
						value="<%=qnPageNo%>" />
					<input type="button" name="save " title="Save Page"
						value=" Save" class="save"
						onclick="callPage('S', '<s:property value="myQnPage"/>', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N');"
						height="15" />
					<s:if test="%{finalize}">
					
					</s:if>
					<s:else>
					<input type="button" name=" Save & Next" title="Next Page"
						value=" Save & Next" class="saveandnext"
						onclick="callPage('N', 'N', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N')"
						height="15" />
				
					<input type="button" name=" Save & Last" title="Last Page"
						value=" Save & Last" class="saveandnext"
						onclick="callPage('<%=totalQuestions%>', 'L', '<%=totalQuestions%>', 'EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=N');"
						height="15" />
					</s:else>	
					<s:if test="%{finalize}">
						<input type="button" name=" Finalize" value=" Finalize"
							class="save" onclick="callFinalize();" height="15" />
					</s:if>
				</s:else></td>
			</tr>
		</s:if>
		<s:hidden name="finalize" />
		<s:hidden name="hiddencode" />
		<s:hidden name="last" id="last"/>
	</table>
</s:form>

<script>
	setOptionsOnload();
	
	function callLength(type,maxLenght){
  			var count = type;
  			var lengthType=count.replace("comment","");
			var cmt = document.getElementById(type).value;
			var remain = eval(maxLenght) - eval(cmt.length);
			document.getElementById('paraFrm_descCnt'+lengthType).value = remain;
			if(eval(remain)< 0){
				document.getElementById(type).style.background = '#FFFF99';
			}
			else 
				document.getElementById(type).style.background = '#FFFFFF';
	}
	
	function callWindow(fieldName,windowName,readFlag,charCount,maxLength) {
		   	mytitle=windowName;
			window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=500,height=430,scrollbars=no,resizable=no,top=250,left=350');
			document.getElementById('paraFrm').target ="main";	 
	}
	
	function callFinalize(){
		var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){
	  		var question = 	document.getElementById('surveyQuestion'+a).value;
	  		var hiddenValue=document.getElementById('optionValues'+a).value;
		}
		var con=confirm('Do you want to finalise the survey ?');
		if(con){
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action="EmployeeSurvey_saveAndCallSurveyPage.action?finalizeStatus=Y";
		  	document.getElementById('paraFrm').submit();
		  }
	}
	
	function ckSet(ck){ 
	 var ck_arr=document.getElementsByName(ck.name); 
	 var ca_ln=ck_arr.length; 
	 if(ck.checked){ 
	  for(var i=0;i<ca_ln;i++){ 
	   if(ck_arr[i].id!=ck.id)ck_arr[i].disabled=true; 
	  } 
	 }else{ 
	  for(var i=0;i<ca_ln;i++){ 
	   ck_arr[i].disabled=false; 
	  } 
	 } 
	}
	
	
	function onCheck(optionCode,j,i,chk)
   	{
   	try{
		var optionvalues = trim(document.getElementById('optionValues'+i).value);
		document.getElementById('optionValues'+i).value="";
	   document.getElementById('optionValues'+i).value=optionCode;
	 
	   /*
	    if(document.getElementById('optionCheck'+j).checked == true)
	   {	  
	    document.getElementById('optionValues'+i).value = document.getElementById('optionValues'+i).value+optionCode+",";
	   }
	   else{
	   			var hiddenValue=document.getElementById('optionValues'+i).value;
	        	hiddenValue=hiddenValue.substr(0,hiddenValue.length-1);
	        	var splittedValue=hiddenValue.split(",");
	        	var finalValue="";
	        	for(var k=0;k<splittedValue.length;k++){
	        		if(optionCode!=splittedValue[k]){
	        			finalValue+=splittedValue[k]+",";
	        		}
	        	}
	        	
	  	 	document.getElementById('optionValues'+i).value=finalValue;
	   
	   */
	   }catch(e){
	   	alert(e);
	   }
   }
	
	function callPage(id, pageImg, totalPageHid, action) {	
	try{	
		pageNo = document.getElementById('qnPageNoField').value;	
		actPage = document.getElementById('myQnPage').value; 
		
		//alert("page no--"+pageImg);
		
		alert("PageImg(MyQnPage)........."+pageImg+"......... ID........"+id);
		alert("MyQnPage......."+actPage);
        var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){
	  		var question = 	document.getElementById('surveyQuestion'+a).value;
	  		var hiddenValue=document.getElementById('optionValues'+a).value;
			if(hiddenValue!=null && hiddenValue!=""){
				hiddenValue=hiddenValue.substr(0,hiddenValue.length-1);
		       	var splittedValue=hiddenValue.split(",");
		       	if(document.getElementById('optionType'+a).value=="N"){
			       	if(splittedValue.length > 1){
			       		alert("You cannot check more than one option for question "+a);
			       		return false;
			       	}
			    }
		    }
		}
       	
       	
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('qnPageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('qnPageNoField').value = actPage;
			    document.getElementById('qnPageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('qnPageNoField').value=actPage;
			    document.getElementById('qnPageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('qnPageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('qnPageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('qnPageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('qnPageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('qnPageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('qnPageNoField').value;
			id = eval(p) + 1;
		}
		if(id=='S'){
		var p = document.getElementById('qnPageNoField').value;
			id = eval(p);
		}
		
		
		document.getElementById('myQnPage').value = id;
		alert("Now........."+document.getElementById('myQnPage').value);
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		}catch(e){
		alert(e);
		}
	}
	
	
		function callPageText(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('qnPageNoField').value;
		 	var actPage = document.getElementById('myQnPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('qnPageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('qnPageNoField').focus();
		     document.getElementById('qnPageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('qnPageNoField').focus();
		     document.getElementById('qnPageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('qnPageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('myQnPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	function setOptionsOnload(){
		try{
		
		var page=document.getElementById('myQnPage').value;
	    if(page=='1'){
	      document.getElementById('first').style.display='none';
	      document.getElementById('first1').style.display='none';
	    }else{
	     document.getElementById('first').style.display='';
	     document.getElementById('first1').style.display='';
       	}
       	
		var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){
			var hiddenValue=document.getElementById('optionValues'+a).value;
			if(hiddenValue!=null && hiddenValue!=""){
			
		       	//var splittedValue=hiddenValue.split(",");
		       	for(var i = 1; i < records.length; i++) {
			       	if(	document.getElementById('paraFrm_finalizedStatItrFlag').value=='true'){
			       	document.getElementById('optionCheck'+i).disabled = true;
			       	}
		       	
		       		//for(var k=0;k<splittedValue.length;k++){
		       			if(document.getElementById('optionCode'+i).value==hiddenValue){
		       				document.getElementById('optionCheck'+i).checked = true;
		       			}
		       		//}
		       	}
			}
	    }
       
       	}catch(e){
       		alert(e);
       	}
	}
	
</script>