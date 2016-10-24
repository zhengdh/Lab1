import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Lab1

class Terms{//������һ����+Ϊ�ֽ��ߵ�term
	int nums = 1;
	String tempnums;
	String vars;
	int varflag = 0;//�����б�������1
	int flag = 0;//����ʽ�Ϸ�����1
	int total = 0;//term�ĸ���
}
public class Calculator {
	public static Terms[] expression(String polynomial){
		
		//System.out.println(polynomial);
		
		Pattern p = Pattern.compile("[A-Za-z0-9+*]+");
        Matcher m = p.matcher(polynomial);
        
        int not = 1;//number of terms
        int length = polynomial.length();
        
        for (int i=0; i<length; i++){//����term����
        	if(polynomial.charAt(i) == '+')
        	{
        		not ++;
        	}
        }
        //System.out.println(not);
        Terms[] term = new Terms[not];
        for (int i=0; i<not; i++){
        	term[i] = new Terms();
        }
        term[0].total = not;
        
        if(!m.matches()){//�������Ϸ���1
        	System.out.println("�Ƿ����룺����ʽ������[A-Za-z0-9+*]");
        	return term;
        }
        for (int i=0; i<length-1; i++){//�������Ϸ���2
        	if(((polynomial.charAt(i) >= '0' && polynomial.charAt(i) <= 'z') && (polynomial.charAt(i+1) >= 'A' && polynomial.charAt(i+1) <= 'z')) || ((polynomial.charAt(i) >= 'A' && polynomial.charAt(i) <= 'z') && (polynomial.charAt(i+1) >= '0' && polynomial.charAt(i+1) <= 'z'))){
        		System.out.println("�Ƿ����룺�������ʹ��*����");
        		return term;
        	}
        }
        for (int i=0; i<length-1; i++){//�������Ϸ���3
        	if((polynomial.charAt(i) == '+' && polynomial.charAt(i+1) == '+') || (polynomial.charAt(i) == '*' && polynomial.charAt(i+1) == '*') || (polynomial.charAt(length-1) == '+' || polynomial.charAt(length-1) == '*') || (polynomial.charAt(0) == '+' || polynomial.charAt(0) == '*')){
        		System.out.println("�Ƿ����룺����ʽ��Ч");
        		return term;
        	}
        }
        term[0].flag = 1;
        

        
        int count = 0;
        for (int i=0; i<length; i++){//����term����
        	if(polynomial.charAt(i) == '+')
        	{
        		count ++;
        	}
        	else if(polynomial.charAt(i) >= '0' && polynomial.charAt(i) <= '9'){
        		if(i == length - 1){
        			if(term[count].tempnums == null){
        				term[count].tempnums = String.valueOf(polynomial.charAt(i));
        				term[count].nums = term[count].nums * Integer.parseInt(term[count].tempnums);
        				term[count].tempnums = null;
        			}
        			else{
        				term[count].tempnums = term[count].tempnums + String.valueOf(polynomial.charAt(i));
    					term[count].nums = term[count].nums * Integer.parseInt(term[count].tempnums);
                		term[count].tempnums = null;
        			}
        		}
        		else{
        			if(polynomial.charAt(i+1) == '+' || polynomial.charAt(i+1) == '*'){
        				if(term[count].tempnums == null){
            				term[count].tempnums = String.valueOf(polynomial.charAt(i));
            				term[count].nums = term[count].nums * Integer.parseInt(term[count].tempnums);
            				term[count].tempnums = null;
            			}
            			else{
            				term[count].tempnums = term[count].tempnums + String.valueOf(polynomial.charAt(i));
        					term[count].nums = term[count].nums * Integer.parseInt(term[count].tempnums);
                    		term[count].tempnums = null;
            			}
        			}
        			else{
        				if(term[count].tempnums == null){
            				term[count].tempnums = String.valueOf(polynomial.charAt(i));
            			}
            			else{
            				term[count].tempnums = term[count].tempnums + String.valueOf(polynomial.charAt(i));
            			}
        			}
        		}
        	}
        	else if(polynomial.charAt(i) >= 'A' && polynomial.charAt(i) <= 'z'){
        		if(term[count].varflag == 0){
        			term[count].vars = String.valueOf(polynomial.charAt(i));
        		}
        		else
        			term[count].vars = term[count].vars + String.valueOf(polynomial.charAt(i));
        			term[count].varflag = 1;
        	}
        	else{
        		continue;}
        }
		return term;
	}
	
	public static Terms[] simplify(Terms[] term){
		return term;
	}
	
	public static Terms[] derivative(Terms[] term){
		return term;
	}
	
	public static void main(String [] args) {
		Scanner input = new Scanner(System.in); 
        while (true) { 
        	
        		System.out.println("��������ʽ:");
                String polynomial = input.nextLine(); 
                if (polynomial.equals("exit")) break; 
                //System.out.println(polynomial);
                Terms[] term = expression(polynomial);
                
                if(term[0].flag == 0) continue;
                
                System.out.println(term[0].nums);
                System.out.println(term[0].vars);   
        }
        input.close();
	}

}
