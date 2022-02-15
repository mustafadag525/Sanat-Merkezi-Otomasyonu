package com.company;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AnaSayfa {
    public static void main(String[] args) throws IOException {

        Scanner scanner=new Scanner(System.in);
        ArrayList<Kursiyer>Kursiyer1=read_kursiyers();
        ArrayList<Kurs>Kurs1=read_kurs();

        while (true)
        {
            System.out.println("1. Kurs Ekle");
            System.out.println("2. Kurs Listele");
            System.out.println("3. Kursiyer Ekle");
            System.out.println("4. Kursiyer Ara");
            System.out.println("5. Kursiyer Sil");
            System.out.println("6. Kursiyerleri Listele");
            System.out.println("7. Kursiyerleri Ayrıntılı Listele");
            System.out.println("8. Kursiyerlerin Ödeyeceği Tutarı Hesapla");
            System.out.println("9. Çıkış");

            System.out.println("İşlem Seç: ");
            int islem=scanner.nextInt();
            scanner.nextLine();
            switch (islem)
            {

                case 1 -> KursEkle(Kurs1);
                case 2 -> KursListele(Kurs1);
                case 3 -> KursiyerEkle(Kursiyer1,Kurs1);
                case 4 -> KursiyerAra(Kursiyer1);
                case 5 -> KursiyerSil(Kursiyer1);
                case 6 -> KursiyerleriListele(Kursiyer1);
                case 7 -> KursiyerAyrintiliListele(Kursiyer1);
                case 8 -> KursiyerHarcama(Kursiyer1);
                case 9 -> Kapat(Kursiyer1,Kurs1);
                default -> System.out.println("Yanlış işlem !! , Lütfen tekrar deneyin \n");

            }
        }
    }

    private static  void Kapat(ArrayList<Kursiyer> kursiyer1,ArrayList<Kurs>kurs1) throws IOException{

        write_kursiyer1(kursiyer1);
        write_kurs1(kurs1);
        System.exit(1);
    }

    private static void KursiyerHarcama(ArrayList<Kursiyer>kursiyer1){

        Scanner scanner=new Scanner(System.in);
        System.out.println("öğrenci id girin: ");
        int id=scanner.nextInt();
        scanner.nextLine();
        for (Kursiyer kursiyer:kursiyer1){

            if (kursiyer.getKursiyerID()==id){
                double toplam=0;
                int birimfiyat=100;
                if (kursiyer.kurs1.size()==1)
                {
                    System.out.println("Toplam Kurs:1");
                    System.out.println("İndirim Yok");
                    toplam=birimfiyat;

                }
                else if (kursiyer.kurs1.size()==2)
                {
                    System.out.println("Toplam Kurs:2");
                    System.out.println("kamoanya:1");
                    toplam=birimfiyat + birimfiyat * 0.85;
                }
                else if(kursiyer.kurs1.size()==3)
                {
                    System.out.println("Toplam Kurs:3");
                    System.out.println("Kampanya:2");
                    toplam= 2 * birimfiyat + birimfiyat * 0.75;
                }
                else if(kursiyer.kurs1.size()>3)
                {
                    System.out.println("toplam kurs: "+kursiyer.kurs1.size());
                    System.out.println("kampanya 3");
                    toplam=kursiyer.kurs1.size()*birimfiyat*0.9;
                }
                birimfiyat*=4;
                System.out.println("toplam: "+toplam);
                return;
            }
        }
        System.out.println("kursiyer bulunmadı");

    }

    private static void KursiyerAyrintiliListele(ArrayList<Kursiyer>kursiyers){
        for (Kursiyer kursiyer:kursiyers)
        {
            Kursiyer_Ayrintili_Listele(kursiyer);
        }
    }

    private static void KursiyerleriListele(ArrayList<Kursiyer>kursiyers)
    {
        for (Kursiyer kursiyer:kursiyers)
        {
            System.out.println("ID: "+kursiyer.getKursiyerID()+" AD: "+kursiyer.getKursiyerAD()+" Yas: "+kursiyer.getKursiyerYas());
        }
    }

    private static void KursiyerSil(ArrayList<Kursiyer>kursiyers)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("kursiyer id'yi girin: ");
        int id=scanner.nextInt();
        scanner.nextLine();
        for (Kursiyer kursiyer:kursiyers)
        {
            kursiyers.remove(kursiyer);
            System.out.println("Kursiyer Silindi");
            return;
        }
        System.out.println("kursiyer bulunamadı");
    }

    private static void KursiyerAra(ArrayList<Kursiyer>kursiyers){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Kursiyer adı girin: ");
        String ad=scanner.nextLine();
        for (Kursiyer kursiyer:kursiyers)
        {
            if (kursiyer.getKursiyerAD().equalsIgnoreCase(ad)){
                Kursiyer_Ayrintili_Listele(kursiyer);
                return;
            }
        }
        System.out.println("kursiyer bulunamadı");
    }


    private static void Kursiyer_Ayrintili_Listele(Kursiyer kursiyer)
    {
        System.out.println("Id:"+kursiyer.getKursiyerID()+"ad: "+kursiyer.getKursiyerAD()+"yas: "+ kursiyer.getKursiyerYas());
        for (Kurs kurs:Kursiyer.kurs1)
        {
            System.out.println("id: "+kurs.getKursID()+" ad: "+kurs.getKursAD());
        }
    }


    private static void KursiyerEkle(ArrayList<Kursiyer>kursiyers,ArrayList<Kurs>kurs)
    {
        Scanner scanner=new Scanner(System.in);
        int id;
        int bulundumu;
        while (true)
        {
            bulundumu=0;
            System.out.println("kursiyer id'yi girin: ");
            id=scanner.nextInt();
            scanner.nextLine();
            for (Kursiyer kursiyer:kursiyers)
            {
                if (kursiyer.getKursiyerID()==id)
                {
                    bulundumu=1;
                    break;
                }
            }
            if (bulundumu!=1)
            {
                String ad;
                int i;
                int yas,kurs_count,ekle,c_id;
                System.out.println("kursiyer adı girin: ");
                ad=scanner.nextLine();
                System.out.println("kursiyer yaşı girin: ");
                scanner.nextInt();
                scanner.nextLine();

                Kursiyer kursiyer=new Kursiyer(id,ad,yas);
                System.out.println("kurs adeti girin: ");
                kurs_count=scanner.nextInt();
                scanner.nextLine();

                for (i=0;i<kurs_count;i++)
                {
                    ekle=0;
                    System.out.println((i+1)+" kurs id: ");
                    c_id=scanner.nextInt();
                    scanner.nextLine();
                    for (Kurs kurs1:kurs)
                    {
                        if (kurs1.getKursID()==c_id)
                            kursiyer.kurs1.add(kurs1);
                        ekle=1;
                        break;
                    }
                }
                if (ekle==0){
                    System.out.println("kurs bulunamdı");
                     i--;
                }
            }
            kursiyers.add(kursiyer);
            break;

        }
        else
        {
            System.out.println("zaten kayıtlı id!!!");
        }

    }

    private static void KursListele(ArrayList<Kurs>kurs)
    {
        for (Kurs kurs1:kurs)
        {
            System.out.println("id: "+kurs1.getKursID()+" ad :"+kurs1.getKursAD());
        }
    }

    private static void KursEkle(ArrayList<Kurs>kurs)
    {
        Scanner scanner=new Scanner(System.in);
        int id;
        int bul;
        while (true)
        {
            bul=0;
            System.out.println("kurs id'si girin: ");
            id=scanner.nextInt();
            scanner.nextLine();
            for (Kurs kurs1:kurs)
            {
                if (kurs1.getKursID()==id)
                {
                    bul=1;
                    break;
                }
            }
            if (bul!=1)
            {
                System.out.println("kurs adı gir: ");
                kurs.add(new Kurs(id,scanner.nextLine()));
                break;
            }else
            {
                System.out.println("id zaten bulunuyor");
            }
        }
        System.out.println("kurs başarı ile eklendi");
    }

    private static FileWriter file_writer(String ad) throws IOException {
        File file = new File(ad);
        return new FileWriter(file, false);
    }

    private static void write_kurs1(ArrayList<Kurs>kurs) throws IOException {
        FileWriter fr = file_writer("kurs.txt");
        StringBuilder content = new StringBuilder();
        for (Kurs kurs1: kurs) {
            content.append(kurs1.getKursID()).append("-").append(kurs1.getKursAD()).append("\n");
        }
        fr.write(content.toString());
        fr.close();
    }


    private static void write_kursiyer1(ArrayList<Kursiyer> kursiyers) throws IOException {
        FileWriter fr = file_writer("kursiyer.txt");
        StringBuilder content = new StringBuilder();
        for (Kursiyer kursiyer: kursiyers) {
            content.append("#").append(kursiyer.getKursiyerID()).append("-").append(kursiyer.getKursiyerAD()).append("-").append(kursiyer.getKursiyerYas()).append("\n");
            for (Kurs kurs: kursiyer.kurs1) {
                content.append("*").append(kurs.getKursID()).append("-").append(kurs.getKursAD()).append("\n");
            }
        }
        fr.write(content.toString());
        fr.close();
    }

    private static ArrayList<Kurs> read_kurs() throws IOException {
        ArrayList<Kurs> kurs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("kurs.txt"));
        String temp;
        String[] Temp;
        while ((temp = reader.readLine()) != null) {
            Temp = temp.split("-");
            kurs.add(new Kurs(Integer.parseInt(Temp[0]), Temp[1]));
        }
        reader.close();
        return kurs;
    }


    private static ArrayList<Kursiyer> read_kursiyers() throws IOException {
        ArrayList<Kursiyer> kursiyers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("kursiyer.txt"));
        Kursiyer kursiyer;
        String temp;
        String[] Temp;
        while ((temp = reader.readLine()) != null) {
            if (temp.contains("#")) {
                temp = temp.substring(1);
                Temp = temp.split("-");
                kursiyer = new Kursiyer(Integer.parseInt(Temp[0]), Temp[1], Integer.parseInt(Temp[2]));
                kursiyers.add(kursiyer);
            } else {
                temp = temp.substring(1);
                Temp = temp.split("-");
                kursiyers.get(kursiyers.size() - 1).kurs1.add(new Kurs(Integer.parseInt(Temp[0]), Temp[1]));
            }
        }
        reader.close();
        return kursiyers;
    }


}
