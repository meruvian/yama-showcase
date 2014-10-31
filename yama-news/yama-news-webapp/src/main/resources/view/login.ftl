<html>
	<head>
		<meta charset="UTF-8">
		<title><@s.text name="page.login.title" /></title>
	</head>
	<body>
		<div class="form-box" id="login-box">
			<div class="header bg-gray">
				<img src="<@s.url value="/images/flat_meruvian_200px.png" />" />
			</div>
			<@s.form action="${request.contextPath}${request.servletPath}/do" method="post" theme="bootstrap">
				<div class="body bg-gray">
					<@s.textfield key="label.login.username" name="username" />
					<@s.textfield key="label.login.password" name="password" />
					<div class="form-group">
						<input type="checkbox" name="_spring_security_remember_me"/> <@s.text name="label.login.rememberme" />
					</div>
				</div>
				<div class="footer bg-gray">															   
					<button type="submit" class="btn bg-blue btn-block"><@s.text name="button.main.login" /></button>  
					
					<a href="<@s.url value="/register" />" class="text-center"><@s.text name="label.login.register" /></a>
				</div>
			</@s.form>

			<div class="margin text-center">
				<span><@s.text name="label.login.social" /></span>
				<br />
				<a href="<@s.url value="/login/social/mervpolis/auth" />">
					<span class="fa-stack fa-3x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <img class="fa fa-stack-1x fa-inverse" style="width: 1.96em; height: 1.96em;" src="<@s.url value="/images/midas_96px.png" />"/>
                    </span>
				</a>
				<a href="<@s.url value="/login/social/facebook/auth" />">
					<span class="fa-stack fa-3x">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                    </span>
				</a>
				<a href="<@s.url value="/login/social/google/auth" />">
					<span class="fa-stack fa-3x">
                        <i class="fa fa-circle fa-stack-2x" style="color : #f56954"></i>
                        <i class="fa fa-google-plus fa-stack-1x fa-inverse"></i>
                    </span>
				</a>
			</div>
		</div>
	</body>
</html>