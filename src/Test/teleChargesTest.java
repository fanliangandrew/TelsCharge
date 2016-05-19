package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import PaySystem.pay;
import TeleCharges.telecharges;

public class teleChargesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jxl.Workbook read = null;
		long phonenum;
		int time;
		int delay;
		float lastyear;
		float money;
		boolean state=true;
		String mMsg;
		String payWay;
		InputStream instream = null;
		try {
			instream = new FileInputStream(new File("E:/大三（下）/软件测试/用例测试.xls"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			read=Workbook.getWorkbook(instream);
			Sheet sheet=read.getSheet(0);	//获取该Excel的第一个sheet
			int rows=sheet.getRows();	//获取总行数
			System.out.println(rows);
			//获取文件的输出流
			WritableWorkbook wwb=Workbook.createWorkbook(new File("E:/大三（下）/软件测试/用例测试结果1.xls"),read);
			WritableSheet ws=wwb.getSheet(0);
			
			//获取单元格的对象引用
			for(int i=1;i<rows;i++){
				Cell cell0=sheet.getCell(0,i);
				Cell cell1=sheet.getCell(1,i);
				Cell cell2=sheet.getCell(2,i);
				Cell cell3=sheet.getCell(3, i);
				Cell cell4=sheet.getCell(4,i);
				phonenum=Long.parseLong(cell0.getContents());
				time=Integer.parseInt(cell1.getContents());
				delay=Integer.parseInt(cell2.getContents());
				lastyear=Float.parseFloat(cell3.getContents());
				payWay=(String)cell4.getContents();

				System.out.println(time+" "+delay+" "+lastyear+payWay);
				
				telecharges mtelecharges=new telecharges(time,delay,lastyear);
				money=mtelecharges.getMoney();
				if(mtelecharges.getMsg().equals(" ")){
					mMsg=String.valueOf(money);
					state=true;
				}else{
					mMsg=mtelecharges.getMsg();
					state=false;
				}
		
				pay mPay=new pay(phonenum,payWay,i,money,state);
//				System.out.println(mMsg);
//				wc=ws.getWritableCell(2,i);
//				if(wc.getType()==CellType.LABEL){
//					Label l=(Label)wc;
//					l.setString(mMsg);
//				}
				
				Label lable=new Label(6,i,mMsg);
				ws.addCell(lable);
				
			}
			wwb.write();
			wwb.close();	
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			read.close();
			
		}
			
	}

}
