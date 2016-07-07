<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var mainMenu=0;
</script>
<s:form action="UserDashlet" method="post"
	name="UserDashletConfiguration" validate="true" id="paraFrm"
	theme="simple">
	<!-- Flags used For Cancel Button -->
	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />
	<s:hidden name="saveChkFlag" id="saveChkFlag" /> 
	<input type="hidden" name="hideMenuId" id="hideMenuId" />
	<input type="hidden" name="hideMenuName" id="hideMenuName" />
	<div align="center" id="overlay"
		style="z-index: 3; position: absolute; width: 790px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1; display: none;">
	</div>

	<div id="optionDiv"
		style='position:absolute; z-index:3;
		 width:450px; height:60px; display:none;border:1px solid;
		top: 150px; left: 200px;padding: 10px' class="formbg">
	<table align="center">
		<tr align="center">
			<td colspan="4"><b>Please select anyone of the option given below</b></td>
		</tr>
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr align="center">
			<td></td>
			<td width="100%"><input type="button" value=" Proceed with Save"  name="divBtns"
				onclick="someFun('0','0','0','1');" class="token" style="cursor: pointer;"/>&nbsp;
				<input type="button" value=" Proceed without Save"  name="divBtns" onclick="someFun('0','0','0','2')"
				class="token" style="cursor: pointer;"/>&nbsp;
				<input type="button"  name="divBtns"
				value=" Cancel" onclick="someFun('0','0','0','0');" class="cancel" style="cursor: pointer;"/>
				</td>
		</tr>
	</table>
	</div>
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="4" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">User
					Dashlet Configuration </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2">
				<tr>
					<s:hidden name="hMenuCode" />
					<td width="80%">
					<div id="tabnav" style="vertical-align: bottom">
					<ul>
						<%
								try {
								String[][] menuNames = (String[][]) request
								.getAttribute("menuNames");
								String[][] dashsetObj = (String[][]) request
								.getAttribute("dashletSet");
						%>
						<%!String width1 = "";
	String width2 = "";%>
						<%
									if (dashsetObj != null && dashsetObj.length > 0)
									if (dashsetObj[0][3].indexOf(",") > -1) {
								width1 = dashsetObj[0][3].split(",")[0];
								try {
									width2 = dashsetObj[0][3].split(",")[1];
								} catch (Exception e) {
									width2 = "";
								}
									} else
								width1 = dashsetObj[0][3];
								if (menuNames != null && menuNames.length > 0) {
									for (int i = 0; i < menuNames.length; i++) {
								if (i == 0 || i == 10 || i == 24) {
						%>
						<%
						}
						%>

						<li><a href="#"
							onclick="someFun('<%=menuNames[i][0]%>','<%=menuNames[i][1]%>','1','0');"
							class="li" id='<%="menuId_"+i%>'> <span><%=menuNames[i][1]%>&nbsp;&nbsp;</span>
						</a></li>
						<script>
								if(document.getElementById('paraFrm_hMenuCode').value == '<%=menuNames[i][0]%>')
									document.getElementById('<%="menuId_"+i%>').className = "on";
						</script>
						<%
								}
								}
						%>
						<%
								} catch (Exception e) {
								e.printStackTrace();
							}
						%>
					</ul>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="divFlag" />
		<s:if test="divFlag">
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<s:hidden name="menuHome" />
						<td width="100%" nowrap="nowrap">Dashlet Settings For : <strong><s:property
							value="menuHome" /></strong></td>
					</tr>
					</tr>
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="2">
							<tr>
								<td width="15%" class="formtext"><label name="grid.rows"
									id="grid.rows" ondblclick="callShowDiv(this);"><%=label.get("grid.rows")%></label><font
									color="red">*</font> :</td>
								<td width="15%"><s:select name="gridRows"
									list="#{'':'--Select--','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8'}"
									onchange="callGo();" /></td>
								<td width="15%" class="formtext"><label name="grid.columns"
									id="grid.columns" ondblclick="callShowDiv(this);"><%=label.get("grid.columns")%></label><font
									color="red">*</font> :</td>
								<td width="15%"><s:select name="gridCols"
									list="#{'':'--Select--','1':'1','2':'2'}" onchange="callGo();" /></td>
							</tr>
							<tr>
								<td colspan="2"><s:hidden name="colShowFlag"
									id="colShowFlag"></s:hidden>
								<div id="col1Div" style="display: none">
								<table>
									<tr>
										<td width="15%" colspan="1"><label name="width.first"
											id="width.first" ondblclick="callShowDiv(this);"><%=label.get("width.first")%></label><font
											color="red">*</font> :</td>
										<td width="15%" colspan="1">
										<%
										if (!width1.equals("")) {
										%> <s:textfield name="widthCol1" size="12" maxlength="2"
											value="<%=width1 %>" onkeypress="return numbersOnly();"
											onkeyup="setColumnWidth();" /> <%
 } else {
 %> <s:textfield name="widthCol1" size="12" maxlength="2"
											onkeypress="return numbersOnly();"
											onkeyup="setColumnWidth();" /> <%
 }
 %>
										</td>
									</tr>
								</table>
								</div>
								</td>
								<td colspan="2">
								<div id="col2Div" style="display: none;">
								<table>
									<tr>
										<td width="15%" colspan="1"><label name="width.second"
											id="width.second" ondblclick="callShowDiv(this);"><%=label.get("width.second")%></label><font
											color="red">*</font> :</td>
										<td width="15%" colspan="1">
										<%
										if (!width2.equals("")) {
										%> <s:textfield name="widthCol2" size="12" maxlength="2"
											value="<%=width2 %>" readonly="true"/> <%
 } else {
 %> <s:textfield name="widthCol2" size="12" maxlength="2" readonly="true"/>
  <%
 }
 %> <%
 width1 = width2 = "";
 %>
										</td>

									</tr>
								</table>
								</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:if test="divFlag">
			<tr>
				<td>
				<table width="100%" cellspacing="2" cellpadding="2" class="formbg">
					<%
							try {
							String[][] matrix = (String[][]) request.getAttribute("matrix");
							String[][] dashletObj = (String[][]) request
							.getAttribute("dashletObj");
							System.out.println("LENGTH =======" + matrix.length);
					%>
					</td>
					</tr>

					<tr>
						<td align="center" class="formth" width="15%"><label
							name="row" id="row" ondblclick="callShowDiv(this);"><%=label.get("row")%></label></td>
						<td align="center" class="formth" width="15%"><label
							name="column" id="column" ondblclick="callShowDiv(this);"><%=label.get("column")%></label></td>
						<td align="center" class="formth" width="70%"><label
							name="dashlet" id="dashlet" ondblclick="callShowDiv(this);"><%=label.get("dashlet")%></label></td>
					</tr>
					<input type="hidden" name="hiddenDashId" id="hiddenDashId" />
					<%!int c = 0, d = 0;%>
					<%
								if (matrix != null && matrix.length > 0) {

								for (int i = 0; i < matrix.length; i++) {
							System.out.println("rowCounter =======" + matrix[i][0]);
							System.out.println("colCounter =======" + matrix[i][1]);
					%>

					<tr>
						<input type="hidden" name="row" id="row" <%=i%>
							value="<%=matrix[i][0] %>" />
						<input type="hidden" name="col" id="col" <%=i%>
							value="<%=matrix[i][1] %>" />
						<td class="td_bottom_border" width="15%" align="center"><s:label
							value="<%= String.valueOf(matrix[i][0]) %>" /></td>

						<td class="td_bottom_border" width="15%" align="center"><s:label
							value="<%= String.valueOf(matrix[i][1]) %>" /></td>

						<td class="td_bottom_border" width="70%">
						<%
								boolean flag = false;
								if (dashletObj != null && dashletObj.length > 0) {
									if (i < dashletObj.length) {
										for (int j = 0; j < dashletObj.length; j++) {
									if (matrix[i][0].equals(dashletObj[j][3])
											&& matrix[i][1]
											.equals(dashletObj[j][4])) {
						%><input type="text" id="dashName<%=i %>" name="dashName"
							size="40" readonly="readonly" value="<%=dashletObj[j][1] %>" />
						<input type="text" name="dashCode" id="dashCode<%=i %>"
							value="<%=dashletObj[j][0] %>" /> <img
							src="../pages/common/css/default/images/search2.gif" width="16"
							height="15" onclick="javascript:openDashList('<%=i %>');">
						&nbsp;&nbsp;<input type="button" class="shuffleUp"
							 onclick="moveUp('<%=i %>');" /> &nbsp;&nbsp;<input
							type="button" class="shuffleDown" 
							onclick="moveDown('<%=i %>');" /> &nbsp;&nbsp;<input
							type="button" value="Clear" class="token"
							onclick="resetField('<%=i %>');" /> <%
 				flag = true;
 				break;
 			}
 				}
 			} else {
 				flag = true;
 %> <input type="text" id="dashName<%=i %>" name="dashName" size="40"
							readonly="readonly" /> <input type="text" name="dashCode"
							id="dashCode<%=i %>" /> <img
							src="../pages/common/css/default/images/search2.gif" width="16"
							height="15" onclick="javascript:openDashList('<%=i %>');">
						&nbsp;&nbsp;<input type="button" class="shuffleUp"
							 onclick="moveUp('<%=i %>');" /> &nbsp;&nbsp;<input
							type="button" class="shuffleDown" 
							onclick="moveDown('<%=i %>');" /> &nbsp;&nbsp;<input
							type="button" value="Clear" class="token"
							onclick="resetField('<%=i %>');" /> <%
 		}
 		} else {
 			flag = true;
 %> <input type="text" id="dashName<%=i %>" name="dashName" size="40"
							readonly="readonly" /> <input type="text" name="dashCode"
							id="dashCode<%=i %>" /> <img
							src="../pages/common/css/default/images/search2.gif" width="16"
							height="15" onclick="javascript:openDashList('<%=i %>');">
						&nbsp;&nbsp;<input type="button" class="shuffleUp"
							 onclick="moveUp('<%=i %>');" /> &nbsp;&nbsp;<input
							type="button" class="shuffleDown" 
							onclick="moveDown('<%=i %>');" /> &nbsp;&nbsp;<input
							type="button" value="Clear" class="token"
							onclick="resetField('<%=i %>');" /> <%
 		}
 		if (!flag) {
 %> <input type="text" id="dashName<%=i %>" name="dashName" size="40"
							readonly="readonly" /> <input type="text" name="dashCode"
							id="dashCode<%=i %>" /> <img
							src="../pages/common/css/default/images/search2.gif" width="16"
							height="15" onclick="javascript:openDashList('<%=i %>');">
						&nbsp;&nbsp;<input type="button" class="shuffleUp"
							 onclick="moveUp('<%=i %>');" /> &nbsp;&nbsp;<input
							type="button" class="shuffleDown" 
							onclick="moveDown('<%=i %>');" /> &nbsp;&nbsp;<input
							type="button" value="Clear" class="token"
							onclick="resetField('<%=i %>');" /> <%
 }
 %>
						</td>
					</tr>
					<%
								c++;
								}
							} else {
					%>
					<tr align="center">
						<td colspan="3" class="text_head"><font color="red">No
						Dashlets defined</font></td>
					</tr>

					<%
							}
							d = c;
							c = 0;
					%>

				</table>

				<%
						} catch (Exception e) {
						e.printStackTrace();
					}
				%>
				</td>
			</tr>
		</s:if>
	</table>
