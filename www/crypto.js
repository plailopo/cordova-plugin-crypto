
var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

channel.createSticky('onCordovaInfoReady');
// Tell cordova channel to wait on the CordovaInfoReady event
channel.waitForInitialization('onCordovaInfoReady');


function Crypto() {

    this.pluginName = "Crypto";

	channel.onCordovaReady.subscribe(function() {

    });
}

Crypto.prototype.encrypt = function(phrase, key, cb, opt) {
    exec(cb, function(err){cb(err);}, "Crypto", "AESencrypt", [phrase, key, opt]);
};

module.exports = new Crypto();


