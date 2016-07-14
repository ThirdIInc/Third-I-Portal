  <%@page import="java.util.*,java.io.*" %>
<jsp:include page="/pages/CommonCssJS.jsp" ></jsp:include>
<style>
 
@import url(http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100);
<%
  
String themeName="globe";
String fontName="Roboto, Helvetica, sans-serif, Arial";
String font_Size="8pt";

String smallFontSize ="7pt";
String largeFontSize ="9pt";
try{
 themeName=(String)session.getAttribute("themeName");
 //fontName= "Arial, Helvetica, sans-serif"; //(String)session.getAttribute("fontName");
 //font_Size="8pt"; //(String)session.getAttribute("fontSize");
 
}catch(Exception e){

	
}
if(themeName==null || themeName.equals("null") ||themeName.equals(null)){
	themeName="peoplePower";
	}

  
//	if(fontName==null || fontName.equals("null") ||fontName.equals(null)){
		//fontName="Arial, Helvetica, sans-serif";
	//}
	//if(font_Size==null || font_Size.equals("null") ||font_Size.equals(null)){
	//	font_Size="8pt";
	//}
 

 
	Properties pDefault = new Properties();
	// file object to load that file
	File file; 
	// InputStream for loading that file in property object
	FileInputStream fin; 
	ResourceBundle bundle=ResourceBundle.getBundle("globalMessages");
	
	String path= bundle.getString("data_path");
	
	try {
		try {
			file = new File(path+"/Themes/"
					+ themeName+ ".properties");
			fin = new FileInputStream(file);
			// loading file in property object
			pDefault.load(fin);
		} catch (Exception e) {
			// exception caught if file is not found
			pDefault = null;
			System.out.println(themeName+ ".properties File not found");
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	
	}
	
	
 %>
<%


%>
<%
System.out.println("theme-----------"+pDefault.getProperty("imagePath"));

//System.out.println("backgroundColor-----------"+pDefault.getProperty("backgroundColor"));
/**   BODY   */
String imagePath= (String)pDefault.getProperty("imagePath");
//System.out.println("theme-----------"+request.getContextPath()+"/pages/common/css/"+imagePath+"/images/top.jpg");
String fontSize=font_Size;
String fontFamily=fontName;
String backgroundColor=(String)pDefault.getProperty("backgroundColor");
String thoughtColor=(String)pDefault.getProperty("thoughtColor");
String thoughtFont=(String)pDefault.getProperty("thoughtFont");
String bottomFont=(String)pDefault.getProperty("bottomFont");
String formStripColor=(String)pDefault.getProperty("formStripColor");

/***  BUTTONS***/
String buttonBackground=(String)pDefault.getProperty("buttonBackground");
String buttonBorder=(String)pDefault.getProperty("buttonBorder");
String inputBorder=(String)pDefault.getProperty("inputBorder");
/**      COMMON  **/
String aLink= "#000000";    //(String)pDefault.getProperty("aLink");
String aHover=(String)pDefault.getProperty("aHover");
String aActive=(String)pDefault.getProperty("aActive");
String aVisited=(String)pDefault.getProperty("aVisited");
String aSelected= "#C2C2C2";//(String)pDefault.getProperty("aSelected");

/**      TAB COLORS  **/
String tabALink="#000000"; //(String)pDefault.getProperty("tabALink");
String tabAHover=(String)pDefault.getProperty("tabAHover");
String tabAOn=(String)pDefault.getProperty("tabAOn");

/**      form  **/
String formBgBorder=(String)pDefault.getProperty("formBgBorder");

System.out.println("formBgBorder "+ formBgBorder);

String formThBorder=(String)pDefault.getProperty("formThBorder");
//(String)pDefault.getProperty("formTh");
String tdBottomBorder=(String)pDefault.getProperty("tdBottomBorder");
String dashHeader=(String)pDefault.getProperty("dashHeader");
String mouseOverTD=(String)pDefault.getProperty("mouseOverTD");
String headerHeight=(String)pDefault.getProperty("headerHeight");
%> 



.dasheader {
	 font-Family:<%=fontFamily%>;
	font-size: <%=fontSize%>;
	color: #333333;
	font-weight: bold;
	text-decoration: none;
}


.smalltext {
	 font-Family:<%=fontFamily%>;
	font-size: <%=smallFontSize%>;
	color: #333333;
	text-decoration: none;
	text-align:left; 
}
.seperator
{
	height: 1px; 
	background-color: #EBEBEB;  
	width: 100%;
    
}



input[type=text], textarea, select,input[type=password] {
  font-size:1em;
  padding:2px 0px 2px 4px;
  font-Family:<%=fontFamily%>;
  color:#444;
  line-height:1.3em;
  border-top:1px solid #7c7c7c;
  border-left:1px solid #c3c3c3;
  border-right:1px solid #c3c3c3;
  border-bottom:1px solid #ddd;
  background:#ffffff url(<%=request.getContextPath()%>/pages/common/css/default/images/fieldbg.gif?5275667) repeat-x top;
  vertical-align: center;
}


 
 
	
input[type=checkbox],input[type=radio] {
  padding:2px 0px 2px 4px;
  color:#444;
  line-height:1.3em;
  border:0px solid #7c7c7c;

}

 
select{ padding:2px; }

input.readonly, input.disabled, select.readonly, select.disabled, textarea.readonly, textarea.disabled, select[disabled], input[disabled]{
  opacity: 0.8;
  -moz-opacity: 0.8;
  filter:alpha(opacity=80);
  background:#eee;
  color:#AAA;
}


 
input[type=button],input[type=submit] {
  overflow: hidden;
  display:inline-block;
  font-size: 10pt;
  font-weight: bold;
  color: #FFFFFF;
  background: #5A5A5A;
  border:0px solid #3FBAA2;
  border-radius:3px;
  height:30px;
  font-family: inherit;
  padding:2px 6px 4px 6px; 
  margin-bottom: 2px;
  
}
input[type=button]:hover,input[type=submit]:hover{
  border-color:#888;
  background-color:#3FBAA2;
  color:#5A5A5A;
  font-weight: bold;
  transform: translateZ(0);
  transition-property: color;
  transition:all .15s ease-in;
  
}

 
input.rowEdit{
	height: 20px;
	width: 20px;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/edit.png');
	cursor: pointer;
	border:none;
	background-repeat: no-repeat;
	background-position: center;
}
input.rowDelete{
	height: 20px;
	width: 20px;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/delete.gif');
	cursor: pointer;
	border:none;
	background-repeat: no-repeat;
	background-position: center;
	
}
 
 
.logotext {
	font-Family:<%=fontFamily%>;
	font-size: 12px;
	color: Red;
	text-decoration: none;
}


.smalltext {
	font-Family:<%=fontFamily%>;
	font-size: 8pt;
	color: #333333;
	text-decoration: none;
}

 


.dashlink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: #2080ad;
	text-decoration: none;
}


