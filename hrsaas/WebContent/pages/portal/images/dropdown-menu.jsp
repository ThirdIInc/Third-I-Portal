
<%

System.out.println("Great!!!!!!!!!!!!!!!!!");
out.println("Great!!!!!!!!!!!!!!!!!");

%>

#navigation {
	margin:0;
	padding: 0;
	clear:both;
	width:400px;
	height:28px;
	background-repeat: repeat-x;
	background-position: left top;
	background:  url('<%=request.getContextPath()%>/pages/portal/images/dropdown-bg.gif') repeat-x left top;
}


ul.nav-main,
ul.nav-main li {
	list-style: none;
	margin: 0;
	padding: 0;
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/dropdown-bg-hover.gif') ;
}

ul.nav-main {
	position: relative;
	z-index: 597;
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/dropdown-bg-hover.gif') ;
}

ul.nav-main li:hover > ul {
	visibility: visible;
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/dropdown-bg-hover.gif') ;
}


ul.nav-main li.hover,
ul.nav-main  li:hover {
	position: relative;
	z-index: 599;
	cursor: pointer;	
	background: url('<%=request.getContextPath()%>/pages/portal/images/dropdown-bg-hover.gif') repeat-x left top;
}



ul.nav-main li {
	float:left;
	display:block;
	height: 20px;
	color: #cccccc;
	font: 9pt Arial;
	background: url('<%=request.getContextPath()%>/pages/portal/images/separator.gif') no-repeat right center;
}

ul.nav-main li a {
	display:block;
	padding: 5px 16px 0px 16px;
	height: 20px;
	color: #4d4d4d;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 9pt;
	text-decoration: none;	
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/dropdown-bg-hover.gif') ;
}

ul.nav-main li a:hover {
	color:#ffffff;
	background-color: #000000;
	border-top-width: 2px;
	border-right-width: 2px;
	border-bottom-width: 2px;
	border-left-width: 2px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #FF6600;
	border-right-color: #FF6600;
	border-bottom-color: #FF6600;
	border-left-color: #FF6600;	
}

ul.nav-main li a:active {
	color:#ffffff;
	background-color: #000000;
	border-top-width: 2px;
	border-right-width: 2px;
	border-bottom-width: 2px;
	border-left-width: 2px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #FF6600;
	border-right-color: #FF6600;
	border-bottom-color: #FF6600;
	border-left-color: #FF6600;	
}


ul.nav-main *.list {
	padding-right: 2px;
	background: url('<%=request.getContextPath()%>/pages/portal/images/navigation-arrow.gif') no-repeat right top;	
}



ul.nav-sub {
	visibility: hidden;
	position: absolute;
	padding:10px;
	top: 20px;
	left: 0;
	z-index: 598;
	border-top: 1px solid #F2f2f2;
	border-right: 1px solid #F2f2f2;
	border-bottom: 1px solid #F2f2f2;
	border-left: 1px solid #F2f2f2;
	background-color: #ffffff;
	background: #353535 url('<%=request.getContextPath()%>/pages/portal/images/dropdown-list-bg.gif') repeat-x left top;
}

ul.nav-sub li {
	list-style:none;
	display:block;
	padding: 0;
	height: 25px;
	float: none;
	width:145px;
	border-bottom: 1px solid #5a5a5a;
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/dropdown-bg-hover.gif') ;
}

ul.nav-sub li a {
	list-style:none;
	display:block;
	padding: 6px 5px 6px 5px;
	height: 15px;
	float: none;
	width:145px;
	background: none;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 9pt;
	text-decoration: none;
	background-image: url('<%=request.getContextPath()%>/pages/portal/images/dropdown-bg-hover.gif') ;
	color: #ffffff;
}


