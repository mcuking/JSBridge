window.callbackId = 0;

var bridge = {
  call: function(method, arg, cb) {
    var args = {
      data: arg === undefined ? null : JSON.stringify(arg),
    };

    if (typeof cb === 'function') {
      var cbName = 'CALLBACK' + window.callbackId++;
      window[cbName] = cb;
      args['cbName'] = cbName;
    }

    var url = 'jsbridge://' + method + '?' + JSON.stringify(args);

    prompt(url);
  }
};

module.exports = bridge;
