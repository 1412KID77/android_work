package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        inputText=(EditText) findViewById(R.id.input_text);
        send=(Button) findViewById(R.id.send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //LayoutManager用来指定RecyclerView的布局方式，layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)设置横向滚动，StaggeredLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL) 可以设置3列的瀑布排列
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                String content=inputText.getText().toString();
                if(!"".equals(content))
                {
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });
    }
    public void initMsg()//初始化信息
    {
        Msg msg1=new Msg("hellodfsdfasdfasdfasdfaasdf guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("kkkkkkkkkkkkkkkkkkkkadflkajsdlfkasjdflakjsdf;alkdjalskdfjlaskhgasjdhflkasjdhfalksjdhgaklsjdhfakjsdhfalkjsdhfalkjdhlkajshdkajhsdlfkjahsldkjfhaskjdfhaklsjdhflkasjhdfkljashfdkljashfkjashdfkjahskdjfhaslkjdfhaslkjdfa guy",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}