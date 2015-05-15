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
		angular.forEach(articles, function(article){
			article.getList('tags').then(function(tags){
				article.tags = tags;
			});
		});
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
	
	// Open popup confirmation and delete article if article choose yes
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
	
}).controller('ArticleFormCtrl', function($scope, $modalInstance, Articles, article, Categories, $cacheFactory) {
	
	Categories.getList($scope.searchParams).then(function(categories) {
		$scope.categories = categories;
	});
	
	$scope.loadCategories = function(search) {
		Categories.getList({ q: search }).then(function(categories) {
			$scope.categories = categories;
		});
	};
	
	if (article) {
		var strTag = [];
		article.getList('tags').then(function(tags){
			angular.forEach(tags, function(tag){
				strTag.push(tag.tags);
			});
			$scope.article.tagsValue = [];
			$scope.article.tagsValue = strTag.join(',').toString();
		});
		
		$scope.article = article;
		$scope.article.category = article.category;
		$scope.article.summary = article.content.substring(0, 150);
	}
	
	$scope.updateStatus = function(article, status) {
		article.status = status || article.status;
		article.put().then(success, error);
	};
	
	var invalidateCache = function() {
		$cacheFactory.get('$http').remove(article.one('tags').getRequestedUrl());
	};
	
	var success = function() {
		invalidateCache();
		$modalInstance.close();
	};
	
	var error = function() {
		$scope.error = true;
	};
	
	$scope.submit = function(article) {
		$scope.error = false;
		
		$scope.articleTag = [];
		article.summary = article.content.substring(0, 150);
		var tags = article.tags.split(',');
		angular.forEach(tags, function(tag){
			$scope.articleTag.push({tags: tag});
		});
		article.tags = $scope.articleTag;
		
		if (article.id) {
			article.put().then(success, error);
		} else {
			Articles.post(article).then(success, error);
		}
	};
});
