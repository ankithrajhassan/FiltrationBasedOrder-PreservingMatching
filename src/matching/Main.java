package matching;

//public class Main {

//}


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;




public class Main {
	
	public static int x = 0;
	public static int text_size = 1000000;
	public static int pattern_size;
	//public static int digits_in_pattern = 4;
	public static int upper_limit_of_text = 2^30;
	
	public static int no_of_patterns = 1000;
	public static int[] t = new int[text_size];
	

	public static int[] radixSort(int[] a) {
		 
		int bit = 32;
		int digit = 4;
        int[] temp = null;
        for (int j = 0 ; j < bit/digit ; j++) {
            int c[] = new int[1 << digit];
            // the next three for loops implement counting-sort
            temp = new int[a.length];
            for (int i = 0 ; i < a.length ; i++)
                c[(a[i] >> digit * j) & ((1 << digit)-1)]++;
            for (int i = 1 ; i < 1 << digit ; i++)
                c[i] += c[i-1];
            for (int i = a.length-1 ; i >= 0 ; i--)
                temp[--c[(a[i] >> digit * j) & (( 1<<digit)-1)]] = a[i];
            a = temp;
        }
        return temp;
    }
	
	public static int[] convertToBit(int[] p)
	{
		int n=p.length;
		int[] b=new int[n-1];
		for(int i=0;i<n-1;i++)
		{
			if(p[i]<p[i+1])
				b[i]=1;
			else
				b[i]=0;
		}
		return b;
	}
	
	/*
	public static int randomNumber()
    {
    	Random r=new Random();
    	int n=r.nextInt(9999);
    	return n;
    }
    */
	
