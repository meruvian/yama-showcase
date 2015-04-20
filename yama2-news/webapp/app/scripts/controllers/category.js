'use strict';

/**
 * @ngdoc function
 * @name yama2showcaseApp.controller:CategoryCtrl
 * @description
 * # CategoryCtrl
 * Controller of the yama2showcaseApp
 */
angular.module('yama2showcaseApp').controller('CategoryCtrl', function ($scope, $modal, $location, Categories, angularPopupBoxes) {
	$scope.searchParams = $location.search();

	// Load list on page loaded
	Categories.getList($scope.searchParams).then(function(categories) {
		$scope.categories = categories;
		$scope.page = categories.meta.number + 1;
	});

	// Search form submitted or page changed
	$scope.search = function(p) {
		p.cache = p.cache || 0;
		p.cache++;
		p.page = $scope.page - 1;

		$location.search(p);
	};

	// ID clicked, open popup form dialog
	$scope.openForm = function(category) {
		var modal = $modal.open({
			templateUrl: 'categoryForm.html',
			controller: 'CategoryFormCtrl',
			size: 'lg',
			resolve: {
				category: function() {
					return category;
				}
			}
		});

		modal.result.then(function() {
			$scope.search($scope.searchParams);
		});
	};

	// Open popup confirmation and delete user if user choose yes
	$scope.remove = function(category) {
		angularPopupBoxes.confirm('Are you sure want to delete this data?').result.then(function() {
			category.remove().then(function() {
				$scope.search($scope.searchParams);
			});
		});
	};
}).controller('CategoryFormCtrl', function($scope, $modalInstance, Categories, category) {
	if (category) {
		$scope.category = category;
	}

	var success = function() {
		$modalInstance.close();
	};

	var error = function() {
		$scope.error = true;
	};

	$scope.submit = function(category) {
		$scope.error = false;

		if (category.id) {
			category.put().then(success, error);
		} else {
			Categories.post(category).then(success, error);
		}
	};
});
