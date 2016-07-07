<%--Bhushan--%><%--May 7, 2008--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
 <%@include file="/pages/common/labelManagement.jsp" %>
 
<script type="text/javascript">
	var hDay = new Array();
</script>

<s:form action="BranchHolidayMaster" name="BranchHolidayMaster" id="paraFrm" validate="true" theme="simple">
	<s:hidden name="listFlag" />
<table width="100%" class="formbg"> <!--  table1 -->
	<tr>
		<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt">
						<strong class="text_head">
							<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
						</strong>
					</td>
					<td width="93%" class="txt"><strong class="text_head">Branchwise Holiday</strong></td>
					<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<s:submit action="BranchHolidayMaster_save" cssClass="edit" value="   Update" onclick="return callSave();" />
						<s:submit action="BranchHolidayMaster_reset" cssClass="reset" theme="simple" value="    Reset" />
					</td>
					<td width="22%">
						<div align="right"><font color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td>
			<table width="100%" class="formbg">
				<tr>
					<td>
						<label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font color="red">*</font>: <s:hidden name="brnCode" />
						<s:textfield name="brnName" size="40" readonly="true"/>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
						onclick="callsF9(500,325,'BranchHolidayMaster_f9Branch.action');">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label  class = "set" name="year" id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:
						<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" 
						onkeypress="return numbersOnly();" onblur="return checkYear('paraFrm_year', 'Year');" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:submit action="BranchHolidayMaster_showHolidays" cssClass="search" value="    Show " 
						onclick="return callSearch();"/>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td>
			<s:if test="listFlag">
				<table width="100%" class="formbg">
					<tr>
						<td colspan="1" width="100%">
							<div>
								<table width="100%">
									<tr>
										<td class="formth" align="center" width="175"><label  class = "set" name="holi.date" id="holi.date" ondblclick="callShowDiv(this);"><%=label.get("holi.date")%></label></td>
										<td class="formth" align="center" width="480"><label  class = "set" name="occasion" id="occasion" ondblclick="callShowDiv(this);"><%=label.get("occasion")%></label></td>
										<td class="formth" align="center"><label  class = "set" name="applicable" id="applicable" ondblclick="callShowDiv(this);"><%=label.get("applicable")%></label></td>
									</tr>
									<tr>
										<td>&nbsp;</td><td>&nbsp;</td>
										<td align="center">
											<s:if test="allHolidays">
												<a id="allSel" href="#" onclick="doAll('select');" style="display: none;">Select All</a>
												<a id="allClr" href="#" onclick="doAll('clear');">Clear All</a>
											</s:if>
											<s:else>
												<a id="allSel" href="#" onclick="doAll('select');">Select All</a>
												<a id="allClr" href="#" onclick="doAll('clear');" style="display: none;">Clear All</a>
											</s:else>
										</td>
									</tr>
								</table>
							</div>				 
						</td>
					</tr>			
					<tr>
						<td width="100%" colspan="1">
							<div style="overflow: auto; height: 250;">
								<table width="100%">
									<%!int cnt = 0;%>
									<s:iterator value="holidayList">
										<tr>
											<td align="center" class="borderAttdn">
												<s:hidden name="hDayDate" id='<%="hDayDate"+cnt%>' /><s:property value="hDayDate" />
												<script>
													hDay[<%=cnt%>] = document.getElementById('hDayDate'+<%=cnt%>).value;
												</script>
											</td>
											<td class="borderAttdn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<s:hidden name="occasion" /><s:property value="occasion" />
											</td>
											<td align="center" class="borderAttdn">
												<s:if test="applHDay">
													<input type="checkbox" name="chkHDay" id="chkHDay<%=cnt%>" checked="checked" onclick="checkRecord(<%=cnt%>);" />
												</s:if>
												<s:else>
													<input type="checkbox" name="chkHDay" id="chkHDay<%=cnt%>" onclick="checkRecord(<%=cnt%>);"/>
												</s:else>
												<s:hidden name="applHDay" id='<%="applHDay"+cnt%>' />
											</td>
										</tr>
										<%++cnt;%>
									</s:iterator>
									<%cnt = 0;%>
								</table>
							</div>	
						</td>		
					</tr>
				</table>
			</s:if>
		</td>
	</tr>	
</table><!-- table1 -->
</s:form>

<script type = "text/javascript" >
	var cntExist = 0;
	
	function checkRecord(id)
	{
		try
		{
			var cnt = 0;
			for(var i = 0; i < hDay.length; i++)
			{
				if(document.getElementById('chkHDay'+i).checked)
				{
					document.getElementById('applHDay'+i).value = true;
					cnt = cnt + 1;
				}
				else
				{
					document.getElementById('applHDay'+i).value = false;
				}				
			}

			if(cnt == hDay.length)
			{
				document.getElementById('allSel').style.display = 'none';
				document.getElementById('allClr').style.display = 'inline';
			}
			else if(cnt == 0)
			{
				document.getElementById('allSel').style.display = 'inline';
				document.getElementById('allClr').style.display = 'none';
			}
		}
		catch(e)
		{
			alert(e.description);
		}
	}
	
	function callSearch()
	{
		if(document.getElementById('paraFrm_brnCode').value == "")
		{
			alert('Please select '+document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "")
		{
			alert('Please enter '+document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		return true;
	}
	
	function callSave()
	{
		if(document.getElementById('paraFrm_brnCode').value == "")
		{
			alert('Please select '+document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "")
		{
			alert('Please enter '+document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(hDay.length < 1)
		{
			var listFlag = document.getElementById('paraFrm_listFlag').value;
			if(listFlag == 'false')
			{
				alert('Please click on Show button!');
			}
			else
			{
				alert('Holidays not available!');
			}
			return false;
		}
		return true;
	}
	
	function doAll(type)
	{
		cntExist = 0;
			
		if(type == 'select')
		{
			for(var i = 0; i < hDay.length; i++)
			{
				document.getElementById('chkHDay'+i).checked = true;
				document.getElementById('applHDay'+i).value = true;
			}
			document.getElementById('allSel').style.display = 'none';
			document.getElementById('allClr').style.display = 'inline';
			
			cntExist = hDay.length;
		}
		else
		{
			for(var i = 0; i < hDay.length; i++)
			{
				document.getElementById('chkHDay'+i).checked = false;
				document.getElementById('applHDay'+i).value = false;
			}
			document.getElementById('allSel').style.display = 'inline';
			document.getElementById('allClr').style.display = 'none';
		}
	}
</script>