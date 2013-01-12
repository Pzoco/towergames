<?

class misc extends cards{
	
	protected $_extra;
	
	//Constructor
	public function __construct($title, $rounds, $cost, $type="misc", $extra=null){
		parent:: __construct($title, $rounds, $cost, $type);
		$this->_extra = $extra;
	}
}


?>