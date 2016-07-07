 <%@page import="java.util.*,java.io.*" %>
/************************************************************************
	
	COMMON CLASSES
	
*************************************************************************/

<%
  
String themeName="globe";
String fontName="Arial";
String font_Size="11";
try{
 themeName=(String)session.getAttribute("themeName");
 fontName="";//(String)session.getAttribute("fontName");
 font_Size="";//(String)session.getAttribute("fontSize");
 
 System.out.println(font_Size+"_______________________"+font_Size);
}catch(Exception e){
	themeName="peoplePower";
	fontName="Arial";
	font_Size="11";
	
}
if(themeName==null || themeName.equals("null") ||themeName.equals(null)){
	themeName="peoplePower";
	}
if(fontName==null || fontName.equals("null") ||fontName.equals(null)){
	fontName="Arial";
}
if(font_Size==null || font_Size.equals("null") ||font_Size.equals(null)){
	font_Size="11";
}
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
String aLink=(String)pDefault.getProperty("aLink");
String aHover=(String)pDefault.getProperty("aHover");
String aActive=(String)pDefault.getProperty("aActive");
String aVisited=(String)pDefault.getProperty("aVisited");
String aSelected=(String)pDefault.getProperty("aSelected");

/**      TAB COLORS  **/
String tabALink=(String)pDefault.getProperty("tabALink");
String tabAHover=(String)pDefault.getProperty("tabAHover");
String tabAOn=(String)pDefault.getProperty("tabAOn");

/**      form  **/
String formBgBorder=(String)pDefault.getProperty("formBgBorder");
String formThBorder=(String)pDefault.getProperty("formThBorder");
//(String)pDefault.getProperty("formTh");
String tdBottomBorder=(String)pDefault.getProperty("tdBottomBorder");
String dashHeader=(String)pDefault.getProperty("dashHeader");
String mouseOverTD=(String)pDefault.getProperty("mouseOverTD");
String headerHeight=(String)pDefault.getProperty("headerHeight");
%> 


TR { 
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;

}

TD {  
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;

}

.td_bottom_border {
	border-bottom: 1px solid <%=tdBottomBorder%>;
	FONT-SIZE: <%=fontSize%>px;
	COLOR: #333333;
	font-Family:<%=fontFamily%>;
}

INPUT {
	border: 1px solid <%=inputBorder %>;
	FONT-SIZE: <%=fontSize%>px;
	font-Family:<%=fontFamily%>;
	background-color: #ffffff;
}

.POLLINPUT {
	border: 0px;
	FONT-SIZE: <%=fontSize%>px;
	font-Family:<%=fontFamily%>;
	background-color: #ffffff;
}

.headerline {
	color: <%=inputBorder %>;
}

SELECT {
	border: 1px solid <%=inputBorder %>;
	FONT-SIZE: <%=fontSize%>px;
	font-Family:<%=fontFamily%>;
}

TEXTAREA {
	border: 1px solid <%=inputBorder %>;
	FONT-SIZE: <%=fontSize%>px;
	font-Family:<%=fontFamily%>;
}

BODY {
	
	COLOR: #333333;
	
	TEXT-ALIGN: justify;
	scrollbar-3dlight-color: white;
	scrollbar-arrow-color: <%=formBgBorder %>;
	scrollbar-base-color: white;
	scrollbar-darkshadow-color: white;
	scrollbar-face-color: white;
	scrollbar-highlight-color: <%=formBgBorder %>;
	scrollbar-shadow-color: <%=formBgBorder %>;
	scrollbar-track-color: white;
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
	cursor: hand;
}

A:link {
	TEXT-DECORATION: none;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
}

A:visited {
	TEXT-DECORATION: none;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aVisited %>;
}

A:active {
	TEXT-DECORATION: none;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aActive %>;
}

A:hover {
	TEXT-DECORATION: underline;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aHover %>;
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
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/bighead.gif');
	background-repeat: no-repeat;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=dashHeader %>;
	text-decoration: none;
	height: 22px;
	font-weight: bold;
	
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
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/grad.gif');
	background-repeat: repeat-x;
	background-color: #FFF;
	border-top: 2px solid <%=formStripColor%>;
	border-left: 2px solid <%=formStripColor%>;
	border-right: 2px solid <%=formStripColor%>;
	background-color: white;
	/*filter:alpha(opacity=92); 
   -moz-opacity: 0.92; 
   opacity: 0.92;*/
}

