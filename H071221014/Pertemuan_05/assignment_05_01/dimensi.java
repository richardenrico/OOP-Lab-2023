package no1;

public class dimensi {
    //membuat atribut
    public int panjang, lebar, tinggi, jari;
    public final double phi = Math.PI;

    //method volume , luas permukaan, luas dan keliling untuk menampilkan keterangan hasil perhitungan bangun ruang atau datar
    public void volume(String bangun){
        System.out.printf("VOLUME %s ADALAH\n>", bangun);
    }
    public void luasPermukaan(String bangun){
        System.out.printf("LUAS PERMUKAAN %s ADALAH\n>", bangun);
    }
    public void luas(String bangun){
        System.out.printf("LUAS %s ADALAH\n>", bangun);
    }
    public void keliling(String bangun){
        System.out.printf("KELILING %s ADALAH\n>", bangun);
    }

    public dimensi(){

    }
    //setter dan getter untuk set nilai dan mengembalikan nilai
    public int getJari() {
        return jari;
    }
    public void setJari(int jari) {
        this.jari = jari;
    }
    public int getPanjang() {
        return panjang;
    }

    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    public int getLebar() {
        return lebar;
    }

    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    public int getTinggi() {
        return tinggi;
    }

    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    
}
