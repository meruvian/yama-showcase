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
		});
		
		function room($scope, $http) {
			$scope.confirm = function(id) {
				$http.post('<@s.url value="/admin/conference/rooms/" />' + id + '/delete').success(function() {
					location.reload();
				});
			}
		}
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<a href="<@s.url value="/admin/conference/rooms/-/edit" />" class="btn btn-default col-md-3"><@s.text name="button.main.add" /></a>
			</div>
			<div class="col-md-6">
				<@s.form theme="bootstrap" method="GET">
					<div class="form-group col-md-10">
						<input name="q" value="${q!}" type="text" class="form-control" placeholder="<@s.text name="button.main.search" />...">
					</div>
					<@s.submit cssClass="btn btn-primary col-md-2" value="%{getText('button.main.search')}" />
				</@s.form>
			</div>
		</div>
		<br>
		<div class="row" ng-controller="room">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th><@s.text name="label.admin.conference.room.name" /></th>
										<th><@s.text name="label.admin.conference.room.description" /></th>
										<th><@s.text name="label.admin.conference.room.welcome" /></th>
										<th><@s.text name="label.admin.conference.room.record" /></th>
										<th><@s.text name="label.admin.conference.room.action" /></th>
									</tr>
								</thead>
								<tbody>
									<#list rooms.content as r>
									<tr>
										<td><a href="<@s.url value="/admin/conference/rooms/${(r.id!'')}/edit" />">${(r.name!"")}</a></td>
										<td>${r.description!}</td>
										<td>${r.welcome!}</td>
										<td>
											<#if r.record>
											<span class="glyphicon glyphicon-ok"></span>
											<#else>
											<span class="glyphicon glyphicon-remove"></span>
											</#if>
										</td>
										<td>
											<a href="" title="<@s.text name="label.admin.conference.room.action.delete" />" ng-bootbox-confirm="<@s.text name="message.admin.conference.rooms.delete.confirm" />" ng-bootbox-confirm-action="confirm('${r.id!}')">
												<i class="glyphicon glyphicon-trash"></i>
											</a>
											&nbsp;&nbsp;&nbsp;
											<#if r.running>
											<a href="<@s.url value="/admin/conference/rooms/${r.id!}/stop" />">
											<span class="glyphicon glyphicon-stop"></span>
											</a>
											<#else>
											<a href="<@s.url value="/admin/conference/rooms/${r.id!}/play" />">
											<span class="glyphicon glyphicon-play"></span>
											</a>
											</#if>
										</td>
									</tr>
									</#list>
								</tbody>
							</table>
						</div>
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