	<!--
	@author Vijay.Gaikwad
	DashBorad jsp is used for show dashBoard components
	-->
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
	
	<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
	<%
	Object menuObj[][] = null;
	String reportName="";
	String reportURL="";
	String accountID="";
	String accountName="";
	%>
	<s:form action="DashBoardAction" validate="true" id="paraFrm"name="paraFrm" validate="true" theme="simple">
	<br>
	<%
		menuObj = (Object[][]) request.getAttribute("reportPrameter");// for getting Report parameter object
		reportName=(String) request.getAttribute("reportName");
		System.out.println("reportName- "+reportName);
		reportURL=(String)request.getAttribute("ReportURL");
		accountID=(String)request.getAttribute("accountID");
		accountName=(String)request.getAttribute("accountName");
		System.out.println("acc name - "+accountName);
		if(accountID.equals("")){
			accountID=String.valueOf(menuObj[0][3]);
		}
		if(accountName.equals("")){
			accountName=String.valueOf(menuObj[0][3]);
		}
		String userType=(String)request.getAttribute("userType");
		String paraStr="";
		String paraStr1="";
		String paraCaption="";
	%>

	<%
	try{
		if(menuObj!=null){
		%>
		<table width="100%" border="0" align="center"	id="MenuParameter" name="MenuParameter"  class="formbg" >
		<s:hidden name="reportId" value="<%=(String)request.getAttribute("reportId") %>"/>
		<s:hidden name="accountID" value="<%=accountID%>"/>
		<s:hidden name="accountName" value="<%=accountName %>"/>
		<s:hidden name="dashBoardID"/>
		<s:hidden name="componentName"/>
		<s:hidden name="userType"	value="<%=(String)request.getAttribute("userType") %>"/><!-- attaching this attribute to analytics_url -->
		<s:hidden id="scrWidth" name="screenWidth"/>
			
		<tr>
		<td colspan="2" class="portalAppButtons" width="100%"><%=reportName%></td>
		</tr>
		<% if (menuObj != null) { %>
		<tr>
		<td> Please Enter The Following Details</td>
		</tr>
		<% 
		}
		%>
			<%
			
				if (menuObj != null) {
				for (int i = 0; i < menuObj.length; i++) {
					
					paraStr+=String.valueOf(menuObj[i][0])+"#";//parameter_name
					paraCaption+=String.valueOf(menuObj[i][1])+"#";//parameter caption
					paraStr1+=String.valueOf(menuObj[i][6])+"#";//is_mandatory
										//parameter_type
					if (String.valueOf(menuObj[i][2]).equalsIgnoreCase("date")) {
		%>
		<tr>
			<td colspan="1" name="parameterDate"><%=String.valueOf(menuObj[i][1])%>: <!-- parameter_caption -->
			<% 
			System.out.println("is mandatary value "+String.valueOf(menuObj[i][6]));
			if((String.valueOf(menuObj[i][6])=="true")){ %>
			<font color="red">*</font>
			<%	}	%>
			</td>
			<td nowrap="nowrap">
			<s:textfield name="<%=String.valueOf(menuObj[i][0])%>"  size="30" readonly="false" maxlength="10"  value="<%=String.valueOf(menuObj[i][3])%>" />
			<a	href="javascript:NewCal('paraFrm_<%=String.valueOf(menuObj[i][0])%>','DDMMYYYY');"><!-- parameter_name -->
     		<img class="iconImage"	src="../pages/images/recruitment/Date.gif" width="16" height="16"/>	</a>
			</td>
		</tr>
		<%
					}//if end
					else if (String.valueOf(menuObj[i][2]).trim().equalsIgnoreCase("text")) {
		%>
		<tr>
		<% 
		
		if((String.valueOf(menuObj[i][7]).equals("1"))){ %>
			<td name="parameterText">
			<input type="hidden" value=<%=String.valueOf(menuObj[i][1])%>>
			</td>
			<%}
		else{
		%>
			<td name="parameterText">		
			<%=String.valueOf(menuObj[i][1])%>
			
			<%
		}
			%>
			
			<% if((String.valueOf(menuObj[i][6]).equals("1")&&!(String.valueOf(menuObj[i][7]).equals("1")))){ %>
			<font color="red">*</font></td>
			<%	}	%>
			<%
			System.out.println("texBox Value is - "+menuObj[i][8]);
			if(String.valueOf(menuObj[i][8])!=null&&!String.valueOf(menuObj[i][8]).equals("")&&!String.valueOf(menuObj[i][8]).equals("null") && !String.valueOf(menuObj[i][7]).equals("1") ){ %>
			<td colspan="1" nowrap="nowrap"><s:textfield size="53" readonly="true" cssStyle="background-color: #F2F2F2"
				theme="simple" name="<%=String.valueOf(menuObj[i][0])%>"
				value="<%=String.valueOf(menuObj[i][3])%>" /></td>
				<%}//end of if
				else{%>
					<%if(!String.valueOf(menuObj[i][7]).equals("1")){ %>
					<td colspan="1" nowrap="nowrap"><s:textfield size="53"
						theme="simple" name="<%=String.valueOf(menuObj[i][0])%>"
						value="<%=String.valueOf(menuObj[i][3])%>" /></td>
				<% }else{%>
					<td colspan="1" nowrap="nowrap"><s:hidden 
						 name="<%=String.valueOf(menuObj[i][0])%>"
						value="<%=String.valueOf(menuObj[i][3])%>" /></td>
				<% }			%>
		</tr>
		<%
				}}//end else if
					else if (String.valueOf(menuObj[i][2]).equalsIgnoreCase(
					"number")) {
		%>
		<tr>
			<td name="parameterNumber"><%=String.valueOf(menuObj[i][1])%>:
			<% if((String.valueOf(menuObj[i][6])=="true")){ %>
			<font color="red">*</font>
			<%	}	%>
			</td>
			<td colspan="1" nowrap="nowrap"><s:textfield size="53"
				theme="simple" name="<%=String.valueOf(menuObj[i][0])%>"
				value="<%=String.valueOf(menuObj[i][3])%>"
				onkeypress="return numbersOnly();" /></td>
		</tr>
		<%
					}//else if end
					else if (String.valueOf(menuObj[i][2]).equalsIgnoreCase(
					"query")) {
		%>
		<tr>
			<td><%=String.valueOf(menuObj[i][1])%></td>
			<% if((String.valueOf(menuObj[i][6])=="true")){ %>
			<font color="red">*</font>
			<%	}	%>:
			<td colspan="1" width="85%"><select	name="<%=String.valueOf(menuObj[i][0])%>" id="paraFrm_<%=String.valueOf(menuObj[i][0])%>">
				<%
								String[] arr = (String.valueOf(menuObj[i][3])).split("~");
						for (int a = 0; a < arr.length; a++) {
				%>
				<option value="<%=arr[a].split("@")[0] %>"><%=arr[a].split("@")[1]%></option>
				<%
				}
				%>
			</select></td>

		</tr>

		<%
		}//else if end
		%>

		<%
			}//for End

			}//if End
		%>
		<tr >
		<td colspan="6">
		<center><STRONG>Note :</STRONG>
		Reports are compatible in MS Office 2007 or higher version. Please Click on Download Link to download Compatible tool
		<a href="http://www.microsoft.com/en-us/download/details.aspx?id=3" target="_blank"><u>Download</u></a>
		</center>
		</td>
		
		</tr>
	</table>
	<%
		}//end of if (menuObj)
	}
	catch(Exception e){
		e.printStackTrace();
	}
	%>
	<table width="100%" border="0" align="center" >
		<tr align="Center">
		<td></td>
			<td>
			<br>
			
			<input type="button" 
			onclick="javascript:xmltoURL('<%=paraStr %>','<%=paraStr1 %>','<%=reportURL%>','<%=reportName%>','<%=userType %>','<%=paraCaption %>')" value="Generate Report">
			
			<input type="button" 
			onclick="javascript:sendEmail('<%=paraStr %>','<%=paraStr1 %>','<%=reportURL%>','<%=reportName%>','<%=userType %>','<%=paraCaption %>')" value="Email Report">
			    
			</td>
		</tr>
		</table>
	
	</s:form>
	
	<script type="text/javascript">
	
	 function sendEmail(paraStr,paraStr1,reportURL,reportName,userType,paraCaption){
   
   	 var str= paraStr.substring(0,paraStr.length-1);
     var str1=paraStr1.substring(0,paraStr1.length-1);  
     var captionStr= paraCaption.substring(0,paraCaption.length-1);  
     var paraArr=str.split('#');
     var paraArr1=str1.split('#');
     var queryString="";
     try{
     for (i=0;i<=paraArr.length;i++){
     try{
      var fieldValue=document.getElementById('paraFrm_'+paraArr[i]).value;
      if(fieldValue==""&&paraArr1[i]=="true"){
      alert("Please Enter Mandatory Field");
      return false;
      }
     
     queryString += '&'+paraArr[i]+'='+document.getElementById('paraFrm_'+paraArr[i]).value;
      } catch(e){
     //alert(e);
     }
     }}
     catch(e){
    // alert(e);
     }
     try{
     var reportid='reportID='+document.getElementById("paraFrm_reportId").value;
	 var accountId=document.getElementById("paraFrm_accountID").value;
	 var accountName=document.getElementById("paraFrm_accountName").value;
	 var reportID=reportid+queryString;
	 reportID=reportID.replaceAll('&','`');
	 captionStr=captionStr.replaceAll('#','`');
	 //window.close();
	 window.open('','new','top=181,left=131,width=948,height=512,scrollbars=yes,status=no,resizable=yes');
	 document.getElementById("paraFrm").target="new";
	 document.getElementById("paraFrm").action='DashBoard_getEmailPage.action?report_id='+reportID+'&reportName='+reportName+'&paraCaption='+captionStr+'&isDashBoard=YY';//+'&ACCOUNT_CODE='+accountId+'&ACCOUNT_ID='+accountName
	// document.getElementById("paraFrm").target="main";
	 document.getElementById("paraFrm").submit();
	 
      }
      catch(e){
     // alert(e);
      }
  	} 
	
	function xmltoURL(paraStr,paraStr1,reportURL,reportName,userType,paraCaption){
  
  
   	 var str= paraStr.substring(0,paraStr.length-1);
 
     var str1=paraStr1.substring(0,paraStr1.length-1); 
     var captionStr= paraCaption.substring(0,paraCaption.length-1);  
  
     var paraArr=str.split('#');
     var paraArr1=str1.split('#');
     var queryString="";
     try{
     for (i=0;i<=paraArr.length;i++){
     try{
      var fieldValue=document.getElementById('paraFrm_'+paraArr[i]).value;
      if(fieldValue==""&&paraArr1[i]=="true"){
      alert("Please Enter Mandatory Field");
      return false;
      }
     
     queryString += '&'+paraArr[i]+'='+document.getElementById('paraFrm_'+paraArr[i]).value;
      } catch(e){
     //alert(e);
     }
     }}
     catch(e){
    // alert(e);
     }
     try{
     var reportid='reportID='+document.getElementById("paraFrm_reportId").value;
	
	 var accountId=document.getElementById("paraFrm_accountID").value;
	 var accountName=document.getElementById("paraFrm_accountName").value;
	 var reportID=reportid+queryString;
	 reportID=reportID.replaceAll('&','`');
	 captionStr=captionStr.replaceAll('#','`');
	 
	 document.getElementById("paraFrm").action='DashBoard_getReport.action?report_id='+reportID+'&reportName='+reportName+'&paraCaption='+captionStr;//+'&ACCOUNT_CODE='+accountId+'&ACCOUNT_ID='+accountName
	 document.getElementById("paraFrm").target="main";
	 document.getElementById("paraFrm").submit();
	 window.close();
      }
      catch(e){
      alert(e);
      }
  	
	
	}
	
 function submitFun(paraStr,paraStr1,reportURL,userType)
	{
	alert("Internal called");
     var str= paraStr.substring(0,paraStr.length-1);
     var str1=paraStr1.substring(0,paraStr1.length-1);   
     var paraArr=str.split('#');
     var paraArr1=str1.split('#');
     var queryString="";
     try{
     for (i=0;i<=paraArr.length;i++){
     try{
     var fieldValue=document.getElementById('paraFrm_'+paraArr[i]).value;
      if(fieldValue==""&&paraArr1[i]=="true"){
      alert("Please Enter Mandatory Field");
      return false;
      }
       queryString += '&'+paraArr[i]+'='+document.getElementById('paraFrm_'+paraArr[i]).value;
      } catch(e){
    // alert(e);
     }
     }
     }
     catch(e){
   //  alert(e);
     }
     try{
     var reportid=document.getElementById("paraFrm_reportId").value;
	 var accountId=document.getElementById("paraFrm_accountID").value;
	 var accountName=document.getElementById("paraFrm_accountName").value;
   	 var link=reportURL+'?report_id='+reportid+queryString;//+'&report_type='+userType//+'&ACCOUNT_CODE='+accountId+'&ACCOUNT_ID='+accountName;
      //var link='+?report_id='+reportid+queryString;
	alert(link);
       window.open(link,'report');
       window.close();}
      catch(e){
   //   alert(e);
      }
  }
  
  
  
  
  // to send email
 
	
	String.prototype.replaceAll = function( token, newToken, ignoreCase ) {
    var _token;
    var str = this + "";
    var i = -1;
    if ( typeof token === "string" ) {
        if ( ignoreCase ) {
            _token = token.toLowerCase();
            while( (
                i = str.toLowerCase().indexOf(
                    token, i >= 0 ? i + newToken.length : 0
                ) ) !== -1
            ) {
                str = str.substring( 0, i ) +
                    newToken +
                    str.substring( i + token.length );
            }
        } else {
            return this.split( token ).join( newToken );
        }
	    }
	return str;
	};
	</script>

	<script>
	try{
		document.getElementById("paraFrm_ACCOUNT_CODE").value=document.getElementById("paraFrm_accountID").value;
		document.getElementById("paraFrm_ACCOUNT_ID").value=document.getElementById("paraFrm_accountName").value;
		document.getElementById("paraFrm_ACCOUNT_CODE").readOnly = true;
		document.getElementById("paraFrm_ACCOUNT_ID").style.display = 'block';
		document.getElementById("paraFrm_ACCOUNT_CODE").style.display ='block';
		document.getElementById("paraFrm_REPORT_TYPE").style.display ='none';
		document.getElementById("paraFrm_accountID").style.display = 'none';
		document.getElementById("paraFrm_REPORT_TYPE").value=document.getElementById("paraFrm_userType").value
		
	
	}catch(e){
	}
	
	
	
	
	</script>


