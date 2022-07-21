var TesseractPluginDigits = {
    echo: function (language, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "TesseractPluginDigits", "echo", [language]);
    }
};
module.exports = TesseractPluginDigits;
