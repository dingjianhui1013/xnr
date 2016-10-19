function validateRestCallback(form,method, callback,navtabid) {
	var $form = $(form);
	if (!$form.valid()) {
		console.log("------- test debug ----------");
		return false;
	}	
	$.ajax({
		type: method || 'POST',
		url:$form.attr("action"),
		data:JSON.stringify($form.serializeArray()),
		dataType:"json",
		cache: false,
		success: callback || DWZ.ajaxDone,
		error: DWZ.ajaxError
	});
	return false;
}


function dialogRestAjaxDone(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){		
		if (json.navTabId){			
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else if (json.rel) {
			navTabPageBreak({}, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
		}
	}
}