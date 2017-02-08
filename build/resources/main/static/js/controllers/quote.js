'use strict'

angular.module('app.controllers', [])

.controller('RandomQuoteCtrl', function($scope, QuoteService) {
	QuoteService.random()
		.$promise.then(function(quote) {
			$scope.quote = quote;
		});
})
.controller('ListQuoteCtrl', function($scope, ListQuote, $stateParams) {
	$scope.quotes = ListQuote.list({authorName: $stateParams.authorName});
})
.controller('SaveQuoteCtrl', function($scope, $state, QuoteService) {
    $scope.saveQuote = function() {
        QuoteService.save(
            $scope.quote,
            function(response) {
                $state.go("quote", {});
            },
            function(err) {
                console.log(err);
            }
        );
    };
});