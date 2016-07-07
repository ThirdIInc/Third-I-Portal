<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.HashMap"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<!-- JQueryUI v1.9.2 -->
  <link rel="stylesheet" href="../css/jquery-ui-1.9.2.custom.min.css" />

<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/jquery.toggle.buttons.js"></script>
<script type="text/javascript" src="js/jquery.noty.packaged.min.js"></script>

<!-- Bootstrap CSS -->
  <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <link href="../css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
  
<!-- Glyphicons -->
  <link rel="stylesheet" href="../css/glyphicons.css" />
 
<!-- Theme -->
  <link rel="stylesheet" href="../css/style.min.css" type="text/css"/>
  <link rel="stylesheet" href="../css/jquery.dataTables.min.css" type="text/css"/>
  <link rel="stylesheet" href="../css/dataTables.bootstrap.css" type="text/css"/>

<!-- select2 -->
  <link rel="stylesheet" href="../css/select2.css" type="text/css"/>
  <link rel="stylesheet" href="../css/bootstrap-select.css" type="text/css">


<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-select.js"></script>

<s:form action="DepartmentRatingAction" validate="true" id="paraFrm" theme="simple">
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
	    <!-- HEADER OF THE CONTENT HERE-->

	  
<div class="container">

			<div class="row" style="border-bottom: 3px solid #dedede;margin-bottom: 7px;">
				<div class = "col-md-2">
					<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" style="margin-top:2px;" /><font style="margin-top:8px;font-size:13px;font-weight: bold;">Department Rating</font>
				</div>
			</div>
	
			<div class = "row">
				<div class = "col-md-2" >
				<button type="button" class="btn btn-default btn-xs" name="calDeptRating" value="Calculate Dept Rating" onclick="caliculateDeptRating()">Calculate Department Rating</button>
				</div>
	
				<button type="button" class="btn btn-default btn-xs" type="submit" class="save" name="save" value="Save" onclick="saveDeptScoreData()">Save</button>
	
			</div>
	
			<div class = "row" style="border-bottom: 3px solid #dedede;margin-bottom: 4px;padding: 4px;">
				<div class = "col-md-6">
			
					<form class="from-search form-inline" role="form" style = "margin-top:4px;">
				
						<div class="form-group has-feedback has-feedback-left">
					
							<label class="control-label">Appraisal Code<font color="red">*</font> : </label> 
					 			<s:textfield name="apprCode" size="20" maxlength="25" readonly="true" />
					 			<s:hidden name="apprId" />
					  			<s:hidden name="templateCode"/>
						 	<!--  <input type="text" class="form input-sm" id="app" name="apprCode" placeholder="Search">-->
						 		
								<button class="btn btn-primary btn-xs" class="search" name="Search" value="Search" onclick=" return callCalendar();"><i class="glyphicon glyphicon-search"></i></button>
				
						</div>	
				
					</form>
			
				</div>	
			
			</div>
					
</div>

	

	     <!-- Display Appraisal Department Score List -->
	     <tr>
	     <td>
	     	<table width="100%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td width="35%" class="formth"><label  class = "set"  id="dept1Name" name="dept1Name" ondblclick="callShowDiv(this);"><%=label.get("dept1Name")%></label></td>
					<td width="35%" class="formth"><label  class = "set"  id="dept1Score" name="dept1Score" ondblclick="callShowDiv(this);"><%=label.get("dept1Score")%></label></td>
					<td width="30%" class="formth"><label  class = "set"  id="modDept1Score" name="modDept1Score" ondblclick="callShowDiv(this);"><%=label.get("modDept1Score")%></label></td>
				</tr> 	
				
				<tr>
				<td>
				<%!int p = 0, t = 0, i=0;%>
					<s:iterator value="deptScoreList">
						<tr>
							<td  width="35%" class="sortableTD"  align="left">
								<s:property	value="deptName"/>		
								<s:hidden name="deptName"></s:hidden> 
							 	<s:hidden name="deptId"></s:hidden>	   
							</td>
							<td width="35%" class="sortableTD" align="right">
								<s:property value="deptScore" />
								<s:hidden name="deptScore"></s:hidden>
							</td>
							<td width="35%" class="sortableTD"  align="left">
								<input type="text" style="text-align:right" size="15" onkeyup="sumRow()" name="modDeptScore"  id="<%="modDeptScore"+i%>"
									   value="<s:property	value="modDeptScore" />" onkeypress="return numbersWithDot();" />
								
							</td>	
						</tr>
						<%
						 p++; i++;
						%>
						
					</s:iterator>
					 <%
				if (i == 0) {
			%>
			<tr align="center">
				<td colspan="4" class="sortableTD" width="100%"><font
						color="red">No Data to display</font></td>
			</tr>
			<%
				}
			%>
			<tr class="formbg">
				<td></td>
				<td width="35%" align="right"><label  class = "set"  id="organizedScore" name="organizedScore" ondblclick="callShowDiv(this);"><%=label.get("organizedScore")%></label></td>
				<td colspan="2">
				    <input type="text"  size="15"  value="<s:property value='organizedScore' />" 
					       name="organizedScore"  id="organizedScore" readonly="true"/> 
				 </td>
			</tr>	
			<%
				t = p; p = 0;
			%>	
			</table>	
			
	     </td>
	     </tr>
</table>
</s:form>
<script type="text/javascript">
	function callCalendar(){
			javascript:callsF9(500,325,'DepartmentRating_f9AppCal.action'); 	
	}
	function saveDeptScoreData(){
	
		var appId=document.getElementsByName("apprCode")[0].value;
		
	
		if(appId==""){
			alert("Please Select Appraisal code");
		}else{
			document.getElementById('paraFrm').action ="DepartmentRating_saveDeptScoreData.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';	
		}	 
		
	}
	function caliculateDeptRating(){
		var appId=document.getElementsByName("apprCode")[0].value;
		if(appId==""){
			alert("Please Select Appraisal code");
		}else{
			document.getElementById('paraFrm').action ="DepartmentRating_caliculateDeptRating.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main'; 	
		}	 	
	}
	function sumRow() 
 	{
		var totalrow =<%=t%>;
		var count=0;
		for(var row = 0;row < eval(totalrow) ;row++)
		{
			// alert(document.getElementById('modDeptScore'+row).value);
			var values=document.getElementById('modDeptScore'+row).value;
			if(values ==""){
				values =0;
			}
			values =eval(values*100/100);
			
			count=eval(count)+eval(values);
			// alert("--"+count);
		}
		document.getElementById("organizedScore").value=count;
 }
</script>
