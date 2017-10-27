# Cool Name Effect, Web, 50pts

## Problem

Webmaster developed a simple script to do cool effects on your name, but his code not filtering the inputs correctly execute javascript alert and prove it. 

## Solution

We are presented with a form to give the input text which after processing gives a circular pattern of the text. On experimenting with some inputs we find that the form is not able to filter the word `script` wherever it appears in the text. It is read as `[forbidden]`. On checking the source, we find an obfuscated piece of javascript code. On deobfuscating it using an [online deobfuscator](http://deobfuscatejavascript.com/), we find an interesting piece of code,

```javascript
(function(j, w) {
    var legacyAlert = alert;
    var newAlert = function() {
            var z = ['y', 'o', 'u', 'r', ' ', 'f', 'l', 'a', 'g', ' ', 'i', 's', ':'];
            var f = ([]["fill"] + "")[3];
            f += ([false] + undefined)[10];
            f += (NaN + [Infinity])[10];
            f += (NaN + [Infinity])[10];
            f += (+(211))["to" + String["name"]](31)[1];
            f += ([]["entries"]() + "")[3];
            f += (+(35))["to" + String["name"]](36);
            legacyAlert(z.join('') + f)
        };
    w.alert = newAlert;
    w.prompt = newAlert;
    w.confirm = newAlert;
    console.log(newAlert);
    j(function() {
        $x = j('#name');
        $x.arctext({
            radius: 400
        });
        $x.arctext('set', {
            radius: 140,
            dir: 1
        })
    })
})(jQuery, window);
```

So we download the webpage, replace the javascript with the clean one and add two lines in the javascript indicated by a `->`,

```javascript
(function(j, w) {
    var legacyAlert = alert;
    var newAlert = function() {
            var z = ['y', 'o', 'u', 'r', ' ', 'f', 'l', 'a', 'g', ' ', 'i', 's', ':'];
            var f = ([]["fill"] + "")[3];
            f += ([false] + undefined)[10];
            f += (NaN + [Infinity])[10];
            f += (NaN + [Infinity])[10];
            f += (+(211))["to" + String["name"]](31)[1];
            f += ([]["entries"]() + "")[3];
            f += (+(35))["to" + String["name"]](36);
            legacyAlert(z.join('') + f)
	 -> console.log(legacyAlert);
        };
 -> newAlert();
    w.alert = newAlert;
    w.prompt = newAlert;
    w.confirm = newAlert;
    console.log(newAlert);
    j(function() {
        $x = j('#name');
        $x.arctext({
            radius: 400
        });
        $x.arctext('set', {
            radius: 140,
            dir: 1
        })
    })
})(jQuery, window);
```

On opening the HTML file in a browser we are presented with an alert-  
`your flag is: ciyypjz`.
