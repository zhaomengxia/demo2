package com.mls.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class UpdateReport {
    public static void main(String[] args) {
        UpdateReport up = new UpdateReport();
        //把word文档修改成xml文件，然后再对xml进行操作
        up.UpdateXmlFile("C:\\Users\\xing\\Desktop\\Jenkins后端部署流程.xml", "C:\\Users\\xing\\Desktop\\2.xml");
    }
    /**
     * 解释xml文档，并修改其内容
     * @param filename 模板文件
     * @param newfilename	输出目标文件
     * @return
     */
    public void UpdateXmlFile(String filename,String newfilename){
        //利用dom4j解释xml文件
        try{
            System.out.println(filename+"    11111111111");
            System.out.println(newfilename+"11111111111111111111111");
            SAXReader saxReader = new SAXReader();
            File file=new File(filename);
            FileInputStream fileInputStream=new FileInputStream(file);
            System.out.println(fileInputStream+"------------------------");
            Document document = saxReader.read(fileInputStream);
            System.out.println(document+"   1----------------------");



            System.out.println(document.selectNodes("//w-t")+"-----------------------------------");
            //替换内容

            List list = document.selectNodes("//w-t");
            Iterator iter = list.iterator();
            while(iter.hasNext()){
                Element element = (Element)iter.next();
                String str = element.getText();
                //将变量设成你需要更换的内容，这里把变量统一设成$xxx$格式
                if(str != null && "$rid$".equals(str.trim())) {
                    System.out.println(element.getText());
                    element.setText("LCZC10010");
                }
            }


            //把所有的图片内容含有\n字符的去掉，不知道什么原因，
            //不去掉就显示不出图片来
            List list1 = document.selectNodes("//w-binData" );
            Iterator iter1 = list1.iterator();
            while(iter1.hasNext()){
                Element element = (Element)iter1.next();
                String str = element.getText();
                str = str.replaceAll("\n", "");
                element.setText(str);
            }

            //利用dom4j输出document到文件上
            try{
                XMLWriter writer = null;
                /** 格式化输出 */
                //OutputFormat format = OutputFormat.createPrettyPrint();
                //OutputFormat format = OutputFormat.createCompactFormat();
                //OutputFormat format = new OutputFormat();
                //格式化各标签之间的间隔符，这里将其设置为空
                OutputFormat format  = new OutputFormat("");
                //格式化换行符，这里将其设成空
                format.setLineSeparator("");

                /** 指定XML编码，一定要设成UTF-8，否则如果有中文，就会导致文件打不开 */
                format.setEncoding("UTF-8");
                writer= new XMLWriter(new FileOutputStream(new File(newfilename)),format);
                writer.write(document);
                writer.flush();
                writer.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }

            //写出到文件的另一种方式，没有测试
	           Writer writer = new OutputStreamWriter(new FileOutputStream(newfilename));
	           document.write(writer);
	           writer.flush();
	           writer.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}