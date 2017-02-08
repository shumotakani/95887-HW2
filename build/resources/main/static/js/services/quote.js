'use strict'

angular.module('app.services', ['ngResource'])

.factory('QuoteService', function($resource) {
	return $resource('/api/quote/:id', {id:'@_id'}, {
		random: {
			method: 'GET',
			url: '/api/quote/random'
		}
	});
})

.factory('ListQuote', function($resource) {
	return $resource('/api/quote/:authorName', {authorName:'@_authorName'}, {
		list: {
			method: 'GET', 
			isArray: true,
			url: '/api/quote/list'
		}
	});
});
