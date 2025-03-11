var exec = require('cordova/exec');

exports.init = function(arg0, success, error) {
    exec(success, error, 'RfidSdkMasterPlugin', 'init', [arg0]);
};

exports.startRfidRead = function(success, error) {
    exec(success, error, 'RfidSdkMasterPlugin', 'startRfidRead');
}

exports.stopRfidRead = function(success, error) {
    exec(success, error, 'RfidSdkMasterPlugin', 'stopRfidRead');
}