.dragableBoxStatus {
	background-color: <%=formStripColor%>;
	height: 0px;
}

.dragableBox .r1,.dragableBox .r2,.dragableBox .r3,.dragableBox .r4 {
	filter: alpha(opacity = 92);
	-moz-opacity: 0.92;
	opacity: 0.92;
	display: block;
	overflow: hidden;
	height: 1px;
	font-size: 1px;
}

.dragableBox .r3,.dragableBox .r4 {
	background-color: <%=formStripColor%>;
	border-width: 0 1px;
	border-left: 1px solid <%=formStripColor%>;
	border-right: 1px solid <%=formStripColor%>;
}

.dragableBox .r2 {
	background-color: <%=formStripColor%>;
	border-width: 0 1px;
	border-left: 1px solid <%=formStripColor%>;
	border-right: 1px solid <%=formStripColor%>;
	background-color: <%=formStripColor%>;
}

.dragableBox .r1 {
	margin: 0 4px;
}

.dragableBox .r2 {
	margin: 0 3px;
}

.dragableBox .r3 {
	margin: 0 2px;
}

.dragableBox .r4 {
	margin: 0 1px;
	height: 2px;
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
	FONT-SIZE: <%=fontSize%>px;
	color: #ffffff;
}
.thought td{
	color: <%=thoughtFont%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	}
#toplinks a:hover {
	color: <%=thoughtFont%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-size:11px;
}

#toplinks a:link {
	color: <%=thoughtFont%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-size:11px;
}

#toplinks a:visited {
	color: <%=thoughtFont%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-size:11px;
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
input.addnew {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/add.png');
	background-color:<%=buttonBackground%>;
	cursor: hand;
	border:1px solid <%=buttonBorder%>;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 10px;
	margin-bottom: 2px;
	margin-top: 2px;
}

