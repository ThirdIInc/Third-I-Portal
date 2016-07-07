
<%@ taglib prefix="s" uri="/struts-tags"%>

 

<s:form action="EventData" id="paraFrm" theme="simple"
	name="paraFrmName">

	<%
		String category_Code = null;
		try {

			category_Code = request.getParameter("categoryCode");

		} catch (Exception e) {
			 
		}
	%>
	<table width="910" border="0" align="left" cellpadding="0"
		bgcolor="white" cellspacing="0">
		<input type="hidden" id="categoryCode" value="<%=category_Code%>" />
	 
		<tr>
			<td width="235" valign="top"><%@include
				file="../portal/leftglofest.jsp"%></td>
			<td width="57%" valign="top">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100%" colspan="2">&nbsp;</td>

				</tr>
				<%
				if (category_Code.equals("1")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/glofestbanner.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>

				<%
				if (category_Code.equals("2")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/gclbanner.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>

				<%
				if (category_Code.equals("3")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/gtlcarebanner.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>


				<%
				if (category_Code.equals("4")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/ideabanner.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>


				<%
				if (category_Code.equals("5")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/connexions.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>


				<%
				if (category_Code.equals("6")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/reward.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>

				<%
				if (category_Code.equals("7")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/colorband.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>

				<%
				if (category_Code.equals("8")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/helpdesk.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>

				<%
				if (category_Code.equals("9")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/induction.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>

				<%
				if (category_Code.equals("10")) {
				%>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/pages/portal/images/forum.gif"
						width="675" height="97" /></td>
				</tr>
				<%
				}
				%>

				<tr>
					<td height="5"></td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="90%" border="0" cellpadding="0" cellspacing="0">

						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td class="header1">Feedback Form</td>
							<td width="22%" class="blacklink">
							<div align="right"><span><font color="red">*</font></span>
							Indicates Required</div>
							</td>
						</tr>


						</tr>

					</table>

					<label></label></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>

							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">


								<tr>
									<td nowrap="nowrap" class="contlink">Feedback Title<font
										color="red">*</font> :</td>
									<td><s:textfield theme="simple" name="feedbackTitle" id="feedbackTitle"
										size="78" /></td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>

								<tr>
									<td nowrap="nowrap" class="contlink">Feedback
									Description<font color="red">*</font> :</td>
									<td><s:textarea theme="simple" rows="5" cols="60"  id="feedbackDesc"
										name="feedbackDesc" /></td>
								</tr>



							</table>
							</td>
						</tr>


					</table>
					</td>
				</tr>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">



						<tr>
							<td colspan="4" align="center"><input type="button"
								class="blacklink" value="Submit" onclick="return addFeedback();" /></td>

							<td width="22%"></td>
						</tr>


						</tr>

					</table>

					<label></label></td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
</s:form>



<script>

 
 
function callsave()
{
		try{
		var title = document.getElementById('feedbackTitle').value;
	var desc = document.getElementById('feedbackDesc').value;
	var categoryCode =document.getElementById('categoryCode').value;
		if(title=="")
		{
			alert("Please enter feedback title");
			return false ; 
		}	
		if(desc=="")
		{
			alert("Please enter feedback description");
			return false ;
		}
		
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/EventData_saveFeedbackDetails.action?categoryCode='+categoryCode+'&title='+title+'&desc='+desc;
		document.getElementById('paraFrm').submit();
		
		}catch(e){ alert(e);}
	}
</script>

<script>

	//global variables
	var req;

	function addFeedback() {
		try{
		var title = document.getElementById('feedbackTitle').value;
		var desc = document.getElementById('feedbackDesc').value;
		var categoryCode =document.getElementById('categoryCode').value;
		if(title=="")
		{
			alert("Please enter feedback title");
			return false ; 
		}	
		if(desc=="")
		{
			alert("Please enter feedback description");
			return false ;
		}
		
	
		try {
			url = '<%=request.getContextPath()%>/portal/EventData_saveFeedbackDetails.action?categoryCode='+categoryCode+'&title='+title+'&desc='+desc;
			//alert(url);
		} catch(e) {
			alert(e);
		}
		if (window.XMLHttpRequest) { // Non-IE browsers
			req = new XMLHttpRequest(); // XMLHttpRequest object is created
		    req.onreadystatechange = processStateChange; // XMLHttpRequest object is configured with a callback function
		    try {
		    	/**
		    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
		    	 * if false, send operations are synchronous, browser doesn't accept any input/output
		    	**/
		    	req.open("GET", url, true);
		    } catch (e) {
				alert("Problem Communicating with Server\n"+e);
			}
			req.send(null);
		} else if (window.ActiveXObject) { // IE 
			req = new ActiveXObject("Microsoft.XMLHTTP");
		    if (req) {
		    	req.onreadystatechange = processStateChange;
		      	req.open("GET", url, true);
		       	req.send();
		    }
		}
		
		}catch(e){alert(e);}
			}
	
	function processStateChange() {
			// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
		if(req.readyState == 4) { // Complete
			if (req.status == 200) { // OK response
		    	//responseXML: XML document of data returned from the server
		    	var res = req.responseText; // String version of data returned from the server
		    	 alert(res);
		    	  document.getElementById('feedbackTitle').value="";
		 	document.getElementById('feedbackDesc').value="";
		     	
			}
			parent.frames[2].name = 'main';
		}
	}
	

</script>
