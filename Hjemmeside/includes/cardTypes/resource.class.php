<?

class resource extends cards{
	
	protected $_increment;	
	protected $_instantGold;
	protected $_extra;
	
	//Constructor
	public function __construct($title, $rounds, $cost, $type="resource", $increment=null, $instantGold=null, $extra=null){
		parent:: __construct($title, $rounds, $cost, $type);
		$this->_increment = $increment;
		$this->_instantGold = $instantGold;
		$this->_extra = $extra;
	}
}


?>