import java.io.*;
import java.util.ArrayList;

public class Parser {
    public ArrayList<String> buffer;
    public ArrayList<String> header;
    public ArrayList<String> footer;
    public ArrayList<Integer> counters;
    public ArrayList<String> headers;

    public Parser(){
        buffer=new ArrayList<>();
        counters=new ArrayList<>();
        headers=new ArrayList<>();
        header=new ArrayList<>();
        footer=new ArrayList<>();
    }

    protected int setTree(String[] s){
        int indx=-1;
        for(int i=0; i<(s.length-1);i++){
            if ((headers==null)||(headers.size()<(i+1))||(!(headers.get(i).equals(s[i])))){
                if (indx==-1) indx=i;
                if ((headers==null)||(headers.size()<(i+1))) {
                    headers.add(new String(s[i]));
                    counters.add(1);
                }
                else{
                    headers.set(i,s[i]);
                    counters.set(i,counters.get(i)+1);

                    for (int i1=0;i1<(headers.size()-(i+1));i1++){
                        headers.remove(i1+i+1);
                        counters.remove(i1+i+1);
                    }
                }
            }
            }
            return indx;
    }

    protected boolean isCurrentFolder(String[] s){
        boolean b=true;
        if ((headers==null)||(headers.size()<(s.length-1)))return false;

        for(int i=0;i<s.length-1;i++){
            if (!headers.get(i).equals(s[i])){
                b=false;
                break;
            }
                //i++;
        }
        return b;
    }

    public   void readFile(){
        try{
            FileInputStream fstream = new FileInputStream("C:/ix/filex.txt");
            InputStreamReader sr=new InputStreamReader(fstream,"windows-1251");
            BufferedReader br = new BufferedReader(sr);
            String strLine;
            while ((strLine = br.readLine()) != null){
                System.out.println(strLine);
                buffer.add(strLine);
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }
    }

    protected void readFiles(String fname, ArrayList<String> buf){
        try{
            FileInputStream fstream = new FileInputStream(fname);
            InputStreamReader sr=new InputStreamReader(fstream,"windows-1251");
            BufferedReader br = new BufferedReader(sr);
            String strLine;
            while ((strLine = br.readLine()) != null){
                System.out.println(strLine);
                buf.add(strLine);
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }
    }


    public  void writeToFile() throws IOException {
        FileOutputStream fstream = new FileOutputStream("c:/ix/note.html");
        OutputStreamWriter sr = new OutputStreamWriter(fstream, "windows-1251");
        BufferedWriter bw = new BufferedWriter(sr);

        for (String s : header) {
            try {
                bw.write(s);
                bw.write('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String strLine;
        ArrayList<String> tr=new ArrayList<>();
        for (String s : buffer) {
            String s1=getOutStr(s.split("@"));
            //String[] s1=;
            try {
                bw.write(s1);
                bw.write('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (String s : footer) {
            try {
                bw.write(s);
                bw.write('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bw.flush();
    }

    private String getNumeric(int c, int y){
        String s="";
        for(int i=0;i<=c;i++){
//            counters.size()
            if (s.length()>0)
                s=s+".";
            s=s+counters.get(i).toString();
        }
        return s;
    }

    private String getOutStr(String[] s) {
        String sx="";
        if (!isCurrentFolder(s)){
            int c=setTree(s);
            int y=0;
            for (int i=0;i<(headers.size()-c);i++){
                sx=sx+"<tr><td>"+getNumeric(c+i,y+1)+"</td><td>"+headers.get(c+i)+"<td></tr>";
                sx=sx+'\n';
                y++;
            }
        }
        sx=sx+"<tr><td>&nbsp;</td><td>"+s[s.length-1]+"<td></tr>";
        sx=sx+'\n';
        return sx;
    }

}
