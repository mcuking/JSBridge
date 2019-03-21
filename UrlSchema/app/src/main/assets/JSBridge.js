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

    var iframe = document.createElement('iframe');
    iframe.src = url;
    iframe.style.width = 0;
    iframe.style.height = 0;
    document.body.appendChild(iframe);

    window.setTimeout(function() {
      document.body.removeChild(iframe);
    }, 800);
  }
};

module.exports = bridge;
