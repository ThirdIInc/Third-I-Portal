<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
 

 
<s:form action="EventData" id="paraFrm" theme="simple"
	name="paraFrmName">

	<%
		Object[][] yearObj = null;
		Object[][] imageData = null;
		Object[][] eventData = null;
		Object[][] videoData = null;
		Object[][] contentData = null;
		Object[][] eventYearListObj = null;
		String yearValue = null;
		Object[][] eventNameQueryObj = null;

		String category_Code = null;
		String selectedStr = "";
		try {
			yearObj = (Object[][]) request.getAttribute("yearObj");
			imageData = (Object[][]) request
			.getAttribute("glofestImageObj");
			eventData = (Object[][]) request.getAttribute("aboutEventData");
			videoData = (Object[][]) request.getAttribute("aboutVideoData");
			contentData = (Object[][]) request
			.getAttribute("aboutContentData");
			eventYearListObj = (Object[][]) request
			.getAttribute("eventYearListObj");
			category_Code = request.getParameter("categoryCode");
			yearValue = request.getParameter("yearValue");
			eventNameQueryObj = (Object[][]) request
			.getAttribute("eventNameQueryObj");

			selectedStr = (String) request.getAttribute("selectedTab");
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>

	<table width="100%" border="0" align="left" cellpadding="0"
		bgcolor="white" cellspacing="0">
		<s:hidden name="yearFlag" id="yearFlag" />
		<s:hidden name="imageDataFlag" id="imageDataFlag" />
		<s:hidden name="eventDataFlag" id="eventDataFlag" />
		<s:hidden name="videoDataFlag" id="videoDataFlag" />
		<s:hidden name="contentDataFlag" id="contentDataFlag" />
		<input type="hidden" id="yearValue" value="<%=yearValue%>" />
		<input type="hidden" id="categoryCode" value="<%=category_Code%>" />
		<s:hidden name="checkSelectTab" id="checkSelectTab"
			value="<%=selectedStr%>" />

		<tr>
			 
			<td width="57%" valign="top" align="left">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100%" colspan="2">&nbsp;</td>

				</tr>

				<tr>
					<td height="5"></td>
				</tr>

				<tr>

					<td width="100%"><script>
					function callYearEventPage(yearValue)
					{
					try{
				 document.getElementById('yearValue').value=yearValue;
				 var categoryCode =document.getElementById('categoryCode').value;
				  var selectTab =document.getElementById('checkSelectTab').value;
				document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode='+categoryCode+'&yearValue='+yearValue+'&check='+selectTab;
				document.getElementById('paraFrm').submit();
				}
				catch(e){ //alert(e);
				}
					}
				 
					</script> <s:if test="yearFlag">
						<div id="portalTab" style="vertical-align: bottom">
						<ul class="red1 a1 border">


							<%
									if (yearObj != null && yearObj.length > 0) {
									for (int i = 0; i < yearObj.length; i++) {
							%>

							<li><a href="javascript:void(0);" id="selectId<%=i%>"
								onclick="callCurrent('<%=i%>','<%=yearObj.length%>');callYearEventPage('<%=yearObj[i][0]%>');"
								title="home"><span><%=yearObj[i][0] + "-" + yearObj[i][1]%></span></a></li>
							<%
								}
								}
							%>

						</ul>
						</div>
					</s:if></td>
				</tr>

				<tr>
					<td height="4px"></td>
				</tr>
				<tr id="eventNameId">
					<td height="30" class="eventheader">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">

						<tr>
							<td>
							<%
									if (eventNameQueryObj != null && eventNameQueryObj.length > 0) {
									for (int i = 0; i < eventNameQueryObj.length; i++) {
							%> <%=eventNameQueryObj[i][0]%> <%
 	}
 	}
 %>
							</td>
							<td>
							<table width="47%" border="0" align="right" cellpadding="1"
								cellspacing="1">
								<tr>

									<%
												if ((imageData != null && imageData.length > 0)
												|| (eventData != null && eventData.length > 0)
												|| (videoData != null && videoData.length > 0)
												|| (contentData != null && contentData.length > 0)) {
									%>
									<td width="19%"><a href="javascript:void(0);"
										class="orange"><img onclick="callPage('Event')"
										src="../pages/portal/images/back_icon.gif" width="16"
										height="16" border="0" /></a></td>
									<td width="81%" nowrap="nowrap"><span style="cursor: pointer"
										onclick="callPage('Event')" class="orange"><strong>Back
									to List</strong></span></td>
									<%
									}
									%>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			

				<tr id="lineId1">
					<td>
					<hr size="1" class="headerline">
					</td>
				</tr>

				<tr>
					<td>
					<%
					if (imageData != null && imageData.length > 0) {
					%> <s:if test="imageDataFlag">
						<span style="cursor: pointer"
							onclick="callCurrentType('imageclassId');callPage('Image')"
							id="imageclassId"><strong>Image Gallery</strong> <span
							class="portalOnLink" id="imageclassId">| </span> </span>
					</s:if> <%
					}
					%> <%
 if (eventData != null && eventData.length > 0) {
 %> <s:if test="eventDataFlag">
						<span style="cursor: pointer" id="abouteventId"
							onclick="callCurrentType('abouteventId');callPage('About')"><strong>About
						Event</strong></span>
					</s:if> <%
 }
 %> <%
 if (videoData != null && videoData.length > 0) {
 %><span class="portalOnLink"><strong>|</strong></span> <%
 }
 %> <%
 if (videoData != null && videoData.length > 0) {
 %> <s:if test="videoDataFlag">
						<span style="cursor: pointer"
							onclick="callCurrentType('videoId');callPage('Videos')"
							id="videoId"><strong>Videos</strong> </span>
					</s:if> <%
 }
 %> <%
 if (contentData != null && contentData.length > 0) {
 %>  
 <span class="portalOnLink" id="contentDataId1"><strong>|</strong></span>
 <%
 } 
 %> <%
 if (contentData != null && contentData.length > 0) {
 %> <s:if test="contentDataFlag">
						 <span style="cursor: pointer;" 
							onclick="callCurrentType('contentDataId');callPage('Content')"
							id="contentDataId"><strong>Content</strong></span>
					</s:if> <%
 }
 %>
					</td>
				</tr>
				<tr id="lineId">
					<td>
					<hr size="1" class="headerline">
					</td>


				</tr>
				<tr>
					<td>


					<div id="ImageID"><script
						src="../pages/portal/src/jquery.min.js"></script> <script
						src="../pages/portal/src/galleria.js"></script>
					<style>
#galleria {
	height: 500px;
}
</style>


					<div id="galleria">
					<%
							if (imageData != null && imageData.length > 0) {
							for (int i = 0; i < imageData.length; i++) {
					%> <img 
						src="../pages/images/<%=session.getAttribute("session_pool")%>/employeePortal/<%=imageData[i][0] %>">
					<%
						}
						}
					%>
					</div>
					<script>
    // Load the classic theme
    Galleria.loadTheme('../pages/portal/src/galleria.classic.js');
    // Initialize Galleria
    $('#galleria').galleria();
    </script></div>
					<div id="aboutID" style="display: none" height="100%">
					<table  width="100%">
						<tr>
							<td width="100%">
							<%
							if (eventData != null && eventData.length > 0) {
							%> <span class="txt">
							<table width="100%">
								<tr>
									<td width="20%" class="contlink"><strong>Event
									Name</strong></td>
									<td width="80%"><%=eventData[0][0]%></td>
								</tr>
								<tr>
									<td width="20%" class="contlink"><strong>Event
									Year</strong></td>
									<td width="80%"><%=eventData[0][1]%></td>
								</tr>
								<tr>
									<td width="20%" class="contlink"><strong>Event
									Date</strong></td>
									<td width="80%"><%=eventData[0][2]%></td>
								</tr>
								<tr>
									<td width="20%" class="contlink"><strong>Event
									Location</strong></td>
									<td width="80%"><%=eventData[0][3]%></td>
								</tr>
								<tr>
									<td width="20%" class="contlink"><strong>Event
									Description</strong></td>
									<td width="80%" align="left" style="text-align: justify;"><%=Utility.checkNull(String.valueOf(eventData[0][4]).replace("\n","<BR>"))%>									</td>
								</tr>
							</table>

							</span> <%
 }
 %>
							</td>
						</tr>
					</table>

					</div>

					<div id="videoID" style="display: none"></div>

					<div id="contentID" style="display:none;">

					<table>
						<%
								if (contentData != null && contentData.length > 0) {
								for (int i = 0; i < contentData.length; i++) {
						%>

						<tr>
							<td><img src="../pages/portal/images/next_icon.gif"
								width="16" height="16" border="0" /></td>

							<td height="24"><a target="_blank"
								href="../pages/images/<%=session.getAttribute("session_pool")%>/employeePortal/<%=contentData[i][1] %>"
								class="contlink"> <%=contentData[i][0]%> </a></td>
						</tr>
						<%
							}
							}
						%>

					</table>
					</div>


					<div id="eventList" style="display: none"><script>
					function callEventPage(id)
					{
					try{
				var yearValue =document.getElementById('yearValue').value;		
				var categoryCode =document.getElementById('categoryCode').value;	
			 	var selectTab =document.getElementById('checkSelectTab').value;
			 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode='+categoryCode+'&eventCode='+id+'&yearValue='+yearValue+'&check='+selectTab;
				document.getElementById('paraFrm').submit();
				}
				catch(e){ //alert(e);
				}
				
					}
					</script>



					<table>
						<%
								if (eventYearListObj != null && eventYearListObj.length > 0) {

								for (int i = 0; i < eventYearListObj.length; i++) {
						%>

						<tr>
							<td><img src="../pages/portal/images/next_icon.gif"
								width="16" height="16" border="0" /></td>
							<td height="24"><a href="javascript:void(0);"
								onclick="callEventPage('<%=eventYearListObj[i][0]%>')"
								class="contlink"> <%=eventYearListObj[i][1]%> </a></td>
						</tr>
						<%
							}
							}
					
						%>

					</table>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	
