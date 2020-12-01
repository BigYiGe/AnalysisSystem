package analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class Analysis
{
    private static double Max;
    private static double Peak;
    private static double Root_mean_square;
    private static double Peak_factor;
    private static double Kurtosis;
    private static int Count;
    private static double Average;
    private static double pi = 3.1415926;
    static Scanner input =new Scanner(System.in);

    public  List<Double> Pretreatment() throws FileNotFoundException
    {
        List<Double> list = FileReader.GetInfo("E:\\JAVA\\ANALYSIS\\data.txt");
        double sum = 0;
        Count = list.size();
        for (int i = 0; i < Count; i++)
        {
            sum += list.get(i);
        }
        Average = sum / Count;
        for (int i = 0; i < Count; i++)
        {
            list.set(i, list.get(i) - Average);
        }
        Max = Math.abs(list.get(0));
        for (int i = 0; i < Count; i++)
        {
            if (Math.abs(list.get(i)) > Max)
                Max = Math.abs(list.get(i));
        }
        for (int i = 0; i < Count; i++)
        {
            list.set(i, list.get(i) / Max);
        }
        for (int i = 0; i < Count; i++)
        {
            Root_mean_square += Math.pow(list.get(i), 2);
        }
        Root_mean_square /= Count;
        Root_mean_square = Math.sqrt(Root_mean_square);
        for (int i = 0; i < Count; i++)
        {
            Kurtosis += Math.pow(list.get(i), 4);
        }
        Kurtosis = Kurtosis / (Count * Math.pow(Root_mean_square, 4));
        Peak = list.get(0);
        for (int i = 0; i < Count; i++)
        {
            if (Math.abs(list.get(i)) > Peak)
            {
                Peak = Math.abs(list.get(i));
            }
        }
        Peak_factor = Peak / Root_mean_square;
        return list;
    }

    //傅里叶变换
    public List<Double> DFT() throws FileNotFoundException
    {
        List<Double> list = this.Pretreatment();
        List<Double> Y = new ArrayList<>();
        double[] XI = new double[Count];
        double XQ[] = new double[Count];
        double PI[] = new double[Count];
        double w[] = new double[Count];

        int n, k;
        for (n = 0; n < Count; n++)
        {
            PI[n] = list.get(n);
            w[n] = 0.54 - 0.46 * Math.cos(2 * pi * n / (Count - 1));
            PI[n] = PI[n] * w[n];
        }
        for (k = 0; k < Count; k++)
        {
            for (n = 0; n < Count; n++)
            {
                XI[k] += PI[n] * Math.cos(2 * pi / Count * n * k);
                XQ[k] -= PI[n] * Math.sin(2 * pi / Count * n * k);
            }
            Y.add(Math.log10(XI[k] * XI[k] + XQ[k] * XQ[k]));
        }
    //    System.out.println(Y);
        return Y;
    }

    //倒变换

    public  double[] IDFT() throws FileNotFoundException
    {
        List<Double> list=DFT();
        double pI[] = new double[Count];
        double pq[] = new double[Count];
        int n, k;
        for (n = 0; n < Count; n++)
        {
            for (k = 0; k < Count; k++)
            {
                pI[n] += list.get(k) * Math.cos(2 * pi / Count * n * k) ;
                pq[n] += list.get(k) * Math.sin(2 * pi / Count * n * k);
            }
            pI[n] /= Count;
            pq[n] /= Count;
            pI[n] = Math.sqrt(pI[n] * pI[n] + pq[n] * pq[n]);


        }
        for(int i=0;i<Count;i++)
        {
            System.out.println(pI[i]*1000);
        }
        return pI;
    }

    /******************************************************************
     *　一维卷积函数
     *
     *　说明: 循环卷积,卷积结果的长度与输入信号的长度相同
     *
     *　输入参数: data[],输入信号; core[],卷积核; cov[],卷积结果;
     *			 n,输入信号长度; m,卷积核长度.
     *
     *　李承宇, lichengyu2345@126.com
     *
     *  2010-08-18
     ******************************************************************/

    public static void Covlution(double data[], double core[], double cov[], int n, int m)
    {
        int i = 0;
        int j = 0;
        int k = 0;

        //将cov[]清零
        for(i = 0; i < n; i++)
        {
            cov[i] = 0;
        }

        //前m/2+1行
        i = 0;
        for(j = 0; j < m/2; j++, i++)
        {
            for(k = m/2-j; k < m; k++ )
            {
                cov[i] += data[k-(m/2-j)] * core[k];//k针对core[k]
            }

            for(k = n-m/2+j; k < n; k++ )
            {
                cov[i] += data[k] * core[k-(n-m/2+j)];//k针对data[k]
            }
        }

        //中间的n-m行
        for( i = m/2; i <= (n-m)+m/2; i++)
        {
            for( j = 0; j < m; j++)
            {
                cov[i] += data[i-m/2+j] * core[j];
            }
        }

        //最后m/2-1行
        i = (n - m) + m/2 + 1;
        for(j = 1; j < m/2; j++, i++)
        {
            for(k = 0; k < j; k++)
            {
                cov[i] += data[k] * core[m-j-k];//k针对data[k]
            }

            for(k = 0; k < m-j; k++)
            {
                cov[i] += core[k] * data[n-(m-j)+k];//k针对core[k]
            }
        }

    }

    public static void DWT1D(double input[], double output[], double temp[], double h[], double g[], int n, int m)
    {
        int i = 0;
/*
	//尺度系数和小波系数放在一起
	Covlution(input, h, temp, n, m);

	for(i = 0; i < n; i += 2)
	{
		output[i] = temp[i];
	}

	Covlution(input, g, temp, n, m);

	for(i = 1; i < n; i += 2)
	{
		output[i] = temp[i];
	}
*/

        //尺度系数和小波系数分开
        Covlution(input, h, temp, n, m);

        for(i = 0; i < n; i += 2)
        {
            output[i/2] = temp[i];//尺度系数
        }

        Covlution(input, g, temp, n, m);

        for(i = 1; i < n; i += 2)
        {
            output[n/2+i/2] = temp[i];//小波系数
        }
    }

    public  double[] DWT() throws FileNotFoundException
    {
        int n=0;
        int m=6;
        int i=0;
        List<Double> list = Pretreatment();
        double[] data =new double[Count];//输入信号
        for(i=0;i<Count;i++)
        {
            data[i]=list.get(i);
        }
        double[] temp =new double[Count];//中间结果
        double[] data_output =new double[Count];//一维小波变换后的结果
        double[] h = {.332670552950, .806891509311, .459877502118, -.135011020010,
                -.085441273882, .035226291882};
        double[] g = {.035226291882, .085441273882, -.135011020010, -.459877502118,
                .806891509311, -.332670552950};
        DWT1D(data, data_output, temp, h, g, Count, m);
    //    Arrays.sort(data_output);
        try
        {
        //    String filename =input.nextLine();
        //    File writename = new File(filename); // 相对路径如果没有则要建立一个新的
        //    BufferedWriter out = new BufferedWriter(new FileWriter(writename));

            for(i = 0; i < Count; i++)
            {
                System.out.println(data_output[i]*1000);
        //        out.write(data_output[i] + "\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data_output;
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        /*Pretreatment();
        System.out.println(Root_mean_square);
        System.out.println(Max);
        System.out.println(Peak);
        System.out.println(Peak_factor);
        System.out.println(Kurtosis); */



     //   DWT();
    }
}