.mainheader {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: bold;
	color: #FFFFFF;
	text-decoration: none;
	height: 20px;
 
}

.loginwhitetext {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: #FFFFFF;
	text-decoration: none;
 
}



.tableCell {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: #333333;
	text-decoration: none;
	border-bottom: 0px solid #CCCCCC;
	padding-left: 0px;
}

  .tableHeader {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: #333333;
	text-decoration: none;
	border-bottom: 1px solid #CCCCCC;
	background-color:  #E8F2FE;
	margin-left: 5px;
}

.tableSortOrderCell {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: #333333;
	text-decoration: none;
	border-bottom: 1px solid #CCCCCC;
	background-color:  #E9ECF3;
	margin-left: 5px;
}


.logotext {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: #999999;
	text-decoration: none;
}


.search {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: #333333;
	text-decoration: none;
	border: 1px solid #CCCCCC;
}


TR { 
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;

}

TD {  
font-Family:<%=fontFamily%>;
FONT-SIZE: <%=fontSize%>;

}

.td_bottom_border {
	border-bottom: 1px solid <%=tdBottomBorder%>;
	FONT-SIZE: <%=fontSize%>;
	COLOR: #333333;
	font-Family:<%=fontFamily%>;
	padding: 6px;
	text-align: center;
	
}


.headerline {
	color: <%=inputBorder %>;
}




BODY {
	
	COLOR: #333333;
	
	
}

form{
  margin:0;
  padding:0;
}

/*
top image class


*/

.top_env_bg{
	background-image:url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/top.jpg'); 
	
}
.bottom_env_bg{
	
		background-image:url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/bot.jpg');
		background-position: left;
}
.header_height{
height:72px;
}

	
	
 /*BODY.left {
	
	COLOR: #333333;
	font-Family:<%=fontFamily%>;
	TEXT-ALIGN: justify;	
	background-color:#3B3F5C;
	background-repeat:repeat;
	margin:0px;	
}
*/
BODY.main {
	
	COLOR: #333333;
	
	TEXT-ALIGN: justify;
	background-color: #FFFFFF;
	background-repeat: no-repeat;
	margin: 0px;
}

.iconImage {
	border: 0px;
	cursor: pointer;
}



div.scrollF9 {
	position: relative;
	overflow-y: auto;
	height: 270;
}

table.sortable thead {
	cursor: default;
}

