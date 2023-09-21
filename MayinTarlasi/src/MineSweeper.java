import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber; // satır sayısı
    int colNumber; //sütun sayısı
    int mineNumber; // mayın sayısı
    int rndRowNumber;
    int rndColNumber;
    int row;
    int col;
    String [][] gameMap = new String[rowNumber][colNumber];
    String [][] keyMap= new String[rowNumber][colNumber];

    MineSweeper(int rowNumber,int colNumber){
        this.rowNumber=rowNumber;
        this.colNumber=colNumber;
        this.gameMap=new String[rowNumber][colNumber];
        this.keyMap= new String[rowNumber][colNumber];
        this.mineNumber = (rowNumber * colNumber) / 4;
    }
    public void map(){ // kullanıcıya sunacağımız harita
        for (int i=0;i<rowNumber;i++){
            for (int j=0;j<colNumber;j++){
                System.out.print(this.gameMap[i][j]="-");
            }
            System.out.println();
        }
    }
    public void produceMine(){// mayınları oluşturacağız
        Random rnd= new Random();
        for (int i=0;i<mineNumber;i++){
           rndRowNumber=rnd.nextInt(rowNumber);
           rndColNumber=rnd.nextInt(colNumber);
           if (!Objects.equals(this.keyMap[rndRowNumber][rndColNumber],"*")){
               this.keyMap[rndRowNumber][rndColNumber]="*";
           }else
               i--;
        }
        for (int i=0;i<rowNumber;i++){
            for (int j=0;j<colNumber;j++){
                if (!Objects.equals(keyMap[i][j],"*")){
                    this.keyMap[i][j]=("-");
                }
            }
        }
    }
    void printKeyMap(){
        produceMine(); // oluşturduğumuz mayınları burada çağırıyoruz
        for(int i = 0; i < rowNumber; i++){ // ve burada da yazdırıyoruz
            for(int j = 0; j < colNumber; j++){
                System.out.print(this.keyMap[i][j]);
            }
            System.out.println();
        }
    }
    void run(){
        System.out.println("MAYINLARIN KONUMU :");
        printKeyMap();
        System.out.println("MAYIN TARLASI OYUNUNA HOŞGELDİN!!");
        map();
        Scanner input= new Scanner(System.in);
        int right=(this.rowNumber*this.colNumber)-mineNumber;
        while (right>0){
            System.out.print("satır giriniz: ");
            row=input.nextInt();
            System.out.println();
            System.out.println("sütun giriniz: ");
            col=input.nextInt();
            if((row<0 || row>=this.rowNumber) || (col<0 || col>=this.colNumber)){
                System.out.println("Hatalı giriş yaptınız indis numarasını tekrar giriniz ! ");
                continue;
            }if (keyMap[row][col].equals("*")) {
                System.out.println("GAME OVER");
                break;
            }else{
                if (!this.keyMap[row][col].equals("-")){
                    System.out.println("Bu hamleyi zaten yaptınız");
                } else {
                    int point=0;// point dediğimiz kulanıcının seçtiği bölgenin etrafındaki mayın sayısını gösteren değer olacak
                    int lowRow=(row-1) ; int heighRow=(row+1);// satır sayısının 1 eksiği ve 1 fazlasına bakacağız
                    int lowcol=(col-1); int heighCol=(col+1);// sütun sayısının 1 eksiği ve 1 fazlasına bakacağız

                    if (lowRow<0){ // eğer satırın(row) 1 eksiği 0dan küçük oluyorsa sınırı aşmamak için 0a eşitliyoruz.
                        lowRow=0;
                    }
                    if (lowcol<0){// üsttekinin aynısını yaptık
                        lowcol=0;
                    }
                    if (heighRow>=rowNumber){// eğer satırın(row) 1 fazlası satır sayısından fazla oluyorsa satıra eşitliyoruz.sınırları korumak için
                        heighRow=row;
                    }
                    if (heighCol>=colNumber){// üsttekinin aynısını yaptık
                        heighCol=col;
                    }
                    for (int i=lowRow;i<=heighRow;i++){// bu iç içe döngüde etrafında mayın var mı diye kontrol ediyoruz
                        for (int j=lowcol;j<=heighCol;j++){
                            if (this.keyMap[i][j].equals("*")){
                                point++;// eğer varsa point 1 artıyor ve kullanıcı seçtiği yerin etrafındaki mayın sayılarını görüyor
                            }
                        }
                    }
                    String pointCounter= Integer.toString(point);
                    this.gameMap[row][col]=pointCounter;
                    right--;

                    for (int i=0; i< gameMap.length; i++){
                        for (int j=0; j<gameMap[i].length; j++){
                            System.out.print(this.gameMap[i][j]);
                        }System.out.println();
                    }
                    System.out.println("============================================");

                }

            }
        }
        if (right==0){
            System.out.println("OYUNU KAZANDINIZ!!");
        }

    }
    }
