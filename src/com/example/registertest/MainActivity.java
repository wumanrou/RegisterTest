package com.example.registertest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
  
public class MainActivity extends Activity {
	private Button Btnregister,Btncity;
	private TextView tVname,tVpsd,tVpsd2,tVgender;
	private RadioButton male;
	private EditText city,psd,psd2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Btncity=(Button)findViewById(R.id.Btncity);
		Btnregister=(Button)findViewById(R.id.Btnregister);
		tVname=(TextView)findViewById(R.id.name);
		//这里要注意
		tVpsd=(EditText)findViewById(R.id.psd);
		tVpsd2=(EditText)findViewById(R.id.psd2);
		tVgender=(TextView)findViewById(R.id.tVgender);
		male=(RadioButton)findViewById(R.id.male);
		city=(EditText)findViewById(R.id.city);
		
		//注册按钮的事件处理
		Btnregister.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//验证用户输入信息的结果
				String checkResult=checkInfo ();
				//如果结果不为空，则用对话框提示
				if(checkResult!=null){
					Builder builder=new AlertDialog.Builder(MainActivity.this);
					//设置对话框标题
					builder.setTitle("出错提示");
					//设置对话框内容信息
					builder.setMessage(checkResult);
					builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						//为对话框添加按钮及事件处理
						public void onClick(DialogInterface dialog,int which){
						//将密码框设置为空
						tVpsd.setText("");
						tVpsd2.setText("");
						
						}
					} );
					//创建对话框并显示
					builder.create().show();
					//注册信息符合要求，将数据放入Intent，进行传递
				}else{
					Intent intent=new Intent(MainActivity.this,ResultActivity.class);
					intent.putExtra("name", tVname.getText().toString());
					intent.putExtra("psd",tVpsd.getText().toString());
					String gender=male.isChecked()?"男":"女";
					intent.putExtra("gender",gender);
					intent.putExtra("city",Btncity.getText().toString());
					//启动一个新的Activity
					startActivity(intent);
					}
            }
		});
			//为所在地按钮添加世界处理
		    Btncity.setOnClickListener(new OnClickListener(){
		    	 public void onClick(View v){
		    		 //创建需要启动的Activity的Intent
		    		 Intent intent=new Intent(MainActivity.this,ChooseCityActivity.class);
		    		 //启动指定Acitivity并等待返回的结果，其中0是请求码，用于标识该请求
		    		 startActivityForResult(intent,0);
		    	 }
		    });
	}

		    //实现获取选择的城市信息，还必须在该方法Activity中重写onActivityResult(intrequest-Code,int resultCode,Intent intent)方法
		    public void onActivityResult(int requestCode,int resultCode,Intent intent){
		    	if(requestCode==0&&resultCode==0){
		    		//当requestCode,resultCode同时为0，也就是处理特定的结果
		    		Bundle data=intent.getExtras();
		    		String resultCity=data.getString("city");
		    		city.setText(resultCity);
		    		
		    	}
		    }
		   
			//调用验证用户信息方法
			public String checkInfo(){
				System.out. println("name");
				if(tVname.getText().toString()==null||tVname.getText().toString().equals("")){
					System.out. println("8888888");
					return"用户名不能为空";
				}//对用户名进行验证
				
				if(tVpsd.getText().toString().trim().length()<6||tVpsd.getText().toString().trim().length()>15){
					return"密码位数应该在6~15之间";
				}
				if(!tVpsd.getText().toString().equals(tVpsd2.getText().toString())){
					return"两次输入的密码不一致";
					
				}
				return null;
			}

			
	
		
	}


	


