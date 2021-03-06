package com.example.carrie.carrie_test1;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class FourthActivity extends AppCompatActivity {
    String id;
    String indi;
    String eng;
    String license;
    String cate;
    String compon;
    String maker_Coun;
    String appli;
    String maker_Nam;
    String ima;
    android.support.design.widget.CoordinatorLayout coordinatorLayout;

    private int drugid;
    private String drugname;
    private FloatingActionButton addnmcal;
    private ArrayList<ArrayList<String>> mydrugs = new ArrayList<ArrayList<String>>();
    private String m_calid;
    private static float MAX_TEXT_SIZE = 20;


    //String chineseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("drug","1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.addlayout);
        Log.d("drug","2");
        Bundle bundle5=getIntent().getExtras();
        m_calid = bundle5.getString("m_calid",m_calid);
//        Log.d("qqqqq123",m_calid);

        if (m_calid.equals("-1")){//如果直接從搜尋add符號隱藏
            coordinatorLayout.setVisibility(View.GONE);
        }


        addnmcal = (FloatingActionButton)findViewById(R.id.addmcal);
        inserttomcal();
       // setContentView(R.layout.activity_fourth);
       // Log.d("chart","2");
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        drugid = bundle.getInt("id");
        Log.d("drugooo", String.valueOf(drugid));


        id = bundle.getString("chineseName");//get 中文名字
        drugname=id;
        Log.d("drug","4");
        TextView chineseName=(TextView) findViewById(R.id.chineseName3);
        Log.d("drug","5");
        String string = getIntent().getExtras().getString("chineseName", "not found");
        chineseName.setText(string);

        String string1= getIntent().getExtras().getString("indication", "not found");
        indi = bundle.getString("indication");//get 中文名字
        TextView indication=(TextView) findViewById(R.id.indication);
        indication.setText(string1);
    //    indi = autoSplitText(indication);
   //     indication.setText(indi);

        String string2= getIntent().getExtras().getString("englishName", "not found");
        eng = bundle.getString("englishName");//get 中文名字
        TextView englishName=(TextView) findViewById(R.id.englishName3);
        englishName.setText(string2);

        String string3= getIntent().getExtras().getString("licenseNumber", "not found");
        license = bundle.getString("licenseNumber");//get 中文名字
        TextView licenseNumber=(TextView) findViewById(R.id.licenseNumber);
        licenseNumber.setText(string3);

        String string4= getIntent().getExtras().getString("category", "not found");
        cate = bundle.getString("category");//get 中文名字
        TextView category=(TextView) findViewById(R.id.category);
        category.setText(string4);
        Log.d("drug","6");

        String string5= getIntent().getExtras().getString("component", "not found");
        compon = bundle.getString("component");//get 中文名字
        TextView component=(TextView) findViewById(R.id.component);
        component.setText(string5);
        Log.d("drug","8");


        String string6= getIntent().getExtras().getString("maker_Country", "not found");
        maker_Coun = bundle.getString("maker_Country");//get 中文名字
        TextView maker_Country=(TextView) findViewById(R.id.maker_Country);
        maker_Country.setText(string6);

        String string7= getIntent().getExtras().getString("applicant", "not found");
        appli = bundle.getString("applicant");//get 中文名字
        TextView applicant=(TextView) findViewById(R.id.applicant);
        applicant.setText(string7);

        String string8= getIntent().getExtras().getString("maker_Name", "not found");
        maker_Nam = bundle.getString("maker_Name");//get 中文名字
        TextView maker_Name=(TextView) findViewById(R.id.maker_Name);
        maker_Name.setText(string8);


        String string9= getIntent().getExtras().getString("image", "not found");
        ima = bundle.getString("image");//get 中文名字
        ImageView image=(ImageView) findViewById(R.id.image3);
        Glide.with(getBaseContext()).load(string9).into(image);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Log.d("drug","7");
//

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String member_id = memberdata.getMember_id();
        String google_id= memberdata.getGoogle_id();
        String m_id=memberdata.getMy_mon_id();
        Intent i = new Intent(getApplicationContext(), druginfo.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("my_id", member_id);
        i.putExtra("my_google_id", google_id);
        i.putExtra("my_supervise_id", m_id);
        i.putExtra("m_calid",m_calid);
        startActivity(i);
        finish();
    }


    //
//    将textview中的文字进行排版
    private String autoSplitText(final TextView tv) {
        final String rawText = tv.getText().toString(); //原始文本
        final Paint tvPaint = tv.getPaint(); //paint，包含字体等信息
        final float tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight(); //控件可用宽度

        //将原始文本按行拆分
        String [] rawTextLines = rawText.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //如果整行宽度在控件可用宽度之内，就不处理了
                sbNewText.append(rawTextLine);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
                    lineWidth += tvPaint.measureText(String.valueOf(ch));
                    if (lineWidth <= tvWidth) {
                        sbNewText.append(ch);
                    } else {
                        sbNewText.append("\n");
                        lineWidth = 0;
                        --cnt;
                    }
                }
            }
            sbNewText.append("\n");
        }

        //把结尾多余的\n去掉
        if (!rawText.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }
        return sbNewText.toString();
    }


    public void inserttomcal(){
        addnmcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!m_calid.equals(null) && !m_calid.equals("0")){
                    Log.d("drug","100");
                    Intent it =new Intent(FourthActivity.this,medicine_cal.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("drugid", String.valueOf(drugid));
                    bundle1.putString("chineseName",drugname);
                    bundle1.putInt("entertype",1);
                    bundle1.putString("m_calid",m_calid);
                    it.putExtras(bundle1);
                    startActivity(it);
                }else {
                    //                int i= mcaldata.getMcaldrugs().size();
//                Log.d("drugarray",String.valueOf(i));
//                mdrugs = mcaldata.getMcaldrugs();
//                Log.d("drug","100");
//                mdrugs.add(new ArrayList<String>());
//                Log.d("drug","200");
//                mdrugs.get(i).add(drugname);
//                Log.d("drug","300");
//                mdrugs.get(i).add(String.valueOf(drugid));
//                Log.d("drug","400");
//                mcaldata.setMcaldrugs(mdrugs);
                    mydrugs.add(new ArrayList<String>());
                    mydrugs.get(0).add(drugname);
                    mydrugs.get(0).add(String.valueOf(drugid));
                    Log.d("drug","500");
                    Intent it =new Intent(FourthActivity.this,ThirdActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("drugid", String.valueOf(drugid));
                    bundle1.putString("chineseName",drugname);
                    bundle1.putInt("entertype",1);
                    it.putExtras(bundle1);
                    startActivity(it);
                }


            }
        });
    }


    public void goback(View v){
        finish();
    }


}


