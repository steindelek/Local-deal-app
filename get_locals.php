<?php

require "first.php";

try {
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
//Try to connect and get data
    $stmt = $conn->query("SELECT id AS ID, NAME, CITY, ADDRESS, LONGITUDINAL, LATERAL, URL, JPG, ADDITIONAL_INFO 
	FROM Local 
	WHERE ACTIVE = 1");
	
	$locals = array();
		while(($result = $stmt->fetch(PDO::FETCH_NUM)) != null){
		array_push($locals, array("ID"=>$result[0],
								"NAME"=>$result[1],
								"CITY"=>$result[2],
								"ADDRESS"=>$result[3],
								"LONGITUDINAL"=>$result[4],
								"LATERAL"=>$result[5],
								"URL"=>$result[6],
								"JPG"=>$result[7],
								"ADDITIONAL_INFO"=>$result[8]));
	}
	echo json_encode($locals, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_ERROR_UTF8);
	$result = null;
	
	}catch(PDOException $e){
	echo $sql . "<br>" . $e->getMessage();
}
$conn = null;
?>