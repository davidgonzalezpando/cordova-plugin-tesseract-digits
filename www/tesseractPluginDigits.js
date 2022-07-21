var TesseractPluginDigits = {
    echo: function (message, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "TesseractPluginDigits", "echo", [message]);
    }
};
module.exports = TesseractPluginDigits;
