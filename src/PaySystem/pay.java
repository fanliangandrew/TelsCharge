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
		content="����ʱ�䣺 "+df.format(new Date())+"\r\n�û�Id�� 	"+cusID+"\r\n֧����ʽ��	 "+payWay+"\r\n"+payWay+"���˺�Ϊ��"+account+
				"\r\n֧����	 "+money+"\r\n֧��״̬�� 	"+getpayState()+"\r\n���Ϊ 		"+balance;
	}
	
	public void WriteLog(){
		filename="E:/�������£�/�������/PayList/"+cusID+".txt";	//��ʷ�ļ���¼������ʷ
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
