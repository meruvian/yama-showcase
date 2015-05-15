'use strict';

/**
 * @ngdoc service
 * @name yama2showcaseApp.category
 * @description
 * # category
 * Factory in the yama2showcaseApp.
 */
angular.module('yama2showcaseApp').factory('Categories', function (Restangular) {
	return Restangular.service('categories');
});