</s:form>


<script>
divLoad();
function someFun(id,name,type,retVal)
{
	if(type==1)
	{
		if(name==document.getElementById('paraFrm_menuHome').value)
			return false;
		document.getElementById('hideMenuId').value=id;
		document.getElementById('hideMenuName').value=name;
		document.getElementById('overlay').style.display='';
		document.getElementById('optionDiv').style.display='block';
		return true;
	}
	else
	{
		document.getElementById('overlay').style.display='none';
		document.getElementById('optionDiv').style.display='none';
		if(retVal==0)
		  	return false;
		if(retVal==1)
		{
			document.getElementById('saveChkFlag').value="1";
			if(!checkSequence() || !validateWidth())
			{
				document.getElementById('saveChkFlag').value="";
				return false;
			}
		}
		mainMenu=document.getElementById('hideMenuId').value;
		name=document.getElementById('hideMenuName').value;
		document.getElementById('hideMenuId').value='';
		document.getElementById('hideMenuName').value='';
		document.getElementById('paraFrm').action="UserDashlet_getPages.action?menuId="+mainMenu+'&menuHome='+name;
		document.getElementById('paraFrm').submit();
	}
}
function divLoad()
{
	for(i=1; i <= 2; i++)
		document.getElementById("col"+i+"Div").style.display = 'none';
	if(document.getElementById('colShowFlag').value == "true")
	for(i=1; i <= document.getElementById('paraFrm_gridCols').value; i++){
		document.getElementById("col"+i+"Div").style.display = '';
	}
	if(document.getElementById('paraFrm_gridCols').value==2)
	{
		document.getElementById('paraFrm_widthCol1').readOnly=false;
	}
	else
	{
		document.getElementById('paraFrm_widthCol1').readOnly=true;
		var rowCount = <%=d%>;
		if(rowCount == 0)
		{
			document.getElementById('paraFrm_widthCol1').value="";
			document.getElementById('paraFrm_widthCol1').readOnly=true;
		}
		else
			document.getElementById('paraFrm_widthCol1').value=100;
	}
	document.getElementById('paraFrm').target='main';
}

