var express = require('express');
var server = require('./server/index');
var app = express();
var appPort = process.env.PORT || 3000;

server.init(app);

app.use('/systemjs.config.js', express.static(__dirname + '/systemjs.config.js'));
app.use('/lib/', express.static(__dirname + '/node_modules/'));
app.use('/img/', express.static(__dirname + '/img/'));
app.use('/', express.static(__dirname + '/client/'));
//To force angular routing other
app.get('*', function(req, res) {
  res.sendfile(__dirname + '/client/index.html');
});

app.listen(appPort);
console.log('app started in ' + appPort);
