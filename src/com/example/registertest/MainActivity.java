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
		//����Ҫע��
		tVpsd=(EditText)findViewById(R.id.psd);
		tVpsd2=(EditText)findViewById(R.id.psd2);
		tVgender=(TextView)findViewById(R.id.tVgender);
		male=(RadioButton)findViewById(R.id.male);
		city=(EditText)findViewById(R.id.city);
		
		//ע�ᰴť���¼�����
		Btnregister.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//��֤�û�������Ϣ�Ľ��
				String checkResult=checkInfo ();
				//��������Ϊ�գ����öԻ�����ʾ
				if(checkResult!=null){
					Builder builder=new AlertDialog.Builder(MainActivity.this);
					//���öԻ������
					builder.setTitle("������ʾ");
					//���öԻ���������Ϣ
					builder.setMessage(checkResult);
					builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
						//Ϊ�Ի�����Ӱ�ť���¼�����
						public void onClick(DialogInterface dialog,int which){
						//�����������Ϊ��
						tVpsd.setText("");
						tVpsd2.setText("");
						
						}
					} );
					//�����Ի�����ʾ
					builder.create().show();
					//ע����Ϣ����Ҫ�󣬽����ݷ���Intent�����д���
				}else{
					Intent intent=new Intent(MainActivity.this,ResultActivity.class);
					intent.putExtra("name", tVname.getText().toString());
					intent.putExtra("psd",tVpsd.getText().toString());
					String gender=male.isChecked()?"��":"Ů";
					intent.putExtra("gender",gender);
					intent.putExtra("city",Btncity.getText().toString());
					//����һ���µ�Activity
					startActivity(intent);
					}
            }
		});
			//Ϊ���ڵذ�ť������紦��
		    Btncity.setOnClickListener(new OnClickListener(){
		    	 public void onClick(View v){
		    		 //������Ҫ������Activity��Intent
		    		 Intent intent=new Intent(MainActivity.this,ChooseCityActivity.class);
		    		 //����ָ��Acitivity���ȴ����صĽ��������0�������룬���ڱ�ʶ������
		    		 startActivityForResult(intent,0);
		    	 }
		    });
	}

		    //ʵ�ֻ�ȡѡ��ĳ�����Ϣ���������ڸ÷���Activity����дonActivityResult(intrequest-Code,int resultCode,Intent intent)����
		    public void onActivityResult(int requestCode,int resultCode,Intent intent){
		    	if(requestCode==0&&resultCode==0){
		    		//��requestCode,resultCodeͬʱΪ0��Ҳ���Ǵ����ض��Ľ��
		    		Bundle data=intent.getExtras();
		    		String resultCity=data.getString("city");
		    		city.setText(resultCity);
		    		
		    	}
		    }
		   
			//������֤�û���Ϣ����
			public String checkInfo(){
				System.out. println("name");
				if(tVname.getText().toString()==null||tVname.getText().toString().equals("")){
					System.out. println("8888888");
					return"�û�������Ϊ��";
				}//���û���������֤
				
				if(tVpsd.getText().toString().trim().length()<6||tVpsd.getText().toString().trim().length()>15){
					return"����λ��Ӧ����6~15֮��";
				}
				if(!tVpsd.getText().toString().equals(tVpsd2.getText().toString())){
					return"������������벻һ��";
					
				}
				return null;
			}

			
	
		
	}


	