/***********************************************************************

             		    Dashlet Classes

***********************************************************************/
.refresh {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/refresh.gif');
	background-repeat: no-repeat;
	float: right;
	vertical-align: middle;
	border: 0px;
}

.close {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/close.gif');
	background-repeat: no-repeat;
	vertical-align: middle;
	float: right;
	border: 0px;
}

.min {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/min.gif');
	background-repeat: no-repeat;
	vertical-align: middle;
	float: right;
	border: 0px;
}

.max {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/max.gif');
	background-repeat: no-repeat;
	vertical-align: middle;
	float: right;
	border: 0px;
}

#mainContainer {
	width: 100%;
	margin: 0 auto;
	text-align: left;
	/*padding-bottom: 30px;*/
	vertical-align: top;
}

#header { 
}  /* Logo image */
.dragableBox { /* The RSS box */
	margin: 5px;
}

/* A div inside the rss box - with a blue border */
.dragableBoxInner {
	border: 0px solid #317082;
}

.dragableBoxHeader { /* Header inside RSS box */
	background-color: #f2f2f2;	
	font-family: Arial, Helvetica, sans-serif;
	font-size: 9pt;
	height: 22px;
	font-weight: bold;
	color: #333333;
	text-decoration: none;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #E1E1E1;
	border-right-color: #E1E1E1;
	border-bottom-color: #E1E1E1;
	border-left-color: #E1E1E1;	
}

.dragableBoxHeader span { /* Text inside header of RSS box */
	line-heigth: 22px;
	vertical-align: middle;
	padding-left:5px;
}

.dragableBoxHeader img,.dragableBoxHeader span {
	/* Text and reload image inside RSS box */
	float: left;
	vertical-align: middle;
}

#rectangleDiv {
	/* Dotted rectangle indicating where objects will be dropped */
	border: 1px dotted red;
	margin: 5px;
}

.dragableBoxContent { /* DIV holding data inside dragable boxes */
	overflow: auto;
	width: 100%;
	background-position: left top;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/default/images/grad.jpg');
	background-repeat: repeat-x;
	background-color: #FFFFFF;
	border-left: 1px solid #E1E1E1;
	border-right: 1px solid #E1E1E1;
	border-bottom: 1px solid #E1E1E1;	
	/*filter:alpha(opacity=92); 
   -moz-opacity: 0.92; 
   opacity: 0.92;*/
}

.dragableBoxStatus {
	background-color: <%=formStripColor%>;
	height: 0px;
}


/***********************************************************************

              		   Tabs Classes

***********************************************************************/
.thought {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/thought_bg.gif');
	background-repeat: repeat;
	width: 100%;
	border: 1px solid <%=thoughtColor %>;
	padding: 2px;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: #ffffff;
}
.thought td{
	color: <%=thoughtFont%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	}

/***********************************************************************

             	    Menu Profile Classes

***********************************************************************/
.dtree {
	font-family: <%=fontFamily%>;
	FONT-SIZE: 11px;
	color: #666;
	white-space: nowrap;
}

.dtree img {
	border: 0px;
	vertical-align: middle;
}

.dtree a{
	color: <%=aLink %>;
	text-decoration: none;
}

.dtree a.node,.dtree a.nodeSel {
	white-space: nowrap;
	padding: 1px 2px 1px 2px;
}

.dtree a.node:hover,.dtree a.nodeSel:hover {
	color: #A50F28;
	text-decoration: underline;
}

.dtree a.nodeSel {
	background-color: <%=aSelected %>;
	COLOR:#ffffff;
}

.dtree .clip {
	overflow: hidden;
}

/***********************************************************************

           			      Buttons Classes

***********************************************************************/



/***********************************************************************

                Form Classes

***********************************************************************/
.formhead{
	font-size: 14pt;
	padding: 0px 0px 0px 10px;
	color: #4D4D4D;
}
.text_head {
	font-Family: sans-serif;
	font-size: 10pt;
	color: #4D4D4D;
}

.shuffleUp {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/default/images/up.GIF');
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	width : 19px;
	color: #295598;
	height: 19px;
	text-decoration: none;
	cursor: hand;

}
.shuffleDown {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/default/images/down.GIF');
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	width : 19px;
	color: #295598;
	height: 19px;
	text-decoration: none;
	cursor: hand;
}

.formbg {
 	border-top: 1px solid #CCCCCC;
	border-bottom: 1px solid #CCCCCC;
	border-left: 0px solid #CCCCCC;
	border-right: 0px solid #CCCCCC;
	background-color: white;
	margin-bottom: 5px;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/default/images/grad.gif');
	background-repeat: repeat-x;
	
}


