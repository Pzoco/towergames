<?

class attack extends cards{
		
	protected $_damage;	
	protected $_extra;
	
	//Constructor
	public function __construct($title, $rounds, $cost, $type="attack", $damage=null, $extra=null){
		parent:: __construct($title, $rounds, $cost, $type);
		$this->_damage = $damage;
		$this->_extra = $extra;
	}
}


?>