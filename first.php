<?php

$databasename = '28781462_localdeals';
$username = '28781462_localdeals';
$password = 'P0fwpR4wpe';
$servername = 'sql.serwer1863695.home.pl';

try {
	$conn = new PDO("mysql:host=$servername;dbname=$databasename", $username, $password, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    //echo "Connected successfully"; 
    }
catch(PDOException $e)
    {
    echo "Connection failed: ";// . $e->getMessage();
    }
?>