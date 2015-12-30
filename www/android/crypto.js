
var utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    channel = require('cordova/channel');


var Crypto = function() {
};

Crypto.AES.encrypt = function(phrase, key, cb, opt) {
    exec(cb, function(err){cb(err);}, "Crypto", "AESencrypt", [phrase, key, opt]);
};

channel.onCordovaReady.subscribe(function() {
    exec(success, null, 'Crypto', 'init', []);

    function success(msg) {
        
    }
});

module.exports = Crypto;



