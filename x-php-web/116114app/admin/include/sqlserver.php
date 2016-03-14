<?php

/**
*  sql server class
*	
*/
class sqlserver
{
	
	private $db_host = "";
	private $db_user = "";
	private $db_pwd = "";
	private $db_name = "";
	private $db ;
	private $db_char = "";


	function __construct(){
		$this->db_host = "127.0.0.1";
		$this->db_user = "sa";
		$this->db_pwd = "huisen@8920";
		$this->db_name = "hc";
		$this->db_char = "GB2312";
	}

	public function connent(){
		
		$con_info = array(
			"Database" => $this->db_name, 
			"UID" => $this->db_user, 
			"PWD" => $this->db_pwd
		);

		$con = sqlsrv_connect($this->db_host, $con_info);
		

		if ($con == false)
			die( print_r( sqlsrv_errors(), true));
		
		$this->db = $con;
		return $con;
	}

	public function close($con){
		sqlsrv_close($this->db);
	}

	public function query($sql){

		$query = sqlsrv_query($this->db,$sql);

		if ($query == false)
			die( print_r(sqlsrv_errors(), true));
	
		return $query;
	}

	
	/**
     * 查询所数据，$sql/语句 $isChars/是否转换编码 $setChars 需要转换的编码
     * @param $value 数组值
     */
	public function FetchAll($sql,$isChars = true, $setChars = "UTF-8"){

		$query = $this->query($sql);

		while($row = sqlsrv_fetch_array($query,SQLSRV_FETCH_ASSOC)){
			$rst[] = $row;
		}

		if($isChars)
			array_walk($rst, array($this, 'charset'), $setChars);
		
		return $rst;
	}
	
	//查询一条记录
	public function FetchOne($sql,$isChars = true, $setChars = "UTF-8"){

		$query = $this->query($sql);

		$rst = sqlsrv_fetch_array($query,SQLSRV_FETCH_ASSOC);

		if($isChars)
			array_walk($rst, array($this, 'charset'), $setChars);
		
		return $rst;
	}
	

	public function insert($table, $data, $return_id = false) {

        if (!is_array($data) || $table == '' || count($data) == 0) {
            return "function error";
        }

        $fielddata = array_keys($data);
        $valuedata = array_values($data);
        array_walk($fielddata, array($this, 'add_special_char'));
        array_walk($valuedata, array($this, 'escape_string'));

        $field = implode(',', $fielddata);
        $value = implode(',', $valuedata);

        $sql = 'INSERT INTO ' . $table . ' (' . $field . ') VALUES (' . $value . ')';
        
		/*
        if($return_id)
			$sql = 'INSERT INTO ' . $table . ' (' . $field . ') VALUES (' . $value . ') SELECT LAST_INSERT_ID=@@IDENTITY';
		*/
		
		
       	$query = $this->query($sql);
		
		if($return_id && $query){
       		$sql = "SELECT @@IDENTITY AS 'id'";
			$rst = $this->FetchOne($sql);
			return $rst['id'];
		}else{
			return $query ? true : false;
		}        
    }

	public function upDate($table, $values, $where, $limit = FALSE) {
        foreach ($values as $key => $val) {
            $valstr[] = $key . ' = \'' . $val . '\'';
        }

        if (is_array($where))
            $where = $this->sqls($where);
			
        $where = $where == '' ? '' : ' WHERE ' . $where;

        $limit = (!$limit) ? '' : ' LIMIT ' . $limit;

        $sql = "UPDATE " . $table . " SET " . implode(', ', $valstr);
		
        $sql .= $where . $limit;
        $rst = $this->query($sql);

        if ($rst) {
            return true;
        } else {
            return false;
        }
    }

    //删除记录
    public function delete($table, $where) {
        if ($table == '' || $where == '') {
            return false;
        }
        $where = ' WHERE ' . $where;
        $sql = 'DELETE FROM `' . $table . '`' . $where;
        return $this->query($sql);
    }
	
	
	
	/**
     * where 数组转换成字符串形式
     *
     * @param array $where
     * @param string $font
     * @return string
     */
    final public function sqls($where, $font = ' AND ') {
        if (is_array($where)) {
            $sql = '';
            foreach ($where as $key => $val) {
                $sql .= $sql ? " $font `$key` = '$val' " : " `$key` = '$val'";
            }
            return $sql;
        } else {
            return $where;
        }
    }


	/**
     * 对字段自动转换编码，$val传入数组 $key数组key $setChars 数据编码
     * @param $value 数组值
     */
	private function charset(&$val,$key,$setChars){
		switch (gettype($val)) {
			case 'array':
				array_walk($val, array($this, 'charset'), $setChars);
				break;
			case 'object':
				$val = (array)$val;
				if($val['date'])
					$val = $val['date'];
				break;
			default:
				$val = iconv($this->db_char, "UTF-8", $val);
				break;
		}
	}


	 /**
     * 对字段两边加反引号，以保证数据库安全
     * @param $value 数组值
     */
    private function add_special_char(&$value) {
        if ('*' == $value || false !== strpos($value, '(') || false !== strpos($value, '.') || false !== strpos($value, '`')) {
            //不处理包含* 或者 使用了sql方法。
        } else {
            $value = trim($value);
        }
        return $value;
    }

    /**
     * 对字段值两边加引号，以保证数据库安全
     * @param $value 数组值
     * @param $key 数组key
     * @param $quotation 
     */
    private function escape_string(&$value, $key = '', $quotation = 1) {
        if ($quotation) {
            $q = '\'';
        } else {
            $q = '';
        }
        $value = $q . $value . $q;
        return $value;
    }



}

?>