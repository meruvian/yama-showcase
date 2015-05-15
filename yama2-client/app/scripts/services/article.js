'use strict';

/**
 * @ngdoc service
 * @name yama2showcaseApp.article
 * @description
 * # article
 * Factory in the yama2showcaseApp.
 */
angular.module('yama2showcaseApp').factory('Articles', function (Restangular) {
	return Restangular.service('articles');
});
