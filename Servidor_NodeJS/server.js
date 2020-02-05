var express = require('express');
var body_parser = require('body-parser');
var app = express();
var mysql = require('mysql');
var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database : 'IntercambioJS'
});
app.use(body_parser.urlencoded({ extended: false }));

app.get('/', function (req, res) {
  res.send('<h1>Servidor funcionando</h1>');
  // console.log("Página de inicio.");
});

app.post('/get', function (req, res) {
  // connection.connect();
  connection.query('SELECT * FROM ShoppingList', function(err, rows, fields)
  {
      // connection.end();
      if (err) throw err;
      res.json(rows);
  });
});

app.post('/delete', function (req, res) {
	var id = req.body.id || '';
	if(id != '') {
    var sql = 'DELETE FROM ShoppingList WHERE id = ?';
    connection.query(sql, id, (error, results, fields) => {
      if (error)
        return console.error(error.message);
      // console.log('Deleted Row(s):', results.affectedRows);
    });
    // connection.end();
  }
	// console.log(id);
  res.send("ok");
});

app.post('/add', function (req, res) {
	var name = req.body.name || '';
  var quantity = req.body.quantity || '';
  var amount = req.body.amount || '';
  var sql = "INSERT INTO ShoppingList (name, quantity, amount) VALUES ?";
  var values = [[name, quantity, amount]];
  connection.query(sql, [values], function (err, result) {
    if (err) throw err;
    // console.log("1 record inserted");
  });
  res.send("ok");
});

app.listen(8989);
