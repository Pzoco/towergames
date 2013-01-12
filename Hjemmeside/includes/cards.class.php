<?
require_once 'cardTypes/attack.class.php';
require_once 'cardTypes/defense.class.php';
require_once 'cardTypes/misc.class.php';
require_once 'cardTypes/resource.class.php';

class cards {
	//Properties

	protected $_ID;
	protected $_title;
	protected $_rounds;
	protected $_cost;
	protected $_type;
	
	
	//Constructor
	public function __construct($title, $rounds, $cost, $type){
		$this->_title = $title;
		$this->_type = $type;
		$this->_rounds = $rounds;
		$this->_cost = (int) $cost;
		
		//Creates a new row in the database
		$query = "INSERT INTO cards (title, type, rounds, cost) VALUES ('$this->_title', '$this->_type', $this->_rounds, $this->_cost)";
		$result = mysql_query($query) or die ("Error in inserting card!");
		$this->_ID = mysql_insert_id();	
	}
	public function getTitle(){
		return $this->_title;
	}
	
	public function getID(){
		return $this->_ID;
	}
	
	public function getRounds(){
		return $this->_rounds;
	}
	
	public function getCost(){
		return $this->_cost;
	}
	
	public function getType(){
		return $this->_type;
	}	
}
?>