var express = require('express');
var bodyParser = require('body-parser');
var app = express();

var serverPort = process.env.NODEJS_PORT || 3000;
var serverIpAddress = process.env.NODEJS_IP || '127.0.0.1';

app.use(bodyParser.json());
app.listen(serverPort, serverIpAddress, function() {
  console.log('App started listening on port ' + serverPort);
});