function callGo(){
	if(document.getElementById('paraFrm_gridRows').value=="" || document.getElementById('paraFrm_gridCols').value=="")
		return false;
	document.getElementById('paraFrm_widthCol1').value="";
	document.getElementById('paraFrm_widthCol2').value="";
	document.getElementById('paraFrm').action="UserDashlet_matrixOnGo.action";
	document.getElementById('paraFrm').submit();
}

function openDashList(id){
	document.getElementById("hiddenDashId").value = id;
	window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="new";
	document.getElementById("paraFrm").action='UserDashlet_dashletList.action';
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
}

function saveFun()
{ 
	try {
	if(!validateWidth(1))
		return false;
	if(!checkSequence())
		return false;
	document.getElementById('paraFrm').action="UserDashlet_save.action";
	document.getElementById('paraFrm').submit();
	return true;
	}catch(e)
	{
		alert(e);
	}
}

function validateWidth(type)
{
	var rowCount = <%=d%>;
	if(rowCount == 0)
		return true;
	if(type == 1) 
	{
		flag=false;
		for(i=0;i<rowCount;i++)
		{
			if(document.getElementById('dashName'+i).value!="")
			{
				flag=true;
				break;
			}
		}
		if(!flag)
		{
			document.getElementById('paraFrm_widthCol1').value = "";
			if(document.getElementById('col2Div').style.display == "")
			{
				document.getElementById('paraFrm_widthCol2').value=="";
			}
			return true;
		}
	}
	if(document.getElementById('paraFrm_gridCols').value == 1)
	{
		document.getElementById("paraFrm_widthCol1").value = 100;
	}
	else if(document.getElementById('paraFrm_widthCol1').value == "")
	{
		alert("Please enter "+document.getElementById('width.first').innerHTML.toLowerCase());
		document.getElementById('paraFrm_widthCol1').focus();
		return false;
	}
	if(document.getElementById('paraFrm_gridCols').value == 2)
	{
		if(document.getElementById("paraFrm_widthCol1").value > 75 || document.getElementById("paraFrm_widthCol1").value < 25)
		{
			alert(document.getElementById('width.first').innerHTML.toLowerCase()+" should be between 25 and 75");
			document.getElementById('paraFrm_widthCol1').focus();
			return false;
		}
	}
	else
	{
		document.getElementById("paraFrm_widthCol1").value=100;
	}
	
	return true;
}

