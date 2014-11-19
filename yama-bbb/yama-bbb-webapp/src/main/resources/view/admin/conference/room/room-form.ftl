<html>
	<head>
		<title><@s.text name="page.admin.conference.room.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.admin.conference.room.header" /></content>
		<content tag="script">
		<script type="text/javascript">
		</script>
		</content>

		<@s.actionerror theme="bootstrap"/>
		<@s.actionmessage theme="bootstrap"/>
		<div class="row">
			<div class="col-md-9">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.admin.conference.room.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden name="room.id" />
							<@s.textfield key="label.admin.conference.room.name" name="room.name" />
							<@s.textarea rows="2" key="label.admin.conference.room.description" name="room.description" />
							<@s.textfield key="label.admin.conference.room.welcome" name="room.welcome" />
							
							<@s.checkbox key="label.admin.conference.room.record" name="room.record" fieldValue="true" />
							
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
						</@s.form>
					</div>
				</div>
			</div>
			<div class="col-md-3">
			</div>
		</div>
	</body>
</html>