.borderPhoto{
	border: 1px solid <%=inputBorder %>;
	FONT-SIZE: <%=fontSize%>;
	font-Family:<%=fontFamily%>;
	}




.formtext {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: #333333;
	text-decoration: none;
	font-weight: 500;
}

.salTableHd {
	color: #000000;
	text-decoration: none;
	font-weight:bold;
	vertical-align: middle;
	text-align: center;
	border-right:1px solid <%=formThBorder%>;
	border-bottom:1px solid <%=formThBorder%>;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/grad.gif');

}






.formth {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: #000000;
	text-decoration: none;
	font-weight:bold;
	height: 25px;
	vertical-align: middle;
	background-color:#E2E6EF;
	text-align: center;
	padding-right: 5px;
	padding-left: 5px;

	
}
.sortableTD {
		font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: #333333;
	text-decoration: none;
	//padding-left: 7px;
	//padding-right: 5px;
	padding: 5px;
    text-align: center;
}

.sumTD {
		font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: bold;
	color: #333333;
	text-decoration: none;
	padding-left: 7px;
	padding-right: 5px;
	background-color: #FCF7E7;
}


.mypageTh {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: #000000;
	text-decoration: none;
	font-weight:bold;
	height: 25px;
	vertical-align: middle;
	background-color:#E2E6EF;
	padding-right: 5px;
	padding-left: 7px;

	
}


/***********************************************************************

                Master Form List

***********************************************************************/
.Cell_bg_first {
	background-color: #FCF7E7;
	border: 1px;
	cursor: hand;
}

.Cell_bg_second {
	background-color: #FFFFFF;
	cursor: hand;
	border-bottom: 1px;
}

.onOverCell {
	background-color: <%= mouseOverTD%>;
	cursor: hand;
	border-bottom: 2px;
	font-weight: bold;
}

/***********************************************************************

             TAsk Planner & Menu List 

***********************************************************************/
.left_menu_bg {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/default/images/grad.jpg');
	background-repeat: no-repeat;
	background-position: left top;
	overflow: auto;
	height: 300px;
	width: 180px;
	scrollbar-3dlight-color: white;
	scrollbar-arrow-color: #EEEEEE;
	scrollbar-base-color: white;
	scrollbar-darkshadow-color: white;
	scrollbar-face-color: white;
	scrollbar-highlight-color: #EEEEEE;
	scrollbar-shadow-color: #EEEEEE;
	scrollbar-track-color: white;
}

.header {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/smallhead.png');
	background-repeat: no-repeat;
	color:<%=dashHeader%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: bold;
	
	padding-left: 4px;
	text-decoration: none;
	height: 20px;
}

.bg_grad1 {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/default/images/grad.jpg');
	background-repeat: repeat-x;
	background-color: white;
	filter: alpha(opacity = 92);
	-moz-opacity: 0.92;
	opacity: 0.92;
}

.curvedBox {
	width: 100%;
}

.curvedBox .r1,.curvedBox .r2,.curvedBox .r3,.curvedBox .r4,.curvedBox .r5
	{
	display: block;
	overflow: hidden;
	height: 1px;
	font-size: 1px;
}

.curvedBox .r2,.curvedBox .r3,.curvedBox .r4,.curvedBox .r5 {
	background: #fff;
	border-width: 0 1px;
	border-left: 1px solid #254189;
	border-right: 1px solid #254189;
}

.curvedBox .r1 {
	margin: 0 6px;
}

.curvedBox .r2 {
	margin: 0 3px;
}

.curvedBox .r3 {
	margin: 0 2px;
}

.curvedBox .r4 {
	margin: 0 1px;
	height: 1px;
}

.curvedBox .r5 {
	margin: 0 0px;
	height: 2px;
}

.curvedBox .r2 {
	border-width: 0 1px;
	border-left: 1px solid #254189;
	border-right: 1px solid #254189;
	background-color: #254189;
}

.curvedBox .content {
	background: #fff;
	border-left: 1px solid #254189;
	border-right: 1px solid #254189;
	padding: 0 5px;
}

.borderLR {
	border-top-width: 1px;
	border-right-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-color: <%=formStripColor%>;
}

 

.layoutcurve {
	width: 100%;
}

.layoutcurve .r1,.layoutcurve .r2,.layoutcurve .r3,.layoutcurve .r4,.layoutcurve .r5
	{
	display: block;
	overflow: hidden;
	height: 1px;
	font-size: 1px;
}

.layoutcurve .r2,.layoutcurve .r3,.layoutcurve .r4,.layoutcurve .r5 {
	background: #EEEEEE;
	border-width: 0 1px;
	border-left: 1px solid #EEEEEE;
	border-right: 1px solid #EEEEEE;
}

