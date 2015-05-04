'use strict';

/**
 * @ngdoc function
 * @name yama2showcaseApp.controller:ArticleCtrl
 * @description
 * # ArticleCtrl
 * Controller of the yama2showcaseApp
 */
angular.module('yama2showcaseApp').controller('ArticleCtrl', function ($scope, $modal, $location, Articles, angularPopupBoxes) {
	$scope.searchParams = $location.search();
	
	// Load list on page loaded
	Articles.getList($scope.searchParams).then(function(articles) {
		$scope.articles = articles;
		$scope.page = articles.meta.number + 1;
	});
	
	// Search form submitted or page changed
	$scope.search = function(p) {
		p.cache = p.cache || 0;
		p.cache++;
		p.page = $scope.page - 1;
		
		$location.search(p);
	};
	
	// ID clicked, open popup form dialog
	$scope.openForm = function(article) {
		var modal = $modal.open({
			templateUrl : 'articleForm.html',
			controller : 'ArticleFormCtrl',
			size : 'lg',
			resolve : {
				article : function() {
					return article;
				}
			}
		});
		
		modal.result.then(function() {
			$scope.search($scope.searchParams);
		});
	};
	
	// Open popup confirmation and delete article if user choose yes
	$scope.remove = function(article) {
		angularPopupBoxes.confirm('Are you sure want to delete this data ?').result.then(function() {
			article.remove().then(function() {
				$scope.search($scope.searchParams);
			});
		});
	};
	
	// update status articles
	$scope.updateStatus = function(article, status) {
		article.status = status || article.status;
		article.put().then(success, error);
	};
	
	var success = function() {};
	
	var error = function() {
		$scope.error = true;
	};
	
}).controller('ArticleFormCtrl', function($scope, $modalInstance, Articles, article, Categories) {

	Categories.getList($scope.searchParams).then(function(categories) {
		$scope.categories = categories;
	});
	
	$scope.loadCategories = function(search) {
		Categories.getList({ q: search }).then(function(categories) {
			$scope.categories = categories;
		});
	};
	
	if (article) {
		$scope.article = article;
		$scope.article.category = article.category;
		console.log($scope.article.category);
	}
	
	$scope.updateStatus = function(article, status) {
		article.status = status || article.status;
		article.put().then(success, error);
	};
	
	var success = function() {
		$modalInstance.close();
	};
	
	var error = function() {
		$scope.error = true;
	};
	
	$scope.submit = function(article) {
		$scope.error = false;
		
		if (article.id) {
			article.put().then(success, error);
		} else {
			Articles.post(article).then(success, error);
		}
	};
});
