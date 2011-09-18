/* This source has been formatted by an unregistered SourceFormatX */
/* If you want to remove this info, please register this shareware */
/* Please visit http://www.textrush.com to get more information    */

import java.io.InputStreamReader;import java.io.PrintStream;import 
java.util.Random;import javax.microedition.lcdui.*;import 
javax.microedition.midlet.MIDlet;import javax.microedition.rms.RecordStore;
public class senior7000 extends MIDlet implements CommandListener{String 
progTitle;private Display display;RecordStore rs;byte byte_temp[];public int 
sum_AC;public int sum_WA;easyFunctions EasyFunctions;int combo;int max_combo;
private List mainList;private List menu_list_page;private List 
show_last_ques_page;private List show_record_page;private Form about_page;
private Command cmd_exit;private Command cmd_about;private Command cmd_showlast
;private Command cmd_record;private Command cmd_menu;private Command 
cmd_getQues;private Command cmd_yes;private Command cmd_no;int ques_MAX_size;
int size_ques;public StringBuffer ques[];public StringBuffer ans[];public 
StringBuffer level[];private Alert alert_AC;private Alert alert_WA;private 
Alert alert_clear;private Alert alert_are_you_sure;private int usednums[];
public Random Rnum;public int Qnum;public int num;public int Q_ch;public int 
list_selet;boolean first_use;boolean last;//上一題AC or WA
boolean show_last_AC;int Ques_range;int index_last_ques;int num_last_to_show;
private int last_ques[];private boolean last_ques_AC[];boolean show_alert_WA;
boolean notshow_alert_AC;boolean QeAc;boolean thisWA;//本題是否已經WA
public senior7000(){progTitle="單字隨堂考";byte_temp=null;sum_AC=0;sum_WA=0;
level=new StringBuffer[1001];level[0]=new StringBuffer("村民");level[10]=new 
StringBuffer("初心");level[20]=new StringBuffer("入門");level[30]=new 
StringBuffer("熟練");level[35]=new StringBuffer("熟練+");level[40]=new 
StringBuffer("熟練++");level[50]=new StringBuffer("中上");level[55]=new 
StringBuffer("中上+");level[60]=new StringBuffer("中上++");level[70]=new 
StringBuffer("強者");level[75]=new StringBuffer("強者+");level[80]=new 
StringBuffer("強者++");level[90]=new StringBuffer("神手");level[95]=new 
StringBuffer("神手+");level[99]=new StringBuffer("神手++");level[100]=new 
StringBuffer("悟道");level[120]=new StringBuffer("悟道+");level[130]=new 
StringBuffer("悟道++");level[150]=new StringBuffer("登峰造極");level[170]=new 
StringBuffer("浪跡天涯");level[190]=new StringBuffer("神");level[200]=new 
StringBuffer("大神");level[250]=new StringBuffer("帝");level[300]=new 
StringBuffer("混沌");level[400]=new StringBuffer("芎窘");level[1000]=new 
StringBuffer("空");EasyFunctions=new easyFunctions();combo=0;max_combo=0;
mainList=new List(progTitle,3);menu_list_page=new List(progTitle,3);
show_last_ques_page=new List(progTitle,3);show_record_page=new List(progTitle,3
);about_page=new Form(progTitle);cmd_showlast=new Command("答題紀錄",7,0);
cmd_record=new Command("成就",7,1);cmd_menu=new Command("設定",7,2);cmd_about=
new Command("關於",7,3);cmd_exit=new Command("結束",7,4);cmd_getQues=new 
Command("返回",2,1);cmd_yes=new Command("確定",Command.OK,0);cmd_no=new Command
("取消",Command.CANCEL,0);ques_MAX_size=10000;size_ques=0;ques=new StringBuffer
[ques_MAX_size];ans=new StringBuffer[ques_MAX_size];alert_AC=new Alert("","AC",
null,AlertType.CONFIRMATION);alert_WA=new Alert("","  WA..",null,
AlertType.ERROR);alert_clear=new Alert("","  已歸零",null,AlertType.INFO);
alert_are_you_sure=new Alert("","確定執行？",null,AlertType.CONFIRMATION);
alert_are_you_sure.setTimeout(Alert.FOREVER);alert_are_you_sure.addCommand(
cmd_yes);alert_are_you_sure.addCommand(cmd_no);usednums=new int[5];Rnum=new 
Random();Q_ch=0;first_use=true;last=true;show_last_AC=true;Ques_range=2;
index_last_ques=0;num_last_to_show=1000;last_ques=new int[1001];last_ques_AC=
new boolean[1001];show_alert_WA=true;notshow_alert_AC=false;QeAc=true;
//問英選中或相反
menu_list_page.addCommand(cmd_getQues);about_page.addCommand(cmd_getQues);
mainList.addCommand(cmd_showlast);mainList.addCommand(cmd_record);
mainList.addCommand(cmd_menu);mainList.addCommand(cmd_about);
mainList.addCommand(cmd_exit);mainList.setTitle(progTitle);
show_last_ques_page.addCommand(cmd_getQues);show_record_page.addCommand(
cmd_getQues);thisWA=false;for(int i=0;i<1000;++i)last_ques_AC[i]=true;ReadFile(
);read_all_rms();display=Display.getDisplay(this);mainList.setCommandListener(
this);mainList.setFitPolicy(Choice.TEXT_WRAP_ON);menu_list_page.setFitPolicy(
Choice.TEXT_WRAP_ON);show_last_ques_page.setFitPolicy(Choice.TEXT_WRAP_ON);
show_record_page.setFitPolicy(Choice.TEXT_WRAP_ON);alert_AC.setTimeout(700);
alert_WA.setTimeout(700);if(size_ques>3)getQues(ques,ans);}private void 
read_all_rms(){int j=1;try{rs=RecordStore.openRecordStore("senior7000",true);}
catch(Exception exception){System.out.println("Rs open error");}sum_AC=
ReadRecord(j++);sum_WA=ReadRecord(j++);max_combo=ReadRecord(j++);show_last_AC=
EasyFunctions.IntToBool(ReadRecord(j++));show_alert_WA=EasyFunctions.IntToBool(
ReadRecord(j++));notshow_alert_AC=EasyFunctions.IntToBool(ReadRecord(j++));
Ques_range=ReadRecord(j++);QeAc=EasyFunctions.IntToBool(ReadRecord(j++));}
private void menu(){menu_list_page.deleteAll();if(Ques_range==0)
menu_list_page.append("範圍:4000",null);else if(Ques_range==1)
menu_list_page.append("範圍:4000~7000(*)",null);else if(Ques_range==2)
menu_list_page.append("範圍:7000",null);if(show_last_AC)menu_list_page.append(
"答題記錄:答題",null);else menu_list_page.append("答題記錄:錯題",null);if(
show_alert_WA)menu_list_page.append("WA告示:開",null);else 
menu_list_page.append("WA告示:關",null);if(notshow_alert_AC)//邏輯相反
menu_list_page.append("AC告示:關",null);else menu_list_page.append("AC告示:開",
null);if(QeAc)menu_list_page.append("問英選中",null);else menu_list_page.append
("問中選英",null);menu_list_page.append("紀錄歸零",null);
menu_list_page.setCommandListener(this);display.setCurrent(menu_list_page);}
private void about(){about_page.deleteAll();about_page.append("單字數約: "+
size_ques+"\n版本1.652\n製作: LFking (FHCRC 98th)\n"+
"程式官網:\nhttp://ppt.cc/93Be");about_page.setCommandListener(this);
display.setCurrent(about_page);}private void show_last(){
show_last_ques_page.deleteAll();show_last_ques_page.setCommandListener(this);if
(show_last_AC)show_last_ques_page.append("過去所有答題(不存檔):",null);else 
show_last_ques_page.append("過去錯誤答題(不存檔):",null);for(int i=
index_last_ques-1;i>=0;i--)if(last_ques_AC[i]){if(show_last_AC){
show_last_ques_page.append("O:"+ques[last_ques[i]].toString(),null);
show_last_ques_page.append(ans[last_ques[i]].toString(),null);}}else{
show_last_ques_page.append("X:"+ques[last_ques[i]].toString(),null);
show_last_ques_page.append(ans[last_ques[i]].toString(),null);}
display.setCurrent(show_last_ques_page);}private void show_record(){
show_record_page.deleteAll();show_record_page.setCommandListener(this);if(
max_combo>0)show_record_page.append("等級："+level[(max_combo/10)*10],null);
show_record_page.append("最高Combo: "+max_combo,null);show_record_page.append(
"AC次數: "+sum_AC,null);show_record_page.append("答題數: "+(sum_WA+sum_AC),null
);display.setCurrent(show_record_page);}private void deleteRecord(){try{
rs.closeRecordStore();RecordStore.deleteRecordStore("senior7000");}catch(
Exception exception){System.out.println(exception);}}private int ReadRecord(
int i){try{byte_temp=rs.getRecord(i);}catch(Exception exception){try{byte_temp=
(new byte[]{48});rs.addRecord(byte_temp,0,byte_temp.length);}catch(Exception 
exception1){System.out.println(exception1);}}int j=0;int k=byte_temp.length-1;
for(int l=0;k>=0;++l){j+=(byte_temp[k]-48)*EasyFunctions.pow(10,l);k--;}return 
j;}private void saveRecord(int i,int j){Integer integer=new Integer(j);
byte_temp=integer.toString().getBytes();try{rs.setRecord(i,byte_temp,0,
byte_temp.length);}catch(Exception exception){System.out.println(exception);}}
public void getQues(StringBuffer astringbuffer[],StringBuffer astringbuffer1[])
{if(QeAc)//中英 或英出中
processQues(astringbuffer,astringbuffer1);else processQues(astringbuffer1,
astringbuffer);}public void processQues(StringBuffer astringbuffer[],
StringBuffer astringbuffer1[]){last=true;thisWA=false;mainList.deleteAll();Qnum
=Rnum.nextInt(size_ques);if(QeAc){if(Ques_range==0)for(;astringbuffer[Qnum]
.toString().charAt(0)=='*';Qnum=Rnum.nextInt(size_ques));else if(Ques_range==1)
for(;astringbuffer[Qnum].toString().charAt(0)!='*';Qnum=Rnum.nextInt(size_ques)
);}else if(Ques_range==0)for(;astringbuffer1[Qnum].toString().charAt(0)=='*';
Qnum=Rnum.nextInt(size_ques));else if(Ques_range==1)for(;astringbuffer1[Qnum]
.toString().charAt(0)!='*';Qnum=Rnum.nextInt(size_ques));
//	Ques_range 2不用處理 直接出題
mainList.append("Q: "+astringbuffer[Qnum].toString(),null);Q_ch=Rnum.nextInt(4)
+1;for(int i=1;i<5;++i)usednums[i]=-1;usednums[1]=Qnum;for(int j=1;j<5;++j){if(
j!=Q_ch){for(num=Rnum.nextInt(size_ques);UsedNum(num)==1||num==Qnum;num=
Rnum.nextInt(size_ques));usednums[j]=num;if(!QeAc&&astringbuffer1[num].charAt(0
)=='*')astringbuffer1[num].deleteCharAt(0);mainList.append(astringbuffer1[num]
.toString(),null);continue;}if(!QeAc&&astringbuffer1[Qnum].charAt(0)=='*')
astringbuffer1[Qnum].deleteCharAt(0);mainList.append(astringbuffer1[Qnum]
.toString(),null);}Font font=Font.getFont(0,Font.STYLE_BOLD,Font.SIZE_LARGE);
mainList.setFont(0,font);mainList.setSelectedIndex(1,true);if(first_use){
display.setCurrent(mainList);first_use=false;}}private void ReadFile(){try{
InputStreamReader inputstreamreader=new InputStreamReader(getClass()
.getResourceAsStream("data.txt"),"UTF-8");ques[0]=new StringBuffer();ans[0]=
new StringBuffer();inputstreamreader.read();byte byte0=1;int j=0;int i;do{i=
inputstreamreader.read();if(i==32)byte0=2;else if(i>200)ans[j].append((char)i);
else if(i>=33&&i<=126){if(byte0==1)ques[j].append((char)i);}else if(i==10)
//enter code
{++j;byte0=1;ques[j]=new StringBuffer();ans[j]=new StringBuffer();}}while(i>-1)
;size_ques=j;}catch(Exception exception){System.out.println("File Read Error:"+
exception.toString());}}private int UsedNum(int i){for(int j=1;j<5;j++){if(
usednums[j]!=-1){if(i==usednums[j]||ans[i].toString().equals(ans[usednums[j]]
.toString())//避免重複出題
||ques[i].toString().equals(ques[usednums[j]].toString()))return 1;}}
/*出過的題目不再出*/if(index_last_ques+5>=size_ques)index_last_ques=0;
//全部出完了
for(int k=0;k<index_last_ques;++k)if(last_ques[k]==i)return 1;return 0;}public 
void pauseApp(){}public void startApp(){}public void destroyApp(boolean flag){
int i=1,j=0;saveRecord(i++,sum_AC);saveRecord(i++,sum_WA);saveRecord(i++,
max_combo);saveRecord(i++,EasyFunctions.BoolToInt(show_last_AC));saveRecord(i++
,EasyFunctions.BoolToInt(show_alert_WA));saveRecord(i++,EasyFunctions.BoolToInt
(notshow_alert_AC));saveRecord(i++,Ques_range);saveRecord(i++,
EasyFunctions.BoolToInt(QeAc));try{rs.closeRecordStore();}catch(Exception 
exception){System.out.println("close RS error");}notifyDestroyed();}public 
void commandAction(Command command,Displayable displayable){if(displayable==
mainList){if(command==List.SELECT_COMMAND){if(mainList.getSelectedIndex()==0)
return;if(Q_ch==mainList.getSelectedIndex()){if(index_last_ques==
num_last_to_show)index_last_ques=0;last_ques[index_last_ques++]=Qnum;if(last){
sum_AC++;combo++;if(combo>max_combo)max_combo=combo;if(combo>1)
alert_AC.setString("Combo x "+combo+" !!");else alert_AC.setString("AC!");if(!
notshow_alert_AC)display.setCurrent(alert_AC,mainList);}getQues(ques,ans);}else
{if(thisWA==false){sum_WA++;thisWA=true;}last=false;combo=0;last_ques_AC[
index_last_ques]=last;if(show_alert_WA)display.setCurrent(alert_WA,mainList);}}
}else if(displayable==menu_list_page&&command==List.SELECT_COMMAND){list_selet=
menu_list_page.getSelectedIndex();int i=0;if(list_selet==i++){Ques_range++;if(
Ques_range>2)Ques_range=0;menu();getQues(ques,ans);combo=0;}else if(list_selet
==i++){if(show_last_AC)show_last_AC=false;else show_last_AC=true;menu();}else 
if(list_selet==i++){if(show_alert_WA)show_alert_WA=false;else show_alert_WA=
true;menu();}else if(list_selet==i++){if(notshow_alert_AC)notshow_alert_AC=
false;else notshow_alert_AC=true;menu();}else if(list_selet==i++){if(QeAc)QeAc=
false;else QeAc=true;menu();getQues(ques,ans);combo=0;}else if(list_selet==i++)
{display.setCurrent(alert_are_you_sure,menu_list_page);
alert_are_you_sure.setCommandListener(this);//確認是否刪除
}menu_list_page.setSelectedIndex(list_selet,true);}else if(displayable==
alert_are_you_sure){if(command==cmd_yes){deleteRecord();read_all_rms();menu();
combo=0;menu_list_page.setCommandListener(this);display.setCurrent(alert_clear,
menu_list_page);}else if(command==cmd_no){menu_list_page.setCommandListener(
this);display.setCurrent(menu_list_page);}}if(command==cmd_menu)menu();else if(
command==cmd_exit){destroyApp(false);notifyDestroyed();}else if(command==
cmd_about)about();else if(command==cmd_getQues)display.setCurrent(mainList);
else if(command==cmd_showlast)show_last();else if(command==cmd_record)
show_record();}}class easyFunctions{easyFunctions(){}public int BoolToInt(
boolean flag){return!flag?0:1;}public boolean IntToBool(int i){return i==1;}
public int pow(int i,int j){int k=1;for(int l=0;l<j;++l)k*=i;return k;}}
