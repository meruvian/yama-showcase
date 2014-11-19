<html>
	<head>
		<title><@s.text name="page.admin.conference.room.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.admin.conference.room.header" /></content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				pages: ${rooms.totalPages},
				currentPage: ${rooms.number},
				hrefTextPrefix: '?q=${q!}&max=${max!}&page='
			});
			<#if request.getParameter('shortcut')??>
			$("[data-toggle='offcanvas']").click();
			</#if>
		});
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				
			</div>
		</div>
		<br>
		<div class="row" ng-controller="room">
			<div class="col-md-12">
				<div class="box box-primary">
					<div class="box-header">
					</div>
					<div class="box-body">
						<div class="row">
							<div class="col-md-12">
								<@s.form theme="bootstrap" method="GET">
									<div class="form-group col-md-5 col-xs-12">
										<input name="q" value="${q!}" type="text" class="form-control" placeholder="<@s.text name="button.main.search" />...">
									</div>
									<button type="submit" class="btn btn-primary col-md-1 col-xs-12">
										<i class="fa fa-search"></i>
									</button>
								</@s.form>
							</div>
						</div>
						<hr>
						<#list rooms.content as r>
						<div class="callout">
							<h3>${r.name!} 
								<a href="<@s.url value="/conferences/${r.id!}" />" class="pull-right" target="_blank">
									<span class="btn btn-primary">
									<i class="fa fa-external-link"></i>
									<@s.text name="label.admin.conference.room.join" />
									</span>
								</a>
							</h3>
							<p class="">${r.description!}</p>
						</div>
						</#list>
					</div>
					<div class="box-footer">
						<div class="row">
							<div class="col-md-6">
								<div id="pagination"></div>
							</div>
							<div class="col-md-6">
								<div class="pagination alert pull-right">Found ${rooms.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>