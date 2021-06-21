package com.quail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author quail
 */
public class ReplaceDemo {
    public static void main(String[] args) {
        // 遍历文件夹
        String dirName = "D:\\quail\\jerry\\phoenix";
        File dirs = new File(dirName);
        readDir(dirs);
    }

    public static void readDir(File dirs){
        if (dirs.isDirectory()){
            for (File dir : dirs.listFiles()) {
                readDir(dir);
            }
        }else{
            // file
            // javafile
            try {
                if(dirs.getName().lastIndexOf(".java")!=-1) {
                    handleFile(dirs);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static void handleFile(File file) throws IOException {
        // 读取文件，存储到StringBuilder
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        // 记录行数
        int num = 0;
        while ((s=br.readLine()) != null){
            ++num;
            if(s.split("//").length != 1
            || s.split("/\\*\\*").length != 1
            || s.split(" \\* ").length != 1){
                continue;//一行中有 // 此行被注释,跳过
            }
            // 记录类名
            String className = "";
            if (s.split("public class").length != 1) {
                className = s;
            }
            // 打印中文出处
            if(num==1) System.out.println("所在包："+s);
            for (char c : s.toCharArray()) {
                if ((c >= 0x4e00)&&(c <= 0x9fbb)){
                    // 中文
                    System.out.println("----------------------------------");
                    System.out.println("文件名:"+file.getName());
                    System.out.println("行数："+num);
                    System.out.println(s);
                    System.out.println("----------------------------------");
                    Scanner scanner = new Scanner(System.in);
                    int i = scanner.nextInt();
                    inputIf(i,scanner);
                    break;
                }
            }
        }
        br.close();
        // 遍历每个字符，排查符号、字母
        // 问题字符设置暂停   
    }

    public static void inputIf(int i,Scanner scanner){
        if(i != 0){
            System.out.println("重新输入:");
            int i1 = scanner.nextInt();
            inputIf(i1,scanner);
        }
    }
}