.layoutcurve .r1 {
	margin: 0 6px;
}

.layoutcurve .r2 {
	margin: 0 3px;
}

.layoutcurve .r3 {
	margin: 0 2px;
}

.layoutcurve .r4 {
	margin: 0 1px;
	height: 1px;
}

.layoutcurve .r5 {
	margin: 0 0px;
	height: 2px;
}

.layoutcurve .r2 {
	border-width: 0 1px;
	border-left: 0px solid #EEEEEE;
	border-right: 0px solid #EEEEEE;
	background-color: #EEEEEE;
}

.layoutcolor {
	background-color: #EEEEEE;
}

/*TABS*/
#tabnavcontainer {
	clear: both;
	margin: 0;
	padding: 0;
	text-align: left;
	background: #e9e9e9;
}

#tabnav {
	float: left;
	width: 100%;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
}

#tabnav ul {
	margin: 0;
	padding: 0;
	list-style: none;
}

#tabnav li {
	display: inline;
	margin: 0px;
	padding: 0px;
}

#tabnav a {
	float: left;
	background: url('<%=request.getContextPath()%>/pages/common/css/corp/images/tab_left.gif') no-repeat left top;
	margin: 0px;
	padding: 0 0 0 7px;
	text-decoration: none;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
}

#tabnav a span {
	float: left;
	display: block;
	background: url('<%=request.getContextPath()%>/pages/common/css/corp/images/tab_right.gif') no-repeat right top;
	padding: 4px 0px 4px 0px;
	color: <%=tabALink %>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
}

/* Commented Backslash Hack hides rule from IE5-Mac \*/
#tabnav a span {
	float: none;
}

/* End IE5-Mac hack */
#tabnav a:hover {
	background-position: 0% -42px;
}

#tabnav a.on {
	background-position: 0% -84px;
	
}

#tabnav a:hover span {
	color: <%=tabAHover %>;
	background-position: 100% -42px;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
}

#tabnav a.on span {
	color: <%=tabAOn %>;
	background-position: 100% -84px;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
}

.tokenPay {
	FONT-SIZE: <%=fontSize%>;
	font-Family:<%=fontFamily%>;
	color: #042F71;
	text-decoration: none;
	vertical-align: middle;
	background-image: url(images/shade.gif);
	border: none;
}

#mainDiv {
	position: relative;	
	overflow: hidden;
	z-index:0;
	
	
}

#leftHeader {
	position: absolute;	
	top: 0px;
	overflow: hidden;
	
	font-family:<%=fontFamily%>;
	font-size: 11px;
	color: #042F71;
	text-decoration: none;
	height: 16px;
	
	background-image: url(images/shade.gif);

	
	
}

#middleHeader {
	position: absolute;
	top: 0px;
	overflow: hidden;
	
	font-family: <%=fontFamily %>;
	font-size: 11px;
	color: #042F71;
	text-decoration: none;
	height: 16px;
	
	background-image: url(images/shade.gif);

	
}
#rightHeader {
	position: absolute;
	top: 0px;
	overflow: hidden;
	
	font-family: <%=fontFamily%>; 
	color: #042F71;
	text-decoration: none;
	height: 16px;
	
	background-image: url(images/shade.gif);
}

#leftData {
	overflow: hidden;		
	position: absolute;
	background-color: #F2F2F2;
}

#middleData {
	overflow-y: hidden;
	overflow-x: auto;
	position: absolute;
	
}

#rightData {
	overflow-y: auto;
	overflow-x: hidden;	
	position: absolute;
	background-color: #F2F2F2;
}

.classCheck {
	border :none;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	padding: 0;
	margin: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 0px;
	marker-offset: 0px; 
}

 

