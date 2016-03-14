<?php

class solution extends Controller {
	
	public function __construct() {
		parent::__construct();
	}
	
	//电子政务系统
	public function index() {
	
		$data['test'] = ""; 
		$this->showTemplate('solution', $data); 
	}
	
	
	//医院网站系统
	public function hospital(){
	
		$data['test'] = ""; 
		$this->showTemplate('solution-hospital', $data); 
	}
	
	
	//集团网站群
	public function group(){
	
		$data['test'] = ""; 
		$this->showTemplate('solution-group', $data); 
	}
	
	
	//企业品牌网站
	public function brand(){
	
		$data['test'] = ""; 
		$this->showTemplate('solution-brand', $data); 
	}
	
	
	//企业外贸网站
	public function trade(){
	
		$data['test'] = ""; 
		$this->showTemplate('solution-trade', $data); 
	}
	
	
	//企业营销网站
	public function marketing(){
	
		$data['test'] = ""; 
		$this->showTemplate('solution-marketing', $data); 
	}
	
	
	//电商运营
	public function mall(){
	
		$data['test'] = ""; 
		$this->showTemplate('solution-mall', $data); 
	}
	
	
}