</s:form>
 

	<script type="text/javascript">
				
				function callPage(actionName)
				{
					try{
					document.getElementById('ImageID').style.display='none';
					document.getElementById('aboutID').style.display='none';
					document.getElementById('videoID').style.display='none';
					document.getElementById('contentID').style.display='none';
					document.getElementById('eventList').style.display='none';
					
					if(actionName=='Image')
					{
					document.getElementById('ImageID').style.display='inline';
					}
					if(actionName=='About')
					{
					document.getElementById('aboutID').style.display='';
					}
					if(actionName=='Videos')
					{
					document.getElementById('videoID').style.display='';
					}
					if(actionName=='Content')
					{
					document.getElementById('contentID').style.display='';
					}
						if(actionName=='Event')
					{
						document.getElementById('eventList').style.display='';
						document.getElementById('imageclassId').style.display='none';
						document.getElementById('abouteventId').style.display='none';
						if(document.getElementById('videoDataFlag').value=='true')
						{
							 document.getElementById('videoId').style.display='none';
						 }
						 if(document.getElementById('contentDataFlag').value=='true')
						 {
							document.getElementById('contentDataId').style.display='none'; 
							document.getElementById('contentDataId1').style.display='none';
						}
						document.getElementById('eventNameId').style.display='none';
						document.getElementById('lineId').style.display='none';
					 document.getElementById('lineId1').style.display='none';
					 document.getElementById('contentDataFlag').value='false';
					}
						
					}catch(e)
					{
							//alert(e);
					}
										
					
					
				}
				</script>

