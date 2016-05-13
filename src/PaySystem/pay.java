package PaySystem;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class pay {

	private int cusID;
	private int account;
	private String payWay;
	private float balance = 0;
	private float money;
	private String content;
	private String payState;
	private String filename;
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private boolean state;
	public pay(int _id,String _payway,int _account,float _money,boolean _state){
		cusID=_id;
		payWay=_payway;
		account=_account;
		if(balance==0){
			balance = this.getBalance();
		}
		money=_money;
		state=_state;
		doPay();
		WriteLog();
	}
	
	public String getpayState(){
		payState=" Fail";
		if ((payWay.equals("Alipay") || payWay.equals("Bankcard")) && state==true && balance>money){
			payState=" Success";
			balance-=money;
		}
		return payState;
		
	}
	public void doPay(){
		content="交易时间： "+df.format(new Date())+"\r\n用户Id： 	"+cusID+"\r\n支付方式：	 "+payWay+"\r\n"+payWay+"的账号为："+account+
				"\r\n支付金额：	 "+money+"\r\n支付状态： 	"+getpayState()+"\r\n余额为 		"+balance;
	}
	
	public void WriteLog(){
		filename="E:/大三（下）/软件测试/PayList/"+cusID+".txt";	//历史文件记录消费历史
		try{
			FileWriter fw=new FileWriter(filename);	
			fw.write(content);
			fw.flush();
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public float getBalance(){
		float x=(float)(80+Math.random()*(10));
		return x;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
