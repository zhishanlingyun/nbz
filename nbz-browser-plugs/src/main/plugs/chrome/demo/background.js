function show(msg) {

    new Notification('通知: '+msg, {
        icon: 'avatar1.png',
        body: '数据已保存'
    });
}


// Conditionally initialize the options.
if (!localStorage.isInitialized) {
    localStorage.isActivated = true;   // The display activation.
    localStorage.frequency = 1;        // The display frequency, in minutes.
    localStorage.isInitialized = true; // The option initialization.
}

// Test for notification support.
//var msg =null;
var cks = [];
if (window.Notification) {
    // While activated, show notifications at the display frequency.
    if (JSON.parse(localStorage.isActivated)) { show('one'); }

    var interval = 0; // The display interval, in minutes.
    chrome.commands.onCommand.addListener(function(command) {
        console.log('onclike')
        chrome.cookies.getAll({name:'BDUSS',domain:".baidu.com"},function (cookies) {
            console.log(cookies);
            var ckjson = JSON.stringify(cookies[0]);
            var ck = JSON.parse(ckjson);
            console.log(ck.value);
            chrome.cookies.set(
                {
                    url:'https://image.baidu.com',
                    name:'KKKNEWBDUSS',
                    domain:ck.domain,
                    value:ck.value
                    //path:ck.path,
                },function (cookie) {
                    console.log(chrome.extension.lastError);
                    console.log(chrome.runtime.lastError);
                });
        });
        show('ok');

    });

    /*setInterval(function() {
        interval++;

        if (
            JSON.parse(localStorage.isActivated) &&
            localStorage.frequency <= interval
        ) {
            show('one+');
            interval = 0;
        }
    }, 1000);*/
}

