
<%
	response.setHeader("Cache-Control", "no-store");
%>
<meta http-equiv="X-UA-Compatible" content="IE=7,chrome=1" />
<meta name="renderer" content="webkit" />
<script>
	var base = '${resourceRoot }/account/build';
	var debug = '${kissy.debug}';
	if (debug == 'true' && KISSY.config('debug')) {
		base = 'http://localhost:5555/awarding-account-front'
	}
	KISSY.config({
		packages : [ {
			name : 'awarding-account-front',
			base : base,
			tag : '${awarding.tag}',
			ignorePackageNameInUri : true,
			combine : false
		} ]
	});
</script>
<script src="//cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
<script type="text/javascript"
	src="${resourceRoot }/core/build/jsonx/jsonx-min.js"></script>
<script type="text/javascript"
	src="${resourceRoot }/core/build/smartPath/smartPath-min.js"></script>
<script type="text/javascript"
	src="${resourceRoot }/core/build/authIdentify/index-min.js"></script>
<script type="text/javascript"
	src="${resourceRoot }/core/build/callbackDialog/index-min.js"></script>
<script type="text/javascript"
	src="${resourceRoot }/core/build/random/index-min.js"></script>
<script type="text/javascript"
	src="${resourceRoot }/core/kissy/agiledialog-by-limeng32/build/index.js"></script>