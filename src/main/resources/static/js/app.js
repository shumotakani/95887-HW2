'use strict'

var app = angular.module('app', [
    'ui.router',
    'ngResource',
    'app.services',
    'app.controllers'

]);

app.config(function($urlRouterProvider, $stateProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state("quote", {
            url: "/",
            templateUrl : "templates/quote.html",
            controller: "RandomQuoteCtrl"
        })
        .state("list", {
            url: "/list",
            templateUrl : "templates/list.html",
            controller: "ListQuoteCtrl"
        })
        .state("add", {
            url: "/add",
            templateUrl : "templates/add.html",
            controller: "SaveQuoteCtrl"
        });
});