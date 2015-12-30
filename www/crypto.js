
var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');


function Crypto() {

    this.pluginName = "Crypto";

}

Crypto.prototype.encrypt = function(phrase, key, cb, opt) {
    exec(cb, function(err){cb(err);}, "Crypto", "AESencrypt", [phrase, key, opt]);
};

module.exports = new Crypto();


