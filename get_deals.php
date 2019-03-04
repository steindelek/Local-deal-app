<?php

require "first.php";

try {
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
//Try to connect and get data
    $stmt = $conn->query("SELECT Deal.id AS DEAL_ID, Deal.NAME AS DEAL_NAME, PRICE,
	DESCRIPTION, TERMS, TYPE, DAY, HOUR_START, HOUR_END, Deal.ADDITIONAL_INFO, POINTS, Deal.JPG,
	Local.id AS LOCAL_ID
	FROM Local, Deal, LocalDeal 
	WHERE Deal.id = LocalDeal.Deal_id AND Local.id = LocalDeal.Local_id AND Deal.ACTIVE = 1 AND Local.ACTIVE = 1");
	
	$deals = array();
		while(($result = $stmt->fetch(PDO::FETCH_NUM)) != null){
		array_push($deals, array("DEAL_ID"=>$result[0],
								"DEAL_NAME"=>$result[1],
								"PRICE"=>$result[2],
								"DESCRIPTION"=>$result[3],
								"TERMS"=>$result[4],
								"TYPE"=>$result[5],
								"DAY"=>$result[6],
								"HOUR_START"=>$result[7],
								"HOUR_END"=>$result[8],
								"ADDITIONAL_INFO"=>$result[9],
								"POINTS"=>$result[10],
								"JPG"=>$result[11],
								"LOCAL_ID"=>$result[12]));
	}
	echo json_encode($deals, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_ERROR_UTF8);
	$result = null;
	
	}catch(PDOException $e){
	echo $sql . "<br>" . $e->getMessage();
}
$conn = null;
?>