function checkSequence()
{
	try
	{
		var rowCount = <%=d%>;
		for(i=0;i<rowCount;i++)
		{
			if(document.getElementById('dashName'+i).value=="")
			{
				for(j=i+1;j<rowCount;j++)
				{
					if(document.getElementById('dashName'+j).value!="")
					{
						alert("Please assign the "+document.getElementById('dashlet').innerHTML.toLowerCase()+" sequentially");
						return false;
					}
				}
			}
		}
		return true;
	}catch(e)
	{
		alert(e);
		return false;
	}
}

function resetField(id)
{
	document.getElementById('dashName'+id).value="";
	document.getElementById('dashCode'+id).value="";
}

function moveUp(id)
{
	try {
	if(id==0)
		return false;
	var tempName = document.getElementById('dashName'+id).value;
	var tempCode = document.getElementById('dashCode'+id).value;
	document.getElementById('dashName'+id).value = document.getElementById('dashName'+(eval(id)-1)).value;
	document.getElementById('dashCode'+id).value = document.getElementById('dashCode'+(eval(id)-1)).value;
	document.getElementById('dashName'+(eval(id)-1)).value = tempName;
	document.getElementById('dashCode'+(eval(id)-1)).value = tempCode;
	}catch(e)
	{
	}
}
function moveDown(id)
{
	try
	 {
		if(id=='<%=d-1%>')
			return false;
		var tempName = document.getElementById('dashName'+(eval(id)+1)).value;
		var tempCode = document.getElementById('dashCode'+(eval(id)+1)).value;
		document.getElementById('dashName'+(eval(id)+1)).value = document.getElementById('dashName'+id).value;
		document.getElementById('dashCode'+(eval(id)+1)).value = document.getElementById('dashCode'+id).value;
		document.getElementById('dashName'+id).value = tempName;
		document.getElementById('dashCode'+id).value = tempCode;
	}catch(e)
	{
	}
}

