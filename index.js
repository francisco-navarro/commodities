var express = require('express');
var path = require('path');

var app = express();
var appPort = process.env.PORT || 3000;

app.use('/', express.static(__dirname + '/src/'));
app.use('/lib', express.static(__dirname + '/node_modules/'));
app.use('/img', express.static(__dirname + '/img/'));


app.listen(appPort);
console.log('app started in ' + appPort);
