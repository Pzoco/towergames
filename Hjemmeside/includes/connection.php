<?php 
require("constants.php");

	//oprette en forbindelse til mysql:
	$connection = mysql_connect(DB_SERVER,DB_USER,DB_PASS);
	//hvis forbindelsen ikke kan oprettes, så kom med en fejlbesked, og luk forbindselsen:
	if (!$connection) {//"!" betyder "ikke", altså "hvis der IKKE kunne læses noget til variablen, så luk forbindelsen:
		die("Databasen er ude af drift:" .mysql_error());
	}
	
	//vælg hvilken database der skal oprettes forbindelse til:
	$db_select = mysql_select_db(DB_NAME,$connection);//<-- $connection er ikke nødvendig!
	//hvis forbindelsen ikke kan oprettes, så kom med en fejlbesked, og luk forbindselsen:
	if (!$db_select){//"!" betyder "ikke", altså "hvis der IKKE kunne læses noget til variablen, så luk forbindelsen:
		die("Databasen er ude af drift:" . mysql_error());
	}
?>
