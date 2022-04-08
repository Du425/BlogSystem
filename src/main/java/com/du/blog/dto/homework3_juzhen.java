package com.du.blog.dto;

public class homework3_juzhen {
    public static void main(String[] args) {
        // TODO 自动生成的方法存根

        int [][]a={{1,2,3},{4,5,6}};
        int [][]b={{1,2,3},{4,5,6}};
//        add(a,b);
        String str = new String("");
        for (int i=0; i<2; i++ ){
            for (int j=0; j<1; j++){
                int[][] x = add(a,3,b,1);
                str = String.valueOf(x[i][j]);
                str+=str;
                str+=" ";
            }
            str+="\n";
        }
        System.out.println(str);
    }
    public static int[][] add(int[][] a, int length, int [][] b, int width)//实现矩阵加法
    {

        int [][]c=new int[length][width];
        for(int i=0;i<length-1;i++)
        {
            for(int j=0;j< width-1;j++)
            {
                c[i][j]=a[i][j]+b[i][j];
            }
        }
        return c;
    }

}