.mailbut {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	height: 16px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/icons/Mail.png');
	background-color:#FFFFFF;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left;
	padding-left: 20px;
	width: 200px;
	margin-bottom: 2px;
	margin-top: 2px;
	border:0px solid #EDECE9;	
	text-align: left;
	}
	
	
	
	.salesbut{
	font-family: Arial, Helvetica, sans-serif;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	height: 16px;
	text-decoration: none;
	background-color:#FFFFFF;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left;
	padding-left: 20px;
	width: 200px;
	margin-bottom: 2px;
	margin-top: 2px;
	border:0px solid #EDECE9;	
	text-align: left;
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/icons/seles.gif');
	}
	
	.serviceDesksbut{
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/icons/ser.png');
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	height: 16px;
	text-decoration: none;
	background-color:#FFFFFF;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left;
	padding-left: 20px;
	width: 200px;
	margin-bottom: 2px;
	margin-top: 2px;
	border:0px solid #EDECE9;	
	text-align: left;
	}
	
	 .projectMgtbut{
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/icons/webone.jpg');
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	height: 16px;
	text-decoration: none;
	background-color:#FFFFFF;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left;
	padding-left: 20px;
	width: 200px;
	margin-bottom: 2px;
	margin-top: 2px;
	border:0px solid #EDECE9;	
	text-align: left;
	 }
	
	 .learningbut{
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/icons/learning.png');
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	height: 16px;
	text-decoration: none;
	background-color:#FFFFFF;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left;
	padding-left: 20px;
	width: 200px;
	margin-bottom: 2px;
	margin-top: 2px;
	border:0px solid #EDECE9;	
	text-align: left;
	 }
	 
	 .plusbut{
	 background-image: url('<%=request.getContextPath()%>/pages/portal/images/icons/plus.gif');
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	height: 16px;
	text-decoration: none;
	background-color:#FFFFFF;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left;
	padding-left: 20px;
	margin-left:10px;
	width: 200px;
	margin-bottom: 2px;
	margin-top: 2px;
	border:0px solid #EDECE9;	
	text-align: left;
	 }	



/*skin1.css*/



	
	
	
.whitetxt {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
}
.txt {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
}
.menulink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: underline;
	font-weight: lighter;
}



.contlink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: bold;
}

.apptxt {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: <%=aLink %>;
	font-weight: lighter;
}
.orange {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: #FFFFFF;
	text-decoration: none;
}

.orange1 {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #EF6D1E;
	text-decoration: none;
}

.portalOnLink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: #EF6D1E;
	cursor: hand;
	text-decoration: none;
}
.portalOffLink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: #FFFFFf;
	cursor: hand;
	text-decoration: none;
}

.more {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}


.blacklink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	font-weight: lighter;
	text-decoration: none;
}

<%
System.out.println("fontFamily          "+fontFamily);
System.out.println("fontSize          "+fontSize);

%>
.headerDashlet {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: bold;
	text-decoration: none;
}

.eventheader {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: <%=aLink %>;
	font-weight: bold;
	text-decoration: none;
}

.search {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: <%=aLink %>;
	text-decoration: none;
	border: 1px solid #CCCCCC;
	}	
	
	

	
	
	.radiobut {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: <%=aLink %>;
	text-decoration: none;
	border: 0px solid #CCCCCC;
	}
	
	
	
.appcellin{
Background-color:#f2f2f2;
height:18px;
border:0px solid #cccccc;
}	
	
.appcellout{
Background-color:none;
height:18px;
}	

.border {
 	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #FF9900;
	border-right-color: #FF9900;
	border-bottom-color: #FF9900;
	border-left-color: #FF9900;
	font-family: <%=fontFamily%>;
	font-size: <%=fontFamily%>;
}


.border1 {

	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #cccccc;
	border-right-color: #cccccc;
	border-bottom-color: #cccccc;
	border-left-color: #cccccc;
}
.header1 {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: <%=aLink %>;
 text-decoration: none;
 font-weight:bold;
}

/*Sliding*/
#portalTab{
	float: left;
	width: 100%;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
}

#portalTab ul {
	padding-left: 0px;
	list-style: none;
	float: left;
	clear: left;
	margin:0px;

}

#portalTab ul.a1 {
	padding-left: 3px;
	list-style: none;
	float: left;
	clear: left;
	margin:0px;
	width:100%;


}

#portalTab ul li {
	float: left;
	display: inline; /*For ignore double margin in IE6*/
	margin: 0 5px;
}

#portalTab ul.a1 li {
	float: left;
	display: inline; /*For ignore double margin in IE6*/
	margin: 0 5px;

}


#portalTab ul li a {
	text-decoration: underline;
	float:left;
	color: #243277;
	cursor: pointer;
	font:  11px/22px <%=fontFamily%>;
		
}

 

#portalTab ul.a1 li a {
	text-decoration: underline;
	float:left;
	color: #243277;
	cursor: pointer;
	font:  12px/25px <%=fontFamily%>;
	text-decoration:none;

	
		
}

#portalTab ul li a span {
	margin: 0 5px 0 -8px;
	padding: 1px 1px 1px 6px;
	position: relative; /*To fix IE6 problem (not displaying)*/
	float:left;
		text-decoration: underline;
}

#portalTab ul.a1 li a span {
	margin: 0 5px 0 -8px;
	padding: 1px 1px 1px 6px;
	position: relative; /*To fix IE6 problem (not displaying)*/
	float:left;
		text-decoration: none;
		
}

/*GREEN*/


