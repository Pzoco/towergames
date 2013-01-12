<?

class defense extends cards{
	
	protected $_tower;
	protected $_extra;
	
	//Constructor
	public function __construct($title, $rounds, $cost, $type="defense", $tower=null, $extra=null){
		parent:: __construct($title, $rounds, $cost, $type);
		$this->_tower = $tower;
		$this->_extra = $extra;
	}
	
	
	
}


?>