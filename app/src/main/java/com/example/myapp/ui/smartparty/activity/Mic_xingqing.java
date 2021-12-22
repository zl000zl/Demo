package com.example.myapp.ui.smartparty.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;

public class Mic_xingqing extends BaseActivity {

    private TextView micTitle;
    private TextView micContent;
    private EditText micEt;
    private Button micPl;
    private ImageView micMore;

    private String[] title = {"条块联动，“四化”绘制党务工作新景象"};
    private String[] content = {"党建平台分为PC端、APP端和党建门户三大块，每一块都有不同条目的功能应用项，主要条块之间相互关联，功能的充分应用，为党务工作顺应时代潮流发展绘制出新景象。\n" +
            "一是党员学习实现“随身化”。石油党建APP与党组织的政治优势充分结合，似丰富多样的“自助餐”，每名党员可以根据自己的不同需求摄取“精神食粮”，在这里，党员不仅可以汲取最新党政动态、时事政策、上级精神等党务知识“营养”，还可以吸收党务工作技能提升“能量”，亦能从“电子书屋”中拓展视野、博闻强识，还可通过“在线答题”、“专家问答”随时随地自主“加餐”，党员“碎片化”时间的合理利用，助推了党员教育的“随身化”。\n" +
            "二是支部生活日益“规范化”。为避免党建线上生活“落而不实、抓而不紧、行而不严”，油田推广应用小组将线上指导、线下督导、制度规范、宣传导向、活动激励、阶段评价有机结合，最大限度地调动党员干部参与度，激发创先争优热情。二级党组织落实党建责任制，确定“三会一课”“时间表”、“任务书”，对党费缴纳实行“清单式”督查，并将落实情况纳入党建目标管理考核。宣传的辐射、活动的激励、压力的传导等形成抓党建合力，促进党建平台工作规范有效落实。\n" +
            "三是网上审批凸显“便捷化”。运用党建信息平台，层层审批，优化组织关系接转流程，实时掌握党员队伍流动情况，党员转入、转出或有历史变动时，系统自动发送短信提醒支部管理员。目前，198名党员组织关系接转经平台办理、全程无纸化，形成579条历史管理信息，有效加强了对党员队伍流动情况的实时掌握；支部线上生活请假和审批功能，方便各级党组织及时了解、掌握党员参与组织活动的情况，有针对性的进行提醒和管理，基本实现“党员少走路”目标，提高了工作效率。\n" +
            "四是过程监管渐显“精准化”。油田推广应用小组通过党建信息平台各个管理模块的有机衔接、融合，在一定程度上实现了对“党员参与平台应用情况、党员动态和组织建设情况、‘三会一课’开展频次、个人和支部党费缴纳及时性”等进行监测和管理，方便基层支部工作留痕与油田党工委及二级党组织了解、掌握基层支部工作动态，及时发现党建工作中存在的各种问题，亦便于发现优秀典型，提升了工作指导的针对性和实效性，“抓两边促中间”，进而推动油田党建工作整体水平的提高。\n" +
            "伴随党建平台功能应用深度和广度的拓展，“随身化、规范化、便捷化、精准化”特性凸显，为党务工作的严谨务实高效开展起到了催化剂作用，描绘出大党建、新格局景象。\n"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic_xingqing);
        setTitle("文章详情");
        initView();
        initData();
        initSp();
        initClick();
    }

    private void initClick() {
       micMore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(Mic_xingqing.this,Party_comment.class));
           }
       });
    }

    private void initSp() {
        micPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               KongActivity.comment.add(micEt.getText().toString().trim());
               micEt.setText("");
                Toast.makeText(Mic_xingqing.this, "评论成功！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        micTitle.setText("" + title[0]);
        micContent.setText("内容： " + content[0]);
    }

    private void initView() {
        micTitle = (TextView) findViewById(R.id.mic_title);
        micContent = (TextView) findViewById(R.id.mic_content);
        micEt = (EditText) findViewById(R.id.mic_et);
        micMore = (ImageView) findViewById(R.id.mic_more);
        micPl = (Button) findViewById(R.id.mic_pl);
    }
}