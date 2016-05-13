package TeleCharges;

import java.util.Scanner;

public class telecharges {

	private int basicmoney=25;	//基础金额
	private float permin=(float) 0.15;
	private int calltime = 0;		//通话时间
	private float disrate=1;		//折扣成原来的倍数（0~1）
	private int delaynum =0; 		//0~11，实际未缴费次数
	private float lastyeardelay=0;//上一年的Delay
	private float money=0;		//结果金额，用户截止查询时，需要缴纳的费用
	
	private String msg=" ";			//用以监督过程中的错误事件
	
	public telecharges(int time,int delay,float lastyear){
		this.calltime=time;
		this.delaynum=delay;
		this.lastyeardelay=lastyear;
		this.setDisrate();
		if(msg==" "){
			this.setMoney();
		}
	}
	
	public void setCalltime(int _calltime){
		this.calltime=_calltime;
	}
	public void setDelaynum(int _delaynum){
		this.delaynum=_delaynum;
	}
	public void setMsg(String _msg){
		this.msg=_msg;
	}
	public String getMsg(){
		return msg;
	}
	//设置是否折扣及折扣的值
	public void setDisrate(){
		int time=this.calltime;
		int delay=this.delaynum;
		if(delay>11 || delay<0){
			this.disrate=1;
			this.setMsg("Delay Nummber between 0~11");
			return;
		}
		if(time<0){
			this.disrate=1;
			this.setMsg("Call Time should be meaningful");
			return;
		}
		if(time<=60){
			if(delay<=1) {this.disrate=(float) 0.99;return;}
					else return;
		}else if(time<=120){
			if(delay<=2) {this.disrate=(float) 0.985;return;}
					else return;
		}else if(time<=180){
			if(delay<=3) {this.disrate=(float) 0.98;return;}
					else return;
		}else if(time<=300){
			if(delay<=2) {this.disrate=(float) 0.975;return;}
					else return;
		}else {
			if(delay<=6) {this.disrate=(float) 0.97;return;}
					else return;
		}
	}
	
	public void setMoney(){
		money+=basicmoney;
		money=(float) (money+permin*disrate*calltime+lastyeardelay*0.05);
	}
	public float getMoney(){
		return money;
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Scanner sc = new Scanner(System.in);
//		int time=sc.nextInt();
//		int delay=sc.nextInt();
//		float lastyear=sc.nextFloat();
//		telecharges m_telecharges=new telecharges(time,delay,lastyear);
//		float money=m_telecharges.getMoney();
//		System.out.println(money);
//	}

}
