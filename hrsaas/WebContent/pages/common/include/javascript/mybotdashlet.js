SUGAR.sugarHome.init = function () {
	homepage_dd = new Array();
	j = 0;
	
	for(i in dashletIds) {
		homepage_dd[j] = new ygDDList('dashlet_' + dashletIds[i]);
		homepage_dd[j].setHandleElId('dashlet_header_' + dashletIds[i]);
		homepage_dd[j].onMouseDown = SUGAR.sugarHome.onDrag;  
		homepage_dd[j].afterEndDrag = SUGAR.sugarHome.onDrop;
		//alert('value of i=='+i)
		refreshSection(i);
		j++;
	}
	for(var wp = 0; wp <= 2; wp++) {
	    homepage_dd[j++] = new ygDDListBoundary('hidden' + wp);
	}
	YAHOO.util.DDM.mode = 1;
}
YAHOO.util.Event.addListener(window, 'load', SUGAR.sugarHome.init);  