<script type="text/javascript">

	onload();
	function onload()
	{
	 	try{
	
		var id = document.getElementById('checkSelectTab').value;
		
		if(document.getElementById('yearFlag').value=='true')
		{
			document.getElementById('selectId'+id).className='li';
		document.getElementById('selectId'+id).className='current';
		
		}
	 
		if(document.getElementById('imageDataFlag').value=='true')
		{
		document.getElementById('imageclassId').className='portalOnLink';
		}
		
		if(document.getElementById('eventDataFlag').value=='true')
		{
			document.getElementById('abouteventId').className='contlink';
		}
		if(document.getElementById('videoDataFlag').value=='true')
		{
		document.getElementById('videoId').className='contlink';
		}
		if(document.getElementById('contentDataFlag').value=='true')
		{
		document.getElementById('contentDataId').className='contlink';
		}
	 
	 	
		}catch(e){ //alert(e);
		}
		
		
	}
	
	
		function callCurrentType(id)
	{
		try{
	  
	  
	 	 	if(id=='imageclassId')
	 	 	{
	 	 		document.getElementById('imageclassId').className='portalOnLink';
	 	 		document.getElementById('abouteventId').className='contlink';
	 	 			if(document.getElementById('videoDataFlag').value=='true')
	 	 			{
	 	 			document.getElementById('videoId').className='contlink';
	 	 			}
	 	 			
	 	 			if(document.getElementById('contentDataFlag').value=='true')
	 	 			{
	 	 			document.getElementById('contentDataId').className='contlink';
	 	 			}
	 	 		
	 	 		
	 	 	}
	 	 	 if(id=='abouteventId')
	 	 	{
	 	 		document.getElementById('abouteventId').className='portalOnLink';
	 	 		document.getElementById('imageclassId').className='contlink';
	 	 		
	 	 		if(document.getElementById('videoDataFlag').value=='true')
	 	 			{
	 	 			document.getElementById('videoId').className='contlink';
	 	 			}
	 	 			
	 	 			if(document.getElementById('contentDataFlag').value=='true')
	 	 			{
	 	 			document.getElementById('contentDataId').className='contlink';
	 	 			}
	 	 	}
	 	 	if(id=='videoId')
	 	 	{
	 	 		document.getElementById('videoId').className='portalOnLink';
	 	 		document.getElementById('imageclassId').className='contlink';
	 	 		document.getElementById('abouteventId').className='contlink';
	 	 		document.getElementById('contentDataId').className='contlink';
	 	 	}
	 	 	if(id=='contentDataId')
	 	 	{
	 	 	 	document.getElementById('contentDataFlag').value='true';
	 	 		document.getElementById('contentDataId').className='portalOnLink';	
	 	 		 
	 	 		document.getElementById('imageclassId').className='contlink';
	 	 		document.getElementById('abouteventId').className='contlink';
	 	 		if(document.getElementById('videoDataFlag').value=='true')
	 	 		{
	 	 			document.getElementById('videoId').className='contlink';
	 	 		}
	 	 			
	 	 	}
	 		 
	   
	 	}catch(e){
	 	 //alert(e);
	 	 
	 	}
	}
	

	function callCurrent(id,num) {
	 	try{
	  
	 	for(var i=1; i<num; i++) {
	 	
	 		if(document.getElementById('checkSelectTab').value==i)
	 		{
	 		
			document.getElementById('selectId'+i).className='li';
			document.getElementById('selectId'+id).className='current';
			 
			}
		 
		}
		
		document.getElementById('checkSelectTab').value=id;
	  
	 	}catch(e){
	 		//alert(e);
	 	}
		
	}

</script>	