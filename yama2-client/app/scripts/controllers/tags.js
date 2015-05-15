'use strict';

/**
 * @ngdoc function
 * @name yama2showcaseApp.controller:CategoryCtrl
 * @description
 * # CategoryCtrl
 * Controller of the yama2showcaseApp
 */
angular.module('yama2showcaseApp').controller('TagsCtrl', function ($scope, $modal, $location, Tags, angularPopupBoxes) {
	$scope.searchParams = $location.search();

	// Load list on page loaded
	Tags.getList($scope.searchParams).then(function(tags) {
		$scope.tags = tags;
		$scope.page = tags.meta.number + 1;
	});

	// Search form submitted or page changed
	$scope.search = function(p) {
		p.cache = p.cache || 0;
		p.cache++;
		p.page = $scope.page - 1;

		$location.search(p);
	};

	// ID clicked, open popup form dialog
	$scope.openForm = function(tag) {
		var modal = $modal.open({
			templateUrl: 'tagsForm.html',
			controller: 'TagsFormCtrl',
			size: 'lg',
			resolve: {
				tag: function() {
					return tag;
				}
			}
		});

		modal.result.then(function() {
			$scope.search($scope.searchParams);
		});
	};

	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(tag) {
		angularPopupBoxes.confirm('Are you sure want to delete this data?').result.then(function() {
			tag.remove().then(function() {
				$scope.search($scope.searchParams);
			});
		});
	};
}).controller('TagsFormCtrl', function($scope, $modalInstance, Tags, tag) {
	if (tag) {
		$scope.tag = tag;
	}

	var success = function() {
		$modalInstance.close();
	};

	var error = function() {
		$scope.error = true;
	};

	$scope.submit = function(tag) {
		$scope.error = false;

		if (tag.id) {
			tag.put().then(success, error);
		} else {
			Tags.post(tag).then(success, error);
		}
	};
});
