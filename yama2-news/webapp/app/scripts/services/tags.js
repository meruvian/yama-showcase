'use strict';

/**
 * @ngdoc service
 * @name yama2showcaseApp.category
 * @description
 * # category
 * Factory in the yama2showcaseApp.
 */
angular.module('yama2showcaseApp').factory('Tags', function (Restangular) {
	return Restangular.service('tags');
});
