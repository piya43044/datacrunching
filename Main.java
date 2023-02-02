import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {

        BufferedReader bf1=new BufferedReader(new FileReader("F:\\Git\\data_crunching\\src\\user_email_hash.1m.tsv"));
        BufferedReader bf2=new BufferedReader(new FileReader("F:\\Git\\data_crunching\\src\\ip_1m.tsv"));
       // BufferedReader bf3=new BufferedReader(new FileReader("F:\\Git\\data_crunching\\src\\plain.txt"));

        FileWriter newfile=new FileWriter("F:\\Git\\data_crunching\\src\\outputfile.tsv",true);
        BufferedWriter bfw=new BufferedWriter(newfile);
        String s1;
        String s2;
        String s3;
        String space="          ";

        int i=0;

        while((s1=bf1.readLine())!=null)
        {

            String[] split1=s1.split("\\s",0);
            String id=split1[0];
            String username=split1[1];
            String email=split1[2];
            String hash_password=split1[3];

            bfw.write(id + space + username + space + email + space + hash_password);


            if(i==1)
            {
                int flag=0;
                BufferedReader bf3=new BufferedReader(new FileReader("F:\\Git\\data_crunching\\src\\plain_32m.tsv"));
                while((s3=bf3.readLine())!=null)
                {
                    String[] split3=s3.split("\\s",0);

                    String email_plain=split3[0];
                    String plaintext_password=split3[1];


                    if(email.equals(email_plain))
                    {


                        bfw.write(space + plaintext_password);
                        flag=1;
                        break;
                    }
                }
                if(flag==0)
                {
                    bfw.write(space + space);
                }
                bf3.close();

            }
            else{
                bfw.write(space + "plaintext_password");
                i++;
            }
            while((s2=bf2.readLine())!=null)
            {
                String[] split2=s2.split("\\s",0);
                String id_ip=split2[0];
                String ip=split2[1];
                if(id.equals(id_ip))
                {

                    //Write the content in new file
                    bfw.write(space + ip +"\n");
                    break;
                }
            }


        }


        System.out.println("Successfully.....");
        bf1.close();
        bf2.close();
        //bf3.close();
        bfw.close();
    }
}