/*RED*/
#portalTab ul.red li a:hover {
 	color: #fff;
	background: url(<%=request.getContextPath()%>/pages/portal/images/red.png) no-repeat top right;

}

#portalTab ul.red1 li a:hover {
 	color: #fff;
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top right;

}



#portalTab ul.red li a:hover span {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top left;
	text-decoration: none;
		
}

#portalTab ul.red1 li a:hover span {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top left;
	text-decoration: none;
	 
	
}

#portalTab ul.red li a.current {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top right;
 	color: #fff;
	text-decoration: none;
}

#portalTab ul.red1 li a.current {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top right;
 	color: #fff;
	text-decoration: none;
}


#portalTab ul.red li a.current span {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top left;
		text-decoration: none;
		
}


#portalTab ul.red1 li a.current span {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top left;
		text-decoration: none;
		
}


	
/* The containing DIV element for the Calendar */
#dpCalendar {
  display: none;          /* Important, do not change */
  position: absolute;        /* Important, do not change */
  background-color: #eeeeee;
  color: black;
 FONT-SIZE: <%=fontSize%>;
	font-Family:<%=fontFamily%>;
  width: 150px;
}
/* The table of the Calendar */
#dpCalendar table {
  border: 1px solid black;
  background-color: #eeeeee;
  color: black;
  FONT-SIZE: <%=fontSize%>;
	font-Family:<%=fontFamily%>;
  width: 100%;
}

/* Any regular day of the month cell */
#dpCalendar .cellDay {
  background-color: #FFFFFF;
  color: black;
  text-align: center;
}
/* The Month/Year title cell */
#dpCalendar .cellMonth {
	background-image: url('<%=request.getContextPath()%>/pages/images/botbg.gif');
	FONT-SIZE: 11px;
	COLOR: #3A599A;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;
	FONT-WEIGHT: normal;
	TEXT-ALIGN: center;	
	border: 1px solid #A3D6F3;
	padding-left: 2;
	padding-right: 2;
	padding-top: 2;
	padding-bottom: 2;
} 

/* The Next/Previous buttons */
#dpCalendar .cellButton {
  background-color: #ddddff;
  color: black;
}

/* The day of the month cell that is selected */
#dpCalendar .cellSelected {
  border: 1px solid red;
  background-color: #FFFF99;
  color: black;
  text-align: center;
}
/* The day of the month cell that is Today */
#dpCalendar .cellToday {
  background-color: #62F0EB;
  color: black;
  text-align: center;
}
/* The day of the month cell that is Sunday */
#dpCalendar .cellSunday {
  background-color: #FBA7B5;
  color: black;
  text-align: center;
}

/* The day of the month cell that is Saturdaya */
#dpCalendar .cellSaturday {
  background-color: #A1BEF7;
  color: black;
  text-align: center;
}

/* Any cell in a month that is unused (ie: Not a Day in that month) */
#dpCalendar .unused {
  background-color: #FFFFFF; 
  color: black;
}
/* The cancel button */
#dpCalendar .cellCancel {
  background-color: #cccccc;
  color: black;
  border: 1px solid black;
  text-align: center;
}
/* The clickable text inside the calendar */
#dpCalendar a {
  text-decoration: none;
  background-color: transparent;
  color: blue;
}


.formbgRelogin {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/default/images/grad.gif');
	background-repeat: repeat-x;
	border: 1px solid <%=formBgBorder %>;
	background-color: white;
	padding: 5px;

}
  


/* new css classes */

/* CSS Document */

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.logotext {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #999999;
	text-decoration: none;
}
 

.emptext {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=largeFontSize%>;
	font-weight: normal;
	color: #5D5D5D;
	text-decoration: none;
	
}

.textFont {
	font-Family:<%=fontFamily%>;
	font-size: 9pt;
	font-weight: bold;
	color: #333333;
	text-decoration: none;
	
}

.link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	font-weight: normal;
	color: #333333;
	text-decoration: none;
}
.search {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 9pt;
	color: #333333;
	text-decoration: none;
	border: 1px solid #CCCCCC;
}
.dashborder {
	border: 1px solid #EEEEEE;
}
.dasheader {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	color: #333333;
	text-decoration: none;
}
.dashlink {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 9pt;
	color: #2080ad;
	text-decoration: none;
}
.bottomborder {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #E1E1E1;
	border-right-color: #E1E1E1;
	border-bottom-color: #E1E1E1;
	border-left-color: #E1E1E1;
}
.networkbut {
	background-image: url(images/networkbutton.gif);
	height: 22px;
	width: 148px;
	border: 1px solid #CCCCCC;
	padding-left: 15px;
}

