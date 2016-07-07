<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form id="paraFrm" action="WeekPlanner" validate="true" theme="simple">

	<table class="bodyTable" width="100%" >
      <tr valign="top">
  			<td width="100%" colspan="6" class="pageHeader" align="center" valign="top">Add Task</td>
  	  </tr>
  	   <tr>
	    <td width="100%" colspan="6">&nbsp;</td>
	  </tr>
	  
	  
	   <tr>
	    <td width="100%" colspan="6">&nbsp;</td>
	  </tr>
	  </table>
	  
	  <table width="100%" class="bodyTable">
	  <tr>
	  		<td>Subject:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	  		<td>
	  		<s:textarea name="plannerBean.subject" value="%{plannerBean.subject}" rows="3" cols="95"/>
	  		</td>
	  </tr>
	  
	  <tr>
	    <td width="100%" colspan="6">&nbsp;</td>
	  </tr>
	  </table>
	 
	 <table width="100%" class="bodyTable">
		<tr>
			<td>Start Date:</td>
			<td><s:textfield name="plannerBean.newStartDate" value="%{plannerBean.newtaskDate}"/>
			<s:a
				href="javascript:NewCal('plannerBean.newStartDate','DDMMYYYY');">
				<img src="../pages/images/cal.gif" class="iconImage"  height="16" align="absmiddle" width="16">
			</s:a>
			</td>
			
			<td>Start Time:</td>
			<td>
			
		<select name="startTimeHr">
			<%for(int i=0;i<24;i++){%>
             <option value="<%=i %>"><%=i %></option>
             
             <%} %>
        </select>:
			<select name="StartTimeMin">
			<%for(int i=0;i<6;i++){ 
			for(int j=0;j<=9;j++){%>
             <option value="<%=i %><%=j %>"><%=i %><%=j %></option>
             
             <%}} %>
                            </select>
			</td>
		</tr>
		<tr>
			<td>End Date:</td>
			<td><s:textfield name="plannerBean.newEndDate" value="%{plannerBean.newtaskDate}"/>
			<s:a
				href="javascript:NewCal('plannerBean.newEndDate','DDMMYYYY');">
				<img src="../pages/images/cal.gif" class="iconImage"  height="16" align="absmiddle" width="16">
			</s:a>
			</td>
			
			<td>End Time:</td>
		<td>
			
		<select name="endTimeHr" class="selectTime">
			<%for(int i=0;i<24;i++){%>
             <option value="<%=i %>"><%=i %></option>
             
             <%} %>
        </select>:
			<select name="endTimeMin"   class="selectTime">
			<%for(int i=0;i<6;i++){ 
			for(int j=0;j<=9;j++){%>
             <option value="<%=i %><%=j %>"><%=i %><%=j %></option>
             
             <%}} %>
                            </select>
			</td>
		</tr>
		
		
		
		
		<tr>
		<td><br></td>
		</tr>
		
		<tr>
		<td width="100%" align="center" colspan="6">
		<s:submit cssClass="pagebutton"  value=" Add Task " action="WeekPlanner_addTask"/>
		<input type="button" value="Close" class="pagebutton" onclick="callClose();">
		</td>
		</tr>
		
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

 <SCRIPT type="text/javascript"
			src="../pages/common/include/javascript/dhtmlgoodies_calendar1.js"></script>
  <SCRIPT>
  function callClose(){
  window.close();
  }
  </SCRIPT>
  
  