<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>



  <head>
    <title>Please wait</title>
    <meta http-equiv="refresh" content="50;url=<s:url includeParams="all" />"/>
  </head>
  <body>
   
  </body>
<%		
		Properties pDefault = new Properties();
		// file object to load that file
		File file;
		// InputStream for loading that file in property object
		FileInputStream fin;
		ResourceBundle resBundle=ResourceBundle.getBundle("globalMessages");
		String path=resBundle.getString("data_path");
		double estimatedTime=0;
		try {
			file = new File(path + "\\Labels\\tax.properties");
			fin = new FileInputStream(file);
			// loading file in property object
			pDefault.load(fin);
			estimatedTime=Double.parseDouble(pDefault.getProperty("estimatedTime"));
			} catch (Exception e) {
			// exception caught if file is not found
			pDefault = null;
		}%>
		
<s:form action="DivisionTaxCalc" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Tax
					Calculation Process</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr><s:hidden name="totalCount"></s:hidden>
<tr>
<td height="" valign="middle" align="center">
<img height="200" width="200" src="../pages/portal/images/loading.gif">
</td>
</tr>
		<tr>
		<td height="" valign="middle" align="center">
		<font color="blue"> Please wait while we process your request.
      <a href="<s:url includeParams="all" />"> <font color="red">click here</font></a> if this page does not reload automatically.</font>
	</td></tr>
	<td height="" valign="middle" align="center" ><font color="blue"><%="Employees processed so far "+pDefault.getProperty("employeeCount")+" out of "+ pDefault.getProperty("totalCount")+"."%>
	
	<%if(estimatedTime>0){%>
	<%="Estimated time left to complete the process is " %><strong><%=estimatedTime+" seconds" %></strong>
	<%} %>
	
			</font></td>
	<tr>	</tr>	
	<% String xml="<chart palette='4' bgColor='FFFFFF' formatNumberScale='1' adjustTM='1' lowerLimit='0' upperLimit='"+pDefault.getProperty("totalCount")+"' caption='Employees Processed' chartTopMargin='15' chartRightMargin='20' roundRadius='5' colorRangeFillMix='{light-60},{color},{dark-20},{dark-60}' colorRangeFillRatio='20,50,20,10'>"
	+" <colorRange>"
	 +" <color minValue='0' maxValue='"+pDefault.getProperty("totalCount")+"' /> "
	 + "</colorRange>"
	 + "<value>"+pDefault.getProperty("employeeCount")+"</value> "
	+  "</chart>"; 				 
					
xml=createChartHTML("../pages/charts/fusionCharts/HBullet.swf",    "", xml, "productSales", "500", 70, false);%>

<tr><td><%=xml %></td></tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>
<script>

		
	setFinancialYear('paraFrm_fromYear','paraFrm_toYear');
	function callCalculateTax(){
 		var name=['paraFrm_divName','paraFrm_fromYear'];
 		var labName=['division','fromYear'];
 		var flag=['select','enter'];
 		if(document.getElementById('paraFrm_employeeId').value==""){
 			if(!validateBlank(name, labName, flag)){
 			return false;
 		}
 		}else if(document.getElementById('paraFrm_fromYear').value==""){
 			alert('Please enter '+document.getElementById('fromYear').innerHTML);
 			document.getElementById('paraFrm_fromYear').focus();
 			return false;
 		}
 		document.getElementById('paraFrm').action="DivisionTaxCalc_calculateTaxForDivision.action";
 		document.getElementById('paraFrm').submit();
 }
	
	function setToYear(){
    	var from = document.getElementById('paraFrm_fromYear').value;
    	if(from=="") {
    		document.getElementById('paraFrm_toYear').value="";
    	} else {
   	 		var x=eval(from) +1;
	  		document.getElementById('paraFrm_toYear').value=x;
	  	}
	}
   
   function callReset(){
   try{
   		document.getElementById('paraFrm_deptCode').value="";
   		document.getElementById('paraFrm_deptName').value="";
   		document.getElementById('paraFrm_divCode').value="";
   		document.getElementById('paraFrm_divName').value="";
   		document.getElementById('paraFrm_branchCode').value="";
   		document.getElementById('paraFrm_branchName').value="";
   		document.getElementById('paraFrm_employeeId').value="";
   		document.getElementById('paraFrm_employeeName').value="";
   		document.getElementById('paraFrm_employeeToken').value="";
   		
   		document.getElementById('paraFrm_payBillNo').value="";
   		document.getElementById('paraFrm_payBillName').value="";
   		document.getElementById('paraFrm_empTypeCode').value="";
   		document.getElementById('paraFrm_empTypeName').value="";
   		
   		document.getElementById('paraFrm_declaredInvestments').value="true";
   		}
   		catch(e)
   		{
   		alert(e);
   		}
   		setFinancialYear('paraFrm_fromYear','paraFrm_toYear');
   }
  
  function reportFun() {
  {
		 var from = document.getElementById('paraFrm_fromYear').value;
	    
	    
	     var finYrFrm=document.getElementById('fromYear').innerHTML.toLowerCase(); 
	    
	  	if(from==""){
	  		alert("Please enter "+finYrFrm);
	  		document.getElementById('paraFrm_fromYear').focus();
	  		return false;
	  	}
	  	 if(eval(from)<2000){
	    	alert(finYrFrm+" cannot be less than 2000");
	    	document.getElementById('paraFrm_fromYear').focus();
	    	return false;
	    }
	    
	    
	    var divisionNo = document.getElementById('paraFrm_divCode').value;
	    if(divisionNo==""){
	  		alert("Please select Division ");
	  		document.getElementById('paraFrm_divName').focus();
	  		return false;
	  	}
	    
	  	
		document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action="DivisionTaxCalc_report.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";		
	}
		
	}
	
</script>