.smalltext {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 8pt;
	color: #333333;
	text-decoration: none;
}
.mainheader {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	color: #FFFFFF;
	text-decoration: none;
}
	.mainactive {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	color: #FFFFCC;
	text-decoration: none;
}.border {
	border: 1px solid #EBEBEB;
}
.button {
	background-image: url(images/button.gif);
	height: 18px;
	border: 1px solid #CCCCCC;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #666666;
	text-decoration: none;
}
fieldset {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #666666;
	text-decoration: none;
	border: 1px solid #CEE0F2;
	font-style: normal;
	font-weight: bold;
	}
 

select {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333333;
	text-decoration: none;
	border: 1px solid #EEF4FB;
	background-image: url(images/button.gif);
}
.checkbox {
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
}

 

 
.legend {
	text-transform: none;
	font-size: 12px;
	padding: 5px;
	margin-left: 1em;
	color: #6979AC;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
		text-decoration: none;
	
}

 
.legend1 {
	text-transform: none;
	font-size: 11px;
	padding: 5px;
	margin-left: 1em;
	color: #6979AC;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	
	
	text-decoration: none;
}




.tooltip {
	background-color:#000;
	border:1px solid #fff;
	padding:10px 15px;
	width:200px;
	display:none;
	color:#fff;
	text-align:left;
	font-size:12px;

	/* outline radius for mozilla/firefox only */
	-moz-box-shadow:0 0 10px #000;
	-webkit-box-shadow:0 0 10px #000;
}

 



.pointer
{
	cursor: pointer;
}
 

.star {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #CC0000;
	text-decoration: none;
}


.text1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	font-weight: normal;
	color: #003366;
	text-decoration: none;
}






 A:link {

	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: <%=aLink %>;
	text-decoration: none;

}

A:visited {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: <%=aLink %>;
	text-decoration: none;
}

A:active {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: <%=aLink %>;
	text-decoration: none;
}

A:hover {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: <%=aLink %>;
	text-decoration: none;
}


#toplinks a:hover {
	color: <%=thoughtFont%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	
}

#toplinks a:link {
	color: <%=thoughtFont%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	
}

#toplinks a:visited {
	color: <%=thoughtFont%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;

}

A.mypage:link {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: <%=aLink %>;
	text-decoration: none;
}

A.mypage:visited {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: <%=aLink %>;
	text-decoration: none;
}

A.mypage:active {
	font-Family:<%=fontFamily%>; 
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: <%=aLink %>;
	text-decoration: none;
}

A.mypage:hover {
		font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: <%=aLink %>;
	text-decoration: none;
}


A.loginLink:link {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: white;
	text-decoration: none;
}

A.loginLink:visited {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: white;
	text-decoration: none;
}

A.loginLink:active {
	font-Family:<%=fontFamily%>; 
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: white;
	text-decoration: none;
}

A.loginLink:hover {
		font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	font-weight: normal;
	color: white;
	text-decoration: none;
}


a.servicelink:link, a.servicelink:active{
	font-Family:<%=fontFamily%>;
	FONT-SIZE: 12px;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: 400;
} 

a.servicelink:hover{
	font-Family:<%=fontFamily%>;
	FONT-SIZE: 12px;
	color: <%=aLink %>;
	text-decoration: underline;
	font-weight: 500;
} 


A.contlink:link {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: 400;
}

A.contlink:visited  {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

A.contlink:active {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

A.contlink:hover {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: underline;
	font-weight: lighter;
}


	A.more:link {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}

A.more:visited  {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}

A.more:active {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}

A.more:hover {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}
 

	A.blacklink:link {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	font-weight: lighter;
	text-decoration: none;
}

A.blacklink:visited  {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	font-weight: lighter;
	text-decoration: none;
}

A.blacklink:active {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	font-weight: lighter;
	text-decoration: none;
}

A.blacklink:hover {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	font-weight: lighter;
	text-decoration: none;
}

.pageHeader
{
 border-bottom: 1px solid #CCC ;
 margin-bottom: 3px;
 
}

.text {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #666666;
	text-decoration: none;
}

.apphead {

	font-family: Arial, Helvetica, sans-serif;
	font-size: 18px;
	color: #023E7F;
	text-decoration: none;
}
.appheader {


	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #023E7F;
	text-decoration: none;
}

.heading1 {

	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #F0771F;
	text-decoration: none;
}

 .heading {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 18px;
	color: #F0771F;
	text-decoration: none;
}
.portalAppButtons{

  border: 1px solid #CCC;
  background-image: url('<%=request.getContextPath()%>/pages/images/icons/portal/buttonbg.png');
  padding: 3px;
  margin-top: 1px;
}
 
</style>