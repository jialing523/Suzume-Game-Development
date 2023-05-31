import java.util.ArrayList;
import java.util.Arrays;

public class Decryption
{
    public static void main (String [] args)
    {
        int decryptedNum=17355;
        String decryptedBinaryString = Integer.toBinaryString(decryptedNum);
        
        System.out.println("Encrypted number:\n"+decryptedNum);
        System.out.println("Converting the decimal number into binary:\n"+decryptedBinaryString);
        
        String [] binaryStringBlocks = blockDivision(decryptedBinaryString).split(",");
        System.out.println("Dividing the binary number into 3-digit block(s):\n"+Arrays.toString(binaryStringBlocks));
        
        int secretKey =7;
        System.out.println("Initial secret Key: "+secretKey);
        
        String [] decryptedResults = new String [10];
        int [] finalDecryptedResults = new int [10];
     
        
     
        for(int i=0; i<10; i++)
        {
            String [] temp = decryption(binaryStringBlocks, secretKey,i);
            String result="";
            for (int j=0; j< temp.length;j++)
            {
                result+=temp[j];
                
            }
            decryptedResults [i] = result;
            finalDecryptedResults [i] =Integer.parseInt(result, 2);
            System.out.println("Shifts by "+i+ " places: "+decryptedResults [i] + "( Decimal: "+finalDecryptedResults [i]+ " )");
        }
        
       
    }
    
    public static String blockDivision(String s)
    {
        if(s.length()==3)
        {
            return s;
        }
        else if (s.length()==2)
        {
            return "0"+s;
        }
        else if (s.length()==1)
        {
            return "00"+s;
        }
        else if (s.length()==1)
        {
            return "";
        }
        else
        {
            return s.substring(0,3)+","+blockDivision(s.substring(3));
        }
    }
    
        public static String[] decryption(String [] binaryStringBlock, int secretKey,int shiftAmount)
        {
            String [] binaryDecryptedString= new String [binaryStringBlock.length];
            
            //Decrypt from the most right block
            for(int i=binaryStringBlock.length-1; i>=0;i--)
            {
                //convert the binary String into decimal int 
                int binaryBlock_int = Integer.parseInt(binaryStringBlock[i],2);
                
                //Minus it by secretKey%2
                int decryptedBinary = binaryBlock_int-(secretKey%2);
                
                //Convert it into binary back
                String decryptedBinaryString = Integer.toBinaryString(decryptedBinary);
                
                //ensure that the block is still in 3-digits form
                switch (decryptedBinaryString.length())
                {
                    case 1:
                        binaryDecryptedString[i] = "00"+decryptedBinaryString;
                        break;
                    case 2:
                        binaryDecryptedString[i] = "0"+decryptedBinaryString;
                        break;
                    case 3:
                        binaryDecryptedString[i] = decryptedBinaryString;
                        break;
                }
                       
                //shift the secrectKey
                secretKey =  secretKey >> shiftAmount;
               
            }
            return binaryDecryptedString;
            
        }
        
    
}