function setColumnWidth()
{
	try {
			if(document.getElementById('paraFrm_gridCols').value == 1)
			{
				document.getElementById("paraFrm_widthCol1").value=100;
				document.getElementById("paraFrm_widthCol1").readOnly=true;
			}
			else
			{
				if(document.getElementById("paraFrm_widthCol1").value.length <= 1)
					return false;
				if(document.getElementById("paraFrm_widthCol1").value.length > 2)
				{
					document.getElementById("paraFrm_widthCol1").value=document.getElementById("paraFrm_widthCol1").value.substr(0,document.getElementById("paraFrm_widthCol1").value.length-1);
					return false;
				}
				if(document.getElementById("paraFrm_widthCol1").value.length == 2)
				{
					if(document.getElementById("paraFrm_widthCol1").value > 75 || document.getElementById("paraFrm_widthCol1").value < 25)
					{
						alert(document.getElementById('width.first').innerHTML.toLowerCase()+" should be between 25 and 75");
						document.getElementById("paraFrm_widthCol1").value = "";
						document.getElementById("paraFrm_widthCol2").value = "";
						return false;
					}
					else
					{
						document.getElementById("paraFrm_widthCol2").value=100-document.getElementById("paraFrm_widthCol1").value;
					}
				}
			}
		}catch(e)
		{
			alert(e);
		}
}

function showWidthCol()
{
	if(document.getElementById('paraFrm_gridCols').value==2)
	{
		document.getElementById("col2Div").style.display = "";
		document.getElementById("paraFrm_widthCol1").value="";
		document.getElementById("paraFrm_widthCol2").value="";
		document.getElementById("paraFrm_widthCol1").readOnly=false;
	}
	else
	{
		document.getElementById("col2Div").style.display = "none";
		document.getElementById("paraFrm_widthCol1").value=100;
		document.getElementById("paraFrm_widthCol1").readOnly=true;
	}
}
</script>