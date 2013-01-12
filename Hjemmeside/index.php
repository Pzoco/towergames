<?require_once 'includes/connection.php';require_once 'includes/functions.php';

?>
<? require_once 'includes/header.php'; ?><div id="content">	<?		$testCard = new cards("TestCard", 0, 10, "attack");	echo $testCard->getTitle();		?>	</div><? require_once 'includes/footer.php'; ?>