	/**
	 * Pre processes the pattern array based on proper prefixes and proper
	 * suffixes at every position of the array
	 *
	 * @param ptrn
	 *            word that is to be searched in the search string
	 * @return partial match table which indicates
	 */
	public static int[] preProcessPattern(int[] ptrn) {
	    int i = 0, j = -1;
	    int ptrnLen = ptrn.length;
	    int[] b = new int[ptrnLen + 1];
	 
	    b[i] = j;
	    while (i < ptrnLen) {            
	            while (j >= 0 && ptrn[i] != ptrn[j]) {
	            // if there is mismatch consider the next widest border
	            // The borders to be examined are obtained in decreasing order from 
	            //  the values b[i], b[b[i]] etc.
	            j = b[j];
	        }
	        i++;
	        j++;
	        b[i] = j;
	    }
	    return b;
	}
	/**
     * Based on the pre processed array, search for the pattern in the text
     *
     * @param text
     *            text over which search happens
     * @param ptrn
     *            pattern that is to be searched
     */
    public static int[] KMPsearchSubString(int[] text, int[] ptrn) {
        int i = 0, j = 0;
        // pattern and text lengths
        int ptrnLen = ptrn.length;
        int txtLen = text.length;
        
        int res_arr[]=new int[txtLen+1]; 
 
        // initialize new array and preprocess the pattern
        int[] b = preProcessPattern(ptrn);
 
        while (i < txtLen) {
            while (j >= 0 && text[i] != ptrn[j]) {
                j = b[j];
            }
            i++;
            j++;
 
            // Match found
            if (j == ptrnLen) {
                res_arr[x] = (i - ptrnLen);
                x++;
                j = b[j];
            }
        }
        
        return res_arr;
    }
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{

		
		//int t[] = {22, 85, 79, 24, 42, 27, 62, 40, 32, 47, 69, 55, 25, 220, 85, 79, 24, 42, 27, 62, 40, 32, 47, 69, 55, 25}; // Text input
		//int p[] = {10, 22, 15, 30, 20, 18, 27}; // Pattern
		//int t[] = {10,8,2,6,8,7,11,1,9,8};
		//int p[] = {11,14,13};
		int q;
		double st,et,rt;
		st=System.currentTimeMillis();
		
		for(int i=0;i<text_size;i++)
		{
			Random r1=new Random();
			Random r2=new Random();
			Random r3=new Random();
			long n1=r1.nextInt(upper_limit_of_text );
			long n2=r2.nextInt(upper_limit_of_text );
			long n3=r3.nextInt(upper_limit_of_text );
			
			long n = (n1 * n2 * n3) % upper_limit_of_text ;
			t[i]=(int) n;
		}
		
		st=System.currentTimeMillis();
		
		for(q = 0 ; q < 7 ; q++)
				
		{
			double run_time_arr[] = new double[no_of_patterns];
			
			switch(q)
			{
			case 0 : pattern_size = 5; break;
			case 1 : pattern_size = 8; break;
			case 2 : pattern_size = 10; break;
			case 3 : pattern_size = 15; break;
			case 4 : pattern_size = 20; break;
			case 5 : pattern_size = 30; break;
			case 6 : pattern_size = 50; break;
			default : pattern_size = 5; break;
			
			}
			
			for(int s = 0 ; s < no_of_patterns ; s++)
			{	
			
				//int t[]=new int[text_size];
				int p[]=new int[pattern_size];
		
				int number;
				
				
				
			/*
				for(int i=0;i<text_size;i++)
				{
					Random r1=new Random();
					Random r2=new Random();
					Random r3=new Random();
					long n1=r1.nextInt(upper_limit_of_text );
					long n2=r2.nextInt(upper_limit_of_text );
					long n3=r3.nextInt(upper_limit_of_text );
					
					long n = (n1 * n2 * n3) % upper_limit_of_text ;
					t[i]=(int) n;
				}
			*/

				Random rppick = new Random();
				int ppick = rppick.nextInt(text_size - pattern_size);
				
				for (int i=0 ; i < pattern_size ; i++)
				{
					p[i]=t[ppick++];
				}
				
				/*
				for (int i=0;i<pattern_size;i++)
				{
					
					Random r1=new Random();
					Random r2=new Random();
					Random r3=new Random();
					int n1=r1.nextInt(number);
					int n2=r2.nextInt(number);
					int n3=r3.nextInt(number);	    	
					int n = (n1 * n2 * n3) % number;
					
					
					Random r=new Random();
					int n=r.nextInt(number);
					p[i]=n;
				}
				
					
				*/
		
				int tb[], pb[];
				
				double startTime,endTime,runTime;
				startTime=System.currentTimeMillis();
				
				/************** Filtration **************/
				
		//	Convert to Binary
				
				tb = convertToBit(t);
				pb = convertToBit(p);
				
		// 	Call a matching algorith for pattern matching (KMP)

				int[] res_arr = KMPsearchSubString(tb, pb);
		
				//System.out.println("No. of Matchings: " + x);
		
		/*
		for(int k = 0 ; k < x ; k++)
		{
			//System.out.println("Substring found at position : " + res_arr[k]);
			for(int l = 0 ; l<p.length ; l++)
			{
				System.out.print(t[res_arr[k]+l] + " ");
			}
			System.out.println();
		}
		*/
		
		/************** Verification ***************/
		
		// Sorting the pattern
		
				int ptrn[] = new int[p.length];
		
		//	System.out.println("Pattern before is: ");
				for(int l = 0 ; l<p.length ; l++)
				{
					ptrn[l] = p[l];
			//	System.out.print(ptrn[l] + " ");
				}
		//	System.out.println();
		
				int[] sort_ptrn = radixSort(ptrn);
		/*
			System.out.println("Pattern after is: ");
			for(int l = 0 ; l<p.length ; l++)
			{
				System.out.print(p[l] + " ");
			}
			System.out.println();
		 */
		
		/*
			System.out.println("Sorted Pattern is: ");
			for(int l = 0 ; l<sort_ptrn.length ; l++)
			{
				System.out.print(sort_ptrn[l] + " ");
			}
		 */
				
		// 	Create Equality Vector
				int[] E = new int[sort_ptrn.length];
				int ec = 0;
				
				for(int l = 0 ; l<sort_ptrn.length-1 ; l++)
				{
					if(sort_ptrn[l] == sort_ptrn[l+1])
					{
						E[ec] = 1;
						ec++;
					}
					else
					{
						E[ec] = 0;
						ec++;
					}
				}
				
				/*
			System.out.println();
			System.out.println("Vector E is: ");
			for(int l = 0 ; l<E.length ; l++)
			{
				System.out.print(E[l] + "  ");
			}
				 */
		
		
		// 	Create r vector
				
				int[] temp = p;
				int[] r = new int[sort_ptrn.length];
				
				for(int l = 0 ; l<sort_ptrn.length ; l++)
				{
					int num = sort_ptrn[l];
					for(int m = 0 ; m < temp.length ; m++)
					{
						if(temp[m] == num)
						{
							r[l] = m;
							temp[m] = -1;
							break;
						}
						
					}
				}
				
				/*
			System.out.println();
			System.out.println("Vector r is: ");
			for(int l = 0 ; l<r.length ; l++)
			{
				System.out.print(r[l] + "  ");
			}
				 */
				
		// 	Condition Checking
				
				/*
			System.out.println();
			System.out.println("Before Verification matching indices are: ");
			for(int l = 0 ; l<x ; l++)
			{
				System.out.print(res_arr[l] + " ");
			}
				 */
				
		
				for(int i = 0 ; i < x ; i++)
				{
					for(int j = 0 ; j < p.length - 1 ; j++)
					{
						if((t[res_arr[i] + r[j]] >  t[res_arr[i] + r[j+1]]) ||
								(t[res_arr[i] + r[j]] == t[res_arr[i] + r[j+1]] && E[j] == 0) ||
								(t[res_arr[i] + r[j]] <  t[res_arr[i] + r[j+1]] && E[j] == 1))
						{
							res_arr[i] = -1;
							break;
						}
					}
					
				}
				
				
				/*
			System.out.println();
			System.out.println("After Verification matching indices are: ");
			for(int l = 0 ; l<x ; l++)
			{
				System.out.print(res_arr[l] + " ");
			}
				 */
		
		/*
		 * 		int count = 0;
		 
				
				System.out.println();
				System.out.println("Count before for pattern size " + pattern_size + ": "+ x);
				for(int l = 0 ; l<x ; l++)
				{
					if(res_arr[l] != -1)
						count++;
					
				}
				System.out.println("Count After for pattern size " + pattern_size + ": "+ count);
		*/		
				endTime = System.currentTimeMillis();
				runTime = endTime - startTime;
				
				run_time_arr[s] = runTime;
				
				//System.out.println("Runtime for pattern size " + pattern_size + ": "+ runTime);
				x=0;
				
			} // Inner Loop - No. of Patterns - 1000 Times
			
			double sum_runtime = 0;
			double avg_runtime = 0;
			for(int e = 0 ; e < no_of_patterns ; e++)
			{
				sum_runtime = sum_runtime + run_time_arr[e];
			}
			avg_runtime = sum_runtime/no_of_patterns;
			
			System.out.println("Average Runtime for pattern size " + pattern_size + ": "+ avg_runtime + "ms");
					
		} // Outer Loop - 7 Times - Patterns of Variable length
		
			

			/*
			for(int i = 0 ; i < res_arr.length ; i++)
			{
				System.out.println("Pattern Match At :" + res_arr[i] );
				//System.out.print('('+' ');
				for(int j = 0 ; j < p.length ; j++)
				{
					System.out.print(t[res_arr[i]+j]);
					System.out.print(',');
				}
				//System.out.print(')' + ',' + ' ');
				System.out.println();
			}
		
		
			 */
		
		et=System.currentTimeMillis();
		rt = et - st;
		System.out.println("Total Runtime : " + rt + "ms");
		System.out.println("Total Runtime : " + rt/1000 + "s");
		
		
	} // main method
}  // Main Class
