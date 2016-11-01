$(function () {
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = { preset: 'date', minDate: new Date() };//最小时间为今天
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.default = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式 
		mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
		startYear: currYear - 50, //开始年份
		endYear: currYear + 10 //结束年份
	};
	$("#dateStart").mobiscroll($.extend(opt['date'], opt['default']));
	$("#dateEnd").mobiscroll($.extend(opt['date'], opt['default']));

	})
	

