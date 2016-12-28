var express = require('express');
var server = require('./server/index');
var app = express();
var appPort = process.env.PORT || 3000;

server.init(app);

app.use('/', express.static(__dirname + '/client/'));
app.use('/lib', express.static(__dirname + '/node_modules/'));
app.use('/img', express.static(__dirname + '/img/'));

app.listen(appPort);
console.log('app started in ' + appPort);