input.save {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/save.png');
	background-color:<%=buttonBackground%>;
	cursor: hand;
	border:1px solid <%=buttonBorder%>;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.reset {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/reset.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.delete {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/delete.gif');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 6px;
	margin-bottom: 2px;
	margin-top: 2px;
}

input.saveandnew {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/savenew.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
	margin-bottom:2px;
	margin-top:2px;
}

input.saveandforward {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/next.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.saveandprevious {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/next.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.forward {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/next.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.edit {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/edit.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.lock {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/lock.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.unlock {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/unlock.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.search {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/Search.gif');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 8px;
	margin-bottom: 2px;
	margin-top: 2px;
}

input.add {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink%>;
	height: 20px;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/add.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.cancel {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/cancel.png');
	background-color:<%=buttonBackground%>;
	border:1px solid <%=buttonBorder%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
	margin-bottom:2px;
	margin-top:2px;
}

input.token {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}
input.exportpdfreport {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}
input.exporttextreport {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}
input.finalize {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.sendforapproval {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.next {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/forward.PNG');
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 10px;
	margin-bottom:2px;
	margin-top:2px;
}

input.previous {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/next.png');
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 10px;
	margin-bottom:2px;
	margin-top:2px;
}

input.saveandnext {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/forward.PNG');
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 10px;
	margin-bottom:2px;
	margin-top:2px;
}

input.back {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/icons/next.png');
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 10px;
	margin-bottom:2px;
	margin-top:2px;
}

input.finish {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.approve {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.reject {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.report {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.send {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.show {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.saveandnew {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.forward {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.saveandforward {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.ok {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
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
input.clear {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}

input.converttoemployee {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	height: 20px;
	text-decoration: none;
	border:1px solid <%=buttonBorder%>;
	background-color:<%=buttonBackground%>;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	margin-bottom:2px;
	margin-top:2px;
}



/***********************************************************************

                Form Classes

***********************************************************************/
.text_head {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #000000;
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
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/grad.gif');
	background-repeat: repeat-x;
	border: 1px solid <%=formBgBorder %>;
	background-color: white;
	padding: 5px;

}

.borderPhoto{
	border: 1px solid <%=inputBorder %>;
	FONT-SIZE: <%=fontSize%>px;
	font-Family:<%=fontFamily%>;
	}




.formtext {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #333333;
	text-decoration: none;
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
	FONT-SIZE: <%=fontSize%>px;
	color: #000000;
	text-decoration: none;
	font-weight:bold;
	height: 16px;
	vertical-align: middle;
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/grad.gif');
	text-align: center;
	text-st
	padding-right: 5px;
	padding-left: 5px;
	border-right:1px solid <%=formThBorder %>;
	border-bottom:1px solid <%=formThBorder %>;
}
.sortableTD {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #000000;
	text-decoration: none;
	height: 16px;
	vertical-align: middle;
	padding-right: 5px;
	padding-left: 5px;
	border-right:1px solid <%=tdBottomBorder %>;
	border-bottom:1px solid <%=tdBottomBorder %>;
}



/***********************************************************************

                Master Form List

***********************************************************************/
.Cell_bg_first {
	background-color: #E6F9BF;
	border: 2px;
	cursor: hand;
}

.Cell_bg_second {
	background-color: #FFFFF;
	cursor: hand;
	border-bottom: 2px;
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
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/grad.gif');
	background-repeat: no-repeat;
	background-position: left top;
	overflow: auto;
	height: 300px;
	width: 180px;
	scrollbar-3dlight-color: white;
	scrollbar-arrow-color: <%=formBgBorder %>;
	scrollbar-base-color: white;
	scrollbar-darkshadow-color: white;
	scrollbar-face-color: white;
	scrollbar-highlight-color: <%=formBgBorder %>;
	scrollbar-shadow-color: <%=formBgBorder %>;
	scrollbar-track-color: white;
}

.header {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/smallhead.png');
	background-repeat: no-repeat;
	color:<%=dashHeader%>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-weight: bold;
	
	padding-left: 4px;
	text-decoration: none;
	height: 20px;
}

.bg_grad {
	background-image: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/grad.gif');
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
	border-top-width: 4px;
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
	background: <%=formStripColor%>;
	border-width: 0 1px;
	border-left: 1px solid <%=formStripColor%>;
	border-right: 1px solid <%=formStripColor%>;
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
	border-left: 1px solid <%=formStripColor%>;
	border-right: 1px solid <%=formStripColor%>;
	background-color: <%=formStripColor%>;
}

.layoutcolor {
	background-color: <%=formStripColor%>;
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
	FONT-SIZE: <%=fontSize%>px;
}

#tabnav ul {
	margin: 0;
	padding: 0;
	list-style: none;
}

#tabnav li {
	display: inline;
	margin: 0;
	padding: 0;
}

#tabnav a {
	float: left;
	background: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/tab_left.gif') no-repeat left top;
	margin: 0;
	padding: 0 0 0 7px;
	text-decoration: none;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
}

#tabnav a span {
	float: left;
	display: block;
	background: url('<%=request.getContextPath()%>/pages/common/css/<%=imagePath%>/images/tab_right.gif') no-repeat right top;
	padding: 4px 0px 4px 0px;
	color: <%=tabALink %>;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
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
	FONT-SIZE: <%=fontSize%>px;
}

#tabnav a.on span {
	color: <%=tabAOn %>;
	background-position: 100% -84px;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
}

.tokenPay {
	FONT-SIZE: <%=fontSize%>px;
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


.yui-calcontainer {
	position:relative;
	padding:5px;
	background-color:#F7F9FB;
	border:1px solid #7B9EBD;
	float:center;
	vertical-align:top;
	_overflow:hidden; /* IE6 only, to clip iframe shim */
}

.yui-calcontainer iframe {
	position:absolute;
	border:none;
	margin:0;padding:0;
	z-index:0;
	width:100%;
	height:100%;
	left:0px;
	top:0px;
}

/* IE6 only */
.yui-calcontainer iframe.fixedsize {
	width:50em;
	height:50em;
	top:-1px;
	left:-1px;
}

.yui-calcontainer.multi {
	padding:0;
}

.yui-calcontainer.multi .groupcal {
	padding:5px;
	background-color:transparent;
	z-index:1;
	float:left;
	position:relative;
	border:none;
}

.yui-calcontainer .title {
	font:100%  <%=fontFamily%>;
	color:#000;
	font-weight:bold;
	margin-bottom:5px;
	height:25px;
	position:absolute;
	top:3px;left:5px;
	z-index:1;
}

.yui-calcontainer .close-icon {
	position:absolute;
	right:3px;
	top:3px;
	border:none;
	z-index:1;
}

.yui-calcontainer .calclose {
	background: url("calx.gif") no-repeat;
	width:17px;
	height:13px;
	cursor:pointer;	
}

/* Calendar element styles */

.yui-calendar {
	font:100%  <%=fontFamily%>;
	text-align:center;
	border-spacing:0;
	border-collapse:separate;
	position:relative;
}

.yui-calcontainer.withtitle {
	padding-top:1.5em;
}

.yui-calendar .calnavleft {
	position:absolute;
	cursor:pointer;
	top:2px;
	bottom:0;
	width:9px;
	height:12px;
	left:2px;
	z-index:1;
	background: url("callt.gif") no-repeat;
}

.yui-calendar .calnavright {
	position:absolute;
	cursor:pointer;
	top:2px;
	bottom:0;
	width:9px;
	height:12px;
	right:2px;
	z-index:1;
	background: url("calrt.gif") no-repeat;
}

.yui-calendar td.calcell {
	padding:.1em .2em;
	border:1px solid #E0E0E0;
	text-align:center;
}

.yui-calendar td.calcell a {
	color:#003DB8;
	text-decoration:none;
}

.yui-calendar td.calcell.today {
	border:1px solid #000;
}

.yui-calendar td.calcell.oom {
	cursor:default;
	color:#999;
	background-color:#EEE;
	border:1px solid #E0E0E0;
}

.yui-calendar td.calcell.selected {
	color:#003DB8;
	background-color:#FFF19F;
	border:1px solid #FF9900;
}

.yui-calendar td.calcell.calcellhover {
	cursor:pointer;
	color:#FFF;
	background-color:#FF9900;
	border:1px solid #FF9900;
}

.yui-calendar td.calcell.calcellhover a {
	color:#FFF;
}

.yui-calendar td.calcell.restricted {
	text-decoration:line-through;
}

.yui-calendar td.calcell.previous {
	color:#CCC;
}

.yui-calendar td.calcell.highlight1 { background-color:#CCFF99; }
.yui-calendar td.calcell.highlight2 { background-color:#99CCFF; }
.yui-calendar td.calcell.highlight3 { background-color:#FFCCCC; }
.yui-calendar td.calcell.highlight4 { background-color:#CCFF99; }

.yui-calendar .calhead {
	border:1px solid #E0E0E0;
	vertical-align:middle;
	background-color:#FFF;
}

.yui-calendar .calheader {
	position:relative;
	width:100%;
	text-align:center;
}

.yui-calendar .calheader img {
	border:none;
}

.yui-calendar .calweekdaycell {
	color:#666;
	font-weight:normal;
	text-align:center;
	width:1.5em;
}

.yui-calendar .calfoot {
	background-color:#EEE;
}

.yui-calendar .calrowhead, .yui-calendar .calrowfoot {
	color:#666;
	font-size:9px;
	font-style:italic;
	font-weight:normal;
	width:15px;
}

.yui-calendar .calrowhead {
	border-right-width:2px;
}

/* CalendarNavigator */
.yui-calendar a.calnav {
	_position:relative;
	padding-left:2px;
	padding-right:2px;
	text-decoration:none;
	color:#000;
}

.yui-calendar a.calnav:hover {
	border:1px solid #003366;
	background-color:#6699cc;
	background: url(calgrad.png) repeat-x;
	color:#fff;
	cursor:pointer;
}

.yui-calcontainer .yui-cal-nav-mask {
	position:absolute;
	z-index:2;
	display:none;

	margin:0;
	padding:0;

	left:0;
	top:0;
	width:100%;
	height:100%;
	_width:0;    /* IE6, IE7 Quirks - width/height set programmatically to match container */
	_height:0;

	background-color:#000;
	opacity:0.25;
	*filter:alpha(opacity=25);
}

.yui-calcontainer .yui-cal-nav {
	position:absolute;
	z-index:3;
	display:none;

	padding:0;
	top:1.5em;
	left:50%;
	width:12em;
	margin-left:-6em;

	border:1px solid #7B9EBD;
	background-color:#F7F9FB;
	font-size:93%;
}

.yui-calcontainer.withtitle .yui-cal-nav {
	top:3.5em;
}

.yui-calcontainer .yui-cal-nav-y,
.yui-calcontainer .yui-cal-nav-m,
.yui-calcontainer .yui-cal-nav-b {
	padding:2px 5px 2px 5px;
}

.yui-calcontainer .yui-cal-nav-b {
	text-align:center;
}

.yui-calcontainer .yui-cal-nav-e {
	margin-top:2px;
	padding:2px;
	background-color:#EDF5FF;
	border-top:1px solid black;
	display:none;
}

.yui-calcontainer .yui-cal-nav label {
	display:block;
	font-weight:bold;
}

.yui-calcontainer .yui-cal-nav-mc {
	width:100%;
	_width:auto; /* IE6 doesn't like width 100% */
}

.yui-calcontainer .yui-cal-nav-y input.yui-invalid {
	background-color:#FFEE69;
	border: 1px solid #000;
}

.yui-calcontainer .yui-cal-nav-yc {
	width:3em;
}

.yui-calcontainer .yui-cal-nav-b button {
	font-size:93%;
	text-decoration:none;
	cursor: pointer;
	background-color: #79b2ea;
	border: 1px solid #003366;
	border-top-color:#FFF;
	border-left-color:#FFF;
	margin:1px;
}

.yui-calcontainer .yui-cal-nav-b .yui-default button {
	/* not implemented */
}

.mailbut {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
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
	FONT-SIZE: <%=fontSize%>px;
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
	FONT-SIZE: <%=fontSize%>px;
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
	FONT-SIZE: <%=fontSize%>px;
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
	FONT-SIZE: <%=fontSize%>px;
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
	FONT-SIZE: <%=fontSize%>px;
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
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	text-decoration: none;
}
.txt {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	text-decoration: none;
}
.menulink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	text-decoration: underline;
	font-weight: lighter;
}

 

.contlink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

.apptxt {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight: lighter;
}
.orange {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #EF6D1E;
	text-decoration: none;
}

.portalOnLink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #EF6D1E;
	cursor: hand;
	text-decoration: none;
}
.portalOffLink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #FFFFFf;
	cursor: hand;
	text-decoration: none;
}

.more {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}


.blacklink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-weight: lighter;
	text-decoration: none;
}
.headerDashlet {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-weight: bold;
	text-decoration: none;
}

.eventheader {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight: bold;
	text-decoration: none;
}

.search {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	text-decoration: none;
	border: 1px solid #CCCCCC;
	}	
	
	

	
	
	.radiobut {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	text-decoration: none;
	border: 0px solid #CCCCCC;
	}
	
	A.contlink:link {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

A.contlink:visited  {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

A.contlink:active {
font-Family:<%=fontFamily%>;
	FONT-SIZE: 20px;
	col<%=fontSize%>: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

A.contlink:hover {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: 20px;
	col<%=fontSize%>: <%=aLink %>;
	text-decoration: underline;
	font-weight: lighter;
}


	A.more:link {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}

A.more:visited  {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}

A.more:active {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}

A.more:hover {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight:bold;
	text-decoration: none;
}
 

	A.blacklink:link {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight: lighter;
	text-decoration: none;
}

A.blacklink:visited  {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight: lighter;
	text-decoration: none;
}

A.blacklink:active {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight: lighter;
	text-decoration: none;
}

A.blacklink:hover {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
	font-weight: lighter;
	text-decoration: none;
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
	font-size: <%=fontSize%>px;
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
	FONT-SIZE: <%=fontSize%>px;
	color: <%=aLink %>;
 text-decoration: none;
 font-weight:bold;
}

/*Sliding*/
#portalTab{
	float: left;
	width: 100%;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
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
 
/* Specific changes for calendar running under fonts/reset */
.yui-calendar .calbody a:hover {background:inherit;}
p#clear {clear:left; padding-top:10px;}